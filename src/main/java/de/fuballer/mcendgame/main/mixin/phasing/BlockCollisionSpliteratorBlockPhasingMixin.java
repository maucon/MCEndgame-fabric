package de.fuballer.mcendgame.main.mixin.phasing;

import com.llamalad7.mixinextras.sugar.Local;
import de.fuballer.mcendgame.main.component.custom_attribute.CustomAttributesExtensions;
import de.fuballer.mcendgame.main.component.tags.CustomTags;
import de.fuballer.mcendgame.main.util.extension.WorldExtension;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockCollisionSpliterator;
import net.minecraft.world.CollisionView;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.BiFunction;

@Mixin(BlockCollisionSpliterator.class)
public class BlockCollisionSpliteratorBlockPhasingMixin<T> {
    @Shadow
    @Final
    private ShapeContext context;
    @Shadow
    @Final
    private BlockPos.Mutable pos;
    @Unique
    private boolean blockPhasing = false;

    @Inject(
            method = "<init>(Lnet/minecraft/world/CollisionView;Lnet/minecraft/entity/Entity;Lnet/minecraft/util/math/Box;ZLjava/util/function/BiFunction;)V",
            at = @At("TAIL")
    )
    void init(
            CollisionView world,
            @Nullable Entity entity,
            Box box,
            boolean forEntity,
            BiFunction<BlockPos.Mutable, VoxelShape, T> resultFunction,
            CallbackInfo ci
    ) {
        if (!(entity instanceof LivingEntity livingEntity)) return;
        if (WorldExtension.INSTANCE.isDungeonWorld(livingEntity.getWorld())) return;
        blockPhasing = CustomAttributesExtensions.INSTANCE.isBlockPhasing(livingEntity);
    }

    @ModifyVariable(
            method = "computeNext",
            at = @At(value = "STORE", ordinal = 0),
            ordinal = 0
    )
    private VoxelShape filterVoxelShape(
            VoxelShape voxelShape,
            @Local BlockState blockState
    ) {
        if (!blockPhasing) return voxelShape;
        if (blockState.isIn(CustomTags.INSTANCE.getPHASING_BLOCKING())) return voxelShape;

        var collisionShape = VoxelShapes.empty();
        if (context.isDescending()) return collisionShape;

        for (Box box : voxelShape.getBoundingBoxes()) {
            var boxShape = VoxelShapes.cuboid(box);
            if (!context.isAbove(boxShape, pos, true)) continue;

            collisionShape = VoxelShapes.union(collisionShape, boxShape);
        }
        return collisionShape;
    }
}

package de.fuballer.mcendgame.main.mixin.phasing;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import de.fuballer.mcendgame.main.component.custom_attribute.CustomAttributesExtensions;
import de.fuballer.mcendgame.main.component.tags.CustomTags;
import de.fuballer.mcendgame.main.util.extension.WorldExtension;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.EntityShapeContext;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(AbstractBlock.AbstractBlockState.class)
public class AbstractBlockStateBlockPhasingMixin {
    @Unique
    private static final double MIN_PITCH_FOR_GROUND_PHASING = 85;

    @ModifyReturnValue(
            method = "getCollisionShape(Lnet/minecraft/world/BlockView;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/ShapeContext;)Lnet/minecraft/util/shape/VoxelShape;",
            at = @At("RETURN")
    )
    VoxelShape getBlockPhasingCollisionShape(
            VoxelShape original,
            @Local(argsOnly = true) BlockView world,
            @Local(argsOnly = true) BlockPos pos,
            @Local(argsOnly = true) ShapeContext context
    ) {
        if (!(context instanceof EntityShapeContext entityContext)) return original;
        if (!(entityContext.getEntity() instanceof LivingEntity entity)) return original;
        if (WorldExtension.INSTANCE.isDungeonWorld(entity.getEntityWorld())) return original;
        if (!CustomAttributesExtensions.INSTANCE.hasBlockPhasing(entity)) return original;

        var blockState = world.getBlockState(pos);
        if (blockState.isIn(CustomTags.INSTANCE.getPHASING_BLOCKING())) return original;

        var collisionShape = VoxelShapes.empty();
        if (context.isDescending() && entity.getPitch() >= MIN_PITCH_FOR_GROUND_PHASING) return collisionShape;

        for (Box box : original.getBoundingBoxes()) {
            var boxShape = VoxelShapes.cuboid(box);
            if (!context.isAbove(boxShape, pos, true)) continue;

            collisionShape = VoxelShapes.union(collisionShape, boxShape);
        }
        return collisionShape;
    }
}

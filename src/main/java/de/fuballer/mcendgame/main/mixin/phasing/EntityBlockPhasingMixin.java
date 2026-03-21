package de.fuballer.mcendgame.main.mixin.phasing;

import de.fuballer.mcendgame.main.component.custom_attribute.CustomAttributesExtensions;
import de.fuballer.mcendgame.main.util.extension.EntityExtension;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MovementType;
import net.minecraft.util.math.Vec3d;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(Entity.class)
public class EntityBlockPhasingMixin {
    @Shadow
    protected Vec3d movementMultiplier;
    @Unique
    private Vec3d blockPhasingMovementMultiplier = new Vec3d(0.5, 1.0, 0.5);

    @ModifyVariable(
            method = "move",
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/entity/Entity;movementMultiplier:Lnet/minecraft/util/math/Vec3d;",
                    ordinal = 0,
                    opcode = Opcodes.GETFIELD
            ),
            ordinal = 0,
            argsOnly = true
    )
    Vec3d slowMovementWhenBlockPhasing(Vec3d movement) {
        var entity = (Entity) (Object) this;
        if (!(entity instanceof LivingEntity livingEntity)) return movement;
        if (!CustomAttributesExtensions.INSTANCE.hasBlockPhasing(livingEntity)) return movement;
        if (!EntityExtension.INSTANCE.isBlockPhasing(livingEntity)) return movement;
        return movement.multiply(blockPhasingMovementMultiplier);
    }

    @Inject(
            method = "move",
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/entity/Entity;movementMultiplier:Lnet/minecraft/util/math/Vec3d;",
                    ordinal = 0,
                    opcode = Opcodes.GETFIELD
            )
    )
    void ignoreMovementMultiplierWhenBlockPhasing(MovementType type, Vec3d movement, CallbackInfo ci) {
        var entity = (Entity) (Object) this;
        if (!(entity instanceof LivingEntity livingEntity)) return;
        if (!CustomAttributesExtensions.INSTANCE.hasBlockPhasing(livingEntity)) return;
        movementMultiplier = Vec3d.ZERO;
    }
}

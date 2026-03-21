package de.fuballer.mcendgame.main.mixin.phasing;

import de.fuballer.mcendgame.main.component.custom_attribute.CustomAttributesExtensions;
import de.fuballer.mcendgame.main.component.tags.CustomTags;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class LivingEntityBlockPhasingMixin {
    @Inject(
            method = "isInvulnerableTo",
            at = @At("HEAD"),
            cancellable = true)
    void isInvulnerableTo(
            ServerWorld world,
            DamageSource source,
            CallbackInfoReturnable<Boolean> cir
    ) {
        if (!source.isIn(CustomTags.INSTANCE.getBLOCK_PHASING_IMMUNE())) return;

        var livingEntity = (LivingEntity) (Object) this;
        if (!CustomAttributesExtensions.INSTANCE.hasBlockPhasing(livingEntity)) return;
        cir.setReturnValue(true);
    }
}

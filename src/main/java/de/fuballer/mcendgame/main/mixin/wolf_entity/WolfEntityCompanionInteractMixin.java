package de.fuballer.mcendgame.main.mixin.wolf_entity;

import de.fuballer.mcendgame.main.util.extension.mixin.EntityMixinExtension;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(WolfEntity.class)
public class WolfEntityCompanionInteractMixin {
    @Inject(method = "interactMob", at = @At("HEAD"), cancellable = true)
    void interactMob(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
        var wolf = (WolfEntity) (Object) this;
        if (!EntityMixinExtension.INSTANCE.isCompanion(wolf)) return;

        cir.setReturnValue(ActionResult.SUCCESS);
    }
}

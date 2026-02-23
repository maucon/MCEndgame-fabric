package de.fuballer.mcendgame.main.mixin.server_player_entity;

import de.fuballer.mcendgame.main.messaging.misc.PlayerBeforeDimensionChangeEvent;
import de.maucon.mauconframework.event.EventGateway;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.TeleportTarget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ServerPlayerEntity.class)
public class ServerPlayerEntityTeleportEventMixin {
    @Inject(
            method = "teleportTo(Lnet/minecraft/world/TeleportTarget;)Lnet/minecraft/server/network/ServerPlayerEntity;",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/server/world/ServerWorld;getLevelProperties()Lnet/minecraft/world/WorldProperties;")
    )
    void teleportCrossDimension(TeleportTarget teleportTarget, CallbackInfoReturnable<ServerPlayerEntity> cir) {
        var entity = (ServerPlayerEntity) (Object) this;
        if (!(entity.getEntityWorld() instanceof ServerWorld world)) return;

        var event = new PlayerBeforeDimensionChangeEvent(entity, world, teleportTarget);
        EventGateway.INSTANCE.launchPublish(event);
    }
}

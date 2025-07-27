package de.fuballer.mcendgame.main.mixin.server_player_entity;

import de.fuballer.mcendgame.main.util.extension.mixin.PlayerEntityMixinExtension;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayerEntity.class)
public class ServerPlayerEntityDeathNBTCopyMixin {
    /*
    Minecraft creates a new ServerPlayerEntity object instance on player death,
    so we need to update our values here.
     */
    @Inject(method = "copyFrom", at = @At(value = "TAIL"))
    void copyFrom(ServerPlayerEntity oldPlayer, boolean alive, CallbackInfo ci) {
        var player = (ServerPlayerEntity) (Object) this;

        PlayerEntityMixinExtension.INSTANCE.setDungeonLevel(player, PlayerEntityMixinExtension.INSTANCE.getDungeonLevel(oldPlayer));
        PlayerEntityMixinExtension.INSTANCE.setDungeonSeed(player, PlayerEntityMixinExtension.INSTANCE.getDungeonSeed(oldPlayer));
    }
}

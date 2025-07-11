package de.fuballer.mcendgame.main.mixin.world;

import de.fuballer.mcendgame.main.accessor.ServerWorldDungeonWorldAccessor;
import de.fuballer.mcendgame.main.component.item.custom.aspect.AspectItem;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

import java.util.HashMap;

@Mixin(ServerWorld.class)
public class ServerWorldDungeonWorldMixin implements ServerWorldDungeonWorldAccessor {
    @Unique
    private HashMap<AspectItem, Integer> aspects = new HashMap<>();

    @Override
    public void mcendgame$setAspects(HashMap<AspectItem, Integer> aspects) {
        this.aspects = aspects;
    }

    @Override
    public HashMap<AspectItem, Integer> mcendgame$getAspects() {
        return aspects;
    }
}

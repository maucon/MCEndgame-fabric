package de.fuballer.mcendgame.main.mixin.world;

import de.fuballer.mcendgame.main.accessor.ServerWorldDungeonWorldAccessor;
import de.fuballer.mcendgame.main.component.item.custom.aspect.AspectItem;
import de.fuballer.mcendgame.main.component.item.custom.aspect.AspectItems;
import de.fuballer.mcendgame.main.component.item.custom.aspect.item.AspectOfTyranny;
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
    public int mcendgame$getAdditionalElitesCount() {
        var item = AspectItems.INSTANCE.getASPECT_OF_TYRANNY();
        if (!aspects.containsKey(item)) return 0;
        return aspects.get(item) * AspectOfTyranny.ADDITIONAL_ELITES;
    }
}

package de.fuballer.mcendgame.client.mixin.stats;

import de.fuballer.mcendgame.main.MCEndgame;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.client.gui.screen.StatsScreen;
import net.minecraft.stat.Stat;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(StatsScreen.GeneralStatsListWidget.class)
public class GeneralStatsListWidgetMixin {
    @ModifyVariable(
            method = "<init>",
            at = @At("STORE"),
            ordinal = 0
    )
    private ObjectArrayList<Stat<Identifier>> filterModStats(ObjectArrayList<Stat<Identifier>> list) {
        list.removeIf(stat -> stat.getValue().getNamespace().equals(MCEndgame.MOD_ID));
        return list;
    }
}

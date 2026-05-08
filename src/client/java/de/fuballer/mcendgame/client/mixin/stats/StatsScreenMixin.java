package de.fuballer.mcendgame.client.mixin.stats;

import de.fuballer.mcendgame.client.component.screen.CustomStatsListWidget;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.StatsScreen;
import net.minecraft.client.gui.tab.Tab;
import net.minecraft.client.gui.widget.ThreePartsLayoutWidget;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(StatsScreen.class)
public class StatsScreenMixin {
    @Shadow
    @Final
    ThreePartsLayoutWidget layout;

    @ModifyArg(
            method = "onStatsReady",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/widget/TabNavigationWidget$Builder;tabs([Lnet/minecraft/client/gui/tab/Tab;)Lnet/minecraft/client/gui/widget/TabNavigationWidget$Builder;"
            )
    )
    private Tab[] addModTab(Tab[] originalTabs) {
        StatsScreen self = (StatsScreen) (Object) this;

        Tab[] newTabs = new Tab[originalTabs.length + 1];
        System.arraycopy(originalTabs, 0, newTabs, 0, originalTabs.length);
        newTabs[originalTabs.length] = self.new StatsTab(
                Text.translatable("container.mcendgame.stats_screen.tab.title"),
                new CustomStatsListWidget(MinecraftClient.getInstance(), self.width, layout.getContentHeight())
        );
        return newTabs;
    }
}

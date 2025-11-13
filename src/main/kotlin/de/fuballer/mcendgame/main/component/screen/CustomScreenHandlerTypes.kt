package de.fuballer.mcendgame.main.component.screen

import de.fuballer.mcendgame.main.component.block.crystalforge.CrystalForgeBlock
import de.fuballer.mcendgame.main.component.block.crystalforge.CrystalForgeScreenHandler
import de.fuballer.mcendgame.main.component.block.dungeon_device.DungeonDeviceBlock
import de.fuballer.mcendgame.main.component.block.dungeon_device.DungeonDeviceScreenHandler
import de.fuballer.mcendgame.main.component.block.dungeon_device.networking.DungeonDevicePayload
import de.fuballer.mcendgame.main.component.killer.KillerScreenHandler
import de.fuballer.mcendgame.main.component.killer.networking.KillerEntityPayload
import de.fuballer.mcendgame.main.component.totem.TotemScreenHandler
import de.fuballer.mcendgame.main.util.minecraft.RegistryUtil
import de.maucon.mauconframework.di.annotation.Injectable
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType
import net.minecraft.resource.featuretoggle.FeatureFlags
import net.minecraft.screen.ScreenHandlerType

@Injectable
object CustomScreenHandlerTypes {
    val DUNGEON_DEVICE = ExtendedScreenHandlerType(
        { syncId, inventory, payload -> DungeonDeviceScreenHandler(syncId, inventory, payload = payload) },
        DungeonDevicePayload.CODEC,
    ).also { RegistryUtil.registerScreenHandler(DungeonDeviceBlock.ID, it) }

    val KILLER = ExtendedScreenHandlerType(
        { syncId, inventory, payload -> KillerScreenHandler(syncId, inventory, payload = payload) },
        KillerEntityPayload.CODEC,
    ).also { RegistryUtil.registerScreenHandler("killer", it) }

    val CRYSTAL_FORGE = ScreenHandlerType<CrystalForgeScreenHandler>(
        ::CrystalForgeScreenHandler, FeatureFlags.VANILLA_FEATURES,
    ).also { RegistryUtil.registerScreenHandler(CrystalForgeBlock.ID, it) }

    val TOTEM = ScreenHandlerType<TotemScreenHandler>(
        ::TotemScreenHandler, FeatureFlags.VANILLA_FEATURES,
    ).also { RegistryUtil.registerScreenHandler("totem", it) }
}
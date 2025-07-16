package de.fuballer.mcendgame.main.component.dungeon.device

import de.fuballer.mcendgame.main.component.dungeon.device.networking.DungeonDevicePayload
import de.fuballer.mcendgame.main.component.dungeon.device.screen.DungeonDeviceScreenHandler
import de.fuballer.mcendgame.main.component.dungeon.player.DungeonPlayerEntity.Companion.toDungeonPlayerEntity
import de.fuballer.mcendgame.main.functional.inventory.ImplementedInventory
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory
import net.minecraft.block.BlockState
import net.minecraft.block.entity.BlockEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.inventory.Inventories
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NbtCompound
import net.minecraft.registry.RegistryWrapper.WrapperLookup
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.text.Text
import net.minecraft.util.collection.DefaultedList
import net.minecraft.util.math.BlockPos

private val TITLE = Text.translatable("container.mcendgame.dungeon_device.title")

class DungeonDeviceBlockEntity(
    blockPos: BlockPos,
    blockState: BlockState,
) : BlockEntity(DungeonDevice.BLOCK_ENTITY_TYPE, blockPos, blockState), ExtendedScreenHandlerFactory<DungeonDevicePayload>, ImplementedInventory {
    private val inventory = DefaultedList.ofSize(DungeonDeviceSettings.INVENTORY_SIZE, ItemStack.EMPTY)

    override fun getItems(): DefaultedList<ItemStack> = inventory

    override fun createMenu(syncId: Int, playerInventory: PlayerInventory, player: PlayerEntity) = DungeonDeviceScreenHandler(syncId, playerInventory, this)

    override fun getDisplayName(): Text = TITLE

    override fun getScreenOpeningData(player: ServerPlayerEntity): DungeonDevicePayload {
        val dungeonPlayer = player.toDungeonPlayerEntity()
        return DungeonDevicePayload(pos, world!!.registryKey, player.uuid, dungeonPlayer.dungeonLevel)
    }

    override fun markDirty() = super<ImplementedInventory>.markDirty(world, pos)

    override fun readNbt(nbt: NbtCompound, registryLookup: WrapperLookup) {
        super.readNbt(nbt, registryLookup)
        Inventories.readNbt(nbt, this.inventory, registryLookup)
    }

    override fun writeNbt(nbt: NbtCompound, registryLookup: WrapperLookup) {
        super.writeNbt(nbt, registryLookup)
        Inventories.writeNbt(nbt, this.inventory, registryLookup)
    }
}
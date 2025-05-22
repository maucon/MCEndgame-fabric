package de.fuballer.mcendgame.main.component.dungeon.loot

import de.fuballer.mcendgame.main.component.item.custom.armor.interfaces.UniqueAttributesItem
import de.fuballer.mcendgame.main.component.tags.CustomTags
import de.fuballer.mcendgame.main.configuration.RuntimeConfig
import de.fuballer.mcendgame.main.messaging.dungeon.DungeonEntityDeathEvent
import de.fuballer.mcendgame.main.messaging.misc.LivingEntityDropCommand
import de.fuballer.mcendgame.main.messaging.misc.MagicFindCommand
import de.fuballer.mcendgame.main.util.extension.WorldExtension.isDungeonWorld
import de.maucon.mauconframework.command.CommandGateway
import de.maucon.mauconframework.command.CommandHandler
import de.maucon.mauconframework.di.annotation.Injectable
import de.maucon.mauconframework.event.EventSubscriber
import net.minecraft.enchantment.Enchantments
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.LivingEntity
import net.minecraft.item.ItemStack
import net.minecraft.registry.RegistryKeys
import net.minecraft.server.world.ServerWorld
import kotlin.random.Random

@Injectable
class LootService {
    @CommandHandler
    fun on(cmd: LivingEntityDropCommand) {
        if (!cmd.world.isDungeonWorld()) return

        cmd.dropLoot = false
        cmd.dropEquipment = false
        cmd.dropInventory = false
        cmd.dropExperience = true
    }

    @EventSubscriber
    fun on(event: DungeonEntityDeathEvent) {
        if (event.isClient) return
        val serverWorld = event.world as? ServerWorld ?: return

        // TODO check if enemy
        // TODO check if boss
        // TODO check if minion

        val entity = event.entity

        EquipmentSlot.VALUES
            .map { entity.getEquippedStack(it) }
            .filter {
                val lootingLevel = getLootingLevel(event.killer)
                val baseDropProbability = getDropProbability(it, lootingLevel)
                val dropProbability = baseDropProbability * getMagicFindFactor(event.killer).also { println("COCK AND BALL TORTURE: $it") }

                Random.nextDouble() <= dropProbability
            }
            .onEach { setRandomDurability(it) }
            .forEach { entity.dropStack(serverWorld, it) }
    }

    private fun getLootingLevel(entity: LivingEntity?): Int {
        if (entity == null) return 0

        val itemStack = entity.getEquippedStack(EquipmentSlot.MAINHAND)
        val enchantmentRegistry = RuntimeConfig.SERVER.registryManager.getOrThrow(RegistryKeys.ENCHANTMENT)
        val lootingEntry = enchantmentRegistry.getOrThrow(Enchantments.LOOTING)

        return itemStack.enchantments.getLevel(lootingEntry)
    }

    private fun getMagicFindFactor(entity: LivingEntity?): Double {
        if (entity == null) return 1.0

        val magicFindCommand = MagicFindCommand(entity)
        val cmd = CommandGateway.apply(magicFindCommand)

        return LootSettings.calculateMagicFindDropProbabilityFactor(cmd.magicFind)
    }

    private fun getDropProbability(stack: ItemStack, lootingLevel: Int): Double {
        if (stack.item is UniqueAttributesItem) {
            return 1.0 // uniques should always drop
        }

        if (stack.isIn(CustomTags.DIAMOND_GEAR)) {
            return LootSettings.ITEMS_DROP_PROBABILITY_DIAMOND + LootSettings.ITEMS_DROP_PROBABILITY_DIAMOND_PER_LOOTING * lootingLevel
        }
        if (stack.isIn(CustomTags.NETHERITE_GEAR)) {
            return LootSettings.ITEMS_DROP_PROBABILITY_NETHERITE + LootSettings.ITEMS_DROP_PROBABILITY_NETHERITE_PER_LOOTING * lootingLevel
        }

        if (stack.isIn(CustomTags.DUNGEON_DROP_DISABLED)) {
            return 0.0
        }

        return LootSettings.ITEMS_DROP_PROBABILITY + LootSettings.ITEMS_DROP_PROBABILITY_PER_LOOTING * lootingLevel
    }

    private fun setRandomDurability(itemStack: ItemStack) {
        itemStack.damage = (itemStack.maxDamage * Random.nextDouble()).toInt()
    }
}
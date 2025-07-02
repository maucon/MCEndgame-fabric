package de.fuballer.mcendgame.main.component.custom_attribute.effects

import de.fuballer.mcendgame.main.accessor.LivingEntityCompanionAccessor
import de.fuballer.mcendgame.main.accessor.WolfEntityColorAndVariantAccessor
import de.fuballer.mcendgame.main.component.custom_attribute.CustomAttributesExtensions.asStringRoll
import de.fuballer.mcendgame.main.component.custom_attribute.CustomAttributesExtensions.getAllCustomAttributes
import de.fuballer.mcendgame.main.component.custom_attribute.CustomAttributesExtensions.getCustomAttributes
import de.fuballer.mcendgame.main.component.custom_attribute.types.CustomAttributeTypes
import de.fuballer.mcendgame.main.messaging.misc.*
import de.maucon.mauconframework.di.annotation.Injectable
import de.maucon.mauconframework.event.EventSubscriber
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityType
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.passive.WolfEntity
import net.minecraft.entity.passive.WolfVariant
import net.minecraft.entity.passive.WolfVariants
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.registry.Registry
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.server.world.ServerWorld
import net.minecraft.util.DyeColor
import net.minecraft.util.TypeFilter

@Injectable
class WolfCompanionService {
    data class WolfCompanionType(
        val name: String,
        val variant: RegistryKey<WolfVariant>,
        val color: DyeColor,
        val scale: Double,
    )

    companion object {
        private val TYPES = listOf(
            WolfCompanionType("Slowing", WolfVariants.SNOWY, DyeColor.LIGHT_BLUE, 1.05),
            WolfCompanionType("Life Stealing", WolfVariants.RUSTY, DyeColor.ORANGE, 1.0),
            WolfCompanionType("Weakening", WolfVariants.WOODS, DyeColor.GREEN, 0.95),
            WolfCompanionType("Inciting", WolfVariants.BLACK, DyeColor.RED, 1.1),
            WolfCompanionType("Hasting", WolfVariants.STRIPED, DyeColor.YELLOW, 0.9),
        )

        fun getNames() = TYPES.map { it.name }

        fun getByName(name: String) = TYPES.firstOrNull { it.name == name }
    }

    @EventSubscriber
    fun on(event: PlayerJoinEvent) {
        summonWolfCompanions(event.player)
    }

    @EventSubscriber
    fun on(event: PlayerDisconnectEvent) {
        removeWolfCompanions(event.player)
    }

    @EventSubscriber
    fun on(event: PlayerAfterDimensionChangeEvent) {
        summonWolfCompanions(event.player, event.newWorld)
    }

    @EventSubscriber
    fun on(event: PlayerBeforeDimensionChangeEvent) {
        removeWolfCompanions(event.player, event.world)
    }

    @EventSubscriber
    fun on(event: PlayerAfterRespawnEvent) {
        summonWolfCompanions(event.newPlayer)
    }

    @EventSubscriber
    fun on(event: PlayerEntityDeathEvent) {
        if (event.isClient) return
        val serverWorld = event.world as? ServerWorld ?: return
        removeWolfCompanions(event.player, serverWorld)
    }

    @EventSubscriber
    fun on(event: EquipmentChangeEvent) {
        val player = event.entity as? PlayerEntity ?: return
        if (event.oldStack.getCustomAttributes().none { it.type == CustomAttributeTypes.WOLF_COMPANION }
            && event.newStack.getCustomAttributes().none { it.type == CustomAttributeTypes.WOLF_COMPANION }) return

        removeWolfCompanions(player)
        summonWolfCompanions(player)
    }

    private fun summonWolfCompanions(
        player: PlayerEntity,
    ) {
        val world = player.world as? ServerWorld ?: return
        summonWolfCompanions(player, world)
    }

    private fun summonWolfCompanions(
        player: PlayerEntity,
        world: ServerWorld,
    ) {
        val attributes = player.getAllCustomAttributes()[CustomAttributeTypes.WOLF_COMPANION] ?: return

        println("add ${attributes.size} wolf companions")

        val registry = world.registryManager.getOrThrow(RegistryKeys.WOLF_VARIANT)
        attributes.forEach { attribute ->
            val type = getByName(attribute.rolls[0].asStringRoll().getActualRoll()) ?: return@forEach
            summonWolfCompanion(player, world, type, registry)
        }
    }

    private fun summonWolfCompanion(
        player: PlayerEntity,
        world: ServerWorld,
        type: WolfCompanionType,
        registry: Registry<WolfVariant>,
    ) {
        val wolf = WolfEntity(EntityType.WOLF, world)
        wolf.setPosition(player.pos)

        wolf.setTamedBy(player)
        (wolf as LivingEntityCompanionAccessor).`mcendgame$setCompanion`()

        val accessor = wolf as? WolfEntityColorAndVariantAccessor ?: return
        val entry = registry.getOrThrow(type.variant)
        accessor.`mcendgame$callSetVariant`(entry)
        accessor.`mcendgame$callSetCollarColor`(type.color)

        wolf.getAttributeInstance(EntityAttributes.SCALE)?.baseValue = type.scale

        wolf.isInvulnerable = true

        world.spawnEntity(wolf)
    }

    private fun removeWolfCompanions(
        player: PlayerEntity,
    ) {
        val world = player.world as? ServerWorld ?: return
        removeWolfCompanions(player, world)
    }

    private fun removeWolfCompanions(
        player: PlayerEntity,
        world: ServerWorld,
    ) {
        val wolfs = world.getEntitiesByType(TypeFilter.instanceOf(WolfEntity::class.java)) { wolf ->
            (wolf as LivingEntityCompanionAccessor).`mcendgame$isCompanion`()
                    && wolf.owner == player
        }

        println("remove ${wolfs.size} wolf companions")

        wolfs.forEach { it.remove(Entity.RemovalReason.UNLOADED_WITH_PLAYER) }
    }
}
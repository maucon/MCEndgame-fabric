package de.fuballer.mcendgame.main.messaging

import de.fuballer.mcendgame.main.component.dungeon.enemy.DungeonEnemyEntity.Companion.toDungeonEnemyEntity
import de.fuballer.mcendgame.main.component.dungeon.enemy.boss.DungeonBossEntity.Companion.toDungeonBossEntity
import de.fuballer.mcendgame.main.messaging.dungeon.DungeonBossDeathEvent
import de.fuballer.mcendgame.main.messaging.dungeon.DungeonEnemyDeathEvent
import de.fuballer.mcendgame.main.messaging.dungeon.DungeonEntityDeathEvent
import de.fuballer.mcendgame.main.messaging.dungeon.DungeonPlayerDeathEvent
import de.fuballer.mcendgame.main.messaging.misc.*
import de.fuballer.mcendgame.main.messaging.server.ServerEndTickEvent
import de.fuballer.mcendgame.main.messaging.server.ServerStartedEvent
import de.fuballer.mcendgame.main.messaging.server.ServerStoppingEvent
import de.fuballer.mcendgame.main.util.extension.EntityExtension.isDungeonEnemy
import de.fuballer.mcendgame.main.util.extension.ItemStackExtension.isSameIgnoringDurability
import de.fuballer.mcendgame.main.util.extension.WorldExtension.isDungeonWorld
import de.maucon.mauconframework.di.annotation.Injectable
import de.maucon.mauconframework.event.EventGateway
import de.maucon.mauconframework.event.EventSubscriber
import de.maucon.mauconframework.initializer.Initializer
import net.fabricmc.fabric.api.entity.event.v1.ServerEntityWorldChangeEvents
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents
import net.minecraft.entity.player.PlayerEntity

@Injectable
object EventMapper {
    @EventSubscriber
    fun onPlayerDeath(event: LivingEntityDeathEvent) {
        val player = event.entity as? PlayerEntity ?: return
        val playerDeathEvent = PlayerEntityDeathEvent(event.isClient, event.world, player, event.killer)
        EventGateway.launchPublish(playerDeathEvent)
    }

    @EventSubscriber
    fun onDungeonPlayerDeath(event: PlayerEntityDeathEvent) {
        if (!event.player.world.isDungeonWorld()) return
        val dungeonPlayerDeathEvent = DungeonPlayerDeathEvent(event.isClient, event.player, event.killer)
        EventGateway.launchPublish(dungeonPlayerDeathEvent)
    }

    @EventSubscriber
    fun onDungeonEntityDeath(event: LivingEntityDeathEvent) {
        val entity = event.entity
        if (!entity.world.isDungeonWorld()) return

        val dungeonEntityDeathEvent = DungeonEntityDeathEvent(event.isClient, event.world, event.entity, event.killer)
        EventGateway.launchPublish(dungeonEntityDeathEvent)
    }

    @EventSubscriber
    fun onDungeonEnemyDeath(event: DungeonEntityDeathEvent) {
        val entity = event.entity
        if (!entity.isDungeonEnemy()) return

        val enemyEntity = entity.toDungeonEnemyEntity()
        val dungeonEnemyDeathEvent = DungeonEnemyDeathEvent(event.isClient, event.world, enemyEntity, event.killer)
        EventGateway.launchPublish(dungeonEnemyDeathEvent)
    }

    @EventSubscriber
    fun onDungeonBossDeath(event: DungeonEnemyDeathEvent) {
        val entity = event.enemyEntity
        if (!entity.isDungeonBoss) return

        val bossEntity = entity.toDungeonBossEntity()
        val dungeonBossDeathEvent = DungeonBossDeathEvent(event.isClient, event.world, bossEntity, event.killer)
        EventGateway.launchPublish(dungeonBossDeathEvent)
    }

    @Initializer
    fun onServerStarted() = ServerLifecycleEvents.SERVER_STARTED.register {
        val event = ServerStartedEvent(it)
        EventGateway.launchPublish(event)
    }

    @Initializer
    fun onServerStopping() = ServerLifecycleEvents.SERVER_STOPPING.register {
        val event = ServerStoppingEvent(it)
        EventGateway.launchPublish(event)
    }

    @Initializer
    fun onServerTickEnd() = ServerTickEvents.END_SERVER_TICK.register {
        val event = ServerEndTickEvent(it)
        EventGateway.launchPublish(event)
    }

    @Initializer
    fun afterPlayerChangeWorld() = ServerEntityWorldChangeEvents.AFTER_PLAYER_CHANGE_WORLD.register { entity, oldWorld, newWorld ->
        val event = PlayerAfterDimensionChangeEvent(entity, oldWorld, newWorld)
        EventGateway.launchPublish(event)
    }

    @Initializer
    fun afterPlayerRespawn() = ServerPlayerEvents.AFTER_RESPAWN.register { oldPlayer, newPlayer, alive -> // end portal is alive respawn
        val event = PlayerAfterRespawnEvent(oldPlayer, newPlayer, alive)
        EventGateway.launchPublish(event)
    }

    @Initializer
    fun onPlayerJoin() = ServerPlayConnectionEvents.JOIN.register { handler, _, _ ->
        val event = PlayerJoinEvent(handler.player)
        EventGateway.launchPublish(event)
    }

    @Initializer
    fun onPlayerDisconnect() = ServerPlayConnectionEvents.DISCONNECT.register { handler, _ ->
        val event = PlayerDisconnectEvent(handler.player)
        EventGateway.launchPublish(event)
    }

    @Initializer
    fun onEquipmentChange() = ServerEntityEvents.EQUIPMENT_CHANGE.register { entity, slot, oldStack, newStack ->
        if (oldStack.isSameIgnoringDurability(newStack)) return@register

        val event = EquipmentChangeEvent(entity, slot, oldStack, newStack)
        EventGateway.launchPublish(event)
    }
}
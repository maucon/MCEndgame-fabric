package de.fuballer.mcendgame.components.portal

import de.fuballer.mcendgame.components.portal.teleport.TeleportExtensions.teleportTo
import de.fuballer.mcendgame.components.portal.teleport.TeleportLocation
import net.minecraft.entity.EntityType
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.MovementType
import net.minecraft.entity.damage.DamageSource
import net.minecraft.entity.data.DataTracker
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NbtCompound
import net.minecraft.server.world.ServerWorld
import net.minecraft.util.ActionResult
import net.minecraft.util.Arm
import net.minecraft.util.Hand
import net.minecraft.util.math.Vec3d
import net.minecraft.world.World

abstract class AbstractPortalEntity(
    entityType: EntityType<out LivingEntity>,
    world: World
) : LivingEntity(entityType, world) {
    private var ticksSinceStart = 0

    var teleportLocation: TeleportLocation? = null

    init {
        this.setNoGravity(true)
    }

    abstract fun playAnimation(ticksSinceStart: Int)


    override fun tick() {
        super.tick()

        playAnimation(ticksSinceStart++)
    }

    override fun interactAt(player: PlayerEntity, hitPos: Vec3d, hand: Hand): ActionResult {
        if (world.isClient) return ActionResult.PASS
        if (hand != Hand.MAIN_HAND) return ActionResult.PASS

        return teleportPlayer(player)
    }

    private fun teleportPlayer(player: PlayerEntity): ActionResult {
        println("teleportPlayer")
        val teleportSuccessful = teleportLocation?.let { player.teleportTo(it) } ?: false

        if (!teleportSuccessful) {
            player.sendMessage(PortalSettings.TELEPORTATION_FAILED_MESSAGE, false)
        }

        remove(RemovalReason.KILLED)
        return ActionResult.SUCCESS
    }

    override fun shouldRenderName(): Boolean = false

    override fun isOnFire() = false
    override fun canFreeze() = false

    override fun move(type: MovementType, movement: Vec3d) {}

    override fun getArmorItems(): MutableIterable<ItemStack> = mutableListOf()
    override fun getEquippedStack(slot: EquipmentSlot): ItemStack = ItemStack.EMPTY
    override fun equipStack(slot: EquipmentSlot?, stack: ItemStack) {}
    override fun getMainArm(): Arm = Arm.RIGHT

    override fun damage(world: ServerWorld, source: DamageSource, amount: Float) = false

    override fun kill(level: ServerWorld) {
        remove(RemovalReason.KILLED)
    }


    override fun initDataTracker(builder: DataTracker.Builder) {
        super.initDataTracker(builder)
    }

    override fun readCustomDataFromNbt(nbt: NbtCompound) {}

    override fun writeCustomDataToNbt(nbt: NbtCompound) {}
}
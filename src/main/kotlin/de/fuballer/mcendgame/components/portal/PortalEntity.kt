package de.fuballer.mcendgame.components.portal

import de.fuballer.mcendgame.components.portal.teleport.TeleportExtensions.teleportTo
import de.fuballer.mcendgame.components.portal.teleport.TeleportLocation
import de.fuballer.mcendgame.components.portal.type.DefaultPortalType
import de.fuballer.mcendgame.components.portal.type.PortalType
import net.minecraft.command.argument.EntityAnchorArgumentType
import net.minecraft.entity.EntityType
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.MovementType
import net.minecraft.entity.damage.DamageSource
import net.minecraft.entity.data.DataTracker
import net.minecraft.entity.data.TrackedData
import net.minecraft.entity.data.TrackedDataHandlerRegistry
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NbtCompound
import net.minecraft.server.world.ServerWorld
import net.minecraft.util.ActionResult
import net.minecraft.util.Arm
import net.minecraft.util.Hand
import net.minecraft.util.math.Vec3d
import net.minecraft.world.World

private const val TYPE_NBT = "Type"
private const val TELEPORT_LOCATION_NBT = "TeleportLocation"

class PortalEntity(
    entityType: EntityType<out LivingEntity>,
    world: World,
) : LivingEntity(entityType, world) {

    var type: PortalType = DefaultPortalType()
    var teleportLocation: TeleportLocation? = null

    init {
        this.setNoGravity(true)
        isInvulnerable = true
    }

    companion object {
        val TYPE: TrackedData<String> = DataTracker.registerData(PortalEntity::class.java, TrackedDataHandlerRegistry.STRING)
    }

    override fun tick() {
        super.tick()

        type.tickAnimation(this)
    }

    override fun interactAt(player: PlayerEntity, hitPos: Vec3d, hand: Hand): ActionResult {
        if (world.isClient) return ActionResult.PASS
        if (hand != Hand.MAIN_HAND) return ActionResult.PASS

        return teleportPlayer(player)
    }

    private fun teleportPlayer(player: PlayerEntity): ActionResult {
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
        builder.add(TYPE, "default")
    }

    override fun readCustomDataFromNbt(nbt: NbtCompound) {
        val typeId = nbt.getString(TYPE_NBT)
        type = PortalType.getById(typeId)
        dataTracker.set(TYPE, typeId)

        val teleportLocationNBT = nbt.getCompound(TELEPORT_LOCATION_NBT)
        teleportLocation = TeleportLocation.fromNBT(teleportLocationNBT)
    }

    override fun writeCustomDataToNbt(nbt: NbtCompound) {
        nbt.putString(TYPE_NBT, type.getId())

        teleportLocation?.toNBT()?.also {
            nbt.put(TELEPORT_LOCATION_NBT, it)
        }
    }
}
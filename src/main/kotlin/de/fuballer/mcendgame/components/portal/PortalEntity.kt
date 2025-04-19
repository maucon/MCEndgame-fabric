package de.fuballer.mcendgame.components.portal

import com.mojang.logging.LogUtils
import de.fuballer.mcendgame.components.portal.teleport.TeleportExtensions.teleportTo
import de.fuballer.mcendgame.components.portal.teleport.TeleportLocation
import de.fuballer.mcendgame.components.portal.type.DefaultPortalType
import de.fuballer.mcendgame.components.portal.type.PortalType
import de.fuballer.mcendgame.util.NbtExtension.getSafe
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
import kotlin.jvm.optionals.getOrNull


private const val TYPE_NBT = "Type"
private const val TELEPORT_LOCATION_NBT = "TeleportLocation"
private const val SINGLE_USE_NBT = "SingleUse"

class PortalEntity(
    entityType: EntityType<out LivingEntity>,
    world: World,
) : LivingEntity(entityType, world) {
    private val log = LogUtils.getLogger()

    private var removed: Boolean = false
    var type: PortalType = DefaultPortalType()
    var teleportLocation: TeleportLocation? = null
    var singleUse = false

    init {
        this.setNoGravity(true)
        isInvulnerable = true
    }

    companion object {
        val TYPE: TrackedData<String> = DataTracker.registerData(PortalEntity::class.java, TrackedDataHandlerRegistry.STRING)
    }

    override fun tick() {
        if (removed) {
            discard()
            return
        }

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

        if (singleUse) {
            remove(RemovalReason.KILLED)
        }
        return ActionResult.SUCCESS
    }

    override fun shouldRenderName(): Boolean = false

    override fun isOnFire() = false
    override fun canFreeze() = false

    override fun move(type: MovementType, movement: Vec3d) {}

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
        val typeId = nbt.getString(TYPE_NBT).get()
        type = PortalType.getById(typeId)
        dataTracker.set(TYPE, typeId)

        if (world.isClient) return

        singleUse = nbt.getBoolean(SINGLE_USE_NBT, false)

        teleportLocation = nbt.getSafe(TELEPORT_LOCATION_NBT, TeleportLocation.CODEC).getOrNull()
        if (teleportLocation == null) {
            log.info("Marking outdated portal to be removed: $uuid")
            removed = true
        }
    }

    override fun writeCustomDataToNbt(nbt: NbtCompound) {
        nbt.putString(TYPE_NBT, type.getId())
        nbt.putNullable(TELEPORT_LOCATION_NBT, TeleportLocation.CODEC, teleportLocation)
        nbt.putBoolean(SINGLE_USE_NBT, singleUse)
    }
}
package de.fuballer.mcendgame.main.component.portal

import com.mojang.logging.LogUtils
import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import de.fuballer.mcendgame.main.component.portal.teleport.TeleportExtensions.teleportTo
import de.fuballer.mcendgame.main.component.portal.teleport.TeleportLocation
import de.fuballer.mcendgame.main.component.portal.type.DefaultPortalType
import de.fuballer.mcendgame.main.component.portal.type.PortalType
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
import net.minecraft.server.world.ServerWorld
import net.minecraft.storage.ReadView
import net.minecraft.storage.WriteView
import net.minecraft.util.ActionResult
import net.minecraft.util.Arm
import net.minecraft.util.Hand
import net.minecraft.util.math.Vec3d
import net.minecraft.world.World
import java.util.*
import kotlin.jvm.optionals.getOrNull

private const val DATA_KEY = "portal_entity_data"

class PortalEntity(
    entityType: EntityType<out LivingEntity>,
    world: World,
) : LivingEntity(entityType, world) {
    private val log = LogUtils.getLogger()

    private var removed: Boolean = false
    var type: PortalType = DefaultPortalType()
    var teleportLocation: TeleportLocation? = null
    var singleUse = false

    private data class PortalEntityData(
        val typeId: String,
        val singleUse: Boolean,
        val teleportLocation: TeleportLocation?,
    ) {
        companion object {
            val CODEC: Codec<PortalEntityData> = RecordCodecBuilder.create { instance ->
                instance.group(
                    Codec.STRING.fieldOf("type_id").forGetter(PortalEntityData::typeId),
                    Codec.BOOL.fieldOf("single_use").forGetter(PortalEntityData::singleUse),
                    TeleportLocation.CODEC.optionalFieldOf("teleport_location").forGetter { Optional.ofNullable(it.teleportLocation) },
                ).apply(instance) { typeId, singleUse, location ->
                    PortalEntityData(typeId, singleUse, location.getOrNull())
                }
            }
        }
    }

    init {
        this.setNoGravity(true)
        isInvulnerable = true
        noClip = true
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
        if (entityWorld.isClient) return ActionResult.PASS
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

    override fun readCustomData(view: ReadView) {
        super.readCustomData(view)

        val data = view.read(DATA_KEY, PortalEntityData.CODEC).orElseThrow()
        type = PortalType.getById(data.typeId)
        dataTracker.set(TYPE, data.typeId)

        if (entityWorld.isClient) return

        singleUse = data.singleUse
        teleportLocation = data.teleportLocation
    }

    override fun writeCustomData(view: WriteView) {
        super.writeCustomData(view)

        view.put(DATA_KEY, PortalEntityData.CODEC, PortalEntityData(type.getId(), singleUse, teleportLocation))
    }
}
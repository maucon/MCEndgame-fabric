package de.fuballer.mcendgame.main.component.killer.db

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import de.maucon.mauconframework.stereotype.Entity
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap
import net.minecraft.entity.EntityType
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.SpawnReason
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.network.RegistryByteBuf
import net.minecraft.network.codec.PacketCodec
import net.minecraft.network.codec.PacketCodecs
import net.minecraft.registry.Registries
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.text.Text
import net.minecraft.text.TextCodecs
import net.minecraft.util.Uuids
import net.minecraft.world.World
import java.util.*

data class KillerEntity(
    /** id of a player */
    override var id: UUID,
    val type: RegistryKey<EntityType<*>>,
    val name: Optional<Text>,
    val equipment: Map<EquipmentSlot, ItemStack>,
    val statusEffects: List<StatusEffectInstance>,
) : Entity<UUID> {
    companion object {
        val EQUIPMENT_MAP_CODEC: Codec<Map<EquipmentSlot, ItemStack>> = Codec.unboundedMap(EquipmentSlot.CODEC, ItemStack.CODEC)

        val STATUS_EFFECTS_CODEC: Codec<List<StatusEffectInstance>> = Codec.list(StatusEffectInstance.CODEC)

        val CODEC: Codec<KillerEntity> =
            RecordCodecBuilder.create { instance ->
                instance.group(
                    Uuids.CODEC.fieldOf("id").forGetter(KillerEntity::id),
                    RegistryKey.createCodec(RegistryKeys.ENTITY_TYPE).fieldOf("type").forGetter(KillerEntity::type),
                    Codec.optionalField("name", TextCodecs.CODEC, true).fieldOf("name").forGetter(KillerEntity::name),
                    EQUIPMENT_MAP_CODEC.fieldOf("equipment").forGetter(KillerEntity::equipment),
                    STATUS_EFFECTS_CODEC.fieldOf("status_effects").forGetter(KillerEntity::statusEffects),
                ).apply(instance, ::KillerEntity)
            }

        val EQUIPMENT_MAP_PACKET_CODEC: PacketCodec<RegistryByteBuf, Map<EquipmentSlot, ItemStack>> =
            PacketCodecs.map(::Object2ObjectOpenHashMap, EquipmentSlot.PACKET_CODEC, ItemStack.PACKET_CODEC)

        val STATUS_EFFECTS_PACKET_CODEC: PacketCodec<RegistryByteBuf, List<StatusEffectInstance>> =
            PacketCodecs.collection(::ArrayList, StatusEffectInstance.PACKET_CODEC)

        val PACKET_CODEC: PacketCodec<RegistryByteBuf, KillerEntity> = PacketCodec.tuple(
            Uuids.PACKET_CODEC, KillerEntity::id,
            RegistryKey.createPacketCodec(RegistryKeys.ENTITY_TYPE), KillerEntity::type,
            PacketCodecs.optional(TextCodecs.PACKET_CODEC), KillerEntity::name,
            EQUIPMENT_MAP_PACKET_CODEC, KillerEntity::equipment,
            STATUS_EFFECTS_PACKET_CODEC, KillerEntity::statusEffects,
            ::KillerEntity
        )

        fun of(
            killed: PlayerEntity,
            killer: LivingEntity,
        ): KillerEntity {
            val type = Registries.ENTITY_TYPE.getKey(killer.type).get()
            val name = Optional.ofNullable(killer.customName)
            val equipment = mapOf(
                EquipmentSlot.HEAD to killer.getEquippedStack(EquipmentSlot.HEAD),
                EquipmentSlot.CHEST to killer.getEquippedStack(EquipmentSlot.CHEST),
                EquipmentSlot.LEGS to killer.getEquippedStack(EquipmentSlot.LEGS),
                EquipmentSlot.FEET to killer.getEquippedStack(EquipmentSlot.FEET),
                EquipmentSlot.MAINHAND to killer.getEquippedStack(EquipmentSlot.MAINHAND),
                EquipmentSlot.OFFHAND to killer.getEquippedStack(EquipmentSlot.OFFHAND),
            ).filter { !it.value.isEmpty }
            val effects = killer.statusEffects.toList()

            return KillerEntity(killed.uuid, type, name, equipment, effects)
        }
    }

    fun asLivingEntity(world: World): LivingEntity? {
        val type = Registries.ENTITY_TYPE.get(type) ?: return null
        val livingEntity = type.create(world, SpawnReason.COMMAND) as LivingEntity

        if (name.isPresent) livingEntity.customName = name.get()

        equipment.forEach { livingEntity.equipStack(it.key, it.value) }
        statusEffects.forEach { livingEntity.addStatusEffect(it) }

        return livingEntity
    }

    fun getNameOrTypeName(): Text {
        if (name.isPresent) return name.get()
        val type = Registries.ENTITY_TYPE.get(type) ?: return Text.translatable("entity.mcendgame.unknown")
        return Text.translatable(type.translationKey)
    }
}
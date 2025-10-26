package de.fuballer.mcendgame.main.mixin.living_entity;

import de.fuballer.mcendgame.main.accessor.LivingEntityLinkAttributeAccessor;
import de.fuballer.mcendgame.main.component.custom_attribute.CustomAttributesExtensions;
import de.fuballer.mcendgame.main.component.custom_attribute.data.DoubleRoll;
import de.fuballer.mcendgame.main.component.custom_attribute.data.IntRoll;
import de.fuballer.mcendgame.main.component.custom_attribute.effects.link.LinkSettings;
import de.fuballer.mcendgame.main.component.custom_attribute.types.CustomAttributeTypes;
import de.fuballer.mcendgame.main.util.extension.EntityExtension;
import io.netty.buffer.ByteBuf;
import kotlin.Pair;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandler;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.*;
import java.util.stream.Collectors;

@Mixin(LivingEntity.class)
public class LivingEntityLinkAttributeMixin implements LivingEntityLinkAttributeAccessor {
    @Unique
    private static PacketCodec<ByteBuf, UUID> UUID_PACKET_CODEC = PacketCodecs.STRING.xmap(UUID::fromString, UUID::toString);
    @Unique
    private static final PacketCodec<ByteBuf, Pair<UUID, Long>> UUID_LONG_PAIR_PACKET_CODEC =
            PacketCodec.tuple(
                    UUID_PACKET_CODEC,
                    Pair::getFirst,
                    PacketCodecs.VAR_LONG,
                    Pair::getSecond,
                    Pair::new
            );
    @Unique
    private static PacketCodec<ByteBuf, List<Pair<UUID, Long>>> UUID_LONG_PAIR_LIST_PACKET_CODEC
            = UUID_LONG_PAIR_PACKET_CODEC.collect(PacketCodecs.toList());
    @Unique
    private static TrackedDataHandler<List<Pair<UUID, Long>>> UUID_LONG_PAIR_LIST_TRACKED_DATA_HANDLER
            = TrackedDataHandler.create(UUID_LONG_PAIR_LIST_PACKET_CODEC);

    static {
        TrackedDataHandlerRegistry.register(UUID_LONG_PAIR_LIST_TRACKED_DATA_HANDLER);
    }

    @Unique
    private static final TrackedData<List<Pair<UUID, Long>>> LINKED_ENTITIES =
            DataTracker.registerData(LivingEntity.class, UUID_LONG_PAIR_LIST_TRACKED_DATA_HANDLER);

    @Inject(method = "initDataTracker", at = @At("TAIL"))
    private void initDataTracker(DataTracker.Builder builder, CallbackInfo ci) {
        builder.add(LINKED_ENTITIES, new ArrayList<>());
    }

    @Unique
    public Map<UUID, Long> getLinkedEntitiesMap() {
        return ((LivingEntity) (Object) this)
                .getDataTracker()
                .get(LINKED_ENTITIES)
                .stream()
                .collect(Collectors.toMap(Pair::getFirst, Pair::getSecond));
    }

    @Unique
    public void setLinkedEntitiesMap(Map<UUID, Long> map) {
        var list = map.entrySet().stream()
                .map(e -> new Pair<>(e.getKey(), e.getValue()))
                .collect(Collectors.toList());
        ((LivingEntity) (Object) this).getDataTracker().set(LINKED_ENTITIES, list);
    }

    @Inject(method = "tick", at = @At("HEAD"))
    void tick(CallbackInfo ci) {
        var entity = (LivingEntity) (Object) this;
        if (entity.getWorld().isClient) return;

        if (entity.age % LinkSettings.LINK_UPDATE_INTERVAL == 0) updateLinkedEntities(entity);
        if (entity.age % LinkSettings.LINK_DAMAGE_INTERVAL == 0) damageLinkedEntities(entity);
    }

    @Unique
    private void updateLinkedEntities(LivingEntity entity) {
        var allAttributes = CustomAttributesExtensions.INSTANCE.getAllCustomAttributes(entity);
        var linkAttributes = allAttributes.get(CustomAttributeTypes.INSTANCE.getLINK_NEARBY_ENEMIES());
        if (linkAttributes == null || linkAttributes.isEmpty()) {
            clearLinkedEntities(entity);
            return;
        }

        var distance = linkAttributes.stream().mapToInt(a -> ((IntRoll) a.getRolls().getFirst()).getValue()).max().orElse(0);
        if (distance <= 0) {
            clearLinkedEntities(entity);
            return;
        }

        var world = (ServerWorld) entity.getWorld();

        var paddedDistance = distance + LinkSettings.LINK_DISTANCE_BREAK_PADDING;
        var enemiesInPaddedRange = getEnemiesInRange(world, entity, paddedDistance);
        var enemyUuidsInPaddedRange = new HashMap<UUID, Float>(enemiesInPaddedRange.size());
        enemiesInPaddedRange.forEach((e, d) -> enemyUuidsInPaddedRange.put(e.getUuid(), d));

        var updatedLinkedEntities = new HashMap<>(getLinkedEntitiesMap());
        updatedLinkedEntities.keySet().retainAll(enemyUuidsInPaddedRange.keySet());

        var enemyUuidsInNonPaddedRange = new ArrayList<UUID>();
        enemyUuidsInPaddedRange.forEach((uuid, dist) -> {
            if (dist <= distance) enemyUuidsInNonPaddedRange.add(uuid);
        });

        var currentTime = world.getTime();
        enemyUuidsInNonPaddedRange.forEach(uuid -> updatedLinkedEntities.putIfAbsent(uuid, currentTime));

        setLinkedEntitiesMap(updatedLinkedEntities);
    }

    @Unique
    private Map<Entity, Float> getEnemiesInRange(
            ServerWorld world,
            LivingEntity entity,
            Float distance
    ) {
        return world.getOtherEntities(entity, entity.getBoundingBox().expand(distance))
                .stream()
                .filter(e -> EntityExtension.INSTANCE.isEnemy(entity, e))
                .map(enemy -> new Pair<>(enemy, enemy.distanceTo(entity)))
                .filter(enemyDistancePair -> enemyDistancePair.getSecond() <= distance)
                .collect(Collectors.toMap(Pair::getFirst, Pair::getSecond));
    }

    @Unique
    private void clearLinkedEntities(LivingEntity entity) {
        entity.getDataTracker().set(LINKED_ENTITIES, new LinkedList<>());
    }

    @Unique
    private void damageLinkedEntities(LivingEntity entity) {
        var allAttributes = CustomAttributesExtensions.INSTANCE.getAllCustomAttributes(entity);
        var linkAttributes = allAttributes.get(CustomAttributeTypes.INSTANCE.getDAMAGE_LINKED_ENEMIES());
        if (linkAttributes == null || linkAttributes.isEmpty()) return;
        var sum = linkAttributes.stream().mapToDouble(attribute -> ((DoubleRoll) attribute.getRolls().getFirst()).getValue()).sum();

        var world = (ServerWorld) entity.getWorld();
        var time = world.getTime();
        var linkedEntities = entity.getDataTracker().get(LINKED_ENTITIES)
                .stream()
                .filter(pair -> time - pair.getSecond() > LinkSettings.DAMAGE_DELAY)
                .map(pair -> world.getEntity(pair.getFirst()))
                .filter(e -> e != null && e.isAlive());

        //TODO deal magic damage percent instead
        linkedEntities.forEach(linkedEntity -> linkedEntity.damage(world, entity.getDamageSources().mobAttack(entity), (float) sum));
    }

    @Override
    public Map<UUID, Long> mcendgame$getLinkedEntities() {
        return getLinkedEntitiesMap();
    }
}

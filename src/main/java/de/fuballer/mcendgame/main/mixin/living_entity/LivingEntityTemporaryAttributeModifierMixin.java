package de.fuballer.mcendgame.main.mixin.living_entity;

import de.fuballer.mcendgame.main.accessor.LivingEntityTemporaryAttributeModifierAccessor;
import kotlin.Pair;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.HashMap;
import java.util.Map;

@Mixin(LivingEntity.class)
public class LivingEntityTemporaryAttributeModifierMixin implements LivingEntityTemporaryAttributeModifierAccessor {
    @Unique
    private static final int checkInterval = 4;
    @Unique
    private final Map<Pair<RegistryEntry<EntityAttribute>, Identifier>, Integer> toRemoveModifiers = new HashMap<>();

    @Inject(method = "tick", at = @At("HEAD"))
    void tick(CallbackInfo ci) {
        var entity = (LivingEntity) (Object) this;
        if (entity.getWorld().isClient) return;

        if (entity.age % checkInterval != 0) return;

        for (Pair<RegistryEntry<EntityAttribute>, Identifier> modifier : toRemoveModifiers.keySet()) {

            var newDelay = toRemoveModifiers.get(modifier) - checkInterval;
            if (newDelay > 0) {
                toRemoveModifiers.put(modifier, newDelay);
                continue;
            }
            toRemoveModifiers.remove(modifier);

            var attributeInstance = entity.getAttributeInstance(modifier.getFirst());
            if (attributeInstance == null) continue;
            attributeInstance.removeModifier(modifier.getSecond());
        }
    }

    @Override
    public void mcendgame$addTemporaryAttributeModifier(
            RegistryEntry<EntityAttribute> type,
            Identifier identifier,
            int ticks,
            double value,
            EntityAttributeModifier.Operation operation
    ) {
        var entity = (LivingEntity) (Object) this;
        var attributeInstance = entity.getAttributeInstance(type);
        if (attributeInstance == null) return;

        var key = new Pair<>(type, identifier);
        if (toRemoveModifiers.getOrDefault(key, -1) > ticks) return;
        toRemoveModifiers.put(key, ticks);

        var existingModifier = attributeInstance.getModifier(identifier);
        if (existingModifier != null && Math.abs(existingModifier.value() - value) < 0.001) return;

        var modifier = new EntityAttributeModifier(identifier, value, operation);

        attributeInstance.removeModifier(identifier);
        attributeInstance.addTemporaryModifier(modifier);
    }
}
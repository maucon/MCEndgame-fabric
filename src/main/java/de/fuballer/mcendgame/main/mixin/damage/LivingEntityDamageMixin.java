package de.fuballer.mcendgame.main.mixin.damage;

import de.fuballer.mcendgame.main.component.damage.ApplyDamageCalculationCommand;
import de.fuballer.mcendgame.main.component.damage.DamageUtil;
import de.fuballer.mcendgame.main.component.damage.calculator.*;
import de.maucon.mauconframework.command.CommandGateway;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.tag.DamageTypeTags;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.stat.Stats;
import net.minecraft.world.event.GameEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.Objects;

@Mixin(LivingEntity.class)
public abstract class LivingEntityDamageMixin {
    @Unique
    private static final List<DamageCalculator> DAMAGE_CALCULATORS = List.of(
            MeleeAttackCalculator.INSTANCE,
            TridentProjectileCalculator.INSTANCE,
            PersistentProjectileCalculator.INSTANCE,
            SnowballCalculator.INSTANCE,
            OtherProjectilesCalculator.INSTANCE
    );

    @Inject(at = @At("HEAD"), method = "applyDamage", cancellable = true)
    protected void applyDamage(
            ServerWorld world,
            DamageSource source,
            float originalDamage,
            CallbackInfo ci
    ) {
        LivingEntity entity = (LivingEntity) (Object) this;

        if (entity.isInvulnerableTo(world, source)) {
            ci.cancel();
            return;
        }

        // TODO what about difficulty
        // -> maybe just add hard multiplier?
        /*
        if (source.isScaledWithDifficulty()) {
                    if (world.getDifficulty() == Difficulty.PEACEFUL) {
                        amount = 0.0F;
                    }

                    if (world.getDifficulty() == Difficulty.EASY) {
                        amount = Math.min(amount / 2.0F + 1.0F, amount);
                    }

                    if (world.getDifficulty() == Difficulty.HARD) {
                        amount = amount * 3.0F / 2.0F;
                    }
                }
         */
        // TODO skeleton, stray, bogged proj
        // TODO drowned with trident

        // TODO blaze proj and melee
        // TODO ghast
        // -> also redirected ghast balls do 500 damage to ghasts
        // -> fire res entities do not take hit damage of (small)fireballs
        // TODO breeze proj
        // TODO guardians
        // TODO evoker spells
        // TODO shulker proj
        // TODO warden
        // TODO witch potions
        // TODO wither skulls
        // TODO ender dragon ball

        var applyDamageCalculationCommand = ApplyDamageCalculationCommand.Companion.of(entity, world, source);
        var cmd = CommandGateway.INSTANCE.apply(applyDamageCalculationCommand);

        var combinedDamage = getFullDamage(originalDamage, entity, source, cmd);

        float healthDamage = Math.max(combinedDamage - entity.getAbsorptionAmount(), 0.0F);
        float absorbedDamage = combinedDamage - healthDamage;
        entity.setAbsorptionAmount(entity.getAbsorptionAmount() - absorbedDamage);

        if (absorbedDamage > 0.0F && absorbedDamage < 3.4028235E37F) {
            if (source.getAttacker() instanceof ServerPlayerEntity serverPlayerEntity) {
                serverPlayerEntity.increaseStat(Stats.DAMAGE_DEALT_ABSORBED, Math.round(absorbedDamage * 10.0F));
            }

            if (entity instanceof PlayerEntity player) {
                player.increaseStat(Stats.DAMAGE_ABSORBED, Math.round(absorbedDamage * 10.0F));
            }
        }

        if (healthDamage != 0.0F) {
            entity.getDamageTracker().onDamage(source, healthDamage);
            entity.setHealth(entity.getHealth() - healthDamage);

            if (entity instanceof PlayerEntity player && healthDamage < 3.4028235E37F) {
                player.increaseStat(Stats.DAMAGE_TAKEN, Math.round(healthDamage * 10.0F));
            }

            entity.emitGameEvent(GameEvent.ENTITY_DAMAGE);
        }

        ci.cancel();
    }

    @Unique
    private float getFullDamage(
            float originalDamage,
            LivingEntity attacked,
            DamageSource source,
            ApplyDamageCalculationCommand cmd
    ) {
        System.out.println("---------------------------------");

        var damageCalculatorOptional = DAMAGE_CALCULATORS.stream()
                .filter(calculator -> calculator.isActive(source))
                .findFirst();

        if (damageCalculatorOptional.isEmpty()) {
            return originalDamage;
        }

        var damageCalculator = damageCalculatorOptional.get();
        System.out.println("damageCalculator: " + damageCalculator.getClass().getSimpleName());

        // TODO enchant breach
        var attackDamage = damageCalculator.calculateAttackDamage(originalDamage, attacked, source, cmd);
        attackDamage = applyArmorToDamage(attackDamage, source, cmd, attacked);
        attackDamage = modifyAppliedDamage(source, attackDamage, attacked);
        attackDamage = DamageUtil.INSTANCE.reduceDamageByAttributes(attackDamage, cmd);

        var elementalDamage = damageCalculator.calculateElementalDamage(originalDamage, attacked, source, cmd);
        elementalDamage = applyWardToDamage(elementalDamage, source, cmd, attacked);
        elementalDamage = modifyAppliedDamage(source, elementalDamage, attacked);
        elementalDamage = DamageUtil.INSTANCE.reduceDamageByAttributes(elementalDamage, cmd);

        System.out.println("attack damage: " + attackDamage);
        System.out.println("elemental damage: " + elementalDamage);
        var combinedDamage = attackDamage + elementalDamage;
        System.out.println("combined damage: " + combinedDamage);

        return combinedDamage;
    }

    @Unique
    private float applyArmorToDamage(
            float amount,
            DamageSource source,
            ApplyDamageCalculationCommand cmd,
            LivingEntity entity
    ) {
        if (source.isIn(DamageTypeTags.BYPASSES_ARMOR)) return amount;
        entity.damageArmor(source, amount);

        var armor = entity.getArmor();
        var armorToughness = (float) entity.getAttributeValue(EntityAttributes.ARMOR_TOUGHNESS);
        amount = DamageUtil.INSTANCE.reduceAttackDamageByArmor(entity, amount, source, armor, armorToughness);

        return amount;
    }

    @Unique
    private float applyWardToDamage(
            float amount,
            DamageSource source,
            ApplyDamageCalculationCommand cmd,
            LivingEntity entity
    ) {
        // if (source.isIn(DamageTypeTags.BYPASSES_ARMOR)) return amount; //TODO DECIDE IF APPLY
        // attacked.damageArmor(source, amount);

        var ward = cmd.getWard().stream().mapToDouble(Double::doubleValue).sum();
        amount = DamageUtil.INSTANCE.reduceElementalDamageByWard(entity, amount, source, (float) ward);

        return amount;
    }

    @Unique
    protected float modifyAppliedDamage(
            DamageSource source,
            float amount,
            LivingEntity entity
    ) {
        if (source.isIn(DamageTypeTags.BYPASSES_EFFECTS)) return amount;

        if (entity.hasStatusEffect(StatusEffects.RESISTANCE) && !source.isIn(DamageTypeTags.BYPASSES_RESISTANCE)) {
            int resistance = Objects.requireNonNull(entity.getStatusEffect(StatusEffects.RESISTANCE)).getAmplifier() + 1;

            float resistancePercent = resistance * 0.2F;
            float resistedDamage = Math.min(amount * resistancePercent, amount);
            amount -= resistedDamage;

            if (resistedDamage > 0.0F && resistedDamage < 3.4028235E37F) {
                if (entity instanceof ServerPlayerEntity) {
                    ((ServerPlayerEntity) entity).increaseStat(Stats.DAMAGE_RESISTED, Math.round(resistedDamage * 10.0F));
                } else if (source.getAttacker() instanceof ServerPlayerEntity) {
                    ((ServerPlayerEntity) source.getAttacker()).increaseStat(Stats.DAMAGE_DEALT_RESISTED, Math.round(resistedDamage * 10.0F));
                }
            }
        }

        if (amount <= 0.0F) return 0.0F;
        if (source.isIn(DamageTypeTags.BYPASSES_ENCHANTMENTS)) return amount;
        if (!(entity.getWorld() instanceof ServerWorld serverWorld)) return amount;

        var protectionAmount = EnchantmentHelper.getProtectionAmount(serverWorld, entity, source);
        amount = net.minecraft.entity.DamageUtil.getInflictedDamage(amount, protectionAmount);
        return amount;
    }
}

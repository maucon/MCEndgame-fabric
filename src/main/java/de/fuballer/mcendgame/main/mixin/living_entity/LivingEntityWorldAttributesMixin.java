package de.fuballer.mcendgame.main.mixin.living_entity;

import de.fuballer.mcendgame.main.accessor.LivingEntityWorldAttributesAccessor;
import de.fuballer.mcendgame.main.component.custom_attribute.data.DoubleRoll;
import de.fuballer.mcendgame.main.component.custom_attribute.data.VanillaAttributeType;
import de.fuballer.mcendgame.main.component.world.VanillaTypeWorldAttributeInstance;
import de.fuballer.mcendgame.main.component.world.WorldAttributeAction;
import de.fuballer.mcendgame.main.util.extension.mixin.WorldMixinExtension;
import de.fuballer.mcendgame.main.util.minecraft.IdentifierUtil;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public class LivingEntityWorldAttributesMixin implements LivingEntityWorldAttributesAccessor {
    @Unique
    private int appliedWorldAttributesUpdate = 0;

    @Inject(
            method = "tick",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;sendEquipmentChanges()V")
    )
    void updateWorldAttributes(CallbackInfo ci) {
        var entity = (LivingEntity) (Object) this;
        var world = (ServerWorld) entity.getEntityWorld();

        var latestUpdate = WorldMixinExtension.INSTANCE.getAttributeUpdateCount(world);
        if (latestUpdate <= appliedWorldAttributesUpdate) return;

        var history = WorldMixinExtension.INSTANCE.getVanillaTypeAttributesHistory(world, entity);
        for (VanillaTypeWorldAttributeInstance updateInstance : history) {
            if (updateInstance.getUpdate() <= appliedWorldAttributesUpdate) continue;

            var updateAttribute = updateInstance.getAttribute();
            var type = (VanillaAttributeType) updateAttribute.getType();
            var attributeInstance = entity.getAttributeInstance(type.getAttribute());
            if (attributeInstance == null) continue;

            var identifier = IdentifierUtil.INSTANCE.defaultCustomAttribute(updateAttribute);

            if (updateInstance.getAction() == WorldAttributeAction.ADD) {
                if (attributeInstance.hasModifier(identifier)) continue;
                var modifier = new EntityAttributeModifier(
                        identifier,
                        ((DoubleRoll) updateAttribute.getRolls().getFirst()).getValue(),
                        type.getScaleType()
                );
                attributeInstance.addTemporaryModifier(modifier);
            } else {
                attributeInstance.removeModifier(identifier);
            }
        }

        appliedWorldAttributesUpdate = latestUpdate;
    }

    @Override
    public void mcendgame$resetWorldAttributesUpdate() {
        appliedWorldAttributesUpdate = 0;
    }
}

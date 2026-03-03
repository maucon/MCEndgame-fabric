package de.fuballer.mcendgame.main.mixin.living_entity;

import de.fuballer.mcendgame.main.accessor.LivingEntityCustomAttributesAccessor;
import de.fuballer.mcendgame.main.component.custom_attribute.data.CustomAttribute;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtOps;
import net.minecraft.registry.RegistryOps;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.LinkedList;
import java.util.List;

@Mixin(LivingEntity.class)
public class LivingEntityCustomAttributesMixin implements LivingEntityCustomAttributesAccessor {
    @Unique
    private final String CUSTOM_ATTRIBUTES_NBT_KEY = "CustomAttributes";

    @Unique
    private static final TrackedData<List<CustomAttribute>> CUSTOM_ATTRIBUTES =
            DataTracker.registerData(LivingEntity.class, CustomAttribute.Companion.getLIST_TRACKED_DATA_HANDLER());

    @Inject(method = "initDataTracker", at = @At("TAIL"))
    void initDataTracker(DataTracker.Builder builder, CallbackInfo ci) {
        builder.add(CUSTOM_ATTRIBUTES, new LinkedList<>());
    }

    @Inject(method = "writeCustomData", at = @At("TAIL"))
    void writeNbt(WriteView view, CallbackInfo ci) {
        var attributes = mcendgame$getCustomAttributes();
        if (attributes.isEmpty()) return;

        view.put(CUSTOM_ATTRIBUTES_NBT_KEY, CustomAttribute.Companion.getCODEC().listOf(), attributes);
    }

    @Inject(method = "readCustomData", at = @At("TAIL"))
    void readNbt(ReadView view, CallbackInfo ci) {
        var attributes = view.read(CUSTOM_ATTRIBUTES_NBT_KEY, CustomAttribute.Companion.getCODEC().listOf()).orElse(List.of());

        var entity = (LivingEntity) (Object) this;
        var dataTracker = entity.getDataTracker();
        dataTracker.set(CUSTOM_ATTRIBUTES, attributes);
    }

    @Override
    public void mcendgame$addCustomAttribute(CustomAttribute customAttribute) {
        var entity = (LivingEntity) (Object) this;
        var dataTracker = entity.getDataTracker();
        var attributes = new LinkedList<>(dataTracker.get(CUSTOM_ATTRIBUTES));
        attributes.add(customAttribute);
        dataTracker.set(CUSTOM_ATTRIBUTES, attributes);
    }

    @Override
    public List<CustomAttribute> mcendgame$getCustomAttributes() {
        var entity = (LivingEntity) (Object) this;
        var dataTracker = entity.getDataTracker();
        return dataTracker.get(CUSTOM_ATTRIBUTES);
    }
}
package de.fuballer.mcendgame.client.mixin.biped;

import de.fuballer.mcendgame.client.accessor.BipedEntityRenderStateAccessor;
import de.fuballer.mcendgame.main.component.item.custom.armor.interfaces.HideBipedBoneArmor;
import net.minecraft.client.render.entity.state.BipedEntityRenderState;
import net.minecraft.entity.EquipmentSlot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

import java.util.HashSet;
import java.util.Set;

@Mixin(BipedEntityRenderState.class)
public class BipedEntityRenderStateMixin implements BipedEntityRenderStateAccessor {
    @Unique
    private Set<HideBipedBoneArmor.BipedBone> hiddenBones = new HashSet<>();
    @Unique
    private Set<EquipmentSlot> hiddenArmor = new HashSet<>();

    @Override
    public void mcendgame$setHiddenBones(Set<HideBipedBoneArmor.BipedBone> bones) {
        hiddenBones = bones;
    }

    @Override
    public Set<HideBipedBoneArmor.BipedBone> mcendgame$getHiddenBones() {
        return hiddenBones;
    }

    @Override
    public void mcendgame$setHiddenArmor(Set<EquipmentSlot> armorSlots) {
        hiddenArmor = armorSlots;
    }

    @Override
    public Set<EquipmentSlot> mcendgame$getHiddenArmor() {
        return hiddenArmor;
    }
}

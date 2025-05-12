package de.fuballer.mcendgame.client.accessor;

import de.fuballer.mcendgame.main.component.item.custom.armor.interfaces.HideBipedBoneArmor;
import net.minecraft.entity.EquipmentSlot;

import java.util.Set;

public interface BipedEntityRenderStateAccessor {
    void mcendgame$setHiddenBones(Set<HideBipedBoneArmor.BipedBone> bones);

    Set<HideBipedBoneArmor.BipedBone> mcendgame$getHiddenBones();

    void mcendgame$setHiddenArmor(Set<EquipmentSlot> armorSlots);

    Set<EquipmentSlot> mcendgame$getHiddenArmor();
}

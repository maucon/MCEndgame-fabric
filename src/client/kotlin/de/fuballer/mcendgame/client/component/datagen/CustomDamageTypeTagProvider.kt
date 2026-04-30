package de.fuballer.mcendgame.client.component.datagen

import de.fuballer.mcendgame.main.MCEndgame
import de.fuballer.mcendgame.main.component.damage.custom_type.CustomDamageTypes
import de.fuballer.mcendgame.main.component.tags.CustomTags
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider
import net.minecraft.entity.damage.DamageType
import net.minecraft.entity.damage.DamageTypes
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.RegistryWrapper
import net.minecraft.registry.tag.DamageTypeTags
import java.util.concurrent.CompletableFuture

class CustomDamageTypeTagProvider(
    output: FabricDataOutput,
    registriesFuture: CompletableFuture<RegistryWrapper.WrapperLookup>
) : FabricTagProvider<DamageType>(output, RegistryKeys.DAMAGE_TYPE, registriesFuture) {
    override fun getName() = "${MCEndgame.MOD_ID}DamageTypeTagProvider"

    override fun configure(arg: RegistryWrapper.WrapperLookup) {
        getTagBuilder(DamageTypeTags.NO_KNOCKBACK)
            .addOptional(CustomDamageTypes.SPELL.value)

        getTagBuilder(DamageTypeTags.BYPASSES_SHIELD)
            .addOptional(CustomDamageTypes.GENERIC_ATTACK_UNBLOCKABLE.value)
            .addOptional(CustomDamageTypes.SPELL.value)

        getTagBuilder(CustomTags.MELEE_ATTACK)
            .add(DamageTypes.PLAYER_ATTACK.value)
            .add(DamageTypes.MOB_ATTACK.value)
            .add(DamageTypes.MOB_ATTACK_NO_AGGRO.value)
            .add(DamageTypes.STING.value)
            .add(DamageTypes.MACE_SMASH.value)
            .addOptional(CustomDamageTypes.GENERIC_ATTACK.value)
            .addOptional(CustomDamageTypes.GENERIC_ATTACK_UNBLOCKABLE.value)
            .addOptional(CustomDamageTypes.PIERCE_ATTACK.value)
            .addOptional(CustomDamageTypes.KINETIC_ATTACK.value)

        getTagBuilder(CustomTags.BLOCK_PHASING_IMMUNE)
            .add(DamageTypes.IN_WALL.value)
            .add(DamageTypes.CACTUS.value)
            .add(DamageTypes.SWEET_BERRY_BUSH.value)
            .add(DamageTypes.FALLING_ANVIL.value)
            .add(DamageTypes.FALLING_STALACTITE.value)
    }
}
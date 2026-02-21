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
        getOrCreateTagBuilder(DamageTypeTags.NO_KNOCKBACK)
            .addOptional(CustomDamageTypes.ELEMENTAL)

        getOrCreateTagBuilder(DamageTypeTags.BYPASSES_SHIELD)
            .addOptional(CustomDamageTypes.GENERIC_ATTACK_UNBLOCKABLE)

        getOrCreateTagBuilder(CustomTags.MELEE_ATTACK)
            .add(DamageTypes.PLAYER_ATTACK)
            .add(DamageTypes.MOB_ATTACK)
            .add(DamageTypes.MOB_ATTACK_NO_AGGRO)
            .add(DamageTypes.STING)
            .add(DamageTypes.MACE_SMASH)
            .addOptional(CustomDamageTypes.GENERIC_ATTACK)
            .addOptional(CustomDamageTypes.GENERIC_ATTACK_UNBLOCKABLE)
            .addOptional(CustomDamageTypes.SWEEPING)
    }
}
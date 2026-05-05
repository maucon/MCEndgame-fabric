package de.fuballer.mcendgame.main.component.dungeon.generation.encounter.encounters.scarred_one.data

import de.fuballer.mcendgame.main.component.custom_attribute.data.CustomAttribute
import de.fuballer.mcendgame.main.component.dungeon.generation.encounter.encounters.scarred_one.ScarredOneEffectTargetGroup
import de.fuballer.mcendgame.main.messaging.misc.GetCustomAttributesTextsCommand
import de.maucon.mauconframework.command.CommandGateway
import net.minecraft.network.RegistryByteBuf
import net.minecraft.network.codec.PacketCodec
import net.minecraft.network.codec.PacketCodecs
import net.minecraft.text.Text

data class RolledScarredOneEffect(
    val attribute: CustomAttribute,
    val targets: ScarredOneEffectTargetGroup,
) {
    companion object {
        val PACKET_CODEC: PacketCodec<RegistryByteBuf, RolledScarredOneEffect> =
            PacketCodec.tuple(
                CustomAttribute.PACKET_CODEC, RolledScarredOneEffect::attribute,

                PacketCodecs.indexed(
                    { index -> ScarredOneEffectTargetGroup.entries[index] },
                    ScarredOneEffectTargetGroup::ordinal
                ), RolledScarredOneEffect::targets,

                ::RolledScarredOneEffect
            )
    }

    fun getText(detailed: Boolean = false): Text {
        val targetsText = targets.text
        val attributeText = CommandGateway.apply(GetCustomAttributesTextsCommand(attribute, detailed)).texts.firstOrNull() ?: return targetsText
        return targetsText.copy().append(attributeText)
    }
}
package de.fuballer.mcendgame.client.component.item.custom.armor.model.geistergaloschen

import de.fuballer.mcendgame.client.mixin.ghostly_appearance.accessors.RenderLayerMultiPhaseAccessorMixin
import de.fuballer.mcendgame.client.mixin.ghostly_appearance.accessors.RenderLayerMultiPhaseParametersAccessorMixin
import de.fuballer.mcendgame.client.mixin.ghostly_appearance.accessors.RenderPhaseTextureAccessorMixin
import net.minecraft.client.render.RenderLayer
import net.minecraft.client.render.VertexConsumer
import net.minecraft.client.render.VertexConsumerProvider
import net.minecraft.util.Identifier
import java.util.*

class GhostlyVertexConsumerProvider(
    parent: VertexConsumerProvider,
) : VertexConsumerProvider {
    var delegate = VertexConsumerProvider { layer ->
        val vertexConsumer = parent.getBuffer(layer)
        val name = layer.name
        when {
            name == "leash" ||
                    name.contains("text") ||
                    name == "entity_shadow" -> vertexConsumer

            else -> GhostlyVertexConsumer(vertexConsumer)
        }
    }

    companion object {
        val DEFAULT_TEXTURE_ID: Identifier = Identifier.of("minecraft", "textures/misc/white.png")
    }

    override fun getBuffer(layer: RenderLayer): VertexConsumer {
        val texture = extractTexture(layer).orElse(DEFAULT_TEXTURE_ID)
        return when (layer.name) {
            "entity_translucent" -> delegate.getBuffer(layer)
            "eyes",
            "entity_solid",
            "entity_cutout",
            "entity_cutout_no_cull",
            "entity_cutout_no_cull_z_offset",
            "entity_solid_z_offset_forward",
            "entity_smooth_cutout" -> delegate.getBuffer(RenderLayer.getEntityTranslucent(texture))

            "item_entity_translucent_cull" -> delegate.getBuffer(layer)

            "armor_translucent" -> delegate.getBuffer(layer)
            "armor_cutout_no_cull" -> delegate.getBuffer(RenderLayer.createArmorTranslucent(texture))

            else -> delegate.getBuffer(layer)
        }
    }

    private fun extractTexture(layer: RenderLayer): Optional<Identifier> {
        val multiPhaseAccessor = layer as? RenderLayerMultiPhaseAccessorMixin ?: return Optional.empty()
        val parameters = multiPhaseAccessor.phases

        val parametersAccessor = parameters as? RenderLayerMultiPhaseParametersAccessorMixin ?: return Optional.empty()
        val textureBase = parametersAccessor.textureBase

        val textureBaseAccessor = textureBase as? RenderPhaseTextureAccessorMixin ?: return Optional.empty()
        return textureBaseAccessor.id
    }
}
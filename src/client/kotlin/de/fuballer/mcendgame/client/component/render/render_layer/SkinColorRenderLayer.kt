package de.fuballer.mcendgame.client.component.render.render_layer

import de.fuballer.mcendgame.main.util.ColorUtil
import net.minecraft.client.render.RenderLayer
import net.minecraft.client.render.VertexConsumer
import net.minecraft.client.render.VertexConsumerProvider
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.entity.EntityType
import net.minecraft.util.Identifier
import software.bernie.geckolib.animatable.GeoAnimatable
import software.bernie.geckolib.cache.`object`.BakedGeoModel
import software.bernie.geckolib.constant.dataticket.DataTicket
import software.bernie.geckolib.renderer.GeoArmorRenderer.RenderData
import software.bernie.geckolib.renderer.base.GeoRenderState
import software.bernie.geckolib.renderer.base.GeoRenderer
import software.bernie.geckolib.renderer.layer.TextureLayerGeoLayer

class SkinColorRenderLayer<T : GeoAnimatable, O, R : GeoRenderState>(
    renderer: GeoRenderer<T, O, R>,
    texture: Identifier,
) : TextureLayerGeoLayer<T, O, R>(renderer, texture) {
    override fun render(
        renderState: R,
        poseStack: MatrixStack,
        bakedModel: BakedGeoModel,
        renderType: RenderLayer?,
        bufferSource: VertexConsumerProvider,
        buffer: VertexConsumer?,
        packedLight: Int,
        packedOverlay: Int,
        renderColor: Int,
    ) {
        val wearerType = renderState.getGeckolibData(WEARING_ENTITY_TYPE_DATA_TICKET)

        if (NO_SKIN.contains(wearerType)) return

        val color = SKIN_COLORS[wearerType] ?: DEFAULT_COLOR
        super.render(renderState, poseStack, bakedModel, renderType, bufferSource, buffer, packedLight, packedOverlay, color)
    }

    override fun addRenderData(animatable: T, relatedObject: O, renderState: R) {
        if (relatedObject !is RenderData) return

        val wearerEntityType = relatedObject.entity.type
        renderState.addGeckolibData(WEARING_ENTITY_TYPE_DATA_TICKET, wearerEntityType)
    }

    companion object {
        val WEARING_ENTITY_TYPE_DATA_TICKET: DataTicket<EntityType<*>> = DataTicket.create("wearing_entity", EntityType::class.java)

        val DEFAULT_COLOR = ColorUtil.intColor(190, 180, 160)

        val SKIN_COLORS = mapOf(
            EntityType.ZOMBIE to ColorUtil.intColor(62, 105, 45),
            EntityType.ZOMBIE_VILLAGER to ColorUtil.intColor(62, 105, 45),
            EntityType.HUSK to ColorUtil.intColor(106, 93, 74),
            EntityType.ZOMBIFIED_PIGLIN to ColorUtil.intColor(230, 121, 115),
            EntityType.PIGLIN to ColorUtil.intColor(232, 160, 116),
            EntityType.SKELETON to ColorUtil.intColor(232, 232, 232),
        )

        val NO_SKIN = listOf(
            EntityType.ARMOR_STAND,
            //EntityType.SKELETON,
            EntityType.BOGGED,
            EntityType.STRAY,
            EntityType.WITHER_SKELETON,
        )
    }
}
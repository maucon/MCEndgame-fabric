package de.fuballer.mcendgame.components.entity.custom.entities.arachne

import de.fuballer.mcendgame.util.IdentifierUtil
import net.minecraft.client.model.*
import net.minecraft.client.render.entity.model.EntityModel
import net.minecraft.client.render.entity.model.EntityModelLayer


class ArachneEntityModel(
    modelPart: ModelPart,
) : EntityModel<ArachneRenderState>(modelPart) {
    val arachne = root.getChild("arachne")
    val cephalothorax = arachne.getChild("cephalothorax")
    val legs = cephalothorax.getChild("legs")
    val legLeft1 = legs.getChild("legLeft1")
    val temurLeft1 = legLeft1.getChild("temurLeft1")
    val tibiaLeft1 = temurLeft1.getChild("tibiaLeft1")
    val metatarsusLeft1 = tibiaLeft1.getChild("metatarsusLeft1")
    val tarsusLeft1 = metatarsusLeft1.getChild("tarsusLeft1")
    val legLeft2 = legs.getChild("legLeft2")
    val temurLeft2 = legLeft2.getChild("temurLeft2")
    val tibiaLeft2 = temurLeft2.getChild("tibiaLeft2")
    val metatarsusLeft2 = tibiaLeft2.getChild("metatarsusLeft2")
    val tarsusLeft2 = metatarsusLeft2.getChild("tarsusLeft2")
    val legLeft3 = legs.getChild("legLeft3")
    val temurLeft3 = legLeft3.getChild("temurLeft3")
    val tibiaLeft3 = temurLeft3.getChild("tibiaLeft3")
    val metatarsusLeft3 = tibiaLeft3.getChild("metatarsusLeft3")
    val tarsusLeft3 = metatarsusLeft3.getChild("tarsusLeft3")
    val legLeft4 = legs.getChild("legLeft4")
    val temurLeft4 = legLeft4.getChild("temurLeft4")
    val tibiaLeft4 = temurLeft4.getChild("tibiaLeft4")
    val metatarsusLeft4 = tibiaLeft4.getChild("metatarsusLeft4")
    val tarsusLeft4 = metatarsusLeft4.getChild("tarsusLeft4")
    val legRight1 = legs.getChild("legRight1")
    val temurRight1 = legRight1.getChild("temurRight1")
    val tibiaRight1 = temurRight1.getChild("tibiaRight1")
    val metatarsusRight1 = tibiaRight1.getChild("metatarsusRight1")
    val tarsusRight1 = metatarsusRight1.getChild("tarsusRight1")
    val legRight2 = legs.getChild("legRight2")
    val temurRight2 = legRight2.getChild("temurRight2")
    val tibiaRight2 = temurRight2.getChild("tibiaRight2")
    val metatarsusRight2 = tibiaRight2.getChild("metatarsusRight2")
    val tarsusRight2 = metatarsusRight2.getChild("tarsusRight2")
    val legRight3 = legs.getChild("legRight3")
    val temurRight3 = legRight3.getChild("temurRight3")
    val tibiaRight3 = temurRight3.getChild("tibiaRight3")
    val metatarsusRight3 = tibiaRight3.getChild("metatarsusRight3")
    val tarsusRight3 = metatarsusRight3.getChild("tarsusRight3")
    val legRight4 = legs.getChild("legRight4")
    val temurRight4 = legRight4.getChild("temurRight4")
    val tibiaRight4 = temurRight4.getChild("tibiaRight4")
    val metatarsusRight4 = tibiaRight4.getChild("metatarsusRight4")
    val tarsusRight4 = metatarsusRight4.getChild("tarsusRight4")
    val upperbody = cephalothorax.getChild("upperbody")
    val chest = upperbody.getChild("chest")
    val breast = chest.getChild("breast")
    val chestDressLower = breast.getChild("chestDressLower")
    val breastTop = breast.getChild("breastTop")
    val neck = chest.getChild("neck")
    val head = neck.getChild("head")
    val armLeft = chest.getChild("armLeft")
    val armLeftLower = armLeft.getChild("armLeftLower")
    val armRight = chest.getChild("armRight")
    val armRightLower = armRight.getChild("armRightLower")
    val abdomen = cephalothorax.getChild("abdomen")

    companion object {
        val ARACHNE = EntityModelLayer(IdentifierUtil.default("arachne"), "main")

        fun getTexturedModelData(): TexturedModelData {
            val modelData = ModelData()
            val modelPartData = modelData.root
            val arachne =
                modelPartData.addChild("arachne", ModelPartBuilder.create(), ModelTransform.pivot(0.0f, 25.0f, -3.0f))

            val cephalothorax = arachne.addChild(
                "cephalothorax",
                ModelPartBuilder.create().uv(110, 118).cuboid(-4.0f, -12.0f, -2.0f, 8.0f, 6.0f, 10.0f, Dilation(0.0f))
                    .uv(114, 108).cuboid(-3.0f, -13.0f, 0.0f, 6.0f, 1.0f, 8.0f, Dilation(0.0f))
                    .uv(120, 103).cuboid(-2.5f, -14.0f, 1.0f, 5.0f, 1.0f, 3.0f, Dilation(0.0f))
                    .uv(122, 98).cuboid(-2.0f, -16.0f, 0.5f, 4.0f, 2.0f, 2.0f, Dilation(0.0f)),
                ModelTransform.pivot(0.0f, 0.0f, 0.0f)
            )

            val legs = cephalothorax.addChild("legs", ModelPartBuilder.create(), ModelTransform.pivot(0.0f, 0.0f, 0.0f))

            val legLeft1 = legs.addChild(
                "legLeft1",
                ModelPartBuilder.create().uv(147, 105).cuboid(-0.5f, -1.0f, -1.0f, 2.0f, 2.0f, 2.0f, Dilation(0.25f))
                    .uv(147, 110).cuboid(-0.5f, -1.0f, -1.0f, 2.0f, 2.0f, 2.0f, Dilation(0.3f)),
                ModelTransform.of(3.0f, -8.0f, -2.0f, 0.0011f, 1.149f, 0.087f)
            )

            val temurLeft1 = legLeft1.addChild(
                "temurLeft1",
                ModelPartBuilder.create().uv(156, 105).cuboid(-0.25f, -1.0f, -1.0f, 13.0f, 2.0f, 2.0f, Dilation(0.0f))
                    .uv(156, 110).cuboid(-0.25f, -1.0f, -1.0f, 13.0f, 2.0f, 2.0f, Dilation(0.05f)),
                ModelTransform.of(1.0f, 0.0f, 0.0f, 0.0085f, -0.0059f, -1.1775f)
            )

            val tibiaLeft1 = temurLeft1.addChild(
                "tibiaLeft1",
                ModelPartBuilder.create().uv(186, 105)
                    .cuboid(0.1455f, -0.1039f, -1.0f, 12.0f, 2.0f, 2.0f, Dilation(-0.25f))
                    .uv(186, 110).cuboid(0.1455f, -0.1039f, -1.0f, 12.0f, 2.0f, 2.0f, Dilation(-0.2f)),
                ModelTransform.of(12.65f, -1.0f, 0.0f, 0.0f, 0.0f, 1.4835f)
            )

            val metatarsusLeft1 = tibiaLeft1.addChild(
                "metatarsusLeft1",
                ModelPartBuilder.create().uv(214, 105)
                    .cuboid(-0.2502f, -0.2929f, -1.0034f, 11.0f, 2.0f, 2.0f, Dilation(-0.3f))
                    .uv(214, 110).cuboid(-0.2502f, -0.2929f, -1.0034f, 11.0f, 2.0f, 2.0f, Dilation(-0.25f)),
                ModelTransform.of(11.8955f, 0.1461f, 0.0f, 0.0048f, 0.0019f, 0.7851f)
            )

            val tarsusLeft1 = metatarsusLeft1.addChild(
                "tarsusLeft1",
                ModelPartBuilder.create().uv(241, 107)
                    .cuboid(-0.6299f, 0.0964f, -0.5f, 6.0f, 1.0f, 1.0f, Dilation(0.1f))
                    .uv(241, 112).cuboid(-0.6299f, 0.0964f, -0.5f, 6.0f, 1.0f, 1.0f, Dilation(0.15f)),
                ModelTransform.of(10.4998f, 0.2071f, -0.0034f, 0.0f, 0.0f, 0.2182f)
            )

            val legLeft2 = legs.addChild(
                "legLeft2",
                ModelPartBuilder.create().uv(147, 115).cuboid(-0.5f, -1.0f, -1.0f, 2.0f, 2.0f, 2.0f, Dilation(0.25f))
                    .uv(147, 120).cuboid(-0.5f, -1.0f, -1.0f, 2.0f, 2.0f, 2.0f, Dilation(0.3f)),
                ModelTransform.of(4.0f, -8.0f, 0.0f, 0.0005f, 0.4509f, 0.0863f)
            )

            val temurLeft2 = legLeft2.addChild(
                "temurLeft2",
                ModelPartBuilder.create().uv(156, 115).cuboid(-0.25f, -1.0f, -1.0f, 13.0f, 2.0f, 2.0f, Dilation(0.0f))
                    .uv(156, 120).cuboid(-0.25f, -1.0f, -1.0f, 13.0f, 2.0f, 2.0f, Dilation(0.05f)),
                ModelTransform.of(1.0f, 0.0f, 0.0f, 0.0085f, -0.0059f, -1.1775f)
            )

            val tibiaLeft2 = temurLeft2.addChild(
                "tibiaLeft2",
                ModelPartBuilder.create().uv(186, 115)
                    .cuboid(0.1371f, -0.028f, -1.0f, 12.0f, 2.0f, 2.0f, Dilation(-0.25f))
                    .uv(186, 120).cuboid(0.1371f, -0.028f, -1.0f, 12.0f, 2.0f, 2.0f, Dilation(-0.2f)),
                ModelTransform.of(12.65f, -1.0f, 0.0f, 0.0f, 0.0f, 1.4399f)
            )

            val metatarsusLeft2 = tibiaLeft2.addChild(
                "metatarsusLeft2",
                ModelPartBuilder.create().uv(214, 115)
                    .cuboid(-0.2502f, -0.2929f, -1.0034f, 11.0f, 2.0f, 2.0f, Dilation(-0.3f))
                    .uv(214, 120).cuboid(-0.2502f, -0.2929f, -1.0034f, 11.0f, 2.0f, 2.0f, Dilation(-0.25f)),
                ModelTransform.of(11.8871f, 0.222f, 0.0f, 0.0048f, 0.0019f, 0.7851f)
            )

            val tarsusLeft2 = metatarsusLeft2.addChild(
                "tarsusLeft2",
                ModelPartBuilder.create().uv(241, 117)
                    .cuboid(-0.6299f, 0.0964f, -0.5f, 6.0f, 1.0f, 1.0f, Dilation(0.1f))
                    .uv(241, 122).cuboid(-0.6299f, 0.0964f, -0.5f, 6.0f, 1.0f, 1.0f, Dilation(0.15f)),
                ModelTransform.of(10.4998f, 0.2071f, -0.0034f, 0.0f, 0.0f, 0.2182f)
            )

            val legLeft3 = legs.addChild(
                "legLeft3",
                ModelPartBuilder.create().uv(147, 125).cuboid(-0.5f, -1.0f, -1.0f, 2.0f, 2.0f, 2.0f, Dilation(0.25f))
                    .uv(147, 130).cuboid(-0.5f, -1.0f, -1.0f, 2.0f, 2.0f, 2.0f, Dilation(0.3f)),
                ModelTransform.of(4.0f, -8.0f, 3.0f, 0.0004f, -0.0727f, 0.086f)
            )

            val temurLeft3 = legLeft3.addChild(
                "temurLeft3",
                ModelPartBuilder.create().uv(156, 125).cuboid(-0.25f, -1.0f, -1.0f, 13.0f, 2.0f, 2.0f, Dilation(0.0f))
                    .uv(156, 130).cuboid(-0.25f, -1.0f, -1.0f, 13.0f, 2.0f, 2.0f, Dilation(0.05f)),
                ModelTransform.of(1.0f, 0.0f, 0.0f, 0.0085f, -0.0059f, -1.1775f)
            )

            val tibiaLeft3 = temurLeft3.addChild(
                "tibiaLeft3",
                ModelPartBuilder.create().uv(186, 125)
                    .cuboid(0.1371f, -0.028f, -1.0f, 12.0f, 2.0f, 2.0f, Dilation(-0.25f))
                    .uv(186, 130).cuboid(0.1371f, -0.028f, -1.0f, 12.0f, 2.0f, 2.0f, Dilation(-0.2f)),
                ModelTransform.of(12.65f, -1.0f, 0.0f, 0.0f, 0.0f, 1.4399f)
            )

            val metatarsusLeft3 = tibiaLeft3.addChild(
                "metatarsusLeft3",
                ModelPartBuilder.create().uv(214, 125)
                    .cuboid(-0.2502f, -0.2929f, -1.0034f, 11.0f, 2.0f, 2.0f, Dilation(-0.3f))
                    .uv(214, 130).cuboid(-0.2502f, -0.2929f, -1.0034f, 11.0f, 2.0f, 2.0f, Dilation(-0.25f)),
                ModelTransform.of(11.8871f, 0.222f, 0.0f, 0.0048f, 0.0019f, 0.7851f)
            )

            val tarsusLeft3 = metatarsusLeft3.addChild(
                "tarsusLeft3",
                ModelPartBuilder.create().uv(241, 127)
                    .cuboid(-0.6299f, 0.0964f, -0.5f, 6.0f, 1.0f, 1.0f, Dilation(0.1f))
                    .uv(241, 132).cuboid(-0.6299f, 0.0964f, -0.5f, 6.0f, 1.0f, 1.0f, Dilation(0.15f)),
                ModelTransform.of(10.4998f, 0.2071f, -0.0034f, 0.0f, 0.0f, 0.2182f)
            )

            val legLeft4 = legs.addChild(
                "legLeft4",
                ModelPartBuilder.create().uv(147, 135).cuboid(-0.5f, -1.0f, -1.0f, 2.0f, 2.0f, 2.0f, Dilation(0.25f))
                    .uv(147, 140).cuboid(-0.5f, -1.0f, -1.0f, 2.0f, 2.0f, 2.0f, Dilation(0.3f)),
                ModelTransform.of(4.0f, -8.0f, 6.0f, 0.0005f, -0.5963f, 0.0857f)
            )

            val temurLeft4 = legLeft4.addChild(
                "temurLeft4",
                ModelPartBuilder.create().uv(156, 135).cuboid(-0.25f, -1.0f, -1.0f, 13.0f, 2.0f, 2.0f, Dilation(0.0f))
                    .uv(156, 140).cuboid(-0.25f, -1.0f, -1.0f, 13.0f, 2.0f, 2.0f, Dilation(0.05f)),
                ModelTransform.of(1.0f, 0.0f, 0.0f, 0.0085f, -0.0059f, -1.1775f)
            )

            val tibiaLeft4 = temurLeft4.addChild(
                "tibiaLeft4",
                ModelPartBuilder.create().uv(186, 135)
                    .cuboid(0.1309f, -0.028f, -1.1427f, 12.0f, 2.0f, 2.0f, Dilation(-0.25f))
                    .uv(186, 140).cuboid(0.1309f, -0.028f, -1.1427f, 12.0f, 2.0f, 2.0f, Dilation(-0.2f)),
                ModelTransform.of(12.65f, -1.0f, 0.0f, 0.0f, -0.0873f, 1.4399f)
            )

            val metatarsusLeft4 = tibiaLeft4.addChild(
                "metatarsusLeft4",
                ModelPartBuilder.create().uv(214, 135)
                    .cuboid(-0.2502f, -0.2929f, -1.0034f, 11.0f, 2.0f, 2.0f, Dilation(-0.3f))
                    .uv(214, 140).cuboid(-0.2502f, -0.2929f, -1.0034f, 11.0f, 2.0f, 2.0f, Dilation(-0.25f)),
                ModelTransform.of(11.8809f, 0.222f, -0.1427f, 0.0048f, 0.0019f, 0.7851f)
            )

            val tarsusLeft4 = metatarsusLeft4.addChild(
                "tarsusLeft4",
                ModelPartBuilder.create().uv(241, 137)
                    .cuboid(-0.6299f, 0.0964f, -0.5f, 6.0f, 1.0f, 1.0f, Dilation(0.1f))
                    .uv(241, 142).cuboid(-0.6299f, 0.0964f, -0.5f, 6.0f, 1.0f, 1.0f, Dilation(0.15f)),
                ModelTransform.of(10.4998f, 0.2071f, -0.0034f, 0.0f, 0.0f, 0.2182f)
            )

            val legRight1 = legs.addChild(
                "legRight1",
                ModelPartBuilder.create().uv(101, 105).cuboid(-1.5f, -1.0f, -1.0f, 2.0f, 2.0f, 2.0f, Dilation(0.25f))
                    .uv(101, 110).cuboid(-1.5f, -1.0f, -1.0f, 2.0f, 2.0f, 2.0f, Dilation(0.3f)),
                ModelTransform.of(-3.0f, -8.0f, -2.0f, 0.0011f, -1.149f, -0.087f)
            )

            val temurRight1 = legRight1.addChild(
                "temurRight1",
                ModelPartBuilder.create().uv(70, 105).cuboid(-12.75f, -1.0f, -1.0f, 13.0f, 2.0f, 2.0f, Dilation(0.0f))
                    .uv(70, 110).cuboid(-12.75f, -1.0f, -1.0f, 13.0f, 2.0f, 2.0f, Dilation(0.05f)),
                ModelTransform.of(-1.0f, 0.0f, 0.0f, 0.0085f, 0.0059f, 1.1775f)
            )

            val tibiaRight1 = temurRight1.addChild(
                "tibiaRight1",
                ModelPartBuilder.create().uv(42, 105)
                    .cuboid(-12.1455f, -0.1039f, -1.0f, 12.0f, 2.0f, 2.0f, Dilation(-0.25f))
                    .uv(42, 110).cuboid(-12.1455f, -0.1039f, -1.0f, 12.0f, 2.0f, 2.0f, Dilation(-0.2f)),
                ModelTransform.of(-12.65f, -1.0f, 0.0f, 0.0f, 0.0f, -1.4835f)
            )

            val metatarsusRight1 = tibiaRight1.addChild(
                "metatarsusRight1",
                ModelPartBuilder.create().uv(16, 105)
                    .cuboid(-10.7498f, -0.2929f, -1.0034f, 11.0f, 2.0f, 2.0f, Dilation(-0.3f))
                    .uv(16, 110).cuboid(-10.7498f, -0.2929f, -1.0034f, 11.0f, 2.0f, 2.0f, Dilation(-0.25f)),
                ModelTransform.of(-11.8955f, 0.1461f, 0.0f, 0.0048f, -0.0019f, -0.7851f)
            )

            val tarsusRight1 = metatarsusRight1.addChild(
                "tarsusRight1",
                ModelPartBuilder.create().uv(1, 107).cuboid(-5.3701f, 0.0964f, -0.5f, 6.0f, 1.0f, 1.0f, Dilation(0.1f))
                    .uv(1, 112).cuboid(-5.3701f, 0.0964f, -0.5f, 6.0f, 1.0f, 1.0f, Dilation(0.15f)),
                ModelTransform.of(-10.4998f, 0.2071f, -0.0034f, 0.0f, 0.0f, -0.2182f)
            )

            val legRight2 = legs.addChild(
                "legRight2",
                ModelPartBuilder.create().uv(101, 115).cuboid(-1.5f, -1.0f, -1.0f, 2.0f, 2.0f, 2.0f, Dilation(0.25f))
                    .uv(101, 120).cuboid(-1.5f, -1.0f, -1.0f, 2.0f, 2.0f, 2.0f, Dilation(0.3f)),
                ModelTransform.of(-4.0f, -8.0f, 0.0f, 0.0005f, -0.4509f, -0.0863f)
            )

            val temurRight2 = legRight2.addChild(
                "temurRight2",
                ModelPartBuilder.create().uv(70, 115).cuboid(-12.75f, -1.0f, -1.0f, 13.0f, 2.0f, 2.0f, Dilation(0.0f))
                    .uv(70, 120).cuboid(-12.75f, -1.0f, -1.0f, 13.0f, 2.0f, 2.0f, Dilation(0.05f)),
                ModelTransform.of(-1.0f, 0.0f, 0.0f, 0.0085f, 0.0059f, 1.1775f)
            )

            val tibiaRight2 = temurRight2.addChild(
                "tibiaRight2",
                ModelPartBuilder.create().uv(42, 115)
                    .cuboid(-12.1371f, -0.028f, -1.0f, 12.0f, 2.0f, 2.0f, Dilation(-0.25f))
                    .uv(42, 120).cuboid(-12.1371f, -0.028f, -1.0f, 12.0f, 2.0f, 2.0f, Dilation(-0.2f)),
                ModelTransform.of(-12.65f, -1.0f, 0.0f, 0.0f, 0.0f, -1.4399f)
            )

            val metatarsusRight2 = tibiaRight2.addChild(
                "metatarsusRight2",
                ModelPartBuilder.create().uv(16, 115)
                    .cuboid(-10.7498f, -0.2929f, -1.0034f, 11.0f, 2.0f, 2.0f, Dilation(-0.3f))
                    .uv(16, 120).cuboid(-10.7498f, -0.2929f, -1.0034f, 11.0f, 2.0f, 2.0f, Dilation(-0.25f)),
                ModelTransform.of(-11.8871f, 0.222f, 0.0f, 0.0048f, -0.0019f, -0.7851f)
            )

            val tarsusRight2 = metatarsusRight2.addChild(
                "tarsusRight2",
                ModelPartBuilder.create().uv(1, 117).cuboid(-5.3701f, 0.0964f, -0.5f, 6.0f, 1.0f, 1.0f, Dilation(0.1f))
                    .uv(1, 122).cuboid(-5.3701f, 0.0964f, -0.5f, 6.0f, 1.0f, 1.0f, Dilation(0.15f)),
                ModelTransform.of(-10.4998f, 0.2071f, -0.0034f, 0.0f, 0.0f, -0.2182f)
            )

            val legRight3 = legs.addChild(
                "legRight3",
                ModelPartBuilder.create().uv(101, 125).cuboid(-1.5f, -1.0f, -1.0f, 2.0f, 2.0f, 2.0f, Dilation(0.25f))
                    .uv(101, 130).cuboid(-1.5f, -1.0f, -1.0f, 2.0f, 2.0f, 2.0f, Dilation(0.3f)),
                ModelTransform.of(-4.0f, -8.0f, 3.0f, 0.0004f, 0.0727f, -0.086f)
            )

            val temurRight3 = legRight3.addChild(
                "temurRight3",
                ModelPartBuilder.create().uv(70, 125).cuboid(-12.75f, -1.0f, -1.0f, 13.0f, 2.0f, 2.0f, Dilation(0.0f))
                    .uv(70, 130).cuboid(-12.75f, -1.0f, -1.0f, 13.0f, 2.0f, 2.0f, Dilation(0.05f)),
                ModelTransform.of(-1.0f, 0.0f, 0.0f, 0.0085f, 0.0059f, 1.1775f)
            )

            val tibiaRight3 = temurRight3.addChild(
                "tibiaRight3",
                ModelPartBuilder.create().uv(42, 125)
                    .cuboid(-12.1371f, -0.028f, -1.0f, 12.0f, 2.0f, 2.0f, Dilation(-0.25f))
                    .uv(42, 130).cuboid(-12.1371f, -0.028f, -1.0f, 12.0f, 2.0f, 2.0f, Dilation(-0.2f)),
                ModelTransform.of(-12.65f, -1.0f, 0.0f, 0.0f, 0.0f, -1.4399f)
            )

            val metatarsusRight3 = tibiaRight3.addChild(
                "metatarsusRight3",
                ModelPartBuilder.create().uv(16, 125)
                    .cuboid(-10.7498f, -0.2929f, -1.0034f, 11.0f, 2.0f, 2.0f, Dilation(-0.3f))
                    .uv(16, 130).cuboid(-10.7498f, -0.2929f, -1.0034f, 11.0f, 2.0f, 2.0f, Dilation(-0.25f)),
                ModelTransform.of(-11.8871f, 0.222f, 0.0f, 0.0048f, -0.0019f, -0.7851f)
            )

            val tarsusRight3 = metatarsusRight3.addChild(
                "tarsusRight3",
                ModelPartBuilder.create().uv(1, 127).cuboid(-5.3701f, 0.0964f, -0.5f, 6.0f, 1.0f, 1.0f, Dilation(0.1f))
                    .uv(1, 132).cuboid(-5.3701f, 0.0964f, -0.5f, 6.0f, 1.0f, 1.0f, Dilation(0.15f)),
                ModelTransform.of(-10.4998f, 0.2071f, -0.0034f, 0.0f, 0.0f, -0.2182f)
            )

            val legRight4 = legs.addChild(
                "legRight4",
                ModelPartBuilder.create().uv(101, 135).cuboid(-1.5f, -1.0f, -1.0f, 2.0f, 2.0f, 2.0f, Dilation(0.25f))
                    .uv(101, 140).cuboid(-1.5f, -1.0f, -1.0f, 2.0f, 2.0f, 2.0f, Dilation(0.3f)),
                ModelTransform.of(-4.0f, -8.0f, 6.0f, 0.0005f, 0.5963f, -0.0857f)
            )

            val temurRight4 = legRight4.addChild(
                "temurRight4",
                ModelPartBuilder.create().uv(70, 135).cuboid(-12.75f, -1.0f, -1.0f, 13.0f, 2.0f, 2.0f, Dilation(0.0f))
                    .uv(70, 140).cuboid(-12.75f, -1.0f, -1.0f, 13.0f, 2.0f, 2.0f, Dilation(0.05f)),
                ModelTransform.of(-1.0f, 0.0f, 0.0f, 0.0085f, 0.0059f, 1.1775f)
            )

            val tibiaRight4 = temurRight4.addChild(
                "tibiaRight4",
                ModelPartBuilder.create().uv(42, 135)
                    .cuboid(-12.1309f, -0.028f, -1.1427f, 12.0f, 2.0f, 2.0f, Dilation(-0.25f))
                    .uv(42, 140).cuboid(-12.1309f, -0.028f, -1.1427f, 12.0f, 2.0f, 2.0f, Dilation(-0.2f)),
                ModelTransform.of(-12.65f, -1.0f, 0.0f, 0.0f, 0.0873f, -1.4399f)
            )

            val metatarsusRight4 = tibiaRight4.addChild(
                "metatarsusRight4",
                ModelPartBuilder.create().uv(16, 135)
                    .cuboid(-10.7498f, -0.2929f, -1.0034f, 11.0f, 2.0f, 2.0f, Dilation(-0.3f))
                    .uv(16, 140).cuboid(-10.7498f, -0.2929f, -1.0034f, 11.0f, 2.0f, 2.0f, Dilation(-0.25f)),
                ModelTransform.of(-11.8809f, 0.222f, -0.1427f, 0.0048f, -0.0019f, -0.7851f)
            )

            val tarsusRight4 = metatarsusRight4.addChild(
                "tarsusRight4",
                ModelPartBuilder.create().uv(1, 137).cuboid(-5.3701f, 0.0964f, -0.5f, 6.0f, 1.0f, 1.0f, Dilation(0.1f))
                    .uv(1, 142).cuboid(-5.3701f, 0.0964f, -0.5f, 6.0f, 1.0f, 1.0f, Dilation(0.15f)),
                ModelTransform.of(-10.4998f, 0.2071f, -0.0034f, 0.0f, 0.0f, -0.2182f)
            )

            val upperbody = cephalothorax.addChild(
                "upperbody",
                ModelPartBuilder.create().uv(116, 80).cuboid(-4.0f, -5.5f, -2.25f, 8.0f, 5.0f, 4.0f, Dilation(-0.35f))
                    .uv(116, 90).cuboid(-4.0f, -1.5f, -2.25f, 8.0f, 3.0f, 4.0f, Dilation(0.1f)),
                ModelTransform.pivot(0.0f, -12.5f, 0.25f)
            )

            val chest = upperbody.addChild(
                "chest",
                ModelPartBuilder.create().uv(116, 49).cuboid(-4.0f, -7.0f, -2.25f, 8.0f, 7.0f, 4.0f, Dilation(0.0f)),
                ModelTransform.pivot(0.0f, -4.5f, -0.25f)
            )

            val breast = chest.addChild(
                "breast",
                ModelPartBuilder.create().uv(117, 67).cuboid(-4.0f, -0.5f, -2.0f, 8.0f, 2.0f, 3.0f, Dilation(-0.25f)),
                ModelTransform.pivot(0.0f, -4.0f, -2.25f)
            )

            val chestDressLower = breast.addChild(
                "chestDressLower",
                ModelPartBuilder.create().uv(118, 73).cuboid(-3.5f, -0.25f, 0.0f, 7.0f, 3.0f, 3.0f, Dilation(0.0f)),
                ModelTransform.of(0.0f, 1.25f, -1.5f, 0.5236f, 0.0f, 0.0f)
            )

            val breastTop = breast.addChild(
                "breastTop",
                ModelPartBuilder.create(),
                ModelTransform.of(0.0f, -0.25f, -1.7f, 0.48f, 0.0f, 0.0f)
            )

            val breastRight_r1 = breastTop.addChild(
                "breastRight_r1",
                ModelPartBuilder.create().uv(115, 61).cuboid(-1.0f, 0.0f, -0.2f, 3.0f, 2.0f, 3.0f, Dilation(0.0f))
                    .uv(129, 61).cuboid(2.5f, 0.0f, -0.2f, 3.0f, 2.0f, 3.0f, Dilation(0.0f)),
                ModelTransform.of(-2.25f, 0.0f, 0.2f, 0.2618f, 0.0f, 0.0f)
            )

            val neck = chest.addChild(
                "neck",
                ModelPartBuilder.create().uv(122, 42).cuboid(-1.5f, -2.0f, -1.5f, 3.0f, 3.0f, 3.0f, Dilation(0.0f)),
                ModelTransform.pivot(0.0f, -7.0f, 0.0f)
            )

            val head = neck.addChild(
                "head",
                ModelPartBuilder.create().uv(112, 25).cuboid(-4.0f, -8.0f, -4.25f, 8.0f, 8.0f, 8.0f, Dilation(0.0f))
                    .uv(112, 0).cuboid(-4.0f, -8.0f, -4.25f, 8.0f, 16.0f, 8.0f, Dilation(0.15f)),
                ModelTransform.pivot(0.0f, -1.0f, 0.0f)
            )

            val armLeft = chest.addChild(
                "armLeft",
                ModelPartBuilder.create().uv(142, 51).cuboid(0.0f, -0.5f, -1.5f, 3.0f, 6.0f, 3.0f, Dilation(0.0f))
                    .uv(155, 51).cuboid(0.0f, -0.5f, -1.5f, 3.0f, 6.0f, 3.0f, Dilation(0.1f)),
                ModelTransform.pivot(3.75f, -6.25f, 0.0f)
            )

            val armLeftLower = armLeft.addChild(
                "armLeftLower",
                ModelPartBuilder.create().uv(142, 61).cuboid(4.0f, -18.75f, -1.5f, 3.0f, 7.0f, 3.0f, Dilation(-0.25f))
                    .uv(155, 61).cuboid(4.0f, -18.75f, -1.5f, 3.0f, 7.0f, 3.0f, Dilation(-0.15f)),
                ModelTransform.pivot(-4.0f, 23.25f, 0.0f)
            )

            val armRight = chest.addChild(
                "armRight",
                ModelPartBuilder.create().uv(102, 50).cuboid(-3.0f, -0.5f, -1.5f, 3.0f, 6.0f, 3.0f, Dilation(0.0f))
                    .uv(89, 50).cuboid(-3.0f, -0.5f, -1.5f, 3.0f, 6.0f, 3.0f, Dilation(0.1f)),
                ModelTransform.pivot(-3.75f, -6.25f, 0.0f)
            )

            val armRightLower = armRight.addChild(
                "armRightLower",
                ModelPartBuilder.create().uv(102, 60).cuboid(-7.0f, -18.75f, -1.5f, 3.0f, 7.0f, 3.0f, Dilation(-0.25f))
                    .uv(89, 60).cuboid(-7.0f, -18.75f, -1.5f, 3.0f, 7.0f, 3.0f, Dilation(-0.15f)),
                ModelTransform.pivot(4.0f, 23.25f, 0.0f)
            )

            val abdomen = cephalothorax.addChild(
                "abdomen",
                ModelPartBuilder.create().uv(104, 147).cuboid(-6.0f, -5.0f, 0.0f, 12.0f, 9.0f, 12.0f, Dilation(0.0f))
                    .uv(109, 135).cuboid(-4.5f, -6.0f, 1.0f, 9.0f, 1.0f, 10.0f, Dilation(0.0f))
                    .uv(109, 169).cuboid(-4.5f, 4.0f, 1.0f, 9.0f, 1.0f, 10.0f, Dilation(0.0f))
                    .uv(81, 151).cuboid(-7.0f, -4.0f, 1.0f, 1.0f, 7.0f, 10.0f, Dilation(0.0f))
                    .uv(153, 151).cuboid(6.0f, -4.0f, 1.0f, 1.0f, 7.0f, 10.0f, Dilation(0.0f))
                    .uv(117, 181).cuboid(-5.0f, -3.75f, 12.0f, 10.0f, 7.0f, 1.0f, Dilation(0.0f))
                    .uv(119, 190).cuboid(-4.0f, -2.25f, 13.0f, 8.0f, 5.0f, 1.0f, Dilation(0.0f)),
                ModelTransform.pivot(0.0f, -9.0f, 7.0f)
            )
            return TexturedModelData.of(modelData, 256, 256)
        }
    }

    override fun setAngles(
        renderState: ArachneRenderState,
    ) {
        super.setAngles(renderState)

        animate(renderState.idleAnimationState, ArachneAnimation.IDLE, renderState.age, 1.0F)
        val walkAnimSpeed = renderState.moveSpeed * 6F / renderState.baseScale
        animate(renderState.walkAnimationState, ArachneAnimation.WALK, renderState.age, walkAnimSpeed)
        animate(renderState.walkBWAnimationState, ArachneAnimation.WALK_BW, renderState.age, walkAnimSpeed)

        animate(renderState.spitAnimationState, ArachneAnimation.SPIT, renderState.age, 1.0F)

        setHeadAngles(renderState)
    }

    private fun setHeadAngles(
        renderState: ArachneRenderState,
    ) {
        head.pitch += Math.toRadians(renderState.pitch.toDouble()).toFloat()
        head.yaw += Math.toRadians(renderState.yawDegrees.toDouble()).toFloat()
        head.yaw = Math.clamp(head.yaw, -1F, 1F)
    }
}
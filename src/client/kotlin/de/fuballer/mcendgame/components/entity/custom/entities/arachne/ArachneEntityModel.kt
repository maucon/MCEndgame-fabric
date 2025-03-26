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
    val temur4 = legLeft1.getChild("temur4")
    val tibia4 = temur4.getChild("tibia4")
    val metatarsus4 = tibia4.getChild("metatarsus4")
    val tarsus4 = metatarsus4.getChild("tarsus4")
    val legLeft2 = legs.getChild("legLeft2")
    val temur2 = legLeft2.getChild("temur2")
    val tibia2 = temur2.getChild("tibia2")
    val metatarsus2 = tibia2.getChild("metatarsus2")
    val tarsus2 = metatarsus2.getChild("tarsus2")
    val legLeft3 = legs.getChild("legLeft3")
    val temur3 = legLeft3.getChild("temur3")
    val tibia3 = temur3.getChild("tibia3")
    val metatarsus3 = tibia3.getChild("metatarsus3")
    val tarsus3 = metatarsus3.getChild("tarsus3")
    val legLeft4 = legs.getChild("legLeft4")
    val temur = legLeft4.getChild("temur")
    val tibia = temur.getChild("tibia")
    val metatarsus = tibia.getChild("metatarsus")
    val tarsus = metatarsus.getChild("tarsus")
    val legRight1 = legs.getChild("legRight1")
    val temur5 = legRight1.getChild("temur5")
    val tibia5 = temur5.getChild("tibia5")
    val metatarsus5 = tibia5.getChild("metatarsus5")
    val tarsus5 = metatarsus5.getChild("tarsus5")
    val legRight2 = legs.getChild("legRight2")
    val temur6 = legRight2.getChild("temur6")
    val tibia6 = temur6.getChild("tibia6")
    val metatarsus6 = tibia6.getChild("metatarsus6")
    val tarsus6 = metatarsus6.getChild("tarsus6")
    val legRight3 = legs.getChild("legRight3")
    val temur7 = legRight3.getChild("temur7")
    val tibia7 = temur7.getChild("tibia7")
    val metatarsus7 = tibia7.getChild("metatarsus7")
    val tarsus7 = metatarsus7.getChild("tarsus7")
    val legRight4 = legs.getChild("legRight4")
    val temur8 = legRight4.getChild("temur8")
    val tibia8 = temur8.getChild("tibia8")
    val metatarsus8 = tibia8.getChild("metatarsus8")
    val tarsus8 = metatarsus8.getChild("tarsus8")
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
                ModelTransform.of(3.0f, -8.0f, -2.0f, 0.0006f, 0.7999f, 0.0865f)
            )

            val temur4 = legLeft1.addChild(
                "temur4",
                ModelPartBuilder.create().uv(156, 105).cuboid(-0.25f, -1.0f, -1.0f, 13.0f, 2.0f, 2.0f, Dilation(0.0f))
                    .uv(156, 110).cuboid(-0.25f, -1.0f, -1.0f, 13.0f, 2.0f, 2.0f, Dilation(0.05f)),
                ModelTransform.of(1.0f, 0.0f, 0.0f, 0.0085f, -0.0059f, -1.1775f)
            )

            val tibia4 = temur4.addChild(
                "tibia4",
                ModelPartBuilder.create().uv(187, 105).cuboid(-0.5f, -1.0f, -1.0f, 11.0f, 2.0f, 2.0f, Dilation(-0.25f))
                    .uv(187, 110).cuboid(-0.5f, -1.0f, -1.0f, 11.0f, 2.0f, 2.0f, Dilation(-0.2f)),
                ModelTransform.of(11.9f, 0.75f, 0.0f, 0.0f, 0.0f, 1.7453f)
            )

            val metatarsus4 = tibia4.addChild(
                "metatarsus4",
                ModelPartBuilder.create().uv(214, 105).cuboid(-0.25f, -1.0f, -1.0f, 11.0f, 2.0f, 2.0f, Dilation(-0.3f))
                    .uv(214, 110).cuboid(-0.25f, -1.0f, -1.0f, 11.0f, 2.0f, 2.0f, Dilation(-0.25f)),
                ModelTransform.of(10.0f, 0.0f, 0.0f, 0.0036f, 0.0037f, 0.3488f)
            )

            val tarsus4 = metatarsus4.addChild(
                "tarsus4",
                ModelPartBuilder.create().uv(241, 107).cuboid(-0.25f, -0.5f, -0.5f, 6.0f, 1.0f, 1.0f, Dilation(0.1f))
                    .uv(241, 112).cuboid(-0.25f, -0.5f, -0.5f, 6.0f, 1.0f, 1.0f, Dilation(0.15f)),
                ModelTransform.of(10.0f, 0.0f, 0.0f, 0.0f, 0.0f, -0.3054f)
            )

            val legLeft2 = legs.addChild(
                "legLeft2",
                ModelPartBuilder.create().uv(147, 115).cuboid(-0.5f, -1.0f, -1.0f, 2.0f, 2.0f, 2.0f, Dilation(0.25f))
                    .uv(147, 120).cuboid(-0.5f, -1.0f, -1.0f, 2.0f, 2.0f, 2.0f, Dilation(0.3f)),
                ModelTransform.of(4.0f, -8.0f, 0.0f, 0.0005f, 0.2763f, 0.0862f)
            )

            val temur2 = legLeft2.addChild(
                "temur2",
                ModelPartBuilder.create().uv(156, 115).cuboid(-0.25f, -1.0f, -1.0f, 13.0f, 2.0f, 2.0f, Dilation(0.0f))
                    .uv(156, 120).cuboid(-0.25f, -1.0f, -1.0f, 13.0f, 2.0f, 2.0f, Dilation(0.05f)),
                ModelTransform.of(1.0f, 0.0f, 0.0f, 0.0085f, -0.0059f, -1.1775f)
            )

            val tibia2 = temur2.addChild(
                "tibia2",
                ModelPartBuilder.create().uv(187, 115).cuboid(-0.5f, -1.0f, -1.0f, 11.0f, 2.0f, 2.0f, Dilation(-0.25f))
                    .uv(187, 120).cuboid(-0.5f, -1.0f, -1.0f, 11.0f, 2.0f, 2.0f, Dilation(-0.2f)),
                ModelTransform.of(11.9f, 0.75f, 0.0f, 0.0f, 0.0f, 1.7453f)
            )

            val metatarsus2 = tibia2.addChild(
                "metatarsus2",
                ModelPartBuilder.create().uv(214, 115).cuboid(-0.25f, -1.0f, -1.0f, 11.0f, 2.0f, 2.0f, Dilation(-0.3f))
                    .uv(214, 120).cuboid(-0.25f, -1.0f, -1.0f, 11.0f, 2.0f, 2.0f, Dilation(-0.25f)),
                ModelTransform.of(10.0f, 0.0f, 0.0f, 0.0036f, 0.0037f, 0.3488f)
            )

            val tarsus2 = metatarsus2.addChild(
                "tarsus2",
                ModelPartBuilder.create().uv(241, 117).cuboid(-0.25f, -0.5f, -0.5f, 6.0f, 1.0f, 1.0f, Dilation(0.1f))
                    .uv(241, 122).cuboid(-0.25f, -0.5f, -0.5f, 6.0f, 1.0f, 1.0f, Dilation(0.15f)),
                ModelTransform.of(10.0f, 0.0f, 0.0f, 0.0f, 0.0f, -0.3054f)
            )

            val legLeft3 = legs.addChild(
                "legLeft3",
                ModelPartBuilder.create().uv(147, 125).cuboid(-0.5f, -1.0f, -1.0f, 2.0f, 2.0f, 2.0f, Dilation(0.25f))
                    .uv(147, 130).cuboid(-0.5f, -1.0f, -1.0f, 2.0f, 2.0f, 2.0f, Dilation(0.3f)),
                ModelTransform.of(4.0f, -8.0f, 3.0f, 0.0004f, -0.0727f, 0.086f)
            )

            val temur3 = legLeft3.addChild(
                "temur3",
                ModelPartBuilder.create().uv(156, 125).cuboid(-0.25f, -1.0f, -1.0f, 13.0f, 2.0f, 2.0f, Dilation(0.0f))
                    .uv(156, 130).cuboid(-0.25f, -1.0f, -1.0f, 13.0f, 2.0f, 2.0f, Dilation(0.05f)),
                ModelTransform.of(1.0f, 0.0f, 0.0f, 0.0085f, -0.0059f, -1.1775f)
            )

            val tibia3 = temur3.addChild(
                "tibia3",
                ModelPartBuilder.create().uv(187, 125).cuboid(-0.5f, -1.0f, -1.0f, 11.0f, 2.0f, 2.0f, Dilation(-0.25f))
                    .uv(187, 130).cuboid(-0.5f, -1.0f, -1.0f, 11.0f, 2.0f, 2.0f, Dilation(-0.2f)),
                ModelTransform.of(11.9f, 0.75f, 0.0f, 0.0f, 0.0f, 1.7453f)
            )

            val metatarsus3 = tibia3.addChild(
                "metatarsus3",
                ModelPartBuilder.create().uv(214, 125).cuboid(-0.25f, -1.0f, -1.0f, 11.0f, 2.0f, 2.0f, Dilation(-0.3f))
                    .uv(214, 130).cuboid(-0.25f, -1.0f, -1.0f, 11.0f, 2.0f, 2.0f, Dilation(-0.25f)),
                ModelTransform.of(10.0f, 0.0f, 0.0f, 0.0036f, 0.0037f, 0.3488f)
            )

            val tarsus3 = metatarsus3.addChild(
                "tarsus3",
                ModelPartBuilder.create().uv(241, 127).cuboid(-0.25f, -0.5f, -0.5f, 6.0f, 1.0f, 1.0f, Dilation(0.1f))
                    .uv(241, 132).cuboid(-0.25f, -0.5f, -0.5f, 6.0f, 1.0f, 1.0f, Dilation(0.15f)),
                ModelTransform.of(10.0f, 0.0f, 0.0f, 0.0f, 0.0f, -0.3054f)
            )

            val legLeft4 = legs.addChild(
                "legLeft4",
                ModelPartBuilder.create().uv(147, 135).cuboid(-0.5f, -1.0f, -1.0f, 2.0f, 2.0f, 2.0f, Dilation(0.25f))
                    .uv(147, 140).cuboid(-0.5f, -1.0f, -1.0f, 2.0f, 2.0f, 2.0f, Dilation(0.3f)),
                ModelTransform.of(4.0f, -8.0f, 6.0f, 0.0005f, -0.4654f, 0.0858f)
            )

            val temur = legLeft4.addChild(
                "temur",
                ModelPartBuilder.create().uv(156, 135).cuboid(-0.25f, -1.0f, -1.0f, 13.0f, 2.0f, 2.0f, Dilation(0.0f))
                    .uv(156, 140).cuboid(-0.25f, -1.0f, -1.0f, 13.0f, 2.0f, 2.0f, Dilation(0.05f)),
                ModelTransform.of(1.0f, 0.0f, 0.0f, 0.0085f, -0.0059f, -1.1775f)
            )

            val tibia = temur.addChild(
                "tibia",
                ModelPartBuilder.create().uv(187, 135).cuboid(-0.5f, -1.0f, -1.0f, 11.0f, 2.0f, 2.0f, Dilation(-0.25f))
                    .uv(187, 140).cuboid(-0.5f, -1.0f, -1.0f, 11.0f, 2.0f, 2.0f, Dilation(-0.2f)),
                ModelTransform.of(11.9f, 0.75f, 0.0f, 0.0f, 0.0f, 1.7453f)
            )

            val metatarsus = tibia.addChild(
                "metatarsus",
                ModelPartBuilder.create().uv(214, 135).cuboid(-0.25f, -1.0f, -1.0f, 11.0f, 2.0f, 2.0f, Dilation(-0.3f))
                    .uv(214, 140).cuboid(-0.25f, -1.0f, -1.0f, 11.0f, 2.0f, 2.0f, Dilation(-0.25f)),
                ModelTransform.of(10.0f, 0.0f, 0.0f, 0.0036f, 0.0037f, 0.3488f)
            )

            val tarsus = metatarsus.addChild(
                "tarsus",
                ModelPartBuilder.create().uv(241, 137).cuboid(-0.25f, -0.5f, -0.5f, 6.0f, 1.0f, 1.0f, Dilation(0.1f))
                    .uv(241, 142).cuboid(-0.25f, -0.5f, -0.5f, 6.0f, 1.0f, 1.0f, Dilation(0.15f)),
                ModelTransform.of(10.0f, 0.0f, 0.0f, 0.0f, 0.0f, -0.3054f)
            )

            val legRight1 = legs.addChild(
                "legRight1",
                ModelPartBuilder.create().uv(101, 105).cuboid(-1.5f, -1.0f, -1.0f, 2.0f, 2.0f, 2.0f, Dilation(0.25f))
                    .uv(101, 110).cuboid(-1.5f, -1.0f, -1.0f, 2.0f, 2.0f, 2.0f, Dilation(0.3f)),
                ModelTransform.of(-3.0f, -8.0f, -2.0f, 0.0006f, -0.7999f, -0.0865f)
            )

            val temur5 = legRight1.addChild(
                "temur5",
                ModelPartBuilder.create().uv(70, 105).cuboid(-12.75f, -1.0f, -1.0f, 13.0f, 2.0f, 2.0f, Dilation(0.0f))
                    .uv(70, 110).cuboid(-12.75f, -1.0f, -1.0f, 13.0f, 2.0f, 2.0f, Dilation(0.05f)),
                ModelTransform.of(-1.0f, 0.0f, 0.0f, 0.0085f, 0.0059f, 1.1775f)
            )

            val tibia5 = temur5.addChild(
                "tibia5",
                ModelPartBuilder.create().uv(43, 105).cuboid(-10.5f, -1.0f, -1.0f, 11.0f, 2.0f, 2.0f, Dilation(-0.25f))
                    .uv(43, 110).cuboid(-10.5f, -1.0f, -1.0f, 11.0f, 2.0f, 2.0f, Dilation(-0.2f)),
                ModelTransform.of(-11.9f, 0.75f, 0.0f, 0.0f, 0.0f, -1.7453f)
            )

            val metatarsus5 = tibia5.addChild(
                "metatarsus5",
                ModelPartBuilder.create().uv(16, 105).cuboid(-10.75f, -1.0f, -1.0f, 11.0f, 2.0f, 2.0f, Dilation(-0.3f))
                    .uv(16, 110).cuboid(-10.75f, -1.0f, -1.0f, 11.0f, 2.0f, 2.0f, Dilation(-0.25f)),
                ModelTransform.of(-10.0f, 0.0f, 0.0f, 0.0036f, -0.0037f, -0.3488f)
            )

            val tarsus5 = metatarsus5.addChild(
                "tarsus5",
                ModelPartBuilder.create().uv(1, 107).cuboid(-5.75f, -0.5f, -0.5f, 6.0f, 1.0f, 1.0f, Dilation(0.1f))
                    .uv(1, 112).cuboid(-5.75f, -0.5f, -0.5f, 6.0f, 1.0f, 1.0f, Dilation(0.15f)),
                ModelTransform.of(-10.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.3054f)
            )

            val legRight2 = legs.addChild(
                "legRight2",
                ModelPartBuilder.create().uv(101, 115).cuboid(-1.5f, -1.0f, -1.0f, 2.0f, 2.0f, 2.0f, Dilation(0.25f))
                    .uv(101, 120).cuboid(-1.5f, -1.0f, -1.0f, 2.0f, 2.0f, 2.0f, Dilation(0.3f)),
                ModelTransform.of(-4.0f, -8.0f, 0.0f, 0.0005f, -0.2763f, -0.0862f)
            )

            val temur6 = legRight2.addChild(
                "temur6",
                ModelPartBuilder.create().uv(70, 115).cuboid(-12.75f, -1.0f, -1.0f, 13.0f, 2.0f, 2.0f, Dilation(0.0f))
                    .uv(70, 120).cuboid(-12.75f, -1.0f, -1.0f, 13.0f, 2.0f, 2.0f, Dilation(0.05f)),
                ModelTransform.of(-1.0f, 0.0f, 0.0f, 0.0085f, 0.0059f, 1.1775f)
            )

            val tibia6 = temur6.addChild(
                "tibia6",
                ModelPartBuilder.create().uv(43, 115).cuboid(-10.5f, -1.0f, -1.0f, 11.0f, 2.0f, 2.0f, Dilation(-0.25f))
                    .uv(43, 120).cuboid(-10.5f, -1.0f, -1.0f, 11.0f, 2.0f, 2.0f, Dilation(-0.2f)),
                ModelTransform.of(-11.9f, 0.75f, 0.0f, 0.0f, 0.0f, -1.7453f)
            )

            val metatarsus6 = tibia6.addChild(
                "metatarsus6",
                ModelPartBuilder.create().uv(16, 115).cuboid(-10.75f, -1.0f, -1.0f, 11.0f, 2.0f, 2.0f, Dilation(-0.3f))
                    .uv(16, 120).cuboid(-10.75f, -1.0f, -1.0f, 11.0f, 2.0f, 2.0f, Dilation(-0.25f)),
                ModelTransform.of(-10.0f, 0.0f, 0.0f, 0.0036f, -0.0037f, -0.3488f)
            )

            val tarsus6 = metatarsus6.addChild(
                "tarsus6",
                ModelPartBuilder.create().uv(1, 117).cuboid(-5.75f, -0.5f, -0.5f, 6.0f, 1.0f, 1.0f, Dilation(0.1f))
                    .uv(1, 122).cuboid(-5.75f, -0.5f, -0.5f, 6.0f, 1.0f, 1.0f, Dilation(0.15f)),
                ModelTransform.of(-10.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.3054f)
            )

            val legRight3 = legs.addChild(
                "legRight3",
                ModelPartBuilder.create().uv(101, 125).cuboid(-1.5f, -1.0f, -1.0f, 2.0f, 2.0f, 2.0f, Dilation(0.25f))
                    .uv(101, 130).cuboid(-1.5f, -1.0f, -1.0f, 2.0f, 2.0f, 2.0f, Dilation(0.3f)),
                ModelTransform.of(-4.0f, -8.0f, 3.0f, 0.0004f, 0.0727f, -0.086f)
            )

            val temur7 = legRight3.addChild(
                "temur7",
                ModelPartBuilder.create().uv(70, 125).cuboid(-12.75f, -1.0f, -1.0f, 13.0f, 2.0f, 2.0f, Dilation(0.0f))
                    .uv(70, 130).cuboid(-12.75f, -1.0f, -1.0f, 13.0f, 2.0f, 2.0f, Dilation(0.05f)),
                ModelTransform.of(-1.0f, 0.0f, 0.0f, 0.0085f, 0.0059f, 1.1775f)
            )

            val tibia7 = temur7.addChild(
                "tibia7",
                ModelPartBuilder.create().uv(43, 125).cuboid(-10.5f, -1.0f, -1.0f, 11.0f, 2.0f, 2.0f, Dilation(-0.25f))
                    .uv(43, 130).cuboid(-10.5f, -1.0f, -1.0f, 11.0f, 2.0f, 2.0f, Dilation(-0.2f)),
                ModelTransform.of(-11.9f, 0.75f, 0.0f, 0.0f, 0.0f, -1.7453f)
            )

            val metatarsus7 = tibia7.addChild(
                "metatarsus7",
                ModelPartBuilder.create().uv(16, 125).cuboid(-10.75f, -1.0f, -1.0f, 11.0f, 2.0f, 2.0f, Dilation(-0.3f))
                    .uv(16, 130).cuboid(-10.75f, -1.0f, -1.0f, 11.0f, 2.0f, 2.0f, Dilation(-0.25f)),
                ModelTransform.of(-10.0f, 0.0f, 0.0f, 0.0036f, -0.0037f, -0.3488f)
            )

            val tarsus7 = metatarsus7.addChild(
                "tarsus7",
                ModelPartBuilder.create().uv(1, 127).cuboid(-5.75f, -0.5f, -0.5f, 6.0f, 1.0f, 1.0f, Dilation(0.1f))
                    .uv(1, 132).cuboid(-5.75f, -0.5f, -0.5f, 6.0f, 1.0f, 1.0f, Dilation(0.15f)),
                ModelTransform.of(-10.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.3054f)
            )

            val legRight4 = legs.addChild(
                "legRight4",
                ModelPartBuilder.create().uv(101, 135).cuboid(-1.5f, -1.0f, -1.0f, 2.0f, 2.0f, 2.0f, Dilation(0.25f))
                    .uv(101, 140).cuboid(-1.5f, -1.0f, -1.0f, 2.0f, 2.0f, 2.0f, Dilation(0.3f)),
                ModelTransform.of(-4.0f, -8.0f, 6.0f, 0.0005f, 0.4654f, -0.0858f)
            )

            val temur8 = legRight4.addChild(
                "temur8",
                ModelPartBuilder.create().uv(70, 135).cuboid(-12.75f, -1.0f, -1.0f, 13.0f, 2.0f, 2.0f, Dilation(0.0f))
                    .uv(70, 140).cuboid(-12.75f, -1.0f, -1.0f, 13.0f, 2.0f, 2.0f, Dilation(0.05f)),
                ModelTransform.of(-1.0f, 0.0f, 0.0f, 0.0085f, 0.0059f, 1.1775f)
            )

            val tibia8 = temur8.addChild(
                "tibia8",
                ModelPartBuilder.create().uv(43, 135).cuboid(-10.5f, -1.0f, -1.0f, 11.0f, 2.0f, 2.0f, Dilation(-0.25f))
                    .uv(43, 140).cuboid(-10.5f, -1.0f, -1.0f, 11.0f, 2.0f, 2.0f, Dilation(-0.2f)),
                ModelTransform.of(-11.9f, 0.75f, 0.0f, 0.0f, 0.0f, -1.7453f)
            )

            val metatarsus8 = tibia8.addChild(
                "metatarsus8",
                ModelPartBuilder.create().uv(16, 135).cuboid(-10.75f, -1.0f, -1.0f, 11.0f, 2.0f, 2.0f, Dilation(-0.3f))
                    .uv(16, 140).cuboid(-10.75f, -1.0f, -1.0f, 11.0f, 2.0f, 2.0f, Dilation(-0.25f)),
                ModelTransform.of(-10.0f, 0.0f, 0.0f, 0.0036f, -0.0037f, -0.3488f)
            )

            val tarsus8 = metatarsus8.addChild(
                "tarsus8",
                ModelPartBuilder.create().uv(1, 137).cuboid(-5.75f, -0.5f, -0.5f, 6.0f, 1.0f, 1.0f, Dilation(0.1f))
                    .uv(1, 142).cuboid(-5.75f, -0.5f, -0.5f, 6.0f, 1.0f, 1.0f, Dilation(0.15f)),
                ModelTransform.of(-10.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.3054f)
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
                ModelPartBuilder.create().uv(142, 51).cuboid(4.0f, -23.75f, -1.5f, 3.0f, 6.0f, 3.0f, Dilation(0.0f))
                    .uv(155, 51).cuboid(4.0f, -23.75f, -1.5f, 3.0f, 6.0f, 3.0f, Dilation(0.1f)),
                ModelTransform.pivot(-0.25f, 17.0f, 0.0f)
            )

            val armLeftLower = armLeft.addChild(
                "armLeftLower",
                ModelPartBuilder.create().uv(142, 61).cuboid(4.0f, -18.75f, -1.5f, 3.0f, 7.0f, 3.0f, Dilation(-0.25f))
                    .uv(155, 61).cuboid(4.0f, -18.75f, -1.5f, 3.0f, 7.0f, 3.0f, Dilation(-0.15f)),
                ModelTransform.pivot(0.0f, 0.0f, 0.0f)
            )

            val armRight = chest.addChild(
                "armRight",
                ModelPartBuilder.create().uv(102, 50).cuboid(-7.0f, -23.75f, -1.5f, 3.0f, 6.0f, 3.0f, Dilation(0.0f))
                    .uv(89, 50).cuboid(-7.0f, -23.75f, -1.5f, 3.0f, 6.0f, 3.0f, Dilation(0.1f)),
                ModelTransform.pivot(0.25f, 17.0f, 0.0f)
            )

            val armRightLower = armRight.addChild(
                "armRightLower",
                ModelPartBuilder.create().uv(102, 60).cuboid(-7.0f, -18.75f, -1.5f, 3.0f, 7.0f, 3.0f, Dilation(-0.25f))
                    .uv(89, 60).cuboid(-7.0f, -18.75f, -1.5f, 3.0f, 7.0f, 3.0f, Dilation(-0.15f)),
                ModelTransform.pivot(0.0f, 0.0f, 0.0f)
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
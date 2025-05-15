package de.fuballer.mcendgame.client.component.item.custom.armor.fae

import de.fuballer.mcendgame.client.component.item.custom.ModelPartDataExtension.createEmptyChild
import de.fuballer.mcendgame.main.util.minecraft.IdentifierUtil
import net.minecraft.client.model.*
import net.minecraft.client.render.entity.model.BipedEntityModel
import net.minecraft.client.render.entity.model.EntityModelLayer
import net.minecraft.client.render.entity.model.EntityModelPartNames
import net.minecraft.client.render.entity.state.BipedEntityRenderState
import kotlin.math.PI
import kotlin.math.sin

class FaeLeggingsModel<S : BipedEntityRenderState>(
    root: ModelPart,
) : BipedEntityModel<S>(root) {

    private val skirtFrontCenterStrand = mutableListOf<ModelPart>()
    private val skirtFrontLeftStrands = List(3) { mutableListOf<ModelPart>() }
    private val skirtFrontRightStrands = List(3) { mutableListOf<ModelPart>() }

    private val skirtLeftStrands = List(4) { mutableListOf<ModelPart>() }
    private val skirtRightStrands = List(4) { mutableListOf<ModelPart>() }

    private val skirtBackCenterStrand = mutableListOf<ModelPart>()
    private val skirtBackLeftStrands = List(3) { mutableListOf<ModelPart>() }
    private val skirtBackRightStrands = List(3) { mutableListOf<ModelPart>() }

    private val skirtBackStrands: List<List<ModelPart>>

    private val combinedParts: List<ModelPart>

    init {
        initSkirtStrands()

        skirtBackStrands = listOf(
            skirtBackCenterStrand,
            *skirtBackLeftStrands.toTypedArray(),
            *skirtBackRightStrands.toTypedArray(),
        )

        combinedParts = listOf(
            skirtFrontCenterStrand,
            *skirtFrontLeftStrands.toTypedArray(),
            *skirtFrontRightStrands.toTypedArray(),
            *skirtLeftStrands.toTypedArray(),
            *skirtRightStrands.toTypedArray(),
            skirtBackCenterStrand,
            *skirtBackLeftStrands.toTypedArray(),
            *skirtBackRightStrands.toTypedArray()
        ).flatten()
    }

    private fun initSkirtStrands(
    ) {
        val leggingsWaist = body.getChild("leggings_waist")
        val skirt = leggingsWaist.getChild("skirt")

        initSkirtStrand(skirt, skirtFrontCenterStrand, "FC", 2)
        initSkirtStrand(skirt, skirtBackCenterStrand, "BC", 7)
        for (i in 0 until 4) {
            initSkirtStrand(skirt, skirtLeftStrands[i], "L${i}_", if (i == 0) 6 else 7)
            initSkirtStrand(skirt, skirtRightStrands[i], "R${i}_", if (i == 0) 6 else 7)

            if (i == 3) continue
            initSkirtStrand(skirt, skirtFrontLeftStrands[i], "F${i + 1}L", 2 + i)
            initSkirtStrand(skirt, skirtFrontRightStrands[i], "F${i + 1}R", 2 + i)

            initSkirtStrand(skirt, skirtBackLeftStrands[i], "B${i + 1}L", 7)
            initSkirtStrand(skirt, skirtBackRightStrands[i], "B${i + 1}R", 7)
        }
    }

    private fun initSkirtStrand(
        skirt: ModelPart,
        strang: MutableList<ModelPart>,
        prefix: String,
        parts: Int,
    ) {
        strang.add(skirt.getChild("skirt${prefix}0"))
        for (i in 1 until parts) {
            strang.add(strang[i - 1].getChild("skirt$prefix$i"))
        }
    }

    companion object {
        val MODEL_LAYER = EntityModelLayer(IdentifierUtil.default("fae_leggings"), "main")

        fun getTexturedModelData(): TexturedModelData {
            val modelData = ModelData()
            val modelPartData = modelData.root

            val head = modelPartData.createEmptyChild(EntityModelPartNames.HEAD)
            val hat = head.createEmptyChild(EntityModelPartNames.HAT)
            val left_arm = modelPartData.createEmptyChild(EntityModelPartNames.LEFT_ARM)
            val right_arm = modelPartData.createEmptyChild(EntityModelPartNames.RIGHT_ARM)
            val left_leg = modelPartData.createEmptyChild(EntityModelPartNames.LEFT_LEG)
            val right_leg = modelPartData.createEmptyChild(EntityModelPartNames.RIGHT_LEG)

            val body = modelPartData.addChild(EntityModelPartNames.BODY, ModelPartBuilder.create(), ModelTransform.origin(0.0f, 0.0f, 0.0f))

            val leggings_waist = body.addChild("leggings_waist", ModelPartBuilder.create(), ModelTransform.origin(0.0f, 0.0f, 0.0f))

            val left_belt = leggings_waist.addChild(
                "left_belt",
                ModelPartBuilder.create().uv(34, 46).cuboid(-5.6f, -0.4f, -2.95f, 6.0f, 3.0f, 6.0f, Dilation(-0.4f)),
                ModelTransform.of(4.6f, 8.65f, 0.0f, 0.0f, 0.0f, -0.0873f)
            )

            val right_belt = leggings_waist.addChild(
                "right_belt",
                ModelPartBuilder.create().uv(8, 46).cuboid(-0.4f, 0.1f, -3.05f, 6.0f, 3.0f, 6.0f, Dilation(-0.4f)),
                ModelTransform.of(-4.6f, 8.15f, 0.0f, 0.0f, 0.0f, 0.0873f)
            )

            val skirt = leggings_waist.addChild("skirt", ModelPartBuilder.create(), ModelTransform.origin(0.0f, 0.0f, 0.0f))

            val skirtFC0 =
                skirt.addChild("skirtFC0", ModelPartBuilder.create().uv(21, 79).cuboid(-0.5f, 0.0f, 0.0f, 1.0f, 2.0f, 0.0f, Dilation(0.0f)), ModelTransform.origin(0.0f, 10.5f, -2.45f))

            val skirtFC1 =
                skirtFC0.addChild("skirtFC1", ModelPartBuilder.create().uv(21, 81).cuboid(-0.5f, 0.0f, 0.0f, 1.0f, 2.0f, 0.0f, Dilation(0.0f)), ModelTransform.origin(0.0f, 2.0f, 0.0f))

            val skirtF1L0 =
                skirt.addChild("skirtF1L0", ModelPartBuilder.create().uv(23, 79).cuboid(-0.5f, 0.0f, 0.0f, 1.0f, 2.0f, 0.0f, Dilation(0.0f)), ModelTransform.origin(1.25f, 10.75f, -2.45f))

            val skirtF1L1 =
                skirtF1L0.addChild("skirtF1L1", ModelPartBuilder.create().uv(23, 81).cuboid(-0.5f, 0.0f, 0.0f, 1.0f, 2.0f, 0.0f, Dilation(0.0f)), ModelTransform.origin(0.0f, 2.0f, 0.0f))

            val skirtF1R0 =
                skirt.addChild("skirtF1R0", ModelPartBuilder.create().uv(19, 79).cuboid(-0.5f, 0.0f, 0.0f, 1.0f, 2.0f, 0.0f, Dilation(0.0f)), ModelTransform.origin(-1.25f, 10.75f, -2.45f))

            val skirtF1R1 =
                skirtF1R0.addChild("skirtF1R1", ModelPartBuilder.create().uv(19, 81).cuboid(-0.5f, 0.0f, 0.0f, 1.0f, 2.0f, 0.0f, Dilation(0.0f)), ModelTransform.origin(0.0f, 2.0f, 0.0f))

            val skirtF2L0 =
                skirt.addChild("skirtF2L0", ModelPartBuilder.create().uv(25, 79).cuboid(-0.5f, 0.0f, 0.0f, 1.0f, 2.0f, 0.0f, Dilation(0.0f)), ModelTransform.origin(2.5f, 10.5f, -2.45f))

            val skirtF2L1 =
                skirtF2L0.addChild("skirtF2L1", ModelPartBuilder.create().uv(25, 81).cuboid(-0.5f, 0.0f, 0.0f, 1.0f, 2.0f, 0.0f, Dilation(0.0f)), ModelTransform.origin(0.0f, 2.0f, 0.0f))

            val skirtF2L2 =
                skirtF2L1.addChild("skirtF2L2", ModelPartBuilder.create().uv(25, 83).cuboid(-0.5f, 0.0f, 0.0f, 1.0f, 2.0f, 0.0f, Dilation(0.0f)), ModelTransform.origin(0.0f, 2.0f, 0.0f))

            val skirtF2R0 =
                skirt.addChild("skirtF2R0", ModelPartBuilder.create().uv(17, 79).cuboid(-0.5f, 0.0f, 0.0f, 1.0f, 2.0f, 0.0f, Dilation(0.0f)), ModelTransform.origin(-2.5f, 10.5f, -2.45f))

            val skirtF2R1 =
                skirtF2R0.addChild("skirtF2R1", ModelPartBuilder.create().uv(17, 81).cuboid(-0.5f, 0.0f, 0.0f, 1.0f, 2.0f, 0.0f, Dilation(0.0f)), ModelTransform.origin(0.0f, 2.0f, 0.0f))

            val skirtF2R2 =
                skirtF2R1.addChild("skirtF2R2", ModelPartBuilder.create().uv(17, 83).cuboid(-0.5f, 0.0f, 0.0f, 1.0f, 2.0f, 0.0f, Dilation(0.0f)), ModelTransform.origin(0.0f, 2.0f, 0.0f))

            val skirtF3L0 =
                skirt.addChild("skirtF3L0", ModelPartBuilder.create().uv(27, 79).cuboid(-0.5f, 0.0f, 0.0f, 1.0f, 2.0f, 0.0f, Dilation(0.0f)), ModelTransform.origin(3.75f, 10.5f, -2.45f))

            val skirtF3L1 =
                skirtF3L0.addChild("skirtF3L1", ModelPartBuilder.create().uv(27, 81).cuboid(-0.5f, 0.0f, 0.0f, 1.0f, 2.0f, 0.0f, Dilation(0.0f)), ModelTransform.origin(0.0f, 2.0f, 0.0f))

            val skirtF3L2 =
                skirtF3L1.addChild("skirtF3L2", ModelPartBuilder.create().uv(27, 83).cuboid(-0.5f, 0.0f, 0.0f, 1.0f, 2.0f, 0.0f, Dilation(0.0f)), ModelTransform.origin(0.0f, 2.0f, 0.0f))

            val skirtF3L3 =
                skirtF3L2.addChild("skirtF3L3", ModelPartBuilder.create().uv(27, 85).cuboid(-0.5f, 0.0f, 0.0f, 1.0f, 2.0f, 0.0f, Dilation(0.0f)), ModelTransform.origin(0.0f, 2.0f, 0.0f))

            val skirtF3R0 =
                skirt.addChild("skirtF3R0", ModelPartBuilder.create().uv(15, 79).cuboid(-0.5f, 0.0f, 0.0f, 1.0f, 2.0f, 0.0f, Dilation(0.0f)), ModelTransform.origin(-3.75f, 10.5f, -2.45f))

            val skirtF3R1 =
                skirtF3R0.addChild("skirtF3R1", ModelPartBuilder.create().uv(15, 81).cuboid(-0.5f, 0.0f, 0.0f, 1.0f, 2.0f, 0.0f, Dilation(0.0f)), ModelTransform.origin(0.0f, 2.0f, 0.0f))

            val skirtF3R2 =
                skirtF3R1.addChild("skirtF3R2", ModelPartBuilder.create().uv(15, 83).cuboid(-0.5f, 0.0f, 0.0f, 1.0f, 2.0f, 0.0f, Dilation(0.0f)), ModelTransform.origin(0.0f, 2.0f, 0.0f))

            val skirtF3R3 =
                skirtF3R2.addChild("skirtF3R3", ModelPartBuilder.create().uv(15, 85).cuboid(-0.5f, 0.0f, 0.0f, 1.0f, 2.0f, 0.0f, Dilation(0.0f)), ModelTransform.origin(0.0f, 2.0f, 0.0f))

            val skirtBC0 =
                skirt.addChild("skirtBC0", ModelPartBuilder.create().uv(21, 64).cuboid(-0.5f, 0.0f, 0.0f, 1.0f, 2.0f, 0.0f, Dilation(0.0f)), ModelTransform.origin(0.0f, 10.75f, 2.45f))

            val skirtBC1 =
                skirtBC0.addChild("skirtBC1", ModelPartBuilder.create().uv(21, 66).cuboid(-0.5f, 0.0f, 0.0f, 1.0f, 2.0f, 0.0f, Dilation(0.0f)), ModelTransform.origin(0.0f, 2.0f, 0.0f))

            val skirtBC2 =
                skirtBC1.addChild("skirtBC2", ModelPartBuilder.create().uv(21, 68).cuboid(-0.5f, 0.0f, 0.0f, 1.0f, 2.0f, 0.0f, Dilation(0.0f)), ModelTransform.origin(0.0f, 2.0f, 0.0f))

            val skirtBC3 =
                skirtBC2.addChild("skirtBC3", ModelPartBuilder.create().uv(21, 70).cuboid(-0.5f, 0.0f, 0.0f, 1.0f, 2.0f, 0.0f, Dilation(0.0f)), ModelTransform.origin(0.0f, 2.0f, 0.0f))

            val skirtBC4 =
                skirtBC3.addChild("skirtBC4", ModelPartBuilder.create().uv(21, 72).cuboid(-0.5f, 0.0f, 0.0f, 1.0f, 2.0f, 0.0f, Dilation(0.0f)), ModelTransform.origin(0.0f, 2.0f, 0.0f))

            val skirtBC5 =
                skirtBC4.addChild("skirtBC5", ModelPartBuilder.create().uv(21, 74).cuboid(-0.5f, 0.0f, 0.0f, 1.0f, 2.0f, 0.0f, Dilation(0.0f)), ModelTransform.origin(0.0f, 2.0f, 0.0f))

            val skirtBC6 =
                skirtBC5.addChild("skirtBC6", ModelPartBuilder.create().uv(21, 76).cuboid(-0.5f, 0.0f, 0.0f, 1.0f, 2.0f, 0.0f, Dilation(0.0f)), ModelTransform.origin(0.0f, 2.0f, 0.0f))

            val skirtB1L0 =
                skirt.addChild("skirtB1L0", ModelPartBuilder.create().uv(23, 64).cuboid(-0.5f, 0.0f, 0.0f, 1.0f, 2.0f, 0.0f, Dilation(0.0f)), ModelTransform.origin(1.25f, 10.75f, 2.45f))

            val skirtB1L1 =
                skirtB1L0.addChild("skirtB1L1", ModelPartBuilder.create().uv(23, 66).cuboid(-0.5f, 0.0f, 0.0f, 1.0f, 2.0f, 0.0f, Dilation(0.0f)), ModelTransform.origin(0.0f, 2.0f, 0.0f))

            val skirtB1L2 =
                skirtB1L1.addChild("skirtB1L2", ModelPartBuilder.create().uv(23, 68).cuboid(-0.5f, 0.0f, 0.0f, 1.0f, 2.0f, 0.0f, Dilation(0.0f)), ModelTransform.origin(0.0f, 2.0f, 0.0f))

            val skirtB1L3 =
                skirtB1L2.addChild("skirtB1L3", ModelPartBuilder.create().uv(23, 70).cuboid(-0.5f, 0.0f, 0.0f, 1.0f, 2.0f, 0.0f, Dilation(0.0f)), ModelTransform.origin(0.0f, 2.0f, 0.0f))

            val skirtB1L4 =
                skirtB1L3.addChild("skirtB1L4", ModelPartBuilder.create().uv(23, 72).cuboid(-0.5f, 0.0f, 0.0f, 1.0f, 2.0f, 0.0f, Dilation(0.0f)), ModelTransform.origin(0.0f, 2.0f, 0.0f))

            val skirtB1L5 =
                skirtB1L4.addChild("skirtB1L5", ModelPartBuilder.create().uv(23, 74).cuboid(-0.5f, 0.0f, 0.0f, 1.0f, 2.0f, 0.0f, Dilation(0.0f)), ModelTransform.origin(0.0f, 2.0f, 0.0f))

            val skirtB1L6 =
                skirtB1L5.addChild("skirtB1L6", ModelPartBuilder.create().uv(23, 76).cuboid(-0.5f, 0.0f, 0.0f, 1.0f, 2.0f, 0.0f, Dilation(0.0f)), ModelTransform.origin(0.0f, 2.0f, 0.0f))

            val skirtB1R0 =
                skirt.addChild("skirtB1R0", ModelPartBuilder.create().uv(19, 64).cuboid(-0.5f, 0.0f, 0.0f, 1.0f, 2.0f, 0.0f, Dilation(0.0f)), ModelTransform.origin(-1.25f, 10.75f, 2.45f))

            val skirtB1R1 =
                skirtB1R0.addChild("skirtB1R1", ModelPartBuilder.create().uv(19, 66).cuboid(-0.5f, 0.0f, 0.0f, 1.0f, 2.0f, 0.0f, Dilation(0.0f)), ModelTransform.origin(0.0f, 2.0f, 0.0f))

            val skirtB1R2 =
                skirtB1R1.addChild("skirtB1R2", ModelPartBuilder.create().uv(19, 68).cuboid(-0.5f, 0.0f, 0.0f, 1.0f, 2.0f, 0.0f, Dilation(0.0f)), ModelTransform.origin(0.0f, 2.0f, 0.0f))

            val skirtB1R3 =
                skirtB1R2.addChild("skirtB1R3", ModelPartBuilder.create().uv(19, 70).cuboid(-0.5f, 0.0f, 0.0f, 1.0f, 2.0f, 0.0f, Dilation(0.0f)), ModelTransform.origin(0.0f, 2.0f, 0.0f))

            val skirtB1R4 =
                skirtB1R3.addChild("skirtB1R4", ModelPartBuilder.create().uv(19, 72).cuboid(-0.5f, 0.0f, 0.0f, 1.0f, 2.0f, 0.0f, Dilation(0.0f)), ModelTransform.origin(0.0f, 2.0f, 0.0f))

            val skirtB1R5 =
                skirtB1R4.addChild("skirtB1R5", ModelPartBuilder.create().uv(19, 74).cuboid(-0.5f, 0.0f, 0.0f, 1.0f, 2.0f, 0.0f, Dilation(0.0f)), ModelTransform.origin(0.0f, 2.0f, 0.0f))

            val skirtB1R6 =
                skirtB1R5.addChild("skirtB1R6", ModelPartBuilder.create().uv(19, 76).cuboid(-0.5f, 0.0f, 0.0f, 1.0f, 2.0f, 0.0f, Dilation(0.0f)), ModelTransform.origin(0.0f, 2.0f, 0.0f))

            val skirtB2L0 =
                skirt.addChild("skirtB2L0", ModelPartBuilder.create().uv(25, 64).cuboid(-0.5f, 0.0f, 0.0f, 1.0f, 2.0f, 0.0f, Dilation(0.0f)), ModelTransform.origin(2.5f, 10.5f, 2.45f))

            val skirtB2L1 =
                skirtB2L0.addChild("skirtB2L1", ModelPartBuilder.create().uv(25, 66).cuboid(-0.5f, 0.0f, 0.0f, 1.0f, 2.0f, 0.0f, Dilation(0.0f)), ModelTransform.origin(0.0f, 2.0f, 0.0f))

            val skirtB2L2 =
                skirtB2L1.addChild("skirtB2L2", ModelPartBuilder.create().uv(25, 68).cuboid(-0.5f, 0.0f, 0.0f, 1.0f, 2.0f, 0.0f, Dilation(0.0f)), ModelTransform.origin(0.0f, 2.0f, 0.0f))

            val skirtB2L3 =
                skirtB2L2.addChild("skirtB2L3", ModelPartBuilder.create().uv(25, 70).cuboid(-0.5f, 0.0f, 0.0f, 1.0f, 2.0f, 0.0f, Dilation(0.0f)), ModelTransform.origin(0.0f, 2.0f, 0.0f))

            val skirtB2L4 =
                skirtB2L3.addChild("skirtB2L4", ModelPartBuilder.create().uv(25, 72).cuboid(-0.5f, 0.0f, 0.0f, 1.0f, 2.0f, 0.0f, Dilation(0.0f)), ModelTransform.origin(0.0f, 2.0f, 0.0f))

            val skirtB2L5 =
                skirtB2L4.addChild("skirtB2L5", ModelPartBuilder.create().uv(25, 74).cuboid(-0.5f, 0.0f, 0.0f, 1.0f, 2.0f, 0.0f, Dilation(0.0f)), ModelTransform.origin(0.0f, 2.0f, 0.0f))

            val skirtB2L6 =
                skirtB2L5.addChild("skirtB2L6", ModelPartBuilder.create().uv(25, 76).cuboid(-0.5f, 0.0f, 0.0f, 1.0f, 2.0f, 0.0f, Dilation(0.0f)), ModelTransform.origin(0.0f, 2.0f, 0.0f))

            val skirtB2R0 =
                skirt.addChild("skirtB2R0", ModelPartBuilder.create().uv(17, 64).cuboid(-0.5f, 0.0f, 0.0f, 1.0f, 2.0f, 0.0f, Dilation(0.0f)), ModelTransform.origin(-2.5f, 10.5f, 2.45f))

            val skirtB2R1 =
                skirtB2R0.addChild("skirtB2R1", ModelPartBuilder.create().uv(17, 66).cuboid(-0.5f, 0.0f, 0.0f, 1.0f, 2.0f, 0.0f, Dilation(0.0f)), ModelTransform.origin(0.0f, 2.0f, 0.0f))

            val skirtB2R2 =
                skirtB2R1.addChild("skirtB2R2", ModelPartBuilder.create().uv(17, 68).cuboid(-0.5f, 0.0f, 0.0f, 1.0f, 2.0f, 0.0f, Dilation(0.0f)), ModelTransform.origin(0.0f, 2.0f, 0.0f))

            val skirtB2R3 =
                skirtB2R2.addChild("skirtB2R3", ModelPartBuilder.create().uv(17, 70).cuboid(-0.5f, 0.0f, 0.0f, 1.0f, 2.0f, 0.0f, Dilation(0.0f)), ModelTransform.origin(0.0f, 2.0f, 0.0f))

            val skirtB2R4 =
                skirtB2R3.addChild("skirtB2R4", ModelPartBuilder.create().uv(17, 72).cuboid(-0.5f, 0.0f, 0.0f, 1.0f, 2.0f, 0.0f, Dilation(0.0f)), ModelTransform.origin(0.0f, 2.0f, 0.0f))

            val skirtB2R5 =
                skirtB2R4.addChild("skirtB2R5", ModelPartBuilder.create().uv(17, 74).cuboid(-0.5f, 0.0f, 0.0f, 1.0f, 2.0f, 0.0f, Dilation(0.0f)), ModelTransform.origin(0.0f, 2.0f, 0.0f))

            val skirtB2R6 =
                skirtB2R5.addChild("skirtB2R6", ModelPartBuilder.create().uv(17, 76).cuboid(-0.5f, 0.0f, 0.0f, 1.0f, 2.0f, 0.0f, Dilation(0.0f)), ModelTransform.origin(0.0f, 2.0f, 0.0f))

            val skirtB3L0 =
                skirt.addChild("skirtB3L0", ModelPartBuilder.create().uv(27, 64).cuboid(-0.5f, 0.0f, 0.0f, 1.0f, 2.0f, 0.0f, Dilation(0.0f)), ModelTransform.origin(3.75f, 10.5f, 2.45f))

            val skirtB3L1 =
                skirtB3L0.addChild("skirtB3L1", ModelPartBuilder.create().uv(27, 66).cuboid(-0.5f, 0.0f, 0.0f, 1.0f, 2.0f, 0.0f, Dilation(0.0f)), ModelTransform.origin(0.0f, 2.0f, 0.0f))

            val skirtB3L2 =
                skirtB3L1.addChild("skirtB3L2", ModelPartBuilder.create().uv(27, 68).cuboid(-0.5f, 0.0f, 0.0f, 1.0f, 2.0f, 0.0f, Dilation(0.0f)), ModelTransform.origin(0.0f, 2.0f, 0.0f))

            val skirtB3L3 =
                skirtB3L2.addChild("skirtB3L3", ModelPartBuilder.create().uv(27, 70).cuboid(-0.5f, 0.0f, 0.0f, 1.0f, 2.0f, 0.0f, Dilation(0.0f)), ModelTransform.origin(0.0f, 2.0f, 0.0f))

            val skirtB3L4 =
                skirtB3L3.addChild("skirtB3L4", ModelPartBuilder.create().uv(27, 72).cuboid(-0.5f, 0.0f, 0.0f, 1.0f, 2.0f, 0.0f, Dilation(0.0f)), ModelTransform.origin(0.0f, 2.0f, 0.0f))

            val skirtB3L5 =
                skirtB3L4.addChild("skirtB3L5", ModelPartBuilder.create().uv(27, 74).cuboid(-0.5f, 0.0f, 0.0f, 1.0f, 2.0f, 0.0f, Dilation(0.0f)), ModelTransform.origin(0.0f, 2.0f, 0.0f))

            val skirtB3L6 =
                skirtB3L5.addChild("skirtB3L6", ModelPartBuilder.create().uv(27, 76).cuboid(-0.5f, 0.0f, 0.0f, 1.0f, 2.0f, 0.0f, Dilation(0.0f)), ModelTransform.origin(0.0f, 2.0f, 0.0f))

            val skirtB3R0 =
                skirt.addChild("skirtB3R0", ModelPartBuilder.create().uv(15, 64).cuboid(-0.5f, 0.0f, 0.0f, 1.0f, 2.0f, 0.0f, Dilation(0.0f)), ModelTransform.origin(-3.75f, 10.5f, 2.45f))

            val skirtB3R1 =
                skirtB3R0.addChild("skirtB3R1", ModelPartBuilder.create().uv(15, 66).cuboid(-0.5f, 0.0f, 0.0f, 1.0f, 2.0f, 0.0f, Dilation(0.0f)), ModelTransform.origin(0.0f, 2.0f, 0.0f))

            val skirtB3R2 =
                skirtB3R1.addChild("skirtB3R2", ModelPartBuilder.create().uv(15, 68).cuboid(-0.5f, 0.0f, 0.0f, 1.0f, 2.0f, 0.0f, Dilation(0.0f)), ModelTransform.origin(0.0f, 2.0f, 0.0f))

            val skirtB3R3 =
                skirtB3R2.addChild("skirtB3R3", ModelPartBuilder.create().uv(15, 70).cuboid(-0.5f, 0.0f, 0.0f, 1.0f, 2.0f, 0.0f, Dilation(0.0f)), ModelTransform.origin(0.0f, 2.0f, 0.0f))

            val skirtB3R4 =
                skirtB3R3.addChild("skirtB3R4", ModelPartBuilder.create().uv(15, 72).cuboid(-0.5f, 0.0f, 0.0f, 1.0f, 2.0f, 0.0f, Dilation(0.0f)), ModelTransform.origin(0.0f, 2.0f, 0.0f))

            val skirtB3R5 =
                skirtB3R4.addChild("skirtB3R5", ModelPartBuilder.create().uv(15, 74).cuboid(-0.5f, 0.0f, 0.0f, 1.0f, 2.0f, 0.0f, Dilation(0.0f)), ModelTransform.origin(0.0f, 2.0f, 0.0f))

            val skirtB3R6 =
                skirtB3R5.addChild("skirtB3R6", ModelPartBuilder.create().uv(15, 76).cuboid(-0.5f, 0.0f, 0.0f, 1.0f, 2.0f, 0.0f, Dilation(0.0f)), ModelTransform.origin(0.0f, 2.0f, 0.0f))

            val skirtL0_0 = skirt.addChild(
                "skirtL0_0",
                ModelPartBuilder.create().uv(35, 64).cuboid(-0.5f, 0.0f, 0.0f, 1.0f, 2.0f, 0.0f, Dilation(0.0f)),
                ModelTransform.of(4.5f, 10.5f, -1.8f, 0.0f, 1.5708f, 0.0f)
            )

            val skirtL0_1 =
                skirtL0_0.addChild("skirtL0_1", ModelPartBuilder.create().uv(35, 66).cuboid(-0.5f, 0.0f, 0.0f, 1.0f, 2.0f, 0.0f, Dilation(0.0f)), ModelTransform.origin(0.0f, 2.0f, 0.0f))

            val skirtL0_2 =
                skirtL0_1.addChild("skirtL0_2", ModelPartBuilder.create().uv(35, 68).cuboid(-0.5f, 0.0f, 0.0f, 1.0f, 2.0f, 0.0f, Dilation(0.0f)), ModelTransform.origin(0.0f, 2.0f, 0.0f))

            val skirtL0_3 =
                skirtL0_2.addChild("skirtL0_3", ModelPartBuilder.create().uv(35, 70).cuboid(-0.5f, 0.0f, 0.0f, 1.0f, 2.0f, 0.0f, Dilation(0.0f)), ModelTransform.origin(0.0f, 2.0f, 0.0f))

            val skirtL0_4 =
                skirtL0_3.addChild("skirtL0_4", ModelPartBuilder.create().uv(35, 72).cuboid(-0.5f, 0.0f, 0.0f, 1.0f, 2.0f, 0.0f, Dilation(0.0f)), ModelTransform.origin(0.0f, 2.0f, 0.0f))

            val skirtL0_5 =
                skirtL0_4.addChild("skirtL0_5", ModelPartBuilder.create().uv(35, 74).cuboid(-0.5f, 0.0f, 0.0f, 1.0f, 2.0f, 0.0f, Dilation(0.0f)), ModelTransform.origin(0.0f, 2.0f, 0.0f))

            val skirtL1_0 = skirt.addChild(
                "skirtL1_0",
                ModelPartBuilder.create().uv(33, 64).cuboid(-0.5f, 0.0f, 0.0f, 1.0f, 2.0f, 0.0f, Dilation(0.0f)),
                ModelTransform.of(4.5f, 10.5f, -0.55f, 0.0f, 1.5708f, 0.0f)
            )

            val skirtL1_1 =
                skirtL1_0.addChild("skirtL1_1", ModelPartBuilder.create().uv(33, 66).cuboid(-0.5f, 0.0f, 0.0f, 1.0f, 2.0f, 0.0f, Dilation(0.0f)), ModelTransform.origin(0.0f, 2.0f, 0.0f))

            val skirtL1_2 =
                skirtL1_1.addChild("skirtL1_2", ModelPartBuilder.create().uv(33, 68).cuboid(-0.5f, 0.0f, 0.0f, 1.0f, 2.0f, 0.0f, Dilation(0.0f)), ModelTransform.origin(0.0f, 2.0f, 0.0f))

            val skirtL1_3 =
                skirtL1_2.addChild("skirtL1_3", ModelPartBuilder.create().uv(33, 70).cuboid(-0.5f, 0.0f, 0.0f, 1.0f, 2.0f, 0.0f, Dilation(0.0f)), ModelTransform.origin(0.0f, 2.0f, 0.0f))

            val skirtL1_4 =
                skirtL1_3.addChild("skirtL1_4", ModelPartBuilder.create().uv(33, 72).cuboid(-0.5f, 0.0f, 0.0f, 1.0f, 2.0f, 0.0f, Dilation(0.0f)), ModelTransform.origin(0.0f, 2.0f, 0.0f))

            val skirtL1_5 =
                skirtL1_4.addChild("skirtL1_5", ModelPartBuilder.create().uv(33, 74).cuboid(-0.5f, 0.0f, 0.0f, 1.0f, 2.0f, 0.0f, Dilation(0.0f)), ModelTransform.origin(0.0f, 2.0f, 0.0f))

            val skirtL1_6 =
                skirtL1_5.addChild("skirtL1_6", ModelPartBuilder.create().uv(33, 76).cuboid(-0.5f, 0.0f, 0.0f, 1.0f, 2.0f, 0.0f, Dilation(0.0f)), ModelTransform.origin(0.0f, 2.0f, 0.0f))

            val skirtL2_0 = skirt.addChild(
                "skirtL2_0",
                ModelPartBuilder.create().uv(31, 64).cuboid(-0.5f, 0.0f, 0.0f, 1.0f, 2.0f, 0.0f, Dilation(0.0f)),
                ModelTransform.of(4.5f, 10.5f, 0.7f, 0.0f, 1.5708f, 0.0f)
            )

            val skirtL2_1 =
                skirtL2_0.addChild("skirtL2_1", ModelPartBuilder.create().uv(31, 66).cuboid(-0.5f, 0.0f, 0.0f, 1.0f, 2.0f, 0.0f, Dilation(0.0f)), ModelTransform.origin(0.0f, 2.0f, 0.0f))

            val skirtL2_2 =
                skirtL2_1.addChild("skirtL2_2", ModelPartBuilder.create().uv(31, 68).cuboid(-0.5f, 0.0f, 0.0f, 1.0f, 2.0f, 0.0f, Dilation(0.0f)), ModelTransform.origin(0.0f, 2.0f, 0.0f))

            val skirtL2_3 =
                skirtL2_2.addChild("skirtL2_3", ModelPartBuilder.create().uv(31, 70).cuboid(-0.5f, 0.0f, 0.0f, 1.0f, 2.0f, 0.0f, Dilation(0.0f)), ModelTransform.origin(0.0f, 2.0f, 0.0f))

            val skirtL2_4 =
                skirtL2_3.addChild("skirtL2_4", ModelPartBuilder.create().uv(31, 72).cuboid(-0.5f, 0.0f, 0.0f, 1.0f, 2.0f, 0.0f, Dilation(0.0f)), ModelTransform.origin(0.0f, 2.0f, 0.0f))

            val skirtL2_5 =
                skirtL2_4.addChild("skirtL2_5", ModelPartBuilder.create().uv(31, 74).cuboid(-0.5f, 0.0f, 0.0f, 1.0f, 2.0f, 0.0f, Dilation(0.0f)), ModelTransform.origin(0.0f, 2.0f, 0.0f))

            val skirtL2_6 =
                skirtL2_5.addChild("skirtL2_6", ModelPartBuilder.create().uv(31, 76).cuboid(-0.5f, 0.0f, 0.0f, 1.0f, 2.0f, 0.0f, Dilation(0.0f)), ModelTransform.origin(0.0f, 2.0f, 0.0f))

            val skirtL3_0 = skirt.addChild(
                "skirtL3_0",
                ModelPartBuilder.create().uv(29, 64).cuboid(-0.5f, 0.0f, 0.0f, 1.0f, 2.0f, 0.0f, Dilation(0.0f)),
                ModelTransform.of(4.5f, 10.5f, 1.95f, 0.0f, 1.5708f, 0.0f)
            )

            val skirtL3_1 =
                skirtL3_0.addChild("skirtL3_1", ModelPartBuilder.create().uv(29, 66).cuboid(-0.5f, 0.0f, 0.0f, 1.0f, 2.0f, 0.0f, Dilation(0.0f)), ModelTransform.origin(0.0f, 2.0f, 0.0f))

            val skirtL3_2 =
                skirtL3_1.addChild("skirtL3_2", ModelPartBuilder.create().uv(29, 68).cuboid(-0.5f, 0.0f, 0.0f, 1.0f, 2.0f, 0.0f, Dilation(0.0f)), ModelTransform.origin(0.0f, 2.0f, 0.0f))

            val skirtL3_3 =
                skirtL3_2.addChild("skirtL3_3", ModelPartBuilder.create().uv(29, 70).cuboid(-0.5f, 0.0f, 0.0f, 1.0f, 2.0f, 0.0f, Dilation(0.0f)), ModelTransform.origin(0.0f, 2.0f, 0.0f))

            val skirtL3_4 =
                skirtL3_3.addChild("skirtL3_4", ModelPartBuilder.create().uv(29, 72).cuboid(-0.5f, 0.0f, 0.0f, 1.0f, 2.0f, 0.0f, Dilation(0.0f)), ModelTransform.origin(0.0f, 2.0f, 0.0f))

            val skirtL3_5 =
                skirtL3_4.addChild("skirtL3_5", ModelPartBuilder.create().uv(29, 74).cuboid(-0.5f, 0.0f, 0.0f, 1.0f, 2.0f, 0.0f, Dilation(0.0f)), ModelTransform.origin(0.0f, 2.0f, 0.0f))

            val skirtL3_6 =
                skirtL3_5.addChild("skirtL3_6", ModelPartBuilder.create().uv(29, 76).cuboid(-0.5f, 0.0f, 0.0f, 1.0f, 2.0f, 0.0f, Dilation(0.0f)), ModelTransform.origin(0.0f, 2.0f, 0.0f))

            val skirtR0_0 = skirt.addChild(
                "skirtR0_0",
                ModelPartBuilder.create().uv(7, 64).cuboid(-0.5f, 0.0f, 0.0f, 1.0f, 2.0f, 0.0f, Dilation(0.0f)),
                ModelTransform.of(-4.5f, 10.5f, -1.8f, 0.0f, -1.5708f, 0.0f)
            )

            val skirtR0_1 =
                skirtR0_0.addChild("skirtR0_1", ModelPartBuilder.create().uv(7, 66).cuboid(-0.5f, 0.0f, 0.0f, 1.0f, 2.0f, 0.0f, Dilation(0.0f)), ModelTransform.origin(0.0f, 2.0f, 0.0f))

            val skirtR0_2 =
                skirtR0_1.addChild("skirtR0_2", ModelPartBuilder.create().uv(7, 68).cuboid(-0.5f, 0.0f, 0.0f, 1.0f, 2.0f, 0.0f, Dilation(0.0f)), ModelTransform.origin(0.0f, 2.0f, 0.0f))

            val skirtR0_3 =
                skirtR0_2.addChild("skirtR0_3", ModelPartBuilder.create().uv(7, 70).cuboid(-0.5f, 0.0f, 0.0f, 1.0f, 2.0f, 0.0f, Dilation(0.0f)), ModelTransform.origin(0.0f, 2.0f, 0.0f))

            val skirtR0_4 =
                skirtR0_3.addChild("skirtR0_4", ModelPartBuilder.create().uv(7, 72).cuboid(-0.5f, 0.0f, 0.0f, 1.0f, 2.0f, 0.0f, Dilation(0.0f)), ModelTransform.origin(0.0f, 2.0f, 0.0f))

            val skirtR0_5 =
                skirtR0_4.addChild("skirtR0_5", ModelPartBuilder.create().uv(7, 74).cuboid(-0.5f, 0.0f, 0.0f, 1.0f, 2.0f, 0.0f, Dilation(0.0f)), ModelTransform.origin(0.0f, 2.0f, 0.0f))

            val skirtR1_0 = skirt.addChild(
                "skirtR1_0",
                ModelPartBuilder.create().uv(9, 64).cuboid(-0.5f, 0.0f, 0.0f, 1.0f, 2.0f, 0.0f, Dilation(0.0f)),
                ModelTransform.of(-4.5f, 10.5f, -0.55f, 0.0f, -1.5708f, 0.0f)
            )

            val skirtR1_1 =
                skirtR1_0.addChild("skirtR1_1", ModelPartBuilder.create().uv(9, 66).cuboid(-0.5f, 0.0f, 0.0f, 1.0f, 2.0f, 0.0f, Dilation(0.0f)), ModelTransform.origin(0.0f, 2.0f, 0.0f))

            val skirtR1_2 =
                skirtR1_1.addChild("skirtR1_2", ModelPartBuilder.create().uv(9, 68).cuboid(-0.5f, 0.0f, 0.0f, 1.0f, 2.0f, 0.0f, Dilation(0.0f)), ModelTransform.origin(0.0f, 2.0f, 0.0f))

            val skirtR1_3 =
                skirtR1_2.addChild("skirtR1_3", ModelPartBuilder.create().uv(9, 70).cuboid(-0.5f, 0.0f, 0.0f, 1.0f, 2.0f, 0.0f, Dilation(0.0f)), ModelTransform.origin(0.0f, 2.0f, 0.0f))

            val skirtR1_4 =
                skirtR1_3.addChild("skirtR1_4", ModelPartBuilder.create().uv(9, 72).cuboid(-0.5f, 0.0f, 0.0f, 1.0f, 2.0f, 0.0f, Dilation(0.0f)), ModelTransform.origin(0.0f, 2.0f, 0.0f))

            val skirtR1_5 =
                skirtR1_4.addChild("skirtR1_5", ModelPartBuilder.create().uv(9, 74).cuboid(-0.5f, 0.0f, 0.0f, 1.0f, 2.0f, 0.0f, Dilation(0.0f)), ModelTransform.origin(0.0f, 2.0f, 0.0f))

            val skirtR1_6 =
                skirtR1_5.addChild("skirtR1_6", ModelPartBuilder.create().uv(9, 76).cuboid(-0.5f, 0.0f, 0.0f, 1.0f, 2.0f, 0.0f, Dilation(0.0f)), ModelTransform.origin(0.0f, 2.0f, 0.0f))

            val skirtR2_0 = skirt.addChild(
                "skirtR2_0",
                ModelPartBuilder.create().uv(11, 64).cuboid(-0.5f, 0.0f, 0.0f, 1.0f, 2.0f, 0.0f, Dilation(0.0f)),
                ModelTransform.of(-4.5f, 10.5f, 0.7f, 0.0f, -1.5708f, 0.0f)
            )

            val skirtR2_1 =
                skirtR2_0.addChild("skirtR2_1", ModelPartBuilder.create().uv(11, 66).cuboid(-0.5f, 0.0f, 0.0f, 1.0f, 2.0f, 0.0f, Dilation(0.0f)), ModelTransform.origin(0.0f, 2.0f, 0.0f))

            val skirtR2_2 =
                skirtR2_1.addChild("skirtR2_2", ModelPartBuilder.create().uv(11, 68).cuboid(-0.5f, 0.0f, 0.0f, 1.0f, 2.0f, 0.0f, Dilation(0.0f)), ModelTransform.origin(0.0f, 2.0f, 0.0f))

            val skirtR2_3 =
                skirtR2_2.addChild("skirtR2_3", ModelPartBuilder.create().uv(11, 70).cuboid(-0.5f, 0.0f, 0.0f, 1.0f, 2.0f, 0.0f, Dilation(0.0f)), ModelTransform.origin(0.0f, 2.0f, 0.0f))

            val skirtR2_4 =
                skirtR2_3.addChild("skirtR2_4", ModelPartBuilder.create().uv(11, 72).cuboid(-0.5f, 0.0f, 0.0f, 1.0f, 2.0f, 0.0f, Dilation(0.0f)), ModelTransform.origin(0.0f, 2.0f, 0.0f))

            val skirtR2_5 =
                skirtR2_4.addChild("skirtR2_5", ModelPartBuilder.create().uv(11, 74).cuboid(-0.5f, 0.0f, 0.0f, 1.0f, 2.0f, 0.0f, Dilation(0.0f)), ModelTransform.origin(0.0f, 2.0f, 0.0f))

            val skirtR2_6 =
                skirtR2_5.addChild("skirtR2_6", ModelPartBuilder.create().uv(11, 76).cuboid(-0.5f, 0.0f, 0.0f, 1.0f, 2.0f, 0.0f, Dilation(0.0f)), ModelTransform.origin(0.0f, 2.0f, 0.0f))

            val skirtR3_0 = skirt.addChild(
                "skirtR3_0",
                ModelPartBuilder.create().uv(13, 64).cuboid(-0.5f, 0.0f, 0.0f, 1.0f, 2.0f, 0.0f, Dilation(0.0f)),
                ModelTransform.of(-4.5f, 10.5f, 1.95f, 0.0f, -1.5708f, 0.0f)
            )

            val skirtR3_1 =
                skirtR3_0.addChild("skirtR3_1", ModelPartBuilder.create().uv(13, 66).cuboid(-0.5f, 0.0f, 0.0f, 1.0f, 2.0f, 0.0f, Dilation(0.0f)), ModelTransform.origin(0.0f, 2.0f, 0.0f))

            val skirtR3_2 =
                skirtR3_1.addChild("skirtR3_2", ModelPartBuilder.create().uv(13, 68).cuboid(-0.5f, 0.0f, 0.0f, 1.0f, 2.0f, 0.0f, Dilation(0.0f)), ModelTransform.origin(0.0f, 2.0f, 0.0f))

            val skirtR3_3 =
                skirtR3_2.addChild("skirtR3_3", ModelPartBuilder.create().uv(13, 70).cuboid(-0.5f, 0.0f, 0.0f, 1.0f, 2.0f, 0.0f, Dilation(0.0f)), ModelTransform.origin(0.0f, 2.0f, 0.0f))

            val skirtR3_4 =
                skirtR3_3.addChild("skirtR3_4", ModelPartBuilder.create().uv(13, 72).cuboid(-0.5f, 0.0f, 0.0f, 1.0f, 2.0f, 0.0f, Dilation(0.0f)), ModelTransform.origin(0.0f, 2.0f, 0.0f))

            val skirtR3_5 =
                skirtR3_4.addChild("skirtR3_5", ModelPartBuilder.create().uv(13, 74).cuboid(-0.5f, 0.0f, 0.0f, 1.0f, 2.0f, 0.0f, Dilation(0.0f)), ModelTransform.origin(0.0f, 2.0f, 0.0f))

            val skirtR3_6 =
                skirtR3_5.addChild("skirtR3_6", ModelPartBuilder.create().uv(13, 76).cuboid(-0.5f, 0.0f, 0.0f, 1.0f, 2.0f, 0.0f, Dilation(0.0f)), ModelTransform.origin(0.0f, 2.0f, 0.0f))
            return TexturedModelData.of(modelData, 128, 128)
        }
    }

    override fun setAngles(
        state: S,
    ) {
        resetNotCopiedTransforms()
        setSkirtAngles(state)
    }

    private fun resetNotCopiedTransforms() {
        combinedParts.forEach { it.resetTransform() }
    }

    private fun setSkirtAngles(
        state: S,
    ) {
        val slowedAge = state.age / 8F
        val speed = state.limbSwingAmplitude // 0.0 to 1.0

        setFrontStrandsRotation(slowedAge, speed)
        setSideStrandsRotation(slowedAge, speed)
        setBackStrandsRotation(slowedAge, speed)
    }

    private fun setFrontStrandsRotation(
        slowedAge: Float,
        speed: Float,
    ) {
        setStrandRotation(skirtFrontCenterStrand, slowedAge, speed, 0F, 0F, true)
        for (index in skirtFrontLeftStrands.indices) {
            val offsetAge = slowedAge - (index + 1) * 0.7F
            val yaw = 0.25F * (index + 1)
            setStrandRotation(skirtFrontLeftStrands[index], offsetAge, speed, -yaw, -yaw, true)
            setStrandRotation(skirtFrontRightStrands[index], offsetAge, speed, yaw, yaw, true)
        }
    }

    private fun setSideStrandsRotation(
        slowedAge: Float,
        speed: Float,
    ) {
        for (index in skirtLeftStrands.indices) {
            val offsetAge = slowedAge - (index + 4) * 0.7F
            val yaw = PI.toFloat() * 0.15F - index * PI.toFloat() * 0.1F
            setStrandRotation(skirtLeftStrands[index], offsetAge, speed, yaw, yaw)
            setStrandRotation(skirtRightStrands[index], offsetAge, speed, -yaw, -yaw)
        }
    }

    private fun setBackStrandsRotation(
        slowedAge: Float,
        speed: Float,
    ) {
        setStrandRotation(skirtBackCenterStrand, slowedAge - 11 * 0.7F, speed, 0F, 0F)
        for (index in skirtBackLeftStrands.indices) {
            val offsetAge = slowedAge - (10 - index) * 0.7F
            val yaw = 0.25F * (index + 1)
            setStrandRotation(skirtBackLeftStrands[index], offsetAge, speed, yaw, yaw)
            setStrandRotation(skirtBackRightStrands[index], offsetAge, speed, -yaw, -yaw)
        }
    }

    private fun setStrandRotation(
        strand: List<ModelPart>,
        slowedOffsetAge: Float,
        speed: Float,
        baseYaw: Float,
        speedYaw: Float,
        isFront: Boolean = false,
    ) {
        val speedFlowAmp = 1F + (speed * 2)

        for ((partIndex, part) in strand.withIndex()) {
            val defaultCurvePitch = 0.1F * (1F - speed)
            val speedPitch = if (partIndex == 0) speed * 1.5F else 0F
            val flowPitch = sin(slowedOffsetAge - partIndex) * 0.03F * partIndex * speedFlowAmp

            val pitch = defaultCurvePitch + speedPitch + flowPitch
            part.pitch += if (!isFront) pitch else -pitch

            if (partIndex > 0) continue
            val yaw = baseYaw + (speedYaw - baseYaw) * speed
            part.yaw += yaw
        }
    }
}
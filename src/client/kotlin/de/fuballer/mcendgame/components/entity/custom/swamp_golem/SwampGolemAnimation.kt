package de.fuballer.mcendgame.components.entity.custom.swamp_golem

import net.minecraft.client.render.entity.animation.Animation
import net.minecraft.client.render.entity.animation.AnimationHelper
import net.minecraft.client.render.entity.animation.Keyframe
import net.minecraft.client.render.entity.animation.Transformation

object SwampGolemAnimation {
    val IDLE: Animation = Animation.Builder.create(3.0f).looping()
        .addBoneAnimation(
            "upper_body", Transformation(
                Transformation.Targets.ROTATE,
                Keyframe(
                    0.0f,
                    AnimationHelper.createRotationalVector(0.0f, 0.0f, 0.0f),
                    Transformation.Interpolations.CUBIC
                ),
                Keyframe(
                    1.5f,
                    AnimationHelper.createRotationalVector(2.5f, 0.0f, 0.0f),
                    Transformation.Interpolations.CUBIC
                ),
                Keyframe(
                    3.0f,
                    AnimationHelper.createRotationalVector(0.0f, 0.0f, 0.0f),
                    Transformation.Interpolations.CUBIC
                )
            )
        )
        .addBoneAnimation(
            "upper_left_arm", Transformation(
                Transformation.Targets.ROTATE,
                Keyframe(
                    0.0f,
                    AnimationHelper.createRotationalVector(0.0f, 0.0f, 0.0f),
                    Transformation.Interpolations.CUBIC
                ),
                Keyframe(
                    1.5f,
                    AnimationHelper.createRotationalVector(0.0f, 0.0f, -5.0f),
                    Transformation.Interpolations.CUBIC
                ),
                Keyframe(
                    3.0f,
                    AnimationHelper.createRotationalVector(0.0f, 0.0f, 0.0f),
                    Transformation.Interpolations.CUBIC
                )
            )
        )
        .addBoneAnimation(
            "upper_right_arm", Transformation(
                Transformation.Targets.ROTATE,
                Keyframe(
                    0.0f,
                    AnimationHelper.createRotationalVector(0.0f, 0.0f, 0.0f),
                    Transformation.Interpolations.CUBIC
                ),
                Keyframe(
                    1.5f,
                    AnimationHelper.createRotationalVector(0.0f, 0.0f, 5.0f),
                    Transformation.Interpolations.CUBIC
                ),
                Keyframe(
                    3.0f,
                    AnimationHelper.createRotationalVector(0.0f, 0.0f, 0.0f),
                    Transformation.Interpolations.CUBIC
                )
            )
        )
        .build()

    val SLAM: Animation = Animation.Builder.create(1.25f)
        .addBoneAnimation(
            "lower_body", Transformation(
                Transformation.Targets.ROTATE,
                Keyframe(
                    0.0f,
                    AnimationHelper.createRotationalVector(0.0f, 0.0f, 0.0f),
                    Transformation.Interpolations.LINEAR
                ),
                Keyframe(
                    0.5f,
                    AnimationHelper.createRotationalVector(-5.0f, 0.0f, 0.0f),
                    Transformation.Interpolations.CUBIC
                ),
                Keyframe(
                    0.8333f,
                    AnimationHelper.createRotationalVector(70.0f, 0.0f, 0.0f),
                    Transformation.Interpolations.LINEAR
                ),
                Keyframe(
                    1.25f,
                    AnimationHelper.createRotationalVector(0.0f, 0.0f, 0.0f),
                    Transformation.Interpolations.LINEAR
                )
            )
        )
        .addBoneAnimation(
            "lower_body", Transformation(
                Transformation.Targets.TRANSLATE,
                Keyframe(
                    0.0f,
                    AnimationHelper.createTranslationalVector(0.0f, 0.0f, 0.0f),
                    Transformation.Interpolations.LINEAR
                ),
                Keyframe(
                    0.5f,
                    AnimationHelper.createTranslationalVector(0.0f, 1.0f, 0.0f),
                    Transformation.Interpolations.CUBIC
                ),
                Keyframe(
                    0.8333f,
                    AnimationHelper.createTranslationalVector(0.0f, -1.0f, 0.0f),
                    Transformation.Interpolations.LINEAR
                ),
                Keyframe(
                    1.25f,
                    AnimationHelper.createTranslationalVector(0.0f, 0.0f, 0.0f),
                    Transformation.Interpolations.LINEAR
                )
            )
        )
        .addBoneAnimation(
            "upper_body", Transformation(
                Transformation.Targets.ROTATE,
                Keyframe(
                    0.0f,
                    AnimationHelper.createRotationalVector(0.0f, 0.0f, 0.0f),
                    Transformation.Interpolations.LINEAR
                ),
                Keyframe(
                    0.5f,
                    AnimationHelper.createRotationalVector(-7.5093f, -0.434f, 2.4621f),
                    Transformation.Interpolations.CUBIC
                ),
                Keyframe(
                    0.8333f,
                    AnimationHelper.createRotationalVector(24.9728f, 1.6887f, -1.8437f),
                    Transformation.Interpolations.LINEAR
                ),
                Keyframe(
                    1.25f,
                    AnimationHelper.createRotationalVector(0.0f, 0.0f, 0.0f),
                    Transformation.Interpolations.LINEAR
                )
            )
        )
        .addBoneAnimation(
            "upper_left_arm", Transformation(
                Transformation.Targets.ROTATE,
                Keyframe(
                    0.0f,
                    AnimationHelper.createRotationalVector(0.0f, 0.0f, 0.0f),
                    Transformation.Interpolations.LINEAR
                ),
                Keyframe(
                    0.5f,
                    AnimationHelper.createRotationalVector(-160.0179f, 1.2957f, -4.8293f),
                    Transformation.Interpolations.CUBIC
                ),
                Keyframe(
                    0.8333f,
                    AnimationHelper.createRotationalVector(-137.4437f, 6.1247f, -3.5303f),
                    Transformation.Interpolations.LINEAR
                ),
                Keyframe(
                    1.25f,
                    AnimationHelper.createRotationalVector(0.0f, 0.0f, 0.0f),
                    Transformation.Interpolations.LINEAR
                )
            )
        )
        .addBoneAnimation(
            "lower_left_arm", Transformation(
                Transformation.Targets.ROTATE,
                Keyframe(
                    0.0f,
                    AnimationHelper.createRotationalVector(0.0f, 0.0f, 0.0f),
                    Transformation.Interpolations.LINEAR
                ),
                Keyframe(
                    0.5f,
                    AnimationHelper.createRotationalVector(25.0f, 0.0f, 0.0f),
                    Transformation.Interpolations.CUBIC
                ),
                Keyframe(
                    0.8333f,
                    AnimationHelper.createRotationalVector(15.0f, 0.0f, 0.0f),
                    Transformation.Interpolations.LINEAR
                ),
                Keyframe(
                    1.25f,
                    AnimationHelper.createRotationalVector(0.0f, 0.0f, 0.0f),
                    Transformation.Interpolations.LINEAR
                )
            )
        )
        .addBoneAnimation(
            "right_arm", Transformation(
                Transformation.Targets.ROTATE,
                Keyframe(
                    0.0f,
                    AnimationHelper.createRotationalVector(0.0f, 0.0f, 0.0f),
                    Transformation.Interpolations.LINEAR
                ),
                Keyframe(
                    0.5f,
                    AnimationHelper.createRotationalVector(0.0f, -5.0f, 0.0f),
                    Transformation.Interpolations.CUBIC
                ),
                Keyframe(
                    1.25f,
                    AnimationHelper.createRotationalVector(0.0f, 0.0f, 0.0f),
                    Transformation.Interpolations.LINEAR
                )
            )
        )
        .addBoneAnimation(
            "upper_right_arm", Transformation(
                Transformation.Targets.ROTATE,
                Keyframe(
                    0.0f,
                    AnimationHelper.createRotationalVector(0.0f, 0.0f, 0.0f),
                    Transformation.Interpolations.LINEAR
                ),
                Keyframe(
                    0.5f,
                    AnimationHelper.createRotationalVector(-159.9828f, 3.5337f, 6.1251f),
                    Transformation.Interpolations.CUBIC
                ),
                Keyframe(
                    0.8333f,
                    AnimationHelper.createRotationalVector(-137.5179f, -1.2957f, 4.8293f),
                    Transformation.Interpolations.LINEAR
                ),
                Keyframe(
                    1.25f,
                    AnimationHelper.createRotationalVector(0.0f, 0.0f, 0.0f),
                    Transformation.Interpolations.LINEAR
                )
            )
        )
        .addBoneAnimation(
            "lower_right_arm", Transformation(
                Transformation.Targets.ROTATE,
                Keyframe(
                    0.0f,
                    AnimationHelper.createRotationalVector(0.0f, 0.0f, 0.0f),
                    Transformation.Interpolations.LINEAR
                ),
                Keyframe(
                    0.5f,
                    AnimationHelper.createRotationalVector(25.0f, 0.0f, 0.0f),
                    Transformation.Interpolations.CUBIC
                ),
                Keyframe(
                    0.8333f,
                    AnimationHelper.createRotationalVector(15.0f, 0.0f, 0.0f),
                    Transformation.Interpolations.LINEAR
                ),
                Keyframe(
                    1.25f,
                    AnimationHelper.createRotationalVector(0.0f, 0.0f, 0.0f),
                    Transformation.Interpolations.LINEAR
                )
            )
        )
        .addBoneAnimation(
            "head", Transformation(
                Transformation.Targets.ROTATE,
                Keyframe(
                    0.0f,
                    AnimationHelper.createRotationalVector(0.0f, 0.0f, 0.0f),
                    Transformation.Interpolations.LINEAR
                ),
                Keyframe(
                    0.5f,
                    AnimationHelper.createRotationalVector(7.5f, 0.0f, 0.0f),
                    Transformation.Interpolations.CUBIC
                ),
                Keyframe(
                    0.8333f,
                    AnimationHelper.createRotationalVector(-15.0f, 0.0f, 0.0f),
                    Transformation.Interpolations.LINEAR
                ),
                Keyframe(
                    1.25f,
                    AnimationHelper.createRotationalVector(0.0f, 0.0f, 0.0f),
                    Transformation.Interpolations.LINEAR
                )
            )
        )
        .addBoneAnimation(
            "upper_left_leg", Transformation(
                Transformation.Targets.ROTATE,
                Keyframe(
                    0.0f,
                    AnimationHelper.createRotationalVector(0.0f, 0.0f, 0.0f),
                    Transformation.Interpolations.LINEAR
                ),
                Keyframe(
                    0.5f,
                    AnimationHelper.createRotationalVector(10.0f, 0.0f, 0.0f),
                    Transformation.Interpolations.LINEAR
                ),
                Keyframe(
                    0.8333f,
                    AnimationHelper.createRotationalVector(-80.0f, 0.0f, 0.0f),
                    Transformation.Interpolations.LINEAR
                ),
                Keyframe(
                    1.25f,
                    AnimationHelper.createRotationalVector(0.0f, 0.0f, 0.0f),
                    Transformation.Interpolations.LINEAR
                )
            )
        )
        .addBoneAnimation(
            "upper_left_leg", Transformation(
                Transformation.Targets.TRANSLATE,
                Keyframe(
                    0.0f,
                    AnimationHelper.createTranslationalVector(0.0f, 0.0f, 0.0f),
                    Transformation.Interpolations.LINEAR
                ),
                Keyframe(
                    0.5f,
                    AnimationHelper.createTranslationalVector(0.0f, 0.0f, 0.0f),
                    Transformation.Interpolations.LINEAR
                ),
                Keyframe(
                    0.8333f,
                    AnimationHelper.createTranslationalVector(0.0f, 2.0f, -1.75f),
                    Transformation.Interpolations.LINEAR
                ),
                Keyframe(
                    1.25f,
                    AnimationHelper.createTranslationalVector(0.0f, 0.0f, 0.0f),
                    Transformation.Interpolations.LINEAR
                )
            )
        )
        .addBoneAnimation(
            "lower_left_leg", Transformation(
                Transformation.Targets.ROTATE,
                Keyframe(
                    0.0f,
                    AnimationHelper.createRotationalVector(0.0f, 0.0f, 0.0f),
                    Transformation.Interpolations.LINEAR
                ),
                Keyframe(
                    0.5f,
                    AnimationHelper.createRotationalVector(-10.0f, 0.0f, 0.0f),
                    Transformation.Interpolations.CUBIC
                ),
                Keyframe(
                    0.8333f,
                    AnimationHelper.createRotationalVector(20.0f, 0.0f, 0.0f),
                    Transformation.Interpolations.LINEAR
                ),
                Keyframe(
                    1.25f,
                    AnimationHelper.createRotationalVector(0.0f, 0.0f, 0.0f),
                    Transformation.Interpolations.LINEAR
                )
            )
        )
        .addBoneAnimation(
            "right_leg", Transformation(
                Transformation.Targets.ROTATE,
                Keyframe(
                    0.0f,
                    AnimationHelper.createRotationalVector(0.0f, 0.0f, 0.0f),
                    Transformation.Interpolations.LINEAR
                ),
                Keyframe(
                    0.5f,
                    AnimationHelper.createRotationalVector(-2.5f, 0.0f, 0.0f),
                    Transformation.Interpolations.CUBIC
                ),
                Keyframe(
                    1.25f,
                    AnimationHelper.createRotationalVector(0.0f, 0.0f, 0.0f),
                    Transformation.Interpolations.LINEAR
                )
            )
        )
        .build()
}
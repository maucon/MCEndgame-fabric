package de.fuballer.mcendgame.components.entity.custom.entities.arachne

import net.minecraft.client.render.entity.animation.Animation
import net.minecraft.client.render.entity.animation.AnimationHelper
import net.minecraft.client.render.entity.animation.Keyframe
import net.minecraft.client.render.entity.animation.Transformation

object ArachneAnimation {
    val WALK: Animation = Animation.Builder.create(1.1667f).looping()
        .addBoneAnimation(
            "temurLeft1", Transformation(
                Transformation.Targets.ROTATE,
                Keyframe(
                    0.0f,
                    AnimationHelper.createRotationalVector(0.0f, 0.0f, -5.0f),
                    Transformation.Interpolations.CUBIC
                ),
                Keyframe(
                    0.1667f,
                    AnimationHelper.createRotationalVector(0.0f, 0.0f, -5.0f),
                    Transformation.Interpolations.CUBIC
                ),
                Keyframe(
                    0.5417f,
                    AnimationHelper.createRotationalVector(0.0111f, 12.4995f, 47.6074f),
                    Transformation.Interpolations.CUBIC
                ),
                Keyframe(
                    1.1667f,
                    AnimationHelper.createRotationalVector(0.0f, 0.0f, -5.0f),
                    Transformation.Interpolations.CUBIC
                )
            )
        )
        .addBoneAnimation(
            "tibiaLeft1", Transformation(
                Transformation.Targets.ROTATE,
                Keyframe(
                    0.0f,
                    AnimationHelper.createRotationalVector(0.0f, 0.0f, 5.0f),
                    Transformation.Interpolations.CUBIC
                ),
                Keyframe(
                    0.2083f,
                    AnimationHelper.createRotationalVector(0.0f, 0.0f, -17.5f),
                    Transformation.Interpolations.CUBIC
                ),
                Keyframe(
                    0.5417f,
                    AnimationHelper.createRotationalVector(0.0f, 0.0f, -57.5f),
                    Transformation.Interpolations.CUBIC
                ),
                Keyframe(
                    1.1667f,
                    AnimationHelper.createRotationalVector(0.0f, 0.0f, 5.0f),
                    Transformation.Interpolations.CUBIC
                )
            )
        )
        .addBoneAnimation(
            "metatarsusLeft1", Transformation(
                Transformation.Targets.ROTATE,
                Keyframe(
                    0.0f,
                    AnimationHelper.createRotationalVector(0.0f, 0.0f, 5.0f),
                    Transformation.Interpolations.CUBIC
                ),
                Keyframe(
                    0.2083f,
                    AnimationHelper.createRotationalVector(0.0f, 0.0f, -12.5f),
                    Transformation.Interpolations.CUBIC
                ),
                Keyframe(
                    0.5417f,
                    AnimationHelper.createRotationalVector(0.0f, 0.0f, -25.0f),
                    Transformation.Interpolations.CUBIC
                ),
                Keyframe(
                    1.1667f,
                    AnimationHelper.createRotationalVector(0.0f, 0.0f, 5.0f),
                    Transformation.Interpolations.CUBIC
                )
            )
        )
        .addBoneAnimation(
            "temurLeft2", Transformation(
                Transformation.Targets.ROTATE,
                Keyframe(
                    0.0f,
                    AnimationHelper.createRotationalVector(-8.4f, 15.5065f, 26.0154f),
                    Transformation.Interpolations.CUBIC
                ),
                Keyframe(
                    0.2917f,
                    AnimationHelper.createRotationalVector(6.3941f, 6.9262f, 11.4667f),
                    Transformation.Interpolations.CUBIC
                ),
                Keyframe(
                    0.625f,
                    AnimationHelper.createRotationalVector(22.28f, -2.9f, 6.9f),
                    Transformation.Interpolations.CUBIC
                ),
                Keyframe(
                    0.7917f,
                    AnimationHelper.createRotationalVector(19.9761f, -1.0844f, 2.2559f),
                    Transformation.Interpolations.CUBIC
                ),
                Keyframe(
                    1.1667f,
                    AnimationHelper.createRotationalVector(-8.44f, 15.5f, 26.0f),
                    Transformation.Interpolations.CUBIC
                )
            )
        )
        .addBoneAnimation(
            "tibiaLeft2", Transformation(
                Transformation.Targets.ROTATE,
                Keyframe(
                    0.0f,
                    AnimationHelper.createRotationalVector(0.0f, 0.0f, -30.0f),
                    Transformation.Interpolations.CUBIC
                ),
                Keyframe(
                    0.2917f,
                    AnimationHelper.createRotationalVector(0.0f, 0.0f, -11.25f),
                    Transformation.Interpolations.CUBIC
                ),
                Keyframe(
                    0.625f,
                    AnimationHelper.createRotationalVector(0.0f, 0.0f, -7.5f),
                    Transformation.Interpolations.CUBIC
                ),
                Keyframe(
                    0.7917f,
                    AnimationHelper.createRotationalVector(0.0f, 0.0f, -12.5f),
                    Transformation.Interpolations.CUBIC
                ),
                Keyframe(
                    1.1667f,
                    AnimationHelper.createRotationalVector(0.0f, 0.0f, -30.0f),
                    Transformation.Interpolations.CUBIC
                )
            )
        )
        .addBoneAnimation(
            "metatarsusLeft2", Transformation(
                Transformation.Targets.ROTATE,
                Keyframe(
                    0.0f,
                    AnimationHelper.createRotationalVector(0.0f, 0.0f, -20.0f),
                    Transformation.Interpolations.CUBIC
                ),
                Keyframe(
                    0.2917f,
                    AnimationHelper.createRotationalVector(0.0f, 0.0f, -10.0f),
                    Transformation.Interpolations.CUBIC
                ),
                Keyframe(
                    0.625f,
                    AnimationHelper.createRotationalVector(0.0f, 0.0f, -5.0f),
                    Transformation.Interpolations.CUBIC
                ),
                Keyframe(
                    0.7917f,
                    AnimationHelper.createRotationalVector(0.0f, 0.0f, -7.5f),
                    Transformation.Interpolations.CUBIC
                ),
                Keyframe(
                    1.1667f,
                    AnimationHelper.createRotationalVector(0.0f, 0.0f, -20.0f),
                    Transformation.Interpolations.CUBIC
                )
            )
        )
        .addBoneAnimation(
            "temurLeft3", Transformation(
                Transformation.Targets.ROTATE,
                Keyframe(
                    0.0f,
                    AnimationHelper.createRotationalVector(21.35f, -10.3f, 4.9f),
                    Transformation.Interpolations.CUBIC
                ),
                Keyframe(
                    0.1667f,
                    AnimationHelper.createRotationalVector(22.1411f, -8.4073f, 0.2048f),
                    Transformation.Interpolations.CUBIC
                ),
                Keyframe(
                    0.5833f,
                    AnimationHelper.createRotationalVector(-12.537f, 4.9106f, -0.9356f),
                    Transformation.Interpolations.CUBIC
                ),
                Keyframe(
                    0.875f,
                    AnimationHelper.createRotationalVector(4.6517f, -2.2574f, -3.0047f),
                    Transformation.Interpolations.CUBIC
                ),
                Keyframe(
                    1.1667f,
                    AnimationHelper.createRotationalVector(21.35f, -10.3f, 4.9f),
                    Transformation.Interpolations.CUBIC
                )
            )
        )
        .addBoneAnimation(
            "tibiaLeft3", Transformation(
                Transformation.Targets.ROTATE,
                Keyframe(
                    0.0f,
                    AnimationHelper.createRotationalVector(0.0f, 0.0f, -10.0f),
                    Transformation.Interpolations.CUBIC
                ),
                Keyframe(
                    0.1667f,
                    AnimationHelper.createRotationalVector(0.0f, 0.0f, -15.0f),
                    Transformation.Interpolations.CUBIC
                ),
                Keyframe(
                    0.5833f,
                    AnimationHelper.createRotationalVector(0.0f, 0.0f, 0.0f),
                    Transformation.Interpolations.CUBIC
                ),
                Keyframe(
                    0.875f,
                    AnimationHelper.createRotationalVector(0.0f, 0.0f, 0.0f),
                    Transformation.Interpolations.CUBIC
                ),
                Keyframe(
                    1.1667f,
                    AnimationHelper.createRotationalVector(0.0f, 0.0f, -10.0f),
                    Transformation.Interpolations.CUBIC
                )
            )
        )
        .addBoneAnimation(
            "metatarsusLeft3", Transformation(
                Transformation.Targets.ROTATE,
                Keyframe(
                    0.0f,
                    AnimationHelper.createRotationalVector(0.0f, 0.0f, -2.5f),
                    Transformation.Interpolations.CUBIC
                ),
                Keyframe(
                    0.1667f,
                    AnimationHelper.createRotationalVector(0.0f, 0.0f, -5.0f),
                    Transformation.Interpolations.CUBIC
                ),
                Keyframe(
                    0.5833f,
                    AnimationHelper.createRotationalVector(0.0f, 0.0f, 0.0f),
                    Transformation.Interpolations.CUBIC
                ),
                Keyframe(
                    0.875f,
                    AnimationHelper.createRotationalVector(0.0f, 0.0f, 1.25f),
                    Transformation.Interpolations.CUBIC
                ),
                Keyframe(
                    1.1667f,
                    AnimationHelper.createRotationalVector(0.0f, 0.0f, -2.5f),
                    Transformation.Interpolations.CUBIC
                )
            )
        )
        .addBoneAnimation(
            "temurLeft4", Transformation(
                Transformation.Targets.ROTATE,
                Keyframe(
                    0.0f,
                    AnimationHelper.createRotationalVector(-13.1323f, 2.6954f, -11.041f),
                    Transformation.Interpolations.CUBIC
                ),
                Keyframe(
                    0.2917f,
                    AnimationHelper.createRotationalVector(0.7077f, -7.4856f, -0.7504f),
                    Transformation.Interpolations.CUBIC
                ),
                Keyframe(
                    0.5417f,
                    AnimationHelper.createRotationalVector(11.7765f, -17.8414f, 29.73f),
                    Transformation.Interpolations.CUBIC
                ),
                Keyframe(
                    0.7083f,
                    AnimationHelper.createRotationalVector(7.5044f, -7.5659f, 19.1435f),
                    Transformation.Interpolations.CUBIC
                ),
                Keyframe(
                    1.1667f,
                    AnimationHelper.createRotationalVector(-13.1323f, 2.6954f, -11.041f),
                    Transformation.Interpolations.CUBIC
                )
            )
        )
        .addBoneAnimation(
            "tibiaLeft4", Transformation(
                Transformation.Targets.ROTATE,
                Keyframe(
                    0.0f,
                    AnimationHelper.createRotationalVector(0.0f, 0.0f, 12.5f),
                    Transformation.Interpolations.CUBIC
                ),
                Keyframe(
                    0.2917f,
                    AnimationHelper.createRotationalVector(0.0f, 0.0f, 2.5f),
                    Transformation.Interpolations.CUBIC
                ),
                Keyframe(
                    0.5417f,
                    AnimationHelper.createRotationalVector(0.0f, 0.0f, -32.5f),
                    Transformation.Interpolations.CUBIC
                ),
                Keyframe(
                    0.7083f,
                    AnimationHelper.createRotationalVector(0.0f, 0.0f, -32.5f),
                    Transformation.Interpolations.CUBIC
                ),
                Keyframe(
                    1.1667f,
                    AnimationHelper.createRotationalVector(0.0f, 0.0f, 12.5f),
                    Transformation.Interpolations.CUBIC
                )
            )
        )
        .addBoneAnimation(
            "metatarsusLeft4", Transformation(
                Transformation.Targets.ROTATE,
                Keyframe(
                    0.0f,
                    AnimationHelper.createRotationalVector(0.0f, 0.0f, 2.5f),
                    Transformation.Interpolations.CUBIC
                ),
                Keyframe(
                    0.2917f,
                    AnimationHelper.createRotationalVector(0.0f, 0.0f, -3.75f),
                    Transformation.Interpolations.CUBIC
                ),
                Keyframe(
                    0.5417f,
                    AnimationHelper.createRotationalVector(0.0f, 0.0f, -25.0f),
                    Transformation.Interpolations.CUBIC
                ),
                Keyframe(
                    0.7083f,
                    AnimationHelper.createRotationalVector(0.0f, 0.0f, -22.5f),
                    Transformation.Interpolations.CUBIC
                ),
                Keyframe(
                    1.1667f,
                    AnimationHelper.createRotationalVector(0.0f, 0.0f, 2.5f),
                    Transformation.Interpolations.CUBIC
                )
            )
        )
        .addBoneAnimation(
            "temurRight1", Transformation(
                Transformation.Targets.ROTATE,
                Keyframe(
                    0.0f,
                    AnimationHelper.createRotationalVector(0.0f, -12.5f, -47.5f),
                    Transformation.Interpolations.CUBIC
                ),
                Keyframe(
                    0.5833f,
                    AnimationHelper.createRotationalVector(0.0f, 0.0f, 7.5f),
                    Transformation.Interpolations.CUBIC
                ),
                Keyframe(
                    0.75f,
                    AnimationHelper.createRotationalVector(0.0f, 0.0f, 7.5f),
                    Transformation.Interpolations.CUBIC
                ),
                Keyframe(
                    1.1667f,
                    AnimationHelper.createRotationalVector(0.0f, -12.5f, -47.5f),
                    Transformation.Interpolations.CUBIC
                )
            )
        )
        .addBoneAnimation(
            "tibiaRight1", Transformation(
                Transformation.Targets.ROTATE,
                Keyframe(
                    0.0f,
                    AnimationHelper.createRotationalVector(0.0f, 0.0f, 57.5f),
                    Transformation.Interpolations.CUBIC
                ),
                Keyframe(
                    0.5833f,
                    AnimationHelper.createRotationalVector(0.0f, 0.0f, -7.5f),
                    Transformation.Interpolations.CUBIC
                ),
                Keyframe(
                    0.7917f,
                    AnimationHelper.createRotationalVector(0.0f, 0.0f, 15.0f),
                    Transformation.Interpolations.CUBIC
                ),
                Keyframe(
                    1.1667f,
                    AnimationHelper.createRotationalVector(0.0f, 0.0f, 57.5f),
                    Transformation.Interpolations.CUBIC
                )
            )
        )
        .addBoneAnimation(
            "metatarsusRight1", Transformation(
                Transformation.Targets.ROTATE,
                Keyframe(
                    0.0f,
                    AnimationHelper.createRotationalVector(0.0f, 0.0f, 25.0f),
                    Transformation.Interpolations.CUBIC
                ),
                Keyframe(
                    0.5833f,
                    AnimationHelper.createRotationalVector(0.0f, 0.0f, -5.0f),
                    Transformation.Interpolations.CUBIC
                ),
                Keyframe(
                    0.7917f,
                    AnimationHelper.createRotationalVector(0.0f, 0.0f, 10.0f),
                    Transformation.Interpolations.CUBIC
                ),
                Keyframe(
                    1.1667f,
                    AnimationHelper.createRotationalVector(0.0f, 0.0f, 25.0f),
                    Transformation.Interpolations.CUBIC
                )
            )
        )
        .addBoneAnimation(
            "temurRight2", Transformation(
                Transformation.Targets.ROTATE,
                Keyframe(
                    0.0f,
                    AnimationHelper.createRotationalVector(22.2826f, 2.9191f, -6.9128f),
                    Transformation.Interpolations.CUBIC
                ),
                Keyframe(
                    0.1667f,
                    AnimationHelper.createRotationalVector(19.9701f, 1.077f, -2.2617f),
                    Transformation.Interpolations.CUBIC
                ),
                Keyframe(
                    0.625f,
                    AnimationHelper.createRotationalVector(-8.4406f, -15.5092f, -25.976f),
                    Transformation.Interpolations.CUBIC
                ),
                Keyframe(
                    0.9167f,
                    AnimationHelper.createRotationalVector(6.3759f, -6.9163f, -11.4533f),
                    Transformation.Interpolations.CUBIC
                ),
                Keyframe(
                    1.1667f,
                    AnimationHelper.createRotationalVector(22.2826f, 2.9191f, -6.9128f),
                    Transformation.Interpolations.CUBIC
                )
            )
        )
        .addBoneAnimation(
            "tibiaRight2", Transformation(
                Transformation.Targets.ROTATE,
                Keyframe(
                    0.0f,
                    AnimationHelper.createRotationalVector(0.0f, 0.0f, 7.5f),
                    Transformation.Interpolations.CUBIC
                ),
                Keyframe(
                    0.1667f,
                    AnimationHelper.createRotationalVector(0.0f, 0.0f, 12.5f),
                    Transformation.Interpolations.CUBIC
                ),
                Keyframe(
                    0.625f,
                    AnimationHelper.createRotationalVector(0.0f, 0.0f, 30.0f),
                    Transformation.Interpolations.CUBIC
                ),
                Keyframe(
                    0.9167f,
                    AnimationHelper.createRotationalVector(0.0f, 0.0f, 11.25f),
                    Transformation.Interpolations.CUBIC
                ),
                Keyframe(
                    1.1667f,
                    AnimationHelper.createRotationalVector(0.0f, 0.0f, 7.5f),
                    Transformation.Interpolations.CUBIC
                )
            )
        )
        .addBoneAnimation(
            "metatarsusRight2", Transformation(
                Transformation.Targets.ROTATE,
                Keyframe(
                    0.0f,
                    AnimationHelper.createRotationalVector(0.0f, 0.0f, 5.0f),
                    Transformation.Interpolations.CUBIC
                ),
                Keyframe(
                    0.1667f,
                    AnimationHelper.createRotationalVector(0.0f, 0.0f, 7.5f),
                    Transformation.Interpolations.CUBIC
                ),
                Keyframe(
                    0.625f,
                    AnimationHelper.createRotationalVector(0.0f, 0.0f, 20.0f),
                    Transformation.Interpolations.CUBIC
                ),
                Keyframe(
                    0.9167f,
                    AnimationHelper.createRotationalVector(0.0f, 0.0f, 10.0f),
                    Transformation.Interpolations.CUBIC
                ),
                Keyframe(
                    1.1667f,
                    AnimationHelper.createRotationalVector(0.0f, 0.0f, 5.0f),
                    Transformation.Interpolations.CUBIC
                )
            )
        )
        .addBoneAnimation(
            "temurRight3", Transformation(
                Transformation.Targets.ROTATE,
                Keyframe(
                    0.0f,
                    AnimationHelper.createRotationalVector(-12.537f, -4.9106f, 0.9356f),
                    Transformation.Interpolations.CUBIC
                ),
                Keyframe(
                    0.2917f,
                    AnimationHelper.createRotationalVector(4.6591f, 2.3022f, 3.007f),
                    Transformation.Interpolations.CUBIC
                ),
                Keyframe(
                    0.5417f,
                    AnimationHelper.createRotationalVector(21.3599f, 10.3963f, -4.9009f),
                    Transformation.Interpolations.CUBIC
                ),
                Keyframe(
                    0.7083f,
                    AnimationHelper.createRotationalVector(22.1589f, 8.5024f, -0.2048f),
                    Transformation.Interpolations.CUBIC
                ),
                Keyframe(
                    1.1667f,
                    AnimationHelper.createRotationalVector(-12.537f, -4.9106f, 0.9356f),
                    Transformation.Interpolations.CUBIC
                )
            )
        )
        .addBoneAnimation(
            "tibiaRight3", Transformation(
                Transformation.Targets.ROTATE,
                Keyframe(
                    0.0f,
                    AnimationHelper.createRotationalVector(0.0f, 0.0f, 0.0f),
                    Transformation.Interpolations.CUBIC
                ),
                Keyframe(
                    0.2917f,
                    AnimationHelper.createRotationalVector(0.0f, 0.0f, 0.0f),
                    Transformation.Interpolations.CUBIC
                ),
                Keyframe(
                    0.5417f,
                    AnimationHelper.createRotationalVector(0.0f, 0.0f, 10.0f),
                    Transformation.Interpolations.CUBIC
                ),
                Keyframe(
                    0.7083f,
                    AnimationHelper.createRotationalVector(0.0f, 0.0f, 15.0f),
                    Transformation.Interpolations.CUBIC
                ),
                Keyframe(
                    1.1667f,
                    AnimationHelper.createRotationalVector(0.0f, 0.0f, 0.0f),
                    Transformation.Interpolations.CUBIC
                )
            )
        )
        .addBoneAnimation(
            "metatarsusRight3", Transformation(
                Transformation.Targets.ROTATE,
                Keyframe(
                    0.0f,
                    AnimationHelper.createRotationalVector(0.0f, 0.0f, 0.0f),
                    Transformation.Interpolations.CUBIC
                ),
                Keyframe(
                    0.2917f,
                    AnimationHelper.createRotationalVector(0.0f, 0.0f, -1.25f),
                    Transformation.Interpolations.CUBIC
                ),
                Keyframe(
                    0.5417f,
                    AnimationHelper.createRotationalVector(0.0f, 0.0f, 2.5f),
                    Transformation.Interpolations.CUBIC
                ),
                Keyframe(
                    0.7083f,
                    AnimationHelper.createRotationalVector(0.0f, 0.0f, 7.5f),
                    Transformation.Interpolations.CUBIC
                ),
                Keyframe(
                    1.1667f,
                    AnimationHelper.createRotationalVector(0.0f, 0.0f, 0.0f),
                    Transformation.Interpolations.CUBIC
                )
            )
        )
        .addBoneAnimation(
            "temurRight4", Transformation(
                Transformation.Targets.ROTATE,
                Keyframe(
                    0.0f,
                    AnimationHelper.createRotationalVector(11.77f, 17.8f, -29.7f),
                    Transformation.Interpolations.CUBIC
                ),
                Keyframe(
                    0.1667f,
                    AnimationHelper.createRotationalVector(12.5975f, 9.9351f, -18.4357f),
                    Transformation.Interpolations.CUBIC
                ),
                Keyframe(
                    0.5417f,
                    AnimationHelper.createRotationalVector(-13.1f, -2.7f, 11.0f),
                    Transformation.Interpolations.CUBIC
                ),
                Keyframe(
                    0.8333f,
                    AnimationHelper.createRotationalVector(0.7f, 7.5f, 0.75f),
                    Transformation.Interpolations.CUBIC
                ),
                Keyframe(
                    1.1667f,
                    AnimationHelper.createRotationalVector(11.77f, 17.8f, -29.7f),
                    Transformation.Interpolations.CUBIC
                )
            )
        )
        .addBoneAnimation(
            "tibiaRight4", Transformation(
                Transformation.Targets.ROTATE,
                Keyframe(
                    0.0f,
                    AnimationHelper.createRotationalVector(0.0f, 0.0f, 32.5f),
                    Transformation.Interpolations.CUBIC
                ),
                Keyframe(
                    0.1667f,
                    AnimationHelper.createRotationalVector(0.0f, 0.0f, 32.5f),
                    Transformation.Interpolations.CUBIC
                ),
                Keyframe(
                    0.5417f,
                    AnimationHelper.createRotationalVector(0.0f, 0.0f, -12.5f),
                    Transformation.Interpolations.CUBIC
                ),
                Keyframe(
                    0.8333f,
                    AnimationHelper.createRotationalVector(0.0f, 0.0f, -2.5f),
                    Transformation.Interpolations.CUBIC
                ),
                Keyframe(
                    1.1667f,
                    AnimationHelper.createRotationalVector(0.0f, 0.0f, 32.5f),
                    Transformation.Interpolations.CUBIC
                )
            )
        )
        .addBoneAnimation(
            "metatarsusRight4", Transformation(
                Transformation.Targets.ROTATE,
                Keyframe(
                    0.0f,
                    AnimationHelper.createRotationalVector(0.0f, 0.0f, 25.0f),
                    Transformation.Interpolations.CUBIC
                ),
                Keyframe(
                    0.1667f,
                    AnimationHelper.createRotationalVector(0.0f, 0.0f, 22.5f),
                    Transformation.Interpolations.CUBIC
                ),
                Keyframe(
                    0.5417f,
                    AnimationHelper.createRotationalVector(0.0f, 0.0f, -2.5f),
                    Transformation.Interpolations.CUBIC
                ),
                Keyframe(
                    0.8333f,
                    AnimationHelper.createRotationalVector(0.0f, 0.0f, 3.75f),
                    Transformation.Interpolations.CUBIC
                ),
                Keyframe(
                    1.1667f,
                    AnimationHelper.createRotationalVector(0.0f, 0.0f, 25.0f),
                    Transformation.Interpolations.CUBIC
                )
            )
        )
        .addBoneAnimation(
            "upperbody", Transformation(
                Transformation.Targets.ROTATE,
                Keyframe(
                    0.0f,
                    AnimationHelper.createRotationalVector(0.5f, -1.0f, -0.3f),
                    Transformation.Interpolations.CUBIC
                ),
                Keyframe(
                    0.2917f,
                    AnimationHelper.createRotationalVector(-0.2f, 0.0f, 0.0f),
                    Transformation.Interpolations.CUBIC
                ),
                Keyframe(
                    0.5833f,
                    AnimationHelper.createRotationalVector(0.5f, 1.0f, 0.3f),
                    Transformation.Interpolations.CUBIC
                ),
                Keyframe(
                    0.875f,
                    AnimationHelper.createRotationalVector(-0.2f, 0.0f, 0.0f),
                    Transformation.Interpolations.CUBIC
                ),
                Keyframe(
                    1.1667f,
                    AnimationHelper.createRotationalVector(0.5f, -1.0f, -0.3f),
                    Transformation.Interpolations.CUBIC
                )
            )
        )
        .addBoneAnimation(
            "chest", Transformation(
                Transformation.Targets.ROTATE,
                Keyframe(
                    0.0f,
                    AnimationHelper.createRotationalVector(0.5f, -1.0f, 0.0f),
                    Transformation.Interpolations.CUBIC
                ),
                Keyframe(
                    0.2917f,
                    AnimationHelper.createRotationalVector(-0.2f, 0.0f, 0.0f),
                    Transformation.Interpolations.CUBIC
                ),
                Keyframe(
                    0.5833f,
                    AnimationHelper.createRotationalVector(0.5f, 1.0f, 0.0f),
                    Transformation.Interpolations.CUBIC
                ),
                Keyframe(
                    0.875f,
                    AnimationHelper.createRotationalVector(-0.2f, 0.0f, 0.0f),
                    Transformation.Interpolations.CUBIC
                ),
                Keyframe(
                    1.1667f,
                    AnimationHelper.createRotationalVector(0.5f, -1.0f, 0.0f),
                    Transformation.Interpolations.CUBIC
                )
            )
        )
        .addBoneAnimation(
            "head", Transformation(
                Transformation.Targets.ROTATE,
                Keyframe(
                    0.0f,
                    AnimationHelper.createRotationalVector(0.0f, 1.0f, 0.0f),
                    Transformation.Interpolations.CUBIC
                ),
                Keyframe(
                    0.5833f,
                    AnimationHelper.createRotationalVector(0.0f, -1.0f, 0.0f),
                    Transformation.Interpolations.CUBIC
                ),
                Keyframe(
                    1.1667f,
                    AnimationHelper.createRotationalVector(0.0f, 1.0f, 0.0f),
                    Transformation.Interpolations.CUBIC
                )
            )
        )
        .addBoneAnimation(
            "armLeft", Transformation(
                Transformation.Targets.ROTATE,
                Keyframe(
                    0.0f,
                    AnimationHelper.createRotationalVector(2.5f, 0.0f, -2.5f),
                    Transformation.Interpolations.CUBIC
                ),
                Keyframe(
                    0.625f,
                    AnimationHelper.createRotationalVector(-2.5f, 0.0f, -2.5f),
                    Transformation.Interpolations.CUBIC
                ),
                Keyframe(
                    1.1667f,
                    AnimationHelper.createRotationalVector(2.5f, 0.0f, -2.5f),
                    Transformation.Interpolations.CUBIC
                )
            )
        )
        .addBoneAnimation(
            "armRight", Transformation(
                Transformation.Targets.ROTATE,
                Keyframe(
                    0.0f,
                    AnimationHelper.createRotationalVector(-2.5f, 0.0f, 2.5f),
                    Transformation.Interpolations.CUBIC
                ),
                Keyframe(
                    0.5417f,
                    AnimationHelper.createRotationalVector(2.5f, 0.0f, 2.5f),
                    Transformation.Interpolations.CUBIC
                ),
                Keyframe(
                    1.1667f,
                    AnimationHelper.createRotationalVector(-2.5f, 0.0f, 2.5f),
                    Transformation.Interpolations.CUBIC
                )
            )
        )
        .addBoneAnimation(
            "abdomen", Transformation(
                Transformation.Targets.ROTATE,
                Keyframe(
                    0.0f,
                    AnimationHelper.createRotationalVector(1.5f, -1.0f, 0.0f),
                    Transformation.Interpolations.CUBIC
                ),
                Keyframe(
                    0.2917f,
                    AnimationHelper.createRotationalVector(-1.5f, 0.0f, 0.0f),
                    Transformation.Interpolations.CUBIC
                ),
                Keyframe(
                    0.5833f,
                    AnimationHelper.createRotationalVector(1.5f, 1.0f, 0.0f),
                    Transformation.Interpolations.CUBIC
                ),
                Keyframe(
                    0.875f,
                    AnimationHelper.createRotationalVector(-1.5f, 0.0f, 0.0f),
                    Transformation.Interpolations.CUBIC
                ),
                Keyframe(
                    1.1667f,
                    AnimationHelper.createRotationalVector(1.5f, -1.0f, 0.0f),
                    Transformation.Interpolations.CUBIC
                )
            )
        )
        .build()
}
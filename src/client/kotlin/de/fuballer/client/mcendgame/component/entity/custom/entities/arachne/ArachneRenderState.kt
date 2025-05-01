package de.fuballer.client.mcendgame.component.entity.custom.entities.arachne

import net.minecraft.client.render.entity.state.LivingEntityRenderState
import net.minecraft.entity.AnimationState
import net.minecraft.util.math.Vec3d

class ArachneRenderState : LivingEntityRenderState() {
    val idleAnimationState: AnimationState = AnimationState()
    val walkAnimationState: AnimationState = AnimationState()
    val walkBWAnimationState: AnimationState = AnimationState()

    val spitAnimationState: AnimationState = AnimationState()
    val meleeAttackAnimationState: AnimationState = AnimationState()

    var isSaddled: Boolean = false
    var moveSpeed: Float = 0F

    var webHookData: WebHookData? = null

    companion object {
        class WebHookData {
            var offset: Vec3d = Vec3d.ZERO
            var pos: Vec3d = Vec3d.ZERO
            var blockLight: Int = 0
            var skyLight: Int = 15
            var hookedEntities: MutableList<WebHookedEntityData> = mutableListOf()
        }

        class WebHookedEntityData {
            var pos: Vec3d = Vec3d.ZERO
            var blockLight: Int = 0
            var skyLight: Int = 15
        }
    }
}
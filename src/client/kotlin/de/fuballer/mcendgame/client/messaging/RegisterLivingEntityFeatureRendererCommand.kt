package de.fuballer.mcendgame.client.messaging

import net.fabricmc.fabric.api.client.rendering.v1.LivingEntityFeatureRendererRegistrationCallback
import net.minecraft.client.render.entity.EntityRendererFactory
import net.minecraft.client.render.entity.LivingEntityRenderer
import net.minecraft.entity.EntityType
import net.minecraft.entity.LivingEntity

class RegisterLivingEntityFeatureRendererCommand(
    val entityType: EntityType<out LivingEntity>,
    val entityRenderer: LivingEntityRenderer<*, *, *>,
    val registrationHelper: LivingEntityFeatureRendererRegistrationCallback.RegistrationHelper,
    val context: EntityRendererFactory.Context
)
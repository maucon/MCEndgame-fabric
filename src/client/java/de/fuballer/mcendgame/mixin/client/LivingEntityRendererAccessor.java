package de.fuballer.mcendgame.mixin.client;

import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(LivingEntityRenderer.class)
interface LivingEntityRendererAccessor {
    @Invoker("addFeature")
    <S extends LivingEntityRenderState, M extends EntityModel<S>> boolean invokeAddFeature(FeatureRenderer<S, M> feature);
}

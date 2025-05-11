package de.fuballer.mcendgame.client.component.item.custom.armor.forest_queen

import de.fuballer.mcendgame.client.component.render.render_layer.SkinColorRenderLayer
import de.fuballer.mcendgame.main.component.item.custom.armor.forest_queen.ForestQueenChestplate
import de.fuballer.mcendgame.main.util.minecraft.IdentifierUtil
import net.minecraft.client.render.entity.state.BipedEntityRenderState
import software.bernie.geckolib.renderer.GeoArmorRenderer
import software.bernie.geckolib.renderer.base.GeoRenderState

class ForestQueenRenderer {
    class Chestplate<R> : GeoArmorRenderer<ForestQueenChestplate, R>(ForestQueenModel.Chestplate()) where  R : BipedEntityRenderState, R : GeoRenderState {
        init {
            addRenderLayer(
                SkinColorRenderLayer(
                    this,
                    IdentifierUtil.default("textures/armor/forest_queen_skin.png"),
                )
            )
        }
    }
}
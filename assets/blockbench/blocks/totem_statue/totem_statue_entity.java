// Made with Blockbench 5.0.2
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports

package com.example.mod;
   
public class totem_statue_entity extends EntityModel<Entity> {
	private final ModelPart bb_main;
	public totem_statue_entity(ModelPart root) {
		this.bb_main = root.getChild("bb_main");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData bb_main = modelPartData.addChild("bb_main", ModelPartBuilder.create().uv(4, 31).cuboid(-3.0F, -8.0F, -3.0F, 6.0F, 6.0F, 6.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-4.0F, -15.0F, -4.0F, 8.0F, 7.0F, 8.0F, new Dilation(0.0F))
		.uv(12, 16).cuboid(-1.0F, -11.0F, -5.0F, 2.0F, 4.0F, 2.0F, new Dilation(0.0F))
		.uv(8, 44).cuboid(-2.0F, -2.0F, -2.0F, 4.0F, 2.0F, 4.0F, new Dilation(0.0F))
		.uv(18, 20).cuboid(3.0F, -8.0F, -1.0F, 4.0F, 2.0F, 3.0F, new Dilation(0.0F))
		.uv(19, 26).cuboid(3.0F, -6.0F, -1.0F, 3.0F, 1.0F, 3.0F, new Dilation(0.0F))
		.uv(0, 20).cuboid(-7.0F, -8.0F, -1.0F, 4.0F, 2.0F, 3.0F, new Dilation(0.0F))
		.uv(1, 26).cuboid(-6.0F, -6.0F, -1.0F, 3.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));
		return TexturedModelData.of(modelData, 32, 64);
	}
	@Override
	public void setAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}
	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		bb_main.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}
}
// Made with Blockbench 4.12.4
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
public class Webshot extends EntityModel<Entity> {
	private final ModelPart webshot;
	public Webshot(ModelPart root) {
		this.webshot = root.getChild("webshot");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData webshot = modelPartData.addChild("webshot", ModelPartBuilder.create().uv(0, 5).cuboid(-2.0F, -2.0F, -1.0F, 4.0F, 4.0F, 2.0F, new Dilation(0.0F))
		.uv(2, 0).cuboid(-1.5F, -1.5F, -2.0F, 3.0F, 3.0F, 1.0F, new Dilation(0.0F))
		.uv(1, 12).cuboid(-1.5F, -1.5F, 1.0F, 3.0F, 3.0F, 2.0F, new Dilation(0.0F))
		.uv(2, 18).cuboid(-1.0F, -1.0F, 3.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F))
		.uv(3, 23).cuboid(-0.5F, -0.5F, 5.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 21.5F, 0.0F));
		return TexturedModelData.of(modelData, 32, 32);
	}
	@Override
	public void setAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}
	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		webshot.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}
}
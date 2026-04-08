// Made with Blockbench 4.12.4
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
public class portal extends EntityModel<Entity> {
	private final ModelPart portal;
	public portal(ModelPart root) {
		this.portal = root.getChild("portal");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData portal = modelPartData.addChild("portal", ModelPartBuilder.create().uv(0, 0).cuboid(-8.0F, -16.0F, 0.0F, 16.0F, 32.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 8.0F, 0.0F));
		return TexturedModelData.of(modelData, 32, 32);
	}
	@Override
	public void setAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}
	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		portal.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}
}
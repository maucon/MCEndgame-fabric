// Made with Blockbench 4.12.4
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
public class suede extends EntityModel<Entity> {
	private final ModelPart left_leg;
	private final ModelPart left_boot;
	private final ModelPart right_leg;
	private final ModelPart right_boot;
	public suede(ModelPart root) {
		this.left_leg = root.getChild("left_leg");
		this.left_boot = this.left_leg.getChild("left_boot");
		this.right_leg = root.getChild("right_leg");
		this.right_boot = this.right_leg.getChild("right_boot");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData left_leg = modelPartData.addChild("left_leg", ModelPartBuilder.create(), ModelTransform.pivot(2.0F, 12.0F, 0.0F));

		ModelPartData left_boot = left_leg.addChild("left_boot", ModelPartBuilder.create().uv(34, 29).cuboid(-2.5F, 3.5F, -2.5F, 5.0F, 9.0F, 5.0F, new Dilation(-0.25F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData right_leg = modelPartData.addChild("right_leg", ModelPartBuilder.create(), ModelTransform.pivot(-2.0F, 12.0F, 0.0F));

		ModelPartData right_boot = right_leg.addChild("right_boot", ModelPartBuilder.create().uv(12, 29).cuboid(1.5F, 3.5F, -2.5F, 5.0F, 9.0F, 5.0F, new Dilation(-0.25F)), ModelTransform.pivot(-4.0F, 0.0F, 0.0F));
		return TexturedModelData.of(modelData, 128, 128);
	}
	@Override
	public void setAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}
	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		left_leg.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
		right_leg.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}
}
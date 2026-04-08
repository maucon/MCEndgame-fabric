// Made with Blockbench 4.12.2
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
public class solar_eclipse_leona extends EntityModel<Entity> {
	private final ModelPart left_leg;
	private final ModelPart left_boot;
	private final ModelPart left_boot_top;
	private final ModelPart right_leg;
	private final ModelPart right_boot;
	private final ModelPart right_boot_top;
	public solar_eclipse_leona(ModelPart root) {
		this.left_leg = root.getChild("left_leg");
		this.left_boot = this.left_leg.getChild("left_boot");
		this.left_boot_top = this.left_boot.getChild("left_boot_top");
		this.right_leg = root.getChild("right_leg");
		this.right_boot = this.right_leg.getChild("right_boot");
		this.right_boot_top = this.right_boot.getChild("right_boot_top");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData left_leg = modelPartData.addChild("left_leg", ModelPartBuilder.create(), ModelTransform.pivot(2.0F, 12.0F, 0.0F));

		ModelPartData left_boot = left_leg.addChild("left_boot", ModelPartBuilder.create().uv(22, 19).cuboid(-2.5F, 9.35F, -2.5F, 5.0F, 3.0F, 5.0F, new Dilation(0.25F))
		.uv(24, 10).cuboid(-2.0F, 6.0F, -2.0F, 4.0F, 5.0F, 4.0F, new Dilation(0.55F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData left_boot_top = left_boot.addChild("left_boot_top", ModelPartBuilder.create().uv(22, 0).cuboid(-2.5F, -5.25F, 0.25F, 5.0F, 5.0F, 5.0F, new Dilation(0.25F)), ModelTransform.of(0.0F, 6.5F, -2.6F, 0.0873F, 0.0F, 0.0F));

		ModelPartData right_leg = modelPartData.addChild("right_leg", ModelPartBuilder.create(), ModelTransform.pivot(-2.0F, 12.0F, 0.0F));

		ModelPartData right_boot = right_leg.addChild("right_boot", ModelPartBuilder.create().uv(0, 19).cuboid(-2.5F, 9.35F, -2.5F, 5.0F, 3.0F, 5.0F, new Dilation(0.25F))
		.uv(2, 10).cuboid(-2.0F, 6.0F, -2.0F, 4.0F, 5.0F, 4.0F, new Dilation(0.55F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData right_boot_top = right_boot.addChild("right_boot_top", ModelPartBuilder.create().uv(0, 0).cuboid(-2.5F, -5.25F, 0.25F, 5.0F, 5.0F, 5.0F, new Dilation(0.25F)), ModelTransform.of(0.0F, 6.5F, -2.6F, 0.0873F, 0.0F, 0.0F));
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
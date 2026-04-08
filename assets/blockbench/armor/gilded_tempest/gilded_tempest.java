// Made with Blockbench 5.0.4
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
public class gilded_tempest extends EntityModel<Entity> {
	private final ModelPart body;
	private final ModelPart leggings_waist;
	private final ModelPart left_leg;
	private final ModelPart left_leggings;
	private final ModelPart right_leg;
	private final ModelPart right_leggings;
	public gilded_tempest(ModelPart root) {
		this.body = root.getChild("body");
		this.leggings_waist = this.body.getChild("leggings_waist");
		this.left_leg = root.getChild("left_leg");
		this.left_leggings = this.left_leg.getChild("left_leggings");
		this.right_leg = root.getChild("right_leg");
		this.right_leggings = this.right_leg.getChild("right_leggings");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData body = modelPartData.addChild("body", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData leggings_waist = body.addChild("leggings_waist", ModelPartBuilder.create().uv(18, 0).cuboid(-4.5F, 6.5F, -2.5F, 9.0F, 5.0F, 5.0F, new Dilation(0.0F))
		.uv(17, 39).cuboid(-5.0F, 9.0F, -2.5F, 10.0F, 3.0F, 5.0F, new Dilation(0.1F))
		.uv(15, 10).cuboid(-5.5F, 7.5F, -3.0F, 11.0F, 5.0F, 6.0F, new Dilation(-0.05F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData left_leg = modelPartData.addChild("left_leg", ModelPartBuilder.create(), ModelTransform.pivot(2.0F, 12.0F, 0.0F));

		ModelPartData left_leggings = left_leg.addChild("left_leggings", ModelPartBuilder.create().uv(43, 32).cuboid(-2.1F, -0.5F, -2.5F, 5.0F, 6.0F, 5.0F, new Dilation(0.0F))
		.uv(45, 52).cuboid(-2.0F, 2.5F, -2.0F, 4.0F, 8.0F, 4.0F, new Dilation(0.05F))
		.uv(44, 43).cuboid(-1.5F, 5.5F, -2.5F, 4.0F, 4.0F, 5.0F, new Dilation(0.0F))
		.uv(42, 22).cuboid(-1.5F, 0.5F, -3.0F, 5.0F, 4.0F, 6.0F, new Dilation(-0.05F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData right_leg = modelPartData.addChild("right_leg", ModelPartBuilder.create(), ModelTransform.pivot(-2.0F, 12.0F, 0.0F));

		ModelPartData right_leggings = right_leg.addChild("right_leggings", ModelPartBuilder.create().uv(1, 32).cuboid(-2.9F, -0.5F, -2.5F, 5.0F, 6.0F, 5.0F, new Dilation(0.0F))
		.uv(3, 52).cuboid(-2.0F, 2.5F, -2.0F, 4.0F, 8.0F, 4.0F, new Dilation(0.05F))
		.uv(2, 43).cuboid(-2.5F, 5.5F, -2.5F, 4.0F, 4.0F, 5.0F, new Dilation(0.0F))
		.uv(0, 22).cuboid(-3.5F, 0.5F, -3.0F, 5.0F, 4.0F, 6.0F, new Dilation(-0.05F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
		return TexturedModelData.of(modelData, 64, 64);
	}
	@Override
	public void setAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}
	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		body.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
		left_leg.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
		right_leg.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}
}
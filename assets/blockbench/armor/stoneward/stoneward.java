// Made with Blockbench 4.12.6
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
public class template extends EntityModel<Entity> {
	private final ModelPart body;
	private final ModelPart leggings_waist;
	private final ModelPart left_leg;
	private final ModelPart left_leggings;
	private final ModelPart right_leg;
	private final ModelPart right_leggings;
	public template(ModelPart root) {
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

		ModelPartData leggings_waist = body.addChild("leggings_waist", ModelPartBuilder.create().uv(13, 0).cuboid(-4.0F, 8.0F, -2.0F, 8.0F, 4.0F, 4.0F, new Dilation(0.55F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData left_leg = modelPartData.addChild("left_leg", ModelPartBuilder.create(), ModelTransform.pivot(2.0F, 12.0F, 0.0F));

		ModelPartData left_leggings = left_leg.addChild("left_leggings", ModelPartBuilder.create().uv(30, 47).cuboid(-2.1F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.5F))
		.uv(28, 28).cuboid(-2.6F, -0.5F, -2.5F, 5.0F, 13.0F, 5.0F, new Dilation(0.23F))
		.uv(26, 9).cuboid(-2.9F, -5.5F, -3.0F, 6.0F, 12.0F, 6.0F, new Dilation(0.1F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData right_leg = modelPartData.addChild("right_leg", ModelPartBuilder.create(), ModelTransform.pivot(-2.0F, 12.0F, 0.0F));

		ModelPartData right_leggings = right_leg.addChild("right_leggings", ModelPartBuilder.create().uv(4, 47).cuboid(-1.9F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.5F))
		.uv(2, 28).cuboid(-2.4F, -0.5F, -2.5F, 5.0F, 13.0F, 5.0F, new Dilation(0.23F))
		.uv(0, 9).cuboid(-3.1F, -5.5F, -3.0F, 6.0F, 12.0F, 6.0F, new Dilation(0.1F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
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
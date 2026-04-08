// Made with Blockbench 5.0.5
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
public class template extends EntityModel<Entity> {
	private final ModelPart body;
	private final ModelPart chestplate;
	private final ModelPart capeBottomLeft;
	private final ModelPart capeBottomRight;
	private final ModelPart leggings_waist;
	private final ModelPart left_arm;
	private final ModelPart chestplate_left_arm;
	private final ModelPart right_arm;
	private final ModelPart chestplate_right_arm;
	public template(ModelPart root) {
		this.body = root.getChild("body");
		this.chestplate = this.body.getChild("chestplate");
		this.capeBottomLeft = this.chestplate.getChild("capeBottomLeft");
		this.capeBottomRight = this.chestplate.getChild("capeBottomRight");
		this.leggings_waist = this.body.getChild("leggings_waist");
		this.left_arm = root.getChild("left_arm");
		this.chestplate_left_arm = this.left_arm.getChild("chestplate_left_arm");
		this.right_arm = root.getChild("right_arm");
		this.chestplate_right_arm = this.right_arm.getChild("chestplate_right_arm");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData body = modelPartData.addChild("body", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData chestplate = body.addChild("chestplate", ModelPartBuilder.create().uv(21, 34).cuboid(-4.5F, -0.25F, -2.5F, 9.0F, 7.0F, 5.0F, new Dilation(0.0F))
		.uv(23, 47).cuboid(-4.0F, 6.0F, -2.0F, 8.0F, 3.0F, 4.0F, new Dilation(0.105F))
		.uv(21, 55).cuboid(-4.5F, 8.95F, -2.5F, 9.0F, 3.0F, 5.0F, new Dilation(-0.15F))
		.uv(18, 0).cuboid(-5.5F, -2.75F, -3.0F, 11.0F, 10.0F, 6.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData capeBottomLeft = chestplate.addChild("capeBottomLeft", ModelPartBuilder.create().uv(36, 17).cuboid(-1.03F, -0.2F, -2.975F, 6.0F, 10.0F, 6.0F, new Dilation(-0.2F)), ModelTransform.of(-0.05F, 7.25F, 0.0F, 0.0F, 0.0F, -0.0288F));

		ModelPartData capeBottomRight = chestplate.addChild("capeBottomRight", ModelPartBuilder.create().uv(10, 17).cuboid(-4.97F, -0.2F, -3.0F, 6.0F, 10.0F, 6.0F, new Dilation(-0.2F)), ModelTransform.of(0.05F, 7.25F, 0.0F, 0.0F, 0.0F, 0.0288F));

		ModelPartData leggings_waist = body.addChild("leggings_waist", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData left_arm = modelPartData.addChild("left_arm", ModelPartBuilder.create(), ModelTransform.pivot(5.0F, 2.0F, 0.0F));

		ModelPartData chestplate_left_arm = left_arm.addChild("chestplate_left_arm", ModelPartBuilder.create().uv(52, 46).cuboid(-1.0F, 3.5F, -2.0F, 4.0F, 7.0F, 4.0F, new Dilation(-0.5F))
		.uv(50, 34).cuboid(-1.5F, -2.0F, -2.5F, 5.0F, 6.0F, 5.0F, new Dilation(-0.01F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData right_arm = modelPartData.addChild("right_arm", ModelPartBuilder.create(), ModelTransform.pivot(-5.0F, 2.0F, 0.0F));

		ModelPartData chestplate_right_arm = right_arm.addChild("chestplate_right_arm", ModelPartBuilder.create().uv(2, 46).cuboid(-3.0F, 3.5F, -2.0F, 4.0F, 7.0F, 4.0F, new Dilation(-0.5F))
		.uv(0, 34).cuboid(-3.5F, -2.0F, -2.5F, 5.0F, 6.0F, 5.0F, new Dilation(-0.01F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
		return TexturedModelData.of(modelData, 128, 128);
	}
	@Override
	public void setAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}
	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		body.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
		left_arm.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
		right_arm.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}
}
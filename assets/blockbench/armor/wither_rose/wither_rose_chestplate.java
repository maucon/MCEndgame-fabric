// Made with Blockbench 4.12.4
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
public class solar_eclipse_leona extends EntityModel<Entity> {
	private final ModelPart body;
	private final ModelPart chestplate;
	private final ModelPart left_arm;
	private final ModelPart chestplate_left_arm;
	private final ModelPart vambraceLeft;
	private final ModelPart pauldronLeft;
	private final ModelPart pauldronLeftTop;
	private final ModelPart right_arm;
	private final ModelPart chestplate_right_arm;
	private final ModelPart vambraceRight;
	private final ModelPart pauldronRight;
	private final ModelPart pauldronRightTop;
	public solar_eclipse_leona(ModelPart root) {
		this.body = root.getChild("body");
		this.chestplate = this.body.getChild("chestplate");
		this.left_arm = root.getChild("left_arm");
		this.chestplate_left_arm = this.left_arm.getChild("chestplate_left_arm");
		this.vambraceLeft = this.chestplate_left_arm.getChild("vambraceLeft");
		this.pauldronLeft = this.chestplate_left_arm.getChild("pauldronLeft");
		this.pauldronLeftTop = this.pauldronLeft.getChild("pauldronLeftTop");
		this.right_arm = root.getChild("right_arm");
		this.chestplate_right_arm = this.right_arm.getChild("chestplate_right_arm");
		this.vambraceRight = this.chestplate_right_arm.getChild("vambraceRight");
		this.pauldronRight = this.chestplate_right_arm.getChild("pauldronRight");
		this.pauldronRightTop = this.pauldronRight.getChild("pauldronRightTop");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData body = modelPartData.addChild("body", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData chestplate = body.addChild("chestplate", ModelPartBuilder.create().uv(0, 52).cuboid(-4.5F, 5.5F, -2.5F, 9.0F, 5.0F, 5.0F, new Dilation(0.3F))
		.uv(0, 39).cuboid(-5.0F, -0.5F, -3.25F, 10.0F, 6.0F, 6.0F, new Dilation(0.3F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData left_arm = modelPartData.addChild("left_arm", ModelPartBuilder.create(), ModelTransform.pivot(5.0F, 2.0F, 0.0F));

		ModelPartData chestplate_left_arm = left_arm.addChild("chestplate_left_arm", ModelPartBuilder.create().uv(33, 17).cuboid(-1.0F, -2.0F, -2.0F, 4.0F, 5.0F, 4.0F, new Dilation(0.5F))
		.uv(33, 27).cuboid(-1.0F, 3.25F, -2.0F, 4.0F, 7.0F, 4.0F, new Dilation(0.1F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData vambraceLeft = chestplate_left_arm.addChild("vambraceLeft", ModelPartBuilder.create().uv(50, 28).cuboid(1.0F, 4.25F, -3.0F, 2.0F, 5.0F, 5.0F, new Dilation(0.0F)), ModelTransform.pivot(0.5F, 0.0F, 0.5F));

		ModelPartData pauldronLeft = chestplate_left_arm.addChild("pauldronLeft", ModelPartBuilder.create().uv(33, 6).cuboid(-3.5F, 0.5F, -2.5F, 3.0F, 5.0F, 5.0F, new Dilation(0.5F)), ModelTransform.of(3.75F, -3.5F, 0.0F, 0.0F, 0.0F, -0.0873F));

		ModelPartData pauldronLeftTop = pauldronLeft.addChild("pauldronLeftTop", ModelPartBuilder.create().uv(33, 0).cuboid(-4.0F, 0.0F, -2.0F, 4.0F, 1.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.1745F));

		ModelPartData right_arm = modelPartData.addChild("right_arm", ModelPartBuilder.create(), ModelTransform.pivot(-5.0F, 2.0F, 0.0F));

		ModelPartData chestplate_right_arm = right_arm.addChild("chestplate_right_arm", ModelPartBuilder.create().uv(15, 27).cuboid(-3.0F, 3.25F, -2.0F, 4.0F, 7.0F, 4.0F, new Dilation(0.1F))
		.uv(15, 17).cuboid(-3.0F, -2.0F, -2.0F, 4.0F, 5.0F, 4.0F, new Dilation(0.5F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData vambraceRight = chestplate_right_arm.addChild("vambraceRight", ModelPartBuilder.create().uv(0, 28).cuboid(-3.0F, 4.25F, -3.0F, 2.0F, 5.0F, 5.0F, new Dilation(0.0F)), ModelTransform.pivot(-0.5F, 0.0F, 0.5F));

		ModelPartData pauldronRight = chestplate_right_arm.addChild("pauldronRight", ModelPartBuilder.create().uv(15, 6).cuboid(0.5F, 0.5F, -2.5F, 3.0F, 5.0F, 5.0F, new Dilation(0.5F)), ModelTransform.of(-3.75F, -3.5F, 0.0F, 0.0F, 0.0F, 0.0873F));

		ModelPartData pauldronRightTop = pauldronRight.addChild("pauldronRightTop", ModelPartBuilder.create().uv(15, 0).cuboid(0.0F, 0.0F, -2.0F, 4.0F, 1.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.1745F));
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
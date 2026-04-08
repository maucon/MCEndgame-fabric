// Made with Blockbench 4.12.4
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
public class suede extends EntityModel<Entity> {
	private final ModelPart body;
	private final ModelPart chestplate;
	private final ModelPart left_arm;
	private final ModelPart chestplate_left_arm;
	private final ModelPart right_arm;
	private final ModelPart chestplate_right_arm;
	public suede(ModelPart root) {
		this.body = root.getChild("body");
		this.chestplate = this.body.getChild("chestplate");
		this.left_arm = root.getChild("left_arm");
		this.chestplate_left_arm = this.left_arm.getChild("chestplate_left_arm");
		this.right_arm = root.getChild("right_arm");
		this.chestplate_right_arm = this.right_arm.getChild("chestplate_right_arm");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData body = modelPartData.addChild("body", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData chestplate = body.addChild("chestplate", ModelPartBuilder.create().uv(21, 20).cuboid(-4.0F, 7.0F, -2.0F, 8.0F, 4.0F, 4.0F, new Dilation(0.25F))
		.uv(19, 9).cuboid(-4.5F, 2.0F, -2.5F, 9.0F, 5.0F, 5.0F, new Dilation(0.0F))
		.uv(21, 0).cuboid(-3.5F, -0.25F, -2.25F, 7.0F, 3.0F, 5.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData left_arm = modelPartData.addChild("left_arm", ModelPartBuilder.create(), ModelTransform.pivot(5.0F, 2.0F, 0.0F));

		ModelPartData chestplate_left_arm = left_arm.addChild("chestplate_left_arm", ModelPartBuilder.create().uv(48, 8).cuboid(-1.0F, 0.0F, -2.0F, 4.0F, 7.0F, 4.0F, new Dilation(0.1F))
		.uv(46, 0).cuboid(-1.5F, 0.5F, -2.5F, 5.0F, 3.0F, 5.0F, new Dilation(-0.25F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData right_arm = modelPartData.addChild("right_arm", ModelPartBuilder.create(), ModelTransform.pivot(-5.0F, 2.0F, 0.0F));

		ModelPartData chestplate_right_arm = right_arm.addChild("chestplate_right_arm", ModelPartBuilder.create().uv(2, 8).cuboid(-3.0F, 0.0F, -2.0F, 4.0F, 7.0F, 4.0F, new Dilation(0.1F))
		.uv(0, 0).cuboid(-3.5F, 0.5F, -2.5F, 5.0F, 3.0F, 5.0F, new Dilation(-0.25F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
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
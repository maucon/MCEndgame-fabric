// Made with Blockbench 5.0.2
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
public class template extends EntityModel<Entity> {
	private final ModelPart body;
	private final ModelPart chestplate;
	private final ModelPart left_arm;
	private final ModelPart chestplate_left_arm;
	private final ModelPart right_arm;
	private final ModelPart chestplate_right_arm;
	public template(ModelPart root) {
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

		ModelPartData chestplate = body.addChild("chestplate", ModelPartBuilder.create().uv(19, 13).cuboid(-4.0F, 2.5F, -2.0F, 8.0F, 8.0F, 4.0F, new Dilation(0.2F))
		.uv(17, 0).cuboid(-4.5F, -0.5F, -2.6F, 9.0F, 7.0F, 5.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData left_arm = modelPartData.addChild("left_arm", ModelPartBuilder.create(), ModelTransform.pivot(5.0F, 2.0F, 0.0F));

		ModelPartData chestplate_left_arm = left_arm.addChild("chestplate_left_arm", ModelPartBuilder.create().uv(46, 12).cuboid(-1.0F, -2.0F, -2.0F, 4.0F, 11.0F, 4.0F, new Dilation(0.05F))
		.uv(46, 3).cuboid(-0.85F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, new Dilation(0.3F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData right_arm = modelPartData.addChild("right_arm", ModelPartBuilder.create(), ModelTransform.pivot(-5.0F, 2.0F, 0.0F));

		ModelPartData chestplate_right_arm = right_arm.addChild("chestplate_right_arm", ModelPartBuilder.create().uv(0, 12).cuboid(-13.0F, -2.0F, -2.0F, 4.0F, 11.0F, 4.0F, new Dilation(0.05F))
		.uv(0, 3).cuboid(-13.15F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, new Dilation(0.3F)), ModelTransform.pivot(10.0F, 0.0F, 0.0F));
		return TexturedModelData.of(modelData, 64, 32);
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
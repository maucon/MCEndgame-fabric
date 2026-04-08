// Made with Blockbench 4.12.2
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
public class SwampGolem extends EntityModel<Entity> {
	private final ModelPart body;
	private final ModelPart lower_body;
	private final ModelPart upper_body;
	private final ModelPart left_arm;
	private final ModelPart upper_left_arm;
	private final ModelPart lower_left_arm;
	private final ModelPart right_arm;
	private final ModelPart upper_right_arm;
	private final ModelPart lower_right_arm;
	private final ModelPart head;
	private final ModelPart left_leg;
	private final ModelPart upper_left_leg;
	private final ModelPart lower_left_leg;
	private final ModelPart right_leg;
	private final ModelPart upper_right_leg;
	private final ModelPart lower_right_leg;
	public SwampGolem(ModelPart root) {
		this.body = root.getChild("body");
		this.lower_body = this.body.getChild("lower_body");
		this.upper_body = this.lower_body.getChild("upper_body");
		this.left_arm = this.upper_body.getChild("left_arm");
		this.upper_left_arm = this.left_arm.getChild("upper_left_arm");
		this.lower_left_arm = this.upper_left_arm.getChild("lower_left_arm");
		this.right_arm = this.upper_body.getChild("right_arm");
		this.upper_right_arm = this.right_arm.getChild("upper_right_arm");
		this.lower_right_arm = this.upper_right_arm.getChild("lower_right_arm");
		this.head = this.upper_body.getChild("head");
		this.left_leg = this.lower_body.getChild("left_leg");
		this.upper_left_leg = this.left_leg.getChild("upper_left_leg");
		this.lower_left_leg = this.upper_left_leg.getChild("lower_left_leg");
		this.right_leg = this.lower_body.getChild("right_leg");
		this.upper_right_leg = this.right_leg.getChild("upper_right_leg");
		this.lower_right_leg = this.upper_right_leg.getChild("lower_right_leg");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData body = modelPartData.addChild("body", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 10.0F, 2.75F));

		ModelPartData lower_body = body.addChild("lower_body", ModelPartBuilder.create().uv(46, 65).cuboid(-5.5F, -8.0F, -2.5F, 11.0F, 8.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 1.5F, 0.0F, 0.0436F, 0.0F, 0.0F));

		ModelPartData upper_body = lower_body.addChild("upper_body", ModelPartBuilder.create().uv(42, 50).cuboid(-6.5F, -7.5F, -4.0F, 13.0F, 8.0F, 7.0F, new Dilation(0.0F))
		.uv(42, 34).cuboid(-6.5F, -7.5F, -4.0F, 13.0F, 9.0F, 7.0F, new Dilation(0.25F)), ModelTransform.of(0.0F, -7.0F, 0.0F, 0.3054F, 0.0F, 0.0F));

		ModelPartData left_arm = upper_body.addChild("left_arm", ModelPartBuilder.create(), ModelTransform.pivot(6.75F, -6.25F, 0.15F));

		ModelPartData upper_left_arm = left_arm.addChild("upper_left_arm", ModelPartBuilder.create().uv(83, 52).cuboid(-0.5198F, -0.9918F, -2.7492F, 4.0F, 8.0F, 5.0F, new Dilation(0.0F))
		.uv(83, 38).cuboid(-0.5198F, -0.9918F, -2.7492F, 4.0F, 9.0F, 5.0F, new Dilation(0.25F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -0.0869F, -0.0076F, -0.0869F));

		ModelPartData lower_left_arm = upper_left_arm.addChild("lower_left_arm", ModelPartBuilder.create().uv(85, 65).cuboid(-1.5F, 0.0F, -4.0F, 3.0F, 10.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(1.2302F, 7.0082F, 2.2508F, -0.7854F, 0.0F, 0.0F));

		ModelPartData right_arm = upper_body.addChild("right_arm", ModelPartBuilder.create(), ModelTransform.pivot(-6.75F, -6.25F, 0.15F));

		ModelPartData upper_right_arm = right_arm.addChild("upper_right_arm", ModelPartBuilder.create().uv(23, 52).cuboid(-3.4802F, -0.9918F, -2.7492F, 4.0F, 8.0F, 5.0F, new Dilation(0.0F))
		.uv(23, 38).cuboid(-3.4802F, -0.9918F, -2.7492F, 4.0F, 9.0F, 5.0F, new Dilation(0.25F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -0.0869F, 0.0076F, 0.0869F));

		ModelPartData lower_right_arm = upper_right_arm.addChild("lower_right_arm", ModelPartBuilder.create().uv(25, 65).cuboid(-1.5F, 0.0F, -4.0F, 3.0F, 10.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(-1.2302F, 7.0082F, 2.2508F, -0.7854F, 0.0F, 0.0F));

		ModelPartData head = upper_body.addChild("head", ModelPartBuilder.create().uv(46, 18).cuboid(-4.0F, -5.5F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.0F))
		.uv(46, 0).cuboid(-4.0F, -5.5F, -4.0F, 8.0F, 10.0F, 8.0F, new Dilation(0.25F)), ModelTransform.of(0.0F, -7.607F, -1.9999F, -0.3054F, 0.0F, 0.0F));

		ModelPartData left_leg = lower_body.addChild("left_leg", ModelPartBuilder.create(), ModelTransform.pivot(3.5F, -0.5F, 0.0F));

		ModelPartData upper_left_leg = left_leg.addChild("upper_left_leg", ModelPartBuilder.create().uv(63, 93).cuboid(-2.25F, -1.75F, -3.25F, 5.0F, 9.0F, 5.0F, new Dilation(0.0F))
		.uv(63, 79).cuboid(-2.25F, -1.75F, -3.25F, 5.0F, 9.0F, 5.0F, new Dilation(0.25F)), ModelTransform.of(-0.5F, 1.0F, 0.0F, -0.6526F, -0.0617F, -0.0618F));

		ModelPartData lower_left_leg = upper_left_leg.addChild("lower_left_leg", ModelPartBuilder.create().uv(66, 107).cuboid(-2.0F, -0.2358F, -0.039F, 4.0F, 10.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(0.25F, 7.0F, -3.0F, 0.8727F, 0.0F, 0.0F));

		ModelPartData right_leg = lower_body.addChild("right_leg", ModelPartBuilder.create(), ModelTransform.pivot(-3.5F, -0.5F, 0.0F));

		ModelPartData upper_right_leg = right_leg.addChild("upper_right_leg", ModelPartBuilder.create().uv(41, 93).mirrored().cuboid(-2.75F, -1.75F, -3.25F, 5.0F, 9.0F, 5.0F, new Dilation(0.0F)).mirrored(false)
		.uv(41, 79).mirrored().cuboid(-2.75F, -1.75F, -3.25F, 5.0F, 9.0F, 5.0F, new Dilation(0.25F)).mirrored(false), ModelTransform.of(0.5F, 1.0F, 0.0F, -0.6526F, 0.0617F, 0.0618F));

		ModelPartData lower_right_leg = upper_right_leg.addChild("lower_right_leg", ModelPartBuilder.create().uv(43, 107).mirrored().cuboid(-1.75F, -0.2358F, -0.039F, 4.0F, 10.0F, 4.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(-0.5F, 7.0F, -3.0F, 0.8727F, 0.0F, 0.0F));
		return TexturedModelData.of(modelData, 128, 128);
	}
	@Override
	public void setAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}
	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		body.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}
}
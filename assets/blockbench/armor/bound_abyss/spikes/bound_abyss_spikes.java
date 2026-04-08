// Made with Blockbench 4.12.2
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
public class xaya_body_armor extends EntityModel<Entity> {
	private final ModelPart body;
	private final ModelPart chestplateBody;
	private final ModelPart belt;
	private final ModelPart left_arm;
	private final ModelPart chestplateArmLeft;
	private final ModelPart shoulderPadLeft;
	private final ModelPart topSpikeLeft;
	private final ModelPart sideSpikeLeft;
	private final ModelPart sleeveLeft;
	private final ModelPart vambraceLeft;
	private final ModelPart vambraceSpikeLeft;
	private final ModelPart right_arm;
	private final ModelPart chestplateArmRight;
	private final ModelPart shoulderPadRight;
	private final ModelPart topSpikeRight;
	private final ModelPart sideSpikeRight;
	private final ModelPart sleeveRight;
	private final ModelPart vambraceRight;
	private final ModelPart vambraceSpikeRight;
	public xaya_body_armor(ModelPart root) {
		this.body = root.getChild("body");
		this.chestplateBody = this.body.getChild("chestplateBody");
		this.belt = this.chestplateBody.getChild("belt");
		this.left_arm = root.getChild("left_arm");
		this.chestplateArmLeft = this.left_arm.getChild("chestplateArmLeft");
		this.shoulderPadLeft = this.chestplateArmLeft.getChild("shoulderPadLeft");
		this.topSpikeLeft = this.shoulderPadLeft.getChild("topSpikeLeft");
		this.sideSpikeLeft = this.shoulderPadLeft.getChild("sideSpikeLeft");
		this.sleeveLeft = this.chestplateArmLeft.getChild("sleeveLeft");
		this.vambraceLeft = this.sleeveLeft.getChild("vambraceLeft");
		this.vambraceSpikeLeft = this.vambraceLeft.getChild("vambraceSpikeLeft");
		this.right_arm = root.getChild("right_arm");
		this.chestplateArmRight = this.right_arm.getChild("chestplateArmRight");
		this.shoulderPadRight = this.chestplateArmRight.getChild("shoulderPadRight");
		this.topSpikeRight = this.shoulderPadRight.getChild("topSpikeRight");
		this.sideSpikeRight = this.shoulderPadRight.getChild("sideSpikeRight");
		this.sleeveRight = this.chestplateArmRight.getChild("sleeveRight");
		this.vambraceRight = this.sleeveRight.getChild("vambraceRight");
		this.vambraceSpikeRight = this.vambraceRight.getChild("vambraceSpikeRight");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData body = modelPartData.addChild("body", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData chestplateBody = body.addChild("chestplateBody", ModelPartBuilder.create().uv(18, 35).cuboid(-4.5F, -24.5F, -2.5F, 9.0F, 14.0F, 5.0F, new Dilation(0.25F))
		.uv(16, 22).cuboid(-5.0F, -24.85F, -3.0F, 10.0F, 7.0F, 6.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		ModelPartData belt = chestplateBody.addChild("belt", ModelPartBuilder.create().uv(14, 54).cuboid(-5.5F, -2.0F, -3.5F, 11.0F, 3.0F, 7.0F, new Dilation(-0.5F)), ModelTransform.of(0.0F, -15.25F, 0.0F, 0.0F, 0.0F, -0.0873F));

		ModelPartData left_arm = modelPartData.addChild("left_arm", ModelPartBuilder.create(), ModelTransform.pivot(5.0F, 2.0F, 0.0F));

		ModelPartData chestplateArmLeft = left_arm.addChild("chestplateArmLeft", ModelPartBuilder.create(), ModelTransform.pivot(-5.0F, 22.0F, 0.0F));

		ModelPartData shoulderPadLeft = chestplateArmLeft.addChild("shoulderPadLeft", ModelPartBuilder.create().uv(44, 18).cuboid(-3.0F, -2.0F, -2.5F, 5.0F, 5.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(7.0F, -23.0F, 0.0F, 0.0436F, 0.0436F, 0.1309F));

		ModelPartData topSpikeLeft = shoulderPadLeft.addChild("topSpikeLeft", ModelPartBuilder.create().uv(48, 13).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 3.0F, 2.0F, new Dilation(0.0F))
		.uv(50, 10).cuboid(-0.5F, -4.0F, -0.5F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -2.0F, 0.0F, 0.0F, 0.0F, 0.3927F));

		ModelPartData sideSpikeLeft = shoulderPadLeft.addChild("sideSpikeLeft", ModelPartBuilder.create().uv(56, 14).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F))
		.uv(58, 11).cuboid(-0.5F, -4.0F, -0.5F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(2.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

		ModelPartData sleeveLeft = chestplateArmLeft.addChild("sleeveLeft", ModelPartBuilder.create().uv(48, 30).cuboid(4.0F, -24.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.35F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData vambraceLeft = sleeveLeft.addChild("vambraceLeft", ModelPartBuilder.create().uv(48, 46).cuboid(4.0F, -18.0F, -2.0F, 4.0F, 6.0F, 4.0F, new Dilation(0.5F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData vambraceSpikeLeft = vambraceLeft.addChild("vambraceSpikeLeft", ModelPartBuilder.create().uv(53, 59).cuboid(-1.25F, -3.25F, -1.0F, 1.0F, 3.0F, 2.0F, new Dilation(0.25F))
		.uv(54, 56).cuboid(-1.25F, -5.0F, -0.5F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(8.5F, -13.75F, 0.0F, 0.0F, 0.0F, 0.3927F));

		ModelPartData right_arm = modelPartData.addChild("right_arm", ModelPartBuilder.create(), ModelTransform.pivot(-5.0F, 2.0F, 0.0F));

		ModelPartData chestplateArmRight = right_arm.addChild("chestplateArmRight", ModelPartBuilder.create(), ModelTransform.pivot(5.0F, 22.0F, 0.0F));

		ModelPartData shoulderPadRight = chestplateArmRight.addChild("shoulderPadRight", ModelPartBuilder.create().uv(0, 18).cuboid(-2.0F, -2.0F, -2.5F, 5.0F, 5.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(-7.0F, -23.0F, 0.0F, 0.0436F, -0.0436F, -0.1309F));

		ModelPartData topSpikeRight = shoulderPadRight.addChild("topSpikeRight", ModelPartBuilder.create().uv(8, 13).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 3.0F, 2.0F, new Dilation(0.0F))
		.uv(10, 10).mirrored().cuboid(-0.5F, -4.0F, -0.5F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(0.0F, -2.0F, 0.0F, 0.0F, 0.0F, -0.3927F));

		ModelPartData sideSpikeRight = shoulderPadRight.addChild("sideSpikeRight", ModelPartBuilder.create().uv(0, 14).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F))
		.uv(2, 11).cuboid(-0.5F, -4.0F, -0.5F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-2.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.5708F));

		ModelPartData sleeveRight = chestplateArmRight.addChild("sleeveRight", ModelPartBuilder.create().uv(0, 30).cuboid(-8.0F, -24.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.35F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData vambraceRight = sleeveRight.addChild("vambraceRight", ModelPartBuilder.create().uv(0, 46).cuboid(-8.0F, -18.0F, -2.0F, 4.0F, 6.0F, 4.0F, new Dilation(0.5F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData vambraceSpikeRight = vambraceRight.addChild("vambraceSpikeRight", ModelPartBuilder.create().uv(5, 59).cuboid(0.25F, -3.25F, -1.0F, 1.0F, 3.0F, 2.0F, new Dilation(0.25F))
		.uv(6, 56).cuboid(0.25F, -5.0F, -0.5F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-8.5F, -13.75F, 0.0F, 0.0F, 0.0F, -0.3927F));
		return TexturedModelData.of(modelData, 64, 64);
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
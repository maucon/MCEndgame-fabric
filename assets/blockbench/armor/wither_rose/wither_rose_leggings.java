// Made with Blockbench 4.12.4
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
public class solar_eclipse_leona extends EntityModel<Entity> {
	private final ModelPart body;
	private final ModelPart leggings_waist;
	private final ModelPart skirt;
	private final ModelPart skirtBackRight;
	private final ModelPart skirtBackLeft;
	private final ModelPart skirtLeft;
	private final ModelPart skirtRight;
	private final ModelPart skirtFrontRight;
	private final ModelPart skirtFrontLeft;
	private final ModelPart beltLeft;
	private final ModelPart beltRight;
	private final ModelPart beltFront;
	private final ModelPart left_leg;
	private final ModelPart left_leggings;
	private final ModelPart right_leg;
	private final ModelPart right_leggings;
	public solar_eclipse_leona(ModelPart root) {
		this.body = root.getChild("body");
		this.leggings_waist = this.body.getChild("leggings_waist");
		this.skirt = this.leggings_waist.getChild("skirt");
		this.skirtBackRight = this.skirt.getChild("skirtBackRight");
		this.skirtBackLeft = this.skirt.getChild("skirtBackLeft");
		this.skirtLeft = this.skirt.getChild("skirtLeft");
		this.skirtRight = this.skirt.getChild("skirtRight");
		this.skirtFrontRight = this.skirt.getChild("skirtFrontRight");
		this.skirtFrontLeft = this.skirt.getChild("skirtFrontLeft");
		this.beltLeft = this.skirt.getChild("beltLeft");
		this.beltRight = this.skirt.getChild("beltRight");
		this.beltFront = this.skirt.getChild("beltFront");
		this.left_leg = root.getChild("left_leg");
		this.left_leggings = this.left_leg.getChild("left_leggings");
		this.right_leg = root.getChild("right_leg");
		this.right_leggings = this.right_leg.getChild("right_leggings");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData body = modelPartData.addChild("body", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData leggings_waist = body.addChild("leggings_waist", ModelPartBuilder.create().uv(9, 92).cuboid(-4.0F, 8.0F, -2.0F, 8.0F, 4.0F, 4.0F, new Dilation(0.05F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData skirt = leggings_waist.addChild("skirt", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.25F, 0.0F));

		ModelPartData skirtBackRight = skirt.addChild("skirtBackRight", ModelPartBuilder.create().uv(0, 79).cuboid(0.0F, 0.0F, 0.0F, 4.0F, 12.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-4.25F, 8.0F, 3.1F, 0.0872F, -0.0038F, 0.0435F));

		ModelPartData skirtBackLeft = skirt.addChild("skirtBackLeft", ModelPartBuilder.create().uv(43, 79).cuboid(-4.0F, 0.0F, 0.0F, 4.0F, 12.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(4.25F, 8.0F, 3.1F, 0.0872F, 0.0038F, -0.0435F));

		ModelPartData skirtLeft = skirt.addChild("skirtLeft", ModelPartBuilder.create().uv(34, 75).cuboid(0.0F, 0.0F, 0.0F, 0.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(5.1F, 8.0F, -2.0F, 0.0436F, 0.0F, -0.0436F));

		ModelPartData skirtRight = skirt.addChild("skirtRight", ModelPartBuilder.create().uv(9, 75).cuboid(0.0F, 0.0F, 0.0F, 0.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(-5.1F, 8.0F, -2.0F, 0.0436F, 0.0F, 0.0436F));

		ModelPartData skirtFrontRight = skirt.addChild("skirtFrontRight", ModelPartBuilder.create().uv(18, 79).cuboid(0.0F, 0.0F, 0.0F, 2.0F, 12.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-4.25F, 8.0F, -3.1F, -0.0436F, 0.0019F, 0.0436F));

		ModelPartData skirtFrontLeft = skirt.addChild("skirtFrontLeft", ModelPartBuilder.create().uv(23, 86).cuboid(-5.0F, 0.0F, 0.0F, 5.0F, 5.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(4.25F, 8.0F, -3.1F, -0.0436F, -0.0019F, -0.0436F));

		ModelPartData beltLeft = skirt.addChild("beltLeft", ModelPartBuilder.create().uv(25, 66).cuboid(-6.75F, -0.25F, -3.45F, 7.0F, 3.0F, 7.0F, new Dilation(-0.25F)), ModelTransform.of(5.25F, 7.25F, 0.0F, 0.0F, 0.0F, -0.0873F));

		ModelPartData beltRight = skirt.addChild("beltRight", ModelPartBuilder.create().uv(0, 68).cuboid(-0.25F, -0.25F, -3.5F, 5.0F, 3.0F, 7.0F, new Dilation(-0.25F)), ModelTransform.of(-5.25F, 7.25F, 0.0F, 0.0F, 0.0F, 0.1309F));

		ModelPartData beltFront = skirt.addChild("beltFront", ModelPartBuilder.create().uv(24, 76).cuboid(-1.5F, -0.5F, -0.5F, 3.0F, 8.0F, 1.0F, new Dilation(-0.5F)), ModelTransform.of(-1.0F, 7.6F, -3.35F, -0.0436F, 0.0019F, 0.0436F));

		ModelPartData left_leg = modelPartData.addChild("left_leg", ModelPartBuilder.create(), ModelTransform.pivot(2.0F, 12.0F, 0.0F));

		ModelPartData left_leggings = left_leg.addChild("left_leggings", ModelPartBuilder.create().uv(22, 101).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 10.0F, 4.0F, new Dilation(0.05F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData right_leg = modelPartData.addChild("right_leg", ModelPartBuilder.create(), ModelTransform.pivot(-2.0F, 12.0F, 0.0F));

		ModelPartData right_leggings = right_leg.addChild("right_leggings", ModelPartBuilder.create().uv(4, 101).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 10.0F, 4.0F, new Dilation(0.05F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
		return TexturedModelData.of(modelData, 128, 128);
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
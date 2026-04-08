// Made with Blockbench 4.12.4
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
public class lamias_gift extends EntityModel<Entity> {
	private final ModelPart body;
	private final ModelPart leggings;
	private final ModelPart nagaTailZero;
	private final ModelPart nagaTailOne;
	private final ModelPart nagaTailTwo;
	private final ModelPart nagaTailThree;
	private final ModelPart nagaTailFour;
	private final ModelPart nagaTailFive;
	public lamias_gift(ModelPart root) {
		this.body = root.getChild("body");
		this.leggings = this.body.getChild("leggings");
		this.nagaTailZero = this.leggings.getChild("nagaTailZero");
		this.nagaTailOne = this.nagaTailZero.getChild("nagaTailOne");
		this.nagaTailTwo = this.nagaTailOne.getChild("nagaTailTwo");
		this.nagaTailThree = this.nagaTailTwo.getChild("nagaTailThree");
		this.nagaTailFour = this.nagaTailThree.getChild("nagaTailFour");
		this.nagaTailFive = this.nagaTailFour.getChild("nagaTailFive");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData body = modelPartData.addChild("body", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		ModelPartData leggings = body.addChild("leggings", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData nagaTailZero = leggings.addChild("nagaTailZero", ModelPartBuilder.create().uv(2, 1).cuboid(-4.5F, 0.0F, -3.0F, 9.0F, 10.0F, 5.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -15.75F, 0.5F));

		ModelPartData nagaTailOne = nagaTailZero.addChild("nagaTailOne", ModelPartBuilder.create().uv(3, 16).cuboid(-4.0F, -1.2145F, -2.6213F, 8.0F, 8.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 9.0F, -0.25F, 0.7854F, 0.0F, 0.0F));

		ModelPartData nagaTailTwo = nagaTailOne.addChild("nagaTailTwo", ModelPartBuilder.create().uv(6, 30).cuboid(-3.0F, -0.7951F, -2.2981F, 6.0F, 7.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 5.7855F, -0.3713F, 0.7854F, 0.0F, 0.0F));

		ModelPartData nagaTailThree = nagaTailTwo.addChild("nagaTailThree", ModelPartBuilder.create().uv(9, 42).cuboid(-2.0F, -0.5F, -1.5006F, 4.0F, 5.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 6.205F, -0.7981F));

		ModelPartData nagaTailFour = nagaTailThree.addChild("nagaTailFour", ModelPartBuilder.create().uv(11, 51).cuboid(-1.5F, -0.5F, -1.0056F, 3.0F, 5.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 4.5F, -0.5F));

		ModelPartData nagaTailFive = nagaTailFour.addChild("nagaTailFive", ModelPartBuilder.create().uv(12, 58).cuboid(-1.0F, -0.5F, -0.6806F, 2.0F, 4.0F, 2.0F, new Dilation(-0.25F)), ModelTransform.pivot(0.0F, 4.5F, -0.5F));
		return TexturedModelData.of(modelData, 32, 64);
	}
	@Override
	public void setAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}
	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		body.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}
}
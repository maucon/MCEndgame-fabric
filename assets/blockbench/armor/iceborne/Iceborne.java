// Made with Blockbench 4.12.2
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
public class iceborne extends EntityModel<Entity> {
	private final ModelPart helmet;
	private final ModelPart left_horn;
	private final ModelPart left_horn_0;
	private final ModelPart left_horn_1;
	private final ModelPart left_horn_2;
	private final ModelPart left_horn_3;
	private final ModelPart left_horn_4;
	private final ModelPart left_horn_5;
	private final ModelPart left_horn_6;
	private final ModelPart right_horn;
	private final ModelPart right_horn_0;
	private final ModelPart right_horn_1;
	private final ModelPart right_horn_2;
	private final ModelPart right_horn_3;
	private final ModelPart right_horn_4;
	private final ModelPart right_horn_5;
	private final ModelPart right_horn_6;
	public iceborne(ModelPart root) {
		this.helmet = root.getChild("helmet");
		this.left_horn = this.helmet.getChild("left_horn");
		this.left_horn_0 = this.left_horn.getChild("left_horn_0");
		this.left_horn_1 = this.left_horn_0.getChild("left_horn_1");
		this.left_horn_2 = this.left_horn_1.getChild("left_horn_2");
		this.left_horn_3 = this.left_horn_2.getChild("left_horn_3");
		this.left_horn_4 = this.left_horn_3.getChild("left_horn_4");
		this.left_horn_5 = this.left_horn_4.getChild("left_horn_5");
		this.left_horn_6 = this.left_horn_5.getChild("left_horn_6");
		this.right_horn = this.helmet.getChild("right_horn");
		this.right_horn_0 = this.right_horn.getChild("right_horn_0");
		this.right_horn_1 = this.right_horn_0.getChild("right_horn_1");
		this.right_horn_2 = this.right_horn_1.getChild("right_horn_2");
		this.right_horn_3 = this.right_horn_2.getChild("right_horn_3");
		this.right_horn_4 = this.right_horn_3.getChild("right_horn_4");
		this.right_horn_5 = this.right_horn_4.getChild("right_horn_5");
		this.right_horn_6 = this.right_horn_5.getChild("right_horn_6");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData helmet = modelPartData.addChild("helmet", ModelPartBuilder.create().uv(16, 48).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(1.0F))
		.uv(18, 31).cuboid(-1.0F, -9.75F, -6.0F, 2.0F, 4.0F, 12.0F, new Dilation(0.0F))
		.uv(22, 20).cuboid(-1.0F, -11.2F, -4.0F, 2.0F, 2.0F, 8.0F, new Dilation(-0.25F))
		.uv(29, 15).cuboid(-1.0F, -6.25F, -5.9F, 2.0F, 3.0F, 1.0F, new Dilation(-0.1F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData left_horn = helmet.addChild("left_horn", ModelPartBuilder.create().uv(44, 0).cuboid(-2.0F, -3.0F, -3.0F, 4.0F, 6.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(4.5F, -6.25F, 1.0F, 0.0F, 0.0F, -0.1309F));

		ModelPartData left_horn_0 = left_horn.addChild("left_horn_0", ModelPartBuilder.create().uv(46, 13).cuboid(-1.25F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(2.0F, 0.0F, 0.0F, 0.0F, 0.0873F, -0.0873F));

		ModelPartData left_horn_1 = left_horn_0.addChild("left_horn_1", ModelPartBuilder.create().uv(47, 22).cuboid(-1.5F, -1.5F, -1.5F, 4.0F, 3.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(2.75F, -0.2F, -0.1F, -0.0669F, 0.3431F, -0.5344F));

		ModelPartData left_horn_2 = left_horn_1.addChild("left_horn_2", ModelPartBuilder.create().uv(47, 29).cuboid(-1.05F, -1.25F, -1.5F, 4.0F, 3.0F, 3.0F, new Dilation(-0.125F)), ModelTransform.of(2.25F, -0.5F, -0.25F, 0.0F, 0.2618F, -0.6109F));

		ModelPartData left_horn_3 = left_horn_2.addChild("left_horn_3", ModelPartBuilder.create().uv(49, 36).cuboid(-0.6F, -1.05F, -1.1F, 3.0F, 2.0F, 2.0F, new Dilation(0.1F)), ModelTransform.of(2.8F, 0.25F, 0.0F, -0.0832F, -0.1882F, -0.4456F));

		ModelPartData left_horn_4 = left_horn_3.addChild("left_horn_4", ModelPartBuilder.create().uv(49, 41).cuboid(-1.0F, -1.1F, -1.0F, 3.0F, 2.0F, 2.0F, new Dilation(-0.15F)), ModelTransform.of(2.4F, 0.0F, -0.1F, 0.0F, 0.0F, -0.3491F));

		ModelPartData left_horn_5 = left_horn_4.addChild("left_horn_5", ModelPartBuilder.create().uv(49, 46).cuboid(-1.0F, -0.9F, -1.0F, 3.0F, 2.0F, 2.0F, new Dilation(-0.35F)), ModelTransform.of(1.8F, -0.2F, 0.0F, -0.0873F, -0.0873F, 0.1745F));

		ModelPartData left_horn_6 = left_horn_5.addChild("left_horn_6", ModelPartBuilder.create().uv(51, 51).cuboid(-0.2F, -0.4F, -0.5F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(1.6F, 0.1F, 0.0F, -0.3054F, -0.1745F, 0.2618F));

		ModelPartData right_horn = helmet.addChild("right_horn", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -3.0F, -3.0F, 4.0F, 6.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(-4.5F, -6.25F, 1.0F, 0.0F, 0.0F, 0.1309F));

		ModelPartData right_horn_0 = right_horn.addChild("right_horn_0", ModelPartBuilder.create().uv(2, 13).cuboid(-2.75F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(-2.0F, 0.0F, 0.0F, 0.0F, -0.0873F, 0.0873F));

		ModelPartData right_horn_1 = right_horn_0.addChild("right_horn_1", ModelPartBuilder.create().uv(3, 22).cuboid(-2.5F, -1.5F, -1.5F, 4.0F, 3.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(-2.75F, -0.2F, -0.1F, -0.0669F, -0.3431F, 0.5344F));

		ModelPartData right_horn_2 = right_horn_1.addChild("right_horn_2", ModelPartBuilder.create().uv(3, 29).cuboid(-2.95F, -1.25F, -1.5F, 4.0F, 3.0F, 3.0F, new Dilation(-0.125F)), ModelTransform.of(-2.25F, -0.5F, -0.25F, 0.0F, -0.2618F, 0.6109F));

		ModelPartData right_horn_3 = right_horn_2.addChild("right_horn_3", ModelPartBuilder.create().uv(5, 36).cuboid(-2.4F, -1.05F, -1.1F, 3.0F, 2.0F, 2.0F, new Dilation(0.1F)), ModelTransform.of(-2.8F, 0.25F, 0.0F, -0.0832F, 0.1882F, 0.4456F));

		ModelPartData right_horn_4 = right_horn_3.addChild("right_horn_4", ModelPartBuilder.create().uv(5, 41).cuboid(-2.0F, -1.1F, -1.0F, 3.0F, 2.0F, 2.0F, new Dilation(-0.15F)), ModelTransform.of(-2.4F, 0.0F, -0.1F, 0.0F, 0.0F, 0.3491F));

		ModelPartData right_horn_5 = right_horn_4.addChild("right_horn_5", ModelPartBuilder.create().uv(5, 46).cuboid(-2.0F, -0.9F, -1.0F, 3.0F, 2.0F, 2.0F, new Dilation(-0.35F)), ModelTransform.of(-1.8F, -0.2F, 0.0F, -0.0873F, 0.0873F, -0.1745F));

		ModelPartData right_horn_6 = right_horn_5.addChild("right_horn_6", ModelPartBuilder.create().uv(7, 51).cuboid(-1.8F, -0.4F, -0.5F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-1.6F, 0.1F, 0.0F, -0.3054F, 0.1745F, -0.2618F));
		return TexturedModelData.of(modelData, 64, 64);
	}
	@Override
	public void setAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}
	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		helmet.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}
}
// Made with Blockbench 4.12.2
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
public class solar_eclipse_leona extends EntityModel<Entity> {
	private final ModelPart head;
	private final ModelPart helmet;
	private final ModelPart crystal;
	private final ModelPart helmet_horn_left_base;
	private final ModelPart helmet_horn_left_0;
	private final ModelPart helmet_horn_left_1;
	private final ModelPart helmet_horn_left_2;
	private final ModelPart helmet_horn_left_3;
	private final ModelPart helmet_horn_right_base;
	private final ModelPart helmet_horn_right_0;
	private final ModelPart helmet_horn_right_1;
	private final ModelPart helmet_horn_right_2;
	private final ModelPart helmet_horn_right_3;
	public solar_eclipse_leona(ModelPart root) {
		this.head = root.getChild("head");
		this.helmet = this.head.getChild("helmet");
		this.crystal = this.helmet.getChild("crystal");
		this.helmet_horn_left_base = this.helmet.getChild("helmet_horn_left_base");
		this.helmet_horn_left_0 = this.helmet_horn_left_base.getChild("helmet_horn_left_0");
		this.helmet_horn_left_1 = this.helmet_horn_left_0.getChild("helmet_horn_left_1");
		this.helmet_horn_left_2 = this.helmet_horn_left_1.getChild("helmet_horn_left_2");
		this.helmet_horn_left_3 = this.helmet_horn_left_2.getChild("helmet_horn_left_3");
		this.helmet_horn_right_base = this.helmet.getChild("helmet_horn_right_base");
		this.helmet_horn_right_0 = this.helmet_horn_right_base.getChild("helmet_horn_right_0");
		this.helmet_horn_right_1 = this.helmet_horn_right_0.getChild("helmet_horn_right_1");
		this.helmet_horn_right_2 = this.helmet_horn_right_1.getChild("helmet_horn_right_2");
		this.helmet_horn_right_3 = this.helmet_horn_right_2.getChild("helmet_horn_right_3");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData head = modelPartData.addChild("head", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData helmet = head.addChild("helmet", ModelPartBuilder.create().uv(38, 73).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.6F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData crystal = helmet.addChild("crystal", ModelPartBuilder.create().uv(36, 76).cuboid(-3.0F, -3.0F, -2.0F, 3.0F, 3.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -5.5F, -3.0F, 0.0F, 0.0F, 0.7854F));

		ModelPartData helmet_horn_left_base = helmet.addChild("helmet_horn_left_base", ModelPartBuilder.create().uv(70, 83).cuboid(0.0F, -3.0F, -1.5F, 1.0F, 3.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(4.5F, -4.75F, 2.0F, 0.2182F, 0.0F, -0.0873F));

		ModelPartData helmet_horn_left_0 = helmet_horn_left_base.addChild("helmet_horn_left_0", ModelPartBuilder.create().uv(78, 85).cuboid(0.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, -0.5F, 0.0F, 0.0F, 0.0F, -0.4363F));

		ModelPartData helmet_horn_left_1 = helmet_horn_left_0.addChild("helmet_horn_left_1", ModelPartBuilder.create().uv(86, 85).cuboid(-0.25F, -1.75F, -1.0F, 2.0F, 2.0F, 2.0F, new Dilation(-0.25F)), ModelTransform.of(2.0F, -0.25F, 0.0F, 0.0F, 0.0F, -0.4363F));

		ModelPartData helmet_horn_left_2 = helmet_horn_left_1.addChild("helmet_horn_left_2", ModelPartBuilder.create().uv(94, 85).cuboid(-0.5F, -1.5F, -1.0F, 2.0F, 2.0F, 2.0F, new Dilation(-0.5F)), ModelTransform.of(1.5F, -0.25F, 0.0F, 0.0F, 0.0F, -0.4363F));

		ModelPartData helmet_horn_left_3 = helmet_horn_left_2.addChild("helmet_horn_left_3", ModelPartBuilder.create().uv(102, 87).cuboid(-0.5F, -0.75F, -0.5F, 2.0F, 1.0F, 1.0F, new Dilation(-0.25F)), ModelTransform.of(0.5F, -0.15F, 0.0F, 0.0F, 0.0F, -0.1309F));

		ModelPartData helmet_horn_right_base = helmet.addChild("helmet_horn_right_base", ModelPartBuilder.create().uv(30, 83).cuboid(-1.0F, -3.0F, -1.5F, 1.0F, 3.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(-4.5F, -4.75F, 2.0F, 0.2182F, 0.0F, 0.0873F));

		ModelPartData helmet_horn_right_0 = helmet_horn_right_base.addChild("helmet_horn_right_0", ModelPartBuilder.create().uv(22, 85).cuboid(-2.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-1.0F, -0.5F, 0.0F, 0.0F, 0.0F, 0.4363F));

		ModelPartData helmet_horn_right_1 = helmet_horn_right_0.addChild("helmet_horn_right_1", ModelPartBuilder.create().uv(14, 85).cuboid(-1.75F, -1.75F, -1.0F, 2.0F, 2.0F, 2.0F, new Dilation(-0.25F)), ModelTransform.of(-2.0F, -0.25F, 0.0F, 0.0F, 0.0F, 0.4363F));

		ModelPartData helmet_horn_right_2 = helmet_horn_right_1.addChild("helmet_horn_right_2", ModelPartBuilder.create().uv(6, 85).cuboid(-1.5F, -1.5F, -1.0F, 2.0F, 2.0F, 2.0F, new Dilation(-0.5F)), ModelTransform.of(-1.5F, -0.25F, 0.0F, 0.0F, 0.0F, 0.4363F));

		ModelPartData helmet_horn_right_3 = helmet_horn_right_2.addChild("helmet_horn_right_3", ModelPartBuilder.create().uv(0, 87).cuboid(-1.5F, -0.75F, -0.5F, 2.0F, 1.0F, 1.0F, new Dilation(-0.25F)), ModelTransform.of(-0.5F, -0.15F, 0.0F, 0.0F, 0.0F, 0.1309F));
		return TexturedModelData.of(modelData, 128, 128);
	}
	@Override
	public void setAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}
	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		head.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}
}
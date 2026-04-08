// Made with Blockbench 4.12.2
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
public class solar_eclipse_leona extends EntityModel<Entity> {
	private final ModelPart body;
	private final ModelPart chestplate;
	private final ModelPart chestplate_armor;
	private final ModelPart armor_bottom_front;
	private final ModelPart chestplate_base;
	private final ModelPart left_arm;
	private final ModelPart chestplate_left_arm;
	private final ModelPart shoulderpad_left;
	private final ModelPart right_arm;
	private final ModelPart chestplate_right_arm;
	private final ModelPart shoulderpad_right;
	public solar_eclipse_leona(ModelPart root) {
		this.body = root.getChild("body");
		this.chestplate = this.body.getChild("chestplate");
		this.chestplate_armor = this.chestplate.getChild("chestplate_armor");
		this.armor_bottom_front = this.chestplate_armor.getChild("armor_bottom_front");
		this.chestplate_base = this.chestplate.getChild("chestplate_base");
		this.left_arm = root.getChild("left_arm");
		this.chestplate_left_arm = this.left_arm.getChild("chestplate_left_arm");
		this.shoulderpad_left = this.chestplate_left_arm.getChild("shoulderpad_left");
		this.right_arm = root.getChild("right_arm");
		this.chestplate_right_arm = this.right_arm.getChild("chestplate_right_arm");
		this.shoulderpad_right = this.chestplate_right_arm.getChild("shoulderpad_right");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData body = modelPartData.addChild("body", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData chestplate = body.addChild("chestplate", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData chestplate_armor = chestplate.addChild("chestplate_armor", ModelPartBuilder.create().uv(40, 89).cuboid(-4.5F, -0.25F, -2.75F, 9.0F, 5.0F, 5.0F, new Dilation(0.5F))
		.uv(44, 105).cuboid(-4.5F, 4.0F, 1.2F, 9.0F, 2.0F, 1.0F, new Dilation(0.5F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData armor_bottom_front = chestplate_armor.addChild("armor_bottom_front", ModelPartBuilder.create().uv(44, 99).cuboid(-4.5F, 0.5F, 0.5F, 9.0F, 5.0F, 1.0F, new Dilation(0.5F)), ModelTransform.of(0.0F, 5.25F, -3.25F, 0.0873F, 0.0F, 0.0F));

		ModelPartData chestplate_base = chestplate.addChild("chestplate_base", ModelPartBuilder.create().uv(41, 108).cuboid(-4.5F, -0.3F, -2.75F, 9.0F, 6.0F, 5.0F, new Dilation(0.3F))
		.uv(42, 119).cuboid(-4.0F, 6.1F, -2.0F, 8.0F, 5.0F, 4.0F, new Dilation(0.51F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData left_arm = modelPartData.addChild("left_arm", ModelPartBuilder.create(), ModelTransform.pivot(5.0F, 2.0F, 0.0F));

		ModelPartData chestplate_left_arm = left_arm.addChild("chestplate_left_arm", ModelPartBuilder.create().uv(68, 91).cuboid(-1.0F, -2.0F, -2.0F, 4.0F, 6.0F, 4.0F, new Dilation(0.4F))
		.uv(68, 101).cuboid(-1.0F, 4.0F, -2.0F, 4.0F, 6.0F, 4.0F, new Dilation(0.26F))
		.uv(84, 101).cuboid(-1.5F, 4.0F, -2.5F, 5.0F, 2.0F, 5.0F, new Dilation(-0.15F))
		.uv(84, 108).cuboid(-1.5F, 6.55F, -2.5F, 5.0F, 4.0F, 5.0F, new Dilation(-0.15F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData shoulderpad_left = chestplate_left_arm.addChild("shoulderpad_left", ModelPartBuilder.create().uv(84, 93).cuboid(0.0F, 0.5F, -2.0F, 3.0F, 4.0F, 4.0F, new Dilation(0.5F)), ModelTransform.of(0.5F, -3.0F, 0.1F, 0.0F, 0.0436F, 0.0873F));

		ModelPartData right_arm = modelPartData.addChild("right_arm", ModelPartBuilder.create(), ModelTransform.pivot(-5.0F, 2.0F, 0.0F));

		ModelPartData chestplate_right_arm = right_arm.addChild("chestplate_right_arm", ModelPartBuilder.create().uv(24, 91).cuboid(-3.0F, -2.0F, -2.0F, 4.0F, 6.0F, 4.0F, new Dilation(0.4F))
		.uv(24, 101).cuboid(-3.0F, 4.0F, -2.0F, 4.0F, 6.0F, 4.0F, new Dilation(0.26F))
		.uv(4, 101).cuboid(-3.5F, 4.0F, -2.5F, 5.0F, 2.0F, 5.0F, new Dilation(-0.15F))
		.uv(4, 108).cuboid(-3.5F, 6.55F, -2.5F, 5.0F, 4.0F, 5.0F, new Dilation(-0.15F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData shoulderpad_right = chestplate_right_arm.addChild("shoulderpad_right", ModelPartBuilder.create().uv(10, 93).cuboid(-3.0F, 0.5F, -2.0F, 3.0F, 4.0F, 4.0F, new Dilation(0.5F)), ModelTransform.of(-0.5F, -3.0F, 0.1F, 0.0F, -0.0436F, -0.0873F));
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
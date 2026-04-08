// Made with Blockbench 4.12.2
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
public class solar_eclipse_leona extends EntityModel<Entity> {
	private final ModelPart body;
	private final ModelPart leggings_waist;
	private final ModelPart battle_skirt;
	private final ModelPart battle_skirt_back;
	private final ModelPart battle_skirt_left;
	private final ModelPart battle_skirt_right;
	private final ModelPart battle_skirt_front;
	private final ModelPart left_leg;
	private final ModelPart left_leggings;
	private final ModelPart right_leg;
	private final ModelPart right_leggings;
	public solar_eclipse_leona(ModelPart root) {
		this.body = root.getChild("body");
		this.leggings_waist = this.body.getChild("leggings_waist");
		this.battle_skirt = this.leggings_waist.getChild("battle_skirt");
		this.battle_skirt_back = this.battle_skirt.getChild("battle_skirt_back");
		this.battle_skirt_left = this.battle_skirt.getChild("battle_skirt_left");
		this.battle_skirt_right = this.battle_skirt.getChild("battle_skirt_right");
		this.battle_skirt_front = this.battle_skirt.getChild("battle_skirt_front");
		this.left_leg = root.getChild("left_leg");
		this.left_leggings = this.left_leg.getChild("left_leggings");
		this.right_leg = root.getChild("right_leg");
		this.right_leggings = this.right_leg.getChild("right_leggings");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData body = modelPartData.addChild("body", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData leggings_waist = body.addChild("leggings_waist", ModelPartBuilder.create().uv(5, 40).cuboid(-5.0F, 7.0F, -3.0F, 10.0F, 7.0F, 6.0F, new Dilation(0.05F))
		.uv(9, 33).cuboid(-4.0F, 9.0F, -2.0F, 8.0F, 3.0F, 4.0F, new Dilation(0.5F))
		.uv(9, 27).cuboid(-4.0F, 6.75F, -2.0F, 8.0F, 2.0F, 4.0F, new Dilation(0.26F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData battle_skirt = leggings_waist.addChild("battle_skirt", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData battle_skirt_back = battle_skirt.addChild("battle_skirt_back", ModelPartBuilder.create().uv(49, 0).cuboid(-4.0F, 0.0F, 0.0F, 8.0F, 14.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 9.5F, 2.75F, 0.0873F, 0.0F, 0.0F));

		ModelPartData battle_skirt_left = battle_skirt.addChild("battle_skirt_left", ModelPartBuilder.create().uv(43, 2).cuboid(-3.0F, -1.0218F, -0.0005F, 3.0F, 5.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(4.75F, 10.5F, 2.25F, 0.0F, -1.5708F, -0.0436F));

		ModelPartData battle_skirt_right = battle_skirt.addChild("battle_skirt_right", ModelPartBuilder.create().uv(65, 2).cuboid(0.0F, -1.0218F, -0.0005F, 3.0F, 5.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-4.75F, 10.5F, 2.25F, 0.0F, 1.5708F, 0.0436F));

		ModelPartData battle_skirt_front = battle_skirt.addChild("battle_skirt_front", ModelPartBuilder.create().uv(53, 14).cuboid(-2.0F, 0.0F, 0.0F, 4.0F, 6.0F, 0.0F, new Dilation(0.0F))
		.uv(54, 20).cuboid(-1.5F, 6.0F, 0.0F, 3.0F, 6.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 10.0F, -3.0F, -0.0436F, 0.0F, 0.0F));

		ModelPartData left_leg = modelPartData.addChild("left_leg", ModelPartBuilder.create(), ModelTransform.pivot(2.0F, 12.0F, 0.0F));

		ModelPartData left_leggings = left_leg.addChild("left_leggings", ModelPartBuilder.create().uv(24, 53).cuboid(-2.1F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.5F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData right_leg = modelPartData.addChild("right_leg", ModelPartBuilder.create(), ModelTransform.pivot(-2.0F, 12.0F, 0.0F));

		ModelPartData right_leggings = right_leg.addChild("right_leggings", ModelPartBuilder.create().uv(2, 53).cuboid(-1.9F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.5F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
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
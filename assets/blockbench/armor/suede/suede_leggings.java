// Made with Blockbench 4.12.4
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
public class suede extends EntityModel<Entity> {
	private final ModelPart body;
	private final ModelPart leggings_waist;
	private final ModelPart left_belt;
	private final ModelPart right_belt;
	private final ModelPart left_leg;
	private final ModelPart left_leggings;
	private final ModelPart left_skirt2;
	private final ModelPart right_leg;
	private final ModelPart right_leggings;
	private final ModelPart right_skirt;
	public suede(ModelPart root) {
		this.body = root.getChild("body");
		this.leggings_waist = this.body.getChild("leggings_waist");
		this.left_belt = this.leggings_waist.getChild("left_belt");
		this.right_belt = this.leggings_waist.getChild("right_belt");
		this.left_leg = root.getChild("left_leg");
		this.left_leggings = this.left_leg.getChild("left_leggings");
		this.left_skirt2 = this.left_leggings.getChild("left_skirt2");
		this.right_leg = root.getChild("right_leg");
		this.right_leggings = this.right_leg.getChild("right_leggings");
		this.right_skirt = this.right_leggings.getChild("right_skirt");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData body = modelPartData.addChild("body", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData leggings_waist = body.addChild("leggings_waist", ModelPartBuilder.create().uv(21, 82).cuboid(-4.0F, 9.0F, -2.0F, 8.0F, 3.0F, 4.0F, new Dilation(0.05F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData left_belt = leggings_waist.addChild("left_belt", ModelPartBuilder.create().uv(34, 44).cuboid(-5.6F, -0.4F, -2.95F, 6.0F, 3.0F, 6.0F, new Dilation(-0.4F)), ModelTransform.of(4.6F, 8.65F, 0.0F, 0.0F, 0.0F, -0.0873F));

		ModelPartData right_belt = leggings_waist.addChild("right_belt", ModelPartBuilder.create().uv(8, 44).cuboid(-0.4F, 0.1F, -3.05F, 6.0F, 3.0F, 6.0F, new Dilation(-0.4F)), ModelTransform.of(-4.6F, 8.15F, 0.0F, 0.0F, 0.0F, 0.0873F));

		ModelPartData left_leg = modelPartData.addChild("left_leg", ModelPartBuilder.create(), ModelTransform.pivot(2.0F, 12.0F, 0.0F));

		ModelPartData left_leggings = left_leg.addChild("left_leggings", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData left_skirt2 = left_leggings.addChild("left_skirt2", ModelPartBuilder.create().uv(36, 54).cuboid(-5.1F, 1.1F, -2.45F, 5.0F, 7.0F, 5.0F, new Dilation(-0.1F)), ModelTransform.of(2.6F, -3.35F, 0.0F, 0.0F, 0.0F, -0.0436F));

		ModelPartData right_leg = modelPartData.addChild("right_leg", ModelPartBuilder.create(), ModelTransform.pivot(-2.0F, 12.0F, 0.0F));

		ModelPartData right_leggings = right_leg.addChild("right_leggings", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData right_skirt = right_leggings.addChild("right_skirt", ModelPartBuilder.create().uv(10, 54).cuboid(0.1F, 1.6F, -2.55F, 5.0F, 7.0F, 5.0F, new Dilation(-0.1F)), ModelTransform.of(-2.6F, -3.85F, 0.0F, 0.0F, 0.0F, 0.0436F));
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
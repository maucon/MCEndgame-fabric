// Made with Blockbench 4.12.4
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
public class suede extends EntityModel<Entity> {
	private final ModelPart head;
	private final ModelPart helmet;
	private final ModelPart scarf_band_long;
	private final ModelPart scarf_band_short;
	public suede(ModelPart root) {
		this.head = root.getChild("head");
		this.helmet = this.head.getChild("helmet");
		this.scarf_band_long = this.helmet.getChild("scarf_band_long");
		this.scarf_band_short = this.helmet.getChild("scarf_band_short");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData head = modelPartData.addChild("head", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData helmet = head.addChild("helmet", ModelPartBuilder.create().uv(15, 67).cuboid(-4.5F, -8.5F, -4.5F, 9.0F, 5.0F, 9.0F, new Dilation(0.0F))
		.uv(15, 71).cuboid(-1.75F, -7.0F, 4.5F, 3.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData scarf_band_long = helmet.addChild("scarf_band_long", ModelPartBuilder.create().uv(43, 69).cuboid(-1.0F, 0.0F, 0.0F, 2.0F, 6.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-0.4F, -4.1F, 5.1F, 0.0F, -0.1309F, 0.0436F));

		ModelPartData scarf_band_short = helmet.addChild("scarf_band_short", ModelPartBuilder.create().uv(48, 71).cuboid(-0.5F, 0.0F, 0.0F, 1.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.3F, -4.1F, 4.9F, 0.0F, 0.1309F, -0.1309F));
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
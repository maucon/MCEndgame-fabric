// Made with Blockbench 4.12.4
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
public class Emberchant extends EntityModel<Entity> {
	private final ModelPart helmet;
	private final ModelPart brim;
	private final ModelPart base;
	private final ModelPart band;
	private final ModelPart tip0;
	private final ModelPart tip1;
	public Emberchant(ModelPart root) {
		this.helmet = root.getChild("helmet");
		this.brim = this.helmet.getChild("brim");
		this.base = this.brim.getChild("base");
		this.band = this.base.getChild("band");
		this.tip0 = this.base.getChild("tip0");
		this.tip1 = this.tip0.getChild("tip1");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData helmet = modelPartData.addChild("helmet", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData brim = helmet.addChild("brim", ModelPartBuilder.create().uv(0, 47).cuboid(-8.0F, 0.0F, -8.0F, 16.0F, 1.0F, 16.0F, new Dilation(0.0F))
		.uv(5, 41).cuboid(-6.0F, 0.0F, -9.0F, 12.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -5.5F, 0.0F, -0.0873F, 0.0435F, 0.0175F));

		ModelPartData brimLeft_r1 = brim.addChild("brimLeft_r1", ModelPartBuilder.create().uv(5, 44).cuboid(-5.0F, 0.0F, -8.0F, 12.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-1.0F, 0.0F, 1.0F, 0.0F, 1.5708F, 0.0F));

		ModelPartData brimRight_r1 = brim.addChild("brimRight_r1", ModelPartBuilder.create().uv(33, 41).cuboid(-5.0F, 0.0F, -8.0F, 12.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, 0.0F, -1.0F, 0.0F, -1.5708F, 0.0F));

		ModelPartData brimBack_r1 = brim.addChild("brimBack_r1", ModelPartBuilder.create().uv(33, 44).cuboid(-5.0F, 0.0F, -8.0F, 12.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, 0.0F, 1.0F, 0.0F, 3.1416F, 0.0F));

		ModelPartData base = brim.addChild("base", ModelPartBuilder.create().uv(0, 26).cuboid(-10.0F, -3.75F, 0.0F, 10.0F, 4.0F, 10.0F, new Dilation(0.0F)), ModelTransform.of(5.0F, -0.25F, -4.75F, -0.0314F, -0.0436F, -0.0436F));

		ModelPartData band = base.addChild("band", ModelPartBuilder.create().uv(0, 10).cuboid(-10.5F, -3.6F, -0.5F, 11.0F, 4.0F, 11.0F, new Dilation(-0.25F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0262F));

		ModelPartData tip0 = base.addChild("tip0", ModelPartBuilder.create().uv(40, 30).cuboid(0.0F, -3.7642F, 0.0906F, 6.0F, 4.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(-8.0F, -3.95F, 1.75F, -0.2611F, 0.0151F, 0.0859F));

		ModelPartData tip1 = tip0.addChild("tip1", ModelPartBuilder.create().uv(46, 23).cuboid(-3.0F, -3.0F, 0.0F, 3.0F, 3.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(4.5F, -3.75F, 1.25F, -0.5655F, -0.0468F, -0.0737F));
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
// Made with Blockbench 4.12.3
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
public class elf_duelist extends EntityModel<Entity> {
	private final ModelPart elf;
	private final ModelPart elfBody;
	private final ModelPart elfUpperBody;
	private final ModelPart elfChest;
	private final ModelPart elfBreast;
	private final ModelPart elfCape;
	private final ModelPart elfNeck;
	private final ModelPart elfHead;
	private final ModelPart elfEarLeft;
	private final ModelPart elfEarRight;
	private final ModelPart elfArmLeft;
	private final ModelPart elfArmUpperLeft;
	private final ModelPart elfPauldronLeft;
	private final ModelPart elfArmLowerLeft;
	private final ModelPart elfSwordLeft;
	private final ModelPart elfArmRight;
	private final ModelPart elfArmUpperRight;
	private final ModelPart elfPauldronRight;
	private final ModelPart elfArmLowerRight;
	private final ModelPart elfSwordRight;
	private final ModelPart elfLegLeft;
	private final ModelPart elfLegRight;
	public elf_duelist(ModelPart root) {
		this.elf = root.getChild("elf");
		this.elfBody = this.elf.getChild("elfBody");
		this.elfUpperBody = this.elfBody.getChild("elfUpperBody");
		this.elfChest = this.elfUpperBody.getChild("elfChest");
		this.elfBreast = this.elfChest.getChild("elfBreast");
		this.elfCape = this.elfChest.getChild("elfCape");
		this.elfNeck = this.elfChest.getChild("elfNeck");
		this.elfHead = this.elfNeck.getChild("elfHead");
		this.elfEarLeft = this.elfHead.getChild("elfEarLeft");
		this.elfEarRight = this.elfHead.getChild("elfEarRight");
		this.elfArmLeft = this.elfChest.getChild("elfArmLeft");
		this.elfArmUpperLeft = this.elfArmLeft.getChild("elfArmUpperLeft");
		this.elfPauldronLeft = this.elfArmUpperLeft.getChild("elfPauldronLeft");
		this.elfArmLowerLeft = this.elfArmUpperLeft.getChild("elfArmLowerLeft");
		this.elfSwordLeft = this.elfArmLowerLeft.getChild("elfSwordLeft");
		this.elfArmRight = this.elfChest.getChild("elfArmRight");
		this.elfArmUpperRight = this.elfArmRight.getChild("elfArmUpperRight");
		this.elfPauldronRight = this.elfArmUpperRight.getChild("elfPauldronRight");
		this.elfArmLowerRight = this.elfArmUpperRight.getChild("elfArmLowerRight");
		this.elfSwordRight = this.elfArmLowerRight.getChild("elfSwordRight");
		this.elfLegLeft = this.elf.getChild("elfLegLeft");
		this.elfLegRight = this.elf.getChild("elfLegRight");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData elf = modelPartData.addChild("elf", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData elfBody = elf.addChild("elfBody", ModelPartBuilder.create().uv(55, 46).cuboid(-4.0F, -1.5F, -1.85F, 8.0F, 3.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 10.5F, 0.0F));

		ModelPartData elfUpperBody = elfBody.addChild("elfUpperBody", ModelPartBuilder.create().uv(57, 39).cuboid(-3.5F, -3.0F, -1.5F, 7.0F, 3.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -1.5F, 0.0F));

		ModelPartData elfChest = elfUpperBody.addChild("elfChest", ModelPartBuilder.create().uv(57, 22).cuboid(-3.5F, -6.0F, -1.5F, 7.0F, 6.0F, 3.0F, new Dilation(0.25F)), ModelTransform.pivot(0.0F, -3.0F, 0.0F));

		ModelPartData elfBreast = elfChest.addChild("elfBreast", ModelPartBuilder.create().uv(58, 32).cuboid(-3.5F, 0.0F, 0.0F, 7.0F, 4.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -4.75F, -1.75F, -0.2182F, 0.0F, 0.0F));

		ModelPartData elfCape = elfChest.addChild("elfCape", ModelPartBuilder.create().uv(104, 25).cuboid(-4.0F, 0.0F, -0.25F, 8.0F, 14.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -5.75F, 2.0F, 0.1309F, 0.0F, 0.0F));

		ModelPartData elfNeck = elfChest.addChild("elfNeck", ModelPartBuilder.create().uv(63, 17).cuboid(-1.0F, -1.75F, -1.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.25F)), ModelTransform.pivot(0.0F, -6.25F, 0.0F));

		ModelPartData elfHead = elfNeck.addChild("elfHead", ModelPartBuilder.create().uv(51, 0).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.0F))
		.uv(96, 0).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 16.0F, 8.0F, new Dilation(0.25F)), ModelTransform.pivot(0.0F, -0.75F, 0.0F));

		ModelPartData elfEarLeft = elfHead.addChild("elfEarLeft", ModelPartBuilder.create().uv(84, 7).cuboid(0.0F, -6.0F, 0.0F, 0.0F, 6.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(3.5F, -1.25F, -1.25F, -0.2618F, 0.6109F, 0.0F));

		ModelPartData elfEarRight = elfHead.addChild("elfEarRight", ModelPartBuilder.create().uv(44, 7).cuboid(0.0F, -6.0F, 0.0F, 0.0F, 6.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(-3.5F, -1.25F, -1.25F, -0.2618F, -0.6109F, 0.0F));

		ModelPartData elfArmLeft = elfChest.addChild("elfArmLeft", ModelPartBuilder.create(), ModelTransform.pivot(3.5F, -5.25F, 0.25F));

		ModelPartData elfArmUpperLeft = elfArmLeft.addChild("elfArmUpperLeft", ModelPartBuilder.create().uv(80, 32).cuboid(0.0F, -1.0F, -1.55F, 3.0F, 6.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData elfPauldronLeft = elfArmUpperLeft.addChild("elfPauldronLeft", ModelPartBuilder.create().uv(80, 23).cuboid(0.25F, 0.25F, -1.5F, 3.0F, 5.0F, 3.0F, new Dilation(0.25F)), ModelTransform.of(0.0F, -1.5F, 0.0F, 0.0F, 0.0F, 0.0436F));

		ModelPartData elfArmLowerLeft = elfArmUpperLeft.addChild("elfArmLowerLeft", ModelPartBuilder.create().uv(80, 42).cuboid(-1.5F, -0.5F, -1.5F, 3.0F, 7.0F, 3.0F, new Dilation(-0.25F)), ModelTransform.pivot(1.5F, 5.0F, -0.05F));

		ModelPartData elfSwordLeft = elfArmLowerLeft.addChild("elfSwordLeft", ModelPartBuilder.create().uv(95, 46).cuboid(-0.5F, -1.75F, -0.5F, 1.0F, 5.0F, 1.0F, new Dilation(0.0F))
		.uv(89, 53).cuboid(-2.0F, -2.75F, -2.0F, 4.0F, 1.0F, 4.0F, new Dilation(0.0F))
		.uv(95, 59).cuboid(-0.5F, -19.75F, -0.5F, 1.0F, 17.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-0.25F, 5.25F, 0.05F, 1.5708F, 0.0F, 0.0F));

		ModelPartData elfArmRight = elfChest.addChild("elfArmRight", ModelPartBuilder.create(), ModelTransform.pivot(-3.5F, -5.25F, 0.25F));

		ModelPartData elfArmUpperRight = elfArmRight.addChild("elfArmUpperRight", ModelPartBuilder.create().uv(42, 32).cuboid(-3.0F, -1.0F, -1.55F, 3.0F, 6.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData elfPauldronRight = elfArmUpperRight.addChild("elfPauldronRight", ModelPartBuilder.create().uv(42, 23).cuboid(-3.25F, 0.25F, -1.5F, 3.0F, 5.0F, 3.0F, new Dilation(0.25F)), ModelTransform.of(0.0F, -1.5F, 0.0F, 0.0F, 0.0F, -0.0436F));

		ModelPartData elfArmLowerRight = elfArmUpperRight.addChild("elfArmLowerRight", ModelPartBuilder.create().uv(42, 42).cuboid(-1.5F, -0.5F, -1.5F, 3.0F, 7.0F, 3.0F, new Dilation(-0.25F)), ModelTransform.pivot(-1.5F, 5.0F, -0.05F));

		ModelPartData elfSwordRight = elfArmLowerRight.addChild("elfSwordRight", ModelPartBuilder.create().uv(35, 46).cuboid(-0.5F, -1.75F, -0.5F, 1.0F, 5.0F, 1.0F, new Dilation(0.0F))
		.uv(29, 53).cuboid(-2.0F, -2.75F, -2.0F, 4.0F, 1.0F, 4.0F, new Dilation(0.0F))
		.uv(35, 59).cuboid(-0.5F, -19.75F, -0.5F, 1.0F, 17.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.25F, 5.25F, 0.05F, 1.5708F, 0.0F, 0.0F));

		ModelPartData elfLegLeft = elf.addChild("elfLegLeft", ModelPartBuilder.create().uv(73, 72).cuboid(-1.5F, 0.0F, -1.5F, 3.0F, 6.0F, 3.0F, new Dilation(0.25F))
		.uv(73, 82).cuboid(-1.5F, 6.0F, -1.5F, 3.0F, 6.0F, 3.0F, new Dilation(0.0F))
		.uv(71, 54).cuboid(-2.0F, -1.75F, -1.75F, 4.0F, 13.0F, 4.0F, new Dilation(0.25F)), ModelTransform.pivot(2.0F, 12.0F, 0.0F));

		ModelPartData elfLegRight = elf.addChild("elfLegRight", ModelPartBuilder.create().uv(49, 72).cuboid(-1.25F, 0.0F, -1.5F, 3.0F, 6.0F, 3.0F, new Dilation(0.25F))
		.uv(49, 82).cuboid(-1.25F, 6.0F, -1.5F, 3.0F, 6.0F, 3.0F, new Dilation(0.0F))
		.uv(47, 54).cuboid(-1.75F, -1.75F, -1.75F, 4.0F, 13.0F, 4.0F, new Dilation(0.25F)), ModelTransform.pivot(-2.25F, 12.0F, 0.0F));
		return TexturedModelData.of(modelData, 128, 128);
	}
	@Override
	public void setAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}
	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		elf.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}
}
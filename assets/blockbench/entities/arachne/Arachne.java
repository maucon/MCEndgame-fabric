// Made with Blockbench 4.12.4
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
public class Arachne extends EntityModel<Entity> {
	private final ModelPart arachne;
	private final ModelPart cephalothorax;
	private final ModelPart legs;
	private final ModelPart legLeft1;
	private final ModelPart temurLeft1;
	private final ModelPart tibiaLeft1;
	private final ModelPart metatarsusLeft1;
	private final ModelPart tarsusLeft1;
	private final ModelPart legLeft2;
	private final ModelPart temurLeft2;
	private final ModelPart tibiaLeft2;
	private final ModelPart metatarsusLeft2;
	private final ModelPart tarsusLeft2;
	private final ModelPart legLeft3;
	private final ModelPart temurLeft3;
	private final ModelPart tibiaLeft3;
	private final ModelPart metatarsusLeft3;
	private final ModelPart tarsusLeft3;
	private final ModelPart legLeft4;
	private final ModelPart temurLeft4;
	private final ModelPart tibiaLeft4;
	private final ModelPart metatarsusLeft4;
	private final ModelPart tarsusLeft4;
	private final ModelPart legRight1;
	private final ModelPart temurRight1;
	private final ModelPart tibiaRight1;
	private final ModelPart metatarsusRight1;
	private final ModelPart tarsusRight1;
	private final ModelPart legRight2;
	private final ModelPart temurRight2;
	private final ModelPart tibiaRight2;
	private final ModelPart metatarsusRight2;
	private final ModelPart tarsusRight2;
	private final ModelPart legRight3;
	private final ModelPart temurRight3;
	private final ModelPart tibiaRight3;
	private final ModelPart metatarsusRight3;
	private final ModelPart tarsusRight3;
	private final ModelPart legRight4;
	private final ModelPart temurRight4;
	private final ModelPart tibiaRight4;
	private final ModelPart metatarsusRight4;
	private final ModelPart tarsusRight4;
	private final ModelPart upperbody;
	private final ModelPart chest;
	private final ModelPart breast;
	private final ModelPart chestDressLower;
	private final ModelPart breastTop;
	private final ModelPart neck;
	private final ModelPart head;
	private final ModelPart armLeft;
	private final ModelPart armLeftLower;
	private final ModelPart armRight;
	private final ModelPart armRightLower;
	private final ModelPart abdomen;
	public Arachne(ModelPart root) {
		this.arachne = root.getChild("arachne");
		this.cephalothorax = this.arachne.getChild("cephalothorax");
		this.legs = this.cephalothorax.getChild("legs");
		this.legLeft1 = this.legs.getChild("legLeft1");
		this.temurLeft1 = this.legLeft1.getChild("temurLeft1");
		this.tibiaLeft1 = this.temurLeft1.getChild("tibiaLeft1");
		this.metatarsusLeft1 = this.tibiaLeft1.getChild("metatarsusLeft1");
		this.tarsusLeft1 = this.metatarsusLeft1.getChild("tarsusLeft1");
		this.legLeft2 = this.legs.getChild("legLeft2");
		this.temurLeft2 = this.legLeft2.getChild("temurLeft2");
		this.tibiaLeft2 = this.temurLeft2.getChild("tibiaLeft2");
		this.metatarsusLeft2 = this.tibiaLeft2.getChild("metatarsusLeft2");
		this.tarsusLeft2 = this.metatarsusLeft2.getChild("tarsusLeft2");
		this.legLeft3 = this.legs.getChild("legLeft3");
		this.temurLeft3 = this.legLeft3.getChild("temurLeft3");
		this.tibiaLeft3 = this.temurLeft3.getChild("tibiaLeft3");
		this.metatarsusLeft3 = this.tibiaLeft3.getChild("metatarsusLeft3");
		this.tarsusLeft3 = this.metatarsusLeft3.getChild("tarsusLeft3");
		this.legLeft4 = this.legs.getChild("legLeft4");
		this.temurLeft4 = this.legLeft4.getChild("temurLeft4");
		this.tibiaLeft4 = this.temurLeft4.getChild("tibiaLeft4");
		this.metatarsusLeft4 = this.tibiaLeft4.getChild("metatarsusLeft4");
		this.tarsusLeft4 = this.metatarsusLeft4.getChild("tarsusLeft4");
		this.legRight1 = this.legs.getChild("legRight1");
		this.temurRight1 = this.legRight1.getChild("temurRight1");
		this.tibiaRight1 = this.temurRight1.getChild("tibiaRight1");
		this.metatarsusRight1 = this.tibiaRight1.getChild("metatarsusRight1");
		this.tarsusRight1 = this.metatarsusRight1.getChild("tarsusRight1");
		this.legRight2 = this.legs.getChild("legRight2");
		this.temurRight2 = this.legRight2.getChild("temurRight2");
		this.tibiaRight2 = this.temurRight2.getChild("tibiaRight2");
		this.metatarsusRight2 = this.tibiaRight2.getChild("metatarsusRight2");
		this.tarsusRight2 = this.metatarsusRight2.getChild("tarsusRight2");
		this.legRight3 = this.legs.getChild("legRight3");
		this.temurRight3 = this.legRight3.getChild("temurRight3");
		this.tibiaRight3 = this.temurRight3.getChild("tibiaRight3");
		this.metatarsusRight3 = this.tibiaRight3.getChild("metatarsusRight3");
		this.tarsusRight3 = this.metatarsusRight3.getChild("tarsusRight3");
		this.legRight4 = this.legs.getChild("legRight4");
		this.temurRight4 = this.legRight4.getChild("temurRight4");
		this.tibiaRight4 = this.temurRight4.getChild("tibiaRight4");
		this.metatarsusRight4 = this.tibiaRight4.getChild("metatarsusRight4");
		this.tarsusRight4 = this.metatarsusRight4.getChild("tarsusRight4");
		this.upperbody = this.cephalothorax.getChild("upperbody");
		this.chest = this.upperbody.getChild("chest");
		this.breast = this.chest.getChild("breast");
		this.chestDressLower = this.breast.getChild("chestDressLower");
		this.breastTop = this.breast.getChild("breastTop");
		this.neck = this.chest.getChild("neck");
		this.head = this.neck.getChild("head");
		this.armLeft = this.chest.getChild("armLeft");
		this.armLeftLower = this.armLeft.getChild("armLeftLower");
		this.armRight = this.chest.getChild("armRight");
		this.armRightLower = this.armRight.getChild("armRightLower");
		this.abdomen = this.cephalothorax.getChild("abdomen");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData arachne = modelPartData.addChild("arachne", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 25.0F, -3.0F));

		ModelPartData cephalothorax = arachne.addChild("cephalothorax", ModelPartBuilder.create().uv(110, 118).cuboid(-4.0F, -12.0F, -2.0F, 8.0F, 6.0F, 10.0F, new Dilation(0.0F))
		.uv(114, 108).cuboid(-3.0F, -13.0F, 0.0F, 6.0F, 1.0F, 8.0F, new Dilation(0.0F))
		.uv(120, 103).cuboid(-2.5F, -14.0F, 1.0F, 5.0F, 1.0F, 3.0F, new Dilation(0.0F))
		.uv(122, 98).cuboid(-2.0F, -16.0F, 0.5F, 4.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData legs = cephalothorax.addChild("legs", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData legLeft1 = legs.addChild("legLeft1", ModelPartBuilder.create().uv(147, 105).cuboid(-0.5F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.25F))
		.uv(147, 110).cuboid(-0.5F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.3F)), ModelTransform.of(3.0F, -8.0F, -2.0F, 0.0011F, 1.149F, 0.087F));

		ModelPartData temurLeft1 = legLeft1.addChild("temurLeft1", ModelPartBuilder.create().uv(156, 105).cuboid(-0.25F, -1.0F, -1.0F, 13.0F, 2.0F, 2.0F, new Dilation(0.0F))
		.uv(156, 110).cuboid(-0.25F, -1.0F, -1.0F, 13.0F, 2.0F, 2.0F, new Dilation(0.05F)), ModelTransform.of(1.0F, 0.0F, 0.0F, 0.0085F, -0.0059F, -1.1775F));

		ModelPartData tibiaLeft1 = temurLeft1.addChild("tibiaLeft1", ModelPartBuilder.create().uv(186, 105).cuboid(0.1455F, -0.1039F, -1.0F, 12.0F, 2.0F, 2.0F, new Dilation(-0.25F))
		.uv(186, 110).cuboid(0.1455F, -0.1039F, -1.0F, 12.0F, 2.0F, 2.0F, new Dilation(-0.2F)), ModelTransform.of(12.65F, -1.0F, 0.0F, 0.0F, 0.0F, 1.4835F));

		ModelPartData metatarsusLeft1 = tibiaLeft1.addChild("metatarsusLeft1", ModelPartBuilder.create().uv(214, 105).cuboid(-0.2502F, -0.2929F, -1.0034F, 11.0F, 2.0F, 2.0F, new Dilation(-0.3F))
		.uv(214, 110).cuboid(-0.2502F, -0.2929F, -1.0034F, 11.0F, 2.0F, 2.0F, new Dilation(-0.25F)), ModelTransform.of(11.8955F, 0.1461F, 0.0F, 0.0048F, 0.0019F, 0.7851F));

		ModelPartData tarsusLeft1 = metatarsusLeft1.addChild("tarsusLeft1", ModelPartBuilder.create().uv(241, 107).cuboid(-0.6299F, 0.0964F, -0.5F, 6.0F, 1.0F, 1.0F, new Dilation(0.1F))
		.uv(241, 112).cuboid(-0.6299F, 0.0964F, -0.5F, 6.0F, 1.0F, 1.0F, new Dilation(0.15F)), ModelTransform.of(10.4998F, 0.2071F, -0.0034F, 0.0F, 0.0F, 0.2182F));

		ModelPartData legLeft2 = legs.addChild("legLeft2", ModelPartBuilder.create().uv(147, 115).cuboid(-0.5F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.25F))
		.uv(147, 120).cuboid(-0.5F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.3F)), ModelTransform.of(4.0F, -8.0F, 0.0F, 0.0005F, 0.4509F, 0.0863F));

		ModelPartData temurLeft2 = legLeft2.addChild("temurLeft2", ModelPartBuilder.create().uv(156, 115).cuboid(-0.25F, -1.0F, -1.0F, 13.0F, 2.0F, 2.0F, new Dilation(0.0F))
		.uv(156, 120).cuboid(-0.25F, -1.0F, -1.0F, 13.0F, 2.0F, 2.0F, new Dilation(0.05F)), ModelTransform.of(1.0F, 0.0F, 0.0F, 0.0085F, -0.0059F, -1.1775F));

		ModelPartData tibiaLeft2 = temurLeft2.addChild("tibiaLeft2", ModelPartBuilder.create().uv(186, 115).cuboid(0.1371F, -0.028F, -1.0F, 12.0F, 2.0F, 2.0F, new Dilation(-0.25F))
		.uv(186, 120).cuboid(0.1371F, -0.028F, -1.0F, 12.0F, 2.0F, 2.0F, new Dilation(-0.2F)), ModelTransform.of(12.65F, -1.0F, 0.0F, 0.0F, 0.0F, 1.4399F));

		ModelPartData metatarsusLeft2 = tibiaLeft2.addChild("metatarsusLeft2", ModelPartBuilder.create().uv(214, 115).cuboid(-0.2502F, -0.2929F, -1.0034F, 11.0F, 2.0F, 2.0F, new Dilation(-0.3F))
		.uv(214, 120).cuboid(-0.2502F, -0.2929F, -1.0034F, 11.0F, 2.0F, 2.0F, new Dilation(-0.25F)), ModelTransform.of(11.8871F, 0.222F, 0.0F, 0.0048F, 0.0019F, 0.7851F));

		ModelPartData tarsusLeft2 = metatarsusLeft2.addChild("tarsusLeft2", ModelPartBuilder.create().uv(241, 117).cuboid(-0.6299F, 0.0964F, -0.5F, 6.0F, 1.0F, 1.0F, new Dilation(0.1F))
		.uv(241, 122).cuboid(-0.6299F, 0.0964F, -0.5F, 6.0F, 1.0F, 1.0F, new Dilation(0.15F)), ModelTransform.of(10.4998F, 0.2071F, -0.0034F, 0.0F, 0.0F, 0.2182F));

		ModelPartData legLeft3 = legs.addChild("legLeft3", ModelPartBuilder.create().uv(147, 125).cuboid(-0.5F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.25F))
		.uv(147, 130).cuboid(-0.5F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.3F)), ModelTransform.of(4.0F, -8.0F, 3.0F, 0.0004F, -0.0727F, 0.086F));

		ModelPartData temurLeft3 = legLeft3.addChild("temurLeft3", ModelPartBuilder.create().uv(156, 125).cuboid(-0.25F, -1.0F, -1.0F, 13.0F, 2.0F, 2.0F, new Dilation(0.0F))
		.uv(156, 130).cuboid(-0.25F, -1.0F, -1.0F, 13.0F, 2.0F, 2.0F, new Dilation(0.05F)), ModelTransform.of(1.0F, 0.0F, 0.0F, 0.0085F, -0.0059F, -1.1775F));

		ModelPartData tibiaLeft3 = temurLeft3.addChild("tibiaLeft3", ModelPartBuilder.create().uv(186, 125).cuboid(0.1371F, -0.028F, -1.0F, 12.0F, 2.0F, 2.0F, new Dilation(-0.25F))
		.uv(186, 130).cuboid(0.1371F, -0.028F, -1.0F, 12.0F, 2.0F, 2.0F, new Dilation(-0.2F)), ModelTransform.of(12.65F, -1.0F, 0.0F, 0.0F, 0.0F, 1.4399F));

		ModelPartData metatarsusLeft3 = tibiaLeft3.addChild("metatarsusLeft3", ModelPartBuilder.create().uv(214, 125).cuboid(-0.2502F, -0.2929F, -1.0034F, 11.0F, 2.0F, 2.0F, new Dilation(-0.3F))
		.uv(214, 130).cuboid(-0.2502F, -0.2929F, -1.0034F, 11.0F, 2.0F, 2.0F, new Dilation(-0.25F)), ModelTransform.of(11.8871F, 0.222F, 0.0F, 0.0048F, 0.0019F, 0.7851F));

		ModelPartData tarsusLeft3 = metatarsusLeft3.addChild("tarsusLeft3", ModelPartBuilder.create().uv(241, 127).cuboid(-0.6299F, 0.0964F, -0.5F, 6.0F, 1.0F, 1.0F, new Dilation(0.1F))
		.uv(241, 132).cuboid(-0.6299F, 0.0964F, -0.5F, 6.0F, 1.0F, 1.0F, new Dilation(0.15F)), ModelTransform.of(10.4998F, 0.2071F, -0.0034F, 0.0F, 0.0F, 0.2182F));

		ModelPartData legLeft4 = legs.addChild("legLeft4", ModelPartBuilder.create().uv(147, 135).cuboid(-0.5F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.25F))
		.uv(147, 140).cuboid(-0.5F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.3F)), ModelTransform.of(4.0F, -8.0F, 6.0F, 0.0005F, -0.5963F, 0.0857F));

		ModelPartData temurLeft4 = legLeft4.addChild("temurLeft4", ModelPartBuilder.create().uv(156, 135).cuboid(-0.25F, -1.0F, -1.0F, 13.0F, 2.0F, 2.0F, new Dilation(0.0F))
		.uv(156, 140).cuboid(-0.25F, -1.0F, -1.0F, 13.0F, 2.0F, 2.0F, new Dilation(0.05F)), ModelTransform.of(1.0F, 0.0F, 0.0F, 0.0085F, -0.0059F, -1.1775F));

		ModelPartData tibiaLeft4 = temurLeft4.addChild("tibiaLeft4", ModelPartBuilder.create().uv(186, 135).cuboid(0.1309F, -0.028F, -1.1427F, 12.0F, 2.0F, 2.0F, new Dilation(-0.25F))
		.uv(186, 140).cuboid(0.1309F, -0.028F, -1.1427F, 12.0F, 2.0F, 2.0F, new Dilation(-0.2F)), ModelTransform.of(12.65F, -1.0F, 0.0F, 0.0F, -0.0873F, 1.4399F));

		ModelPartData metatarsusLeft4 = tibiaLeft4.addChild("metatarsusLeft4", ModelPartBuilder.create().uv(214, 135).cuboid(-0.2502F, -0.2929F, -1.0034F, 11.0F, 2.0F, 2.0F, new Dilation(-0.3F))
		.uv(214, 140).cuboid(-0.2502F, -0.2929F, -1.0034F, 11.0F, 2.0F, 2.0F, new Dilation(-0.25F)), ModelTransform.of(11.8809F, 0.222F, -0.1427F, 0.0048F, 0.0019F, 0.7851F));

		ModelPartData tarsusLeft4 = metatarsusLeft4.addChild("tarsusLeft4", ModelPartBuilder.create().uv(241, 137).cuboid(-0.6299F, 0.0964F, -0.5F, 6.0F, 1.0F, 1.0F, new Dilation(0.1F))
		.uv(241, 142).cuboid(-0.6299F, 0.0964F, -0.5F, 6.0F, 1.0F, 1.0F, new Dilation(0.15F)), ModelTransform.of(10.4998F, 0.2071F, -0.0034F, 0.0F, 0.0F, 0.2182F));

		ModelPartData legRight1 = legs.addChild("legRight1", ModelPartBuilder.create().uv(101, 105).cuboid(-1.5F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.25F))
		.uv(101, 110).cuboid(-1.5F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.3F)), ModelTransform.of(-3.0F, -8.0F, -2.0F, 0.0011F, -1.149F, -0.087F));

		ModelPartData temurRight1 = legRight1.addChild("temurRight1", ModelPartBuilder.create().uv(70, 105).cuboid(-12.75F, -1.0F, -1.0F, 13.0F, 2.0F, 2.0F, new Dilation(0.0F))
		.uv(70, 110).cuboid(-12.75F, -1.0F, -1.0F, 13.0F, 2.0F, 2.0F, new Dilation(0.05F)), ModelTransform.of(-1.0F, 0.0F, 0.0F, 0.0085F, 0.0059F, 1.1775F));

		ModelPartData tibiaRight1 = temurRight1.addChild("tibiaRight1", ModelPartBuilder.create().uv(42, 105).cuboid(-12.1455F, -0.1039F, -1.0F, 12.0F, 2.0F, 2.0F, new Dilation(-0.25F))
		.uv(42, 110).cuboid(-12.1455F, -0.1039F, -1.0F, 12.0F, 2.0F, 2.0F, new Dilation(-0.2F)), ModelTransform.of(-12.65F, -1.0F, 0.0F, 0.0F, 0.0F, -1.4835F));

		ModelPartData metatarsusRight1 = tibiaRight1.addChild("metatarsusRight1", ModelPartBuilder.create().uv(16, 105).cuboid(-10.7498F, -0.2929F, -1.0034F, 11.0F, 2.0F, 2.0F, new Dilation(-0.3F))
		.uv(16, 110).cuboid(-10.7498F, -0.2929F, -1.0034F, 11.0F, 2.0F, 2.0F, new Dilation(-0.25F)), ModelTransform.of(-11.8955F, 0.1461F, 0.0F, 0.0048F, -0.0019F, -0.7851F));

		ModelPartData tarsusRight1 = metatarsusRight1.addChild("tarsusRight1", ModelPartBuilder.create().uv(1, 107).cuboid(-5.3701F, 0.0964F, -0.5F, 6.0F, 1.0F, 1.0F, new Dilation(0.1F))
		.uv(1, 112).cuboid(-5.3701F, 0.0964F, -0.5F, 6.0F, 1.0F, 1.0F, new Dilation(0.15F)), ModelTransform.of(-10.4998F, 0.2071F, -0.0034F, 0.0F, 0.0F, -0.2182F));

		ModelPartData legRight2 = legs.addChild("legRight2", ModelPartBuilder.create().uv(101, 115).cuboid(-1.5F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.25F))
		.uv(101, 120).cuboid(-1.5F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.3F)), ModelTransform.of(-4.0F, -8.0F, 0.0F, 0.0005F, -0.4509F, -0.0863F));

		ModelPartData temurRight2 = legRight2.addChild("temurRight2", ModelPartBuilder.create().uv(70, 115).cuboid(-12.75F, -1.0F, -1.0F, 13.0F, 2.0F, 2.0F, new Dilation(0.0F))
		.uv(70, 120).cuboid(-12.75F, -1.0F, -1.0F, 13.0F, 2.0F, 2.0F, new Dilation(0.05F)), ModelTransform.of(-1.0F, 0.0F, 0.0F, 0.0085F, 0.0059F, 1.1775F));

		ModelPartData tibiaRight2 = temurRight2.addChild("tibiaRight2", ModelPartBuilder.create().uv(42, 115).cuboid(-12.1371F, -0.028F, -1.0F, 12.0F, 2.0F, 2.0F, new Dilation(-0.25F))
		.uv(42, 120).cuboid(-12.1371F, -0.028F, -1.0F, 12.0F, 2.0F, 2.0F, new Dilation(-0.2F)), ModelTransform.of(-12.65F, -1.0F, 0.0F, 0.0F, 0.0F, -1.4399F));

		ModelPartData metatarsusRight2 = tibiaRight2.addChild("metatarsusRight2", ModelPartBuilder.create().uv(16, 115).cuboid(-10.7498F, -0.2929F, -1.0034F, 11.0F, 2.0F, 2.0F, new Dilation(-0.3F))
		.uv(16, 120).cuboid(-10.7498F, -0.2929F, -1.0034F, 11.0F, 2.0F, 2.0F, new Dilation(-0.25F)), ModelTransform.of(-11.8871F, 0.222F, 0.0F, 0.0048F, -0.0019F, -0.7851F));

		ModelPartData tarsusRight2 = metatarsusRight2.addChild("tarsusRight2", ModelPartBuilder.create().uv(1, 117).cuboid(-5.3701F, 0.0964F, -0.5F, 6.0F, 1.0F, 1.0F, new Dilation(0.1F))
		.uv(1, 122).cuboid(-5.3701F, 0.0964F, -0.5F, 6.0F, 1.0F, 1.0F, new Dilation(0.15F)), ModelTransform.of(-10.4998F, 0.2071F, -0.0034F, 0.0F, 0.0F, -0.2182F));

		ModelPartData legRight3 = legs.addChild("legRight3", ModelPartBuilder.create().uv(101, 125).cuboid(-1.5F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.25F))
		.uv(101, 130).cuboid(-1.5F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.3F)), ModelTransform.of(-4.0F, -8.0F, 3.0F, 0.0004F, 0.0727F, -0.086F));

		ModelPartData temurRight3 = legRight3.addChild("temurRight3", ModelPartBuilder.create().uv(70, 125).cuboid(-12.75F, -1.0F, -1.0F, 13.0F, 2.0F, 2.0F, new Dilation(0.0F))
		.uv(70, 130).cuboid(-12.75F, -1.0F, -1.0F, 13.0F, 2.0F, 2.0F, new Dilation(0.05F)), ModelTransform.of(-1.0F, 0.0F, 0.0F, 0.0085F, 0.0059F, 1.1775F));

		ModelPartData tibiaRight3 = temurRight3.addChild("tibiaRight3", ModelPartBuilder.create().uv(42, 125).cuboid(-12.1371F, -0.028F, -1.0F, 12.0F, 2.0F, 2.0F, new Dilation(-0.25F))
		.uv(42, 130).cuboid(-12.1371F, -0.028F, -1.0F, 12.0F, 2.0F, 2.0F, new Dilation(-0.2F)), ModelTransform.of(-12.65F, -1.0F, 0.0F, 0.0F, 0.0F, -1.4399F));

		ModelPartData metatarsusRight3 = tibiaRight3.addChild("metatarsusRight3", ModelPartBuilder.create().uv(16, 125).cuboid(-10.7498F, -0.2929F, -1.0034F, 11.0F, 2.0F, 2.0F, new Dilation(-0.3F))
		.uv(16, 130).cuboid(-10.7498F, -0.2929F, -1.0034F, 11.0F, 2.0F, 2.0F, new Dilation(-0.25F)), ModelTransform.of(-11.8871F, 0.222F, 0.0F, 0.0048F, -0.0019F, -0.7851F));

		ModelPartData tarsusRight3 = metatarsusRight3.addChild("tarsusRight3", ModelPartBuilder.create().uv(1, 127).cuboid(-5.3701F, 0.0964F, -0.5F, 6.0F, 1.0F, 1.0F, new Dilation(0.1F))
		.uv(1, 132).cuboid(-5.3701F, 0.0964F, -0.5F, 6.0F, 1.0F, 1.0F, new Dilation(0.15F)), ModelTransform.of(-10.4998F, 0.2071F, -0.0034F, 0.0F, 0.0F, -0.2182F));

		ModelPartData legRight4 = legs.addChild("legRight4", ModelPartBuilder.create().uv(101, 135).cuboid(-1.5F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.25F))
		.uv(101, 140).cuboid(-1.5F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.3F)), ModelTransform.of(-4.0F, -8.0F, 6.0F, 0.0005F, 0.5963F, -0.0857F));

		ModelPartData temurRight4 = legRight4.addChild("temurRight4", ModelPartBuilder.create().uv(70, 135).cuboid(-12.75F, -1.0F, -1.0F, 13.0F, 2.0F, 2.0F, new Dilation(0.0F))
		.uv(70, 140).cuboid(-12.75F, -1.0F, -1.0F, 13.0F, 2.0F, 2.0F, new Dilation(0.05F)), ModelTransform.of(-1.0F, 0.0F, 0.0F, 0.0085F, 0.0059F, 1.1775F));

		ModelPartData tibiaRight4 = temurRight4.addChild("tibiaRight4", ModelPartBuilder.create().uv(42, 135).cuboid(-12.1309F, -0.028F, -1.1427F, 12.0F, 2.0F, 2.0F, new Dilation(-0.25F))
		.uv(42, 140).cuboid(-12.1309F, -0.028F, -1.1427F, 12.0F, 2.0F, 2.0F, new Dilation(-0.2F)), ModelTransform.of(-12.65F, -1.0F, 0.0F, 0.0F, 0.0873F, -1.4399F));

		ModelPartData metatarsusRight4 = tibiaRight4.addChild("metatarsusRight4", ModelPartBuilder.create().uv(16, 135).cuboid(-10.7498F, -0.2929F, -1.0034F, 11.0F, 2.0F, 2.0F, new Dilation(-0.3F))
		.uv(16, 140).cuboid(-10.7498F, -0.2929F, -1.0034F, 11.0F, 2.0F, 2.0F, new Dilation(-0.25F)), ModelTransform.of(-11.8809F, 0.222F, -0.1427F, 0.0048F, -0.0019F, -0.7851F));

		ModelPartData tarsusRight4 = metatarsusRight4.addChild("tarsusRight4", ModelPartBuilder.create().uv(1, 137).cuboid(-5.3701F, 0.0964F, -0.5F, 6.0F, 1.0F, 1.0F, new Dilation(0.1F))
		.uv(1, 142).cuboid(-5.3701F, 0.0964F, -0.5F, 6.0F, 1.0F, 1.0F, new Dilation(0.15F)), ModelTransform.of(-10.4998F, 0.2071F, -0.0034F, 0.0F, 0.0F, -0.2182F));

		ModelPartData upperbody = cephalothorax.addChild("upperbody", ModelPartBuilder.create().uv(116, 80).cuboid(-4.0F, -5.5F, -2.25F, 8.0F, 5.0F, 4.0F, new Dilation(-0.35F))
		.uv(116, 90).cuboid(-4.0F, -1.5F, -2.25F, 8.0F, 3.0F, 4.0F, new Dilation(0.1F)), ModelTransform.pivot(0.0F, -12.5F, 0.25F));

		ModelPartData chest = upperbody.addChild("chest", ModelPartBuilder.create().uv(116, 49).cuboid(-4.0F, -7.0F, -2.25F, 8.0F, 7.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -4.5F, -0.25F));

		ModelPartData breast = chest.addChild("breast", ModelPartBuilder.create().uv(117, 67).cuboid(-4.0F, -0.5F, -2.0F, 8.0F, 2.0F, 3.0F, new Dilation(-0.25F)), ModelTransform.pivot(0.0F, -4.0F, -2.25F));

		ModelPartData chestDressLower = breast.addChild("chestDressLower", ModelPartBuilder.create().uv(118, 73).cuboid(-3.5F, -0.25F, 0.0F, 7.0F, 3.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 1.25F, -1.5F, 0.5236F, 0.0F, 0.0F));

		ModelPartData breastTop = breast.addChild("breastTop", ModelPartBuilder.create(), ModelTransform.of(0.0F, -0.25F, -1.7F, 0.48F, 0.0F, 0.0F));

		ModelPartData breastRight_r1 = breastTop.addChild("breastRight_r1", ModelPartBuilder.create().uv(115, 61).cuboid(-1.0F, 0.0F, -0.2F, 3.0F, 2.0F, 3.0F, new Dilation(0.0F))
		.uv(129, 61).cuboid(2.5F, 0.0F, -0.2F, 3.0F, 2.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(-2.25F, 0.0F, 0.2F, 0.2618F, 0.0F, 0.0F));

		ModelPartData neck = chest.addChild("neck", ModelPartBuilder.create().uv(122, 42).cuboid(-1.5F, -2.0F, -1.5F, 3.0F, 3.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -7.0F, 0.0F));

		ModelPartData head = neck.addChild("head", ModelPartBuilder.create().uv(112, 25).cuboid(-4.0F, -8.0F, -4.25F, 8.0F, 8.0F, 8.0F, new Dilation(0.0F))
		.uv(112, 0).cuboid(-4.0F, -8.0F, -4.25F, 8.0F, 16.0F, 8.0F, new Dilation(0.15F)), ModelTransform.pivot(0.0F, -1.0F, 0.0F));

		ModelPartData armLeft = chest.addChild("armLeft", ModelPartBuilder.create().uv(142, 51).cuboid(0.0F, -0.5F, -1.5F, 3.0F, 6.0F, 3.0F, new Dilation(0.0F))
		.uv(155, 51).cuboid(0.0F, -0.5F, -1.5F, 3.0F, 6.0F, 3.0F, new Dilation(0.1F)), ModelTransform.pivot(3.75F, -6.25F, 0.0F));

		ModelPartData armLeftLower = armLeft.addChild("armLeftLower", ModelPartBuilder.create().uv(142, 61).cuboid(4.0F, -18.75F, -1.5F, 3.0F, 7.0F, 3.0F, new Dilation(-0.25F))
		.uv(155, 61).cuboid(4.0F, -18.75F, -1.5F, 3.0F, 7.0F, 3.0F, new Dilation(-0.15F)), ModelTransform.pivot(-4.0F, 23.25F, 0.0F));

		ModelPartData armRight = chest.addChild("armRight", ModelPartBuilder.create().uv(102, 50).cuboid(-3.0F, -0.5F, -1.5F, 3.0F, 6.0F, 3.0F, new Dilation(0.0F))
		.uv(89, 50).cuboid(-3.0F, -0.5F, -1.5F, 3.0F, 6.0F, 3.0F, new Dilation(0.1F)), ModelTransform.pivot(-3.75F, -6.25F, 0.0F));

		ModelPartData armRightLower = armRight.addChild("armRightLower", ModelPartBuilder.create().uv(102, 60).cuboid(-7.0F, -18.75F, -1.5F, 3.0F, 7.0F, 3.0F, new Dilation(-0.25F))
		.uv(89, 60).cuboid(-7.0F, -18.75F, -1.5F, 3.0F, 7.0F, 3.0F, new Dilation(-0.15F)), ModelTransform.pivot(4.0F, 23.25F, 0.0F));

		ModelPartData abdomen = cephalothorax.addChild("abdomen", ModelPartBuilder.create().uv(104, 147).cuboid(-6.0F, -5.0F, 0.0F, 12.0F, 9.0F, 12.0F, new Dilation(0.0F))
		.uv(109, 135).cuboid(-4.5F, -6.0F, 1.0F, 9.0F, 1.0F, 10.0F, new Dilation(0.0F))
		.uv(109, 169).cuboid(-4.5F, 4.0F, 1.0F, 9.0F, 1.0F, 10.0F, new Dilation(0.0F))
		.uv(81, 151).cuboid(-7.0F, -4.0F, 1.0F, 1.0F, 7.0F, 10.0F, new Dilation(0.0F))
		.uv(153, 151).cuboid(6.0F, -4.0F, 1.0F, 1.0F, 7.0F, 10.0F, new Dilation(0.0F))
		.uv(117, 181).cuboid(-5.0F, -3.75F, 12.0F, 10.0F, 7.0F, 1.0F, new Dilation(0.0F))
		.uv(119, 190).cuboid(-4.0F, -2.25F, 13.0F, 8.0F, 5.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -9.0F, 7.0F));
		return TexturedModelData.of(modelData, 256, 256);
	}
	@Override
	public void setAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}
	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		arachne.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}
}
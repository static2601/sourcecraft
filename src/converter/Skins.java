package converter;

import java.util.function.Supplier;

import basic.Loggger;
import basic.NameSupplier;
import converter.actions.BlockMap;
import minecraft.*;
import org.w3c.dom.Text;
import periphery.TexturePack;
import vmfWriter.Skin;

public class Skins {

	private static final double DEFAULT_SCALE = 0.25;
	public static Skins INSTANCE;

	public static final void init(TexturePack texturePack, int optionScale) {
		INSTANCE = new Skins(texturePack.getName(), texturePack.getTextureSize(), optionScale);
	}

	private static final String NODRAW_TEXTURE = "tools/toolsnodraw";

	public static final Skin NODRAW = new Skin(NODRAW_TEXTURE, DEFAULT_SCALE);
	public static final Skin TRIGGER = new Skin("tools/toolstrigger", DEFAULT_SCALE);
	public static final Skin SKYBOX = new Skin("tools/toolsskybox", DEFAULT_SCALE);
	public static final Skin PLAYER_CLIP = new Skin("tools/toolsplayerclip", DEFAULT_SCALE);
	public static final Skin LADDER = new Skin("tools/toolsinvisibleladder", DEFAULT_SCALE);

	public static final String DEFAULT_TEXTURE = "dev/dev_measurecrate02";

	private double textureScale;
	private Skin[] skinLegacy;

	private String folder;

	public Skin setSourceSkin(String texture) {
		return new Skin(texture, this.textureScale);
	}

	public Skin createSkin(String main) {
		return new Skin(this.folder + main, this.textureScale);
	}

	public Skin createSkin(NameSupplier main, NameSupplier topBottom) {
		return new Skin(this.folder + main.getName(), this.folder + topBottom.getName(), this.textureScale);
	}

	private Skin createSkinTopBottom(NameSupplier side, NameSupplier top, NameSupplier bottom) {

		return new Skin(this.folder + side.getName(), this.folder + top.getName(), this.folder + side.getName(),
				this.folder + bottom.getName(), Orientation.UP, this.textureScale);
	}

	private Skin createSkinTopBottom2(String side, NameSupplier top, String bottom) {

		return new Skin(side, this.folder + top.getName(), side, bottom, this.textureScale);
	}

	private Skin createSkinTopBottomDir(NameSupplier side, NameSupplier top, NameSupplier bottom, String axis) {

		return new Skin(this.folder + side.getName(), this.folder + top.getName(), this.folder + side.getName(),
				this.folder + bottom.getName(), axis, this.textureScale);
	}
//
 	/// only for use with terracotta since faces are adjusted for it only
	/// anything else will take the same face rotations
	/// it might be anything with an orientation of cardinal directions
	public Skin createSkinTopFront(NameSupplier main, NameSupplier top, NameSupplier front, Orientation orientation) {
		return new Skin(this.folder + main.getName(), this.folder + top.getName(), this.folder + front.getName(),
				orientation, this.textureScale);
	}
	public Skin createSkinTopFrontBottom(NameSupplier main, NameSupplier top, NameSupplier front, NameSupplier bottom, Orientation orientation) {
		return new Skin(this.folder + main.getName(), this.folder + top.getName(), this.folder + front.getName(),
				this.folder + bottom.getName(), orientation, this.textureScale, 0);
	}
	public Skin createSkinAllSides(NameSupplier top, NameSupplier front, NameSupplier bottom, NameSupplier back, NameSupplier left, NameSupplier right) {
		return new Skin(this.folder + top.getName(), this.folder + front.getName(), this.folder + bottom.getName(),
				this.folder + back.getName(), this.folder + left.getName(), this.folder + right.getName(), this.textureScale);
	}
//
//	public Skin createSkinTopFrontBottom(NameSupplier main, NameSupplier top, NameSupplier front, NameSupplier bottom) {
//		return new Skin(this.folder + main.getName(), this.folder + top.getName(), this.folder + front.getName(),
//				this.folder + bottom.getName(), Orientation.NORTH, this.textureScale);
//	}
//
//	public Skin createSkinTopFrontBottom(NameSupplier main, NameSupplier top, NameSupplier front, NameSupplier bottom,
//										 Orientation orientation) {
//		return new Skin(this.folder + main.getName(), this.folder + top.getName(), this.folder + front.getName(),
//				this.folder + bottom.getName(), orientation, this.textureScale);
//	}
	//me
//	public Skin createSkinFrontBack(NameSupplier main, NameSupplier front,  Orientation orientation) {
//		return new Skin(this.folder + main.getName(), this.folder + front.getName(),
//				orientation, this.textureScale);
//		//public Skin(String newMaterial, String newMaterialFront, Orientation orientation, double newScale)
//	}

	public Skins(String folder, int textureSizeNew, int scale) {
		this.init(folder, textureSizeNew, scale);
	}

	private BlockMap<Skin> skins;

	/**
	 * Returns also whether given material has suffix
	 *
	 * @param suffix
	 * @return
	 */
	private boolean putPrefixMadeSuffix(Material material, Material suffix) {
		String name = material.name();
		String _suf = suffix.name();
		if (name.endsWith(_suf)) { // except dark oak
			String suf_ = _suf.substring(1) + "_";
			String textureName = suf_ + name.substring(0, name.length() - suf_.length());
			this.put(material, textureName);
			return true;
		}
		return false;
	}

	public void init(String folder, int textureSizeNew, int scale) {
		this.textureScale = ((double) scale) / ((double) textureSizeNew);
		this.folder = folder + "/";
		this.skins = new BlockMap<Skin>().setDefault(new Skin(DEFAULT_TEXTURE, this.textureScale));

		// set based on matching texture name
		for (Texture texture : Texture.values()) {
			this.put(texture);
		}
		// set based on common
		for (Material material : Material.values()) {
			String name = material.name();

			if (name.endsWith("_log")) {
				this.put(material,
						this.createSkinTopBottomDir(() -> name, () -> name+"_top", () -> name+"_top", "y"));
			}
			// should wood/hyphae go here?
			if (name.endsWith("crimson_stem")) {
				this.put(material,
						this.createSkinTopBottomDir(() -> name, () -> name+"_top", () -> name+"_top", "y"));
			}
			if (name.endsWith("warped_stem")) {
				this.put(material,
						this.createSkinTopBottomDir(() -> name, () -> name+"_top", () -> name+"_top", "y"));
			}
			if (name.endsWith("bamboo_block")) {
				this.put(material,
						this.createSkinTopBottomDir(() -> name, () -> name+"_top", () -> name+"_top", "y"));
			}
			if (name.endsWith("_pressure_plate") && !name.startsWith("_")) {
				String mat = name.replace("_pressure_plate", "");

				if(mat.equals("light_weighted")) this.put(material, Texture.gold_block);
				else if(mat.equals("heavy_weighted")) this.put(material, Texture.iron_block);
				else if(mat.equals("stone")) this.put(material, Texture.stone);
				else if(mat.equals("polished_blackstone")) this.put(material, Texture.polished_blackstone);
				else this.put(material, Texture.valueOf(mat + "_planks"));
			}
			if (name.equals("target")) {
				this.put(material,
						this.createSkinTopBottomDir(Texture.target_side,
								Texture.target_top, Texture.target_top, "y"));
			}
			if (name.equals("quartz_pillar")) {
				this.put(material,
						this.createSkinTopBottomDir(Texture.quartz_pillar,
								Texture.quartz_pillar_top, Texture.quartz_pillar_top, "y"));
			}
			if (name.equals("muddy_mangrove_roots")) {
				this.put(material,
						this.createSkinTopBottomDir(Texture.muddy_mangrove_roots_side,
								Texture.muddy_mangrove_roots_top, Texture.muddy_mangrove_roots_top, "y"));
			}
			if (name.equals("ochre_froglight")) {
				this.put(material,
						this.createSkinTopBottomDir(Texture.ochre_froglight_side,
								Texture.ochre_froglight_top, Texture.ochre_froglight_top, "y"));
			}
			if (name.equals("verdant_froglight")) {
				this.put(material,
						this.createSkinTopBottomDir(Texture.verdant_froglight_side,
								Texture.verdant_froglight_top, Texture.verdant_froglight_top, "y"));
			}
			if (name.equals("pearlescent_froglight")) {
				this.put(material,
						this.createSkinTopBottomDir(Texture.pearlescent_froglight_side,
								Texture.pearlescent_froglight_top, Texture.pearlescent_froglight_top, "y"));
			}
			if (name.equals("chest")) {
				this.put(material, this.createSkinTopFront(() -> "chest_side", () -> "chest_top", () -> "chest_front",
						Orientation.NORTH));
			}
			if (name.equals("pumpkin")) {
				this.put(material, this.createSkinTopBottom(Texture.pumpkin_side, Texture.pumpkin_top, Texture.pumpkin_top));
			}
			if (name.equals("jack_o_lantern")) {
				this.put(material, this.createSkinTopFront(Texture.pumpkin_side, Texture.pumpkin_top, Texture.jack_o_lantern,
						Orientation.NORTH));
			}
			if (name.equals("carved_pumpkin")) {
				this.put(material, this.createSkinTopFront(Texture.pumpkin_side, Texture.pumpkin_top, Texture.carved_pumpkin,
						Orientation.NORTH));
			}
			if (name.equals("blast_furnace")) {
				this.put(material, this.createSkinTopFront(Texture.blast_furnace_side, Texture.blast_furnace_top, Texture.blast_furnace_front,
						Orientation.NORTH));
			}
			if (name.equals("smoker")) {
				this.put(material, this.createSkinTopFront(Texture.smoker_side, Texture.smoker_top, Texture.smoker_front,
						Orientation.NORTH));
			}
			if (name.equals("loom")) {
				this.put(material, this.createSkinTopFront(Texture.loom_side, Texture.loom_top, Texture.loom_front,
						Orientation.NORTH));
			}
			if (name.equals("beehive")) {
				this.put(material, this.createSkinTopFrontBottom(Texture.beehive_side, Texture.beehive_end, Texture.beehive_front,
						Texture.beehive_end, Orientation.NORTH));
			}
			if (name.equals("bee_nest")) {
				this.put(material, this.createSkinTopFrontBottom(Texture.bee_nest_side, Texture.bee_nest_top, Texture.bee_nest_front,
						Texture.bee_nest_bottom, Orientation.NORTH));
			}
			if (name.equals("cartography_table")) {
				this.put(material, this.createSkinAllSides(Texture.cartography_table_top, Texture.cartography_table_side1,
						Texture.cartography_table_side3, Texture.cartography_table_side3, Texture.cartography_table_side2,
						Texture.cartography_table_side3));
			}
			if (name.equals("smithing_table")) {
				this.put(material, this.createSkinAllSides(Texture.smithing_table_top, Texture.smithing_table_front,
						Texture.smithing_table_bottom, Texture.smithing_table_front, Texture.smithing_table_side,
						Texture.smithing_table_side));
			}
			if (name.equals("fletching_table")) {
				this.put(material, this.createSkinAllSides(Texture.fletching_table_top, Texture.fletching_table_front,
						Texture.birch_planks, Texture.fletching_table_front, Texture.fletching_table_side,
						Texture.fletching_table_side));
			}
			if (name.equals("sculk_catalyst")) {
				this.put(material, this.createSkinTopBottom(Texture.sculk_catalyst_side, Texture.sculk_catalyst_top, Texture.sculk_catalyst_bottom));
			}
		}

		this.put(Material.barrel, this.createSkinTopBottom(() -> "barrel_side", () -> "barrel_top", () -> "barrel_bottom"));
		this.put(Material.reinforced_deepslate, this.createSkinTopBottom(() -> "reinforced_deepslate_side",
				() -> "reinforced_deepslate_top", () -> "reinforced_deepslate_bottom"));
		//TODO 0-4 side variants, top and top_off variants
		this.put(Material.respawn_anchor, this.createSkinTopBottom(() -> "respawn_anchor_side0",
				() -> "respawn_anchor_top_off", () -> "respawn_anchor_bottom"));

		this.put(Material.stripped_acacia_wood, Texture.stripped_acacia_log);
		this.put(Material.acacia_wood, Texture.acacia_log);
		this.put(Material.acacia_slab, Texture.acacia_planks);
		this.put(Material.acacia_stairs, Texture.acacia_planks);

		this.put(Material.andesite_slab, Texture.andesite);
		this.put(Material.andesite_stairs, Texture.andesite);
		this.put(Material.bamboo_slab, Texture.bamboo_planks);
		this.put(Material.bamboo_stairs, Texture.bamboo_planks);
		this.put(Material.birch_slab, Texture.birch_planks);
		this.put(Material.birch_stairs, Texture.birch_planks);
		this.put(Material.black_carpet, Texture.black_wool);
		this.put(Material.black_concrete, Texture.black_concrete);
		this.put(Material.blue_carpet, Texture.blue_wool);
		this.put(Material.brick_slab, Texture.bricks);
		this.put(Material.brown_carpet, Texture.brown_wool);
		this.put(Material.cherry_wood, Texture.cherry_log);
		this.put(Material.stripped_cherry_wood, Texture.stripped_cherry_log);
		this.put(Material.cherry_stairs, Texture.cherry_planks);
		this.put(Material.cherry_slab, Texture.cherry_planks);

		this.put(Material.cobblestone_slab, Texture.cobblestone);
		this.put(Material.stripped_crimson_hyphae, Texture.stripped_crimson_stem);
		this.put(Material.infested_cracked_stone_bricks, Texture.cracked_stone_bricks);
		this.put(Material.cracked_stone_bricks, Texture.cracked_stone_bricks);
		this.put(Material.crimson_hyphae, Texture.crimson_stem);
		this.put(Material.crimson_stairs, Texture.crimson_planks);
		this.put(Material.crimson_slab, Texture.crimson_planks);
		this.put(Material.cut_red_sandstone_slab, Texture.cut_red_sandstone);
		this.put(Material.cut_sandstone_slab, Texture.cut_sandstone);
		this.put(Material.cyan_carpet, Texture.cyan_wool);

		this.put(Material.stripped_dark_oak_wood, Texture.stripped_dark_oak_log);
		this.put(Material.dark_oak_wood, Texture.dark_oak_log);
		this.put(Material.dark_oak_slab, Texture.dark_oak_planks);
		this.put(Material.dark_oak_stairs, Texture.dark_oak_planks);

		this.put(Material.dark_prismarine_slab, Texture.dark_prismarine);
		this.put(Material.diorite_slab, Texture.diorite);
		this.put(Material.diorite_stairs, Texture.diorite);
		this.put(Material.end_stone_brick_slab, Texture.end_stone_bricks);
		this.put(Material.granite_slab, Texture.granite);
		this.put(Material.granite_stairs, Texture.granite);
		this.put(Material.grass_block, Texture.grass_block_top);
		this.put(Material.gray_carpet, Texture.gray_wool);
		this.put(Material.green_carpet, Texture.green_wool);

		this.put(Material.stripped_jungle_wood, Texture.stripped_jungle_log);
		this.put(Material.jungle_wood, Texture.jungle_log);
		this.put(Material.jungle_slab, Texture.jungle_planks);
		this.put(Material.jungle_stairs, Texture.jungle_planks);

		this.put(Material.light_blue_carpet, Texture.light_blue_wool);
		this.put(Material.light_gray_carpet, Texture.light_gray_wool);
		this.put(Material.lime_carpet, Texture.lime_wool);
		this.put(Material.magenta_carpet, Texture.magenta_wool);
		this.put(Material.stripped_mangrove_wood, Texture.stripped_mangrove_log);
		this.put(Material.mangrove_wood, Texture.mangrove_log);
		this.put(Material.mangrove_stairs, Texture.mangrove_planks);
		this.put(Material.mangrove_slab, Texture.mangrove_planks);
		this.put(Material.moss_carpet, Texture.moss_block);
		this.put(Material.mossy_cobblestone_slab, Texture.mossy_cobblestone);
		this.put(Material.mossy_stone_brick_slab, Texture.mossy_stone_bricks);
		this.put(Material.nether_brick_slab, Texture.nether_bricks);

		this.put(Material.stripped_oak_wood, Texture.stripped_oak_log);
		this.put(Material.oak_wood, Texture.oak_log);
		this.put(Material.oak_slab, Texture.oak_planks);
		this.put(Material.oak_stairs, Texture.oak_planks);

		this.put(Material.orange_carpet, Texture.orange_wool);
		this.put(Material.stripped_pale_oak_wood, Texture.stripped_pale_oak_log);
		this.put(Material.pale_oak_wood, Texture.pale_oak_log);
		this.put(Material.pale_oak_slab, Texture.pale_oak_planks);
		this.put(Material.pale_oak_stairs, Texture.pale_oak_planks);
		this.put(Material.pink_carpet, Texture.pink_wool);
		this.put(Material.polished_andesite_slab, Texture.polished_andesite);
		this.put(Material.polished_diorite_slab, Texture.polished_diorite);
		this.put(Material.polished_granite_slab, Texture.polished_granite);
		this.put(Material.prismarine_brick_slab, Texture.prismarine_bricks);
		this.put(Material.prismarine_slab, Texture.prismarine);
		this.put(Material.purple_carpet, Texture.purple_wool);
		this.put(Material.purpur_stairs, Texture.purpur_block);
		this.put(Material.red_carpet, Texture.red_wool);
		this.put(Material.red_nether_brick_slab, Texture.red_nether_bricks);
		this.put(Material.red_sandstone_slab, Texture.red_sandstone);
		this.put(Material.sandstone_slab, Texture.sandstone);
		this.put(Material.smooth_stone_slab, Texture.smooth_stone);

		this.put(Material.stripped_spruce_wood, Texture.stripped_spruce_log);
		this.put(Material.spruce_wood, Texture.spruce_log);
		this.put(Material.spruce_slab, Texture.spruce_planks);
		this.put(Material.spruce_stairs, Texture.spruce_planks);

		this.put(Material.stone_brick_slab, Texture.stone_bricks);
		this.put(Material.stripped_warped_hyphae, Texture.stripped_warped_stem);
		this.put(Material.warped_hyphae, Texture.warped_stem);
		this.put(Material.warped_stairs, Texture.warped_planks);
		this.put(Material.warped_slab, Texture.warped_planks);

		//brick wall
		this.put(Material.stone_brick_wall, Texture.stone_bricks);
		this.put(Material.mossy_stone_brick_wall, Texture.mossy_stone_bricks);
		this.put(Material.nether_brick_wall, Texture.nether_bricks);
		this.put(Material.brick_wall, Texture.bricks);

		this.put(Material.stone_slab, Texture.stone);
		this.put(Material.white_carpet, Texture.white_wool);
		this.put(Material.yellow_carpet, Texture.yellow_wool);

		// manually added
		this.put(Material.quartz_block, Texture.quartz_block_top);
		this.put(Material.smooth_quartz, Texture.quartz_block_bottom);
		this.put(Material.smooth_quartz_slab, Texture.quartz_block_bottom);
		this.put(Material.smooth_quartz_stairs, Texture.quartz_block_bottom);
		this.put(Material.hay_block, Texture.hay_block_side);
		this.put(Material.stripped_oak_wood, Texture.stripped_oak_log);

		// trapdoor
		this.put(Material.waxed_copper_trapdoor, Texture.copper_trapdoor);
		this.put(Material.waxed_exposed_copper_trapdoor, Texture.exposed_copper_trapdoor);
		this.put(Material.waxed_weathered_copper_trapdoor, Texture.weathered_copper_trapdoor);
		this.put(Material.waxed_oxidized_copper_trapdoor, Texture.oxidized_copper_trapdoor);

		this.put(Material.smooth_red_sandstone, Texture.red_sandstone_top);
		this.put(Material.smooth_red_sandstone_slab, Texture.red_sandstone_top);
		this.put(Material.smooth_sandstone_slab, Texture.sandstone_top);
		this.put(Material.smooth_sandstone_stairs, Texture.sandstone_top);
		this.put(Material.sandstone_stairs, Texture.sandstone);
		this.put(Material.red_nether_brick_stairs, Texture.red_nether_bricks);
		//this.put(Material.red_sandstone_stairs, Texture.red_sandstone);
		this.put(Material.smooth_red_sandstone_stairs, Texture.red_sandstone_top);
		this.put(Material.stone_brick_stairs, Texture.stone_bricks);
		this.put(Material.purpur_slab, Texture.purpur_block);
//		this.put(Material.purpur_stairs, Texture.purpur_block);
		this.put(Material.petrified_oak_slab, Texture.oak_planks);
		this.put(Material.mossy_stone_brick_stairs, Texture.mossy_stone_bricks);
		this.put(Material.nether_brick_stairs, Texture.nether_bricks);
		this.put(Material.quartz_slab, Texture.quartz_block_top);
		this.put(Material.quartz_stairs, Texture.quartz_block_top);
		this.put(Material.end_stone_brick_stairs, Texture.end_stone_bricks);
		this.put(Material.end_stone_brick_slab, Texture.end_stone_bricks);
		this.put(Material.prismarine_brick_stairs, Texture.prismarine_bricks);
		this.put(Material.prismarine_brick_slab, Texture.prismarine_bricks);
		this.put(Material.redstone_wire, Texture.redstone_dust_line0);
		this.put(Material.command_block, Texture.command_block_front);
		this.put(Material.repeating_command_block, Texture.repeating_command_block_front);
		this.put(Material.chain_command_block, Texture.chain_command_block_front);

		//temp
		this.put(Material.bamboo, Texture.bamboo_stalk);
		this.put(Material.crafting_table, Texture.crafting_table_side);
		this.put(Material.grindstone, Texture.grindstone_side);
		this.put(Material.bell, Texture.bell_side);
		this.put(Material.hopper, Texture.hopper_outside);

		this.put(Material.campfire, Texture.campfire_log);
		this.put(Material.piston_head, Texture.piston_top);
		//this.put(Material.bamboo_sapling, Texture.bamboo_singleleaf);
		this.put(Material.sticky_piston, Texture.piston_side);
		this.put(Material.piston, Texture.piston_side);

		//this.put(Material.heavy_weighted_pressure_plate, Texture.iron_block);
		//this.put(Material.light_weighted_pressure_plate, Texture.gold_block);


		//this.put(Material.lantern, Texture.lantern);

		this.put(Material.lantern,
				this.createSkin(Texture.lantern, Texture.lantern));

		this.put(Material.sea_lantern,
				this.createSkin(Texture.sea_lantern, Texture.sea_lantern));

		// sides and top block
		this.put(Material.sandstone,
				this.createSkin(Texture.sandstone, Texture.sandstone_top));
		this.put(Material.chiseled_sandstone,
				this.createSkin(Texture.chiseled_sandstone, Texture.sandstone_top));
		this.put(Material.cut_sandstone,
				this.createSkin(Texture.cut_sandstone, Texture.sandstone_top));

		this.put(Material.red_sandstone_stairs,
				this.createSkinTopBottom(Texture.red_sandstone_bottom, Texture.red_sandstone_top, Texture.red_sandstone));
		this.put(Material.red_sandstone_slab,
				this.createSkinTopBottom(Texture.red_sandstone_bottom, Texture.red_sandstone_top, Texture.red_sandstone));

		this.put(Material.chiseled_quartz_block,
				this.createSkin(Texture.chiseled_quartz_block, Texture.chiseled_quartz_block_top));
		this.put(Material.cactus,
				this.createSkin(Texture.cactus_side, Texture.cactus_top));
		this.put(Material.scaffolding,
				this.createSkinTopBottom(Texture.scaffolding_bottom, Texture.scaffolding_top, Texture.scaffolding_side));

		// side, top and bottom
		this.put(Material.grass_block,
				this.createSkinTopBottom(Texture.grass_block_side, Texture.grass_block_top, Texture.dirt));
		this.put(Material.dirt_path,
				this.createSkinTopBottom(Texture.dirt_path_side, Texture.dirt_path_top, Texture.dirt));
		this.put(Material.bamboo,
				// would need to include other skins to make up model?
				this.createSkinTopBottom(Texture.bamboo_stalk, Texture.bamboo_stalk, Texture.bamboo_stalk));
		this.put(Material.water,
				this.createSkinTopBottom2(this.folder + Texture.water_flow.getName(), Texture.water_still,
						this.folder + Texture.water_flow.getName()));

		//TODO lava not working, material may not be created.
		this.put(Material.lava,
				this.createSkinTopBottom2(this.folder + Texture.lava_flow.getName(), Texture.lava_still, this.folder + Texture.lava_flow.getName()));

		this.skins.put(Blocks.get("sourcecraft:ramp"), PLAYER_CLIP);
		this.skins.put(Blocks.get("sourcecraft:ladder"), TRIGGER);

	}

	private void put(Texture block) {
		this.put(block, block);
	}

	private void put(Supplier<Block> block, Texture texture) {
		this.put(block, this.createSkin(texture.name()));
	}

	private void put(Supplier<Block> block, String texture) {
		this.put(block, this.createSkin(texture));
	}

	private void put(Supplier<Block> block, Skin skin) {
		this.skins.put(block, skin);
	}

	public Skin getSkin(Block block) {
		return this.skins.getFallBackToPrefix(block);
	}
}
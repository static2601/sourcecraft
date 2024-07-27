package converter;

import java.util.function.Supplier;

import basic.NameSupplier;
import converter.actions.BlockMap;
//import converter.actions.actions.NoAction;
import minecraft.Block;
import minecraft.Blocks;
import minecraft.Material;
//import minecraft.Property;
import minecraft.Texture;
import periphery.TexturePack;
//import converter.Orientation;
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
	private static final Skin PLAYER_CLIP = new Skin("tools/toolsplayerclip", DEFAULT_SCALE);
	private static final Skin STONE = new Skin("minecraft_original/stone", DEFAULT_SCALE);

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
				this.folder + bottom.getName(), Orientation.NORTH, this.textureScale);
	}

//	public Skin createSkinTopFront(NameSupplier main, NameSupplier top, NameSupplier front, Orientation orientation) {
//		return new Skin(this.folder + main.getName(), this.folder + top.getName(), this.folder + front.getName(),
//				orientation, this.textureScale);
//	}

//	public Skin createSkinTopFrontBottom(NameSupplier main, NameSupplier top, NameSupplier front, NameSupplier bottom) {
//		return new Skin(this.folder + main.getName(), this.folder + top.getName(), this.folder + front.getName(),
//				this.folder + bottom.getName(), Orientation.NORTH, this.textureScale);
//	}

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
	// (this.putPrefixMadeSuffix(material, Material._stairs))
	private boolean putPrefixMadeSuffix(Material material, Material suffix) {
		String name = material.name();
		String _suf = suffix.name();
		if (name.endsWith(_suf)) { // except dark oak
			String suf_ = _suf.substring(1) + "_";// stairs
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
		// this.skins.put(Blocks.get("sourcecraft:ramp"), PLAYER_CLIP);

		this.put(Material.acacia_leaves, Texture.acacia_leaves);
		this.put(Material.acacia_log, Texture.acacia_log);
		this.put(Material.acacia_planks, Texture.acacia_planks);
		this.put(Material.acacia_sapling, Texture.acacia_sapling);
		this.put(Material.acacia_slab, Texture.acacia_planks);
		this.put(Material.acacia_stairs, Texture.acacia_planks);
		this.put(Material.acacia_trapdoor, Texture.acacia_trapdoor);
		this.put(Material.activator_rail, Texture.activator_rail);
		this.put(Material.allium, Texture.allium);
		this.put(Material.andesite, Texture.andesite);
		this.put(Material.andesite_slab, Texture.andesite);
		this.put(Material.andesite_stairs, Texture.andesite);
		this.put(Material.anvil, Texture.anvil);
		this.put(Material.attached_melon_stem, Texture.attached_melon_stem);
		this.put(Material.attached_pumpkin_stem, Texture.attached_pumpkin_stem);
		this.put(Material.azure_bluet, Texture.azure_bluet);
		this.put(Material.beacon, Texture.beacon);
		this.put(Material.bedrock, Texture.bedrock);
		this.put(Material.birch_leaves, Texture.birch_leaves);
		this.put(Material.birch_log, Texture.birch_log);
		this.put(Material.birch_planks, Texture.birch_planks);
		this.put(Material.birch_sapling, Texture.birch_sapling);
		this.put(Material.birch_slab, Texture.birch_planks);
		this.put(Material.birch_stairs, Texture.birch_planks);
		this.put(Material.birch_trapdoor, Texture.birch_trapdoor);
		this.put(Material.black_carpet, Texture.black_wool);
		this.put(Material.black_concrete, Texture.black_concrete);
		this.put(Material.black_concrete_powder, Texture.black_concrete_powder);
		this.put(Material.black_glazed_terracotta, Texture.black_glazed_terracotta);
		this.put(Material.black_shulker_box, Texture.black_shulker_box);
		this.put(Material.black_stained_glass, Texture.black_stained_glass);
		this.put(Material.black_terracotta, Texture.black_terracotta);
		this.put(Material.black_wool, Texture.black_wool);
		this.put(Material.blue_carpet, Texture.blue_wool);
		this.put(Material.blue_concrete, Texture.blue_concrete);
		this.put(Material.blue_concrete_powder, Texture.blue_concrete_powder);
		this.put(Material.blue_glazed_terracotta, Texture.blue_glazed_terracotta);
		this.put(Material.blue_ice, Texture.blue_ice);
		this.put(Material.blue_orchid, Texture.blue_orchid);
		this.put(Material.blue_shulker_box, Texture.blue_shulker_box);
		this.put(Material.blue_stained_glass, Texture.blue_stained_glass);
		this.put(Material.blue_terracotta, Texture.blue_terracotta);
		this.put(Material.blue_wool, Texture.blue_wool);
		this.put(Material.bookshelf, Texture.bookshelf);
		this.put(Material.brain_coral, Texture.brain_coral);
		this.put(Material.brain_coral_block, Texture.brain_coral_block);
		this.put(Material.brain_coral_fan, Texture.brain_coral_fan);
		this.put(Material.brewing_stand, Texture.brewing_stand);
		this.put(Material.brick_slab, Texture.bricks);
		this.put(Material.bricks, Texture.bricks);
		this.put(Material.brown_carpet, Texture.brown_wool);
		this.put(Material.brown_concrete, Texture.brown_concrete);
		this.put(Material.brown_concrete_powder, Texture.brown_concrete_powder);
		this.put(Material.brown_glazed_terracotta, Texture.brown_glazed_terracotta);
		this.put(Material.brown_mushroom, Texture.brown_mushroom);
		this.put(Material.brown_mushroom_block, Texture.brown_mushroom_block);
		this.put(Material.brown_shulker_box, Texture.brown_shulker_box);
		this.put(Material.brown_stained_glass, Texture.brown_stained_glass);
		this.put(Material.brown_terracotta, Texture.brown_terracotta);
		this.put(Material.brown_wool, Texture.brown_wool);
		this.put(Material.bubble_coral, Texture.bubble_coral);
		this.put(Material.bubble_coral_block, Texture.bubble_coral_block);
		this.put(Material.bubble_coral_fan, Texture.bubble_coral_fan);
		this.put(Material.carved_pumpkin, Texture.carved_pumpkin);
		this.put(Material.chiseled_quartz_block, Texture.chiseled_quartz_block);
		this.put(Material.chiseled_red_sandstone, Texture.chiseled_red_sandstone);
		this.put(Material.chiseled_sandstone, Texture.chiseled_sandstone);
		this.put(Material.chiseled_stone_bricks, Texture.chiseled_stone_bricks);
		this.put(Material.chorus_flower, Texture.chorus_flower);
		this.put(Material.chorus_plant, Texture.chorus_plant);
		this.put(Material.clay, Texture.clay);
		this.put(Material.coal_ore, Texture.coal_ore);
		this.put(Material.coarse_dirt, Texture.coarse_dirt);
		this.put(Material.cobblestone, Texture.cobblestone);
		this.put(Material.cobblestone_slab, Texture.cobblestone);
		this.put(Material.cobweb, Texture.cobweb);
		this.put(Material.conduit, Texture.conduit);
		this.put(Material.cornflower, Texture.cornflower);
		this.put(Material.cracked_stone_bricks, Texture.cracked_stone_bricks);
		this.put(Material.cut_red_sandstone, Texture.cut_red_sandstone);
		this.put(Material.cut_red_sandstone_slab, Texture.cut_red_sandstone);
		//this.put(Material.cut_sandstone, Texture.cut_sandstone);
		//this.put(Material.cut_sandstone_slab, Texture.cut_sandstone);
		this.put(Material.cyan_carpet, Texture.cyan_wool);
		this.put(Material.cyan_concrete, Texture.cyan_concrete);
		this.put(Material.cyan_concrete_powder, Texture.cyan_concrete_powder);
		this.put(Material.cyan_glazed_terracotta, Texture.cyan_glazed_terracotta);
		this.put(Material.cyan_shulker_box, Texture.cyan_shulker_box);
		this.put(Material.cyan_stained_glass, Texture.cyan_stained_glass);
		this.put(Material.cyan_terracotta, Texture.cyan_terracotta);
		this.put(Material.cyan_wool, Texture.cyan_wool);
		this.put(Material.dandelion, Texture.dandelion);
		this.put(Material.dark_oak_leaves, Texture.dark_oak_leaves);
		this.put(Material.dark_oak_log, Texture.dark_oak_log);
		this.put(Material.dark_oak_planks, Texture.dark_oak_planks);
		this.put(Material.dark_oak_sapling, Texture.dark_oak_sapling);
		this.put(Material.dark_oak_slab, Texture.dark_oak_planks);
		this.put(Material.dark_oak_stairs, Texture.dark_oak_planks);
		this.put(Material.dark_oak_stairs, Texture.dark_oak_planks);
		this.put(Material.dark_oak_trapdoor, Texture.dark_oak_trapdoor);
		this.put(Material.dark_prismarine, Texture.dark_prismarine);
		this.put(Material.dark_prismarine_slab, Texture.dark_prismarine);
		this.put(Material.dead_brain_coral, Texture.dead_brain_coral);
		this.put(Material.dead_brain_coral_block, Texture.dead_brain_coral_block);
		this.put(Material.dead_brain_coral_fan, Texture.dead_brain_coral_fan);
		this.put(Material.dead_bubble_coral, Texture.dead_bubble_coral);
		this.put(Material.dead_bubble_coral_block, Texture.dead_bubble_coral_block);
		this.put(Material.dead_bubble_coral_fan, Texture.dead_bubble_coral_fan);
		this.put(Material.dead_bush, Texture.dead_bush);
		this.put(Material.dead_fire_coral, Texture.dead_fire_coral);
		this.put(Material.dead_fire_coral_block, Texture.dead_fire_coral_block);
		this.put(Material.dead_fire_coral_fan, Texture.dead_fire_coral_fan);
		this.put(Material.dead_horn_coral, Texture.dead_horn_coral);
		this.put(Material.dead_horn_coral_block, Texture.dead_horn_coral_block);
		this.put(Material.dead_horn_coral_fan, Texture.dead_horn_coral_fan);
		this.put(Material.dead_tube_coral, Texture.dead_tube_coral);
		this.put(Material.dead_tube_coral_block, Texture.dead_tube_coral_block);
		this.put(Material.dead_tube_coral_fan, Texture.dead_tube_coral_fan);
		this.put(Material.detector_rail, Texture.detector_rail);
		this.put(Material.diamond_ore, Texture.diamond_ore);
		this.put(Material.diorite, Texture.diorite);
		this.put(Material.diorite_slab, Texture.diorite);
		this.put(Material.diorite_stairs, Texture.diorite);
		this.put(Material.dirt, Texture.dirt);
		this.put(Material.dragon_egg, Texture.dragon_egg);
		this.put(Material.emerald_ore, Texture.emerald_ore);
		this.put(Material.end_rod, Texture.end_rod);
		this.put(Material.end_stone, Texture.end_stone);
		this.put(Material.end_stone_brick_slab, Texture.end_stone_bricks);

		this.put(Material.end_stone_bricks, Texture.end_stone_bricks);
		this.put(Material.farmland, Texture.farmland);
		this.put(Material.fern, Texture.fern);
		this.put(Material.fire_coral, Texture.fire_coral);
		this.put(Material.fire_coral_block, Texture.fire_coral_block);
		this.put(Material.fire_coral_fan, Texture.fire_coral_fan);
		this.put(Material.flower_pot, Texture.flower_pot);
		this.put(Material.glass, Texture.glass);
		this.put(Material.glowstone, Texture.glowstone);
		this.put(Material.gold_ore, Texture.gold_ore);
		this.put(Material.granite, Texture.granite);
		this.put(Material.granite_slab, Texture.granite);
		this.put(Material.granite_stairs, Texture.granite);
		this.put(Material.grass_block, Texture.grass);
		this.put(Material.gravel, Texture.gravel);
		this.put(Material.gray_carpet, Texture.gray_wool);
		this.put(Material.gray_concrete, Texture.gray_concrete);
		this.put(Material.gray_concrete_powder, Texture.gray_concrete_powder);
		this.put(Material.gray_glazed_terracotta, Texture.gray_glazed_terracotta);
		this.put(Material.gray_shulker_box, Texture.gray_shulker_box);
		this.put(Material.gray_stained_glass, Texture.gray_stained_glass);
		this.put(Material.gray_terracotta, Texture.gray_terracotta);
		this.put(Material.gray_wool, Texture.gray_wool);
		this.put(Material.green_carpet, Texture.green_wool);
		this.put(Material.green_concrete, Texture.green_concrete);
		this.put(Material.green_concrete_powder, Texture.green_concrete_powder);
		this.put(Material.green_glazed_terracotta, Texture.green_glazed_terracotta);
		this.put(Material.green_shulker_box, Texture.green_shulker_box);
		this.put(Material.green_stained_glass, Texture.green_stained_glass);
		this.put(Material.green_terracotta, Texture.green_terracotta);
		this.put(Material.green_wool, Texture.green_wool);
		this.put(Material.honeycomb_block, Texture.honeycomb_block);
		this.put(Material.horn_coral, Texture.horn_coral);
		this.put(Material.horn_coral_block, Texture.horn_coral_block);
		this.put(Material.horn_coral_fan, Texture.horn_coral_fan);
		this.put(Material.ice, Texture.ice);
		this.put(Material.iron_bars, Texture.iron_bars);
		this.put(Material.iron_ore, Texture.iron_ore);
		this.put(Material.iron_trapdoor, Texture.iron_trapdoor);
		this.put(Material.jack_o_lantern, Texture.jack_o_lantern);
		this.put(Material.jungle_leaves, Texture.jungle_leaves);
		this.put(Material.jungle_log, Texture.jungle_log);
		this.put(Material.jungle_planks, Texture.jungle_planks);
		this.put(Material.jungle_sapling, Texture.jungle_sapling);
		this.put(Material.jungle_slab, Texture.jungle_planks);
		this.put(Material.jungle_stairs, Texture.jungle_planks);
		this.put(Material.jungle_trapdoor, Texture.jungle_trapdoor);
		this.put(Material.kelp, Texture.kelp);
		this.put(Material.kelp_plant, Texture.kelp_plant);
		this.put(Material.ladder, Texture.ladder);
		this.put(Material.lantern, Texture.lantern);
		this.put(Material.lapis_block, Texture.lapis_block);
		this.put(Material.lapis_ore, Texture.lapis_ore);
		this.put(Material.lever, Texture.lever);
		this.put(Material.light_blue_carpet, Texture.light_blue_wool);
		this.put(Material.light_blue_concrete, Texture.light_blue_concrete);
		this.put(Material.light_blue_concrete_powder, Texture.light_blue_concrete_powder);
		this.put(Material.light_blue_glazed_terracotta, Texture.light_blue_glazed_terracotta);
		this.put(Material.light_blue_shulker_box, Texture.light_blue_shulker_box);
		this.put(Material.light_blue_stained_glass, Texture.light_blue_stained_glass);
		this.put(Material.light_blue_terracotta, Texture.light_blue_terracotta);
		this.put(Material.light_blue_wool, Texture.light_blue_wool);
		this.put(Material.light_gray_carpet, Texture.light_gray_wool);
		this.put(Material.light_gray_concrete, Texture.light_gray_concrete);
		this.put(Material.light_gray_concrete_powder, Texture.light_gray_concrete_powder);
		this.put(Material.light_gray_glazed_terracotta, Texture.light_gray_glazed_terracotta);
		this.put(Material.light_gray_shulker_box, Texture.light_gray_shulker_box);
		this.put(Material.light_gray_stained_glass, Texture.light_gray_stained_glass);
		this.put(Material.light_gray_terracotta, Texture.light_gray_terracotta);
		this.put(Material.light_gray_wool, Texture.light_gray_wool);
		this.put(Material.lily_pad, Texture.lily_pad);
		this.put(Material.lily_of_the_valley, Texture.lily_of_the_valley);
		this.put(Material.lime_carpet, Texture.lime_wool);
		this.put(Material.lime_concrete, Texture.lime_concrete);
		this.put(Material.lime_concrete_powder, Texture.lime_concrete_powder);
		this.put(Material.lime_glazed_terracotta, Texture.lime_glazed_terracotta);
		this.put(Material.lime_shulker_box, Texture.lime_shulker_box);
		this.put(Material.lime_stained_glass, Texture.lime_stained_glass);
		this.put(Material.lime_terracotta, Texture.lime_terracotta);
		this.put(Material.lime_wool, Texture.lime_wool);
		this.put(Material.magenta_carpet, Texture.magenta_wool);
		this.put(Material.magenta_concrete, Texture.magenta_concrete);
		this.put(Material.magenta_concrete_powder, Texture.magenta_concrete_powder);
		this.put(Material.magenta_glazed_terracotta, Texture.magenta_glazed_terracotta);
		this.put(Material.magenta_shulker_box, Texture.magenta_shulker_box);
		this.put(Material.magenta_stained_glass, Texture.magenta_stained_glass);
		this.put(Material.magenta_terracotta, Texture.magenta_terracotta);
		this.put(Material.magenta_wool, Texture.magenta_wool);
		this.put(Material.melon_stem, Texture.melon_stem);
		this.put(Material.mossy_cobblestone, Texture.mossy_cobblestone);
		this.put(Material.mossy_cobblestone_slab, Texture.mossy_cobblestone);
		this.put(Material.mossy_stone_brick_slab, Texture.mossy_stone_bricks);

		this.put(Material.mossy_stone_bricks, Texture.mossy_stone_bricks);
		this.put(Material.mushroom_stem, Texture.mushroom_stem);
		this.put(Material.nether_brick_slab, Texture.nether_bricks);

		this.put(Material.nether_bricks, Texture.nether_bricks);
		this.put(Material.nether_portal, Texture.nether_portal);
		this.put(Material.nether_quartz_ore, Texture.nether_quartz_ore);
		this.put(Material.nether_wart_block, Texture.nether_wart_block);
		this.put(Material.netherrack, Texture.netherrack);
		this.put(Material.note_block, Texture.note_block);
		this.put(Material.oak_leaves, Texture.oak_leaves);
		this.put(Material.oak_log, Texture.oak_log);
		this.put(Material.oak_planks, Texture.oak_planks);
		this.put(Material.oak_sapling, Texture.oak_sapling);
		this.put(Material.oak_slab, Texture.oak_planks);
		this.put(Material.oak_stairs, Texture.oak_planks);
		this.put(Material.oak_trapdoor, Texture.oak_trapdoor);
		this.put(Material.obsidian, Texture.obsidian);
		this.put(Material.orange_carpet, Texture.orange_wool);
		this.put(Material.orange_concrete, Texture.orange_concrete);
		this.put(Material.orange_concrete_powder, Texture.orange_concrete_powder);
		this.put(Material.orange_glazed_terracotta, Texture.orange_glazed_terracotta);
		this.put(Material.orange_shulker_box, Texture.orange_shulker_box);
		this.put(Material.orange_stained_glass, Texture.orange_stained_glass);
		this.put(Material.orange_terracotta, Texture.orange_terracotta);
		this.put(Material.orange_tulip, Texture.orange_tulip);
		this.put(Material.orange_wool, Texture.orange_wool);
		this.put(Material.oxeye_daisy, Texture.oxeye_daisy);
		this.put(Material.packed_ice, Texture.packed_ice);

		this.put(Material.pink_carpet, Texture.pink_wool);
		this.put(Material.pink_concrete, Texture.pink_concrete);
		this.put(Material.pink_concrete_powder, Texture.pink_concrete_powder);
		this.put(Material.pink_glazed_terracotta, Texture.pink_glazed_terracotta);
		this.put(Material.pink_shulker_box, Texture.pink_shulker_box);
		this.put(Material.pink_stained_glass, Texture.pink_stained_glass);
		this.put(Material.pink_terracotta, Texture.pink_terracotta);
		this.put(Material.pink_tulip, Texture.pink_tulip);
		this.put(Material.pink_wool, Texture.pink_wool);
		this.put(Material.polished_andesite, Texture.polished_andesite);
		this.put(Material.polished_andesite_slab, Texture.polished_andesite);
		this.put(Material.polished_diorite, Texture.polished_diorite);
		this.put(Material.polished_diorite_slab, Texture.polished_diorite);
		this.put(Material.polished_granite, Texture.polished_granite);
		this.put(Material.polished_granite_slab, Texture.polished_granite);
		this.put(Material.poppy, Texture.poppy);
		this.put(Material.powered_rail, Texture.powered_rail);
		this.put(Material.prismarine, Texture.prismarine);
		this.put(Material.prismarine_brick_slab, Texture.prismarine_bricks);
		this.put(Material.prismarine_bricks, Texture.prismarine_bricks);
		this.put(Material.prismarine_slab, Texture.prismarine);
		this.put(Material.pumpkin_stem, Texture.pumpkin_stem);
		this.put(Material.purple_carpet, Texture.purple_wool);
		this.put(Material.purple_concrete, Texture.purple_concrete);
		this.put(Material.purple_concrete_powder, Texture.purple_concrete_powder);
		this.put(Material.purple_glazed_terracotta, Texture.purple_glazed_terracotta);
		this.put(Material.purple_shulker_box, Texture.purple_shulker_box);
		this.put(Material.purple_stained_glass, Texture.purple_stained_glass);
		this.put(Material.purple_terracotta, Texture.purple_terracotta);
		this.put(Material.purple_wool, Texture.purple_wool);
		this.put(Material.purpur_block, Texture.purpur_block);
		this.put(Material.purpur_pillar, Texture.purpur_pillar);

		this.put(Material.quartz_pillar, Texture.quartz_pillar);

		this.put(Material.rail, Texture.rail);
		this.put(Material.red_carpet, Texture.red_wool);
		this.put(Material.red_concrete, Texture.red_concrete);
		this.put(Material.red_concrete_powder, Texture.red_concrete_powder);
		this.put(Material.red_glazed_terracotta, Texture.red_glazed_terracotta);
		this.put(Material.red_mushroom, Texture.red_mushroom);
		this.put(Material.red_mushroom_block, Texture.red_mushroom_block);
		this.put(Material.red_nether_brick_slab, Texture.red_nether_bricks);

		this.put(Material.red_nether_bricks, Texture.red_nether_bricks);
		this.put(Material.red_sand, Texture.red_sand);
		this.put(Material.red_sandstone, Texture.red_sandstone);
		this.put(Material.red_sandstone_slab, Texture.red_sandstone);
		this.put(Material.red_shulker_box, Texture.red_shulker_box);
		this.put(Material.red_stained_glass, Texture.red_stained_glass);
		this.put(Material.red_terracotta, Texture.red_terracotta);
		this.put(Material.red_tulip, Texture.red_tulip);
		this.put(Material.red_wool, Texture.red_wool);
		this.put(Material.redstone_lamp, Texture.redstone_lamp);
		this.put(Material.redstone_ore, Texture.redstone_ore);
		this.put(Material.redstone_torch, Texture.redstone_torch);
		this.put(Material.sand, Texture.sand);
		//this.put(Material.sandstone, Texture.sandstone);
		this.put(Material.sandstone_slab, Texture.sandstone);
		this.put(Material.sea_lantern, Texture.sea_lantern);
		this.put(Material.sea_pickle, Texture.sea_pickle);
		this.put(Material.seagrass, Texture.seagrass);
		this.put(Material.shulker_box, Texture.shulker_box);
		this.put(Material.slime_block, Texture.slime_block);

		this.put(Material.smooth_stone, Texture.smooth_stone);
		this.put(Material.smooth_stone_slab, Texture.smooth_stone);
		this.put(Material.snow, Texture.snow);
		this.put(Material.soul_sand, Texture.soul_sand);
		this.put(Material.spawner, Texture.spawner);
		this.put(Material.sponge, Texture.sponge);
		this.put(Material.spruce_leaves, Texture.spruce_leaves);
		this.put(Material.spruce_log, Texture.spruce_log);
		this.put(Material.spruce_planks, Texture.spruce_planks);
		this.put(Material.spruce_sapling, Texture.spruce_sapling);
		this.put(Material.spruce_slab, Texture.spruce_planks);
		this.put(Material.spruce_stairs, Texture.spruce_planks);
		this.put(Material.spruce_trapdoor, Texture.spruce_trapdoor);
		this.put(Material.stone, Texture.stone);
		this.put(Material.stone_brick_slab, Texture.stone_bricks);

		//brick wall
		this.put(Material.stone_brick_wall, Texture.stone_bricks);
		this.put(Material.mossy_stone_brick_wall, Texture.mossy_stone_bricks);
		this.put(Material.nether_brick_wall, Texture.nether_bricks);
		this.put(Material.brick_wall, Texture.bricks);

		this.put(Material.stone_bricks, Texture.stone_bricks);
		this.put(Material.stone_slab, Texture.stone);
		this.put(Material.stripped_acacia_log, Texture.stripped_acacia_log);
		this.put(Material.stripped_birch_log, Texture.stripped_birch_log);
		this.put(Material.stripped_dark_oak_log, Texture.stripped_dark_oak_log);
		this.put(Material.stripped_jungle_log, Texture.stripped_jungle_log);
		this.put(Material.stripped_oak_log, Texture.stripped_oak_log);
		this.put(Material.stripped_spruce_log, Texture.stripped_spruce_log);
		this.put(Material.structure_block, Texture.structure_block);
		this.put(Material.sugar_cane, Texture.sugar_cane);
		this.put(Material.terracotta, Texture.terracotta);
		this.put(Material.torch, Texture.torch);
		this.put(Material.tripwire, Texture.tripwire);
		this.put(Material.tripwire_hook, Texture.tripwire_hook);
		this.put(Material.tube_coral, Texture.tube_coral);
		this.put(Material.tube_coral_block, Texture.tube_coral_block);
		this.put(Material.tube_coral_fan, Texture.tube_coral_fan);
		this.put(Material.turtle_egg, Texture.turtle_egg);
		this.put(Material.wet_sponge, Texture.wet_sponge);
		this.put(Material.white_carpet, Texture.white_wool);
		this.put(Material.white_concrete, Texture.white_concrete);
		this.put(Material.white_concrete_powder, Texture.white_concrete_powder);
		this.put(Material.white_glazed_terracotta, Texture.white_glazed_terracotta);
		this.put(Material.white_shulker_box, Texture.white_shulker_box);
		this.put(Material.white_stained_glass, Texture.white_stained_glass);
		this.put(Material.white_terracotta, Texture.white_terracotta);
		this.put(Material.white_tulip, Texture.white_tulip);
		this.put(Material.white_wool, Texture.white_wool);
		this.put(Material.wither_rose, Texture.wither_rose);
		this.put(Material.yellow_carpet, Texture.yellow_wool);
		this.put(Material.yellow_concrete, Texture.yellow_concrete);
		this.put(Material.yellow_concrete_powder, Texture.yellow_concrete_powder);
		this.put(Material.yellow_glazed_terracotta, Texture.yellow_glazed_terracotta);
		this.put(Material.yellow_shulker_box, Texture.yellow_shulker_box);
		this.put(Material.yellow_stained_glass, Texture.yellow_stained_glass);
		this.put(Material.yellow_terracotta, Texture.yellow_terracotta);
		this.put(Material.yellow_wool, Texture.yellow_wool);

		// manually added
		this.put(Material.quartz_block, Texture.quartz_block_top);
		this.put(Material.smooth_quartz, Texture.quartz_block_bottom);
		this.put(Material.smooth_quartz_slab, Texture.quartz_block_bottom);
		this.put(Material.smooth_quartz_stairs, Texture.quartz_block_bottom);
		this.put(Material.chiseled_quartz_block, Texture.chiseled_quartz_block);
		this.put(Material.hay_block, Texture.hay_block_side);
		this.put(Material.stripped_oak_wood, Texture.stripped_oak_log);
		// trapdoor
		this.put(Material.warped_trapdoor, Texture.warped_trapdoor);
		this.put(Material.cherry_trapdoor, Texture.cherry_trapdoor);
		this.put(Material.copper_trapdoor, Texture.copper_trapdoor);
		this.put(Material.waxed_copper_trapdoor, Texture.copper_trapdoor);
		this.put(Material.exposed_copper_trapdoor, Texture.exposed_copper_trapdoor);
		this.put(Material.weathered_copper_trapdoor, Texture.weathered_copper_trapdoor);
		this.put(Material.oxidized_copper_trapdoor, Texture.oxidized_copper_trapdoor);
		this.put(Material.waxed_exposed_copper_trapdoor, Texture.exposed_copper_trapdoor);
		this.put(Material.waxed_weathered_copper_trapdoor, Texture.weathered_copper_trapdoor);
		this.put(Material.waxed_oxidized_copper_trapdoor, Texture.oxidized_copper_trapdoor);
		//this.put(Material.vines, Texture.stone);

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
		this.put(Material.purpur_stairs, Texture.purpur_block);
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
		this.put(Material.quartz_pillar, this.createSkin(Texture.quartz_pillar, Texture.quartz_pillar_top));
		this.put(Material.cactus,
				this.createSkin(Texture.cactus_side, Texture.cactus_top));
		this.put(Material.scaffolding,
				this.createSkinTopBottom(Texture.scaffolding_bottom, Texture.scaffolding_top, Texture.scaffolding_side));

		//String axis = Material.quartz_pillar.toString(); //getProperty(Property.axis);
		//System.out.println("axis = "+axis);

		//this.skins.put(Blocks.get("sourcecraft:ramp"), PLAYER_CLIP);
		//Blocks.get("minecraft:quartz_pillar").getProperty(Property.axis));
		//if(Material.quartz_pillar.get().getProperty(Property.axis) != null) {
		//String str = Material.quartz_pillar.get().getProperty(Property.axis);
		//String str = Blocks.get("minecraft:quartz_pillar").getProperty(Property.axis);

		//if(str.contains("y")) {
		//	this.put(Material.quartz_pillar, this.createSkinFrontBack(Texture.quartz_pillar, Texture.quartz_pillar_top, Orientation.NORTH));
		//}
		//}


		//this.put(Material.quartz_pillar,
		//		this.createSkin(Texture.quartz_pillar, Texture.quartz_pillar_top, "north", )

		//public Skin(String newMaterial, String newMaterialFront, Orientation orientation, double newScale)

		//String axis = Material.quartz_pillar.get().getProperty(Property.axis);

		// side, top and bottom
		this.put(Material.grass_block,
				this.createSkinTopBottom(Texture.grass_block_side, Texture.grass_block_top, Texture.dirt));
		this.put(Material.dirt_path,
				this.createSkinTopBottom(Texture.dirt_path_side, Texture.dirt_path_top, Texture.dirt));
		//log
		this.put(Material.acacia_log,
				this.createSkinTopBottom(Texture.acacia_log, Texture.acacia_log_top, Texture.acacia_log_top));
		this.put(Material.dark_oak_log,
				this.createSkinTopBottom(Texture.dark_oak_log, Texture.dark_oak_log_top, Texture.dark_oak_log_top));
		this.put(Material.oak_log,
				this.createSkinTopBottom(Texture.oak_log, Texture.oak_log_top, Texture.oak_log_top));
		this.put(Material.jungle_log,
				this.createSkinTopBottom(Texture.jungle_log, Texture.jungle_log_top, Texture.jungle_log_top));
		this.put(Material.birch_log,
				this.createSkinTopBottom(Texture.birch_log, Texture.birch_log_top, Texture.birch_log_top));
		this.put(Material.spruce_log,
				this.createSkinTopBottom(Texture.spruce_log, Texture.spruce_log_top, Texture.spruce_log_top));
		//stripped log
		this.put(Material.stripped_acacia_log,
				this.createSkinTopBottom(Texture.stripped_acacia_log, Texture.stripped_acacia_log_top, Texture.stripped_acacia_log_top));
		this.put(Material.stripped_dark_oak_log,
				this.createSkinTopBottom(Texture.stripped_dark_oak_log, Texture.stripped_dark_oak_log_top, Texture.stripped_dark_oak_log_top));
		this.put(Material.stripped_oak_log,
				this.createSkinTopBottom(Texture.stripped_oak_log, Texture.stripped_oak_log_top, Texture.stripped_oak_log_top));
		this.put(Material.stripped_jungle_log,
				this.createSkinTopBottom(Texture.stripped_jungle_log, Texture.stripped_jungle_log_top, Texture.stripped_jungle_log_top));
		//this.put(Material.stripped_birch_log,
		//this.createSkinTopBottom(Texture.stripped_birch_log, Texture.stripped_birch_log_top, Texture.stripped_birch_log_top));
		//setBlockDirection(Material.dark_oak_log);
		//setBlockDirection(Material.acacia_log);
		//setBlockDirection(Material.oak_log);
		//setBlockDirection(Material.jungle_log);
		//setBlockDirection(Material.spruce_log);
		//if(Material.stripped_acacia_log.get().getProperty(Property.axis).equals("z")) {
		//	this.put(Material.stripped_spruce_log,
		//			this.createSkinTopBottom(Texture.stripped_spruce_log, Texture.stripped_spruce_log_top, Texture.stripped_spruce_log_top));
		//}else {
		//	this.put(Material.stripped_spruce_log,
		//		this.createSkinTopBottom(Texture.stripped_spruce_log, Texture.stripped_spruce_log_top, Texture.stripped_spruce_log_top));
		//}


		// wthis.put(Material, Texture);

		// this.skins.put(Blocks.get("sourcecraft:stone"), STONE);

		this.skins.put(Blocks.get("sourcecraft:ramp"), PLAYER_CLIP);

	}

	// System.out.println(Material.values().length);

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

//	public void setBlockDirection( Material block ) {
//		System.out.println("DEBUG: fucntion ran <--------------------------------------------------------------------------------");
//		if(block.get().getProperties() != null) {
//			if(block.get().getProperty(Property.axis) != null) {
//				String axis = block.get().getProperty(Property.axis);
//				System.out.println("DEBUG: AXIS = " + axis);
//
//				//if (block.getName().equals("minecraft:quartz_pillar")) { // temp
//				if (Skins.INSTANCE.getSkin(block.get()).materialFront.toString().contains("log")) {
//					//System.out.println("DEBUG: Material log found <------------------------------------------------------------");
//					//System.out.println(block.getName().toString());
//					//System.out.println(Skins.INSTANCE.getSkin(block).materialFront.toString());
//
//					Skin skin = Skins.INSTANCE.getSkin(block.get());
//					String side = skin.materialRight;
//					String top = skin.materialTop;
//					System.out.println("skin = " + skin + ", skinTop = " + top + ", side = " + side + "HERE <----------------------");
//
//					//may be ran before textures set to block
//					if(axis.equals("z")) {
//						skin.materialFront = side;
//						skin.materialBack = side;
//						skin.materialTop = side;
//						skin.materialBottom = side;
//						skin.materialLeft = top;
//						skin.materialRight = top;
//
//
//					}
//					if(axis.equals("x")) {
//						skin.materialFront = top;
//						skin.materialBack = top;
//						skin.materialTop = side;
//						skin.materialBottom = side;
//						skin.materialLeft = side;
//						skin.materialRight = side;
//
//
//					}
//					if(axis.equals("y")) {
//						skin.materialFront = side;
//						skin.materialBack = side;
//						skin.materialTop = top;
//						skin.materialBottom = top;
//						skin.materialLeft = side;
//						skin.materialRight = side;
//
//
//					}
//
//
//				} else System.out.println("nogunnawork HERE <----------------------");
//			} else System.out.println("DEBUG: block.get().getProperty(Property.axis) = NULL");
//		} else System.out.println("DEBUG: block.get().getProperties() = NULL");
//	}
}
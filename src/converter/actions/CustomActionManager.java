package converter.actions;

import java.util.Collection;
import converter.actions.actions.*;
import converter.mapper.Mapper;
import minecraft.Blocks;
import minecraft.Material;

public class CustomActionManager extends ActionManager {

	public CustomActionManager(Mapper map, Collection<ConvertEntity> converters) {
		super(Solid.INSTANCE);
		this.setDefaults();
		if (converters != null) {
			for (ConvertEntity converter : converters) {
				this.actions.put(converter.getBlock(), converter.getAction());
			}
		}
	}

	public CustomActionManager setDefaults() {
		this.actions.put(Blocks._UNSET, NoAction.INSTANCE);
		this.actions.put(Material.air, NoAction.INSTANCE);
		this.actions.put(Material.cave_air, NoAction.INSTANCE);
		this.actions.put(Material.void_air, NoAction.INSTANCE);
		this.actions.put(Material.barrier, NoAction.INSTANCE);


		// list of all items with waterlogged property, run check if true and add liquuid


		// if no action described, none will render
		for (Material m : new Material[] {
				Material.ladder,
				Material.rail,
//				Material.lever,
				Material.detector_rail,
				Material.fire,
//				Material.sea_lantern
				//Material.water,
				Material.lava
		}) {
			this.actions.put(m, NoAction.INSTANCE);
		}
		for (Material m : new Material[] {
				//Material._leaves,
				Material.glass,
				Material.ice
		}) {
			this.actions.put(m, new DetailBlock());
		}
		for (Material m : new Material[] {
				//Material.kelp,
				//Material.kelp_plant,
				Material.water,
				Material.lava
		}) {
			this.actions.put(m, new Liquid());
		}

		//this.actions.put(Material._fence, new Fence());
		this.actions.put(Material._stairs, new Stairs());
		this.actions.put(Material.cactus, new Cactus());
		this.actions.put(Material.fire, new Fire());
		this.actions.put(Material._slab, new Slab());
		this.actions.put(Material._pane, new GlassPane());
		this.actions.put(Material.iron_bars, new IronBars());
		this.actions.put(Material.chest, new Chest());
		this.actions.put(Material.lily_pad, new LilyPad());

		// tf2
		//this.actions.put(Material.grass_block, new TallGrassTf2());
		//this.actions.put(Material.tall_grass, new TallGrassTf2());
		this.actions.put(Material.jack_o_lantern, new NoAction());
		this.actions.put(Material.sea_lantern, new NoAction());
		this.actions.put(Material.lantern, new Lantern());
		this.actions.put(Material.soul_lantern, new Lantern());
		//this.actions.put(Material.lantern, new TestDrawFromCoords());

		this.actions.put(Material._carpet, new Carpet());

		// plants test
		this.actions.put(Material.acacia_sapling, new CrossModel(0, 0));
		this.actions.put(Material.allium, new CrossModel(1, 0));
		this.actions.put(Material.amethyst_cluster, new CrossModel(2, 0));
		this.actions.put(Material.azure_bluet, new CrossModel(3, 0));
		//this.actions.put(Material.bamboo, new CrossModel(4, 0, true));
		this.actions.put(Material.birch_sapling, new CrossModel(5, 0));
		this.actions.put(Material.blue_orchid, new CrossModel(6, 0));
		this.actions.put(Material.brain_coral, new CrossModel(7, 0));
		this.actions.put(Material.brown_mushroom, new CrossModel(8, 0));
		this.actions.put(Material.bubble_coral, new CrossModel(9, 0));
		this.actions.put(Material.cave_vines, new CrossModel(10, 0));
		//this.actions.put(Material.cave_vines_lit, new CrossModel(11, 0));
		this.actions.put(Material.cave_vines_plant, new CrossModel(12, 0));
		//this.actions.put(Material.cave_vines_plant_lit, new CrossModel(13, 0));
		this.actions.put(Material.cherry_sapling, new CrossModel(14, 0));
		this.actions.put(Material.cobweb, new CrossModel(15, 0));
		this.actions.put(Material.cornflower, new CrossModel(16, 0));
		this.actions.put(Material.crimson_fungus, new CrossModel(17, 0));
		this.actions.put(Material.crimson_roots, new CrossModel(18, 0));
		this.actions.put(Material.dandelion, new CrossModel(19, 0));
		this.actions.put(Material.dark_oak_sapling, new CrossModel(20, 0));
		this.actions.put(Material.dead_brain_coral, new CrossModel(21, 0));
		this.actions.put(Material.dead_bubble_coral, new CrossModel(22, 0));
		this.actions.put(Material.dead_bush, new CrossModel(23, 0));
		this.actions.put(Material.dead_fire_coral, new CrossModel(24, 0));
		this.actions.put(Material.dead_horn_coral, new CrossModel(25, 0));
		this.actions.put(Material.dead_tube_coral, new CrossModel(26, 0));
		this.actions.put(Material.fern, new CrossModel(27, 0));
		this.actions.put(Material.fire_coral, new CrossModel(28, 0));
		this.actions.put(Material.hanging_roots, new CrossModel(29, 0));
		this.actions.put(Material.horn_coral, new CrossModel(30, 0));
		this.actions.put(Material.jungle_sapling, new CrossModel(31, 0));
		this.actions.put(Material.kelp, new CrossModel(32, 0));
		this.actions.put(Material.kelp_plant, new CrossModel(33, 0));
		this.actions.put(Material.large_amethyst_bud, new CrossModel(34, 0));
		this.actions.put(Material.large_fern, new CrossModel(35, 0));
		this.actions.put(Material.lilac, new CrossModel(37, 0));
		this.actions.put(Material.lily_of_the_valley, new CrossModel(39, 0));
		this.actions.put(Material.medium_amethyst_bud, new CrossModel(40, 0));
		this.actions.put(Material.nether_sprouts, new CrossModel(41, 0));
		this.actions.put(Material.oak_sapling, new CrossModel(42, 0));
		this.actions.put(Material.orange_tulip, new CrossModel(43, 0));
		this.actions.put(Material.oxeye_daisy, new CrossModel(44, 0));
		this.actions.put(Material.peony, new CrossModel(45, 0));
		this.actions.put(Material.pink_tulip, new CrossModel(47, 0));
		this.actions.put(Material.poppy, new CrossModel(48, 0));
		this.actions.put(Material.red_mushroom, new CrossModel(49, 0));
		this.actions.put(Material.red_tulip, new CrossModel(50, 0));
		this.actions.put(Material.rose_bush, new CrossModel(51, 0));
		this.actions.put(Material.short_grass, new CrossModel(53, 0));
		this.actions.put(Material.small_amethyst_bud, new CrossModel(54, 0));
		this.actions.put(Material.spruce_sapling, new CrossModel(55, 0));
		this.actions.put(Material.sugar_cane, new CrossModel(56, 0));
		this.actions.put(Material.sunflower, new CrossModel(57, 0));
		this.actions.put(Material.sweet_berry_bush, new CrossModel(58, 0, true));
		this.actions.put(Material.tall_grass, new CrossModel(62, 0));
		this.actions.put(Material.torchflower, new CrossModel(64, 0));
		this.actions.put(Material.torchflower_crop, new CrossModel(65, 0, true));
		this.actions.put(Material.tube_coral, new CrossModel(67, 0));
		this.actions.put(Material.twisting_vines, new CrossModel(68, 0));
		this.actions.put(Material.twisting_vines_plant, new CrossModel(69, 0));
		this.actions.put(Material.warped_fungus, new CrossModel(70, 0));
		this.actions.put(Material.warped_roots, new CrossModel(71, 0));
		this.actions.put(Material.weeping_vines, new CrossModel(72, 0));
		this.actions.put(Material.weeping_vines_plant, new CrossModel(73, 0));
		this.actions.put(Material.white_tulip, new CrossModel(74, 0));
		this.actions.put(Material.wither_rose, new CrossModel(75, 0));

		this.actions.put(Material.beetroots, new CropModel(0, 0));
		//this.actions.put(Material.beetroots_stage1, new CropModel(1, 0));
		//this.actions.put(Material.beetroots_stage2, new CropModel(2, 0));
		//this.actions.put(Material.beetroots_stage3, new CropModel(3, 0));
		this.actions.put(Material.carrots, new CropModel(4, 0));
		//this.actions.put(Material.carrots_stage1, new CropModel(5, 0));
		//this.actions.put(Material.carrots_stage2, new CropModel(6, 0));
		//this.actions.put(Material.carrots_stage3, new CropModel(7, 0));
		this.actions.put(Material.nether_wart, new CropModel(8, 0));
		//this.actions.put(Material.nether_wart_stage1, new CropModel(9, 0));
		//this.actions.put(Material.nether_wart_stage2, new CropModel(10, 0));
		this.actions.put(Material.potatoes, new CropModel(11, 0));
		//this.actions.put(Material.potatoes_stage1, new CropModel(12, 0));
		//this.actions.put(Material.potatoes_stage2, new CropModel(13, 0));
		//this.actions.put(Material.potatoes_stage3, new CropModel(14, 0));
		this.actions.put(Material.wheat, new CropModel(15, 0));
		//this.actions.put(Material.wheat_stage1, new CropModel(16, 0));
		//this.actions.put(Material.wheat_stage2, new CropModel(17, 0));
		//this.actions.put(Material.wheat_stage3, new CropModel(18, 0));
		//this.actions.put(Material.wheat_stage4, new CropModel(19, 0));
		//this.actions.put(Material.wheat_stage5, new CropModel(20, 0));
		//this.actions.put(Material.wheat_stage6, new CropModel(21, 0));
		//this.actions.put(Material.wheat_stage7, new CropModel(22, 0));

		// coral fan
		this.actions.put(Material.tube_coral_fan, new CoralFan(0, 0));
		this.actions.put(Material.brain_coral_fan, new CoralFan(1, 0));
		this.actions.put(Material.bubble_coral_fan, new CoralFan(2, 0));
		this.actions.put(Material.fire_coral_fan, new CoralFan(3, 0));
		this.actions.put(Material.horn_coral_fan, new CoralFan(4, 0));

		// dead coral fan
		this.actions.put(Material.dead_tube_coral_fan, new CoralFan(5, 0));
		this.actions.put(Material.dead_brain_coral_fan, new CoralFan(6, 0));
		this.actions.put(Material.dead_bubble_coral_fan, new CoralFan(7, 0));
		this.actions.put(Material.dead_fire_coral_fan, new CoralFan(8, 0));
		this.actions.put(Material.dead_horn_coral_fan, new CoralFan(9, 0));

		// coral wall fan
		this.actions.put(Material.tube_coral_wall_fan, new CoralWallFan(0, 0));
		this.actions.put(Material.brain_coral_wall_fan, new CoralWallFan(1, 0));
		this.actions.put(Material.bubble_coral_wall_fan, new CoralWallFan(2, 0));
		this.actions.put(Material.fire_coral_wall_fan, new CoralWallFan(3, 0));
		this.actions.put(Material.horn_coral_wall_fan, new CoralWallFan(4, 0));

		// dead coral wall fan
		this.actions.put(Material.dead_tube_coral_wall_fan, new CoralWallFan(5, 0));
		this.actions.put(Material.dead_brain_coral_wall_fan, new CoralWallFan(6, 0));
		this.actions.put(Material.dead_bubble_coral_wall_fan, new CoralWallFan(7, 0));
		this.actions.put(Material.dead_fire_coral_wall_fan, new CoralWallFan(8, 0));
		this.actions.put(Material.dead_horn_coral_wall_fan, new CoralWallFan(9, 0));

		// tree leaves
//		this.actions.put(Material.birch_leaves, new TreeLeavesB());
//		this.actions.put(Material.jungle_leaves, new TreeLeavesB());
//		this.actions.put(Material.dark_oak_leaves, new TreeLeavesB());
//		this.actions.put(Material.oak_leaves, new TreeLeavesB());
//		this.actions.put(Material.spruce_leaves, new TreeLeavesB());
//		this.actions.put(Material.acacia_leaves, new TreeLeavesB());
//		this.actions.put(Material.oak_leaves, new TreeLeavesB());
//		this.actions.put(Material.oak_leaves, new TreeLeavesB());

		// buttons
		this.actions.put(Material.birch_button, new ButtonA(0, 0));
		this.actions.put(Material.jungle_button, new ButtonA(1, 0));
		this.actions.put(Material.dark_oak_button, new ButtonA(2, 0));
		this.actions.put(Material.oak_button, new ButtonA(3, 0));
		this.actions.put(Material.spruce_button, new ButtonA(4, 0));
		this.actions.put(Material.acacia_button, new ButtonA(5, 0));
		this.actions.put(Material.stone_button, new ButtonA(6, 0));
		this.actions.put(Material.mangrove_button, new ButtonA(7, 0));
		this.actions.put(Material.cherry_button, new ButtonA(8, 0));
		this.actions.put(Material.bamboo_button, new ButtonA(9, 0));
		this.actions.put(Material.crimson_button, new ButtonA(10, 0));
		this.actions.put(Material.warped_button, new ButtonA(11, 0));
		this.actions.put(Material.polished_blackstone_button, new ButtonA(12, 0));

		//scaffolding
		this.actions.put(Material.scaffolding, new Scaffolding(0, 6));

		for (Material m : Material.values()) {
			if(m.getName().endsWith("_trapdoor")) {
				this.actions.put(Blocks.get(m.getName()), new Trapdoor());
			}
			if(m.getName().endsWith("_door")) {
				this.actions.put(Blocks.get(m.getName()), new Door());
			}
			if(m.getName().endsWith("_fence")) {
				this.actions.put(Blocks.get(m.getName()), new Fences());
			}
			if(m.getName().endsWith("_fence_gate")) {
				this.actions.put(Blocks.get(m.getName()), new FenceGate());
			}
			if(m.getName().endsWith("_sign")) {
				this.actions.put(Blocks.get(m.getName()), new Signs());
			}
			if(m.getName().endsWith("_log")) {
				this.actions.put(Blocks.get(m.getName()), new AxisBlocks2());
			}
			if(m.getName().endsWith("_wood")) {
				this.actions.put(Blocks.get(m.getName()), new AxisBlocks2());
			}
			if(m.getName().endsWith("_hyphae")) {
				this.actions.put(Blocks.get(m.getName()), new AxisBlocks2());
			}
			if(m.getName().endsWith("_terracotta")
					&& m.getName().contains("glazed_")) {
				this.actions.put(Blocks.get(m.getName()), new Terracotta());
			}
			if(m.getName().endsWith("_pressure_plate") && !m.getName().startsWith("_")) {
				this.actions.put(Blocks.get(m.getName()), new PressurePlate());
			}
		}

		this.actions.put(Material.quartz_pillar, new AxisBlocks2());
		this.actions.put(Material.warped_stem, new AxisBlocks2());
		this.actions.put(Material.crimson_stem, new AxisBlocks2());
		this.actions.put(Material.bamboo_block, new AxisBlocks2());
		this.actions.put(Material.stripped_bamboo_block, new AxisBlocks2());
		//mushroom stems

		this.actions.put(Material.bamboo, new Bamboo());
		this.actions.put(Material.azalea, new AzaleaPlant(0, 2));
		this.actions.put(Material.flowering_azalea, new AzaleaPlant(1, 2));
		this.actions.put(Material.grindstone, new Grindstone());
		this.actions.put(Material.pink_petals, new PinkPetals());
		this.actions.put(Material.barrel, new Barrel());
		this.actions.put(Material.lectern, new Lectern());

		this.actions.put(Material.redstone_wire, new RedstoneDustLine());
		this.actions.put(Material.vine, new Vines());
		this.actions.put(Material.glow_lichen, new Vines());
		this.actions.put(Material.sculk_vein, new Vines());

		this.actions.put(Material.torch, new Torches(0, 0));
		this.actions.put(Material.wall_torch, new Torches(0, 0));
		this.actions.put(Material.redstone_torch, new Torches(1, 0));
		this.actions.put(Material.redstone_wall_torch, new Torches(1, 0));


		//this.actions.put(Material.water, new NoAction()); // <-------water
		this.actions.put(Material.snow, new Snow());
		this.actions.put(Material.sea_pickle, new SeaPickle(0, 6));
		this.actions.put(Material._wall, new Wall());
		this.actions.put(Material.dirt_path, new GrassPath());
		this.actions.put(Material.seagrass, new Liquid());
		this.actions.put(Material.tall_seagrass, new Liquid());

		// ttt
		//this.actions.put(Material.zombie_head, new CenteredPointEntity("info_player_start"));
		//this.actions.put(Material.fletching_table, new CenteredPointEntity("ttt_random_weapon"));
		//this.actions.put(Material.grindstone, new CenteredPointEntity("ttt_random_ammo"));

		// css
        //this.actions.put(Material.torch, new CssLamp());
        //this.actions.put(Material.wall_torch, new CssLamp());
		//this.actions.put(Material.end_portal_frame, new PlayerSpawnCss(new InfoPlayerT().setRotation(0), false));
		//this.actions.put(Material.ender_chest, new PlayerSpawnCss(new InfoPlayerCT().setRotation(180), true));
		//this.actions.put(Material.vines, new Snow());
		return this;
	}
}
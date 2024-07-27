package converter.actions;

import java.util.Collection;

import basic.Loggger;
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

		// if no action described, none will render
		for (Material m : new Material[] {
				Material.fern,
				Material.dandelion,
				Material.poppy,
				Material.brown_mushroom,
				Material.red_mushroom,
				Material.redstone_wire,
				Material.wheat,
				Material.oak_door,
				Material.ladder,
				Material.rail,
				Material.oak_wall_sign,
				Material.lever,
				Material.stone_pressure_plate,
				Material.iron_door,
				Material.oak_pressure_plate,
				Material.sugar_cane,
				Material.sunflower,
				Material.cobweb,
				Material.detector_rail,
				Material.detector_rail,
				Material.fire,
				Material.redstone_wall_torch,
				Material.redstone_torch,
				Material.stone_button,
				Material.tall_grass,
				Material.tall_grass,
				Material.large_fern,
				Material.blue_orchid,
				Material.allium,
				Material.azure_bluet,
				Material.red_tulip,
				Material.orange_tulip,
				Material.white_tulip,
				Material.pink_tulip,
				Material.oxeye_daisy,
				Material.cornflower,
				Material.lily_of_the_valley,
				Material.wither_rose,
				Material.lilac,
				Material.rose_bush,
				Material.peony,
				Material.sugar_cane,
				Material.seagrass,
				Material.tall_seagrass,
				Material.sweet_berry_bush,
//				Material.sea_lantern
		}) {
			this.actions.put(m, NoAction.INSTANCE);
		}
		for (Material m : new Material[] {
				Material._leaves,
				Material.glass,
				Material.ice
		}) {
			this.actions.put(m, new DetailBlock());
		}
		for (Material m : new Material[] {
				Material.water,
				Material.lava
		//		Material.seagrass,
		//		Material.tall_seagrass,
		//		Material.kelp,
		//		Material.kelp_plant
		}) {
			this.actions.put(m, new Liquid());
		}
		//this.actions.put(Material._fence, new Fence());
		this.actions.put(Material._stairs, new Stairs());
		// this.actions.put(Blocks.get(t -> t.setName(Material._slab)
		// .addProperty(Property.half, Property.Half.top)
		// .addProperty(Property.waterlogged, Property.Waterlogged.false$)), new
		// SlabTop());
		// this.actions.put(Blocks.get(t -> t.setName(Material._slab)
		// .addProperty(Property.half, Property.Half.bottom)
		// .addProperty(Property.waterlogged, Property.Waterlogged.false$)), new
		// SlabBottom());
		///this.actions.put(Material.torch, Torch.INSTANCE);
		///this.actions.put(Material.wall_torch, Torch.INSTANCE);
		this.actions.put(Material.cactus, new Cactus());
		this.actions.put(Material.fire, new Fire());
		this.actions.put(Material._slab, new Slab());
		this.actions.put(Material._pane, new Pane());
		this.actions.put(Material.iron_bars, new IronBars());
		//this.actions.put(Material.lily_pad, new LilyPad());
		//this.actions.put(Material.vines, new Vines());
		this.actions.put(Material.lily_pad, new LilyPad());

		// tf2
		//this.actions.put(Material.grass_block, new TallGrassTf2());
		this.actions.put(Material.tall_grass, new TallGrassTf2());
		this.actions.put(Material.lantern, new LanternGround());
		this.actions.put(Material._carpet, new Carpet());

		// plant models
		// PlantModelA(SkinIndex, solidType)
		this.actions.put(Material.dandelion, new PlantModelA(0, 0));
		this.actions.put(Material.poppy, new PlantModelA(1, 0));
		this.actions.put(Material.allium, new PlantModelA(2, 0));
		this.actions.put(Material.short_grass, new PlantModelA(3, 0));
		this.actions.put(Material.fern, new PlantModelA(4, 0));
		this.actions.put(Material.dead_bush, new PlantModelA(5, 0));
		this.actions.put(Material.brown_mushroom, new PlantModelA(6, 0));
		this.actions.put(Material.red_mushroom, new PlantModelA(7, 0));
		this.actions.put(Material.blue_orchid, new PlantModelA(8, 0));
		this.actions.put(Material.azure_bluet, new PlantModelA(9, 0));
		this.actions.put(Material.red_tulip, new PlantModelA(10, 0));
		this.actions.put(Material.orange_tulip, new PlantModelA(11, 0));
		this.actions.put(Material.white_tulip, new PlantModelA(12, 0));
		this.actions.put(Material.pink_tulip, new PlantModelA(13, 0));
		this.actions.put(Material.oxeye_daisy, new PlantModelA(14, 0));
		this.actions.put(Material.cornflower, new PlantModelA(15, 0));
		this.actions.put(Material.lily_of_the_valley, new PlantModelA(16, 0));

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

		// coral
		this.actions.put(Material.tube_coral, new PlantModelA(17, 0));
		this.actions.put(Material.brain_coral, new PlantModelA(18, 0));
		this.actions.put(Material.bubble_coral, new PlantModelA(19, 0));
		this.actions.put(Material.fire_coral, new PlantModelA(20, 0));
		this.actions.put(Material.horn_coral, new PlantModelA(21, 0));

		// dead coral
		this.actions.put(Material.dead_tube_coral, new PlantModelA(22, 0));
		this.actions.put(Material.dead_brain_coral, new PlantModelA(23, 0));
		this.actions.put(Material.bubble_coral, new PlantModelA(24, 0));
		this.actions.put(Material.dead_fire_coral, new PlantModelA(25, 0));
		this.actions.put(Material.dead_horn_coral, new PlantModelA(26, 0));

		// tree leaves
		this.actions.put(Material.birch_leaves, new TreeLeavesB());
		this.actions.put(Material.jungle_leaves, new TreeLeavesB());
		this.actions.put(Material.dark_oak_leaves, new TreeLeavesB());
		this.actions.put(Material.oak_leaves, new TreeLeavesB());
		this.actions.put(Material.spruce_leaves, new TreeLeavesB());
		this.actions.put(Material.acacia_leaves, new TreeLeavesB());
		// this.actions.put(Material.oak_leaves, new TreeLeavesB());
		//this.actions.put(Material.oak_leaves, new TreeLeavesB());

		// saplings
		this.actions.put(Material.oak_sapling, new PlantModelA(27, 0));
		this.actions.put(Material.spruce_sapling, new PlantModelA(28, 0));
		this.actions.put(Material.birch_sapling, new PlantModelA(29, 0));
		this.actions.put(Material.jungle_sapling, new PlantModelA(30, 0));
		this.actions.put(Material.acacia_sapling, new PlantModelA(31, 0));
		this.actions.put(Material.dark_oak_sapling, new PlantModelA(32, 0));

		// buttons
		this.actions.put(Material.birch_button, new ButtonA(0, 6));
		this.actions.put(Material.jungle_button, new ButtonA(1, 6));
		this.actions.put(Material.dark_oak_button, new ButtonA(2, 6));
		this.actions.put(Material.oak_button, new ButtonA(3, 6));
		this.actions.put(Material.spruce_button, new ButtonA(4, 6));
		this.actions.put(Material.acacia_button, new ButtonA(5, 6));
		this.actions.put(Material.stone_button, new ButtonA(6, 6));

		// Crops
		this.actions.put(Material.wheat, new Crops(7, 0));

		//scaffolding
		this.actions.put(Material.scaffolding, new Scaffolding(0, 6));


		for (Material m : Material.values()) {
			if(m.getName().endsWith("_trapdoor")){
				Loggger.log(m.getName());
				this.actions.put(Blocks.get(m.getName()), new Trapdoor());
			}
			if(m.getName().endsWith("_door")){
				//Loggger.log(m.getName());
				this.actions.put(Blocks.get(m.getName()), new Door());
			}
			if(m.getName().endsWith("_fence")){
				//Loggger.log(m.getName());
				this.actions.put(Blocks.get(m.getName()), new Fences());
			}
			if(m.getName().endsWith("_sign")){
				//Loggger.log(m.getName());
				this.actions.put(Blocks.get(m.getName()), new Signs());
			}
		}

		//this.actions.put(Blocks.get("minecraft:redstone_wire"), new RedstoneDustLine());
		this.actions.put((Material.redstone_wire), new RedstoneDustLine());
		//this.actions.put(Blocks.get("minecraft:vine"), new Vines());
		this.actions.put((Material.vine), new Vines());
		this.actions.put(Material.torch, new Torches(0, 0));
		this.actions.put(Material.wall_torch, new Torches(0, 0));
		this.actions.put(Material.redstone_torch, new Torches(1, 0));
		this.actions.put(Material.redstone_wall_torch, new Torches(1, 0));

		// other
		// this.actions.put(Material.wither_rose, new PlantModelA(33, 0));
		// this.actions.put(Material.cobweb, new PlantModelA(34, 0));
		// this.actions.put(Material.sugar_cane, new PlantModelA(35, 0));
		//this.actions.put(Material.water, new NoAction()); // <-------water
		//this.actions.put(Material.oak_sign, new Sign());
		//this.actions.put(Material.oak_wall_sign, new Sign());
		this.actions.put(Material.snow, new Snow());
		//this.actions.put(Material.vines, NoAction.INSTANCE);
		//this.actions.put(Material.vines, new NoAction());
		this.actions.put(Material.sea_pickle, new SeaPickle(0, 6));
		this.actions.put(Material._wall, new Wall());
		this.actions.put(Material.dirt_path, new GrassPath());
		this.actions.put(Material.seagrass, new NoAction());
		this.actions.put(Material.tall_seagrass, new NoAction());




		//axis based blocks
		//this.actions.put(Material.oak_log, new AxisBlocks());
		//this.actions.put(Material.dark_oak_log, new AxisBlocks());
		//this.actions.put(Material.acacia_log, new AxisBlocks());
		//this.actions.put(Material.spruce_log, new AxisBlocks());
		//this.actions.put(Material.birch_log, new AxisBlocks());

		// ttt
		//this.actions.put(Material.zombie_head, new CenteredPointEntity("info_player_start"));
		//this.actions.put(Material.fletching_table, new CenteredPointEntity("ttt_random_weapon"));
		//this.actions.put(Material.grindstone, new CenteredPointEntity("ttt_random_ammo"));

		// css
//		this.actions.put(Material.torch, new CssLamp());
//		this.actions.put(Material.wall_torch, new CssLamp());
		//this.actions.put(Material.end_portal_frame, new PlayerSpawnCss(new InfoPlayerT().setRotation(0), false));
		//this.actions.put(Material.ender_chest, new PlayerSpawnCss(new InfoPlayerCT().setRotation(180), true));
		//this.actions.put(Material.vines, new Snow());
		return this;
	}
}
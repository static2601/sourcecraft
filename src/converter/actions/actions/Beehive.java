package converter.actions.actions;

import converter.Orientation;
import converter.Skins;
import converter.actions.Action;
import converter.mapper.Mapper;
import minecraft.Block;
import minecraft.Position;
import minecraft.Property;
import vmfWriter.Cuboid;
import vmfWriter.Skin;

public class Beehive extends Action {

	@Override
	public void add(Mapper context, Position pos, Block block) {

		Position end = context.getCuboidFinder().getBestXYZ(pos, block);
		Cuboid beehive = context.createCuboid(pos, end, block);
		String honey_level = block.getProperty(Property.honey_level);
		beehive.setSkin(this.constructTopFrontSkin(block, honey_level));
		context.addSolid(beehive);
		context.markAsConverted(pos, end);
	}

	private Skin constructTopFrontSkin(Block block, String honey_level) {
		Skin oldSkin = Skins.INSTANCE.getSkin(block);
		Orientation dir = this.getOrientation(block);
		String front = oldSkin.materialBack;

		if (honey_level.equals("5")) {
			if (block.getName().endsWith("bee_nest"))
				front = oldSkin.materialBack+"_honey";
			if (block.getName().endsWith("beehive"))
				front = oldSkin.materialBack+"_honey";
		}
		String main = oldSkin.materialFront;
		String top = oldSkin.materialTop;
		String bottom = oldSkin.materialBottom;
		return new Skin(main, top, front, bottom, dir, oldSkin.scale, 0);
	}

	private Orientation getOrientation(Block block) {
		String dir = block.getProperty(Property.facing);
		switch (dir) {
			case "north":
				return Orientation.NORTH;
			case "south":
				return Orientation.SOUTH;
			case "east":
				return Orientation.EAST;
			default:
				return Orientation.WEST;
		}
    }
}
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

public class Barrel extends Action {

	@Override
	public void add(Mapper context, Position pos, Block block) {
		Position end = context.getCuboidFinder().getBestXYZ(pos, block);

		Cuboid barrel = context.createCuboid(pos, end, block);
		barrel.setSkin(this.constructSkin(block));
		context.addDetail(barrel);
		context.markAsConverted(pos, end);
	}

	private Skin constructSkin(Block block) {
		Skin oldSkin = Skins.INSTANCE.getSkin(block);
		Orientation dir = this.getOrientation(block);
		String main = oldSkin.materialFront;
		String front = oldSkin.materialFront;
		String bottom = oldSkin.materialBottom;
		String top = oldSkin.materialTop;
		return new Skin(main, top, front, bottom, dir, oldSkin.scale);
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
			case "up":
				return Orientation.UP;
			case "down":
				return Orientation.DOWN;
            default:
                return Orientation.WEST;
        }
	}
}
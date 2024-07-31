package converter.actions.actions;

import basic.Loggger;
import converter.Orientation;
import converter.Skins;
import converter.actions.Action;
import converter.mapper.Mapper;
import minecraft.Block;
import minecraft.Position;
import minecraft.Property;
import vmfWriter.Cuboid;
import vmfWriter.Skin;

public class Terracotta extends Action {

	@Override
	public void add(Mapper context, Position pos, Block block) {
		Position end = context.getCuboidFinder().getBestXYZ(pos, block);
		Cuboid terracotta = context.createCuboid(pos, end, block);
		terracotta.setSkin(this.constructSkin(block));
		context.addSolid(terracotta);
		context.markAsConverted(pos, end);
	}

	private Skin constructSkin(Block block) {
		Skin oldSkin = Skins.INSTANCE.getSkin(block);
		Orientation dir = this.getOrientation(block);
		String main = oldSkin.materialFront;
		String front = oldSkin.materialBack;
		String top = oldSkin.materialTop;
		return new Skin(main, top, front, dir, oldSkin.scale);
	}

	private Orientation getOrientation(Block block) {
		Loggger.log("terracotta: "+block.get().toString());
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
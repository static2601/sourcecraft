package converter.actions.actions;

import converter.Orientation;
import converter.Skins;
import converter.actions.Action;
import converter.mapper.Mapper;
import minecraft.Block;
import minecraft.Position;
import minecraft.Property;
import minecraft.Texture;
import vmfWriter.Cuboid;
import vmfWriter.Skin;

public class Workstations extends Action {

	@Override
	public void add(Mapper context, Position pos, Block block) {

		Position end = context.getCuboidFinder().getBestXYZ(pos, block);
		Cuboid workstation = context.createCuboid(pos, end, block);
		if (block.getName().endsWith("blast_furnace")
				|| block.getName().endsWith("smoker")) {
			workstation.setSkin(this.constructTopFrontSkin(block, true));
		}
		else if (block.getName().endsWith("loom")) {
			workstation.setSkin(this.constructTopFrontSkin(block, false));
		}

		context.addSolid(workstation);
		context.markAsConverted(pos, end);
	}

	private Skin constructTopFrontSkin(Block block, boolean checkLit) {
		Skin oldSkin = Skins.INSTANCE.getSkin(block);
		Orientation dir = this.getOrientation(block);
		String front = oldSkin.materialBack;
		if (checkLit) {
			String lit = block.getProperty(Property.lit);

			if (lit.equals("true")
					&& block.getName().endsWith("smoker"))
				//front = Texture.smoker_front_on.getName();
				front = oldSkin.materialBack+"_on";


			if (lit.equals("true")
					&& block.getName().endsWith("blast_furnace"))
				front = oldSkin.materialBack+"_on";
		}
		String main = oldSkin.materialFront;

		String top = oldSkin.materialTop;
		return new Skin(main, top, front, dir, oldSkin.scale);
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
package converter.actions.actions;

import basic.Loggger;
import converter.Skins;
import converter.actions.Action;
import converter.mapper.Mapper;
import minecraft.*;
import vmfWriter.Cuboid;
import vmfWriter.Skin;

public class AxisBlocks2 extends Action {

	@Override
	public void add(Mapper context, Position p, Block block) {
		Position end = context.getCuboidFinder()
				.getBestY(p, block);
		Cuboid axisBlock = context.createCuboid(p, end, block);
		axisBlock.setSkin(this.constructSkin(block));
		context.addDetail(axisBlock);
	}

	private Skin constructSkin(Block block) {
		Skin oldSkin = Skins.INSTANCE.getSkin(block);
		String dir = this.getOrientation(block);
		String main = oldSkin.materialFront;
		String front = oldSkin.materialFront;
		String bottom = oldSkin.materialTop;
		String top = oldSkin.materialTop;
		return new Skin(main, top, front, bottom, dir, oldSkin.scale);
	}

	private String getOrientation(Block block) {
		String axis = block.getProperty(Property.axis);
        switch (axis) {
            case "x":
                return "x";
            case "z":
                return "z";
            default:
                return "y";
        }
	}
}
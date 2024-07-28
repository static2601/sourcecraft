package converter.actions.actions;

import converter.actions.Action;
import converter.mapper.Mapper;
import minecraft.*;

public class AxisBlocks extends Action {

	@Override
	public void add(Mapper context, Position p, Block block) {
		Position end = context.getCuboidFinder()
				.getBestY(p, block);
		Blocks.ImmutableBlock blocks = null;
		String name = block.getName().split(":")[1];

		if(name.endsWith("_log"))
			name = name.replace("_log", "");

		if(block.getProperties() != null) {
			if(block.get().getProperty(Property.axis) != null) {
				String axis = block.getProperty(Property.axis);

                switch (axis) {
                    case "z":
                        String finalName = name;
                        blocks = Blocks.get(t -> t.setName(finalName + "_axisZ"));
                        break;

                    case "x":
                        String finalName1 = name;
                        blocks = Blocks.get(t -> t.setName(finalName1 + "_axisX"));
                        break;

                    case "y":
                        String finalName2 = name;
                        blocks = Blocks.get(t -> t.setName(finalName2 + "_axisY"));
                        break;
                }
			}
		}
		context.addSolid(context.createCuboid(p, end, blocks));
		context.markAsConverted(p, end);
	}
}

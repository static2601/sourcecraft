package converter.actions.actions;

import converter.actions.Action;
import converter.mapper.Mapper;
import minecraft.Block;
import minecraft.Position;

public class TreeLeavesB extends Action {

	@Override
	public void add(Mapper context, Position position, Block block) {
		Position end = context.getCuboidFinder()
				.getBestXY(position, block);
		int parts = 8;
		//int parts = 256;
		Position offset = new Position(0, 0, 0);
		//Position negativeOffset = new Position(1, 1, 1);
		Position negativeOffset = new Position(0, 0, 0);
		//context.addDetail(context.createCuboid(position, end, parts, offset, negativeOffset, block));
		context.addDetail(context.createCuboid(position, end, block));
		context.markAsConverted(position, end);
	}
}

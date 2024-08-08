package converter.actions.actions;

import converter.actions.Action;
import converter.mapper.Mapper;
import minecraft.Block;
import minecraft.Position;

public class PressurePlate extends Action {
	private Mapper context;

	@Override
	public void add(Mapper context, Position position, Block block) {
		this.context = context;
		this.addCuboid(position, position, block);
		context.markAsConverted(position, position);
	}

	private void addCuboid(Position position, Position end, Block block) {
		Position startOffset, endOffset;
		startOffset = new Position(1, 0, 1);
		endOffset = new Position(1, 15, 1);
		int pixels = 16;
		context.addDetail(context.createCuboid(position, end, pixels, startOffset, endOffset, block));
	}
}
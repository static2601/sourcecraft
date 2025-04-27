package converter.actions.actions;

import converter.actions.Action;
import converter.mapper.Mapper;
import minecraft.Block;
import minecraft.Position;
import vmfWriter.Solid;
import vmfWriter.entity.solidEntity.TriggerHurt;

public class Cactus extends Action {

	@Override
	public void add(Mapper context, Position position, Block block) {
		Position end = context.getCuboidFinder()
				.getBestY(position, block);
		int parts = 8;
		Position offset = new Position(1, 0, 1);
		Position negativeOffset = new Position(1, 1, 1);
		context.addDetail(context.createCuboid(position, end, parts, offset, negativeOffset, block));

		context.addSolidEntity(new TriggerHurt(this.createArea(
				context, position, end, block), 1, 128));

		context.markAsConverted(position, end);
	}
	private Solid createArea(Mapper context, Position p, Position end, Block material) {
		return context.createCuboid(p, end, material);
	}
}

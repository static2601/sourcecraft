package converter.actions.actions;

import converter.actions.Action;
import converter.mapper.Mapper;
import minecraft.Block;
import minecraft.Blocks;
import minecraft.Position;
import minecraft.Property;

/**
 * A non-solid block (thus counts as air) that has a lowered height when it ends
 *
 */
public class Liquid extends Action {

	@Override
	public void add(Mapper context, Position position, Block material) {
		Position endXYZ = context.getCuboidFinder()
				.getBestXYZ(position, material);
		Position endXZ = context.getCuboidFinder()
				.getBestXZ(position, material);

		Block above = context.getBlock(new Position(position.x,position.y+1,position.z));
		if (material.getName().equals("minecraft:water")) {
			if (above.getName() != null) {
				if (!above.getName().contains("minecraft:water")
						&& !above.hasProperty(Property.waterlogged)) {

					int parts = 16;
					Position offset = new Position(0, 0, 0);
					Position negativeOffset = new Position(0, 2, 0);

					context.addSolid(context.createCuboid(
							position, endXZ, parts, offset, negativeOffset, Blocks.get("minecraft:water")));
					context.markAsConverted(position, endXZ);

				} else {
					context.addSolid(context.createCuboid(position, endXZ, Blocks.get("minecraft:water")));
					context.markAsConverted(position, endXZ);
				}
			}
		}
		else if (material.getName().equals("minecraft:lava")) {
			if (above.getName() != null) {
				if (!above.getName().equals("minecraft:lava")
						&& !above.hasProperty(Property.waterlogged)) {

					int parts = 16;
					Position offset = new Position(0, 0, 0);
					Position negativeOffset = new Position(0, 2, 0);

					context.addSolid(context.createCuboid(
							position, endXZ, parts, offset, negativeOffset, Blocks.get("minecraft:lava")));
					context.markAsConverted(position, endXZ);

				} else {
					context.addSolid(context.createCuboid(position, endXZ, Blocks.get("minecraft:lava")));
					context.markAsConverted(position, endXZ);
				}
			}
		}
	}
}

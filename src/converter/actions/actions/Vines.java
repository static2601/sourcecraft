package converter.actions.actions;

import converter.actions.Action;
import converter.mapper.Mapper;
import minecraft.Block;
import minecraft.Position;
import minecraft.Property;
import periphery.Periphery;
import periphery.SourceGame;
import vmfWriter.Solid;
import vmfWriter.entity.solidEntity.AddTfPlayerCondition;
import vmfWriter.entity.solidEntity.FuncIllusionary;
import vmfWriter.entity.solidEntity.FuncLadder;

public class Vines extends Action {

	@Override
	public void add(Mapper context, Position p, Block material) {
		Position end = context.getCuboidFinder()
				.getBestY(p, material);
		
		int parts = 16;
		Position offset = null;
		Position negativeOffset = null;
		
		String east = material.getProperty(Property.east);
		String north = material.getProperty(Property.north);
		String south = material.getProperty(Property.south);
		String up = material.getProperty(Property.up);
		String west = material.getProperty(Property.west);

		String down = "false";
		if(material.get().toString().contains("down="))
			down = material.getProperty(Property.down);
		
		if(east.equals("true")) {
			offset =         new Position(15, 0, 0);
			negativeOffset = new Position(0, 0, 0);
			context.addSolidEntity(
					new FuncIllusionary(context.createCuboid(
							p, end, parts, offset, negativeOffset, material)));
		}
		if(north.equals("true")) {
			offset =         new Position(0, 0, 0);
			negativeOffset = new Position(0, 0, 15);
			context.addSolidEntity(
					new FuncIllusionary(context.createCuboid(
							p, end, parts, offset, negativeOffset, material)));
		}
		if(south.equals("true")) {
			offset =         new Position(0, 0, 15);
			negativeOffset = new Position(0, 0, 0);
			context.addSolidEntity(
					new FuncIllusionary(context.createCuboid(
							p, end, parts, offset, negativeOffset, material)));
		}
		if(up.equals("true")) {
			offset =         new Position(0, 15, 0);
			negativeOffset = new Position(0, 0, 0);
			context.addSolidEntity(
					new FuncIllusionary(context.createCuboid(
							p, end, parts, offset, negativeOffset, material)));
		}
		if(down.equals("true")) {
			offset =         new Position(0, 0, 0);
			negativeOffset = new Position(0, 15, 0);
			context.addSolidEntity(
					new FuncIllusionary(context.createCuboid(
							p, end, parts, offset, negativeOffset, material)));
		}
		if(west.equals("true")) { //default for trees
			offset =         new Position(0, 0, 0);
			negativeOffset = new Position(15, 0, 0);//(15, 16, 0);
			context.addSolidEntity(
					new FuncIllusionary(context.createCuboid(
							p, end, parts, offset, negativeOffset, material)));
		}
		SourceGame game = Periphery.CONFIG.getGame();

		if (game.getShortName().equals("tf"))
			context.addSolidEntity(new AddTfPlayerCondition(this.createArea(
					context, p, end, parts, offset, negativeOffset, material), 107, -1));
		else
			context.addSolidEntity(new FuncLadder(this.createArea(
					context, p, end, parts, offset, negativeOffset, material)));

		context.markAsConverted(p, end);
	}
	private Solid createArea(Mapper context, Position p, Position end,
			int pixels, Position startOffset, Position endOffset, Block material) {
		return context.createCuboid(p, end, pixels, startOffset, endOffset, material);
	}
}

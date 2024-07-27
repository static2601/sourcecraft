package converter.actions.actions;

import converter.actions.Action;
import converter.mapper.Mapper;
import minecraft.Block;
import minecraft.Position;
import minecraft.Property;

public class Snow extends Action {

	@Override
	public void add(Mapper context, Position p, Block material) {
		Position end = context.getCuboidFinder()
				.getBestXZ(p, material);
		
		int parts = 16;
		Position offset = new Position(0, 0, 0);
		Position negativeOffset = new Position(0, 15, 0);
		
		String layers = material.getProperty(Property.layers);
		
		if(layers.contains("1")) negativeOffset = new Position(0, 14, 0);
		if(layers.contains("2")) negativeOffset = new Position(0, 12, 0);
		if(layers.contains("3")) negativeOffset = new Position(0, 10, 0);
		if(layers.contains("4")) negativeOffset = new Position(0, 8, 0);
		if(layers.contains("5")) negativeOffset = new Position(0, 6, 0);
		if(layers.contains("6")) negativeOffset = new Position(0, 4, 0);
		if(layers.contains("7")) negativeOffset = new Position(0, 2, 0);
		if(layers.contains("8")) negativeOffset = new Position(0, 0, 0);
		
		
		
		context.addDetail(context.createCuboid(p, end, parts, offset, negativeOffset, material));
		//context.addSolid(context.createCuboid(position, end, block));
		context.markAsConverted(p, end);

	}
}

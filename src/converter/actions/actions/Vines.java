package converter.actions.actions;

import basic.Loggger;
import converter.actions.Action;
import converter.mapper.Mapper;
import minecraft.Block;
import minecraft.Position;
import minecraft.Property;

public class Vines extends Action {

	@Override
	public void add(Mapper context, Position p, Block material) {
		Position end = context.getCuboidFinder()
				.getBestY(p, material);
		
		int parts = 16;
		Position offset = new Position(0, 0, 0);
		Position negativeOffset = new Position(15, 16, 0);
		
		String east = material.getProperty(Property.east);
		String north = material.getProperty(Property.north);
		String south = material.getProperty(Property.south);
		String up = material.getProperty(Property.up);
		String west = material.getProperty(Property.west);

		//Loggger.log(material.get().toString());
		
		if(east.equals("true")) {
			offset =         new Position(15, 0, 0);
			negativeOffset = new Position(0, 0, 0);
			context.addDetail(context.createCuboid(p, end, parts, offset, negativeOffset, material));
		}
		if(north.equals("true")) {
			offset =         new Position(0, 0, 0);
			negativeOffset = new Position(0, 0, 15);
			context.addDetail(context.createCuboid(p, end, parts, offset, negativeOffset, material));
		}
		if(south.equals("true")) {
			offset =         new Position(0, 0, 15);
			negativeOffset = new Position(0, 0, 0);
			context.addDetail(context.createCuboid(p, end, parts, offset, negativeOffset, material));
		}
		if(up.equals("true")) {
			offset =         new Position(0, 15, 0);
			negativeOffset = new Position(0, 0, 0);
			context.addDetail(context.createCuboid(p, end, parts, offset, negativeOffset, material));
		}
		if(west.equals("true")) { //default for trees
			offset =         new Position(0, 0, 0);
			negativeOffset = new Position(15, 0, 0);//(15, 16, 0);
			context.addDetail(context.createCuboid(p, end, parts, offset, negativeOffset, material));
		}
		
		context.markAsConverted(p, end);

	}
}

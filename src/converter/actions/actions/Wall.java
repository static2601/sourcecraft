package converter.actions.actions;

import converter.actions.Action;
import converter.mapper.Mapper;
import minecraft.Block;
import minecraft.Position;
import minecraft.Property;

public class Wall extends Action {

	@Override
	public void add(Mapper context, Position p, Block material) {

		Position bestY = context.getCuboidFinder()
				.getBestY(p, material);
		Position bestXY = context.getCuboidFinder()
				.getBestXY(p, material);
		Position bestYZ = context.getCuboidFinder()
				.getBestYZ(p, material);
		
		String east = material.getProperty(Property.east);
		String north = material.getProperty(Property.north);
		String south = material.getProperty(Property.south);
		String waterlogged = material.getProperty(Property.waterlogged);
		String west = material.getProperty(Property.west);
		String up = material.getProperty(Property.up);
		
		String str = "";
		boolean low = false;

		if(east.equals("tall") || east.equals("low")) str += "E";
		if(west.equals("tall") || west.equals("low")) str += "W";
		if(north.equals("tall") || north.equals("low")) str += "N";
		if(south.equals("tall") || south.equals("low")) str += "S";
		//low wall
		if(east.equals("low")) low = true;
		if(west.equals("low")) low = true;
		if(north.equals("low")) low = true;
		if(south.equals("low")) low = true;

		switch (str) {
			//east to west full pane
			case "":
				if(up.equals("true")) Post(context, p, material, bestY);
				break;

			case "EW":
				EWTall(context, p, material, bestXY, low);
				break;

			//north to south full pane
			case "NS":
				NSTall(context, p, material, bestYZ, low);
				break;

			case "E":
				Post(context, p, material, bestY);
				ETall(context, p, material, bestXY, low);
				break;
				
			case "W":
				Post(context, p, material, bestY);
				WTall(context, p, material, bestXY, low);
				break;
				
			case "N":
				Post(context, p, material, bestY);
				NTall(context, p, material, bestYZ, low);
				break;
				
			case "S":
				Post(context, p, material, bestY);
				STall(context, p, material, bestYZ, low);
				break;

			//corner 
			case "EN":
				Post(context, p, material, bestY);
				ETall(context, p, material, bestXY, low);
				NTall(context, p, material, bestYZ, low);
				break;

			case "WN":
				Post(context, p, material, bestY);
				WTall(context, p, material, bestXY, low);
				NTall(context, p, material, bestYZ, low);
				break;
				
			case "ES":
				Post(context, p, material, bestY);
				ETall(context, p, material, bestXY, low);
				STall(context, p, material, bestYZ, low);
				break;
				
			case "WS":
				Post(context, p, material, bestY);
				WTall(context, p, material, bestXY, low);
				STall(context, p, material, bestYZ, low);
				break;

			//full wall with extension
			case "EWN":
				Post(context, p, material, bestY);
				ETall(context, p, material, bestXY, low);
				WTall(context, p, material, bestXY, low);
				NTall(context, p, material, bestYZ, low);
				break;
				
			case "EWS":
				Post(context, p, material, bestY);
				ETall(context, p, material, bestXY, low);
				WTall(context, p, material, bestXY, low);
				STall(context, p, material, bestYZ, low);
				break;
				
			case "ENS":
				Post(context, p, material, bestY);
				NTall(context, p, material, bestXY, low);
				STall(context, p, material, bestYZ, low);
				ETall(context, p, material, bestXY, low);
				break;
				
			case "WNS":
				Post(context, p, material, bestY);
				NTall(context, p, material, bestYZ, low);
				STall(context, p, material, bestYZ, low);
				WTall(context, p, material, bestXY, low);
				break;
			
			//four ways
			case "EWNS":
				Post(context, p, material, bestY);
				NTall(context, p, material, bestYZ, low);
				STall(context, p, material, bestYZ, low);
				ETall(context, p, material, bestXY, low);
				WTall(context, p, material, bestXY, low);
				break;
		}
	}
	private void Post(Mapper c, Position p, Block m, Position end) {
		int parts = 16;
		Position offset =         new Position(4, 0, 4);
		Position negativeOffset = new Position(4, 0, 4);
		c.addDetail(c.createCuboid(p, end, parts, offset, negativeOffset, m));
		c.markAsConverted(p, end);
	}
	//corners - tall
	private void EWTall(Mapper c, Position p, Block m, Position end, boolean low) {
		String up = c.getBlock(p).getProperty(Property.up);
		if(up.equals("true")) {
			int parts = 16;
			Position offset =         new Position(4, 0, 4);
			Position negativeOffset = new Position(4, 0, 4);
			c.addDetail(c.createCuboid(p, end, parts, offset, negativeOffset, m));
		}
		int parts = 16;
		int y = 0;
		if(low) y = 2;
		Position offset =         new Position(0, 0, 5);
		Position negativeOffset = new Position(0, y, 5);
		c.addDetail(c.createCuboid(p, end, parts, offset, negativeOffset, m));
		c.markAsConverted(p, end);
	}
	private void NSTall(Mapper c, Position p, Block m, Position end, boolean low) {
		String up = c.getBlock(p).getProperty(Property.up);
		if(up.equals("true")) {
			int parts = 16;
			Position offset =         new Position(4, 0, 4);
			Position negativeOffset = new Position(4, 0, 4);
			c.addDetail(c.createCuboid(p, end, parts, offset, negativeOffset, m));
		}
		int parts = 16;
		int y = 0;
		if(low) y = 2;
		Position offset =         new Position(5, 0, 0);
		Position negativeOffset = new Position(5, y, 0);
		c.addDetail(c.createCuboid(p, end, parts, offset, negativeOffset, m));
		c.markAsConverted(p, end);
	}
	private void ETall(Mapper c, Position p, Block m, Position end, boolean low) {
		int parts = 16;
		int y = 0;
		if(low) y = 2;
		Position offset =         new Position(8, 0, 5);
		Position negativeOffset = new Position(0, y, 5);
		c.addDetail(c.createCuboid(p, end, parts, offset, negativeOffset, m));
		c.markAsConverted(p, end);
	}
	private void WTall(Mapper c, Position p, Block m, Position end, boolean low) {
		int parts = 16;
		int y = 0;
		if(low) y = 2;
		Position offset =         new Position(0, 0, 5);
		Position negativeOffset = new Position(8, y, 5);
		c.addDetail(c.createCuboid(p, end, parts, offset, negativeOffset, m));
		c.markAsConverted(p, end);
	}
	private void NTall(Mapper c, Position p, Block m, Position end, boolean low) {
		int parts = 16;
		int y = 0;
		if(low) y = 2;
		Position offset =         new Position(5, 0, 0);
		Position negativeOffset = new Position(5, y, 8);
		c.addDetail(c.createCuboid(p, end, parts, offset, negativeOffset, m));
		c.markAsConverted(p, end);
	}
	private void STall(Mapper c, Position p, Block m, Position end, boolean low) {
		int parts = 16;
		int y = 0;
		if(low) y = 2;
		Position offset =         new Position(5, 0, 8);
		Position negativeOffset = new Position(5, y, 0);
		c.addDetail(c.createCuboid(p, end, parts, offset, negativeOffset, m));
		c.markAsConverted(p, end);
	}
}

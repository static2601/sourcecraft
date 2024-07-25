package converter.actions.actions;

import basic.Loggger;
import converter.actions.Action;
import converter.mapper.Mapper;
import minecraft.Block;
import minecraft.Position;
import minecraft.Property;

public class Wall extends Action {

	@Override
	public void add(Mapper context, Position p, Block material) {
		Position end;
		Position bestX = context.getCuboidFinder()
				.getBestX(p, material);
		Position bestY = context.getCuboidFinder()
				.getBestY(p, material);
		Position bestZ = context.getCuboidFinder()
				.getBestZ(p, material);
		Position bestXY = context.getCuboidFinder()
				.getBestXY(p, material);
		Position bestYZ = context.getCuboidFinder()
				.getBestYZ(p, material);
		Position bestXZ = context.getCuboidFinder()
				.getBestXZ(p, material);
		Position bestXYZ = context.getCuboidFinder()
				.getBestXYZ(p, material);
		
		String east = material.getProperty(Property.east);
		String north = material.getProperty(Property.north);
		String south = material.getProperty(Property.south);
		String waterlogged = material.getProperty(Property.waterlogged);
		String west = material.getProperty(Property.west);
		String up = material.getProperty(Property.up);
		
		Position offset = new Position(0, 0, 0);
		Position negativeOffset = new Position(0, 0, 0);
		int parts = 16;
		end = bestX;
		
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
		// post
		if(up.equals("true")) str += "U";
		Loggger.log("DEBUG:  Pane Directions " + str);



		switch (str) {
			case "EU":
				Post(context, p, material, bestY);
				ETall(context, p, material, bestX, low);
				context.markAsConverted(p, end);
				break;
				
			case "WU":
				Post(context, p, material, bestY);
				WTall(context, p, material, bestX, low);
				context.markAsConverted(p, end);
				break;
				
			case "NU":
				Post(context, p, material, bestY);
				NTall(context, p, material, bestZ, low);
				context.markAsConverted(p, end);
				break;
				
			case "SU":
				Post(context, p, material, bestY);
				STall(context, p, material, bestZ, low);
				context.markAsConverted(p, end);
				break;
			
			//up position, single post	
			case "U":
				Post(context, p, material, bestY);
				context.markAsConverted(p, end);
				break;
				
			//east to west full pane
			case "EW":
				EWTall(context, p, material, bestX, low);
				context.markAsConverted(p, end);
				break;
				
			//north to south full pane
			case "NS":
				NSTall(context, p, material, bestZ, low);
				context.markAsConverted(p, end);
				break;
				
			//corner 
			case "ENU":
				Post(context, p, material, bestY);
				ETall(context, p, material, bestZ, low);
				NTall(context, p, material, bestZ, low);
				context.markAsConverted(p, end);
				break;
				
			case "WNU":
				Post(context, p, material, bestY);
				WTall(context, p, material, bestX, low);
				NTall(context, p, material, bestZ, low);
				context.markAsConverted(p, end);
				break;
				
			case "ESU":
				Post(context, p, material, bestY);
				ETall(context, p, material, bestZ, low);
				STall(context, p, material, bestX, low);
				context.markAsConverted(p, end);
				break;
				
			case "WSU":
				Post(context, p, material, bestY);
				WTall(context, p, material, bestZ, low);
				STall(context, p, material, bestX, low);
				context.markAsConverted(p, end);
				break;
			
			//full wall with post
			case "NSU":
				Post(context, p, material, bestY);
				NSTall(context, p, material, bestZ, low);
				context.markAsConverted(p, end);
				break;
			
			case "EWU":
				Post(context, p, material, bestY);
				EWTall(context, p, material, bestZ, low);
				context.markAsConverted(p, end);
				break;
				
			//full wall with extension
			case "EWNU":
				Post(context, p, material, bestY);
				EWTall(context, p, material, bestX, low);
				NTall(context, p, material, bestZ, low);
				context.markAsConverted(p, end);
				break;
				
			case "EWSU":
				Post(context, p, material, bestY);
				EWTall(context, p, material, bestX, low);
				STall(context, p, material, bestZ, low);
				context.markAsConverted(p, end);
				break;
				
			case "ENSU":
				Post(context, p, material, bestY);
				NSTall(context, p, material, bestZ, low);
				ETall(context, p, material, bestX, low);
				context.markAsConverted(p, end);
				break;
				
			case "WNSU":
				Post(context, p, material, bestY);
				NSTall(context, p, material, bestZ, low);
				WTall(context, p, material, bestX, low);
				context.markAsConverted(p, end);
				break;
			
			//four ways
			case "EWNSU":
				Post(context, p, material, bestY);
				NSTall(context, p, material, bestZ, low);
				EWTall(context, p, material, bestX, low);
				context.markAsConverted(p, end);
				break;
			
			//single, no connecting direction
			//case "":
			//	//center post, no connections
			//	offset =         new Position(7, 0, 7);
			//	negativeOffset = new Position(7, 0, 7);
			//	end = bestY;
			//	context.addDetail(context.createCuboid(p, end, parts, offset, negativeOffset, material));
			//	context.markAsConverted(p, end);
			//	break;
				
		}
		//context.addDetail(context.createCuboid(p, end, parts, offset, negativeOffset, material));
		//context.markAsConverted(p, end);
	}
	private void Post(Mapper c, Position p, Block m, Position end) {
		int parts = 16;
		Position offset =         new Position(4, 0, 4);
		Position negativeOffset = new Position(4, 0, 4);
		c.addDetail(c.createCuboid(p, end, parts, offset, negativeOffset, m));
	}
	private void EWLow(Mapper c, Position p, Block m, Position end) {
		int parts = 16;
		Position offset =         new Position(0, 0, 5);
		Position negativeOffset = new Position(0, 2, 5);
		c.addDetail(c.createCuboid(p, end, parts, offset, negativeOffset, m));
	}
	private void NSLow(Mapper c, Position p, Block m, Position end) {
		int parts = 16;
		Position offset =         new Position(5, 0, 0);
		Position negativeOffset = new Position(5, 2, 0);
		c.addDetail(c.createCuboid(p, end, parts, offset, negativeOffset, m));
	}
	private void EWTall(Mapper c, Position p, Block m, Position end, boolean low) {
		int parts = 16;
		int y = 0;
		if(low) y = 2;
		Position offset =         new Position(0, 0, 5);
		Position negativeOffset = new Position(0, y, 5);
		c.addDetail(c.createCuboid(p, end, parts, offset, negativeOffset, m));
	}
	private void NSTall(Mapper c, Position p, Block m, Position end, boolean low) {
		int parts = 16;
		int y = 0;
		if(low) y = 2;
		Position offset =         new Position(5, 0, 0);
		Position negativeOffset = new Position(5, y, 0);
		c.addDetail(c.createCuboid(p, end, parts, offset, negativeOffset, m));
	}
	//corners - low
	private void ELow(Mapper c, Position p, Block m, Position end) {
		int parts = 16;
		Position offset =         new Position(12, 0, 5);
		Position negativeOffset = new Position(0, 2, 5);
		c.addDetail(c.createCuboid(p, end, parts, offset, negativeOffset, m));
	}
	private void WLow(Mapper c, Position p, Block m, Position end) {
		int parts = 16;
		Position offset =         new Position(0, 0, 5);
		Position negativeOffset = new Position(12, 2, 5);
		c.addDetail(c.createCuboid(p, end, parts, offset, negativeOffset, m));
	}
	private void NLow(Mapper c, Position p, Block m, Position end) {
		int parts = 16;
		Position offset =         new Position(5, 0, 0);
		Position negativeOffset = new Position(5, 2, 12);
		c.addDetail(c.createCuboid(p, end, parts, offset, negativeOffset, m));
	}
	private void SLow(Mapper c, Position p, Block m, Position end) {
		int parts = 16;
		Position offset =         new Position(5, 0, 12);
		Position negativeOffset = new Position(5, 2, 0);
		c.addDetail(c.createCuboid(p, end, parts, offset, negativeOffset, m));
	}
	//corners - tall
	private void ETall(Mapper c, Position p, Block m, Position end, boolean low) {
		int parts = 16;
		int y = 0;
		if(low) y = 2;
		Position offset =         new Position(12, 0, 5);
		Position negativeOffset = new Position(0, y, 5);
		c.addDetail(c.createCuboid(p, end, parts, offset, negativeOffset, m));
	}
	private void WTall(Mapper c, Position p, Block m, Position end, boolean low) {
		int parts = 16;
		int y = 0;
		if(low) y = 2;
		Position offset =         new Position(0, 0, 5);
		Position negativeOffset = new Position(12, y, 5);
		c.addDetail(c.createCuboid(p, end, parts, offset, negativeOffset, m));
	}
	private void NTall(Mapper c, Position p, Block m, Position end, boolean low) {
		int parts = 16;
		int y = 0;
		if(low) y = 2;
		Position offset =         new Position(5, 0, 0);
		Position negativeOffset = new Position(5, y, 12);
		c.addDetail(c.createCuboid(p, end, parts, offset, negativeOffset, m));
	}
	private void STall(Mapper c, Position p, Block m, Position end, boolean low) {
		int parts = 16;
		int y = 0;
		if(low) y = 2;
		Position offset =         new Position(5, 0, 12);
		Position negativeOffset = new Position(5, y, 0);
		c.addDetail(c.createCuboid(p, end, parts, offset, negativeOffset, m));
	}

}

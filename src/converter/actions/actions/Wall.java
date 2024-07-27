package converter.actions.actions;

import basic.Loggger;
import converter.actions.Action;
import converter.mapper.Mapper;
import minecraft.Block;
import minecraft.Position;
import minecraft.Property;

public class Wall extends Action {
	//get best not working properly, major overlapping of brushes
	//needs further work
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
		
		//Position offset = new Position(0, 0, 0);
		//Position negativeOffset = new Position(0, 0, 0);
		//int parts = 16;
		//end = bestX;
		
		String str = "";
		boolean low = false;
		/*
		if(east.equals("tall")) ETall(context, p, material, bestX, false);
		if(west.equals("tall")) WTall(context, p, material, bestX, false);
		if(north.equals("tall")) NTall(context, p, material, bestZ, false);
		if(south.equals("tall")) STall(context, p, material, bestZ, false);
		//low wall
		if(east.equals("low")) ETall(context, p, material, bestX, true);
		if(west.equals("low")) WTall(context, p, material, bestX, true);
		if(north.equals("low")) NTall(context, p, material, bestZ, true);
		if(south.equals("low")) STall(context, p, material, bestZ, true);
		// post
		if(up.equals("true")) Post(context, p, material, bestY);
		//context.markAsConverted(p);
		*/

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
		//Loggger.log("DEBUG:  Pane Directions " + str);



		switch (str) {
			case "EU":
				Post(context, p, material, bestY);
				ETall(context, p, material, bestXY, low);
				//context.markAsConverted(p);
				break;
				
			case "WU":
				Post(context, p, material, bestY);
				WTall(context, p, material, bestXY, low);
				//context.markAsConverted(p);
				break;
				
			case "NU":
				Post(context, p, material, bestY);
				NTall(context, p, material, bestYZ, low);
				//context.markAsConverted(p);
				break;
				
			case "SU":
				Post(context, p, material, bestY);
				STall(context, p, material, bestYZ, low);
				//context.markAsConverted(p);
				break;
			
			//up position, single post	
			case "U":
				Post(context, p, material, bestY);
				//context.markAsConverted(p);
				break;
				
			//east to west full pane
			case "EW":
				ETall(context, p, material, bestXY, low);
				WTall(context, p, material, bestXY, low);
				//context.markAsConverted(p);
				break;
				
			//north to south full pane
			case "NS":
				NTall(context, p, material, bestYZ, low);
				STall(context, p, material, bestYZ, low);
				//context.markAsConverted(p);
				break;
				
			//corner 
			case "ENU":
				Post(context, p, material, bestY);
				ETall(context, p, material, bestXY, low);
				NTall(context, p, material, bestYZ, low);
				//context.markAsConverted(p);
				break;
				
			case "WNU":
				Post(context, p, material, bestY);
				WTall(context, p, material, bestXY, low);
				NTall(context, p, material, bestYZ, low);
				//context.markAsConverted(p);
				break;
				
			case "ESU":
				Post(context, p, material, bestY);
				ETall(context, p, material, bestXY, low);
				STall(context, p, material, bestYZ, low);
				//context.markAsConverted(p);
				break;
				
			case "WSU":
				Post(context, p, material, bestY);
				WTall(context, p, material, bestXY, low);
				STall(context, p, material, bestYZ, low);
				//context.markAsConverted(p);
				break;
			
			//full wall with post
			case "NSU":
				Post(context, p, material, bestY);
				NTall(context, p, material, bestYZ, low);
				STall(context, p, material, bestYZ, low);
				//context.markAsConverted(p);
				break;
			
			case "EWU":
				Post(context, p, material, bestY);
				ETall(context, p, material, bestXY, low);
				WTall(context, p, material, bestXY, low);
				//context.markAsConverted(p);
				break;
				
			//full wall with extension
			case "EWNU":
				Post(context, p, material, bestY);
				ETall(context, p, material, bestXY, low);
				WTall(context, p, material, bestXY, low);
				NTall(context, p, material, bestYZ, low);
				//context.markAsConverted(p);
				break;
				
			case "EWSU":
				Post(context, p, material, bestY);
				ETall(context, p, material, bestXY, low);
				WTall(context, p, material, bestXY, low);
				STall(context, p, material, bestYZ, low);
				//context.markAsConverted(p);
				break;
				
			case "ENSU":
				Post(context, p, material, bestY);
				NTall(context, p, material, bestXY, low);
				STall(context, p, material, bestYZ, low);
				ETall(context, p, material, bestXY, low);
				//context.markAsConverted(p);
				break;
				
			case "WNSU":
				Post(context, p, material, bestY);
				NTall(context, p, material, bestYZ, low);
				STall(context, p, material, bestYZ, low);
				WTall(context, p, material, bestXY, low);
				//context.markAsConverted(p);
				break;
			
			//four ways
			case "EWNSU":
				Post(context, p, material, bestY);
				NTall(context, p, material, bestYZ, low);
				STall(context, p, material, bestYZ, low);
				ETall(context, p, material, bestXY, low);
				WTall(context, p, material, bestXY, low);
				//EWTall(context, p, material, bestX, low);
				//context.markAsConverted(p);
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
		c.markAsConverted(p, end);
		//c.getCuboidFinder();
		//Loggger.log("c.getBlock(p): " + c.getBlock(p) + " p: "+ p);
		Loggger.log("c.getBlock(p): " + c.getBlock(p) + " p: "+ p);
	}
	//corners - tall
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

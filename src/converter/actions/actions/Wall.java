package converter.actions.actions;

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
		if(east.equals("true")) str += "E";
		if(west.equals("true")) str += "W";
		if(north.equals("true")) str += "N";
		if(south.equals("true")) str += "S";
		if(up.equals("true")) str += "U";
		//Loggger.log("DEBUG:  Pane Directions " + str);
			
		switch (str) {
			case "EU":
				offset =         new Position(4, 0, 4);
				negativeOffset = new Position(4, 0, 4);
				end = bestY;
				context.addDetail(context.createCuboid(p, end, parts, offset, negativeOffset, material));
				
				offset =         new Position(12, 0, 5);
				negativeOffset = new Position(0, 2, 5);
				end = bestX;
				context.addDetail(context.createCuboid(p, end, parts, offset, negativeOffset, material));
				context.markAsConverted(p, end);
				break;
				
			case "WU":
				offset =         new Position(4, 0, 4);
				negativeOffset = new Position(4, 0, 4);
				end = bestY;
				context.addDetail(context.createCuboid(p, end, parts, offset, negativeOffset, material));
				
				offset =         new Position(0, 0, 5);
				negativeOffset = new Position(12, 2, 5);
				end = bestX;
				context.addDetail(context.createCuboid(p, end, parts, offset, negativeOffset, material));
				context.markAsConverted(p, end);
				break;
				
			case "NU":
				offset =         new Position(4, 0, 4);
				negativeOffset = new Position(4, 0, 4);
				end = bestY;
				context.addDetail(context.createCuboid(p, end, parts, offset, negativeOffset, material));
				
				offset =         new Position(5, 0, 0);
				negativeOffset = new Position(5, 2, 12);
				end = bestZ;
				context.addDetail(context.createCuboid(p, end, parts, offset, negativeOffset, material));
				context.markAsConverted(p, end);
				break;
				
			case "SU":
				offset =         new Position(4, 0, 4);
				negativeOffset = new Position(4, 0, 4);
				end = bestY;
				context.addDetail(context.createCuboid(p, end, parts, offset, negativeOffset, material));
				
				offset =         new Position(5, 0, 12);
				negativeOffset = new Position(5, 2, 0);
				end = bestZ;
				context.addDetail(context.createCuboid(p, end, parts, offset, negativeOffset, material));
				context.markAsConverted(p, end);
				break;
			
			//up position, single post	
			case "U":
				offset =         new Position(4, 0, 4);
				negativeOffset = new Position(4, 0, 4);
				end = bestY;
				context.addDetail(context.createCuboid(p, end, parts, offset, negativeOffset, material));
				context.markAsConverted(p, end);
				break;
				
			//east to west full pane
			case "EW":
				offset =         new Position(0, 0, 5);
				negativeOffset = new Position(0, 2, 5);
				end = bestX;
				context.addDetail(context.createCuboid(p, end, parts, offset, negativeOffset, material));
				context.markAsConverted(p, end);
				break;
				
			//north to south full pane
			case "NS":
				offset =         new Position(5, 0, 0);
				negativeOffset = new Position(5, 2, 0);
				end = bestZ;
				context.addDetail(context.createCuboid(p, end, parts, offset, negativeOffset, material));
				context.markAsConverted(p, end);
				break;
				
			//corner 
			case "ENU":
				offset =         new Position(4, 0, 4);
				negativeOffset = new Position(4, 0, 4);
				end = bestY;
				context.addDetail(context.createCuboid(p, end, parts, offset, negativeOffset, material));
				
				//north piece
				offset =         new Position(5, 0, 0);
				negativeOffset = new Position(5, 2, 12);
				end = bestZ;
				context.addDetail(context.createCuboid(p, end, parts, offset, negativeOffset, material));
				
				//east piece
				offset =         new Position(12, 0, 5);
				negativeOffset = new Position(0, 2, 5);
				end = bestZ;
				context.addDetail(context.createCuboid(p, end, parts, offset, negativeOffset, material));
				context.markAsConverted(p, end);
				break;
				
			case "WNU":
				offset =         new Position(4, 0, 4);
				negativeOffset = new Position(4, 0, 4);
				end = bestY;
				context.addDetail(context.createCuboid(p, end, parts, offset, negativeOffset, material));
				
				//north piece
				offset =         new Position(5, 0, 0);
				negativeOffset = new Position(5, 2, 12);
				end = bestZ;
				context.addDetail(context.createCuboid(p, end, parts, offset, negativeOffset, material));
				
				//west piece
				offset =         new Position(0, 0, 5);
				negativeOffset = new Position(12, 2, 5);
				end = bestX;
				context.addDetail(context.createCuboid(p, end, parts, offset, negativeOffset, material));
				context.markAsConverted(p, end);
				break;
				
			case "ESU":
				offset =         new Position(4, 0, 4);
				negativeOffset = new Position(4, 0, 4);
				end = bestY;
				context.addDetail(context.createCuboid(p, end, parts, offset, negativeOffset, material));
				
				//south piece
				offset =         new Position(5, 0, 12);
				negativeOffset = new Position(5, 2, 0);
				end = bestZ;
				context.addDetail(context.createCuboid(p, end, parts, offset, negativeOffset, material));
				
				//east piece
				offset =         new Position(12, 0, 5);
				negativeOffset = new Position(0, 2, 5);
				end = bestX;
				context.addDetail(context.createCuboid(p, end, parts, offset, negativeOffset, material));
				context.markAsConverted(p, end);
				break;
				
			case "WSU":
				offset =         new Position(4, 0, 4);
				negativeOffset = new Position(4, 0, 4);
				end = bestY;
				context.addDetail(context.createCuboid(p, end, parts, offset, negativeOffset, material));
				
				//south piece
				offset =         new Position(5, 0, 12);
				negativeOffset = new Position(5, 2, 0);
				end = bestZ;
				context.addDetail(context.createCuboid(p, end, parts, offset, negativeOffset, material));
				
				//west piece
				offset =         new Position(0, 0, 5);
				negativeOffset = new Position(12, 2, 5);
				end = bestX;
				context.addDetail(context.createCuboid(p, end, parts, offset, negativeOffset, material));
				context.markAsConverted(p, end);
				break;
			
			//full wall with post
			case "NSU":
				offset =         new Position(4, 0, 4);
				negativeOffset = new Position(4, 0, 4);
				end = bestY;
				context.addDetail(context.createCuboid(p, end, parts, offset, negativeOffset, material));
				
				//north to south piece
				offset =         new Position(5, 0, 0);
				negativeOffset = new Position(5, 2, 0);
				end = bestZ;
				context.addDetail(context.createCuboid(p, end, parts, offset, negativeOffset, material));
				context.markAsConverted(p, end);
				break;
			
			case "EWU":
				offset =         new Position(4, 0, 4);
				negativeOffset = new Position(4, 0, 4);
				end = bestY;
				context.addDetail(context.createCuboid(p, end, parts, offset, negativeOffset, material));
				
				//north to south piece
				offset =         new Position(0, 0, 5);
				negativeOffset = new Position(0, 2, 5);
				end = bestZ;
				context.addDetail(context.createCuboid(p, end, parts, offset, negativeOffset, material));
				context.markAsConverted(p, end);
				break;
				
			//full wall with extension
			case "EWNU":
				offset =         new Position(4, 0, 4);
				negativeOffset = new Position(4, 0, 4);
				end = bestY;
				context.addDetail(context.createCuboid(p, end, parts, offset, negativeOffset, material));
				
				//full pane east to west
				offset =         new Position(0, 0, 5);
				negativeOffset = new Position(0, 2, 5);
				end = bestX;
				context.addDetail(context.createCuboid(p, end, parts, offset, negativeOffset, material));
						
				//north piece
				offset =         new Position(5, 0, 0);
				negativeOffset = new Position(5, 2, 12);
				end = bestZ;
				context.addDetail(context.createCuboid(p, end, parts, offset, negativeOffset, material));
				context.markAsConverted(p, end);
				break;
				
			case "EWSU":
				offset =         new Position(4, 0, 4);
				negativeOffset = new Position(4, 0, 4);
				end = bestY;
				context.addDetail(context.createCuboid(p, end, parts, offset, negativeOffset, material));
				
				//full pane east to west
				offset =         new Position(0, 0, 5);
				negativeOffset = new Position(0, 2, 5);
				end = bestX;
				context.addDetail(context.createCuboid(p, end, parts, offset, negativeOffset, material));
						
				//south piece
				offset =         new Position(5, 0, 12);
				negativeOffset = new Position(5, 2, 0);
				end = bestZ;
				context.addDetail(context.createCuboid(p, end, parts, offset, negativeOffset, material));
				context.markAsConverted(p, end);
				break;
				
			case "ENSU":
				offset =         new Position(4, 0, 4);
				negativeOffset = new Position(4, 0, 4);
				end = bestY;
				context.addDetail(context.createCuboid(p, end, parts, offset, negativeOffset, material));
				
				//full pane north to south
				offset =         new Position(5, 0, 0);
				negativeOffset = new Position(5, 2, 0);
				end = bestZ;
				context.addDetail(context.createCuboid(p, end, parts, offset, negativeOffset, material));
						
				//east piece
				offset =         new Position(12, 0, 5);
				negativeOffset = new Position(0, 2, 5);
				end = bestX;
				context.addDetail(context.createCuboid(p, end, parts, offset, negativeOffset, material));
				context.markAsConverted(p, end);
				break;
				
			case "WNSU":
				offset =         new Position(4, 0, 4);
				negativeOffset = new Position(4, 0, 4);
				end = bestY;
				context.addDetail(context.createCuboid(p, end, parts, offset, negativeOffset, material));
				
				//full pane north to south
				offset =         new Position(5, 0, 0);
				negativeOffset = new Position(5, 2, 0);
				end = bestZ;
				context.addDetail(context.createCuboid(p, end, parts, offset, negativeOffset, material));
						
				//west piece
				offset =         new Position(0, 0, 5);
				negativeOffset = new Position(12, 2, 5);
				end = bestX;
				context.addDetail(context.createCuboid(p, end, parts, offset, negativeOffset, material));
				context.markAsConverted(p, end);
				break;
			
			//four ways
			case "EWNSU":
				offset =         new Position(4, 0, 4);
				negativeOffset = new Position(4, 0, 4);
				end = bestY;
				context.addDetail(context.createCuboid(p, end, parts, offset, negativeOffset, material));
				
				//full pane east to west
				offset =         new Position(0, 0, 5);
				negativeOffset = new Position(0, 2, 5);
				end = bestX;
				context.addDetail(context.createCuboid(p, end, parts, offset, negativeOffset, material));
						
				//full north to south
				offset =         new Position(5, 0, 0);
				negativeOffset = new Position(5, 2, 0);
				end = bestZ;
				context.addDetail(context.createCuboid(p, end, parts, offset, negativeOffset, material));
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
}

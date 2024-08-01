package converter.actions.actions;

import converter.actions.Action;
import converter.mapper.Mapper;
import minecraft.Block;
import minecraft.Position;
import minecraft.Property;

public class GlassPane extends Action {
	
	@Override
	public void add(Mapper context, Position p, Block material) {
		Position end;
		Position bestY = context.getCuboidFinder()
				.getBestY(p, material);
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
		
		Position offset;
		Position negativeOffset;
		int parts = 16;
		end = bestXY;
		
		String str = "";
		if(east.equals("true")) str += "E";
		if(west.equals("true")) str += "W";
		if(north.equals("true")) str += "N";
		if(south.equals("true")) str += "S";
			
		switch (str) {
			case "E":
				offset =         new Position(7, 0, 7);
				negativeOffset = new Position(0, 0, 7);
				end = bestXY;
				context.addDetail(context.createCuboid(p, end, parts, offset, negativeOffset, material));
				break;
				
			case "W":
				offset =         new Position(0, 0, 7);
				negativeOffset = new Position(7, 0, 7);
				end = bestXY;
				context.addDetail(context.createCuboid(p, end, parts, offset, negativeOffset, material));
				break;
				
			case "N":
				offset =         new Position(7, 0, 0);
				negativeOffset = new Position(7, 0, 7);
				end = bestYZ;
				context.addDetail(context.createCuboid(p, end, parts, offset, negativeOffset, material));
				break;
				
			case "S":
				offset =         new Position(7, 0, 7);
				negativeOffset = new Position(7, 0, 0);
				end = bestYZ;
				context.addDetail(context.createCuboid(p, end, parts, offset, negativeOffset, material));
				break;
				
			//east to west full pane
			case "EW":
				offset =         new Position(0, 0, 7);
				negativeOffset = new Position(0, 0, 7);
				end = bestXY;
				context.addDetail(context.createCuboid(p, end, parts, offset, negativeOffset, material));
				break;
				
			//north to south full pane
			case "NS":
				offset =         new Position(7, 0, 0);
				negativeOffset = new Position(7, 0, 0);
				end = bestYZ;
				context.addDetail(context.createCuboid(p, end, parts, offset, negativeOffset, material));
				break;
				
			//corner 
			case "EN":
				//north peice
				offset =         new Position(7, 0, 0);
				negativeOffset = new Position(7, 0, 9);
				end = bestYZ;
				context.addDetail(context.createCuboid(p, end, parts, offset, negativeOffset, material));
				
				//east peice
				offset =         new Position(7, 0, 7);
				negativeOffset = new Position(0, 0, 7);
				end = bestXY;
				context.addDetail(context.createCuboid(p, end, parts, offset, negativeOffset, material));
				break;
				
			case "WN":
				//north peice
				offset =         new Position(7, 0, 0);
				negativeOffset = new Position(7, 0, 9);
				end = bestYZ;
				context.addDetail(context.createCuboid(p, end, parts, offset, negativeOffset, material));
				
				//west peice
				offset =         new Position(0, 0, 7);
				negativeOffset = new Position(7, 0, 7);
				end = bestXY;
				context.addDetail(context.createCuboid(p, end, parts, offset, negativeOffset, material));
				break;
				
			case "ES":
				//south peice
				offset =         new Position(7, 0, 9);
				negativeOffset = new Position(7, 0, 0);
				end = bestYZ;
				context.addDetail(context.createCuboid(p, end, parts, offset, negativeOffset, material));
				
				//east peice
				offset =         new Position(7, 0, 7);
				negativeOffset = new Position(0, 0, 7);
				end = bestXY;
				context.addDetail(context.createCuboid(p, end, parts, offset, negativeOffset, material));
				break;
				
			case "WS":
				//south peice
				offset =         new Position(7, 0, 9);
				negativeOffset = new Position(7, 0, 0);
				end = bestYZ;
				context.addDetail(context.createCuboid(p, end, parts, offset, negativeOffset, material));
				
				//west peice
				offset =         new Position(0, 0, 7);
				negativeOffset = new Position(7, 0, 7);
				end = bestXY;
				context.addDetail(context.createCuboid(p, end, parts, offset, negativeOffset, material));
				break;
				
			//three way
			case "EWN":
				//full pane east to west
				offset =         new Position(0, 0, 7);
				negativeOffset = new Position(0, 0, 7);
				end = bestXY;
				context.addDetail(context.createCuboid(p, end, parts, offset, negativeOffset, material));
						
				//north peice
				offset =         new Position(7, 0, 0);
				negativeOffset = new Position(7, 0, 9);
				end = bestXZ;
				context.addDetail(context.createCuboid(p, end, parts, offset, negativeOffset, material));
				break;
			case "EWS":
				//full pane east to west
				offset =         new Position(0, 0, 7);
				negativeOffset = new Position(0, 0, 7);
				end = bestXY;
				context.addDetail(context.createCuboid(p, end, parts, offset, negativeOffset, material));
						
				//south peice
				offset =         new Position(7, 0, 9);
				negativeOffset = new Position(7, 0, 0);
				end = bestXZ;
				context.addDetail(context.createCuboid(p, end, parts, offset, negativeOffset, material));
				break;
			case "ENS":
				//full pane north to south
				offset =         new Position(7, 0, 0);
				negativeOffset = new Position(7, 0, 0);
				end = bestXZ;
				context.addDetail(context.createCuboid(p, end, parts, offset, negativeOffset, material));
						
				//east peice
				offset =         new Position(9, 0, 7);
				negativeOffset = new Position(0, 0, 7);
				end = bestXY;
				context.addDetail(context.createCuboid(p, end, parts, offset, negativeOffset, material));
				break;
			case "WNS":
				//full pane north to south
				offset =         new Position(7, 0, 0);
				negativeOffset = new Position(7, 0, 0);
				end = bestXZ;
				context.addDetail(context.createCuboid(p, end, parts, offset, negativeOffset, material));
						
				//west peice
				offset =         new Position(0, 0, 7);
				negativeOffset = new Position(9, 0, 7);
				end = bestXY;
				context.addDetail(context.createCuboid(p, end, parts, offset, negativeOffset, material));
				break;
			
			//four ways
			case "EWNS":
				//full pane east to west
				offset =         new Position(0, 0, 7);
				negativeOffset = new Position(0, 0, 7);
				end = bestXY;
				context.addDetail(context.createCuboid(p, end, parts, offset, negativeOffset, material));
						
				//full north to south
				offset =         new Position(7, 0, 0);
				negativeOffset = new Position(7, 0, 0);
				end = bestXZ;
				context.addDetail(context.createCuboid(p, end, parts, offset, negativeOffset, material));
				break;
			
			//single, no connecting direction
			case "":
				//center post, no connections
				offset =         new Position(7, 0, 7);
				negativeOffset = new Position(7, 0, 7);
				end = bestY;
				context.addDetail(context.createCuboid(p, end, parts, offset, negativeOffset, material));
				break;
		}
		context.markAsConverted(p, end);
	}
}

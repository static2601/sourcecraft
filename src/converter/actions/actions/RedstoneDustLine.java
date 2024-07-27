package converter.actions.actions;

import converter.actions.Action;
import converter.mapper.Mapper;
import minecraft.Block;
import minecraft.Position;
import minecraft.Property;
import vmfWriter.entity.pointEntity.pointEntity.PropStatic;

public class RedstoneDustLine extends Action {

	private final static String MODEL = "models/props/minecraft_original/redstonedustline.mdl";
	private final static PropStatic redstone = new PropStatic(RedstoneDustLine.MODEL);

	@Override
	public void add(Mapper context, Position position, Block material) {
		String east = material.getProperty(Property.east);
		String north = material.getProperty(Property.north);
		String south = material.getProperty(Property.south);
		String west = material.getProperty(Property.west);
		
		String power = material.getProperty(Property.power);
		int verticalAngle = 0;
		
		if(east.equals("side") && west.equals("side")) {
			//set direction
			verticalAngle = 90;
		}else if(north.equals("side") && south.equals("side")) {
			//set direction
			verticalAngle = 180;
		}

		switch (power) {
		case "0":
			RedstoneDustLine.redstone.setSkin(0);
			break;
		case "1":
			RedstoneDustLine.redstone.setSkin(1);
			break;
		case "2":
			RedstoneDustLine.redstone.setSkin(2);
			break;
		case "3":
			RedstoneDustLine.redstone.setSkin(3);
			break;
		case "4":
			RedstoneDustLine.redstone.setSkin(4);
			break;
		case "5":
			RedstoneDustLine.redstone.setSkin(5);
			break;
		case "6":
			RedstoneDustLine.redstone.setSkin(6);
			break;
		case "7":
			RedstoneDustLine.redstone.setSkin(7);
			break;
		case "8":
			RedstoneDustLine.redstone.setSkin(8);
			break;
		case "9":
			RedstoneDustLine.redstone.setSkin(9);
			break;
		case "10":
			RedstoneDustLine.redstone.setSkin(10);
			break;
		case "11":
			RedstoneDustLine.redstone.setSkin(11);
			break;
		case "12":
			RedstoneDustLine.redstone.setSkin(12);
			break;
		case "13":
			RedstoneDustLine.redstone.setSkin(13);
			break;
		case "14":
			RedstoneDustLine.redstone.setSkin(14);
			break;
		case "15":
			RedstoneDustLine.redstone.setSkin(15);
			break;
		default:
			RedstoneDustLine.redstone.setSkin(0);
			break;
		}
		context.setPointToGrid(position);
		context.movePointInGridDimension(0.5, 0, 0.5);
		//int verticalAngle = (int) (Math.random() * 360);
		
		RedstoneDustLine.redstone.getAngles()
				.setY(verticalAngle);
		
		RedstoneDustLine.redstone.setSolid(0);
		RedstoneDustLine.redstone.disableShadows(1);
		
		context.addPointEntity(RedstoneDustLine.redstone);
		context.markAsConverted(position);
	}
}

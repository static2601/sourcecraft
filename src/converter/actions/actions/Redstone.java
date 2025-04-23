package converter.actions.actions;

import converter.actions.Action;
import converter.mapper.Mapper;
import minecraft.Block;
import minecraft.Position;
import minecraft.Property;
import vmfWriter.entity.pointEntity.PointEntity;
import vmfWriter.entity.pointEntity.pointEntity.PropStatic;

public class Redstone extends Action {
	private static String COMPREPEATER = "models/props/minecraft_original/ComparatorRepeater.mdl";
	private static String REDINDICATOR = "models/props/minecraft_original/RedstoneIndicator.mdl";
	private static String REDSWITCH = "models/props/minecraft_original/RedstoneSwitch.mdl";
	private final PropStatic redstone = new PropStatic(COMPREPEATER);

	// TODO still need locking bar for repeater
	@Override
	public void add(Mapper context, Position pos, Block block) {
		context.setPointToGrid(pos);
		context.movePointInGridDimension(0.5, 0, 0.5);
		int rotation = 0;
		String facing = block.getProperty(Property.facing);

		switch (facing) {
			case "north":
				rotation = -90;
				break;
			case "east":
				rotation = 180;
				break;
			case "south":
				rotation = -270;
				break;
			case "west":
				rotation = 0;
				break;
		}
		redstone.getAngles().setY(rotation);
		redstone.setSolid(0);
		redstone.disableShadows(1);

		if(block.getName().endsWith("comparator")) {
			String powered = block.getProperty(Property.powered);
			if(powered.equals("true")) redstone.setSkin(1);
			else redstone.setSkin(0);
			redstone.setModel(COMPREPEATER);
			context.addPointEntity(redstone);

			// add indicator and switch
			redstone.setModel(REDINDICATOR);

			String mode = block.getProperty(Property.mode);
			if(mode.equals("compare")) {
				//indicator off and not raised
				redstone.setSkin(1);
				context.addPointEntity(redstone);
			} else {
				// indicator on and raised by one pixel
				redstone.setSkin(0);
				context.movePointInGridDimension(0, ((double) 1/15),0);
				context.addPointEntity(redstone);
				//reset back to default
				context.movePointInGridDimension(0, ((double) -1/15),0);
			}
			// add switches
			redstone.setModel(REDSWITCH);
			if(powered.equals("true")) redstone.setSkin(0);
			else redstone.setSkin(1);
			if(facing.equals("east") || facing.equals("west")) {
				context.movePointInGridDimension(0, 0, ((double) 3 / 15));
				context.addPointEntity(redstone);
				context.movePointInGridDimension(0, 0, ((double) -6 / 15));
				context.addPointEntity(redstone);
			} else {
				context.movePointInGridDimension(((double) 3 / 15), 0, 0);
				context.addPointEntity(redstone);
				context.movePointInGridDimension(((double) -6 / 15), 0, 0);
				context.addPointEntity(redstone);
			}
		}
		else if(block.getName().endsWith("repeater")) {
			String powered = block.getProperty(Property.powered);
			if(powered.equals("true")) redstone.setSkin(3);
			else redstone.setSkin(2);
			redstone.setModel(COMPREPEATER);
			context.addPointEntity(redstone);

			// add switch model for indicator
			redstone.setModel(REDSWITCH);
			if(facing.equals("north"))
				context.movePointInGridDimension(0,0,((double) 9/16));
			if(facing.equals("east"))
				context.movePointInGridDimension(((double) -9/16),0,0);
			if(facing.equals("south"))
				context.movePointInGridDimension(0,0,((double) -9/16));
			if(facing.equals("west"))
				context.movePointInGridDimension(((double) 9/16),0,0);

			if(powered.equals("true")) redstone.setSkin(0);
			else redstone.setSkin(1);

			// add indicator
			context.addPointEntity(redstone);

			// add switch at set delay
			redstone.setModel(REDSWITCH);
			String delay = block.getProperty(Property.delay);

			int x = 2 + (Integer.parseInt(delay)*2);
			if(facing.equals("north"))
				context.movePointInGridDimension(0,0,((double) -x/16));
			if(facing.equals("east"))
				context.movePointInGridDimension(((double) x/16),0,0);
			if(facing.equals("south"))
				context.movePointInGridDimension(0,0,((double) x/16));
			if(facing.equals("west"))
				context.movePointInGridDimension(((double) -x/16),0,0);

			context.addPointEntity(redstone);
		}
	}
}
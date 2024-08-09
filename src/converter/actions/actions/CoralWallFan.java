package converter.actions.actions;

import converter.actions.Action;
import converter.mapper.Mapper;
import minecraft.Block;
import minecraft.Position;
import minecraft.Property;
import vmfWriter.entity.pointEntity.pointEntity.PropStatic;

public class CoralWallFan extends Action {

	// skin, make index for all skin names
	private int skin;
	// not solid = 0, use bounding box = 2, use vphysics = 6
	private int solid;

	private static String MODEL = "models/props/minecraft_original/coral_wall_fan.mdl";
	private PropStatic coralFan = new PropStatic(CoralWallFan.MODEL);

	public CoralWallFan(int skin, int solid) {
		this.skin = skin;
		this.solid = solid;
		this.coralFan.setSkin(skin, solid); // set in PropStatic.java
	}

	@Override
	public void add(Mapper context, Position p, Block material) {
		int yRotation = 0;
		String dir = material.getProperty(Property.facing);

		if (dir.equals("north")) {
			yRotation = 90;
		} else if (dir.equals("south")) {
			yRotation = 270;
		} else if (dir.equals("east")) {
			yRotation = 0;
		} else if (dir.equals("west")) {
			yRotation = 180;
		}

		context.setPointToGrid(p);
		context.movePointInGridDimension(0.5, 0.5, 0.5);
		coralFan.getAngles()
				.setY(yRotation);
		coralFan.setSkin(skin);
		coralFan.setSolid(solid);
		coralFan.disableShadows(1);
		context.addPointEntity(coralFan);
		context.markAsConverted(p);

		String waterlogged = material.getProperty(Property.waterlogged);
		if(waterlogged.equals("true")) {
			addWaterlogged(context, p, material);
		}
	}
}

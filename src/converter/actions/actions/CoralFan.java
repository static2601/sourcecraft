package converter.actions.actions;

import converter.actions.Action;
import converter.mapper.Mapper;
import minecraft.Block;
import minecraft.Position;
import minecraft.Property;
import vmfWriter.entity.pointEntity.pointEntity.PropStatic;

public class CoralFan extends Action {

	// skin, make index for all skin names
	private int skin;
	// not solid = 0, use bounding box = 2, use vphysics = 6
	private int solid;

	private static String MODEL = "models/props/minecraft_original/coral_fan.mdl";
	private PropStatic coralFan = new PropStatic(CoralFan.MODEL);

	public CoralFan(int skin, int solid) {
		this.skin = skin;
		this.solid = solid;
		this.coralFan.setSkin(skin, solid);
	}

	@Override
	public void add(Mapper context, Position p, Block material) {
		context.setPointToGrid(p);
		context.movePointInGridDimension(0.5, 0, 0.5);
		int verticalRotation = 0;
		coralFan.getAngles()
				.setY(verticalRotation);
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

package converter.actions.actions;

import converter.actions.Action;
import converter.mapper.Mapper;
import minecraft.Block;
import minecraft.Position;
import minecraft.Property;
import vmfWriter.entity.pointEntity.pointEntity.PropStatic;

public class Grindstone extends Action {

	private static String MODEL = "models/props/minecraft_original/Grindstone.mdl";
	private PropStatic model = new PropStatic(Grindstone.MODEL);

	@Override
	public void add(Mapper context, Position p, Block material) {

		context.setPointToGrid(p);
		context.movePointInGridDimension(0.5, 0.5, 0.5);
		model.setSolid(2);

		int rotationY = 0;
		int rotationZ = 0;
		String direction = material.getProperty(Property.facing);
		String face = material.getProperty(Property.face);
		if(face.equals("floor")) rotationZ = 0;
		if(face.equals("wall")) rotationZ = -90;
		if(face.equals("ceiling")) rotationZ = 180;
		if(direction.equals("north")) rotationY = 0;
		if(direction.equals("west")) rotationY = 90;
		if(direction.equals("south")) rotationY = 180;
		if(direction.equals("east")) rotationY = 270;

		model.getAngles().setY(rotationY);
		model.getAngles().setZ(rotationZ);
		context.addPointEntity(model);
		context.markAsConverted(p);
	}
}

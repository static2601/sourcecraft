package converter.actions.actions;

import converter.actions.Action;
import converter.mapper.Mapper;
import minecraft.Block;
import minecraft.Position;
import minecraft.Property;
import vmfWriter.entity.pointEntity.pointEntity.PropStatic;

public class EndRod extends Action {

	private static final String MODEL = "models/props/minecraft_original/End_Rod.mdl";
	private final PropStatic endrod = new PropStatic(EndRod.MODEL);
	// skin, make index for all skin names

	public EndRod(){}
	@Override
	public void add(Mapper context, Position p, Block block) {

		String facing = block.getProperty(Property.facing);

		int xRotation = 0; // y in hammer
		int yRotation = 0; // z in hammer
		int zRotation = 0; // x in hammer
		switch (facing) {

			case "north":
				xRotation = 90; // y
				zRotation = -90; // x
				break;

			case "south":
				xRotation = 90; // y
				zRotation = 90; // x
				break;

			case "east":
				xRotation = 90; // y
				zRotation = 0; // x
				break;

			case "west":
				xRotation = 90; // y
				zRotation = 180; // x
				break;

			case "down":
				//yRotation = 270;
				xRotation = 180;
				break;
		}
		context.setPointToGrid(p);
		// put origin in center of block
		context.movePointInGridDimension(0.5, 0.5, 0.5);
		endrod.getAngles()
				.setY(yRotation);
		endrod.getAngles()
				.setZ(zRotation);
		endrod.getAngles()
				.setX(xRotation);
		endrod.setSolid(6);
		context.addPointEntity(endrod);

		context.markAsConverted(p);
	}
}

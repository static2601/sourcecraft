package converter.actions.actions;

import converter.actions.Action;
import converter.mapper.Mapper;
import minecraft.Block;
import minecraft.Position;
import minecraft.Property;
import vmfWriter.entity.pointEntity.pointEntity.PropStatic;

public class LightningRod extends Action {

	private static final String MODEL = "models/props/minecraft_original/Lightning_Rod.mdl";
	private final PropStatic lightningrod = new PropStatic(LightningRod.MODEL);
	// skin, make index for all skin names

	public LightningRod(){}
	@Override
	public void add(Mapper context, Position p, Block block) {

//		String waterlogged = block.getProperty(Property.waterlogged);
//		if(waterlogged.equals("true")) {
//			addWaterlogged(context, p, block);
//		}
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
		lightningrod.getAngles()
				.setY(yRotation);
		lightningrod.getAngles()
				.setZ(zRotation);
		lightningrod.getAngles()
				.setX(xRotation);
		lightningrod.setSolid(6);
		context.addPointEntity(lightningrod);

		context.markAsConverted(p);
	}
}

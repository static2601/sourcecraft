package converter.actions.actions;

import converter.actions.Action;
import converter.mapper.Mapper;
import minecraft.Block;
import minecraft.Position;
import minecraft.Property;
import vmfWriter.entity.pointEntity.pointEntity.PropStatic;

public class Bed extends Action {

	private static final String MODEL = "models/props/minecraft_original/Bed.mdl";
	private final PropStatic chain = new PropStatic(Bed.MODEL);
	// skin, make index for all skin names
	private int skin;

	public Bed(int skin){
		this.skin = skin;
	}
	@Override
	public void add(Mapper context, Position p, Block block) {

		String part = block.getProperty(Property.part);
		if (part.equals("foot")) {
			int yRotation = 0;
			int zRotation = 0;
			int xRotation = 0;

			String facing = block.getProperty(Property.facing);
			switch (facing) {
				case "north":
					yRotation = 180;
					break;

				case "south":
					yRotation = 0;
					break;

				case "east":
					yRotation = 90;
					break;

				case "west":
					yRotation = 270;
					break;

			}
			context.setPointToGrid(p);
			// put origin in center of block
			context.movePointInGridDimension(0.5, 0, 0.5);
			chain.getAngles()
					.setY(yRotation);
			chain.getAngles()
					.setZ(zRotation);
			chain.getAngles()
					.setX(xRotation);
			chain.setSkin(skin);
			chain.setSolid(6);
			context.addPointEntity(chain);
		}
		context.markAsConverted(p);
	}
}

package converter.actions.actions;

import converter.actions.Action;
import converter.mapper.Mapper;
import minecraft.Block;
import minecraft.Position;
import minecraft.Property;
import vmfWriter.entity.pointEntity.pointEntity.PropStatic;

public class Chain extends Action {
	private static final String MODEL = "models/props/minecraft_original/Chain.mdl";
	private final PropStatic chain = new PropStatic(Chain.MODEL);

	@Override
	public void add(Mapper context, Position p, Block block) {

		int yRotation = 0;
		int zRotation = 0;
		int xRotation = 0;

		String axis = block.getProperty(Property.axis);
		switch (axis) {
			case "z":
				zRotation = 90;
				break;

			case "x":
				xRotation = 90;
				break;

		}
		context.setPointToGrid(p);
		// put origin in center of block
		context.movePointInGridDimension(0.5, 0.5, 0.5);
		chain.getAngles()
				.setY(yRotation);
		chain.getAngles()
				.setZ(zRotation);
		chain.getAngles()
				.setX(xRotation);
		chain.setSkin(0);
		chain.setSolid(6);
		context.addPointEntity(chain);
		context.markAsConverted(p);
	}
}

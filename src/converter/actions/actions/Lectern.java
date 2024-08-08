package converter.actions.actions;

import converter.actions.Action;
import converter.mapper.Mapper;
import minecraft.Block;
import minecraft.Position;
import minecraft.Property;
import vmfWriter.entity.pointEntity.pointEntity.PropStatic;

public class Lectern extends Action {

	private static String MODEL = "models/props/minecraft_original/Lectern.mdl";
	private PropStatic lectern = new PropStatic(MODEL);

	@Override
	public void add(Mapper context, Position pos, Block block) {
		context.setPointToGrid(pos);
		context.movePointInGridDimension(0.5, 0, 0.5);
		int rotation = 0;
		String facing = block.getProperty(Property.facing);

		int skin = 0;
		if (facing.equals("south")) {
			rotation = 180;
		}
		if (facing.equals("east")) {
			rotation = -90;
		}
		if (facing.equals("north")) {
			rotation = 0;
		}
		if (facing.equals("west")) {
			rotation = -270;
		}
		lectern.getAngles().setY(rotation);
		lectern.setSkin(skin);
		lectern.setSolid(6);
		context.addPointEntity(lectern);
		context.markAsConverted(pos);
	}
}
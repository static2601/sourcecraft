package converter.actions.actions;

import basic.Loggger;
import converter.actions.Action;
import converter.mapper.Mapper;
import minecraft.Block;
import minecraft.Position;
import vmfWriter.entity.pointEntity.pointEntity.PropStatic;

public class LilyPad extends Action {

	private final static String MODEL = "models/props/minecraft_original/lilypad.mdl";
	private final static PropStatic lilyPad = new PropStatic(LilyPad.MODEL);

	@Override
	public void add(Mapper context, Position position, Block material) {
		context.setPointToGrid(position);
		context.movePointInGridDimension(0.5, 0, 0.5);
		int verticalAngle = (int) ((Math.random() * 4));
		int verticalAngle2 = verticalAngle * 90;
		LilyPad.lilyPad.getAngles()
				.setY(verticalAngle2);
		LilyPad.lilyPad.setSolid(6);
		LilyPad.lilyPad.disableShadows(1);
		context.addPointEntity(LilyPad.lilyPad);
		context.markAsConverted(position);
	}
}

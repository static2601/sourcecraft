package converter.actions.actions;

import converter.actions.Action;
import converter.mapper.Mapper;
import minecraft.Block;
import minecraft.Position;
import vmfWriter.entity.pointEntity.pointEntity.PropStatic;

/**
 *
 *
 */
public class LanternGround extends Action {

	private final static String MODEL = "models/props/minecraft_original/lantern.mdl";;
	private final static PropStatic LANTERN_GROUND = new PropStatic(LanternGround.MODEL);

	@Override
	public void add(Mapper context, Position p, Block material) {
		context.setPointToGrid(p);
		context.movePointInGridDimension(0.5, 0, 0.5);
		context.addPointEntity(LanternGround.LANTERN_GROUND);
		context.markAsConverted(p);
	}

}

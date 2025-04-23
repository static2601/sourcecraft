package converter.actions.actions;

import converter.actions.Action;
import converter.mapper.Mapper;
import minecraft.Block;
import minecraft.Position;
import vmfWriter.entity.pointEntity.pointEntity.PropStatic;

public class SlimeBlock extends Action {

	private final static String MODEL = "models/props/minecraft_original/SlimeBlock.mdl";
	private final static PropStatic slimeBlock = new PropStatic(MODEL);

	@Override
	public void add(Mapper context, Position position, Block material) {
		context.setPointToGrid(position);
		context.movePointInGridDimension(0.5, 0, 0.5);
		slimeBlock.setSolid(6);
		slimeBlock.disableShadows(1);
		context.addPointEntity(slimeBlock);
		context.markAsConverted(position);
	}
}

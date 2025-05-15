package converter.actions.actions;

import converter.actions.Action;
import converter.mapper.Mapper;
import minecraft.Block;
import minecraft.Position;
import vmfWriter.entity.pointEntity.pointEntity.PropStatic;

public class HoneyBlock extends Action {

	private final static String MODEL = "models/props/minecraft_original/HoneyBlock.mdl";
	private final static PropStatic honeyBlock = new PropStatic(MODEL);

	@Override
	public void add(Mapper context, Position position, Block material) {
		context.setPointToGrid(position);
		context.movePointInGridDimension(0.5, 0.5, 0.5);
		honeyBlock.setSolid(6);
		honeyBlock.disableShadows(1);
		context.addPointEntity(honeyBlock);
		context.markAsConverted(position);
	}
}

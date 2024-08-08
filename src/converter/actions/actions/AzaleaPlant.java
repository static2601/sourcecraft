package converter.actions.actions;

import converter.actions.Action;
import converter.mapper.Mapper;
import minecraft.Block;
import minecraft.Position;
import vmfWriter.entity.pointEntity.pointEntity.PropStatic;

public class AzaleaPlant extends Action {

	private int skin;
	private int solid;

	private static String MODEL = "models/props/minecraft_original/Azalea_Plant.mdl";
	private PropStatic model = new PropStatic(AzaleaPlant.MODEL);

	public AzaleaPlant(int skin, int solid) {
		this.skin = skin;
		this.solid = solid;
	}

	@Override
	public void add(Mapper context, Position p, Block material) {
		int skin = this.skin;

		context.setPointToGrid(p);
		context.movePointInGridDimension(0.5, 0, 0.5);
		model.setSkin(skin);
		model.setSolid(this.solid);
		context.addPointEntity(model);
		context.markAsConverted(p);
	}
}

package converter.actions.actions;

import basic.Loggger;
import converter.actions.Action;
import converter.mapper.Mapper;
import minecraft.Block;
import minecraft.Position;
import minecraft.Property;
import vmfWriter.entity.pointEntity.pointEntity.PropStatic;

/**
 *
 *
 */
public class Lantern extends Action {

	private final static String MODEL = "models/props/minecraft_original/lantern.mdl";
	private final static String MODEL2 = "models/props/minecraft_original/lanternHanging.mdl";
	private final static PropStatic lantern = new PropStatic(MODEL);

	@Override
	public void add(Mapper context, Position p, Block material) {
		context.setPointToGrid(p);
		context.movePointInGridDimension(0.5, 0, 0.5);

		int skin = 0;
		if(material.getName().endsWith("soul_lantern"))
			skin = 1;
		Loggger.log(material.get()+"");
		String hanging = material.getProperty(Property.hanging);
		if(hanging.equals("true"))
			lantern.setModel(MODEL2);
		else lantern.setModel(MODEL);

		lantern.setSkin(skin);
		context.addPointEntity(lantern);
		context.markAsConverted(p);
	}

}

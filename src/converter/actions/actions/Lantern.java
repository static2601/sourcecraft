package converter.actions.actions;

import converter.actions.Action;
import converter.mapper.Mapper;
import minecraft.Block;
import minecraft.Position;
import minecraft.Property;
import periphery.Option;
import vmfWriter.Color;
import vmfWriter.entity.pointEntity.pointEntity.Light;
import vmfWriter.entity.pointEntity.pointEntity.PropStatic;

public class Lantern extends Action {

    private final Light LIGHT1;
	private final Light LIGHT2;
	private final static String MODEL = "models/props/minecraft_original/lantern.mdl";
	private final static String MODEL2 = "models/props/minecraft_original/lanternHanging.mdl";
	private static final PropStatic lantern = new PropStatic(MODEL);

	public Lantern() {
		//TODO probably should be a error catch here or whatever
		// try to simplify code, maybe move to Action
		Option option = getOptions(this.getName().toLowerCase());
        Color LIGHT_COLOR1 = option.getNormalLight();
        Color LIGHT_COLOR2 = option.getSoulLight();
        int[] normalDistance = option.getNormalDistance().getDistances();
        int[] soulDistance = option.getSoulDistance().getDistances();
		LIGHT1 = new Light(LIGHT_COLOR1, normalDistance[0], normalDistance[1]);
		LIGHT2 = new Light(LIGHT_COLOR2, soulDistance[0], soulDistance[1]);
	}
	@Override
	public void add(Mapper context, Position p, Block material) {

		context.setPointToGrid(p);
		context.movePointInGridDimension(0.5, 0, 0.5);

		int skin = 0;
		if(material.getName().endsWith("soul_lantern"))
			skin = 1;

		String hanging = material.getProperty(Property.hanging);
		if(hanging.equals("true")) {
			lantern.setModel(MODEL2);
			lantern.setSkin(skin);
			lantern.disableShadows(1);
			context.addPointEntity(lantern);
			addLight(context, 0, 0.5, 0, skin);
		}
		else {
			lantern.setModel(MODEL);
			lantern.setSkin(skin);
			lantern.disableShadows(1);
			context.addPointEntity(lantern);
			addLight(context, 0, 0.5, 0, skin);
		}
		context.markAsConverted(p);
	}

	public void addLight(Mapper context, double x, double y, double z, int skin) {
		context.movePointInGridDimension(x, y, z);
		if (skin == 0)
			context.addPointEntity(this.LIGHT1);
		else if (skin == 1)
			context.addPointEntity(this.LIGHT2);
	}
}

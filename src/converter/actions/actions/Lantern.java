package converter.actions.actions;

import basic.Loggger;
import converter.actions.Action;
import converter.mapper.Mapper;
import minecraft.Block;
import minecraft.Position;
import minecraft.Property;
import vmfWriter.Color;
import vmfWriter.entity.pointEntity.pointEntity.Light;
import vmfWriter.entity.pointEntity.pointEntity.PropStatic;

/**
 *
 *
 */
public class Lantern extends Action {

	public final static int red = 255;
	public final static int blue = 191;
	public final static int green = 236;
	public final static int brigthness = 60;
	public final static int distance50 = 96;
	public final static int distance100 = 256;
	public final static Color LIGHT_COLOR1 = new Color(Lantern.red, Lantern.green, Lantern.blue, Lantern.brigthness);
	public final static Color LIGHT_COLOR2 = new Color(80, 240, 242, Lantern.brigthness);
	public final static Light LIGHT1 = new Light(Lantern.LIGHT_COLOR1, Lantern.distance50, Lantern.distance100);
	public final static Light LIGHT2 = new Light(Lantern.LIGHT_COLOR2, Lantern.distance50, Lantern.distance100);
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
			context.addPointEntity(Lantern.LIGHT1);
		else if (skin == 1)
			context.addPointEntity(Lantern.LIGHT2);

	}

}

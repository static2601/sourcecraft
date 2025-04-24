package converter.actions.actions;

import basic.Loggger;
import converter.actions.Action;
import converter.mapper.Mapper;
import minecraft.Block;
import minecraft.Position;
import minecraft.Property;
import periphery.LightDistance;
import periphery.Lights;
import periphery.Option;
import vmfWriter.Color;
import vmfWriter.entity.pointEntity.pointEntity.Light;
import vmfWriter.entity.pointEntity.pointEntity.PropStatic;

/**
 *
 *
 */
public class Lantern extends Action {

//	public final static int red = 255;
//	public final static int blue = 191;
//	public final static int green = 236;
//	public final static int brigthness = 60;
//	public final static int distance50 = 96;
//	public final static int distance100 = 256;
	public Color LIGHT_COLOR1;// = new Color(255, 236, 191, 60);
	public Color LIGHT_COLOR2;// = new Color(80, 240, 242, 60);
	public int[] normalDistance;// = new int[2];
	public int[] soulDistance;// = new int[2];
	public Light LIGHT1;
	public Light LIGHT2;
//	public static Light LIGHT1 = new Light(Lantern.LIGHT_COLOR1, normalDistance[0], normalDistance[1]);
//	public static Light LIGHT2 = new Light(Lantern.LIGHT_COLOR2, soulDistance[0], soulDistance[1]);
	//public final static Light LIGHT1 = new Light(Lantern.LIGHT_COLOR1, 96, 256);
	//public final static Light LIGHT2 = new Light(Lantern.LIGHT_COLOR2, 96, 256);
	private final static String MODEL = "models/props/minecraft_original/lantern.mdl";
	private final static String MODEL2 = "models/props/minecraft_original/lanternHanging.mdl";
	private static final PropStatic lantern = new PropStatic(MODEL);

	public Lantern() {
		Option option = getOptions(this.getName().toLowerCase());
		this.LIGHT_COLOR1 = option.getNormalLight();
		this.LIGHT_COLOR2 = option.getSoulLight();
		this.normalDistance = option.getNormalDistance().getDistances();
		this.soulDistance = option.getSoulDistance().getDistances();
		//Lights lights = option.getNormalLight();
		//this.LIGHT1 = new Light(lights.getColor(), lights.getDistance()[0], lights.getDistance()[1]);
		this.LIGHT1 = new Light(this.LIGHT_COLOR1, normalDistance[0], normalDistance[1]);
		this.LIGHT2 = new Light(this.LIGHT_COLOR2, soulDistance[0], soulDistance[1]);
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

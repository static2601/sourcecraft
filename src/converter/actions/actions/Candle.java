package converter.actions.actions;

import converter.actions.Action;
import converter.mapper.Mapper;
import minecraft.Block;
import minecraft.Position;
import minecraft.Property;
import periphery.Option;
import vmfWriter.Color;
import vmfWriter.entity.pointEntity.pointEntity.InfoParticleSystem;
import vmfWriter.entity.pointEntity.pointEntity.Light;
import vmfWriter.entity.pointEntity.pointEntity.PropStatic;

import java.util.Objects;
//TODO add lighting control to options and adjust for default lighting
public class Candle extends Action {

    // not solid = 0, use bounding box = 2, use vphysics = 6
	private int solid = 6;
	private final Option option;

	private static final String MODEL1 = "models/props/minecraft_original/candle1.mdl";
	private static final String MODEL2 = "models/props/minecraft_original/candle2.mdl";
	private static final String MODEL3 = "models/props/minecraft_original/candle3.mdl";
	private static final String MODEL4 = "models/props/minecraft_original/candle4.mdl";

	private PropStatic candle = new PropStatic(Candle.MODEL1);
	private final static String EFFECT_NAME = "candle_light1";
	private final static InfoParticleSystem PARTICLE_SYSTEM = new InfoParticleSystem(Candle.EFFECT_NAME, 270, 0, 0);

	// mcBlock prefix names in order as listed in QC file
	private static final String[] skins = {
			"candle", "black_candle", "blue_candle", "brown_candle",
			"cyan_candle", "gray_candle", "green_candle", "light_blue_candle",
			"light_gray_candle", "lime_candle", "magenta_candle", "orange_candle",
			"pink_candle", "purple_candle", "red_candle", "white_candle", "yellow_candle"
	};

	public Candle() {
		this.option = getOptions("Torches".toLowerCase());
	}

	@Override
	public void add(Mapper context, Position p, Block material) {

		// candle count
		String lit = material.getProperty(Property.valueOf("lit"));
		String candles = material.getProperty(Property.valueOf("candles"));

		context.setPointToGrid(p);
		context.movePointInGridDimension(0.5, 0, 0.5);

		candle.getAngles().setY(-90);
		candle.setSkin(getMatSkin(material, skins));
		candle.setSolid(this.solid);
		candle.disableShadows(1);
        Color LIGHT_COLOR1 = option.getNormalLight();

		if (Objects.equals(candles, "1")) {
			candle.setModel(MODEL1);
			if (lit.equals("true")) {
				addFlame(context, p, 0, 7, 0);
				LIGHT_COLOR1.setAlpha(5);
			}
		}
		if (Objects.equals(candles, "2")) {
			candle.setModel(MODEL2);
			if (lit.equals("true")) {
				addFlame(context, p, 1, 7, -2);
				addFlame(context, p, -2, 6, -1);
				LIGHT_COLOR1.setAlpha(10);
			}
		}
		if (Objects.equals(candles, "3")) {
			candle.setModel(MODEL3);
			if (lit.equals("true")) {
				addFlame(context, p, 1, 7, -2);
				addFlame(context, p, -2, 6, -1);
				addFlame(context, p, 0, 5, 1);
				LIGHT_COLOR1.setAlpha(15);
			}
		}
		if (Objects.equals(candles, "4")) {
			candle.setModel(MODEL4);
			if (lit.equals("true")) {
				addFlame(context, p, 1, 7, -2);
				addFlame(context, p, -2, 6, -2);
				addFlame(context, p, 2, 6, 1);
				addFlame(context, p, -1, 5, 1);
				LIGHT_COLOR1.setAlpha(20);
			}
		}
		context.setPointToGrid(p);
		context.movePointInGridDimension(0.5, 0, 0.5);
		context.addPointEntity(candle);

		// move point to center for light
		context.setPointToGrid(p);
		context.movePointInGridDimension(0.5, 0.75, 0.5);

		int[] normalDistance = option.getNormalDistance().getDistances();
		if (lit.equals("true")) {

			int style = 0;

			Light LIGHT = new Light(LIGHT_COLOR1, normalDistance[0], normalDistance[1], style);
			context.addPointEntity(LIGHT);
		}

		context.markAsConverted(p);
	}
	public void addFlame(Mapper context, Position p, double x, double y, double z) {

		context.setPointToGrid(p);
		context.movePointInGridDimension(0.5, 0, 0.5);
		context.movePointByPixel(x, y, z);
		context.addPointEntity(Candle.PARTICLE_SYSTEM);

	}
}

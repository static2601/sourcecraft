package converter.actions.actions;

import basic.Loggger;
import converter.actions.Action;
import converter.mapper.Mapper;
import minecraft.Block;
import minecraft.Blocks;
import minecraft.Position;
import minecraft.Property;
import vmfWriter.entity.pointEntity.pointEntity.PropStatic;

import java.util.Objects;

public class CrossModel extends Action {

	private int skin;
	private int solid;
	private boolean hasAge = false;

	private static String MODEL = "models/props/minecraft_original/CrossModel1.mdl";
	private static String MODEL2 = "models/props/minecraft_original/CrossModel2.mdl";
	private static String MODEL3 = "models/props/minecraft_original/CrossModel3.mdl";
	private PropStatic crossModel = new PropStatic(CrossModel.MODEL);

	public CrossModel(int skin, int solid) {
		this.skin = skin;
		this.solid = solid;
		this.crossModel.setSkin(skin, solid);
	}
	public CrossModel(int skin, int solid, boolean hasAge) {
		this.skin = skin;
		this.solid = solid;
		this.hasAge = hasAge;
		this.crossModel.setSkin(skin, solid);
	}

	@Override
	public void add(Mapper context, Position p, Block material) {
		int skin = this.skin;
		Position end = context.getCuboidFinder()
				.getBestXYZ(p, material);
		if(material.getName().endsWith("sea_grass")) {
			addWaterlogged(context, p, material);
		}
		if(material.getName().endsWith("kelp_plant")) {
			addWaterlogged(context, p, material);
		}
		if(material.getName().endsWith("kelp")) {
			addWaterlogged(context, p, material);
		}
		if(material.getName().endsWith("coral")) {
			String waterlogged = material.getProperty(Property.waterlogged);
			if(waterlogged.equals("true")) {
				addWaterlogged(context, p, material);
			}
		}

		//TODO make getProperties() return empty if non to avoid error
		//if(material.get().toString().contains("half=")) {
		if(material.toString().contains("half=")) {
			String half = material.getProperty(Property.half);
			if(Objects.equals(half, "upper")) {
				skin++;
			}
			// else add as normal using the first skin of the plant
		}

		// cave_vines/cave_vines_plant with/without berries,
		//TODO make function to return minecraft name instead of doing split by colon
		//Loggger.log(material.getName());
		if(material.getName().split(":")[1].startsWith("cave_vines")) {
			String berries = material.getProperty(Property.berries);
			if(berries.equals("true")) skin++;
		}

		// crop age
		if(hasAge) {
			String age = material.getProperty(Property.age);
			if (age != null) {
				// age is stage of growth, add to current skin number
				skin += Integer.parseInt(age);
			}
		}

		// if multiple skins over max of 31, change models
		if(skin > 30 && skin < 62) {
			crossModel = new PropStatic(CrossModel.MODEL2);
			skin = skin - 31;
		}
		if(skin > 61) {
			crossModel = new PropStatic(CrossModel.MODEL3);
			skin = skin - 62;
		}
		//Loggger.log("this.skin after reduce: "+skin);
		context.setPointToGrid(p);
		context.movePointInGridDimension(0.5, 0, 0.5);

		//turn off random rotation for now, later set per object type
		//int verticalRotation = (int) (Math.random() * 360);
		int verticalRotation = 0;
		crossModel.getAngles()
				.setY(verticalRotation);
		crossModel.setSkin(skin);
		crossModel.setSolid(this.solid);
		crossModel.disableShadows(1);
		context.addPointEntity(crossModel);
		context.markAsConverted(p);
	}
}

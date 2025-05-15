package converter.actions.actions;

import basic.Loggger;
import converter.actions.Action;
import converter.mapper.Mapper;
import minecraft.Block;
import minecraft.Position;
import minecraft.Property;
import vmfWriter.entity.pointEntity.pointEntity.PropStatic;

public class PinkPetals extends Action {

	private final static String MODEL1 = "models/props/minecraft_original/Pink_Petals_1.mdl";
	private final static String MODEL2 = "models/props/minecraft_original/Pink_Petals_2.mdl";
	private final static String MODEL3 = "models/props/minecraft_original/Pink_Petals_3.mdl";
	private final static String MODEL4 = "models/props/minecraft_original/Pink_Petals_4.mdl";

	@Override
	public void add(Mapper context, Position position, Block material) {
		int skin = 0;
		context.setPointToGrid(position);
		context.movePointInGridDimension(0.5, 0, 0.5);
		int rotation = 0;
		String facing = material.getProperty(Property.facing);
		String flowers = material.getProperty(Property.flower_amount);

		if(facing.equals("south")) rotation = 180;
		if(facing.equals("east")) rotation = -90;
		if(facing.equals("north")) rotation = 0;
		if(facing.equals("west")) rotation = -270;

		int flowersAmount = Integer.parseInt(flowers);

		PropStatic pinkPetals = new PropStatic(MODEL1);
		pinkPetals.getAngles().setY(rotation);
		pinkPetals.setSolid(0);
		pinkPetals.disableShadows(1);

		Loggger.log("pink pedals material name: "+material.getName());
		if(material.getName().endsWith("wildflowers")) {
			pinkPetals.setSkin(1);
		}
		else pinkPetals.setSkin(0);

		if(flowersAmount == 1) {
			pinkPetals.setModel(MODEL1);
			context.addPointEntity(pinkPetals);
		}
		else
		if(flowersAmount == 2) {
			pinkPetals.setModel(MODEL1);
			context.addPointEntity(pinkPetals);
			pinkPetals.setModel(MODEL2);
			context.addPointEntity(pinkPetals);
		}
		else
		if(flowersAmount == 3) {
			pinkPetals.setModel(MODEL1);
			context.addPointEntity(pinkPetals);
			pinkPetals.setModel(MODEL2);
			context.addPointEntity(pinkPetals);
			pinkPetals.setModel(MODEL3);
			context.addPointEntity(pinkPetals);
		}
		else
		if(flowersAmount == 4) {
			pinkPetals.setModel(MODEL1);
			context.addPointEntity(pinkPetals);
			pinkPetals.setModel(MODEL2);
			context.addPointEntity(pinkPetals);
			pinkPetals.setModel(MODEL3);
			context.addPointEntity(pinkPetals);
			pinkPetals.setModel(MODEL4);
			context.addPointEntity(pinkPetals);
		}
		context.markAsConverted(position);
	}
}

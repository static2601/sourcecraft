package converter.actions.actions;

import basic.Loggger;
import converter.actions.Action;
import converter.mapper.Mapper;
import minecraft.Block;
import minecraft.Position;
import minecraft.Property;
import vmfWriter.entity.pointEntity.pointEntity.PropStatic;

public class Door extends Action {

	private static String DOOR = "models/props/minecraft_original/door.mdl";
	private static String DOOR2 = "models/props/minecraft_original/door2.mdl";
	private static String TRAPDOOR = "models/props/minecraft_original/trapdoor.mdl";
	private PropStatic door = new PropStatic(Door.DOOR);

	// mcBlock prefix names in order as listed in QC file
	private static final String[] skins = {
			"oak", "spruce", "birch", "jungle", "acacia", "dark_oak",
			"mangrove", "cherry", "bamboo", "crimson", "warped", "iron",
			"copper", "exposed_copper", "oxidized_copper", "weathered_copper",
			"pale_oak"
	};

	@Override
	public void add(Mapper context, Position p, Block material) {

		// model xyz position in hammer appears as y, z, x
		// y, z, x is the same x, y, z
		// eg, hammer reads 90 180 0, here is 0, 90, 180
		int yRotation = 0;
		int zRotation = 0;
		int xRotation = 0;

		String half = material.getProperty(Property.half);
		if(half.contains("upper")) {
			// since door is a 2 high model,
			// only convert bottom, ignore top
			context.markAsConverted(p);
		} else {
			
			String hinge = material.getProperty(Property.hinge);
			String dir = material.getProperty(Property.facing);
			context.setPointToGrid(p);
			if (hinge.equals("left")) {
	
				if (dir.equals("north")) {
					context.movePointInGridDimension(0.1, 0, 0.9);// good
					yRotation = 90;
				} else if (dir.equals("south")) {
					context.movePointInGridDimension(0.9, 0, 0.1);
					yRotation = 270;
				} else if (dir.equals("east")) {
					context.movePointInGridDimension(0.1, 0, 0.1); //good
					yRotation = 0;
				} else if (dir.equals("west")) {
					context.movePointInGridDimension(0.9, 0, 0.9); //good
					yRotation = 180;
				}
			} else if (hinge.equals("right")) {
				//context.movePointInGridDimension(0.1, 0, 0.1);
				//Loggger.log("hinges right");
				if (dir.equals("north")) {
					context.movePointInGridDimension(0.9, 0, 0.9); //good
					yRotation = 270;
				} else if (dir.equals("south")) {
					context.movePointInGridDimension(0.1, 0, 0.1); //good
					yRotation = 90;
				} else if (dir.equals("east")) {
					context.movePointInGridDimension(0.1, 0, 0.9); //good
					yRotation = 180;
				} else if (dir.equals("west")) {
					context.movePointInGridDimension(0.9, 0, 0.1); //good
					yRotation = 0;
				}
			}
			
			// put origin in center of block
			//context.movePointInGridDimension(0, 0, 0.1);
			door.getAngles().setY(yRotation);
			door.getAngles().setZ(zRotation);
			door.getAngles().setX(xRotation);
			setMatSkinFilter("waxed_");
			// max skins for a model is 32, including second skin part and base skin
			// so 31 is max, 30(15)
			int skin = getMatSkin(material, skins);
			if (skin > 15) {
				skin -= 15;
				door.setModel(DOOR2);
				// since using a new model, start from 0,
				// but add one since base material is oak_door
				// qc skins must be oak_door top/bottom,
				// then extra skins pale_door top/bottom, etc
				door.setSkin(skin);
			}
			else door.setSkin(getMatSkin(material, skins));

			door.setSolid(6);
			context.addPointEntity(door);
			context.markAsConverted(p);
		}
	}
}

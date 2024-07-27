package converter.actions.actions;

import converter.actions.Action;
import converter.mapper.Mapper;
import minecraft.Block;
import minecraft.Position;
import minecraft.Property;
import vmfWriter.entity.pointEntity.pointEntity.PropStatic;

public class ButtonA extends Action {

	// skin, make index for all skin names
	private int skin;
	// not solid = 0, use bounding box = 2, use vphysics = 6
	private int solid;

	private static String MODEL = "models/props/minecraft_original/Button.mdl";
	private PropStatic button = new PropStatic(ButtonA.MODEL);

	/**
	 * Add button model instance with selected skin and physics
	 * 
	 * @skin skin index
	 * @solid not solid = 0, use bounding box = 2, use vphysics = 6
	 */
	public ButtonA(int skin, int solid) {
		this.skin = skin;
		this.solid = solid;
		this.button.setSkin(skin, solid); // set in PropStatic.java
	}

	@Override
	public void add(Mapper context, Position p, Block material) {

		// model xyz position in hammer appears as y, z, x
		// y, z, x is the same x, y, z
		// eg, hammer reads 90 180 0, here is 0, 90, 180
		int yRotation = 0;
		int zRotation = 0;
		int xRotation = 0;

		String face = material.getProperty(Property.face);

		String dir = material.getProperty(Property.facing);
        switch (face) {
            case "wall":
                switch (dir) {
                    case "north":
                        yRotation = 90;
                        break;
                    case "south":
                        yRotation = 270;
                        break;
                    case "east":
                        yRotation = 0;
                        break;
                    case "west":
                        yRotation = 180;
                        break;
                }
                break;
            case "ceiling":
                switch (dir) {
                    case "east":
                        xRotation = 90;
                        yRotation = 0;
                        zRotation = 0;
                        break;
                    case "west":
                        xRotation = 90;
                        yRotation = 0;
                        zRotation = 180;
                        break;
                    case "north":
                        xRotation = 90;
                        yRotation = 0;
                        zRotation = 270;
                        break;
                    case "south":
                        xRotation = 90;
                        yRotation = 0;
                        zRotation = 90;
                        break;
                }
                break;
            case "floor":
                switch (dir) {
                    case "east":
                        xRotation = 270;
                        yRotation = 0;
                        zRotation = 180;
                        break;
                    case "west":
                        xRotation = 270;
                        yRotation = 0;
                        zRotation = 0;
                        break;
                    case "north":
                        xRotation = 270;
                        yRotation = 270;
                        zRotation = 0;
                        break;
                    case "south":
                        xRotation = 270;
                        yRotation = 90;
                        zRotation = 0;
                        break;
                }
                break;
        }
		context.setPointToGrid(p);
		// put origin in center of block
		context.movePointInGridDimension(0.5, 0.5, 0.5);
		button.getAngles()
				.setY(yRotation);
		button.getAngles()
				.setZ(zRotation);
		button.getAngles()
				.setX(xRotation);
		button.setSkin(skin);
		button.setSolid(solid);
		context.addPointEntity(button);
		context.markAsConverted(p);
	}
}

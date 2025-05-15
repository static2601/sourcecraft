package converter.actions.actions;

import converter.actions.Action;
import converter.mapper.Mapper;
import minecraft.Block;
import minecraft.Position;
import minecraft.Property;
import vmfWriter.entity.pointEntity.pointEntity.PropStatic;

public class Lever extends Action {

	private static String MODEL = "models/props/minecraft_original/Lever.mdl";
    private static String HANDLE = "models/props/minecraft_original/LeverHandle.mdl";
	private PropStatic lever = new PropStatic(Lever.MODEL);
    private PropStatic handle = new PropStatic(Lever.HANDLE);

	public Lever() {}

	@Override
	public void add(Mapper context, Position p, Block material) {

		// model xyz position in hammer appears as y, z, x
		// y, z, x is the same x, y, z
		// eg, hammer reads 90 180 0, here is 0, 90, 180
		int yRotation = 0;
		int zRotation = 0;
		int xRotation = 0;

		String face = material.getProperty(Property.face);
        String powered = material.getProperty(Property.powered);
		String dir = material.getProperty(Property.facing);

        //if (powered.equals("true")) flip = 87;
        context.setPointToGrid(p);
        // put origin in center of block

        switch (face) {
            case "wall":
                switch (dir) {
                    case "north":
                        context.movePointInGridDimension(0.5, 0.5, 0.95);
                        yRotation = 90;
                        break;
                    case "south":
                        context.movePointInGridDimension(0.5, 0.5, 0.08);
                        yRotation = 270;
                        break;
                    case "east":
                        context.movePointInGridDimension(0.08, 0.5, 0.5);
                        yRotation = 0;
                        break;
                    case "west":
                        context.movePointInGridDimension(0.95, 0.5, 0.5);
                        yRotation = 180;
                        break;
                }
                break;
            case "ceiling":
                switch (dir) {
                    case "east":
                        context.movePointInGridDimension(0.5, 0.95, 0.5);
                        xRotation = 90;
                        yRotation = 0;
                        zRotation = 0;
                        break;
                    case "west":
                        context.movePointInGridDimension(0.5, 0.95, 0.5);
                        xRotation = 90;
                        yRotation = 0;
                        zRotation = 180;
                        break;
                    case "north":
                        context.movePointInGridDimension(0.5, 0.95, 0.5);
                        xRotation = 90;
                        yRotation = 0;
                        zRotation = 270;
                        break;
                    case "south":
                        context.movePointInGridDimension(0.5, 0.95, 0.5);
                        xRotation = 90;
                        yRotation = 0;
                        zRotation = 90;
                        break;
                }
                break;
            case "floor":
                switch (dir) {
                    case "east":
                        context.movePointInGridDimension(0.5, 0.08, 0.5);
                        xRotation = 270;
                        yRotation = 0;
                        zRotation = 180;
                        break;
                    case "west":
                        context.movePointInGridDimension(0.5, 0.08, 0.5);
                        xRotation = 270;
                        yRotation = 0;
                        zRotation = 0;
                        break;
                    case "north":
                        context.movePointInGridDimension(0.5, 0.08, 0.5);
                        xRotation = 270;
                        yRotation = 270;
                        zRotation = 0;
                        break;
                    case "south":
                        context.movePointInGridDimension(0.5, 0.08, 0.5);
                        xRotation = 270;
                        yRotation = 90;
                        zRotation = 0;
                        break;
                }
                break;
        }

        lever.getAngles().setY(yRotation);
		lever.getAngles().setZ(zRotation);
		lever.getAngles().setX(xRotation);
		lever.setSolid(6);
		context.addPointEntity(lever);
        //String powered = material.getProperty(Property.powered);
        //int flip = 0;
        //if (powered.equals("true")) flip = 87;

        int flip = 0;
        if (face.equals("wall")) {
            if (powered.equals("true")) flip = 87;
            handle.getAngles().setY(yRotation);
            handle.getAngles().setZ(zRotation);
            handle.getAngles().setX(xRotation+flip);
        }
        else if (face.equals("floor")) {
            if (powered.equals("false")) flip = 180;
            handle.getAngles().setY(yRotation+flip);
            handle.getAngles().setZ(zRotation);
            handle.getAngles().setX(xRotation);
        }
        else {
            if (powered.equals("true")) flip = 180;
            handle.getAngles().setY(yRotation+180);
            handle.getAngles().setZ(zRotation+flip);
            handle.getAngles().setX(xRotation);
        }
        handle.setSolid(6);
        context.addPointEntity(handle);

		context.markAsConverted(p);
	}
}

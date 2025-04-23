package converter.actions.actions;

import converter.actions.Action;
import converter.mapper.Mapper;
import minecraft.Block;
import minecraft.Position;
import minecraft.Property;
import vmfWriter.entity.pointEntity.pointEntity.PropStatic;

public class Rails extends Action {

	private static String MODEL = "models/props/minecraft_original/Rail.mdl";
	private static String MODEL2 = "models/props/minecraft_original/RailCorner.mdl";
	private static String MODEL3 = "models/props/minecraft_original/RailAscending.mdl";
	private PropStatic rails = new PropStatic(MODEL);

    // TODO add the rest of the track parts/skins
	@Override
	public void add(Mapper context, Position pos, Block block) {
		context.setPointToGrid(pos);
		context.movePointInGridDimension(0.5, 0, 0.5);
		int rotation = 0;
		String shape = block.getProperty(Property.shape);

		int skin = 0;
        switch (shape) {
            case "north_south":
                rotation = 0;
                rails.setModel(MODEL);
                break;
            case "east_west":
                rotation = -90;
                rails.setModel(MODEL);
                break;
            case "north_west":
                rotation = 90;
                rails.setModel(MODEL2);
                break;
            case "south_west":
                rotation = 180;
                rails.setModel(MODEL2);
                break;
            case "south_east":
                rotation = 270;
                rails.setModel(MODEL2);
                break;
            case "north_east":
                rotation = 0;
                rails.setModel(MODEL2);
                break;
            case "ascending_north":
                rotation = 180;
                rails.setModel(MODEL3);
                break;
            case "ascending_west":
                rotation = 270;
                rails.setModel(MODEL3);
                break;
            case "ascending_south":
                rotation = 0;
                rails.setModel(MODEL3);
                break;
            case "ascending_east":
                rotation = 90;
                rails.setModel(MODEL3);
                break;
        }
		rails.getAngles().setY(rotation);
		rails.setSolid(6);
		rails.disableShadows(1);
		context.addPointEntity(rails);
		context.markAsConverted(pos);
	}
}
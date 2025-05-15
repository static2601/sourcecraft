package converter.actions.actions;

import converter.actions.Action;
import converter.mapper.Mapper;
import minecraft.Block;
import minecraft.Position;
import minecraft.Property;
import vmfWriter.Solid;
import vmfWriter.entity.pointEntity.pointEntity.PropStatic;

import java.util.Objects;

public class Dripstone extends Action {

	private int skin;
	private int solid;

	private static final String MODEL = "models/props/minecraft_original/Dripstone.mdl";
	private final PropStatic dripstone = new PropStatic(Dripstone.MODEL);

	public Dripstone(int skin, int solid) {
		this.skin = skin;
		this.solid = solid;
		this.dripstone.setSkin(skin, solid);
	}

	@Override
	public void add(Mapper context, Position p, Block material) {
		int skin = this.skin;

//		String waterlogged = material.getProperty(Property.waterlogged);
//		if(waterlogged.equals("true")) {
//			addWaterlogged(context, p, material);
//		}

		String thickness = material.getProperty(Property.thickness);
		String vertical_direction = material.getProperty(Property.vertical_direction);

		if (vertical_direction.equals("up")) {

			if(Objects.equals(thickness, "base")) {
				skin += 4;
			}
			if(Objects.equals(thickness, "middle")) {
				skin += 5;
			}
			if(Objects.equals(thickness, "frustum")) {
				skin += 6;
			}
			if(Objects.equals(thickness, "tip")) {
				skin += 7;
			}
		}
		else {
			// base is first skin
			if(Objects.equals(thickness, "middle")) {
				skin += 1;
			}
			if(Objects.equals(thickness, "frustum")) {
				skin += 2;
			}
			if(Objects.equals(thickness, "tip")) {
				skin += 3;
			}
		}


		context.setPointToGrid(p);
		context.movePointInGridDimension(0.5, 0, 0.5);

		dripstone.setSkin(skin);
		dripstone.setSolid(this.solid);
		dripstone.disableShadows(1);
		context.addPointEntity(dripstone);
		context.markAsConverted(p);
	}
	// trigger hurt could be added for tip...
	private Solid createArea(Mapper context, Position p, Position end, Block material) {
		return context.createCuboid(p, end, material);
	}
}

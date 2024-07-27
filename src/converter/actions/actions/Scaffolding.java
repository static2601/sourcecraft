package converter.actions.actions;

import converter.actions.Action;
import converter.mapper.Mapper;
import minecraft.Block;
import minecraft.Position;
import minecraft.Property;
import vmfWriter.entity.pointEntity.pointEntity.PropStatic;

public class Scaffolding extends Action {

	// skin, make index for all skin names
	private int skin;
	// not solid = 0, use bounding box = 2, use vphysics = 6
	private int solid;

	private static String MODEL = "models/props/minecraft_original/Scaffolding.mdl";
	private static String MODEL2 = "models/props/minecraft_original/ScaffoldingSide.mdl";
	private PropStatic scaffolding = new PropStatic(Scaffolding.MODEL);

	/**
	 * Add button model instance with selected skin and physics
	 * 
	 * @skin skin index
	 * @solid not solid = 0, use bounding box = 2, use vphysics = 6
	 */
	public Scaffolding(int skin, int solid) {
		this.skin = skin;
		this.solid = solid;
		this.scaffolding.setSkin(skin, solid); // set in PropStatic.java
	}

	@Override
	public void add(Mapper context, Position p, Block material) {

		String waterlogged = material.getProperty(Property.waterlogged);
		String bottom = material.getProperty(Property.bottom);
		
		if(bottom.equals("false")) {
			
			//place normal scaffolding
			scaffolding.setModel(MODEL);
			
		} else {
			
			//place side scaffolding
			scaffolding.setModel(MODEL2);
			
		}
		context.setPointToGrid(p);
		context.movePointInGridDimension(0.5, 0, 0.5);
		scaffolding.setSkin(0);
		scaffolding.setSolid(solid);
		context.addPointEntity(scaffolding);
		context.markAsConverted(p);
		// after mark as converted seems to stop
		//doubling up water. test further.
		if(waterlogged.equals("true")) {
			addWaterlogged(context, p, material);
		}
		//context.markAsConverted(p);
	}
}

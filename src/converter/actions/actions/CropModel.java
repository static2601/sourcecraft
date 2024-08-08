package converter.actions.actions;

import converter.actions.Action;
import converter.mapper.Mapper;
import minecraft.Block;
import minecraft.Position;
import minecraft.Property;
import vmfWriter.entity.pointEntity.pointEntity.PropStatic;

public class CropModel extends Action {

	// skin, make index for all skin names
	private int skin;
	// not solid = 0, use bounding box = 2, use vphysics = 6
	private int solid;

	private static String MODEL = "models/props/minecraft_original/CropModel1.mdl";
	private PropStatic cropModel = new PropStatic(CropModel.MODEL);

	/**
	 * Add Plant model instance with selected skin
	 *
	 * @skin skin index
	 * @solid not solid = 0, use bounding box = 2, use vphysics = 6
	 */
	public CropModel(int skin, int solid) {
		this.skin = skin;
		this.solid = solid;
		this.cropModel.setSkin(skin, solid);
	}

	@Override
	public void add(Mapper context, Position p, Block material) {
		int skin = this.skin;

		// crop age
		String age = material.getProperty(Property.valueOf("age"));
		if (age != null) {
			skin += Integer.parseInt(age);
		}

		context.setPointToGrid(p);
		context.movePointInGridDimension(0.5, 0, 0.5);

		//turn off random rotation for now, later set per object type
		//int verticalRotation = (int) (Math.random() * 360);
		int verticalRotation = 0;
		cropModel.getAngles()
				.setY(verticalRotation);
		cropModel.setSkin(skin);
		cropModel.setSolid(this.solid);
		cropModel.disableShadows(1);
		context.addPointEntity(cropModel);
		context.markAsConverted(p);
	}
}

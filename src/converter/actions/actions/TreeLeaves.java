package converter.actions.actions;

import converter.actions.Action;
import converter.mapper.Mapper;
import minecraft.Block;
import minecraft.Position;
import vmfWriter.entity.pointEntity.pointEntity.PropStatic;

public class TreeLeaves extends Action {

	// skin, make index for all skin names
	private int skin;
	// not solid = 0, use bounding box = 2, use vphysics = 6
	private int solid;

	private static String MODEL = "models/props/minecraft_original/Tree_Leaves.mdl";
	// private static String MODEL =
	// "models/props/minecraft_original/Tree_Leaves.mdl";
	private PropStatic treeLeaves = new PropStatic(TreeLeaves.MODEL);

	/**
	 * Add Plant model instance with selected skin
	 * 
	 * @skin skin index
	 * @solid not solid = 0, use bounding box = 2, use vphysics = 6
	 */
	public TreeLeaves(int skin, int solid) {
		this.skin = skin;
		this.solid = solid;
		this.treeLeaves.setSkin(skin, solid); // set in PropStatic.java
	}

	@Override
	public void add(Mapper context, Position p, Block material) {
		context.setPointToGrid(p);
		context.movePointInGridDimension(0.5, 0, 0.5);
		// int verticalRotation = (int) (Math.random() * 360);
		int verticalRotation = 0;
		treeLeaves.getAngles()
				.setY(verticalRotation);
		treeLeaves.setSkin(skin);
		treeLeaves.setSolid(solid);
		context.addPointEntity(treeLeaves);
		context.markAsConverted(p);
	}
}

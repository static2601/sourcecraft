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
	public boolean useModel = false;
	public boolean combined = false;
	public boolean addAsSolid = false;

	private static final String MODEL = "models/props/minecraft_original/Tree_Leaves.mdl";
	private final PropStatic treeLeaves = new PropStatic(TreeLeaves.MODEL);

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
	public TreeLeaves(int skin) {
		this.skin = skin;
		this.solid = 6;
		this.treeLeaves.setSkin(skin, solid); // set in PropStatic.java
	}
	public TreeLeaves() {}

	@Override
	public void add(Mapper context, Position p, Block material) {
		if (useModel) {
			context.setPointToGrid(p);
			//context.movePointInGridDimension(0.5, 0, 0.5);

			int verticalRotation = 0;
			treeLeaves.getAngles()
					.setY(verticalRotation);
			treeLeaves.setSkin(skin);
			treeLeaves.setSolid(solid);
			context.addPointEntity(treeLeaves);
			context.markAsConverted(p);

		} else {
			if (!combined) {
				Position end = context.getCuboidFinder()
						.getBestXY(p, material);
				// making leaves slightly smaller on all sides prevents faces from culling
				// when the map is compiled, else the combined and look bad
				//int parts = 8;
				int parts = 256;
				Position offset = new Position(0, 0, 0);
				Position negativeOffset = new Position(1, 1, 1);
				//Position negativeOffset = new Position(0, 0, 0);
				//context.addDetail(context.createCuboid(position, end, parts, offset, negativeOffset, block));
				//context.addDetail(context.createCuboid(p, end, material));
				if (addAsSolid) context.addSolid(context.createCuboid(p, p,  parts, offset, negativeOffset, material));
				else context.addDetail(context.createCuboid(p, p, parts, offset, negativeOffset, material));
				//context.markAsConverted(p, end);
				context.markAsConverted(p);
			}
			else {
				// combined brushes together as one, looks bad but works well with
				// transparency set to opaque
				Position end = context.getCuboidFinder()
						.getBestXYZ(p, material);
				if (!addAsSolid) context.addDetail(context.createCuboid(p, end, material));
				else context.addSolid(context.createCuboid(p, end, material));
				context.markAsConverted(p, end);

			}
		}
	}
}

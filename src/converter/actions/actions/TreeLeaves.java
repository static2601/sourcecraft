package converter.actions.actions;

import converter.actions.Action;
import converter.mapper.Mapper;
import minecraft.Block;
import minecraft.Position;
import periphery.Option;
import vmfWriter.entity.pointEntity.pointEntity.PropStatic;

public class TreeLeaves extends Action {

	private int skin;
	private int solid;
	public boolean useModel;
	public boolean brushTrimmed;
	public boolean addAsSolid = false;
	public boolean brushOptimized;
	public boolean setForPropper = false;

	private static final String MODEL = "models/props/minecraft_original/Tree_Leaves.mdl";
	private final PropStatic treeLeaves = new PropStatic(TreeLeaves.MODEL);

	public TreeLeaves() {
		Option option = getOptions(this.getName().toLowerCase());
		useModel = option.get_useModel();
		brushOptimized = option.get_brushOptimized();
		brushTrimmed = option.get_brushTrimmed();
		setForPropper = option.get_setForPropper();

		// if brushOptimized=true, brushTrimmed is false can only true if brushOptimized=false
		// if trees need to be converted to a model with propper, would need set in GUI
		// brushTrimmed=false, useModel=false, brushOptimized=false
		if (setForPropper) leavesUnoptimized();
		// else run as normal, with other options

	}
	private void leavesUnoptimized() {
		addAsSolid = true;
		useModel = false;
		brushTrimmed = false;
		brushOptimized = false;
	}

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
			if (!brushOptimized && brushTrimmed) {

				// making leaves slightly smaller on all sides prevents faces from culling
				// when the map is compiled, else the combined and look bad
				//int parts = 8;
				int parts = 512;
				Position offset = new Position(0, 0, 0);
				Position negativeOffset = new Position(1, 1, 1);

				if (addAsSolid) context.addSolid(context.createCuboid(p, p,  parts, offset, negativeOffset, material));
				else context.addDetail(context.createCuboid(p, p, parts, offset, negativeOffset, material));

				context.markAsConverted(p);
			}
			else if (brushOptimized) {
				// combined brushes together as one, looks bad but works well with
				// transparency set to opaque
				Position end = context.getCuboidFinder()
						.getBestXYZ(p, material);
				if (!addAsSolid) context.addDetail(context.createCuboid(p, end, material));
				else context.addSolid(context.createCuboid(p, end, material));
				context.markAsConverted(p, end);
			}
			else if (!brushOptimized) {
				// combined brushes together as one, looks bad but works well with
				// transparency set to opaque
				if (!addAsSolid) context.addDetail(context.createCuboid(p, p, material));
				else context.addSolid(context.createCuboid(p, p, material));
				context.markAsConverted(p);
			}
		}
	}
}

package converter.actions.actions;

import converter.Skins;
import converter.actions.Action;
import converter.mapper.Mapper;
import minecraft.Block;
import minecraft.Position;
import minecraft.Property;
import vmfWriter.Cuboid;
import vmfWriter.Skin;
import vmfWriter.entity.pointEntity.pointEntity.PropStatic;

public class Bamboo extends Action {
	@Override
	public void add(Mapper context, Position p, Block block) {
		context.setPointToGrid(p);
		context.movePointInGridDimension(0.09375, 0, 0.09375);
		Position end = context.getCuboidFinder().getBestY(p, block);
		int parts = 16;
		Position offset = new Position(0, 0, 0);
		Position negativeOffset = new Position(13, 0, 13);

		Cuboid bamboo = context.createCuboid(p, end, parts, offset, negativeOffset, block);
		Skin newSkin = this.constructSkin(block);
		bamboo.setSkin(newSkin);

		//TODO
		// bamboo leaves will needs a plus shaped model, small and large
		// are there any other plants that would use this shape?
		// still need the younger bamboo 2 by ...

		newSkin.adjustUVs(Skin.sides.TOP, 13, 0); // top
		newSkin.adjustUVs(Skin.sides.BOTTOM, 0, 0); // bottom
		newSkin.adjustUVs(Skin.sides.LEFT, 0, 0);   // left
		newSkin.adjustUVs(Skin.sides.RIGHT, 3, 0);  // right
		newSkin.adjustUVs(Skin.sides.BACK, 3, 0);  // back
		newSkin.adjustUVs(Skin.sides.FRONT, 0, 0);   // front

		String leaves = block.getProperty(Property.leaves);
		if (leaves.equals("small")) {
			String MODEL3 = "models/props/minecraft_original/CrossModel3.mdl";
			PropStatic crossModel = new PropStatic(MODEL3);
			int verticalRotation = 45;
			crossModel.getAngles().setY(verticalRotation);
			crossModel.setSkin(20);
			crossModel.setSolid(0);
			crossModel.disableShadows(1);
			context.addPointEntity(crossModel);
		}
		else
		if (leaves.equals("large")) {
			String MODEL3 = "models/props/minecraft_original/CrossModel3.mdl";
			PropStatic crossModel = new PropStatic(MODEL3);
			int verticalRotation = 45;
			crossModel.getAngles().setY(verticalRotation);
			crossModel.setSkin(21);
			crossModel.setSolid(0);
			crossModel.disableShadows(1);
			context.addPointEntity(crossModel);
		}
		context.addDetail(bamboo);
		context.markAsConverted(p, end);
	}

	private Skin constructSkin(Block block) {
		Skin oldSkin = Skins.INSTANCE.getSkin(block);
		//String dir = this.getOrientation(block);
		String main = oldSkin.materialFront;
		String front = oldSkin.materialFront;
		String bottom = oldSkin.materialTop;
		String top = oldSkin.materialTop;
		return new Skin(main, top, front, bottom, oldSkin.scale);
	}
}

package converter.actions.actions;

import converter.Skins;
import converter.actions.Action;
import converter.mapper.Mapper;
import minecraft.Block;
import minecraft.Position;
import vmfWriter.Cuboid;
import vmfWriter.Skin;

public class Bamboo extends Action {
	@Override
	public void add(Mapper context, Position p, Block block) {
		Position end = context.getCuboidFinder()
				.getBestY(p, block);
		int parts = 16;
		Position offset = new Position(0, 0, 0);
		Position negativeOffset = new Position(13, 0, 13);

		Cuboid bamboo = context.createCuboid(p, end, parts, offset, negativeOffset, block);
		Skin newSkin = this.constructSkin(block);
		bamboo.setSkin(newSkin);

		newSkin.adjustUVs(Skin.sides.TOP, 13, 0); // top
		newSkin.adjustUVs(Skin.sides.BOTTOM, 0, 0); // bottom
		newSkin.adjustUVs(Skin.sides.LEFT, 0, 0);   // left
		newSkin.adjustUVs(Skin.sides.RIGHT, 3, 0);  // right
		newSkin.adjustUVs(Skin.sides.BACK, 3, 0);  // back
		newSkin.adjustUVs(Skin.sides.FRONT, 0, 0);   // front
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

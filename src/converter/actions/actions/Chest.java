package converter.actions.actions;

import converter.Orientation;
import converter.Skins;
import converter.actions.Action;
import converter.mapper.Mapper;
import minecraft.Block;
import minecraft.Position;
import minecraft.Property;
import vmfWriter.Cuboid;
import vmfWriter.Skin;
import vmfWriter.entity.pointEntity.pointEntity.PropStatic;

public class Chest extends Action {

	private static String MODEL = "models/props/minecraft_original/SingleChest.mdl";
	private PropStatic chest = new PropStatic(MODEL);
	private static boolean useModel = true;
	private final int skin;

	public Chest(int skin) {
		this.skin = skin;
	}
	@Override
	public void add(Mapper context, Position pos, Block block) {
		if(useModel) {
			context.setPointToGrid(pos);
			context.movePointInGridDimension(0.5, -0.025, 0.5);
			int rotation = 0;
			String facing = block.getProperty(Property.facing);

			//TODO - add double chests

			// get type left or right sections of a double chest
			//String type = block.getProperty(Property.type);

			if(facing.equals("south")) {
				rotation = 180;
			}
			if(facing.equals("east")) {
				rotation = -90;
			}
			if(facing.equals("north")) {
				rotation = 0;
			}
			if(facing.equals("west")) {
				rotation = -270;
			}

			chest.getAngles().setY(rotation);
			chest.setSkin(skin);
			chest.setSolid(6);
			context.addPointEntity(chest);
			context.markAsConverted(pos);

		} else {
			Position end = pos;
			int pixels = 16;
			Position startOffset = new Position(1, 0, 1);
			Position endOffset = new Position(1, 2, 1);
			Cuboid chest = context.createCuboid(pos, end, pixels, startOffset, endOffset, block);
			chest.setSkin(this.constructSkin(block));
			context.addDetail(chest);
			context.markAsConverted(pos, end);
		}
	}

	private Skin constructSkin(Block block) {
		Skin oldSkin = Skins.INSTANCE.getSkin(block);
		Orientation dir = this.getOrientation(block);
		String main = oldSkin.materialFront;
		String front = oldSkin.materialBack;
		String top = oldSkin.materialTop;
		return new Skin(main, top, front, dir, oldSkin.scale);
	}

	private Orientation getOrientation(Block block) {
		String dir = block.getProperty(Property.facing);
		if (dir.equals("north"))
			return Orientation.NORTH;
		else if (dir.equals("south"))
			return Orientation.SOUTH;
		else if (dir.equals("east"))
			return Orientation.EAST;
		else
			return Orientation.WEST;
	}
}
package converter.actions;

import java.util.LinkedList;
import java.util.List;

import basic.Loggger;
import converter.Orientation;
import converter.actions.actions.Liquid;
import converter.mapper.Mapper;
import minecraft.*;
import vmfWriter.entity.pointEntity.RotateablePointEntity;

public abstract class Action {

	public static String matSkinFilter = "";

	public Action() {
	}

	public void addWaterlogged(Mapper context, Position p, Block material){
		// add block of water to brush or model
		// doing this for a regular brush will add that brush instead of water!
		Position end = context.getCuboidFinder()
				.getBestXYZ(p, material);
		//if above is air_block, will have to not be down and should be flush
		//unless get best will correct it
		int parts = 16;
		Position offset = new Position(0, 0, 0);
		Position negativeOffset = new Position(0, 2, 0);

		context.addDetail(context.createCuboid(p, end, parts, offset,
				negativeOffset, Blocks.get(t -> t.setName(Material.water))));
		context.markAsConverted(p);
		//context.addSolid(context.createCuboid(p, end, parts, offset, negativeOffset, material));
		//Loggger.log("added water to block to "+ material.getName());
		//waterlog water doubling up under with top space for both. was other way better?
	}

	public void setMatSkinFilter(String filter){
		matSkinFilter = filter;
	}

	public Iterable<Action> getInstances() {
		List<Action> list = new LinkedList<>();
		try {
			Action a = (Action) this.getClass()
					.getConstructors()[0].newInstance();
			list.add(a);
		} catch (Exception ex) {
			Loggger.warn("Addable " + this.getClass()
					.getSimpleName() + " does not have a suitable constructor (InvocationTargetException)");
		}
		return list;
	}

	public String getName() {
		return this.getClass()
				.getSimpleName();
	}

	/**
	 * Returns whether the added blocks are air.
	 */
	public boolean isAirBlock() {
		return true;
	}

	public boolean hasWall(Orientation orientation) {
		return false;
	}

	/**
	 * For a given position with given material, this method adds solids and
	 * entities to the resulting Source map.
	 *
	 */
	public void add(Mapper context, Position position, Block material) {

	}

	protected void addDebugMarker(Mapper context, Position position, Block material) {
		context.setPointToGrid(position);
		context.movePointInGridDimension(0.5, 0, 0.5);
		int verticalAngle = (int) (Math.random() * 360);
		context.addPointEntity(new RotateablePointEntity().setName(material.getName() + " at " + position.toString())
				.setRotation(verticalAngle));
		context.markAsConverted(position);
	}

	/**
	 * get int y-rotation from facing property
	 * @return y rotation (int)
	 */
	public int getFacingDir(Block material) {
		String facing = material.getProperty(Property.facing);
		switch (facing) {
			case "north":  return 270;
			case "south":  return 90;
			case "east":  return 180;
			default: return 0;
		}
	}

	/**
	 * get int y-rotation from rotation property
	 * @return y rotation (int)
	 */
	public int getRotation(Block material) {
		String rotation = material.getProperty(Property.rotation);
		int offset = 270;
		int rotate = ((int) (Double.parseDouble(rotation) * -22.5 )) + offset;
		return ((rotate % 360) + 360) % 360;
	}

	/**
	 * get skin index in model to display
	 * skins - array of skins(String)
	 * @return skin index(int)
	 */
	public int getMatSkin(Block m, String[] skins) {
		String name = m.getName().split(":")[1];
		String filtered = "";
		for (int i = 0; i < skins.length; i++) {
			if(!matSkinFilter.isEmpty() && name.contains(matSkinFilter)) {
				filtered = name.replace(matSkinFilter, "");
			}
			else filtered = name;
			if (filtered.startsWith(skins[i])) {
				return i;
			}
		}
		return 0;
	}
}

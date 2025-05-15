package converter.actions.actions;

import converter.actions.Action;
import converter.mapper.Mapper;
import minecraft.Block;
import minecraft.Position;
import minecraft.Property;
import vmfWriter.entity.pointEntity.pointEntity.PropStatic;

public class FenceGate extends Action {

	private static String GATECLOSED = "models/props/minecraft_original/GateClosed.mdl";
	private static String GATEOPENED = "models/props/minecraft_original/GateOpened.mdl";
	private PropStatic GateClosed = new PropStatic(FenceGate.GATECLOSED);
	private PropStatic GateOpened = new PropStatic(FenceGate.GATEOPENED);

	// mcBlock prefix names in order as listed in QC file
	private static final String[] skins = {
			"birch", "jungle", "dark_oak",
			"oak", "spruce", "acacia", "mangrove",
			"cherry", "bamboo", "crimson", "warped",
			"nether_brick", "pale_oak"
	};

	@Override
	public void add(Mapper context, Position p, Block material) {

		String facing = material.getProperty(Property.facing);
		String open = material.getProperty(Property.open);
		context.setPointToGrid(p);
		context.movePointInGridDimension(0.5, 0, 0.5);

		if(facing.equals("north")) {
			if(open.equals("true")) {
				GateOpened.getAngles().setY(180);
				GateOpened.setSkin(getMatSkin(material, skins));
				GateOpened.setSolid(0);
				context.addPointEntity(GateOpened);
			}
			else {
				GateClosed.getAngles().setY(180);
				GateClosed.setSkin(getMatSkin(material, skins));
				GateClosed.setSolid(6);
				context.addPointEntity(GateClosed);
			}
		}
		if(facing.equals("south")) {
			if(open.equals("true")) {
				GateOpened.getAngles().setY(0);
				GateOpened.setSkin(getMatSkin(material, skins));
				GateOpened.setSolid(0);
				context.addPointEntity(GateOpened);
			}
			else {
				GateClosed.getAngles().setY(0);
				GateClosed.setSkin(getMatSkin(material, skins));
				GateClosed.setSolid(6);
				context.addPointEntity(GateClosed);
			}
		}
		if(facing.equals("east")) {
			if(open.equals("true")) {
				GateOpened.getAngles().setY(90);
				GateOpened.setSkin(getMatSkin(material, skins));
				GateOpened.setSolid(0);
				context.addPointEntity(GateOpened);
			}
			else {
				GateClosed.getAngles().setY(90);
				GateClosed.setSkin(getMatSkin(material, skins));
				GateClosed.setSolid(6);
				context.addPointEntity(GateClosed);
			}
		}
		if(facing.equals("west")) {
			if(open.equals("true")) {
				GateOpened.getAngles().setY(-90);
				GateOpened.setSkin(getMatSkin(material, skins));
				GateOpened.setSolid(0);
				context.addPointEntity(GateOpened);
			}
			else {
				GateClosed.getAngles().setY(-90);
				GateClosed.setSkin(getMatSkin(material, skins));
				GateClosed.setSolid(6);
				context.addPointEntity(GateClosed);
			}
		}
	}
}

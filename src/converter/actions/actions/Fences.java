package converter.actions.actions;

import basic.Loggger;
import converter.actions.Action;
import converter.mapper.Mapper;
import minecraft.Block;
import minecraft.Position;
import minecraft.Property;
import vmfWriter.entity.pointEntity.pointEntity.PropStatic;

public class Fences extends Action {

	private static String POST = "models/props/minecraft_original/FencePost.mdl";
	private static String BEAMSNS = "models/props/minecraft_original/FenceBeamsNS.mdl";
	private static String BEAMSEW = "models/props/minecraft_original/FenceBeamsEW.mdl";
	private PropStatic fences = new PropStatic(Fences.POST);
	private PropStatic beamsns = new PropStatic(Fences.BEAMSNS);
	private PropStatic beamsew = new PropStatic(Fences.BEAMSEW);

	// mcBlock prefix names in order as listed in QC file
	private static final String[] skins = {
			"birch", "jungle", "dark_oak",
			"oak", "spruce", "acacia", "mangrove",
			"cherry", "bamboo", "crimson", "warped",
			"nether_brick"
	};

	@Override
	public void add(Mapper context, Position p, Block material) {

		String east = material.getProperty(Property.east);
		String north = material.getProperty(Property.north);
		String south = material.getProperty(Property.south);

		String waterlogged = material.getProperty(Property.waterlogged);


		String west = material.getProperty(Property.west);
		
		if(east.equals("true")) {
			context.setPointToGrid(p);
			context.movePointInGridDimension(0.5, 0, 0.5);
			beamsew.getAngles().setY(180);
			beamsew.setSkin(getMatSkin(material, skins));
			beamsew.setSolid(6);
			context.addPointEntity(beamsew);
		}
		if(north.equals("true")) {
			context.setPointToGrid(p);
			context.movePointInGridDimension(0.5, 0, 0.5);
			beamsns.getAngles().setY(180);
			beamsns.setSkin(getMatSkin(material, skins));
			beamsns.setSolid(6);
			context.addPointEntity(beamsns);
		}
		if(south.equals("true")) {
			context.setPointToGrid(p);
			context.movePointInGridDimension(0.5, 0, 0.5);
			beamsns.getAngles().setY(0);
			beamsns.setSkin(getMatSkin(material, skins));
			beamsns.setSolid(6);
			context.addPointEntity(beamsns);
		}
		if(waterlogged.equals("true")) {}

		if(west.equals("true")) {
			context.setPointToGrid(p);
			context.movePointInGridDimension(0.5, 0, 0.5);
			beamsew.getAngles().setY(0);
			beamsew.setSkin(getMatSkin(material, skins));
			beamsew.setSolid(6);
			context.addPointEntity(beamsew);
		}

		//set post
		context.setPointToGrid(p);
		context.movePointInGridDimension(0.5, 0, 0.5);
		fences.getAngles().setY(0);
		fences.setSkin(getMatSkin(material, skins));
		fences.setSolid(6);
		context.addPointEntity(fences);
		context.markAsConverted(p);
		if(waterlogged.equals("true")) {
			addWaterlogged(context, p, material);
		}
	}
}

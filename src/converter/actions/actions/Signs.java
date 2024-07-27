package converter.actions.actions;

import basic.Loggger;
import converter.actions.Action;
import converter.mapper.Mapper;
import minecraft.Block;
import minecraft.Position;
import minecraft.Property;
import vmfWriter.entity.pointEntity.pointEntity.PropStatic;

import java.util.Objects;

public class Signs extends Action {

	private static final String SIGN = "models/props/minecraft_original/sign.mdl";
	private static final String STANDING = "models/props/minecraft_original/signstanding.mdl";
	private static final String HANGING = "models/props/minecraft_original/signhanging.mdl";
	private static final String HANGINGWALL = "models/props/minecraft_original/signhangingwall.mdl";
	private static final String ATTACHED = "models/props/minecraft_original/signhangingattached.mdl";
	private PropStatic sign = new PropStatic(Signs.SIGN);

	// mcBlock prefix names in order as listed in QC file
	private static final String[] skins = {
			"birch", "jungle", "dark_oak", "oak", "spruce", "acacia",
			"mangrove", "cherry", "bamboo", "crimson", "warped"
	};

	@Override
	public void add(Mapper context, Position p, Block material) {

		Loggger.log("get: " + material.get());
		String signType = material.getName();
		String waterlogged = material.getProperty(Property.waterlogged);

		if (signType.endsWith("wall_hanging_sign")) {
			sign = new PropStatic(Signs.HANGINGWALL);
			sign.getAngles().setY(getFacingDir(material));
		}
		else
		if (signType.endsWith("_hanging_sign")) {
			if (Objects.equals(material.getProperty(Property.attached), "true"))
				sign = new PropStatic(Signs.ATTACHED);
			else sign = new PropStatic(Signs.HANGING);
			sign.getAngles().setY(getRotation(material));
		}
		else
		if (signType.endsWith("_wall_sign")) {
			sign = new PropStatic(Signs.SIGN);
			sign.getAngles().setY(getFacingDir(material));
		}
		else
		if (signType.endsWith("_sign")) {
			sign = new PropStatic(Signs.STANDING);
			sign.getAngles().setY(getRotation(material));
		}

		context.setPointToGrid(p);
		context.movePointInGridDimension(0.5, 0, 0.5);
		sign.setSkin(getMatSkin(material, skins));
		sign.setSolid(0);
		context.addPointEntity(sign);
		context.markAsConverted(p);
	}
}

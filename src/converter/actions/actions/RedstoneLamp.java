package converter.actions.actions;

import converter.actions.Action;
import converter.mapper.Mapper;
import minecraft.*;
import periphery.Minecraft;
import java.util.Objects;

public class RedstoneLamp extends Action {

	@Override
	public void add(Mapper context, Position p, Block material) {
		Position end = context.getCuboidFinder()
				.getBestXYZ(p, material);

		String lit = material.getProperty(Property.lit);

		// if property lit == "true" set material to lamp_on instead of normal(off)
		if (Objects.equals(lit, "true")) material = Blocks.get(
				t -> t.setName(Minecraft.toMaterial("redstone_lamp_on")));

		//context.addDetail(context.createCuboid(p, end, material));
		context.addSolid(context.createCuboid(p, end, material));
		context.markAsConverted(p, end);

	}
}

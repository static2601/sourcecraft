package converter.actions.actions;

import converter.actions.Action;
import converter.mapper.Mapper;
import minecraft.Block;
import minecraft.Position;
import minecraft.Property;
import periphery.Option;
import vmfWriter.Color;
import vmfWriter.entity.pointEntity.PointEntity;
import vmfWriter.entity.pointEntity.pointEntity.EnvFire;
import vmfWriter.entity.pointEntity.pointEntity.InfoParticleSystem;
import vmfWriter.entity.pointEntity.pointEntity.Light;
import vmfWriter.entity.pointEntity.pointEntity.PropStatic;

import java.util.Vector;

public class Torches extends Action {

    private final Light TORCH;
	private final Light SOUL_TORCH;
	private final Light REDSTONE_TORCH;
	private final static String EFFECT_NAME = "flaming_arrow";
	private final static InfoParticleSystem PARTICLE_SYSTEM = new InfoParticleSystem(Torches.EFFECT_NAME, 270, 0, 0);
	protected static final PointEntity FLAME = new EnvFire().setFireSize(3);

	private static final String MODEL = "models/props/minecraft_original/Torch.mdl";
	private final PropStatic torch = new PropStatic(Torches.MODEL);

	public Torches() {
		this.torch.setSolid(0);
		Option option = getOptions(this.getName().toLowerCase());
        Color LIGHT_COLOR1 = option.getNormalLight();
        Color LIGHT_COLOR2 = option.getSoulLight();
        Color LIGHT_COLOR3 = option.getRedstoneLight();
        int[] normalDistance = option.getNormalDistance().getDistances();
        int[] soulDistance = option.getSoulDistance().getDistances();
		TORCH = new Light(LIGHT_COLOR1, normalDistance[0], normalDistance[1]);
		SOUL_TORCH = new Light(LIGHT_COLOR2, soulDistance[0], soulDistance[1]);
		REDSTONE_TORCH = new Light(LIGHT_COLOR3, soulDistance[0], soulDistance[1]);
	}

	@Override
	public void add(Mapper context, Position p, Block material) {
		
		if(material.getName().equals("minecraft:torch")) {

			// is normal torch
			torch.setSkin(0);
			context.setPointToGrid(p);
			context.movePointInGridDimension(0.5, 0, 0.5);
			context.addPointEntity(torch);
			
			addFlame(context, 0, 0.6, 0);
		}
		else if(material.getName().equals("minecraft:wall_torch")) {
			
			// is wall torch
			torch.setSkin(0);
			context.setPointToGrid(p);

			// get direction
			torch.getAngles().setZ(25);
			String facing = material.getProperty(Property.facing);
			if(facing.equals("north")) {
				torch.getAngles().setY(180);
				context.movePointInGridDimension(0.5, 0.25, 1);
				context.addPointEntity(torch);
				addFlame(context, 0, 0.6, -0.28);
			}
			if(facing.equals("south")) {
				torch.getAngles().setY(0);
				context.movePointInGridDimension(0.5, 0.25, 0);
				context.addPointEntity(torch);
				addFlame(context, 0, 0.6, 0.28);
			}
			if(facing.equals("west")) {
				torch.getAngles().setY(270);
				context.movePointInGridDimension(1, 0.25, 0.5);
				context.addPointEntity(torch);
				addFlame(context, -0.28, 0.6, 0);
			}
			if(facing.equals("east")) {
				torch.getAngles().setY(90);
				context.movePointInGridDimension(0, 0.25, 0.5);
				context.addPointEntity(torch);
				addFlame(context, 0.28, 0.6, 0);
			}
		}
		else if(material.getName().equals("minecraft:redstone_torch")) {

			// is restone torch
			context.setPointToGrid(p);
			context.movePointInGridDimension(0.5, 0, 0.5);

			String lit = material.getProperty(Property.lit);
			if(lit.equals("true")) {
				torch.setSkin(1);
				context.addPointEntity(torch);
				addRedstone(context, 0, 0.6, 0, true);
			}
			else {
				torch.setSkin(2);
				context.addPointEntity(torch);
				addRedstone(context, 0, 0.6, 0, false);
			}
		}
		else if(material.getName().equals("minecraft:redstone_wall_torch")) {

			// is redstone wall torch
			context.setPointToGrid(p);

			String lit = material.getProperty(Property.lit);
			if(lit.equals("true")) torch.setSkin(1);
			else torch.setSkin(2);

			// get direction
			torch.getAngles().setZ(25);
			String facing = material.getProperty(Property.facing);
			if(facing.equals("north")) {
				torch.getAngles().setY(180);
				context.movePointInGridDimension(0.5, 0.25, 1);
				context.addPointEntity(torch);
                addRedstone(context, 0, 0.6, -0.28, lit.equals("true"));
			}
			if(facing.equals("south")) {
				torch.getAngles().setY(0);
				context.movePointInGridDimension(0.5, 0.25, 0);
				context.addPointEntity(torch);
                addRedstone(context, 0, 0.6, 0.28, lit.equals("true"));
			}
			if(facing.equals("west")) {
				torch.getAngles().setY(270);
				context.movePointInGridDimension(1, 0.25, 0.5);
				context.addPointEntity(torch);
                addRedstone(context, -0.28, 0.6, 0, lit.equals("true"));
			}
			if(facing.equals("east")) {
				torch.getAngles().setY(90);
				context.movePointInGridDimension(0, 0.25, 0.5);
				context.addPointEntity(torch);
                addRedstone(context, 0.28, 0.6, 0, lit.equals("true"));
			}
		}
		else if(material.getName().equals("minecraft:soul_torch")) {

			// is soul torch
			torch.setSkin(3);
			context.setPointToGrid(p);
			context.movePointInGridDimension(0.5, 0, 0.5);
			context.addPointEntity(torch);
			addSoulFlame(context, 0, 0.6, 0);
		}
		else if(material.getName().equals("minecraft:soul_wall_torch")) {

			// is soul wall torch
			context.setPointToGrid(p);
			torch.setSkin(3);

			// get direction
			torch.getAngles().setZ(25);
			String facing = material.getProperty(Property.facing);
			if(facing.equals("north")) {
				torch.getAngles().setY(180);
				context.movePointInGridDimension(0.5, 0.25, 1);
				context.addPointEntity(torch);
				addSoulFlame(context, 0, 0.6, -0.28);
			}
			if(facing.equals("south")) {
				torch.getAngles().setY(0);
				context.movePointInGridDimension(0.5, 0.25, 0);
				context.addPointEntity(torch);
				addSoulFlame(context, 0, 0.6, 0.28);
			}
			if(facing.equals("west")) {
				torch.getAngles().setY(270);
				context.movePointInGridDimension(1, 0.25, 0.5);
				context.addPointEntity(torch);
				addSoulFlame(context, -0.28, 0.6, 0);;
			}
			if(facing.equals("east")) {
				torch.getAngles().setY(90);
				context.movePointInGridDimension(0, 0.25, 0.5);
				context.addPointEntity(torch);
				addSoulFlame(context, 0.28, 0.6, 0);
			}
		}
		context.markAsConverted(p);
	}

	public void addFlame(Mapper context, double x, double y, double z) {
		context.movePointInGridDimension(x, y, z);
		context.addPointEntity(Torches.PARTICLE_SYSTEM);
		//context.addPointEntity(Torches.FLAME);
		context.addPointEntity(TORCH);
	}
	public void addSoulFlame(Mapper context, double x, double y, double z) {
		context.movePointInGridDimension(x, y, z);
		context.addPointEntity(Torches.PARTICLE_SYSTEM);
		context.addPointEntity(Torches.FLAME);
		context.addPointEntity(SOUL_TORCH);
	}
	public void addRedstone(Mapper context, double x, double y, double z, boolean addLight) {
		context.movePointInGridDimension(x, y, z);
		//context.addPointEntity(Torches.PARTICLE_SYSTEM);
		//context.addPointEntity(Torches.FLAME);
		if (addLight) context.addPointEntity(REDSTONE_TORCH);
	}
}


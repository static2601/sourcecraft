package converter.actions.actions;

import converter.actions.Action;
import converter.mapper.Mapper;
import minecraft.Block;
import minecraft.Position;
import minecraft.Property;
import vmfWriter.Color;
import vmfWriter.entity.pointEntity.PointEntity;
import vmfWriter.entity.pointEntity.pointEntity.EnvFire;
import vmfWriter.entity.pointEntity.pointEntity.InfoParticleSystem;
import vmfWriter.entity.pointEntity.pointEntity.Light;
import vmfWriter.entity.pointEntity.pointEntity.PropStatic;

import java.util.Vector;

public class Torches extends Action {

//	public final static int red = 255;
//	public final static int blue = 191;
//	public final static int green = 236;
//	public final static int brigthness = 60;
//	public final static int distance50 = 96;
//	public final static int distance100 = 256;
	public final static Color LIGHT_COLOR = new Color(255, 236, 191, 60);
	public final static Color LIGHT_COLOR2 = new Color(40, 100, 242, 60);
	public final static Color LIGHT_COLOR3 = new Color(200, 20, 20, 60);
	public final static Light TORCH = new Light(Torches.LIGHT_COLOR, 96, 256);
	public final static Light SOUL_TORCH = new Light(Torches.LIGHT_COLOR2, 96, 256);
	public final static Light REDSTONE_TORCH = new Light(Torches.LIGHT_COLOR3, 96, 256);
	private final static String EFFECT_NAME = "flaming_arrow";
	public final static InfoParticleSystem PARTICLE_SYSTEM = new InfoParticleSystem(Torches.EFFECT_NAME, 270, 0, 0);
	protected static final PointEntity FLAME = new EnvFire().setFireSize(3);

	private static String MODEL = "models/props/minecraft_original/Torch.mdl";
	private PropStatic torch = new PropStatic(Torches.MODEL);

	public Torches() {
		this.torch.setSolid(0);
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
		context.addPointEntity(Torches.TORCH);
	}
	public void addSoulFlame(Mapper context, double x, double y, double z) {
		context.movePointInGridDimension(x, y, z);
		context.addPointEntity(Torches.PARTICLE_SYSTEM);
		context.addPointEntity(Torches.FLAME);
		context.addPointEntity(Torches.SOUL_TORCH);
	}
	public void addRedstone(Mapper context, double x, double y, double z, boolean addLight) {
		context.movePointInGridDimension(x, y, z);
		//context.addPointEntity(Torches.PARTICLE_SYSTEM);
		//context.addPointEntity(Torches.FLAME);
		if (addLight) context.addPointEntity(Torches.REDSTONE_TORCH);
	}
}


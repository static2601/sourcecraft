package converter.actions.actions;

import basic.Loggger;
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

public class Torches extends Action {

	public final static int red = 255;
	public final static int blue = 191;
	public final static int green = 236;
	public final static int brigthness = 40;
	public final static int distance50 = 96;
	public final static int distance100 = 256;
	public final static Color LIGHT_COLOR = new Color(Torches.red, Torches.green, Torches.blue, Torches.brigthness);
	public final static Light LIGHT = new Light(Torches.LIGHT_COLOR, Torches.distance50, Torches.distance100);
	private final static String EFFECT_NAME = "flaming_arrow";
	public final static InfoParticleSystem PARTICLE_SYSTEM = new InfoParticleSystem(Torches.EFFECT_NAME, 270, 0, 0);
	protected static final PointEntity FLAME = new EnvFire().setFireSize(3);
	
	// skin, make index for all skin names
	private int skin;
	// not solid = 0, use bounding box = 2, use vphysics = 6
	private int solid;

	private static String MODEL = "models/props/minecraft_original/Torch.mdl";
	
	private PropStatic torch = new PropStatic(Torches.MODEL);
	

	/**
	 * Add Plant model instance with selected skin
	 * 
	 * @skin skin index
	 * @solid not solid = 0, use bounding box = 2, use vphysics = 6
	 */
	public Torches(int skin, int solid) {
		this.skin = skin;
		this.solid = solid;
		this.torch.setSkin(skin, solid); // set in PropStatic.java
	}

	@Override
	public void add(Mapper context, Position p, Block material) {

		//Loggger.log(material.getName().toString());
		int verticalRotation = 0;
		int horizontalRotation = 0;
		
		if(material.getName().equals("minecraft:torch")) {
			//is normal torch
			torch.setSkin(0);
			context.setPointToGrid(p);
			context.movePointInGridDimension(0.5, 0, 0.5);
			//torch.getAngles().setY(verticalRotation);
			//torch.getAngles().setZ(horizontalRotation);
			context.addPointEntity(torch);
			
			addFlame(context, 0, 0.6, 0);
			//Loggger.log("is regular torch");
		}
		else if(material.getName().equals("minecraft:wall_torch")) {
			
			//is wall torch
			//get direction
			//Loggger.log("is wall torch");
			String facing = material.getProperty(Property.facing);
			//Loggger.log("torch facing: "+ facing.toString());
			torch.getAngles().setZ(25);
			
			context.setPointToGrid(p);
			//context.movePointInGridDimension(0.5, 0.5, 0);
			
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
			
			//is restone torch
			//Loggger.log("is redstone torch");
			
			context.setPointToGrid(p);
			context.movePointInGridDimension(0.5, 0, 0.5);
			
			String lit = material.getProperty(Property.lit);
			if(lit.equals("true")) {
				//model set to lit skin 1
				torch.setSkin(1);
			}	else {
				//redstone torch not lit, set skin to 2
				torch.setSkin(2);
			}
			torch.getAngles().setY(verticalRotation);
			torch.getAngles().setZ(horizontalRotation);
			context.addPointEntity(torch);
		}
		else if(material.getName().equals("minecraft:redstone_wall_torch")) {
			
			context.setPointToGrid(p);


			//Loggger.log("is redstone wall torch");
			
			String lit = material.getProperty(Property.lit);
			if(lit.equals("true")) {
				//model set to lit skin 1
				torch.setSkin(1);
			}	else {
				//redstone torch not lit, set skin to 2
				torch.setSkin(2);
			}
			//is redstone wall torch
			//get direction
			torch.setSolid(solid);
			String facing = material.getProperty(Property.facing);
			horizontalRotation = 25;
			if(facing.equals("north")) {
				verticalRotation = 180;
				context.movePointInGridDimension(0.5, 0.25, 1);
			}
			if(facing.equals("south")) {
				verticalRotation = 0;
				context.movePointInGridDimension(0.5, 0.25, 0);
			}
			if(facing.equals("west")) {
				verticalRotation = 270;
				context.movePointInGridDimension(1, 0.25, 0.5);
			}
			if(facing.equals("east")) {
				verticalRotation = 90;
				context.movePointInGridDimension(0, 0.25, 0.5);
			}
			torch.getAngles().setY(verticalRotation);
			torch.getAngles().setZ(horizontalRotation);
			context.addPointEntity(torch);
		}
		
		context.markAsConverted(p);
	}
	
	public void addFlame(Mapper context, double x, double y, double z) {
		context.movePointInGridDimension(x, y, z);
		context.addPointEntity(Torches.PARTICLE_SYSTEM);
		context.addPointEntity(Torches.FLAME);
		context.addPointEntity(Torches.LIGHT);
		
	}
}


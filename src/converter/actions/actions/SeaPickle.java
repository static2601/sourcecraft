package converter.actions.actions;

import converter.actions.Action;
import converter.mapper.Mapper;
import minecraft.Block;
import minecraft.Position;
import minecraft.Property;
import vmfWriter.entity.pointEntity.pointEntity.PropStatic;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SeaPickle extends Action {

	// skin, make index for all skin names
	private int skin;
	// not solid = 0, use bounding box = 2, use vphysics = 6
	private int solid;

	private static String seapickle1wl = "models/props/minecraft_original/SeaPickle1WL.mdl";
	private static String seapickle1 = "models/props/minecraft_original/SeaPickle1.mdl";
	private static String seapickle2wl = "models/props/minecraft_original/SeaPickle2WL.mdl";
	private static String seapickle2 = "models/props/minecraft_original/SeaPickle2.mdl";
	private static String seapickle3wl = "models/props/minecraft_original/SeaPickle3WL.mdl";
	private static String seapickle3 = "models/props/minecraft_original/SeaPickle3.mdl";
	private PropStatic seaPickle = new PropStatic(SeaPickle.seapickle1wl);
	

	/**
	 * Add Plant model instance with selected skin
	 * 
	 * @skin skin index
	 * @solid not solid = 0, use bounding box = 2, use vphysics = 6
	 */
	public SeaPickle(int skin, int solid) {
		this.skin = skin;
		this.solid = solid;
		this.seaPickle.setSkin(skin, solid); // set in PropStatic.java
	}

	@Override
	public void add(Mapper context, Position p, Block material) {
		
		//System.out.println(material.get().toString());
		String pickles = material.getProperty(Property.pickles);
		
		
		String waterlogged = material.getProperty(Property.waterlogged);
		
		
		if(pickles.equals("1")) {
			context.setPointToGrid(p);
			//randomize within set area
			
			context.movePointInGridDimension(0.5, 0, 0.5);
			//int verticalRotation = 180;
			seaPickle.getAngles().setY(0);
			seaPickle.setSkin(skin);
			seaPickle.setSolid(solid);
			if(waterlogged.equals("true")) {
				seaPickle.setModel(seapickle1wl);
			}else {
				seaPickle.setModel(seapickle1);
			}
			context.addPointEntity(seaPickle);
		}
		if(pickles.equals("2")) {
			
			
			//four places pickle could be placed
			//ArrayList<Integer> places = new ArrayList<>() {;
			
			double[][] places = {{0.3, 0.3}, {0.3, 0.6}, {0.6, 0.3}, {0.6, 0.6}};
			//double[][] places = {{0.25, 0.75}, {0.75, 0.25}};
			//Integer[] ints = {0, 1, 2, 3};
			Integer[] ints = {0, 1};
			ints = shuffleArray(ints);
			//System.out.println(ints[0] + ", " + ints[1]);
			
			for(int i = 0; i < ints.length; i++) {
				//shuffled places array
				//apply model to first 2 places
				context.setPointToGrid(p);
				//System.out.println("SeaPickle move to "+places[(ints[i])][0]+", 0, " + places[(ints[i])][1]);
				
				if(i == 0) {
					
					
					context.movePointInGridDimension( places[(ints[i])][0], 0, places[(ints[i])][1] );

					if(waterlogged.equals("true")) {
						
					seaPickle.setModel(seapickle3wl);
					} else {
						
						seaPickle.setModel(seapickle3);
					}
					//seaPickle.getAngles().setY(0);
					seaPickle.setSkin(skin);
					seaPickle.setSolid(solid);
					context.addPointEntity(seaPickle);
				} 
				if(i == 1){
					
					//do the opposite angle as the first pickle placed
					double x, z;
					x = places[(ints[0])][0];
					z = places[(ints[0])][1];
					if(x == 0.3) x = 0.6;
					else x = 0.3;
					if(z == 0.3) z = 0.6;
					else z = 0.3;
					context.movePointInGridDimension( x, 0, z );
					//context.movePointInGridDimension( places[(ints[i])][0], 0, places[(ints[i])][1] );
					
					if(waterlogged.equals("true")) {
						
						seaPickle.setModel(seapickle2wl);
					} else {
						
						seaPickle.setModel(seapickle2);
					}
					//seaPickle.getAngles().setY(0);
					seaPickle.setSkin(skin);
					seaPickle.setSolid(solid);
					context.addPointEntity(seaPickle);
				}
				
			}
			
			
			//int verticalRotation = 180;
			
		}
		if(pickles.equals("3")) {
			context.setPointToGrid(p);
			context.movePointInGridDimension(0.5, 0, 0.5);
			//int verticalRotation = 180;
			seaPickle.getAngles().setY(0);
			seaPickle.setSkin(skin);
			seaPickle.setSolid(solid);
			if(waterlogged.equals("true")) {
				seaPickle.setModel(seapickle2wl);
			}else {
				seaPickle.setModel(seapickle2);
			}
			context.addPointEntity(seaPickle);
		}
		if(pickles.equals("4")) {
			context.setPointToGrid(p);
			context.movePointInGridDimension(0.5, 0, 0.5);
			//int verticalRotation = 180;
			seaPickle.getAngles().setY(0);
			seaPickle.setSkin(skin);
			seaPickle.setSolid(solid);
			if(waterlogged.equals("true")) {
				seaPickle.setModel(seapickle3wl);
			}else {
				seaPickle.setModel(seapickle3);
			}
			context.addPointEntity(seaPickle);
		}
		
		context.markAsConverted(p);
		if(waterlogged.equals("true")) {
			addWaterlogged(context, p, material);
		}
	}
	/*
	 if(pickles.equals("2")) {
			
			
			//four places pickle could be placed
			//ArrayList<Integer> places = new ArrayList<>() {;
			
			double[][] places = {{0.25, 0.25}, {0.25, 0.75}, {0.75, 0.25}, {0.75, 0.75}};
			Integer[] ints = {0, 1, 2, 3};
			ints = shuffleArray(ints);
			System.out.println(ints[0] + ", " + ints[1]);
			
			for(int i = 0; i < ints.length-3; i++) {
				//shuffled places array
				//apply model to first 2 places
				context.setPointToGrid(p);
				System.out.println("SeaPickle move to "+places[(ints[i])][0]+", 0, " + places[(ints[i])][1]);
				context.movePointInGridDimension( places[(ints[i])][0], 0, places[(ints[i])][1] );
				seaPickle.getAngles().setY(0);
				seaPickle.setSkin(skin);
				seaPickle.setSolid(solid);
				if(i == 0) {
					
					if(waterlogged.equals("true")) {
						
					seaPickle.setModel(seapickle1wl);
					} else {
						
						seaPickle.setModel(seapickle1);
					}
					context.addPointEntity(seaPickle);
				} 
				if(i == 1){
					
					if(waterlogged.equals("true")) {
						
						seaPickle.setModel(seapickle2wl);
					} else {
						
						seaPickle.setModel(seapickle2);
					}
					context.addPointEntity(seaPickle);
				}
				
			}
			
			
			//int verticalRotation = 180;
			
		}*/
	private Integer[] shuffleArray(Integer[] intArray) {
		//Integer[] intArray = { 1, 2, 3, 4, 5, 6, 7 };
		List<Integer> intList = Arrays.asList(intArray);
		Collections.shuffle(intList);
		return intList.toArray(intArray);
		//return Arrays(intArray);
	}
}

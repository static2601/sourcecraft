package vmfWriter.entity.pointEntity.pointEntity;

import java.io.IOException;

import minecraft.Position;
import periphery.Config;
import periphery.Periphery;
import vmfWriter.Angles;
import vmfWriter.ValveWriter;
import vmfWriter.entity.pointEntity.PointEntity;

public class PropStatic extends PointEntity {

	private int solid;
	private int skin;
	private int shadows;

	private String model;
	private Angles angles;

	// me
	public PropStatic(String model, int skin) {
		this(model, new Angles());
		this.skin = skin;
	}

	public PropStatic(String model) {
		this(model, new Angles());
	}

	public void setSkin(int skin) {
		this.skin = skin;
	}

	public void setSkin(int skin, int solid) {
		this.skin = skin;
		this.solid = solid;
	}

	/**
	* not solid = 0, use bounding box = 2, use vphysics = 6
	*/
	public void setSolid(int solid) {
		this.solid = solid;
	}
	/** 0 - 1 */
	public void disableShadows(int shadows) {
		this.shadows = shadows;
	}

	public PropStatic(String model, Angles angles, int skin, int solid) {
		this.model = model;
		this.angles = angles.copy();
		this.skin = skin;
		this.solid = solid;
	}

	public PropStatic(String model, Angles angles, int skin, int solid, int shadows) {
		this.model = model;
		this.angles = angles.copy();
		this.skin = skin;
		this.solid = solid;
		this.shadows = shadows;
	}

	public PropStatic(String model, Angles angles) {
		this.model = model;
		this.angles = angles.copy();
	}

	public Angles getAngles() {
		return this.angles;
	}

	public void setModel(String model) {
		this.model = model;
	}

	@Override
	public PropStatic create(Position origin) {
		PropStatic result = new PropStatic(this.model, this.angles, this.skin, this.solid, this.shadows);
		result.setOrigin(origin);
		return result;
	}

	@Override
	public String getName() {
		return "prop_static";
	}

	@Override
	public void writeVmfSpecific(ValveWriter writer) throws IOException {
		// get scale, append it to models directory so we know which scaled models to use
		// find in string the materials folder and append _ + scale
		//TODO - test
		// scale will have to get from config and materials folder could change,
		// maybe change model to only be the model name or subfolder and name of model.
		// maybe an option would be 'use custom model' and a path to it.

		Config config = Periphery.CONFIG;
		int scale = config.getScale(config.getConvertOption());
		String game = config.getConvertOption();

		String modelPath = this.model;

		// the model path set in the class contains minecraft_original in most cases
		// set it to be the actual location, <selected_texturepack>_<scale_used>
		// eg: private static final String MODEL = "models/props/minecraft_original/Bed.mdl";
		// change to "models/props/<texturePack>_<scale>/Bed.mdl"
		String texturePack = config.getTexturePack();
		if (modelPath.contains("/minecraft_original/")) {
			modelPath = this.model.replace("/minecraft_original", "/"+ texturePack +"_"+ scale);
		}
		writer.put("angles", this.angles)
				.put("disableselfshadowing", false)
				.put("disableshadows", this.shadows)
				.put("disablevertexlighting", false)
				.put("fademaxdist", 0)
				.put("fademindist", -1)
				.put("fadescale", 1)
				.put("ignorenormals", false)
				.put("maxdxlevel", 0)
				.put("mindxlevel", 0)
				.put("model", modelPath)
				.put("screenspacefade", 0)
				.put("skin", this.skin)
				.put("solid", this.solid);
	}
}
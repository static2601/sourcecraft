package vmfWriter.entity.pointEntity.pointEntity;

import java.io.IOException;

import minecraft.Position;
import vmfWriter.Angles;
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
	public void writeVmfSpecific(vmfWriter.ValveWriter writer) throws IOException {
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
				.put("model", this.model)
				.put("screenspacefade", 0)
				.put("skin", this.skin)
				.put("solid", this.solid);
	}
}
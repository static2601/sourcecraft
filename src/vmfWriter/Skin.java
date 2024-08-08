package vmfWriter;

import converter.Orientation;

public class Skin {

	public String materialFront;
	public String materialLeft;
	public String materialRight;
	public String materialTop;
	public String materialBottom;
	public String materialBack;

	public double scale;

	public double[] uAdjust = {0,0,0,0,0,0};
	public double[] vAdjust = {0,0,0,0,0,0};
	//public int[] vAdjust = new int[6];
	public String axis;
	public Orientation orientation;


	public Skin() {
		this.materialFront = null;
		this.materialLeft = null;
		this.materialRight = null;
		this.materialTop = null;
		this.materialBottom = null;
		this.materialBack = null;
		this.scale = 64 / 256;
	}

	/**
	 * Adjust texture UV Regular
	 * @param side front face is looking north in hammer at a face, right is blocks right face, etc
	 * @param u x, pixels (0-128); default - 0
	 * @param v y, pixels (0-128); default - 0
	 */
	public void adjustUV(int side, double u, double v) {

		Skin.this.uAdjust[side] = u;
		Skin.this.vAdjust[side] = v;
	}
	/**
	 * Adjust texture UV Multiplied by 8
	 * @param side front face is looking north in hammer at a face, right is blocks right face, etc
	 * @param u x, pixels (0-16); default - 0
	 * @param v y, pixels (0-16); default - 0
	 */
	public void adjustUVs(Enum side, double u, double v) {
		int sides = side.ordinal();
		Skin.this.uAdjust[sides] = u * 8;
		Skin.this.vAdjust[sides] = v * 8;
	}
	public enum sides {
		TOP,
		BOTTOM,
		LEFT,
		RIGHT,
		BACK,
		FRONT;

	}

	public Skin(String newMaterial, double newScale) {
		this.materialFront = newMaterial;
		this.materialLeft = newMaterial;
		this.materialRight = newMaterial;
		this.materialTop = newMaterial;
		this.materialBottom = newMaterial;
		this.materialBack = newMaterial;
		this.scale = newScale;
	}

	public Skin(String material, String materialTopBottom, double newScale) {
		this.materialFront = material;
		this.materialLeft = material;
		this.materialRight = material;
		this.materialBack = material;

		this.materialTop = materialTopBottom;
		this.materialBottom = materialTopBottom;

		this.scale = newScale;
	}

	public Skin(String newMaterial, String newMaterialTop, String newMaterialFront, double newScale) {
		this.materialLeft = newMaterial;
		this.materialRight = newMaterial;
		this.materialBack = newMaterial;

		this.materialTop = newMaterialTop;
		this.materialBottom = newMaterialTop;

		this.materialFront = newMaterialFront;

		this.scale = newScale;
	}
	/** Top, bottom, front face with E, W, N, S Directions*/
	public Skin(String newMaterial, String newMaterialTop, String newMaterialFront, Orientation orientation,
			double newScale) {
		this.materialLeft = newMaterial;
		this.materialRight = newMaterial;
		this.materialBack = newMaterial;
		this.materialFront = newMaterial;

		this.materialTop = newMaterialTop;
		this.materialBottom = newMaterialTop;
		this.orientation = orientation;

		switch (orientation) {
		case SOUTH:
			this.materialFront = newMaterialFront;
			break;
		case EAST:
			this.materialRight = newMaterialFront;
			break;
		case NORTH:
			this.materialBack = newMaterialFront;
			break;
		case WEST:
			this.materialLeft = newMaterialFront;
			break;
		}

		this.scale = newScale;
	}

	public Skin(String newMaterial, String newMaterialTop, String newMaterialFront, String newMaterialBottom,
			double newScale) {
		this.materialLeft = newMaterial;
		this.materialRight = newMaterial;
		this.materialBack = newMaterial;

		this.materialTop = newMaterialTop;
		this.materialBottom = newMaterialBottom;
		this.materialFront = newMaterialFront;

		this.scale = newScale;
	}
	/** Top, bottom, front face with E, W, N, S, U, D Directions*/
	public Skin(String newMaterial, String newMaterialTop, String newMaterialFront, String newMaterialBottom,
			Orientation orientation, double newScale) {
		this.materialLeft = newMaterial;
		this.materialRight = newMaterial;
		this.materialBack = newMaterial;
		this.materialFront = newMaterial;

		this.materialTop = newMaterial;
		this.materialBottom = newMaterial;

		// use axis even though not a property since
		// they rotate faces the same way
		switch (orientation) {
		case SOUTH:
			this.materialFront = newMaterialTop;
			this.materialBack = newMaterialBottom;
			this.axis = "z";
			break;
		case EAST:
			this.materialRight = newMaterialTop;
			this.materialLeft = newMaterialBottom;
			this.axis = "x";
			break;
		case NORTH:
			this.materialFront = newMaterialBottom;
			this.materialBack = newMaterialTop;
			this.axis = "z";
			break;
		case WEST:
			this.materialLeft = newMaterialTop;
			this.materialRight = newMaterialBottom;
			this.axis = "x";
			break;
		case DOWN:
			this.materialBottom = newMaterialTop;
			this.materialTop = newMaterialBottom;
			this.axis = "y";
			break;
		case UP:
			this.materialTop = newMaterialTop;
			this.materialBottom = newMaterialBottom;
			this.axis = "y";
			break;
		}
		this.scale = newScale;
	}
	public Skin(String newMaterial, String newMaterialTop, String newMaterialFront, String newMaterialBottom,
				String axis, double newScale) {
		//reset all sides
		this.materialLeft = newMaterial;
		this.materialRight = newMaterial;
		this.materialBack = newMaterial;
		this.materialFront = newMaterial;
		this.materialTop = newMaterial;
		this.materialBottom = newMaterial;
		this.axis = axis;

		switch (axis) {
			case "y":
				this.materialTop = newMaterialTop;
				this.materialBottom = newMaterialBottom;
				break;
			case "x":
				this.materialLeft = newMaterialTop;
				this.materialRight = newMaterialBottom;
				break;
			case "z":
				this.materialFront = newMaterialTop;
				this.materialBack = newMaterialBottom;
				break;
		}
		this.scale = newScale;
	}
}

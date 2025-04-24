package periphery;

import minecraft.Position;
import vmfWriter.Color;
import java.util.Objects;

public class Option {

	private String className;
	private String world;
	private Color normalLight;
	//private Lights normalLight;
	private Color soulLight;
	private Color redstoneLight;
	private LightDistance normalDistance;
	private LightDistance soulDistance;
	private LightDistance redstoneDistance;

	private boolean useModel;
	private boolean brushTrimmed;
	private boolean brushFull;
	private boolean brushCombined;
	private boolean brushOptimized;
	private boolean brushSetForPropper;
	private boolean translucent;



	private Position start;
	private Position end;

	public Option(String name) {
		this.className = name;
	}

// only used for creating a default/generated script?
//	public static Option create() {
//		return new Option();
//	}
//	public Option() {
//		this.normalLight = new Color(255, 236, 191, 60);
//		//Color color = new Color(255, 236, 191, 60);
//		//this.normalLight = new Lights(color, 236, 191);
//		this.soulLight = new Color(80, 240, 242, 60);
//		this.redstoneLight = new Color(200, 20, 20, 60);
//		this.normalDistance = new LightDistance(96, 256);
//		this.soulDistance = new LightDistance(96, 256);
//		this.redstoneDistance = new LightDistance(96, 256);
//	}

	public Option setNormalLight(Color color) {
		this.normalLight = color;
		return this;
	}
	public Option setSoulLight(Color color) {
		this.soulLight = color;
		return this;
	}
	public Option setRedstoneLight(Color color) {
		this.redstoneLight = color;
		return this;
	}

	public String getClassName() {
		return this.className;
	}

	public Color getNormalLight() { return this.normalLight.copy(); }
	public Color getSoulLight() {
		return this.soulLight.copy();
	}
	public Color getRedstoneLight() {
		return this.redstoneLight.copy();
	}
	public LightDistance getNormalDistance() {
		return this.normalDistance.copy();
	}
	public LightDistance getSoulDistance() { return this.soulDistance.copy(); }
	public LightDistance getRedstoneDistance() {
		return this.redstoneDistance.copy();
	}


	public boolean get_useModel() { return this.useModel; }
	public Option set_useModel(boolean bool) {
		this.useModel = bool;
		return this;
	}
	public boolean get_brushTrimmed() { return this.brushTrimmed; }
	public Option set_brushTrimmed(boolean bool) {
		this.brushTrimmed = bool;
		return this;
	}
//	public boolean get_brushFull() { return this.brushFull; }
//	public Option set_brushFull(boolean bool) {
//		this.brushFull = bool;
//		return this;
//	}
//	public boolean get_brushCombined() { return this.brushCombined; }
//	public Option set_brushCombined(boolean bool) {
//		this.brushCombined = bool;
//		return this;
//	}
	public boolean get_brushOptimized() { return this.brushOptimized; }
	public Option set_brushOptimized(boolean bool) {
		this.brushOptimized = bool;
		return this;
	}
	public boolean get_setForPropper() { return this.brushSetForPropper; }
	public Option set_setForPropper(boolean bool) {
		this.brushSetForPropper = bool;
		return this;
	}
	public boolean get_translucent() { return this.translucent; }
	public Option set_translucent(boolean bool) {
		this.translucent = bool;
		return this;
	}

	@Override
	public String toString() {
		return this.className;
	}

	public Option setTo(Option other) {
		if (other == null) {
			return this;
		}
		if (!other.className.isEmpty()) {
			this.className = other.className;
		}
		this.start.setTo(other.start);
		this.end.setTo(other.end);
		return this;
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.className, this.end, this.start, this.world);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Option)) {
			return false;
		}
		Option other = (Option) obj;
		return Objects.equals(this.className, other.className) && Objects.equals(this.end, other.end)
				&& Objects.equals(this.start, other.start) && Objects.equals(this.world, other.world);
	}

}

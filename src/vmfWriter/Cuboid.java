package vmfWriter;

import java.io.IOException;
import java.util.Objects;
import basic.Loggger;
import basic.Tuple;
import converter.Orientation;
import minecraft.Position;

public class Cuboid extends EightPoint {

	public Cuboid(Cuboid other) {
		this.skin = other.skin; // TODO copy skin
		this.a = other.a;
		this.aTop = other.aTop;
		this.dTop = other.dTop;
		this.d = other.d;
		this.e = other.e;
		this.eTop = other.eTop;
		this.hTop = other.hTop;
		this.h = other.h;
		this.textureScaleX = other.textureScaleX;
		this.textureScaleY = other.textureScaleY;
		this.textureScaleZ = other.textureScaleZ;
	}

	public Cuboid(Position[] p, Skin skin) {
		this.skin = skin;

		if (p.length < 8) {
			if (p.length < 1) {
				Loggger.warn("Less than 8 Points given.");
			} else {
				Loggger.warn("Less than 8 Points given. Create a cuboid with the first given point.");
			}
		} else {
			this.a = p[0];
			this.aTop = p[1];
			this.dTop = p[2];
			this.d = p[3];
			this.e = p[4];
			this.eTop = p[5];
			this.hTop = p[6];
			this.h = p[7];
		}
	}

	public Cuboid(Tuple<Position, Position> positions, Skin skin) {
		this(positions);
		this.setSkin(skin);
	}

	public Cuboid(Tuple<Position, Position> positions) {
		Position aPoint = positions.getFirst();
		Position gPoint = positions.getSecond();
		if (aPoint.smaller(gPoint) == false) {
			Loggger.warn("Attampted to create invalid Cuboid.");
			Loggger.warn(" from " + aPoint.getString() + " to " + gPoint.getString());
			gPoint = aPoint.getOffset(1, 1, 1);
			this.skin = new Skin("Attempted to creat invalid Cuboid", 0.25);
		}
		int xOffset = gPoint.x - aPoint.x;
		int yOffset = gPoint.y - aPoint.y;
		int zOffset = gPoint.z - aPoint.z;
		this.a = new Position(aPoint);
		this.e = this.a.getOffset(0, yOffset, 0);
		this.d = this.a.getOffset(xOffset, 0, 0);
		this.h = this.a.getOffset(xOffset, yOffset, 0);

		this.aTop = this.a.getOffset(0, 0, zOffset);
		this.eTop = this.e.getOffset(0, 0, zOffset);
		this.dTop = this.d.getOffset(0, 0, zOffset);
		this.hTop = this.h.getOffset(0, 0, zOffset);
	}

	public Cuboid(Position fPoint, int xLength, int yLength, int zLength, int scale) {
		if (xLength <= 0 && yLength <= 0 && zLength <= 0) {
			Loggger.warn("Attampted to create invalid Cuboid.");
			xLength = -xLength + 1;
			yLength = -yLength + 1;
			zLength = -zLength + 1;
			this.skin = new Skin("Attempted to creat invalid Cuboid", 0.25);
		}
		this.eTop = new Position(fPoint);
		this.hTop = new Position(this.eTop.x + xLength, this.eTop.y, this.eTop.z);
		this.aTop = new Position(this.eTop.x, this.eTop.y - yLength, this.eTop.z);
		this.dTop = new Position(this.eTop.x + xLength, this.eTop.y - yLength, this.eTop.z);
		this.e = new Position(this.eTop.x, this.eTop.y, this.eTop.z - zLength);
		this.h = new Position(this.hTop.x, this.hTop.y, this.hTop.z - zLength);
		this.a = new Position(this.aTop.x, this.aTop.y, this.aTop.z - zLength);
		this.d = new Position(this.dTop.x, this.dTop.y, this.dTop.z - zLength);
		this.scale(scale);
	}

	public void setTextureScale(double[] textureScale) {
		this.textureScaleX = textureScale[0];
		this.textureScaleY = textureScale[1];
		this.textureScaleZ = textureScale[2];
	}

	public void cut(Orientation cut) {
		int xLength = this.hTop.x - this.a.x;
		int yLength = this.hTop.y - this.a.y;
		if (cut != null) {
			switch (cut) {
			case NORTH:
				this.aTop.y = this.aTop.y + xLength;
				this.dTop.y = this.dTop.y + xLength;
				break;
			case SOUTH:
				this.eTop.y = this.eTop.y - xLength;
				this.hTop.y = this.hTop.y - xLength;
				break;
			case EAST:
				this.eTop.x = this.eTop.x + yLength;
				this.aTop.x = this.aTop.x + yLength;
				break;
			case WEST:
				this.dTop.x = this.dTop.x - yLength;
				this.hTop.x = this.hTop.x - yLength;
				break;
			default:
				break;
			}
		}
	}

	public void extend(Orientation side, Orientation cut) {
		int xLength = this.hTop.x - this.a.x;
		int yLength = this.hTop.y - this.a.y;
		if (cut != null) {
			switch (cut) {
			case NORTH:
				if (side.equals(Orientation.EAST)) {
					this.a.y = this.a.y - xLength;
				} else {
					this.d.y = this.d.y - xLength;
				}
				break;
			case SOUTH:
				if (side.equals(Orientation.EAST)) {
					this.e.y = this.e.y + xLength;
				} else {
					this.h.y = this.h.y + xLength;
				}
				break;
			case EAST:
				if (side.equals(Orientation.NORTH)) {
					this.a.x = this.a.x - yLength;
				} else {
					this.e.x = this.e.x - yLength;
				}
				break;
			case WEST:
				if (side.equals(Orientation.NORTH)) {
					this.d.x = this.d.x + yLength;
				} else {
					this.h.x = this.h.x + yLength;
				}
				break;
			default:
				break;
			}
		}
	}

	@Override
	public void writeVmf(ValveWriter writer) throws IOException {
		if (this.a != null) {
			writer.open(Solid.SOLID_TAG)
					.putBrushID();

			//set default position up, axis y
			Position uTop = TOP_U_AXIS;       //  1,  0,  0
			Position vTop = TOP_V_AXIS;       //  0, -1,  0
			Position uBottom = BOTTOM_U_AXIS; // -1,  0,  0
			Position vBottom = BOTTOM_V_AXIS; //  0, -1,  0
			Position uFront = FRONT_U_AXIS;   //  1,  0,  0
			Position vFront = FRONT_V_AXIS;   //  0,  0, -1
			Position uBack = BACK_U_AXIS;     // -1,  0,  0
			Position vBack = BACK_V_AXIS;     //  0,  0, -1
			Position uLeft = LEFT_U_AXIS;     //  0, -1,  0
			Position vLeft = LEFT_V_AXIS;     //  0,  0, -1
			Position uRight = RIGHT_U_AXIS;   //  0,  1,  0
			Position vRight = RIGHT_V_AXIS;   //  0,  0, -1

			// non - axis specifically setup for terracotta
			if(this.skin.orientation != null) {
				if(Objects.equals(this.skin.orientation, Orientation.SOUTH)) {
					uTop = new Position(1,0,0);
					vTop = new Position(0,-1,0);
					uBottom = new Position(1,0,0);
					vBottom = new Position(0,1,0);
					uFront = new Position(0,0,1);
					vFront = new Position(1,0,0);
					uBack = new Position(0,0,-1);
					vBack = new Position(1,0,0);
					uLeft = new Position(0,-1,0);
					vLeft = new Position(0,0,-1);
					uRight = new Position(0,-1,0);
					vRight = new Position(0,0,1);
				}
				if(Objects.equals(this.skin.orientation, Orientation.EAST)) {
					uTop = new Position(0,1,0);
					vTop = new Position(1,0,0);
					uBottom = new Position(0,1,0);
					vBottom = new Position(-1,0,1);
					uFront = new Position(1,0,0);
					vFront = new Position(0,0,-1);
					uBack = new Position(1,0,0);
					vBack = new Position(0,0,1);
					uLeft = new Position(0,0,-1);
					vLeft = new Position(0,1,0);
					uRight = new Position(0,0,1);
					vRight = new Position(0,1,0);
				}
				if(Objects.equals(this.skin.orientation, Orientation.NORTH)) {
					uTop = new Position(-1,0,0);
					vTop = new Position(0,1,0);
					uBottom = new Position(-1,0,0);
					vBottom = new Position(0,-1,0);
					uFront = new Position(0,0,-1);
					vFront = new Position(-1,0,0);
					uBack = new Position(0,0,1);
					vBack = new Position(-1,0,0);
					uLeft = new Position(0,1,0);
					vLeft = new Position(0,0,1);
					uRight = new Position(0,1,0);
					vRight = new Position(0,0,-1);
				}
				if(Objects.equals(this.skin.orientation, Orientation.WEST)) {
					uTop = new Position(0,-1,0);
					vTop = new Position(-1,0,0);
					uBottom = new Position(0,-1,0);
					vBottom = new Position(1,0,0);
					uFront = new Position(-1,0,0);
					vFront = new Position(0,0,1);
					uBack = new Position(0,0,-1);
					vBack = new Position(-1,0,0);
					uLeft = new Position(0,0,1);
					vLeft = new Position(0,-1,0);
					uRight = new Position(0,0,-1);
					vRight = new Position(0,-1,0);
				}
			}
			if(this.skin.axis != null) {
				if (Objects.equals(this.skin.axis, "x")) {
					uTop = BOTTOM_V_AXIS;
					vTop = BACK_U_AXIS;
					uBottom = RIGHT_U_AXIS;
					vBottom = BACK_U_AXIS;
					uLeft = new Position(0,1,0);
					vLeft = new Position(0,0,-1);
					//uRight = FRONT_V_AXIS;
					//vRight = TOP_V_AXIS;
					uRight = new Position(0,-1,0);
					vRight = new Position(0,0,-1);
					uFront = BACK_V_AXIS;
					vFront = BOTTOM_U_AXIS;
					uBack = BACK_V_AXIS;
					vBack = TOP_U_AXIS;
				}
				if (Objects.equals(this.skin.axis, "z")) {
					uTop = TOP_U_AXIS;
					vTop = BOTTOM_V_AXIS;
					uBottom = TOP_U_AXIS;
					vBottom = RIGHT_U_AXIS;
					uLeft = FRONT_V_AXIS;
					vLeft = RIGHT_U_AXIS;
					uRight = FRONT_V_AXIS;
					vRight = TOP_V_AXIS;
					uFront = new Position(-1,0,0);
					vFront = new Position(0,0,1);
					uBack =  new Position(1,0,0);
					vBack =  new Position(0,0,1);

				}
			}

//			top
			this.writeSide(writer, this.aTop, this.eTop, this.hTop, this.skin.materialTop, this.textureScaleX,
					this.textureScaleY, uTop, vTop, this.skin.uAdjust[0], this.skin.vAdjust[0]);
//			bottom
			this.writeSide(writer, this.e, this.a, this.d, this.skin.materialBottom, this.textureScaleX,
					this.textureScaleY, uBottom, vBottom, this.skin.uAdjust[1], this.skin.vAdjust[1]);

//			negative x side
			this.writeSide(writer, this.a, this.e, this.eTop, this.skin.materialLeft, this.textureScaleX,
					this.textureScaleZ, uLeft, vLeft, this.skin.uAdjust[2], this.skin.vAdjust[2]);
//			positive x side
			this.writeSide(writer, this.h, this.d, this.dTop, this.skin.materialRight, this.textureScaleX,
					this.textureScaleZ, uRight, vRight, this.skin.uAdjust[3], this.skin.vAdjust[3]);

//			positive y side
			this.writeSide(writer, this.e, this.h, this.hTop, this.skin.materialBack, this.textureScaleY,
					this.textureScaleZ, uBack, vBack, this.skin.uAdjust[4], this.skin.vAdjust[4]);
//			negative y side
			this.writeSide(writer, this.d, this.a, this.aTop, this.skin.materialFront, this.textureScaleY,
					this.textureScaleZ, uFront, vFront, this.skin.uAdjust[5], this.skin.vAdjust[5]);

			writer.open(ValveElement.EDITOR_TAG)
					.put(ValveElement.COLOR, "0 215 172")
					.put(ValveElement.VISGROUPSHOWN, 1)
					.put(ValveElement.VISGROUPAUTOSHOWN, 1)
					.close();
			writer.close();
		} else {
			throw new IllegalArgumentException("Attempted to write Cuboid without parameters set.");
		}
	}

}

package nbtReader;

import basic.Loggger;
import converter.mapper.Mapper;
import main.Main;
import minecraft.Block;
import minecraft.Position;
import periphery.Place;
import vmfWriter.entity.pointEntity.pointEntity.PropStatic;

import java.io.IOException;
import java.util.*;

//TODO double check everything works ok/wont error and clean up
public class BlockEntities {

	private String id = "";
	private Position xyz  = new Position(0,0,0);
	private String[] signFrontText = new String[]{"","","",""};
	private String[] signBackText = new String[]{"","","",""};
	private int hasGlowFront;
	private int hasGlowBack;
	private String colorFront;
	private String colorBack;

	public String[] getText(Position pos, int side) {
		getSignEntData(pos);
		if(side == 0) return this.signFrontText;
		else return this.signBackText;
	}

	public int getGlowingText(Position pos, int side) {
		getSignEntData(pos);
		if(side == 0) return hasGlowFront;
		else return hasGlowBack;
	}

	public boolean hasGlowingText(Position pos, int side) {
		getSignEntData(pos);
		if(side == 0)
            return hasGlowFront == 1;
        return hasGlowBack == 1;
	}

	public String getColor(Position pos, int side) {
		getSignEntData(pos);
		if(side == 0) return colorFront;
		else return colorBack;
	}

	public enum side {
		FRONT,
		BACK
	}
	private void getSignEntData(Position pos) {

		Place place = Main.getPlaceCoordinates;
		Position start = place.getStart();
		//Position start = new Position(-60, 50, -60);
		Position newPos = Position.add(start, pos);
		newPos = Position.subtract(newPos, new Position(1,1,1));
		String newXYZ = newPos + "";

		int size = Main.mainSignTextArr.size();
		for(int i = 0; i < size; i++) {
			String signXYZ = Main.mainSignTextArr.get(i)[12];
			String mc = Main.mainSignTextArr.get(i)[13];
			if(mc.endsWith("sign") && signXYZ.equals(newXYZ)) {
				formatData(Main.mainSignTextArr.get(i));
			}
		}
	}

	private void getBookEntData() {
		String bookText = Main.bookTextArr.get(0);
		if(bookText.contains("[SC:ModelPlaceAll]")) {
			int start = bookText.indexOf("[SC:ModelPlaceAll]");
			// get the next line of entry to indicate model tags on signs
			// to replace and which model to replace with
		}
	}

	private void formatData(String[] data) {
		if (data != null) {
			this.signFrontText = new String[]{
					dq(data[0]), dq(data[1]), dq(data[2]), dq(data[3])};
			this.signBackText = new String[]{
					dq(data[6]), dq(data[7]), dq(data[8]), dq(data[9])};
			this.hasGlowFront = Integer.parseInt(data[4]);
			this.hasGlowBack = Integer.parseInt(data[10]);
			this.colorFront = data[5];
			this.colorBack = data[11];
		}
	}
	private String dq(String s) {
		if (s == null) return "";
		return s.replace("\"", "");
	}
	private static final NamedTag XX = new NamedTag(NbtTag.INT, "x");
	private static final NamedTag YY = new NamedTag(NbtTag.INT, "y");
	private static final NamedTag ZZ = new NamedTag(NbtTag.INT, "z");
	private static final NamedTag ID = new NamedTag(NbtTag.STRING, "id");
	//private static final NamedTag ISWAXED = new NamedTag(NbtTag.BYTE, "is_waxed");
	//private static final NamedTag KEEPPACKED = new NamedTag(NbtTag.BYTE, "keepPacked");
	private static final NamedTag MESSAGES = new NamedTag(NbtTag.LIST, "messages");
	private static final NamedTag COLOR = new NamedTag(NbtTag.STRING, "color");
	private static final NamedTag HASGLOWINGTEXT = new NamedTag(NbtTag.BYTE, "has_glowing_text");
	private static final NamedTag TEXTFRONT = new NamedTag(NbtTag.COMPOUND, "front_text");
	private static final NamedTag TEXTBACK = new NamedTag(NbtTag.COMPOUND, "back_text");

	public void readSignText(NbtReader reader) throws IOException {
		String[] signText = new String[14];
		reader.doCompound(NbtTasks.I.create()

			.put(ID, () -> {
				this.id = reader.readString();
				signText[13] = this.id;
				Loggger.log("this.id:" + this.id);
			})
			.put(XX, () -> this.xyz.x = reader.readInt())
			.put(YY, () -> this.xyz.y = reader.readInt())
			.put(ZZ, () -> this.xyz.z = reader.readInt())
			//.put(ISWAXED, () -> this.isWaxed = reader.readByte())
			//.put(KEEPPACKED, () -> this.keepPacked = reader.readByte())
			.put(TEXTFRONT, () -> reader.doCompound(NbtTasks.I.create()
				.put(MESSAGES, () -> {
					int listTag = reader.readTag();
					signText[12] = this.xyz+"";
					if (listTag == NbtTag.STRING && reader.readLength() == 4) {
						signText[0] = reader.readString();
						signText[1] = reader.readString();
						signText[2] = reader.readString();
						signText[3] = reader.readString();
						Loggger.log("adding to Main.mainSignTextArr: "+Arrays.toString(signText));
					} else {
						reader.skipListOf(listTag);
					}
				})
				.put(HASGLOWINGTEXT, () -> {
					signText[4] = String.valueOf(reader.readByte());
				})
				.put(COLOR, () -> {
					signText[5] = reader.readTitle();
				})
			))
			.put(TEXTBACK, () -> reader.doCompound(NbtTasks.I.create()
				.put(MESSAGES, () -> {
					int listTag = reader.readTag();
					if (listTag == NbtTag.STRING && reader.readLength() == 4) {
						signText[6] = reader.readString();
						signText[7] = reader.readString();
						signText[8] = reader.readString();
						signText[9] = reader.readString();
					} else {
						reader.skipListOf(listTag);
					}
				})
				.put(HASGLOWINGTEXT, () -> {
					signText[10] = String.valueOf(reader.readByte());
				})
				.put(COLOR, () -> {
					signText[11] = reader.readTitle();
				})
			))
		);
		Main.mainSignTextArr.add(signText);

		Loggger.log("ID = " +this.id);
		Loggger.log("XYZ: "+ this.xyz.x+", "+this.xyz.y+", "+this.xyz.z);
	}
	public void doSignCommand(String[] text, Mapper context, Position p, Block m, PropStatic sign) {
		if(text[1].toLowerCase().contains("[cs:setmodel]")) {
			String mdl = text[2].toLowerCase()
					.replace("[", "")
					.replace("]", "")
					.replace("\"", "");

			String model = "models/props/minecraft_original/"+mdl+".mdl";
			PropStatic prop = new PropStatic(model);
			context.setPointToGrid(p);
			context.movePointInGridDimension(0.5, 0, 0.5);
			String skn = text[3]
					.replace("[", "")
					.replace("]", "")
					.replace("\"", "");
			int skin = Integer.parseInt(skn);
			prop.getAngles().setY(sign.getAngles().y);
			prop.setSkin(skin);
			prop.setSolid(6);
			context.addPointEntity(prop);
			context.markAsConverted(p);
		}
		else
		if(text[0].toLowerCase().contains("[cs:teleport]")) {
			// get suffix in code, A = teleport, B = destination
			//TODO
		}
		else
		if (text[0].toLowerCase().contains("[cs:playerstart]")) {
			if (text[1].equalsIgnoreCase("team:any")) {

			}
			else {
				// team - specific, game - specific player start

			}
		}
	}
}

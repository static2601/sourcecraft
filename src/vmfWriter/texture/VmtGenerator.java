package vmfWriter.texture;

import java.awt.Color;
import java.io.File;
import java.io.IOException;

import basic.Loggger;
import vmfWriter.ValveWriter;

public class VmtGenerator {
	private static final Color GRASS_COLOR = new Color(102, 255, 51);

	public static void main(String[] args) {
		try {
			File directory = new File("textures" + File.separator + "minecraft_original");
			for (File subFile : directory.listFiles()) {
				if (!subFile.getName()
						.endsWith(".vtf")) {
					continue;
				}
				Loggger.log(subFile.getName()
						.substring(0, subFile.getName()
								.length() - 4));
				String vtfName = subFile.getName();
				String plainName = vtfName.substring(0, vtfName.lastIndexOf('.'));
				ValveWriter writer = new ValveWriter(new File(directory, plainName + ".vmt"));
				ValveTexture texture = new ValveTexture("minecraft_original" + "/" + plainName);
				if (plainName.equals("spawner")) {
					texture.setTranslucent(true);
				} else if (plainName.endsWith("leaves")) {
					texture.setTranslucent(true);
					texture.setColor(GRASS_COLOR);
				} else if (plainName.endsWith("glass") || plainName.endsWith("glass_pane")) {
					texture.setTranslucent(true);
				} else if (plainName.equals("grass_block_top") || plainName.equals("grass_block_side_overlay")) {
					texture.setColor(GRASS_COLOR);
				} else if (plainName.equals("glass") || plainName.equals("glass_pane")) {
					// texture.setColor(GRASS_COLOR);
				}
				texture.writeVmf(writer);
				writer.finish();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

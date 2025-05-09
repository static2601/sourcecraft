package periphery;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import basic.Loggger;

public class Periphery {

	private static final Path CONFIG_PATH = new File("config.json").toPath();
	private static final Path PLACES_PATH = new File("places.json").toPath();
	private static final Path OPTIONS_PATH = new File("options.json").toPath();

	private static final String SC_TEXTURE_FOLDER = "textures";
	private static final String MINECRAFT_ORIGINAL = "minecraft_original";

	public static Config CONFIG = new Config();
	public static Places PLACES = new Places();
	public static Options OPTIONS = new Options();

	public static void init() {
		CONFIG = new Config();
		PLACES = new Places();
		OPTIONS = new Options();
		obtainConfig();
		obtainPlaces();
		obtainOptions();
	}

	public static void obtainConfig() {
		CONFIG = ConfigIO.obtain(CONFIG, CONFIG_PATH);
	}

	public static void writeConfig() {
		try {
			ConfigIO.write(CONFIG, CONFIG_PATH);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void writePlaces() {
		writePlaces(PLACES);
	}

	public static void obtainPlaces() {
		PLACES = new Places();
		try {
			Gson gson = new GsonBuilder().create();
			if (PLACES_PATH.toFile()
					.exists()) {
				String fileAsString = new String(Files.readAllBytes(PLACES_PATH));
				PLACES = gson.fromJson(fileAsString, Places.class);
			}
		} catch (IOException e) {
			Loggger.log(e.getMessage());
		}
	}
	public static void obtainOptions() {
		OPTIONS = new Options();
		try {
			Gson gson = new GsonBuilder().create();
			if (OPTIONS_PATH.toFile()
					.exists()) {
				String fileAsString = new String(Files.readAllBytes(OPTIONS_PATH));
				OPTIONS = gson.fromJson(fileAsString, Options.class);
			}
		} catch (IOException e) {
			Loggger.log(e.getMessage());
		}
	}

	private static void writePlaces(Places places) {
		Gson gson = new GsonBuilder().setPrettyPrinting()
				.create();
		String toSafe = gson.toJson(places);
		try {
			Files.write(PLACES_PATH, toSafe.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void write() {
		Periphery.writeConfig();
		Periphery.writePlaces();
	}

	public static String[] detectTexturePacks() {
		File file = new File(SC_TEXTURE_FOLDER + File.separator);
		File[] files = file.listFiles(new DirectoryFilter());
		if (files == null) {
			// TODO
			Loggger.log("no textures to install found");
		} else {
			int length = files.length;
			String[] detectedTexturePacks = new String[length];
			int i = 0;
			for (File f : files) {
				detectedTexturePacks[i] = f.getName();
				i++;
			}
			return detectedTexturePacks;
		}
		return null;
	}

	/// populate combobox with texture paths by defaultGame selected, by option if provided
	public static String[] getGameDirTexturePacks(String gameName) {
		// change to game directory materials folder
		boolean mc_original = false;

		File file = new File(CONFIG.getGame(gameName).getMaterialsPath() + File.separator);
		File[] files = file.listFiles(new DirectoryFilter());
		if (files == null) {
			//
		} else {
			int length = files.length+1;
			String[] detectedTexturePacks = new String[length];
			int i = 0;
			for (File f : files) {
				if (f.getName().equals(MINECRAFT_ORIGINAL)) {
					mc_original = true;
				}
				if(!excludedFolder(f)) {
					detectedTexturePacks[i] = f.getName();
					i++;
				}
			}
			if (detectedTexturePacks.length > 0) {
				// but doest include minecraft_original, add it as an option
				if (!mc_original) {
					detectedTexturePacks[i] = MINECRAFT_ORIGINAL;
				}

				// remove any null entries
				//TODO - do this a cleaner way
				List<String> tempArr = new ArrayList<>();
				for(String pack : detectedTexturePacks) {
					if (pack != null) {
						tempArr.add(pack);
						Loggger.log("detected Texture Packs: " + pack);
					}
				}
				String[] tempArr2 = new String[tempArr.size()];
				for (int x=0; x<tempArr.size(); x++) {
					tempArr2[x] = tempArr.get(x);
				}
				detectedTexturePacks = tempArr2.clone();

				return detectedTexturePacks;
			}
		}
		// detectedTexturePacks is empty, create single option for original
		String[] detectedTexturePacks = {MINECRAFT_ORIGINAL};
		Loggger.log("no textures found, using default "+ MINECRAFT_ORIGINAL);
		return detectedTexturePacks;
	}

	public static boolean excludedFolder(File f) {
		String[] folders = {
				"skybox",
				"particles",
				"vgui"
		};
		for (String folder : folders) {
			if (f.getName().equals(folder)) {
				return true;
			}
		}
		return false;
	}
}

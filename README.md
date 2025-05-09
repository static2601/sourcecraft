# Updated for Minecraft 1.21.5

# sourcecraft 

an easy to use Minecraft to Source Engine Converter. It converts a given piece of Minecraft World into a map in 
.vmf-format that can be opened by Hammer Editor. It still needs to be compiled by Hammer Editor (/the Source-Engine 
compile tools). Aim of this project though is that no further steps or Hammer-Editor editing skills are needed. 
That is sourcecraft creates as a (after compiling) a runnable and playable map with spawn-points and so on.

### what is needed

- Minecraft world of version **1.18** through **1.21.5** either created in singleplayer or by a Minecraft server.
- ~~Java Runtime Environment 1.8 or newer~~
- JDK 24 minimum. Im not sure which Java Runtime Environment version would be required.
- Recommended: Steam with installed target Source-Engine game and installed 'Source SDK'. The latter includes the Hammer 
- Editor and the compile tools. You should have launched your target game and 'Source SDK' for your target game at least 
- once. Then sourcecraft suggests to create the output-map at the correct path for the Hammer Editor. It can also copy the 
- required textures to the correct path.

### textures

- textures can be generated from my other project 'MCContentConvertor' at: https://github.com/static2601/MCContentConvertor.
This is a work in progress and only just released it. I expect there to be issues, but has been stable in my testings.
The textures will be extracted from your Minecraft JAR file. I have created a lot of the models (SMDs) and those will 
compile at runtime and be added to your game 'directory/props' folder. Everything will go where it needs to based on the
steam path you specify when generating your map. I will have further instructions there.


- Anyone who's familiar with previous Sourcecraft versions might remember that textures in the 'textures' folder would be 
copied to their game directory. I've removed this and replaced with being able to choose which texture pack already
exists in the materials folder of your game directory. This makes it easier to implement the MC Content Convertor since
textures generated there will be sent directly to your game's materials folder. Previous method could be added back if needed.
If you have different texture pack you would like to use, you can add it to your game's materials folder and select it from
the textures dropdown option. 


### hammer editor
- You can launch Hammer Editor directly from Sourcecraft (alternatively you may launch it via Steam). Open the converted 
.vmf-file via 'File->Open'. You may further edit your map here. By pressing F9 you open the compile dialog. For a first 
test, you should select for "Run VIS" the option "Fast". It is also better to check not to launch the game after 
compiling. Instead launch your target game manually with launch option "-console". Ingame you can open your map with the 
console command "map my-map-name".

### instructions
- Unzip anywhere and run the bat file. Select your Minecraft Saves folder (Worlds). Select your Steam path.


- **Continue** -> Select your World. Choose Coordinates. Enter your from: XYZ and to: XYZ.  If you would like to choose 
a name for this, check 'Remember as' and your name.


- **Continue** -> Select your Source game from dropdown. 'Output file' is where your map vmf will be. If you would like 
to change the name, then you can do that here, otherwise it will default to test and **overwrite** any previous map with
that name.


- **Continue** -> 'Details' select your game presets. This sets the Skybox and Light Environment along with what Scale 
(Hammer Units) it will be sized to. If your Converting a TF2 map, I'd suggest changing the default 48 units to 50. This 
can be done in the 'config.json'. Minimum width for any character is 49, so 50 will be tight but you can still get 
though a door way one block wide. Save and close the json. You may have to restart Sourcecraft for changes to be set. If
you set a 'Places', it won't be saved until the convertor is run, unfortunately. This is something that will be changed 
in the future.


- To set your textures, select the dropdown texture pack you'd like to use, 'minecraft_original' is the default. 
Originally, Sourcecraft had a texture folder you would drop the textures into and it would copy them to your game 
directory/materials folder, but has recently changed.


- If you need textures, go to my other project '**MCContentConvertor**' to convert textures from there. Make sure you 
set the Units to what your map is converting to (check the config, under your default game you selected under Details).


- **Run** -> Compile your map. Check the console for any errors and report them if you can. Results of your compile 
will be shown at the bottom of the console.


- **Options.json** -> There are a few other options you can set here. Light color and distance for 'Lanterns' and 
'Torches'. you can also set how your trees look. there are various options for using leaves as a model or as a brush, 
you can have them optimize(default) or be individual brushes. If you let it optimize, if the tree is set to transparent,
the tree may look empty because the engine removes faces that are closed off for optimization. To fix this, i've added 
an option to trim the brushes, so theyre faces dont touch. This will make the leaves have tiny spaces around them, 
you may or may not want this. To enable, check **brushTrimmed: true** and **brushOptimized: false**, **useModel: false**
and **brushSetForPropper: false**.


- If you want to use them as models, you can check **useModel: true**.


- Another thing you can do and I would recommend is to make your trees into models with the Hammer++ feature. Make sure 
you check **brushSetForPropper: true** for trees to only be brushes. Anything that is not a brush on the tree will not 
render into the model. The translucent property wont do anything and will be may be removed in the future. If you want 
the tree leaves to be transparent, set it in the **Options.json** for my other project **MC Content Convertor**.


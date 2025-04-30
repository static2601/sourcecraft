package nbtReader;

import java.io.IOException;

import basic.Loggger;
import converter.Converter;

public class Chunk {

	private static final NamedTag SECTIONS = new NamedTag(NbtTag.LIST, "sections");
	private static final NamedTag BLOCKENTITIES = new NamedTag(NbtTag.LIST, "block_entities");

	public Chunk readNbt(NbtReader reader, WorldPiece piece, Converter target) throws IOException {
		if (reader.readTag() == NbtTag.COMPOUND) {
			reader.readTitle(); // is empty
			reader.doCompound(NbtTasks.I.create()
				.put(SECTIONS, () -> reader.doListOfCompounds(() -> {
					Section section = new Section(piece);
					section.readNbt(reader);
					target.addSection(section);
				}))
				.put(BLOCKENTITIES, () -> {
					//TODO
					// when the chunk is being read, the sign has a chance
					// to send in the coordinates for what area to convert
					// then could update the position or save to places for next time.
					// or maybe stop and start again...
					int listTag = reader.readTag();
					int readLength = reader.readLength();
					BlockEntities blockEntities = new BlockEntities();
					Loggger.log("new BlockEntities()");
					if (listTag == NbtTag.COMPOUND && readLength > 0) {
						for(int i = 0; i < readLength; i++) {
							blockEntities.readSignText(reader);
						}
					} else {
						reader.skipTag();
					}
				})
			);
		}
		return this;
	}
}

package vmfWriter.entity.solidEntity;

import converter.Skins;
import vmfWriter.Solid;
import vmfWriter.ValveWriter;

import java.io.IOException;

public class FuncLadder extends SolidEntity {

	public FuncLadder(Solid solid) {
		super(solid);
		solid.setSkin(Skins.LADDER);
	}

	@Override
	public String getName() {
		return "func_ladder";
	}

	@Override
	public void writeVmfSpecific(ValveWriter writer) throws IOException {
		// no properties
		writer.put("", 0);
	}
}

package vmfWriter.entity.solidEntity;

import converter.Skins;
import vmfWriter.Solid;
import vmfWriter.ValveWriter;

import java.io.IOException;

public class AddTfPlayerCondition extends SolidEntity {

	private int condition;
	private int duration;

	public AddTfPlayerCondition(Solid solid, int condition, int duration) {
		super(solid);
		solid.setSkin(Skins.TRIGGER);
		this.condition = condition;
		this. duration = duration;
	}

	@Override
	public String getName() {
		return "trigger_add_tf_player_condition";
	}

	@Override
	public void writeVmfSpecific(ValveWriter writer) throws IOException {
		writer.put("condition", this.condition);
		writer.put("duration", this.duration);
		writer.put("StartDisabled", 0);
		writer.put("spawnflags", 1);
	}
}

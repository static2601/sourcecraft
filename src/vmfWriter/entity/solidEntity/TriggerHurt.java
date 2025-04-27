package vmfWriter.entity.solidEntity;

import converter.Skins;
import vmfWriter.Solid;
import vmfWriter.ValveWriter;

import java.io.IOException;

public class TriggerHurt extends SolidEntity {

	private int damage;
	private int damageType;

	public TriggerHurt(Solid solid, int damage, int damageType) {
		super(solid);
		solid.setSkin(Skins.TRIGGER);
		this.damage = damage;
		this.damageType = damageType;
	}

	@Override
	public String getName() {
		return "trigger_hurt";
	}

	@Override
	public void writeVmfSpecific(ValveWriter writer) throws IOException {
		writer.put("damage", this.damage);
		writer.put("damagecap", this.damage*2);
		writer.put("damagemodel", 0);
		writer.put("damagetype", this.damageType);
		writer.put("nodmgforce", 0);
		writer.put("spawnflags", 1);
		writer.put("StartDisabled", 0);
	}
}

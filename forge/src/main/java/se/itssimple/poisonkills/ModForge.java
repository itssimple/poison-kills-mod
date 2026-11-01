package se.itssimple.poisonkills;
import se.itssimple.poisonkills.util.Reference;
import net.minecraftforge.fml.common.Mod;

@Mod(Reference.MOD_ID)
public class ModForge {

	public ModForge() {
		ModCommon.init();
	}
}
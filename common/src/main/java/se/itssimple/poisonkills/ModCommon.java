package se.itssimple.poisonkills;

import com.mojang.logging.LogUtils;
import org.slf4j.Logger;
import se.itssimple.poisonkills.util.Reference;

public class ModCommon {
	private static final Logger LOGGER = LogUtils.getLogger();
	public static void init() { LOGGER.info("Loading {} (ID: {}), version {}", Reference.NAME, Reference.MOD_ID, Reference.VERSION);	}
}
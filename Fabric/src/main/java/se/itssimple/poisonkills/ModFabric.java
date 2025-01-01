package se.itssimple.poisonkills;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import se.itssimple.poisonkills.events.PoisonTickEvent;

public class ModFabric implements ModInitializer {

	@Override
	public void onInitialize() {
		setGlobalConstants();
		ModCommon.init();

		loadEvents();
	}

	private void loadEvents() {
		ServerTickEvents.START_WORLD_TICK.register(PoisonTickEvent::onPoisonTick);
	}

	private static void setGlobalConstants() {

	}
}
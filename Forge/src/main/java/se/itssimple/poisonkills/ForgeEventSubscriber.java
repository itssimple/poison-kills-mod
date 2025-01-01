package se.itssimple.poisonkills;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import se.itssimple.poisonkills.events.PoisonTickEvent;

@Mod.EventBusSubscriber
public class ForgeEventSubscriber {
	@SubscribeEvent
	public void onWorldTick(TickEvent.LevelTickEvent e) {
		Level level = e.level;

		if(level.isClientSide || !e.phase.equals(TickEvent.Phase.START)) return;

		if(level instanceof ServerLevel serverLevel) {
			PoisonTickEvent.onPoisonTick(serverLevel);
		}
	}
}

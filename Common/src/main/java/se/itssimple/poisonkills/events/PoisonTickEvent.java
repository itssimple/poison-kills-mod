package se.itssimple.poisonkills.events;

import com.mojang.logging.LogUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.entity.EntityTypeTest;
import org.slf4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class PoisonTickEvent {
    private static final Logger LOGGER = LogUtils.getLogger();

    private static final Map<ResourceKey<Level>, Integer> levelTickCounters = new HashMap<>();
    private static final Integer TICK_INTERVAL = 20;

    public static void onPoisonTick(ServerLevel level) {
        ResourceKey<Level> levelKey = level.dimension();
        levelTickCounters.putIfAbsent(levelKey, 0);

        int tickCount = levelTickCounters.get(levelKey) + 1;

        if(tickCount >= TICK_INTERVAL) {
            for(LivingEntity entity : level.getEntities(
                    EntityTypeTest.forClass(LivingEntity.class),
                    (ent) -> ent.hasEffect(MobEffects.POISON) &&
                    ent.getHealth() <= 1.0f)
            ) {
                entity.kill();
                LOGGER.debug("Killed entity {} due to poison", entity);
            }
            tickCount = 0;
        }

        levelTickCounters.put(levelKey, tickCount);
    }
}

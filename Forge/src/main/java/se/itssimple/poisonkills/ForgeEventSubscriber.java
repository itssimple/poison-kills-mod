package se.itssimple.poisonkills;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class ForgeEventSubscriber {
    @SubscribeEvent
    public void applyEventTick(LivingEvent.LivingTickEvent e) {
        LivingEntity livingEntity = e.getEntity();

        if (livingEntity.hasEffect(MobEffects.POISON)) {
            if (livingEntity instanceof ServerPlayer && livingEntity.getHealth() <= 1.0f) {
                livingEntity.hurt(livingEntity.damageSources().genericKill(), 1.0f);
            } else if (livingEntity.getHealth() <= 1.0f) {
                livingEntity.hurt(livingEntity.damageSources().genericKill(), 1.0f);
            }
        }
    }
}

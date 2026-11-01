package se.itssimple.poisonkills.mixin;

import com.mojang.logging.LogUtils;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(targets = "net.minecraft.world.effect.PoisonMobEffect")
public abstract class PoisonMobEffectMixin {
    private static final Logger LOGGER = LogUtils.getLogger();

    @Inject(method = "applyEffectTick", at = @At("HEAD"))
    private void applyEffectTick(LivingEntity livingEntity, int amplifier, CallbackInfoReturnable<Boolean> cir) {
        if (livingEntity instanceof ServerPlayer && livingEntity.getHealth() < 1.0f) {
            LOGGER.debug("Killing player {} due to poison", livingEntity);
            livingEntity.hurt(livingEntity.damageSources().genericKill(), 1.0f);
        } else if (livingEntity.getHealth() <= 1.0f) {
            LOGGER.debug("Killing entity {} due to poison", livingEntity);
            livingEntity.hurt(livingEntity.damageSources().genericKill(), 1.0f);
        }
    }
}

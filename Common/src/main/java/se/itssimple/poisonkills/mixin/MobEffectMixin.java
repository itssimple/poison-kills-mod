package se.itssimple.poisonkills.mixin;

import com.mojang.logging.LogUtils;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = MobEffect.class)
public class MobEffectMixin {
    private static final Logger LOGGER = LogUtils.getLogger();

    @Inject(method = "applyEffectTick(Lnet/minecraft/world/entity/LivingEntity;I)V", at = @At("TAIL"))
    private void applyEffectTick(LivingEntity $$0, int $$1, CallbackInfo ci) {
        MobEffect mobEffect = (MobEffect) (Object) this;

        if (mobEffect == MobEffects.POISON) {
            if ($$0 instanceof ServerPlayer && $$0.getHealth() < 1.0f) {
                LOGGER.debug("Killing player {} due to poison", $$0);
                $$0.hurt($$0.damageSources().genericKill(), 1.0f);
            } else if ($$0.getHealth() <= 1.0f) {
                LOGGER.debug("Killing entity {} due to poison", $$0);
                $$0.hurt($$0.damageSources().genericKill(), 1.0f);
            }
        }
    }
}

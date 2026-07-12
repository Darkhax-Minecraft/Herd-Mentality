package net.darkhax.herdmentality.common.mixin;

import net.darkhax.herdmentality.common.HerdMentality;
import net.darkhax.herdmentality.common.IPanic;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class MixinLivingEntity implements IPanic {

    @Unique
    private boolean herdmentality$shouldPanic = false;

    @Inject(method = "hurtServer", at = @At("RETURN"))
    private void onHurt(ServerLevel level, DamageSource source, float damage, CallbackInfoReturnable<Boolean> cir) {
        if (cir.getReturnValue() && source != null && source.getEntity() instanceof Player player) {
            HerdMentality.onMobHurt((LivingEntity) (Object) this, player);
        }
    }

    @Override
    public void herdmentality$setPanic(boolean shouldPanic) {
        herdmentality$shouldPanic = shouldPanic;
    }

    @Override
    public boolean herdmentality$shouldPanic() {
        return herdmentality$shouldPanic;
    }
}
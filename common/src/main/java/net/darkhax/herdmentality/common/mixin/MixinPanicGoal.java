package net.darkhax.herdmentality.common.mixin;

import net.darkhax.herdmentality.common.IPanic;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PanicGoal.class)
public class MixinPanicGoal {

    @Shadow
    @Final
    protected PathfinderMob mob;

    @Inject(method = "shouldPanic", at = @At("HEAD"), cancellable = true)
    protected void shouldPanic(CallbackInfoReturnable<Boolean> cir) {
        if (this.mob instanceof IPanic panic && panic.herdmentality$shouldPanic()) {
            cir.setReturnValue(true);
            panic.herdmentality$setPanic(false);
        }
    }
}
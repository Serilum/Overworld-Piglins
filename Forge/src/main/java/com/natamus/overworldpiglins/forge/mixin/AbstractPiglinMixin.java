package com.natamus.overworldpiglins.forge.mixin;

import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.monster.piglin.AbstractPiglin;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = AbstractPiglin.class, priority = 1001)
public class AbstractPiglinMixin {
	@Inject(method = "isImmuneToZombification()Z", at = @At(value = "RETURN"), cancellable = true)
	protected void isImmuneToZombification(CallbackInfoReturnable<Boolean> cir) {
		AbstractPiglin abstractPiglin = (AbstractPiglin)(Object)this;
		if (abstractPiglin.hasEffect(MobEffects.WEAKNESS)) {
			abstractPiglin.setTicksFrozen(Integer.MAX_VALUE);
			cir.setReturnValue(false);
		}
		else {
			cir.setReturnValue(true);
		}
	}
}

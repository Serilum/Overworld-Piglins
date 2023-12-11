package com.natamus.overworldpiglins.forge.mixin;

import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.monster.hoglin.Hoglin;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = Hoglin.class, priority = 1001)
public class HoglinMixin {
	@Inject(method = "isImmuneToZombification()Z", at = @At(value = "RETURN"), cancellable = true)
	protected void isImmuneToZombification(CallbackInfoReturnable<Boolean> cir) {
		Hoglin hoglin = (Hoglin)(Object)this;
		if (hoglin.hasEffect(MobEffects.WEAKNESS)) {
			hoglin.setTicksFrozen(Integer.MAX_VALUE);
			cir.setReturnValue(false);
		}
		else {
			cir.setReturnValue(true);
		}
	}
}

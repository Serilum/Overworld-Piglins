package com.natamus.overworldpiglins.mixin;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.hoglin.Hoglin;
import net.minecraft.world.entity.monster.piglin.AbstractPiglin;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = LivingEntity.class, priority = 1001)
public class LivingEntityMixin {
	@Inject(method = "hurt(Lnet/minecraft/world/damagesource/DamageSource;F)Z", at = @At(value = "HEAD"), cancellable = true)
	public void hurt(DamageSource damageSource, float f, CallbackInfoReturnable<Boolean> cir) {
		if (damageSource.is(DamageTypes.FREEZE)) {
			LivingEntity livingEntity = (LivingEntity)(Object)this;
			if (livingEntity.hasEffect(MobEffects.WEAKNESS)) {
				if (livingEntity instanceof AbstractPiglin || livingEntity instanceof Hoglin) {
					cir.setReturnValue(false);
				}
			}
		}
	}
}

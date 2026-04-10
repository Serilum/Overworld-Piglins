package com.natamus.overworldpiglins.mixin;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.hoglin.Hoglin;
import net.minecraft.world.entity.monster.piglin.AbstractPiglin;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = LivingEntity.class, priority = 1001)
public class LivingEntityMixin {
	@Inject(method = "actuallyHurt(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/damagesource/DamageSource;F)V", at = @At(value = "HEAD"), cancellable = true)
	public void hurt(ServerLevel serverLevel, DamageSource damageSource, float f, CallbackInfo ci) {
		if (damageSource.is(DamageTypes.FREEZE)) {
			LivingEntity livingEntity = (LivingEntity)(Object)this;
			if (livingEntity.hasEffect(MobEffects.WEAKNESS)) {
				if (livingEntity instanceof AbstractPiglin || livingEntity instanceof Hoglin) {
					ci.cancel();
				}
			}
		}
	}
}

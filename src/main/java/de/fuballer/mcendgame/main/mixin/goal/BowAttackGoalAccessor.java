package de.fuballer.mcendgame.main.mixin.goal;

import net.minecraft.entity.ai.RangedAttackMob;
import net.minecraft.entity.ai.goal.BowAttackGoal;
import net.minecraft.entity.mob.HostileEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(BowAttackGoal.class)
public interface BowAttackGoalAccessor<T extends HostileEntity & RangedAttackMob> {
    @Accessor("actor")
    T getActor();
}

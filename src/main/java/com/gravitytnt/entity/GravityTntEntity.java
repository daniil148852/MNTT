package com.gravitytnt.entity;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class GravityTntEntity extends PrimedTnt {

    public GravityTntEntity(EntityType<? extends PrimedTnt> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.setFuse(80); // 4 секунды до взрыва
    }

    public GravityTntEntity(Level pLevel, double pX, double pY, double pZ, net.minecraft.world.entity.LivingEntity pOwner) {
        super(pLevel, pX, pY, pZ, pOwner);
        this.setFuse(80);
    }

    @Override
    public void tick() {
        if (!this.level().isClientSide) {
            // Способность: Притяжение сущностей
            AABB area = new AABB(this.getX() - 10, this.getY() - 10, this.getZ() - 10, 
                                 this.getX() + 10, this.getY() + 10, this.getZ() + 10);
            List<Entity> entities = this.level().getEntitiesOfClass(Entity.class, area, e -> e != this);
            
            for (Entity entity : entities) {
                Vec3 direction = new Vec3(this.getX() - entity.getX(), this.getY() - entity.getY(), this.getZ() - entity.getZ());
                double distance = direction.length();
                
                if (distance > 1.5D) { // Не притягивать, если уже рядом
                    Vec3 pull = direction.normalize().scale(0.15D); // Сила притяжения
                    entity.setDeltaMovement(entity.getDeltaMovement().add(pull));
                    entity.hurtMarked = true; // Синхронизация позиции с клиентом
                } else {
                    // Останавливаем сущности вокруг ТНТ
                    entity.setDeltaMovement(0, entity.getDeltaMovement().y * 0.5, 0);
                }
            }

            // Частицы черной дыры
            this.level().addParticle(ParticleTypes.DRAGON_BREATH, this.getX(), this.getY() + 0.5, this.getZ(), 0, 0.1, 0);
            this.level().addParticle(ParticleTypes.PORTAL, this.getX() + (this.random.nextDouble() - 0.5) * 2, this.getY() + 0.5, this.getZ() + (this.random.nextDouble() - 0.5) * 2, 0, 0, 0);
        }
        
        super.tick();
    }

    @Override
    protected void explode() {
        // Взрыв вверх
        this.level().explode(this, this.getX(), this.getY(), this.getZ(), 4.0F, Level.ExplosionInteraction.TNT);
        
        // Дополнительный подброс выживших сущностей
        AABB area = new AABB(this.getX() - 8, this.getY() - 8, this.getZ() - 8, 
                             this.getX() + 8, this.getY() + 8, this.getZ() + 8);
        List<Entity> entities = this.level().getEntitiesOfClass(Entity.class, area, e -> e != this);
        for (Entity entity : entities) {
            entity.setDeltaMovement(entity.getDeltaMovement().add(0, 2.0D, 0)); // Подброс вверх
            entity.hurtMarked = true;
        }
    }
}
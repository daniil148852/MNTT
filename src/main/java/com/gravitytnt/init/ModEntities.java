package com.gravitytnt.init;

import com.gravitytnt.GravityTntMod;
import com.gravitytnt.entity.GravityTntEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, GravityTntMod.MOD_ID);

    public static final RegistryObject<EntityType<GravityTntEntity>> GRAVITY_TNT = ENTITIES.register("gravity_tnt",
            () -> EntityType.Builder.of(GravityTntEntity::new, MobCategory.MISC)
                    .sized(0.98F, 0.98F)
                    .clientTrackingRange(10)   // <-- вместо clientTrackingOffset
                    .build("gravity_tnt"));

    public static void register(IEventBus bus) {
        ENTITIES.register(bus);
    }
}

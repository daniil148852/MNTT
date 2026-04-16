package com.gravitytnt;

import com.gravitytnt.init.ModBlocks;
import com.gravitytnt.init.ModEntities;
import com.gravitytnt.init.ModItems;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(GravityTntMod.MOD_ID)
public class GravityTntMod {
    public static final String MOD_ID = "gravitytnt";

    public GravityTntMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        
        ModBlocks.register(modEventBus);
        ModItems.register(modEventBus);
        ModEntities.register(modEventBus);
    }
}
package com.gravitytnt.init;

import com.gravitytnt.GravityTntMod;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = 
            DeferredRegister.create(ForgeRegistries.ITEMS, GravityTntMod.MOD_ID);

    public static final RegistryObject<Item> GRAVITY_TNT = ITEMS.register("gravity_tnt", 
            () -> new BlockItem(ModBlocks.GRAVITY_TNT.get(), new Item.Properties()));
}
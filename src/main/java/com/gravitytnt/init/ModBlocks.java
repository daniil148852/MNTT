package com.gravitytnt.init;

import com.gravitytnt.GravityTntMod;
import com.gravitytnt.entity.GravityTntEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, GravityTntMod.MOD_ID);

    public static final RegistryObject<Block> GRAVITY_TNT = BLOCKS.register("gravity_tnt",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.TNT)) {
                @Override
                public void onPlace(BlockState pState, Level pLevel, BlockPos pPos, BlockState pOldState, boolean pIsMoving) {
                    if (!pLevel.isClientSide) {
                        GravityTntEntity tnt = new GravityTntEntity(
                                pLevel,
                                pPos.getX() + 0.5D,
                                pPos.getY(),
                                pPos.getZ() + 0.5D,
                                null
                        );
                        pLevel.addFreshEntity(tnt);
                        pLevel.playSound(null, tnt.getX(), tnt.getY(), tnt.getZ(),
                                SoundEvents.TNT_PRIMED, SoundSource.BLOCKS, 1.0F, 0.5F);
                        pLevel.removeBlock(pPos, false);
                    }
                }
            });

    public static void register(IEventBus bus) {
        BLOCKS.register(bus);
    }
}

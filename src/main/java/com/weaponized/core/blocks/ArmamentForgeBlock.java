package com.weaponized.core.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

public class ArmamentForgeBlock extends BlockWithEntity {
    protected ArmamentForgeBlock(Settings settings) {
        super(settings);
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return null;
    }
}

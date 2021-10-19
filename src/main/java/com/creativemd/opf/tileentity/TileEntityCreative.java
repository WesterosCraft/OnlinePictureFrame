package com.creativemd.opf.tileentity;

import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

public abstract class TileEntityCreative extends TileEntity {
    
    public TileEntityCreative(TileEntityType<?> p_i48289_1_) {
		super(p_i48289_1_);
	}
        
    public void getDescriptionNBT(CompoundNBT nbt) {
        
    }
    
    @OnlyIn(Dist.CLIENT)
    public void receiveUpdatePacket(CompoundNBT nbt) {
        
    }
    
    @Override
    public CompoundNBT getUpdateTag() {
        return save(new CompoundNBT());
    }
    
    @Override
    public SUpdateTileEntityPacket getUpdatePacket() {
        CompoundNBT nbt = new CompoundNBT();
        getDescriptionNBT(nbt);
        return new SUpdateTileEntityPacket(worldPosition, -1, nbt);
    }
    
    public double getDistance(BlockPos coord) {
    	double dx = worldPosition.getX() - coord.getX();
    	double dy = worldPosition.getY() - coord.getY();
    	double dz = worldPosition.getZ() - coord.getZ();
        return Math.sqrt((dx*dx)+(dy*dy)+(dz*dz));
    }
    
    @OnlyIn(Dist.CLIENT)
    public void updateRender() {
        //level.markBlockRangeForRenderUpdate(worldPosition, worldPosition);
    }
    
    public void updateBlock() {
        if (!level.isClientSide) {
            BlockState state = level.getBlockState(worldPosition);
            level.notifyBlockUpdate(worldPosition, state, state, 3);
            level.markChunkDirty(getPos(), this);
        }
    }
    
    @Override
    @OnlyIn(Dist.CLIENT)
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
        receiveUpdatePacket(pkt.getTag());
        updateRender();
    }
    
}
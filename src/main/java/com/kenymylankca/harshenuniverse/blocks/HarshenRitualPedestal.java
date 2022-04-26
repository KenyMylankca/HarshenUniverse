package com.kenymylankca.harshenuniverse.blocks;

import java.util.Random;

import kenymylankca.harshenuniverse.HarshenSounds;
import kenymylankca.harshenuniverse.base.BaseBlockHarshenSingleInventory;
import kenymylankca.harshenuniverse.base.BaseTileEntityHarshenSingleItemInventory;
import kenymylankca.harshenuniverse.tileentity.TileEntityHarshenRitualPedestal;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class HarshenRitualPedestal extends BaseBlockHarshenSingleInventory
{
	public HarshenRitualPedestal() {
		super(Material.ROCK);
		setRegistryName("harshen_ritual_pedestal");
		setUnlocalizedName("harshen_ritual_pedestal");
		setHardness(5.0F);
		setResistance(5.0F);
	}
	
	@Override
	public BaseTileEntityHarshenSingleItemInventory getTile() {
		return new TileEntityHarshenRitualPedestal();
	}
	
	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
		return new AxisAlignedBB(0.25f, 0f, 0.25f, 0.50f, 0.875f, 0.50f);
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return new AxisAlignedBB(0.25f, 0f, 0.25f, 0.75f, 0.875f, 0.75f);
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		return ((TileEntityHarshenRitualPedestal)worldIn.getTileEntity(pos)).isActive() ? true : super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
	}
	
	@SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand)
    {
		if (worldIn.getBlockState(pos.down()).getBlock() == Blocks.GOLD_BLOCK)
			if (rand.nextInt(150) == 0)
			{
				float multiplier = rand.nextFloat() + 0.5F;
				worldIn.playSound(pos.getX(), pos.getY(), pos.getZ(), HarshenSounds.LIGHTNING_SPELLING, SoundCategory.BLOCKS, rand.nextFloat() * multiplier, rand.nextFloat() * multiplier + 0.2F, true);
			}
		
        for (int i = 0; i < 3; ++i)
        {
            double cX = (double)((float)pos.getX() + rand.nextFloat() - 0.2F);
            double cY = (double)((float)pos.getY() + rand.nextFloat() - 0.2F);
            double cZ = (double)((float)pos.getZ() + rand.nextFloat() - 0.2F);
            double sX = ((double)rand.nextFloat() - 0.6D) * 0.4D;
            double sY = ((double)rand.nextFloat() - 0.6D) * 0.4D;
            double sZ = ((double)rand.nextFloat() - 0.6D) * 0.4D;

            if(worldIn.getBlockState(pos.down()).getBlock() == Blocks.GOLD_BLOCK)
            	worldIn.spawnParticle(EnumParticleTypes.TOWN_AURA, cX, cY - 0.25D, cZ, sX, sY, sZ);
            worldIn.spawnParticle(EnumParticleTypes.PORTAL, cX, cY, cZ, sX, sY, sZ);
        }
    }
}
package powercrystals.minefactoryreloaded.block;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import powercrystals.minefactoryreloaded.MineFactoryReloadedCore;
import powercrystals.minefactoryreloaded.gui.MFRCreativeTab;

public class BlockRubberLeaves extends BlockLeaves
{
	private Icon _iconOpaque;
	private Icon _iconTransparent;
	
	public BlockRubberLeaves(int id)
	{
		super(id);
		setHardness(0.2F);
		setLightOpacity(1);
		setStepSound(soundGrassFootstep);
		setUnlocalizedName("mfr.rubberwood.leaves");
		setCreativeTab(MFRCreativeTab.tab);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void func_94332_a(IconRegister ir)
	{
		_iconOpaque = ir.func_94245_a("powercrystals/minefactoryreloaded/" + getUnlocalizedName() + ".opaque");
		_iconTransparent = ir.func_94245_a("powercrystals/minefactoryreloaded/" + getUnlocalizedName() + ".transparent");
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public Icon getBlockTexture(IBlockAccess world, int x, int y, int z, int side)
	{
		return Block.leaves.graphicsLevel ? _iconOpaque : _iconTransparent;
	}
	
	@Override
	public boolean isOpaqueCube()
	{
		return !Block.leaves.graphicsLevel;
	}
	
	@Override
	public int idDropped(int par1, Random par2Random, int par3)
	{
		return MineFactoryReloadedCore.rubberSaplingBlock.blockID;
	}
	
	@Override
	public void dropBlockAsItemWithChance(World par1World, int par2, int par3, int par4, int par5, float par6, int par7)
	{
		if(!par1World.isRemote)
		{
			ArrayList<ItemStack> items = getBlockDropped(par1World, par2, par3, par4, par5, par7);

			for (ItemStack item : items)
			{
				if (par1World.rand.nextFloat() <= par6)
				{
					this.dropBlockAsItem_do(par1World, par2, par3, par4, item);
				}
			}
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockAccess iba, int x, int y, int z, int side)
	{
		return Block.leaves.graphicsLevel ? true : super.shouldSideBeRendered(iba, x, y, z, side);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void getSubBlocks(int blockId, CreativeTabs creativeTab, List subTypes)
	{
		subTypes.add(new ItemStack(blockId, 1, 0));
	}
}
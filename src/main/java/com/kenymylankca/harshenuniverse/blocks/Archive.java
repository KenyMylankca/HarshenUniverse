package com.kenymylankca.harshenuniverse.blocks;

import com.kenymylankca.harshenuniverse.HarshenUniverse;
import com.kenymylankca.harshenuniverse.base.HarshenFacedBlock;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.List;

public class Archive extends HarshenFacedBlock
{
    public static final String registryName = "archive";
    private static final VoxelShape hitbox = Shapes.box(0.1, 0, 0.1, 0.9, 1, 0.9);

    public Archive() {
        super(BlockBehaviour.Properties.of()
                .mapColor(MapColor.TERRACOTTA_BROWN)
                .sound(SoundType.CHISELED_BOOKSHELF)
                .strength(1.5F, 2)
                .requiresCorrectToolForDrops()
        );
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.translatable("tooltip." + HarshenUniverse.MOD_ID + ".archive").withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.ITALIC));
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return hitbox;
    }

    @Override
    protected boolean isCollisionShapeFullBlock(BlockState state, BlockGetter level, BlockPos pos) {
        return false;
    }
}
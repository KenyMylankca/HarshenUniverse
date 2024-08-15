package com.kenymylankca.harshenuniverse.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SnowyDirtBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class AkzeniaMushroom extends Block
{
    public static final String registryName = "akzenia_mushroom";
    private static final VoxelShape hitbox = Shapes.box(0.2, 0, 0.2, 0.8, 0.2, 0.8);

    public AkzeniaMushroom()
    {
        super(BlockBehaviour.Properties.of()
                .mapColor(MapColor.COLOR_PURPLE)
                .noCollission()
                .randomTicks()
                .sound(SoundType.WOOL)
                .strength(1, 2)
                .replaceable()
                .lightLevel(state -> 4)
        );
    }

    @Override
    protected boolean isCollisionShapeFullBlock(BlockState state, BlockGetter level, BlockPos pos) {
        return false;
    }

    @Override
    protected BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
        if (!(level.getBlockState(pos.below()).getBlock() instanceof SnowyDirtBlock))
            level.destroyBlock(pos, true);
        return super.updateShape(state, direction, neighborState, level, pos, neighborPos);
    }

    @Override
    protected void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        Vec3 vec3 = new Vec3(0.95, 0.95, 0.95);
        entity.makeStuckInBlock(state, vec3);
        level.addParticle(ParticleTypes.PORTAL, false,
                pos.getX() + RandomSource.create().nextFloat(),
                pos.getY() + RandomSource.create().nextFloat(),
                pos.getZ() + RandomSource.create().nextFloat(),
                3*RandomSource.create().nextFloat(),  3*RandomSource.create().nextFloat(), 3*RandomSource.create().nextFloat());
        super.entityInside(state, level, pos, entity);
    }

    @Override
    protected void attack(BlockState state, Level level, BlockPos pos, Player player) {
        for(int i=0; i<10; i++)
        {
            float r = RandomSource.create().nextFloat();
            level.addParticle(ParticleTypes.PORTAL, false, pos.getX() + r, pos.getY() + r, pos.getZ() + r, 3*r,  3*r, 3*r);
        }
        super.attack(state, level, pos, player);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return hitbox;
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random)
    {
        switch (random.nextInt(111))
        {
            case 11:
                if(level.getBlockState(pos.east().below()).isCollisionShapeFullBlock(level, pos) && level.getBlockState(pos.above()).isAir())
                    level.setBlockAndUpdate(pos.east(), this.defaultBlockState());
            case 22:
                if(level.getBlockState(pos.west().below()).isCollisionShapeFullBlock(level, pos) && level.getBlockState(pos.above()).isAir())
                    level.setBlockAndUpdate(pos.west(), this.defaultBlockState());
            case 33:
                if(level.getBlockState(pos.north().below()).isCollisionShapeFullBlock(level, pos) && level.getBlockState(pos.above()).isAir())
                    level.setBlockAndUpdate(pos.north(), this.defaultBlockState());
            case 44:
                if(level.getBlockState(pos.south().below()).isCollisionShapeFullBlock(level, pos) && level.getBlockState(pos.above()).isAir())
                    level.setBlockAndUpdate(pos.south(), this.defaultBlockState());
            default: break;
        }
        super.randomTick(state, level, pos, random);
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos)
    {
        return level.getBlockState(pos.below()).getBlock() instanceof SnowyDirtBlock;
    }
}
package net.rk.longroads.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.phys.HitResult;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.rk.longroads.ThingamajigsLongRoads;
import net.rk.longroads.block.custom.*;
import net.rk.longroads.entity.blockentity.custom.BlueRoadwaySign;
import net.rk.longroads.entity.blockentity.custom.BrownRoadwaySign;
import net.rk.longroads.entity.blockentity.custom.GreenRoadwaySign;
import net.rk.longroads.entity.blockentity.custom.RedRoadwaySign;
import net.rk.longroads.item.TLRItems;
import net.rk.longroads.util.Utilities;

import java.util.function.Supplier;
import java.util.function.ToIntFunction;

@SuppressWarnings("deprecated")
public class TLRBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(ThingamajigsLongRoads.MODID);

    // asphalt
    public static final DeferredBlock<Block> ASPHALT = register("asphalt",
            () -> new Asphalt(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(2f).requiresCorrectToolForDrops().friction(0.4F).sound(SoundType.TUFF)));
    public static final DeferredBlock<Block> ASPHALT_OK = register("ok_asphalt",
            () -> new Asphalt(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(1.9f).requiresCorrectToolForDrops().friction(0.45F).sound(SoundType.TUFF)));
    public static final DeferredBlock<Block> ASPHALT_MEDIOCRE = register("mediocre_asphalt",
            () -> new Asphalt(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(1.5f).requiresCorrectToolForDrops().friction(0.5F).sound(SoundType.TUFF)));
    public static final DeferredBlock<Block> ASPHALT_OLD = register("old_asphalt",
            () -> new Asphalt(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(1.2f).requiresCorrectToolForDrops().sound(SoundType.TUFF)));

    // sidewalk
    public static final DeferredBlock<Block> SIDEWALK = register("sidewalk",
            () -> new Sidewalk(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(2f).requiresCorrectToolForDrops().sound(SoundType.TUFF)));
    public static final DeferredBlock<Block> SIDEWALK_CRACKED = register("cracked_sidewalk",
            () -> new Sidewalk(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(2f).requiresCorrectToolForDrops().sound(SoundType.TUFF)));
    public static final DeferredBlock<Block> SIDEWALK_BLOCKED = register("blocked_sidewalk",
            () -> new Sidewalk(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(2f).requiresCorrectToolForDrops().sound(SoundType.TUFF)));
    public static final DeferredBlock<Block> SIDEWALK_SECTIONED = register("sectioned_sidewalk",
            () -> new Sidewalk(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(2f).requiresCorrectToolForDrops().sound(SoundType.TUFF)));

    public static final DeferredBlock<Block> SIDEWALK_SLAB = register("sidewalk_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).requiresCorrectToolForDrops().strength(1F)));
    public static final DeferredBlock<Block> CRACKED_SIDEWALK_SLAB = register("cracked_sidewalk_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).requiresCorrectToolForDrops().strength(1F)));
    public static final DeferredBlock<Block> SECTIONED_SIDEWALK_SLAB = register("sectioned_sidewalk_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).requiresCorrectToolForDrops().strength(1F)));
    public static final DeferredBlock<Block> BLOCKED_SIDEWALK_SLAB = register("blocked_sidewalk_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).requiresCorrectToolForDrops().strength(1F)));


    // marking
    public static final DeferredBlock<Block> WHITE_ROAD_MARKING = registerBlockWithoutItem("white_road_marking",
            () -> new WhiteRoadMarking(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));
    public static final DeferredBlock<Block> BLUE_ROAD_MARKING = registerBlockWithoutItem("blue_road_marking",
            () -> new BlueRoadMarking(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));
    public static final DeferredBlock<Block> YELLOW_ROAD_MARKING = registerBlockWithoutItem("yellow_road_marking",
            () -> new YellowRoadMarking(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));

    public static final DeferredBlock<Block> DOUBLE_WHITE_ASPHALT = register("double_white_asphalt",
            () -> new MarkedAsphalt(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> DOUBLE_YELLOW_ASPHALT = register("double_yellow_asphalt",
            () -> new MarkedAsphalt(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> DOUBLE_CORNER_WHITE_ASPHALT = register("double_corner_white_asphalt",
            () -> new MarkedAsphalt(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> DOUBLE_CORNER_YELLOW_ASPHALT = register("double_corner_yellow_asphalt",
            () -> new MarkedAsphalt(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).requiresCorrectToolForDrops()));


    public static final DeferredBlock<Block> WHITE_PARKING_ASPHALT = register("white_parking_asphalt",
            () -> new MarkedAsphalt(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> YELLOW_PARKING_ASPHALT = register("yellow_parking_asphalt",
            () -> new MarkedAsphalt(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> BLUE_PARKING_ASPHALT = register("blue_parking_asphalt",
            () -> new MarkedAsphalt(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).requiresCorrectToolForDrops()));


    public static final DeferredBlock<Block> ASPHALT_SLAB = register("asphalt_slab",
            () -> new AsphaltSlab(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)
                    .strength(2f)
                    .requiresCorrectToolForDrops()
                    .friction(0.4f)
                    .sound(SoundType.TUFF)));
    public static final DeferredBlock<Block> ASPHALT_OK_SLAB = register("ok_asphalt_slab",
            () -> new AsphaltSlab(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)
                    .strength(1.9f)
                    .requiresCorrectToolForDrops()
                    .friction(0.45f)
                    .sound(SoundType.TUFF)));
    public static final DeferredBlock<Block> ASPHALT_MEDIOCRE_SLAB = register("mediocre_asphalt_slab",
            () -> new AsphaltSlab(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)
                    .strength(1.5f)
                    .requiresCorrectToolForDrops()
                    .friction(0.5f)
                    .sound(SoundType.TUFF)));
    public static final DeferredBlock<Block> ASPHALT_OLD_SLAB = register("old_asphalt_slab",
            () -> new AsphaltSlab(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)
                    .strength(1.2f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.TUFF)));


    public static final DeferredBlock<Block> WHITE_PARKING_ASPHALT_SLAB = register("white_parking_asphalt_slab",
            () -> new MarkedAsphaltSlab(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)
                    .strength(2f)
                    .requiresCorrectToolForDrops()
                    .friction(0.4f)
                    .sound(SoundType.TUFF)));
    public static final DeferredBlock<Block> WHITE_PARKING_OK_ASPHALT_SLAB = register("white_parking_ok_asphalt_slab",
            () -> new MarkedAsphaltSlab(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)
                    .strength(2f)
                    .requiresCorrectToolForDrops()
                    .friction(0.45f)
                    .sound(SoundType.TUFF)));
    public static final DeferredBlock<Block> WHITE_PARKING_MEDIOCRE_ASPHALT_SLAB = register("white_parking_mediocre_asphalt_slab",
            () -> new MarkedAsphaltSlab(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)
                    .strength(2f)
                    .requiresCorrectToolForDrops()
                    .friction(0.5f)
                    .sound(SoundType.TUFF)));
    public static final DeferredBlock<Block> WHITE_PARKING_OLD_ASPHALT_SLAB = register("white_parking_old_asphalt_slab",
            () -> new MarkedAsphaltSlab(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)
                    .strength(2f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.TUFF)));

    public static final DeferredBlock<Block> WHITE_DT_ASPHALT_SLAB = register("white_dt_asphalt_slab",
            () -> new MarkedAsphaltSlab(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)
                    .strength(2f)
                    .requiresCorrectToolForDrops()
                    .friction(0.4f)
                    .sound(SoundType.TUFF)));
    public static final DeferredBlock<Block> WHITE_DT_OK_ASPHALT_SLAB = register("white_dt_ok_asphalt_slab",
            () -> new MarkedAsphaltSlab(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)
                    .strength(2f)
                    .requiresCorrectToolForDrops()
                    .friction(0.45f)
                    .sound(SoundType.TUFF)));
    public static final DeferredBlock<Block> WHITE_DT_MEDIOCRE_ASPHALT_SLAB = register("white_dt_mediocre_asphalt_slab",
            () -> new MarkedAsphaltSlab(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)
                    .strength(2f)
                    .requiresCorrectToolForDrops()
                    .friction(0.5f)
                    .sound(SoundType.TUFF)));
    public static final DeferredBlock<Block> WHITE_DT_OLD_ASPHALT_SLAB = register("white_dt_old_asphalt_slab",
            () -> new MarkedAsphaltSlab(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)
                    .strength(2f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.TUFF)));

    public static final DeferredBlock<Block> BLUE_PARKING_ASPHALT_SLAB = register("blue_parking_asphalt_slab",
            () -> new MarkedAsphaltSlab(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)
                    .strength(2f)
                    .requiresCorrectToolForDrops()
                    .friction(0.4f)
                    .sound(SoundType.TUFF)));
    public static final DeferredBlock<Block> BLUE_PARKING_OK_ASPHALT_SLAB = register("blue_parking_ok_asphalt_slab",
            () -> new MarkedAsphaltSlab(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)
                    .strength(2f)
                    .requiresCorrectToolForDrops()
                    .friction(0.45f)
                    .sound(SoundType.TUFF)));
    public static final DeferredBlock<Block> BLUE_PARKING_MEDIOCRE_ASPHALT_SLAB = register("blue_parking_mediocre_asphalt_slab",
            () -> new MarkedAsphaltSlab(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)
                    .strength(2f)
                    .requiresCorrectToolForDrops()
                    .friction(0.5f)
                    .sound(SoundType.TUFF)));
    public static final DeferredBlock<Block> BLUE_PARKING_OLD_ASPHALT_SLAB = register("blue_parking_old_asphalt_slab",
            () -> new MarkedAsphaltSlab(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)
                    .strength(2f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.TUFF)));

    public static final DeferredBlock<Block> YELLOW_DT_ASPHALT_SLAB = register("yellow_dt_asphalt_slab",
            () -> new MarkedAsphaltSlab(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)
                    .strength(2f)
                    .requiresCorrectToolForDrops()
                    .friction(0.4f)
                    .sound(SoundType.TUFF)));
    public static final DeferredBlock<Block> YELLOW_DT_OK_ASPHALT_SLAB = register("yellow_dt_ok_asphalt_slab",
            () -> new MarkedAsphaltSlab(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)
                    .strength(2f)
                    .requiresCorrectToolForDrops()
                    .friction(0.45f)
                    .sound(SoundType.TUFF)));
    public static final DeferredBlock<Block> YELLOW_DT_MEDIOCRE_ASPHALT_SLAB = register("yellow_dt_mediocre_asphalt_slab",
            () -> new MarkedAsphaltSlab(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)
                    .strength(2f)
                    .requiresCorrectToolForDrops()
                    .friction(0.5f)
                    .sound(SoundType.TUFF)));
    public static final DeferredBlock<Block> YELLOW_DT_OLD_ASPHALT_SLAB = register("yellow_dt_old_asphalt_slab",
            () -> new MarkedAsphaltSlab(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)
                    .strength(2f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.TUFF)));

    public static final DeferredBlock<Block> YELLOW_PARKING_ASPHALT_SLAB = register("yellow_parking_asphalt_slab",
            () -> new MarkedAsphaltSlab(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)
                    .strength(2f)
                    .requiresCorrectToolForDrops()
                    .friction(0.4f)
                    .sound(SoundType.TUFF)));
    public static final DeferredBlock<Block> YELLOW_PARKING_OK_ASPHALT_SLAB = register("yellow_parking_ok_asphalt_slab",
            () -> new MarkedAsphaltSlab(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)
                    .strength(2f)
                    .requiresCorrectToolForDrops()
                    .friction(0.45f)
                    .sound(SoundType.TUFF)));
    public static final DeferredBlock<Block> YELLOW_PARKING_MEDIOCRE_ASPHALT_SLAB = register("yellow_parking_mediocre_asphalt_slab",
            () -> new MarkedAsphaltSlab(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)
                    .strength(2f)
                    .requiresCorrectToolForDrops()
                    .friction(0.5f)
                    .sound(SoundType.TUFF)));
    public static final DeferredBlock<Block> YELLOW_PARKING_OLD_ASPHALT_SLAB = register("yellow_parking_old_asphalt_slab",
            () -> new MarkedAsphaltSlab(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)
                    .strength(2f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.TUFF)));

    public static final DeferredBlock<Block> YELLOW_D_ASPHALT_SLAB = register("yellow_d_asphalt_slab",
            () -> new MarkedAsphaltSlab(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)
                    .strength(2f)
                    .requiresCorrectToolForDrops()
                    .friction(0.4f)
                    .sound(SoundType.TUFF)));
    public static final DeferredBlock<Block> YELLOW_D_OK_ASPHALT_SLAB = register("yellow_d_ok_asphalt_slab",
            () -> new MarkedAsphaltSlab(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)
                    .strength(2f)
                    .requiresCorrectToolForDrops()
                    .friction(0.45f)
                    .sound(SoundType.TUFF)));
    public static final DeferredBlock<Block> YELLOW_D_MEDIOCRE_ASPHALT_SLAB = register("yellow_d_mediocre_asphalt_slab",
            () -> new MarkedAsphaltSlab(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)
                    .strength(2f)
                    .requiresCorrectToolForDrops()
                    .friction(0.5f)
                    .sound(SoundType.TUFF)));
    public static final DeferredBlock<Block> YELLOW_D_OLD_ASPHALT_SLAB = register("yellow_d_old_asphalt_slab",
            () -> new MarkedAsphaltSlab(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)
                    .strength(2f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.TUFF)));

    public static final DeferredBlock<Block> WHITE_D_ASPHALT_SLAB = register("white_d_asphalt_slab",
            () -> new MarkedAsphaltSlab(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)
                    .strength(2f)
                    .requiresCorrectToolForDrops()
                    .friction(0.4f)
                    .sound(SoundType.TUFF)));
    public static final DeferredBlock<Block> WHITE_D_OK_ASPHALT_SLAB = register("white_d_ok_asphalt_slab",
            () -> new MarkedAsphaltSlab(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)
                    .strength(2f)
                    .requiresCorrectToolForDrops()
                    .friction(0.45f)
                    .sound(SoundType.TUFF)));
    public static final DeferredBlock<Block> WHITE_D_MEDIOCRE_ASPHALT_SLAB = register("white_d_mediocre_asphalt_slab",
            () -> new MarkedAsphaltSlab(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)
                    .strength(2f)
                    .requiresCorrectToolForDrops()
                    .friction(0.5f)
                    .sound(SoundType.TUFF)));
    public static final DeferredBlock<Block> WHITE_D_OLD_ASPHALT_SLAB = register("white_d_old_asphalt_slab",
            () -> new MarkedAsphaltSlab(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)
                    .strength(2f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.TUFF)));






    public static final DeferredBlock<Block> GREEN_ROADWAY_STANDING_SIGN = registerBlockWithoutItem(
            "green_roadway_standing_sign",
            () -> new RoadwayStandingSignBlock(
                    Utilities.GREEN_ROADWAY_WOOD,
                    BlockBehaviour.Properties.of()
                            .mapColor(Blocks.GREEN_CONCRETE.defaultMapColor())
                            .forceSolidOn()
                            .instrument(NoteBlockInstrument.BANJO)
                            .noCollission()
                            .strength(1.0F)
            ){
                @Override
                public ItemStack getCloneItemStack(BlockState state, HitResult target, LevelReader level, BlockPos pos, Player player) {
                    return TLRItems.GREEN_ROADWAY_SIGN_ITEM.toStack();
                }

                @Override
                protected RenderShape getRenderShape(BlockState state) {
                    return RenderShape.ENTITYBLOCK_ANIMATED;
                }

                @Override
                public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
                    return new GreenRoadwaySign(pos,state);
                }
            });
    public static final DeferredBlock<Block> GREEN_ROADWAY_WALL_SIGN = registerBlockWithoutItem(
            "green_roadway_wall_sign",
            () -> new RoadwayWallSignBlock(
                    Utilities.GREEN_ROADWAY_WOOD,
                    BlockBehaviour.Properties.of()
                            .mapColor(Blocks.GREEN_CONCRETE.defaultMapColor())
                            .forceSolidOn()
                            .instrument(NoteBlockInstrument.BANJO)
                            .noCollission()
                            .strength(1.0F)
            ){
                @Override
                public ItemStack getCloneItemStack(BlockState state, HitResult target, LevelReader level, BlockPos pos, Player player) {
                    return TLRItems.GREEN_ROADWAY_SIGN_ITEM.toStack();
                }

                @Override
                protected RenderShape getRenderShape(BlockState state) {
                    return RenderShape.ENTITYBLOCK_ANIMATED;
                }

                @Override
                public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
                    return new GreenRoadwaySign(pos,state);
                }
            });

    public static final DeferredBlock<Block> RED_ROADWAY_STANDING_SIGN = registerBlockWithoutItem(
            "red_roadway_standing_sign",
            () -> new RoadwayStandingSignBlock(
                    Utilities.RED_ROADWAY_WOOD,
                    BlockBehaviour.Properties.of()
                            .mapColor(Blocks.RED_CONCRETE.defaultMapColor())
                            .forceSolidOn()
                            .instrument(NoteBlockInstrument.CHIME)
                            .noCollission()
                            .strength(1.0F)
            ){
                @Override
                public ItemStack getCloneItemStack(BlockState state, HitResult target, LevelReader level, BlockPos pos, Player player) {
                    return TLRItems.RED_ROADWAY_SIGN_ITEM.toStack();
                }

                @Override
                protected RenderShape getRenderShape(BlockState state) {
                    return RenderShape.ENTITYBLOCK_ANIMATED;
                }

                @Override
                public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
                    return new RedRoadwaySign(pos,state);
                }
            });
    public static final DeferredBlock<Block> RED_ROADWAY_WALL_SIGN = registerBlockWithoutItem(
            "red_roadway_wall_sign",
            () -> new RoadwayWallSignBlock(
                    Utilities.RED_ROADWAY_WOOD,
                    BlockBehaviour.Properties.of()
                            .mapColor(Blocks.RED_CONCRETE.defaultMapColor())
                            .forceSolidOn()
                            .instrument(NoteBlockInstrument.CHIME)
                            .noCollission()
                            .strength(1.0F)
            ){
                @Override
                public ItemStack getCloneItemStack(BlockState state, HitResult target, LevelReader level, BlockPos pos, Player player) {
                    return TLRItems.RED_ROADWAY_SIGN_ITEM.toStack();
                }

                @Override
                protected RenderShape getRenderShape(BlockState state) {
                    return RenderShape.ENTITYBLOCK_ANIMATED;
                }

                @Override
                public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
                    return new RedRoadwaySign(pos,state);
                }
            });

    public static final DeferredBlock<Block> BLUE_ROADWAY_STANDING_SIGN = registerBlockWithoutItem(
            "blue_roadway_standing_sign",
            () -> new RoadwayStandingSignBlock(
                    Utilities.BLUE_ROADWAY_WOOD,
                    BlockBehaviour.Properties.of()
                            .mapColor(Blocks.BLUE_CONCRETE.defaultMapColor())
                            .forceSolidOn()
                            .instrument(NoteBlockInstrument.XYLOPHONE)
                            .noCollission()
                            .strength(1.0F)
            ){
                @Override
                public ItemStack getCloneItemStack(BlockState state, HitResult target, LevelReader level, BlockPos pos, Player player) {
                    return TLRItems.BLUE_ROADWAY_SIGN_ITEM.toStack();
                }

                @Override
                protected RenderShape getRenderShape(BlockState state) {
                    return RenderShape.ENTITYBLOCK_ANIMATED;
                }

                @Override
                public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
                    return new BlueRoadwaySign(pos,state);
                }
            });
    public static final DeferredBlock<Block> BLUE_ROADWAY_WALL_SIGN = registerBlockWithoutItem(
            "blue_roadway_wall_sign",
            () -> new RoadwayWallSignBlock(
                    Utilities.BLUE_ROADWAY_WOOD,
                    BlockBehaviour.Properties.of()
                            .mapColor(Blocks.BLUE_CONCRETE.defaultMapColor())
                            .forceSolidOn()
                            .instrument(NoteBlockInstrument.XYLOPHONE)
                            .noCollission()
                            .strength(1.0F)
            ){
                @Override
                public ItemStack getCloneItemStack(BlockState state, HitResult target, LevelReader level, BlockPos pos, Player player) {
                    return TLRItems.BLUE_ROADWAY_SIGN_ITEM.toStack();
                }

                @Override
                protected RenderShape getRenderShape(BlockState state) {
                    return RenderShape.ENTITYBLOCK_ANIMATED;
                }

                @Override
                public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
                    return new BlueRoadwaySign(pos,state);
                }
            });

    public static final DeferredBlock<Block> BROWN_ROADWAY_STANDING_SIGN = registerBlockWithoutItem(
            "brown_roadway_standing_sign",
            () -> new RoadwayStandingSignBlock(
                    Utilities.BROWN_ROADWAY_WOOD,
                    BlockBehaviour.Properties.of()
                            .mapColor(Blocks.BROWN_CONCRETE.defaultMapColor())
                            .forceSolidOn()
                            .instrument(NoteBlockInstrument.COW_BELL)
                            .noCollission()
                            .strength(1.0F)
            ){
                @Override
                public ItemStack getCloneItemStack(BlockState state, HitResult target, LevelReader level, BlockPos pos, Player player) {
                    return TLRItems.BROWN_ROADWAY_SIGN_ITEM.toStack();
                }

                @Override
                protected RenderShape getRenderShape(BlockState state) {
                    return RenderShape.ENTITYBLOCK_ANIMATED;
                }

                @Override
                public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
                    return new BrownRoadwaySign(pos,state);
                }
            });
    public static final DeferredBlock<Block> BROWN_ROADWAY_WALL_SIGN = registerBlockWithoutItem(
            "brown_roadway_wall_sign",
            () -> new RoadwayWallSignBlock(
                    Utilities.BROWN_ROADWAY_WOOD,
                    BlockBehaviour.Properties.of()
                            .mapColor(Blocks.BROWN_CONCRETE.defaultMapColor())
                            .forceSolidOn()
                            .instrument(NoteBlockInstrument.COW_BELL)
                            .noCollission()
                            .strength(1.0F)
            ){
                @Override
                public ItemStack getCloneItemStack(BlockState state, HitResult target, LevelReader level, BlockPos pos, Player player) {
                    return TLRItems.BROWN_ROADWAY_SIGN_ITEM.toStack();
                }

                @Override
                protected RenderShape getRenderShape(BlockState state) {
                    return RenderShape.ENTITYBLOCK_ANIMATED;
                }

                @Override
                public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
                    return new BrownRoadwaySign(pos,state);
                }
            });


    public static final DeferredBlock<Block> GREEN_HANGING_SIGN = registerBlockWithoutItem("green_hanging_sign",
            () -> new GreenHangingSignBlock(Utilities.GREEN_ROADWAY_WOOD,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_HANGING_SIGN)){
                @Override
                public ItemStack getCloneItemStack(BlockState state, HitResult target, LevelReader level, BlockPos pos, Player player) {
                    return TLRItems.GREEN_HANGING_ROADWAY_SIGN_ITEM.toStack();
                }

                @Override
                protected RenderShape getRenderShape(BlockState state) {
                    return RenderShape.ENTITYBLOCK_ANIMATED;
                }
            });
    public static final DeferredBlock<Block> GREEN_WALL_HANGING_SIGN = registerBlockWithoutItem("green_wall_hanging_sign",
            () -> new GreenHangingWallSignBlock(Utilities.GREEN_ROADWAY_WOOD,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_WALL_HANGING_SIGN)){
                @Override
                public ItemStack getCloneItemStack(BlockState state, HitResult target, LevelReader level, BlockPos pos, Player player) {
                    return TLRItems.GREEN_HANGING_ROADWAY_SIGN_ITEM.toStack();
                }

                @Override
                protected RenderShape getRenderShape(BlockState state) {
                    return RenderShape.ENTITYBLOCK_ANIMATED;
                }
            });

    public static final DeferredBlock<Block> VERTICAL_REDSTONE_SIDEWALK = register("vertical_redstone_sidewalk",
            () -> new VerticalBlockRedstone(BlockBehaviour.Properties.of()));

    public static final DeferredBlock<Block> ROAD_SIGN = registerBlockWithoutItem("road_sign",
            () -> new DynamicRoadSignBlock(BlockBehaviour.Properties.of()));

    public static final DeferredBlock<Block> STRAIGHT_ROAD_SIGN = registerBlockWithoutItem("straight_road_sign",
            () -> new DynamicRoadSignBlock(BlockBehaviour.Properties.of()));

    public static final DeferredBlock<Block> THREE_WAY_ROAD_SIGN = registerBlockWithoutItem("three_way_road_sign",
            () -> new DynamicRoadSignBlock(BlockBehaviour.Properties.of()));

    public static final DeferredBlock<Block> VERTICAL_REDSTONE_ROAD_SIGN = registerBlockWithoutItem("vertical_redstone_road_sign",
            () -> new DynamicVerticalRedstoneSignBlock(BlockBehaviour.Properties.of()));

    public static final DeferredBlock<Block> ASPHALT_LAYER = register("asphalt_layer",
            () -> new AsphaltLayer(BlockBehaviour.Properties.of().friction(0.4F).strength(2f)));
    public static final DeferredBlock<Block> OK_ASPHALT_LAYER = register("ok_asphalt_layer",
            () -> new AsphaltLayer(BlockBehaviour.Properties.of().friction(0.45F).strength(1.9f)));
    public static final DeferredBlock<Block> MEDIOCRE_ASPHALT_LAYER = register("mediocre_asphalt_layer",
            () -> new AsphaltLayer(BlockBehaviour.Properties.of().friction(0.5F).strength(1.5f)));
    public static final DeferredBlock<Block> OLD_ASPHALT_LAYER = register("old_asphalt_layer",
            () -> new AsphaltLayer(BlockBehaviour.Properties.of().strength(1.2f)));

    public static final DeferredBlock<Block> SIDEWALK_LAYER = register("sidewalk_layer",
            () -> new SidewalkLayer(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<Block> SIDEWALK_LAYER_LEFT = register("sidewalk_layer_left",
            () -> new SidewalkLayer(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<Block> SIDEWALK_LAYER_RIGHT = register("sidewalk_layer_right",
            () -> new SidewalkLayer(BlockBehaviour.Properties.of()));

    public static final DeferredBlock<Block> BLOCKED_SIDEWALK_LAYER = register("blocked_sidewalk_layer",
            () -> new SidewalkLayer(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<Block> BLOCKED_SIDEWALK_LAYER_LEFT = register("blocked_sidewalk_layer_left",
            () -> new SidewalkLayer(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<Block> BLOCKED_SIDEWALK_LAYER_RIGHT = register("blocked_sidewalk_layer_right",
            () -> new SidewalkLayer(BlockBehaviour.Properties.of()));

    public static final DeferredBlock<Block> SECTIONED_SIDEWALK_LAYER = register("sectioned_sidewalk_layer",
            () -> new SidewalkLayer(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<Block> SECTIONED_SIDEWALK_LAYER_LEFT = register("sectioned_sidewalk_layer_left",
            () -> new SidewalkLayer(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<Block> SECTIONED_SIDEWALK_LAYER_RIGHT = register("sectioned_sidewalk_layer_right",
            () -> new SidewalkLayer(BlockBehaviour.Properties.of()));

    public static final DeferredBlock<Block> CRACKED_SIDEWALK_LAYER = register("cracked_sidewalk_layer",
            () -> new SidewalkLayer(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<Block> CRACKED_SIDEWALK_LAYER_LEFT = register("cracked_sidewalk_layer_left",
            () -> new SidewalkLayer(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<Block> CRACKED_SIDEWALK_LAYER_RIGHT = register("cracked_sidewalk_layer_right",
            () -> new SidewalkLayer(BlockBehaviour.Properties.of()));





    private static DeferredBlock<Block> register(String name, Supplier<Block> block) {
        DeferredBlock<Block> blk = BLOCKS.register(name,block);
        DeferredItem<Item> itm = TLRItems.ITEMS.register(name,() -> new BlockItem(blk.get(),new Item.Properties()));
        return blk;
    }

    private static DeferredBlock<Block> registerBlockWithoutItem(String name, Supplier<Block> block){
        return BLOCKS.register(name,block);
    }

    private static boolean always(BlockState bs, BlockGetter bg, BlockPos bp){return true;}
    private static boolean never(BlockState bs, BlockGetter bg, BlockPos bp){return false;}

    private static ToIntFunction<BlockState> customLitBlockEmission(int lv) {
        return (properties) -> {
            return properties.getValue(BlockStateProperties.LIT) ? lv : 0;
        };
    }

    private static ToIntFunction<BlockState> enabledLitBlockEmission(int i) {
        return (properties) -> {
            return properties.getValue(BlockStateProperties.ENABLED) ? i : 0;
        };
    }
}

package net.rk.longroads.entity.blockentity;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.rk.longroads.ThingamajigsLongRoads;
import net.rk.longroads.block.TLRBlocks;
import net.rk.longroads.entity.blockentity.custom.*;

import java.util.function.Supplier;

public class TLRBlockEntity {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(
            BuiltInRegistries.BLOCK_ENTITY_TYPE, ThingamajigsLongRoads.MODID);

    public static final Supplier<BlockEntityType<GreenRoadwaySign>> GREEN_ROADWAY_SIGN =
            BLOCK_ENTITIES.register("green_roadway_sign",
            () -> BlockEntityType.Builder.of(GreenRoadwaySign::new,
                    TLRBlocks.GREEN_ROADWAY_STANDING_SIGN.get(),
                    TLRBlocks.GREEN_ROADWAY_WALL_SIGN.get()
            ).build(null));

    public static final Supplier<BlockEntityType<RedRoadwaySign>> RED_ROADWAY_SIGN =
            BLOCK_ENTITIES.register("red_roadway_sign",
                    () -> BlockEntityType.Builder.of(RedRoadwaySign::new,
                            TLRBlocks.RED_ROADWAY_STANDING_SIGN.get(),
                            TLRBlocks.RED_ROADWAY_WALL_SIGN.get()
                    ).build(null));

    public static final Supplier<BlockEntityType<BlueRoadwaySign>> BLUE_ROADWAY_SIGN =
            BLOCK_ENTITIES.register("blue_roadway_sign",
                    () -> BlockEntityType.Builder.of(BlueRoadwaySign::new,
                            TLRBlocks.BLUE_ROADWAY_STANDING_SIGN.get(),
                            TLRBlocks.BLUE_ROADWAY_WALL_SIGN.get()
                    ).build(null));

    public static final Supplier<BlockEntityType<BrownRoadwaySign>> BROWN_ROADWAY_SIGN =
            BLOCK_ENTITIES.register("brown_roadway_sign",
                    () -> BlockEntityType.Builder.of(BrownRoadwaySign::new,
                            TLRBlocks.BROWN_ROADWAY_STANDING_SIGN.get(),
                            TLRBlocks.BROWN_ROADWAY_WALL_SIGN.get()
                    ).build(null));

    public static final Supplier<BlockEntityType<GreenHangingRoadwaySign>> GREEN_HANGING_ROADWAY_SIGN =
            BLOCK_ENTITIES.register("green_hanging_roadway_sign",
                    () -> BlockEntityType.Builder.of(GreenHangingRoadwaySign::new,
                            TLRBlocks.GREEN_HANGING_SIGN.get(),
                            TLRBlocks.GREEN_WALL_HANGING_SIGN.get()
                    ).build(null));

    public static final Supplier<BlockEntityType<DynamicRoadSignBE>> DYNAMIC_ROAD_SIGN_BE =
            BLOCK_ENTITIES.register("dynamic_road_sign_be",
                    () -> BlockEntityType.Builder.of(DynamicRoadSignBE::new,
                            TLRBlocks.ROAD_SIGN.get()
                    ).build(null));


    public static void register(IEventBus eventBus){
        BLOCK_ENTITIES.register(eventBus);
    }
}

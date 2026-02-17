package net.rk.longroads.item;

import net.minecraft.world.item.HangingSignItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SignItem;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.rk.longroads.ThingamajigsLongRoads;
import net.rk.longroads.block.TLRBlocks;
import net.rk.longroads.item.custom.*;

@SuppressWarnings("deprecated")
public class TLRItems{
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(ThingamajigsLongRoads.MODID);

    public static final DeferredItem<Item> PAINT_BRUSH = ITEMS.register("paint_brush",
            () -> new Paintbrush(new Item.Properties()));

    public static final DeferredItem<Item> WHITE_PAINT_BRUSH = ITEMS.register("white_paint_brush",
            () -> new WhitePaintBrush(new Item.Properties()
                    .component(TLRDataComponents.ROAD_MARKING_PATTERN,0)
                    .component(TLRDataComponents.LENGTH,1)));

    public static final DeferredItem<Item> YELLOW_PAINT_BRUSH = ITEMS.register("yellow_paint_brush",
            () -> new YellowPaintBrush(new Item.Properties()
                    .component(TLRDataComponents.ROAD_MARKING_PATTERN,0)
                    .component(TLRDataComponents.LENGTH,1)));

    public static final DeferredItem<Item> BLUE_PAINT_BRUSH = ITEMS.register("blue_paint_brush",
            () -> new BluePaintBrush(new Item.Properties()
                    .component(TLRDataComponents.ROAD_MARKING_PATTERN,0)
                    .component(TLRDataComponents.LENGTH,1)));

    public static final DeferredItem<Item> SCRAPE_TOOL = ITEMS.register("scrape_tool",
            () -> new ScrapeTool(new Item.Properties()));

    public static final DeferredItem<Item> PURPLE_PAINT_BRUSH = ITEMS.register("purple_paint_brush",
            () -> new PurplePaintBrush(new Item.Properties()
                    .component(TLRDataComponents.ROAD_MARKING_PATTERN,0)
                    .component(TLRDataComponents.LENGTH,1)));

    public static final DeferredItem<Item> MULTICOLOR_PAINT_BRUSH = ITEMS.register("multicolor_paint_brush",
            () -> new MulticolorPaintBrush(new Item.Properties()
                    .component(TLRDataComponents.ROAD_MARKING_PATTERN,0)
                    .component(TLRDataComponents.LENGTH,1)));


    // road signs
    public static final DeferredItem<Item> GREEN_ROADWAY_SIGN_ITEM = ITEMS.register("green_roadway_sign",
            () -> new SignItem((new Item.Properties()
                    .stacksTo(16)),
                    TLRBlocks.GREEN_ROADWAY_STANDING_SIGN.get(),
                    TLRBlocks.GREEN_ROADWAY_WALL_SIGN.get()));
    public static final DeferredItem<Item> RED_ROADWAY_SIGN_ITEM = ITEMS.register("red_roadway_sign",
            () -> new SignItem((new Item.Properties()
                    .stacksTo(16)),
                    TLRBlocks.RED_ROADWAY_STANDING_SIGN.get(),
                    TLRBlocks.RED_ROADWAY_WALL_SIGN.get()));
    public static final DeferredItem<Item> BLUE_ROADWAY_SIGN_ITEM = ITEMS.register("blue_roadway_sign",
            () -> new SignItem((new Item.Properties()
                    .stacksTo(16)),
                    TLRBlocks.BLUE_ROADWAY_STANDING_SIGN.get(),
                    TLRBlocks.BLUE_ROADWAY_WALL_SIGN.get()));
    public static final DeferredItem<Item> BROWN_ROADWAY_SIGN_ITEM = ITEMS.register("brown_roadway_sign",
            () -> new SignItem((new Item.Properties()
                    .stacksTo(16)),
                    TLRBlocks.BROWN_ROADWAY_STANDING_SIGN.get(),
                    TLRBlocks.BROWN_ROADWAY_WALL_SIGN.get()));
    // hanging road signs
    public static final DeferredItem<Item> GREEN_HANGING_ROADWAY_SIGN_ITEM = ITEMS.register("green_hanging_roadway_sign",
            () -> new HangingSignItem(TLRBlocks.GREEN_HANGING_SIGN.get(),
                    TLRBlocks.GREEN_WALL_HANGING_SIGN.get(),
                    (new Item.Properties().stacksTo(16))));

    public static final DeferredItem<Item> DYNAMIC_ROAD_SIGN = ITEMS.register("dynamic_road_sign",
            () -> new DynamicRoadSignItem(TLRBlocks.ROAD_SIGN.get(),new Item.Properties().fireResistant()));

    public static final DeferredItem<Item> DYNAMIC_STRAIGHT_ROAD_SIGN = ITEMS.registerItem("dynamic_straight_road_sign",
            (properties) -> new DynamicRoadSignItem(TLRBlocks.STRAIGHT_ROAD_SIGN.get(),properties));

    public static final DeferredItem<Item> DYNAMIC_THREE_WAY_ROAD_SIGN = ITEMS.registerItem("dynamic_three_way_road_sign",
            (properties) -> new DynamicRoadSignItem(TLRBlocks.THREE_WAY_ROAD_SIGN.get(),properties));

    public static final DeferredItem<Item> DYNAMIC_VERTICAL_REDSTONE_ROAD_SIGN = ITEMS.registerItem("dynamic_vertical_redstone_road_sign",
            (properties) -> new DynamicRoadSignItem(TLRBlocks.VERTICAL_REDSTONE_ROAD_SIGN.get(),properties));
}
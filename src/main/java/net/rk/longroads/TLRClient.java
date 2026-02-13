package net.rk.longroads;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.blockentity.HangingSignRenderer;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.rk.longroads.block.TLRBlocks;
import net.rk.longroads.entity.blockentity.TLRBlockEntity;
import net.rk.longroads.entity.blockentity.model.DynamicDoubleTallSignModel;
import net.rk.longroads.entity.blockentity.model.DynamicRectangleSignModel;
import net.rk.longroads.entity.blockentity.model.DynamicSignModel;
import net.rk.longroads.item.TLRItems;
import net.rk.longroads.menu.TLRMenu;
import net.rk.longroads.render.DynamicRoadSignBERenderer;
import net.rk.longroads.render.TLRBEWLR;
import net.rk.longroads.screen.DynamicRoadSignScreen;
import net.rk.longroads.util.Utilities;

@Mod(value = ThingamajigsLongRoads.MODID,dist = Dist.CLIENT)
public class TLRClient {
    public TLRClient(IEventBus modEventBus, ModContainer modContainer){
        modEventBus.addListener(this::clientSetup);
        modEventBus.addListener(this::layerSetup);
        modEventBus.addListener(this::setupMenuTypes);
        modEventBus.addListener(this::clientExtensions);
        modEventBus.addListener(this::addCreative);
        modContainer.registerExtensionPoint(IConfigScreenFactory.class,ConfigurationScreen::new);
    }

    public void clientSetup(FMLClientSetupEvent event) {
        WoodType.register(Utilities.GREEN_ROADWAY_WOOD);
        Sheets.addWoodType(Utilities.GREEN_ROADWAY_WOOD);
        BlockEntityRenderers.register(TLRBlockEntity.GREEN_ROADWAY_SIGN.get(), SignRenderer::new);
        WoodType.register(Utilities.RED_ROADWAY_WOOD);
        Sheets.addWoodType(Utilities.RED_ROADWAY_WOOD);
        BlockEntityRenderers.register(TLRBlockEntity.RED_ROADWAY_SIGN.get(),SignRenderer::new);
        WoodType.register(Utilities.BLUE_ROADWAY_WOOD);
        Sheets.addWoodType(Utilities.BLUE_ROADWAY_WOOD);
        BlockEntityRenderers.register(TLRBlockEntity.BLUE_ROADWAY_SIGN.get(),SignRenderer::new);
        WoodType.register(Utilities.BROWN_ROADWAY_WOOD);
        Sheets.addWoodType(Utilities.BROWN_ROADWAY_WOOD);
        BlockEntityRenderers.register(TLRBlockEntity.BROWN_ROADWAY_SIGN.get(),SignRenderer::new);
        BlockEntityRenderers.register(TLRBlockEntity.GREEN_HANGING_ROADWAY_SIGN.get(), HangingSignRenderer::new);
        // dynamic renderers
        BlockEntityRenderers.register(TLRBlockEntity.DYNAMIC_ROAD_SIGN_BE.get(), DynamicRoadSignBERenderer::new);
    }

    public void layerSetup(EntityRenderersEvent.RegisterLayerDefinitions event){
        event.registerLayerDefinition(DynamicSignModel.SIGN_TEXTURE_LOCATION,DynamicSignModel::createBodyLayer);
        event.registerLayerDefinition(DynamicRectangleSignModel.RECTANGLE_SIGN_TEXTURE_LOCATION,DynamicRectangleSignModel::createBodyLayer);
        event.registerLayerDefinition(DynamicDoubleTallSignModel.DOUBLE_TALL_SIGN_TEXTURE_LOCATION,DynamicDoubleTallSignModel::createBodyLayer);
    }

    public void setupMenuTypes(RegisterMenuScreensEvent event){
        event.register(TLRMenu.SIGN_MENU.get(), DynamicRoadSignScreen::new);
    }

    public void clientExtensions(RegisterClientExtensionsEvent event) {
        event.registerItem(new IClientItemExtensions() {
            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                return new TLRBEWLR(Minecraft.getInstance().getBlockEntityRenderDispatcher(),Minecraft.getInstance().getEntityModels());
            }
        },TLRItems.DYNAMIC_ROAD_SIGN.asItem(),TLRItems.DYNAMIC_STRAIGHT_ROAD_SIGN.asItem(),
                TLRItems.DYNAMIC_THREE_WAY_ROAD_SIGN.asItem(),TLRItems.DYNAMIC_VERTICAL_REDSTONE_ROAD_SIGN.asItem());
    }

    public void addCreative(BuildCreativeModeTabContentsEvent event){
        if(event.getTabKey() == ThingamajigsLongRoads.TLR_TAB.getKey()){
            event.accept(TLRItems.PAINT_BRUSH);
            event.accept(TLRItems.WHITE_PAINT_BRUSH);
            event.accept(TLRItems.YELLOW_PAINT_BRUSH);
            event.accept(TLRItems.BLUE_PAINT_BRUSH);
            event.accept(TLRItems.SCRAPE_TOOL);
            //
            event.accept(TLRItems.DYNAMIC_ROAD_SIGN);
            event.accept(TLRItems.DYNAMIC_THREE_WAY_ROAD_SIGN);
            event.accept(TLRItems.DYNAMIC_STRAIGHT_ROAD_SIGN);
            event.accept(TLRItems.DYNAMIC_VERTICAL_REDSTONE_ROAD_SIGN);
            //
            event.accept(TLRItems.GREEN_ROADWAY_SIGN_ITEM);
            event.accept(TLRItems.GREEN_HANGING_ROADWAY_SIGN_ITEM);
            event.accept(TLRItems.RED_ROADWAY_SIGN_ITEM);
            event.accept(TLRItems.BLUE_ROADWAY_SIGN_ITEM);
            event.accept(TLRItems.BROWN_ROADWAY_SIGN_ITEM);
            //
            event.accept(TLRBlocks.ASPHALT.asItem());
            event.accept(TLRBlocks.ASPHALT_SLAB.asItem());
            event.accept(TLRBlocks.ASPHALT_OK.asItem());
            event.accept(TLRBlocks.ASPHALT_OK_SLAB.asItem());
            event.accept(TLRBlocks.ASPHALT_MEDIOCRE.asItem());
            event.accept(TLRBlocks.ASPHALT_MEDIOCRE_SLAB.asItem());
            event.accept(TLRBlocks.ASPHALT_OLD.asItem());
            event.accept(TLRBlocks.ASPHALT_OLD_SLAB.asItem());
            event.accept(TLRBlocks.ASPHALT_LAYER.asItem());
            event.accept(TLRBlocks.OK_ASPHALT_LAYER.asItem());
            event.accept(TLRBlocks.MEDIOCRE_ASPHALT_LAYER.asItem());
            event.accept(TLRBlocks.OLD_ASPHALT_LAYER.asItem());
            //
            event.accept(TLRBlocks.DOUBLE_WHITE_ASPHALT.asItem());
            event.accept(TLRBlocks.DOUBLE_CORNER_WHITE_ASPHALT.asItem());
            event.accept(TLRBlocks.WHITE_PARKING_ASPHALT.asItem());
            event.accept(TLRBlocks.WHITE_PARKING_ASPHALT_SLAB.asItem());
            event.accept(TLRBlocks.WHITE_PARKING_OK_ASPHALT_SLAB.asItem());
            event.accept(TLRBlocks.WHITE_PARKING_MEDIOCRE_ASPHALT_SLAB.asItem());
            event.accept(TLRBlocks.WHITE_PARKING_OLD_ASPHALT_SLAB.asItem());
            event.accept(TLRBlocks.WHITE_D_ASPHALT_SLAB.asItem());
            event.accept(TLRBlocks.WHITE_D_OK_ASPHALT_SLAB.asItem());
            event.accept(TLRBlocks.WHITE_D_MEDIOCRE_ASPHALT_SLAB.asItem());
            event.accept(TLRBlocks.WHITE_D_OLD_ASPHALT_SLAB.asItem());
            event.accept(TLRBlocks.WHITE_DT_ASPHALT_SLAB.asItem());
            event.accept(TLRBlocks.WHITE_DT_OK_ASPHALT_SLAB.asItem());
            event.accept(TLRBlocks.WHITE_DT_MEDIOCRE_ASPHALT_SLAB.asItem());
            event.accept(TLRBlocks.WHITE_DT_OLD_ASPHALT_SLAB.asItem());
            //
            event.accept(TLRBlocks.DOUBLE_YELLOW_ASPHALT.asItem());
            event.accept(TLRBlocks.DOUBLE_CORNER_YELLOW_ASPHALT.asItem());
            event.accept(TLRBlocks.YELLOW_PARKING_ASPHALT.asItem());
            event.accept(TLRBlocks.YELLOW_PARKING_ASPHALT_SLAB.asItem());
            event.accept(TLRBlocks.YELLOW_PARKING_OK_ASPHALT_SLAB.asItem());
            event.accept(TLRBlocks.YELLOW_PARKING_MEDIOCRE_ASPHALT_SLAB.asItem());
            event.accept(TLRBlocks.YELLOW_PARKING_OLD_ASPHALT_SLAB.asItem());
            event.accept(TLRBlocks.YELLOW_D_ASPHALT_SLAB.asItem());
            event.accept(TLRBlocks.YELLOW_D_OK_ASPHALT_SLAB.asItem());
            event.accept(TLRBlocks.YELLOW_D_MEDIOCRE_ASPHALT_SLAB.asItem());
            event.accept(TLRBlocks.YELLOW_D_OLD_ASPHALT_SLAB.asItem());
            event.accept(TLRBlocks.YELLOW_DT_ASPHALT_SLAB.asItem());
            event.accept(TLRBlocks.YELLOW_DT_OK_ASPHALT_SLAB.asItem());
            event.accept(TLRBlocks.YELLOW_DT_MEDIOCRE_ASPHALT_SLAB.asItem());
            event.accept(TLRBlocks.YELLOW_DT_OLD_ASPHALT_SLAB.asItem());
            //
            event.accept(TLRBlocks.BLUE_PARKING_ASPHALT.asItem());
            event.accept(TLRBlocks.BLUE_PARKING_ASPHALT_SLAB.asItem());
            event.accept(TLRBlocks.BLUE_PARKING_OK_ASPHALT_SLAB.asItem());
            event.accept(TLRBlocks.BLUE_PARKING_MEDIOCRE_ASPHALT_SLAB.asItem());
            event.accept(TLRBlocks.BLUE_PARKING_OLD_ASPHALT_SLAB.asItem());
            //
            event.accept(TLRBlocks.VERTICAL_REDSTONE_SIDEWALK.asItem());
            event.accept(TLRBlocks.SIDEWALK.asItem());
            event.accept(TLRBlocks.SIDEWALK_BLOCKED.asItem());
            event.accept(TLRBlocks.SIDEWALK_SECTIONED.asItem());
            event.accept(TLRBlocks.SIDEWALK_CRACKED.asItem());

            event.accept(TLRBlocks.SIDEWALK_SLAB.asItem());
            event.accept(TLRBlocks.BLOCKED_SIDEWALK_SLAB.asItem());
            event.accept(TLRBlocks.SECTIONED_SIDEWALK_SLAB.asItem());
            event.accept(TLRBlocks.CRACKED_SIDEWALK_SLAB.asItem());

            event.accept(TLRBlocks.SIDEWALK_LAYER.asItem());
            event.accept(TLRBlocks.SIDEWALK_LAYER_LEFT.asItem());
            event.accept(TLRBlocks.SIDEWALK_LAYER_RIGHT.asItem());

            event.accept(TLRBlocks.BLOCKED_SIDEWALK_LAYER.asItem());
            event.accept(TLRBlocks.BLOCKED_SIDEWALK_LAYER_LEFT.asItem());
            event.accept(TLRBlocks.BLOCKED_SIDEWALK_LAYER_RIGHT.asItem());

            event.accept(TLRBlocks.SECTIONED_SIDEWALK_LAYER.asItem());
            event.accept(TLRBlocks.SECTIONED_SIDEWALK_LAYER_LEFT.asItem());
            event.accept(TLRBlocks.SECTIONED_SIDEWALK_LAYER_RIGHT.asItem());

            event.accept(TLRBlocks.CRACKED_SIDEWALK_LAYER.asItem());
            event.accept(TLRBlocks.CRACKED_SIDEWALK_LAYER_LEFT.asItem());
            event.accept(TLRBlocks.CRACKED_SIDEWALK_LAYER_RIGHT.asItem());
        }
    }
}

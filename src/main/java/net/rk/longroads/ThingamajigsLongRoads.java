package net.rk.longroads;

import com.mojang.logging.LogUtils;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.blockentity.HangingSignRenderer;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.loading.FMLLoader;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.rk.longroads.block.TLRBlocks;
import net.rk.longroads.entity.blockentity.TLRBlockEntity;
import net.rk.longroads.entity.blockentity.model.DynamicSignModel;
import net.rk.longroads.item.TLRDataComponents;
import net.rk.longroads.item.TLRItems;
import net.rk.longroads.network.TLRHandler;
import net.rk.longroads.render.DynamicRoadSignBERenderer;
import net.rk.longroads.util.Utilities;
import net.rk.thingamajigs.Thingamajigs;
import org.slf4j.Logger;

@Mod(ThingamajigsLongRoads.MODID)
public class ThingamajigsLongRoads {
    public static final String MODID = "thingamajigslongroads";
    private static final Logger LOGGER = LogUtils.getLogger();

    public static final DeferredRegister<CreativeModeTab> CMT_TLR = DeferredRegister.create(Registries.CREATIVE_MODE_TAB,MODID);
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> TLR_TAB = CMT_TLR.register(
            "trr_main_tab", () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.thingamajigslongroads"))
                    .withTabsBefore(Thingamajigs.MAIN_CTAB.getKey())
                    .icon(() -> TLRItems.GREEN_HANGING_ROADWAY_SIGN_ITEM.get().getDefaultInstance())
                    .backgroundTexture(ResourceLocation.fromNamespaceAndPath("thingamajigs","textures/gui/thingamajigsitems.png"))
                    .build()
    );

    public static boolean werok = false;

    public static boolean badFileAccessFlag = false; // if the file system for whatever reason cannot be accessed, mark this flag as true

    public ThingamajigsLongRoads(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::commonSetup);

        if(ModList.get().isLoaded("thingamajigs")){
            werok = true;
            LOGGER.info("Detected base required mod exists.");
        }

        modEventBus.addListener(TLRHandler::register);

        TLRBlocks.BLOCKS.register(modEventBus);
        TLRItems.ITEMS.register(modEventBus);
        TLRBlockEntity.register(modEventBus);
        CMT_TLR.register(modEventBus);
        //TLRMenu.register(modEventBus);

        TLRDataComponents.COMPONENTS.register(modEventBus);

        modEventBus.addListener(this::addCreative);

        if(FMLLoader.getDist() == Dist.CLIENT){
            modEventBus.addListener(TLRClientEvents::clientSetup);
            modEventBus.addListener(TLRClientEvents::layerSetup);
            modEventBus.addListener(TLRClientEvents::setupMenuTypes);
        }
        else{
            LOGGER.info("TLong Roads running in server mode");
        }

        //modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event){
        if(event.getTabKey() == TLR_TAB.getKey()){
            event.accept(TLRItems.PAINT_BRUSH);
            event.accept(TLRItems.WHITE_PAINT_BRUSH);
            event.accept(TLRItems.YELLOW_PAINT_BRUSH);
            event.accept(TLRItems.BLUE_PAINT_BRUSH);
            event.accept(TLRItems.SCRAPE_TOOL);
            //
            //event.accept(TLRItems.DYNAMIC_ROAD_SIGN); // so much nope
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
        }
    }

    // client stuff class
    public static class TLRClientEvents {
        @SubscribeEvent
        public static void clientSetup(FMLClientSetupEvent event) {
            WoodType.register(Utilities.GREEN_ROADWAY_WOOD);
            Sheets.addWoodType(Utilities.GREEN_ROADWAY_WOOD);
            BlockEntityRenderers.register(TLRBlockEntity.GREEN_ROADWAY_SIGN.get(),SignRenderer::new);
            WoodType.register(Utilities.RED_ROADWAY_WOOD);
            Sheets.addWoodType(Utilities.RED_ROADWAY_WOOD);
            BlockEntityRenderers.register(TLRBlockEntity.RED_ROADWAY_SIGN.get(),SignRenderer::new);
            WoodType.register(Utilities.BLUE_ROADWAY_WOOD);
            Sheets.addWoodType(Utilities.BLUE_ROADWAY_WOOD);
            BlockEntityRenderers.register(TLRBlockEntity.BLUE_ROADWAY_SIGN.get(),SignRenderer::new);
            WoodType.register(Utilities.BROWN_ROADWAY_WOOD);
            Sheets.addWoodType(Utilities.BROWN_ROADWAY_WOOD);
            BlockEntityRenderers.register(TLRBlockEntity.BROWN_ROADWAY_SIGN.get(),SignRenderer::new);
            BlockEntityRenderers.register(TLRBlockEntity.GREEN_HANGING_ROADWAY_SIGN.get(),HangingSignRenderer::new);


            BlockEntityRenderers.register(TLRBlockEntity.DYNAMIC_ROAD_SIGN_BE.get(),DynamicRoadSignBERenderer::new);
        }

        public static void layerSetup(EntityRenderersEvent.RegisterLayerDefinitions event){
            event.registerLayerDefinition(DynamicSignModel.SIGN_TEXTURE_LOCATION,DynamicSignModel::createBodyLayer);
        }

        public static void setupMenuTypes(RegisterMenuScreensEvent event){

        }

        @SubscribeEvent
        private static void blockColorSetup(RegisterColorHandlersEvent.Block event){
            /*
            event.register((blockState,tintGetter,blockPos,i) ->
                            TColors.getTeal(),
                    TGBlocks.TEAL_BALLOON_BLOCK.get()
            );
            */
        }

        @SubscribeEvent
        private static void itemColorSetup(RegisterColorHandlersEvent.Item event){
            /*
            event.register((itemStack,i) -> TColors.getLightGray(),
                    TGItems.LIGHT_GRAY_BALLOON_BLOCK_ITEM.asItem()
            );
            */
        }


        @SubscribeEvent
        public static void clientExtensions(RegisterClientExtensionsEvent event) {

        }
    }
}

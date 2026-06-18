package net.rk.longroads;

import com.mojang.logging.LogUtils;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.registries.DataPackRegistryEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.rk.longroads.block.TLRBlocks;
import net.rk.longroads.config.TLRServerConfig;
import net.rk.longroads.entity.blockentity.TLRBlockEntity;
import net.rk.longroads.item.TLRDataComponents;
import net.rk.longroads.item.TLRItems;
import net.rk.longroads.menu.TLRMenu;
import net.rk.longroads.network.TLRHandler;
import net.rk.longroads.registries.SignType;
import net.rk.longroads.registries.SignTypeBootstrap;
import net.rk.longroads.registries.TLRRegistries;
import org.slf4j.Logger;

import java.util.Set;

@Mod(ThingamajigsLongRoads.MODID)
public class ThingamajigsLongRoads {
    public static final String MODID = "thingamajigslongroads";
    private static final Logger LOGGER = LogUtils.getLogger();

    public static final DeferredRegister<CreativeModeTab> CMT_TLR = DeferredRegister.create(Registries.CREATIVE_MODE_TAB,MODID);
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> TLR_TAB = CMT_TLR.register(
            "trr_main_tab", () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.thingamajigslongroads"))
                    .withTabsBefore(CreativeModeTabs.SPAWN_EGGS)
                    .icon(() -> TLRItems.GREEN_HANGING_ROADWAY_SIGN_ITEM.get().getDefaultInstance())
                    .build()
    );

    public static boolean thingamajigsWasLoaded = false;
    public static boolean railroadwaysWasLoaded = false;

    public ThingamajigsLongRoads(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::commonSetup);

        if(ModList.get().isLoaded("thingamajigs")){
            thingamajigsWasLoaded = true;
        }
        if(ModList.get().isLoaded("thingamajigsrailroadways")){
            railroadwaysWasLoaded = true;
        }

        modEventBus.addListener(TLRHandler::register);
        modEventBus.addListener(this::registerDatapackRegistries);

        TLRMenu.MENU_TYPES.register(modEventBus);

        TLRBlocks.BLOCKS.register(modEventBus);
        TLRItems.ITEMS.register(modEventBus);
        TLRBlockEntity.register(modEventBus);
        CMT_TLR.register(modEventBus);

        TLRDataComponents.COMPONENTS.register(modEventBus);

        modContainer.registerConfig(ModConfig.Type.SERVER,TLRServerConfig.SPEC);
    }

    public void commonSetup(final FMLCommonSetupEvent event) {
    }

    // datapack registries don't use streamcodecs
    public void registerDatapackRegistries(DataPackRegistryEvent.NewRegistry event){
        event.dataPackRegistry(TLRRegistries.SIGN_TYPE,SignType.CODEC,SignType.CODEC);
    }
}

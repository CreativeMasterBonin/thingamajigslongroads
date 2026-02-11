package net.rk.longroads.menu;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.rk.longroads.ThingamajigsLongRoads;

public class TLRMenu {
    public static final DeferredRegister<MenuType<?>> MENU_TYPES = DeferredRegister.create(
            BuiltInRegistries.MENU, ThingamajigsLongRoads.MODID);

    public static final DeferredHolder<MenuType<?>, MenuType<DynamicSignMenu>> SIGN_MENU = MENU_TYPES.register("road_sign_menu",
            () -> IMenuTypeExtension.create(DynamicSignMenu::new));

    public static void register(IEventBus eventBus){MENU_TYPES.register(eventBus);}
}

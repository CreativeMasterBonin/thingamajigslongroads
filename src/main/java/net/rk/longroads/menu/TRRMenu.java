package net.rk.longroads.menu;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.rk.longroads.ThingamajigsLongRoads;

public class TRRMenu{
    public static final DeferredRegister<MenuType<?>> MENU_TYPES = DeferredRegister.create(
            BuiltInRegistries.MENU, ThingamajigsLongRoads.MODID);


    public static void register(IEventBus eventBus){MENU_TYPES.register(eventBus);}
}

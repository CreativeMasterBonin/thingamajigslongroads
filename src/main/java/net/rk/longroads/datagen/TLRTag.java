package net.rk.longroads.datagen;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;

public class TLRTag {
    public static final TagKey<Block> ROAD_SIGNS = thingamajigsLRBlockTag("road_signs");
    public static final TagKey<Block> ASPHALT_BLOCKS = thingamajigsLRBlockTag("asphalt");
    public static final TagKey<Block> ASPHALT_SLABS_BLOCKS = thingamajigsLRBlockTag("asphalt_slabs");
    public static final TagKey<Block> SUPPORTS_ROAD_HANGING_SIGNS = thingamajigsLRBlockTag("supports_road_hanging_signs");

    public static final TagKey<Item> ASPHALT_ITEMS = thingamajigsLRItemTag("asphalt");
    public static final TagKey<Item> ASPHALT_SLABS_ITEMS = thingamajigsLRItemTag("asphalt_slabs");

    public static final TagKey<Item> ROAD_SIGN_CRAFTING_GLOBS = thingamajigsLRItemTag("road_sign_crafting_globs");
    public static final TagKey<Item> ROADWAY_SIGN_CRAFTING_SIGNS = thingamajigsLRItemTag("roadway_sign_crafting_signs");
    public static final TagKey<Item> ROADWAY_SIGN_EDIT_TOOLS = thingamajigsLRItemTag("roadway_sign_edit_tools");

    private static TagKey<Block> thingamajigsLRBlockTag(String name){
        return BlockTags.create(ResourceLocation.fromNamespaceAndPath("thingamajigslongroads", name));
    }

    private static TagKey<Item> thingamajigsLRItemTag(String name){
        return ItemTags.create(ResourceLocation.fromNamespaceAndPath("thingamajigslongroads", name));
    }

    // default tag registry

    private static TagKey<Block> mcBlockTag(String name){
        return BlockTags.create(ResourceLocation.withDefaultNamespace(name));
    }

    private static TagKey<Item> mcItemTag(String name){
        return ItemTags.create(ResourceLocation.withDefaultNamespace(name));
    }

    private static TagKey<Fluid> mcFluidTag(String name){
        return FluidTags.create(ResourceLocation.withDefaultNamespace(name));
    }

    // common tag registry

    private static TagKey<Fluid> commonFluidTag(String name){
        return FluidTags.create(ResourceLocation.fromNamespaceAndPath("c", name));
    }

    private static TagKey<Block> commonBlockTag(String name){
        return BlockTags.create(ResourceLocation.fromNamespaceAndPath("c", name));
    }

    private static TagKey<Item> commonItemTag(String name){
        return ItemTags.create(ResourceLocation.fromNamespaceAndPath("c", name));
    }
}


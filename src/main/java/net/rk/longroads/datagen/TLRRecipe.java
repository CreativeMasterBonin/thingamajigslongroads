package net.rk.longroads.datagen;

import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.rk.longroads.block.TLRBlocks;
import net.rk.longroads.item.TLRItems;
import net.rk.thingamajigs.block.TBlocks;
import net.rk.thingamajigs.item.TItems;

import java.util.concurrent.CompletableFuture;

public class TLRRecipe extends RecipeProvider{
    public TLRRecipe(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pRegistries) {
        super(pOutput, pRegistries);
    }

    @Override
    protected void buildRecipes(RecipeOutput output) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, TLRItems.PAINT_BRUSH,1)
                .define('t', TItems.THINGAMAJIG)
                .define('f', Items.FEATHER)
                .define('s',Items.STICK)
                .pattern("ftf")
                .pattern(" s ")
                .pattern(" s ")
                .unlockedBy("has_ingredient",has(TItems.THINGAMAJIG))
                .save(output);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,TLRItems.PAINT_BRUSH,1)
                .define('t',TItems.THINGAMAJIG)
                .define('b',Items.BRUSH)
                .pattern("t")
                .pattern("b")
                .unlockedBy("has_ingredient",has(TItems.THINGAMAJIG))
                .save(output,"alternative_paint_brush");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,TLRItems.WHITE_PAINT_BRUSH,1)
                .requires(TLRItems.PAINT_BRUSH)
                .requires(Items.WHITE_DYE)
                .unlockedBy("has_ingredient",has(TLRItems.PAINT_BRUSH))
                .save(output);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,TLRItems.YELLOW_PAINT_BRUSH,1)
                .requires(TLRItems.PAINT_BRUSH)
                .requires(Items.YELLOW_DYE)
                .unlockedBy("has_ingredient",has(TLRItems.PAINT_BRUSH))
                .save(output);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,TLRItems.BLUE_PAINT_BRUSH,1)
                .requires(TLRItems.PAINT_BRUSH)
                .requires(Items.BLUE_DYE)
                .unlockedBy("has_ingredient",has(TLRItems.PAINT_BRUSH))
                .save(output);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,TLRItems.SCRAPE_TOOL,1)
                .define('t',TItems.THINGAMAJIG)
                .define('f',Items.FLINT)
                .define('s',Items.STICK)
                .pattern("ftf")
                .pattern(" s ")
                .pattern(" s ")
                .unlockedBy("has_ingredient",has(TItems.THINGAMAJIG))
                .save(output);

        // colored roadway signs
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,TLRItems.GREEN_ROADWAY_SIGN_ITEM,1)
                .requires(TLRTag.ROADWAY_SIGN_CRAFTING_SIGNS)
                .requires(Items.GREEN_DYE)
                .requires(Items.IRON_INGOT)
                .unlockedBy("has_ingredient",has(Items.GREEN_DYE))
                .save(output);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,TLRItems.GREEN_HANGING_ROADWAY_SIGN_ITEM,1)
                .requires(ItemTags.HANGING_SIGNS)
                .requires(Items.GREEN_DYE)
                .requires(Items.IRON_INGOT)
                .unlockedBy("has_ingredient",has(Items.GREEN_DYE))
                .save(output);
        ShapelessRecipeBuilder. shapeless(RecipeCategory.MISC,TLRItems.RED_ROADWAY_SIGN_ITEM,1)
                .requires(TLRTag.ROADWAY_SIGN_CRAFTING_SIGNS)
                .requires(Items.RED_DYE)
                .requires(Items.IRON_INGOT)
                .unlockedBy("has_ingredient",has(Items.RED_DYE))
                .save(output);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,TLRItems.BLUE_ROADWAY_SIGN_ITEM,1)
                .requires(TLRTag.ROADWAY_SIGN_CRAFTING_SIGNS)
                .requires(Items.BLUE_DYE)
                .requires(Items.IRON_INGOT)
                .unlockedBy("has_ingredient",has(Items.BLUE_DYE))
                .save(output);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,TLRItems.BROWN_ROADWAY_SIGN_ITEM,1)
                .requires(TLRTag.ROADWAY_SIGN_CRAFTING_SIGNS)
                .requires(Items.BROWN_DYE)
                .requires(Items.IRON_INGOT)
                .unlockedBy("has_ingredient",has(Items.BROWN_DYE))
                .save(output);

        // sidewalk
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, TLRBlocks.SIDEWALK.asItem(),3)
                .requires(TBlocks.CONCRETE.asItem())
                .requires(Items.CLAY_BALL)
                .requires(Items.GRAVEL)
                .unlockedBy("has_ingredient",has(TBlocks.CONCRETE.asItem()))
                .save(output);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS,TLRBlocks.SIDEWALK_SLAB,6)
                .define('s',TLRBlocks.SIDEWALK.asItem())
                .pattern("s")
                .unlockedBy("has_ingredient",has(TLRBlocks.SIDEWALK.asItem()))
                .save(output);

        stonecutterAny(TLRBlocks.SIDEWALK_SLAB.asItem(),
                Ingredient.of(TLRBlocks.SIDEWALK.asItem()),
                TLRBlocks.SIDEWALK_SLAB.asItem(),2)
                .unlockedBy("has_ingredient",has(TLRBlocks.SIDEWALK.asItem()))
                .save(output,"sidewalk_slab_stonecutting");

        // variants
        stonecutterAny(TLRBlocks.SIDEWALK_CRACKED.asItem(),
                Ingredient.of(TLRBlocks.SIDEWALK.asItem()),
                TLRBlocks.SIDEWALK_CRACKED.asItem(),1)
                .unlockedBy("has_ingredient",has(TLRBlocks.SIDEWALK.asItem()))
                .save(output);

        stonecutterAny(TLRBlocks.SIDEWALK_BLOCKED.asItem(),
                Ingredient.of(TLRBlocks.SIDEWALK.asItem()),
                TLRBlocks.SIDEWALK_BLOCKED.asItem(),1)
                .unlockedBy("has_ingredient",has(TLRBlocks.SIDEWALK.asItem()))
                .save(output);

        stonecutterAny(TLRBlocks.SIDEWALK_SECTIONED.asItem(),
                Ingredient.of(TLRBlocks.SIDEWALK.asItem()),
                TLRBlocks.SIDEWALK_SECTIONED.asItem(),1)
                .unlockedBy("has_ingredient",has(TLRBlocks.SIDEWALK.asItem()))
                .save(output);

        // crafting variants
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS,TLRBlocks.CRACKED_SIDEWALK_SLAB,6)
                .define('s',TLRBlocks.SIDEWALK_CRACKED.asItem())
                .pattern("s")
                .unlockedBy("has_ingredient",has(TLRBlocks.SIDEWALK_CRACKED.asItem()))
                .save(output);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS,TLRBlocks.SECTIONED_SIDEWALK_SLAB,6)
                .define('s',TLRBlocks.SIDEWALK_SECTIONED.asItem())
                .pattern("s")
                .unlockedBy("has_ingredient",has(TLRBlocks.SIDEWALK_SECTIONED.asItem()))
                .save(output);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS,TLRBlocks.BLOCKED_SIDEWALK_SLAB,6)
                .define('s',TLRBlocks.SIDEWALK_BLOCKED.asItem())
                .pattern("s")
                .unlockedBy("has_ingredient",has(TLRBlocks.SIDEWALK_BLOCKED.asItem()))
                .save(output);

        // stonecutting slab variants
        stonecutterAny(TLRBlocks.CRACKED_SIDEWALK_SLAB.asItem(),
                Ingredient.of(TLRBlocks.SIDEWALK_CRACKED.asItem()),
                TLRBlocks.CRACKED_SIDEWALK_SLAB.asItem(),2)
                .unlockedBy("has_ingredient",has(TLRBlocks.SIDEWALK_CRACKED.asItem()))
                .save(output,"sidewalk_cracked_slab_stonecutting");

        stonecutterAny(TLRBlocks.SECTIONED_SIDEWALK_SLAB.asItem(),
                Ingredient.of(TLRBlocks.SIDEWALK_SECTIONED.asItem()),
                TLRBlocks.SECTIONED_SIDEWALK_SLAB.asItem(),2)
                .unlockedBy("has_ingredient",has(TLRBlocks.SIDEWALK_SECTIONED.asItem()))
                .save(output,"sidewalk_sectioned_slab_stonecutting");

        stonecutterAny(TLRBlocks.BLOCKED_SIDEWALK_SLAB.asItem(),
                Ingredient.of(TLRBlocks.SIDEWALK_BLOCKED.asItem()),
                TLRBlocks.BLOCKED_SIDEWALK_SLAB.asItem(),2)
                .unlockedBy("has_ingredient",has(TLRBlocks.SIDEWALK_BLOCKED.asItem()))
                .save(output,"sidewalk_blocked_slab_stonecutting");

        // vertical redstone blocks
        ShapelessRecipeBuilder.shapeless(RecipeCategory.REDSTONE,TLRBlocks.VERTICAL_REDSTONE_SIDEWALK.asItem(),1)
                .requires(Items.IRON_INGOT)
                .requires(Items.REDSTONE)
                .requires(TLRBlocks.SIDEWALK)
                .unlockedBy("has_ingredient",has(Items.REDSTONE))
                .save(output);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.REDSTONE,TLRItems.DYNAMIC_VERTICAL_REDSTONE_ROAD_SIGN.asItem(),1)
                .requires(Items.IRON_INGOT)
                .requires(Items.REDSTONE)
                .requires(TLRItems.DYNAMIC_ROAD_SIGN.asItem())
                .unlockedBy("has_ingredient",has(Items.REDSTONE))
                .save(output);

        // asphalt
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS,TLRBlocks.ASPHALT.asItem(),2)
                .requires(TItems.INFRASTRUCTURE_COMPONENT)
                .requires(Items.BLACKSTONE)
                .requires(Items.STONE)
                .unlockedBy("has_ingredient",has(TItems.INFRASTRUCTURE_COMPONENT))
                .save(output);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS,TLRBlocks.ASPHALT_SLAB,6)
                .define('a',TLRBlocks.ASPHALT.asItem())
                .pattern("aaa")
                .unlockedBy("has_ingredient",has(TLRBlocks.ASPHALT.asItem()))
                .save(output);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS,TLRBlocks.ASPHALT_OK_SLAB,6)
                .define('a',TLRBlocks.ASPHALT_OK.asItem())
                .pattern("aaa")
                .unlockedBy("has_ingredient",has(TLRBlocks.ASPHALT_OK.asItem()))
                .save(output);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS,TLRBlocks.ASPHALT_MEDIOCRE_SLAB,6)
                .define('a',TLRBlocks.ASPHALT_MEDIOCRE.asItem())
                .pattern("aaa")
                .unlockedBy("has_ingredient",has(TLRBlocks.ASPHALT_MEDIOCRE.asItem()))
                .save(output);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS,TLRBlocks.ASPHALT_OLD_SLAB,6)
                .define('a',TLRBlocks.ASPHALT_OLD.asItem())
                .pattern("aaa")
                .unlockedBy("has_ingredient",has(TLRBlocks.ASPHALT_OLD.asItem()))
                .save(output);

        stonecutterAny(TLRBlocks.ASPHALT_SLAB.asItem(),
                Ingredient.of(TLRBlocks.ASPHALT.asItem()),
                TLRBlocks.ASPHALT_SLAB.asItem(),2)
                .unlockedBy("has_ingredient",has(TLRBlocks.ASPHALT.asItem()))
                .save(output,"asphalt_slab_stonecutting");

        stonecutterAny(TLRBlocks.ASPHALT_OK.asItem(),
                Ingredient.of(TLRBlocks.ASPHALT.asItem()),
                TLRBlocks.ASPHALT_OK.asItem(),2)
                .unlockedBy("has_ingredient",has(TLRBlocks.ASPHALT.asItem()))
                .save(output);

        stonecutterAny(TLRBlocks.ASPHALT_MEDIOCRE.asItem(),
                Ingredient.of(TLRBlocks.ASPHALT_OK.asItem()),
                TLRBlocks.ASPHALT_MEDIOCRE.asItem(),2)
                .unlockedBy("has_ingredient",has(TLRBlocks.ASPHALT_OK.asItem()))
                .save(output);

        stonecutterAny(TLRBlocks.ASPHALT_OLD.asItem(),
                Ingredient.of(TLRBlocks.ASPHALT_MEDIOCRE.asItem()),
                TLRBlocks.ASPHALT_OLD.asItem(),2)
                .unlockedBy("has_ingredient",has(TLRBlocks.ASPHALT_MEDIOCRE.asItem()))
                .save(output);

        stonecutterAny(TLRBlocks.ASPHALT_OK_SLAB.asItem(),
                Ingredient.of(TLRBlocks.ASPHALT_OK.asItem()),
                TLRBlocks.ASPHALT_OK_SLAB.asItem(),2)
                .unlockedBy("has_ingredient",has(TLRBlocks.ASPHALT_OK.asItem()))
                .save(output,"asphalt_ok_slab_stonecutting");

        stonecutterAny(TLRBlocks.ASPHALT_MEDIOCRE_SLAB.asItem(),
                Ingredient.of(TLRBlocks.ASPHALT_MEDIOCRE.asItem()),
                TLRBlocks.ASPHALT_MEDIOCRE_SLAB.asItem(),2)
                .unlockedBy("has_ingredient",has(TLRBlocks.ASPHALT_MEDIOCRE.asItem()))
                .save(output,"asphalt_mediocre_slab_stonecutting");

        stonecutterAny(TLRBlocks.ASPHALT_OLD_SLAB.asItem(),
                Ingredient.of(TLRBlocks.ASPHALT_OLD.asItem()),
                TLRBlocks.ASPHALT_OLD_SLAB.asItem(),2)
                .unlockedBy("has_ingredient",has(TLRBlocks.ASPHALT_OLD.asItem()))
                .save(output,"asphalt_old_slab_stonecutting");


        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,TLRItems.DYNAMIC_ROAD_SIGN.asItem(),1)
                .requires(TItems.INFRASTRUCTURE_COMPONENT)
                .requires(Items.IRON_BARS)
                .requires(TBlocks.PLUS_POLE.asItem())
                .requires(TLRTag.ROAD_SIGN_CRAFTING_GLOBS)
                .unlockedBy("has_ingredient",has(TItems.INFRASTRUCTURE_COMPONENT))
                .save(output);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,TLRItems.DYNAMIC_STRAIGHT_ROAD_SIGN,1)
                .requires(TLRItems.DYNAMIC_ROAD_SIGN.asItem())
                .requires(TBlocks.STRAIGHT_POLE.asItem())
                .unlockedBy("has_ingredient",has(TBlocks.STRAIGHT_POLE.asItem()))
                .save(output);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,TLRItems.DYNAMIC_THREE_WAY_ROAD_SIGN,1)
                .requires(TLRItems.DYNAMIC_ROAD_SIGN.asItem())
                .requires(TBlocks.THREE_WAY_POLE.asItem())
                .unlockedBy("has_ingredient",has(TBlocks.THREE_WAY_POLE.asItem()))
                .save(output);


        stonecutterAny(TLRBlocks.ASPHALT_LAYER.get().asItem(),
                Ingredient.of(TLRBlocks.ASPHALT_SLAB.get()),
                TLRBlocks.ASPHALT_LAYER.get().asItem(),4)
                .unlockedBy("has_thingy", InventoryChangeTrigger.TriggerInstance
                        .hasItems(TLRBlocks.ASPHALT_SLAB.get()))
                .save(output);
        stonecutterAny(TLRBlocks.OK_ASPHALT_LAYER.get().asItem(),
                Ingredient.of(TLRBlocks.ASPHALT_OK_SLAB.get()),
                TLRBlocks.OK_ASPHALT_LAYER.get().asItem(),4)
                .unlockedBy("has_thingy",InventoryChangeTrigger.TriggerInstance
                        .hasItems(TLRBlocks.ASPHALT_OK_SLAB.get()))
                .save(output);
        stonecutterAny(TLRBlocks.MEDIOCRE_ASPHALT_LAYER.get().asItem(),
                Ingredient.of(TLRBlocks.ASPHALT_MEDIOCRE_SLAB.get()),
                TLRBlocks.MEDIOCRE_ASPHALT_LAYER.get().asItem(),4)
                .unlockedBy("has_thingy",InventoryChangeTrigger.TriggerInstance
                        .hasItems(TLRBlocks.ASPHALT_MEDIOCRE_SLAB.get()))
                .save(output);
        stonecutterAny(TLRBlocks.OLD_ASPHALT_LAYER.get().asItem(),
                Ingredient.of(TLRBlocks.ASPHALT_OLD_SLAB.get()),
                TLRBlocks.OLD_ASPHALT_LAYER.get().asItem(),4)
                .unlockedBy("has_thingy",InventoryChangeTrigger.TriggerInstance
                        .hasItems(TLRBlocks.ASPHALT_OLD_SLAB.get()))
                .save(output);
        stonecutterAny(TLRBlocks.SIDEWALK_LAYER_LEFT.get().asItem(),
                Ingredient.of(TLRBlocks.SIDEWALK_SLAB.get()),
                TLRBlocks.SIDEWALK_LAYER_LEFT.get().asItem(),4)
                .unlockedBy("has_thingy",InventoryChangeTrigger.TriggerInstance
                        .hasItems(TLRBlocks.SIDEWALK_SLAB.get()))
                .save(output);
        stonecutterAny(TLRBlocks.SIDEWALK_LAYER_RIGHT.get().asItem(),
                Ingredient.of(TLRBlocks.SIDEWALK_SLAB.get()),
                TLRBlocks.SIDEWALK_LAYER_RIGHT.get().asItem(),4)
                .unlockedBy("has_thingy",InventoryChangeTrigger.TriggerInstance
                        .hasItems(TLRBlocks.SIDEWALK_SLAB.get()))
                .save(output);
        stonecutterAny(TLRBlocks.SIDEWALK_LAYER.get().asItem(),
                Ingredient.of(TLRBlocks.SIDEWALK_SLAB.get()),
                TLRBlocks.SIDEWALK_LAYER.get().asItem(),4)
                .unlockedBy("has_thingy",InventoryChangeTrigger.TriggerInstance
                        .hasItems(TLRBlocks.SIDEWALK_SLAB.get()))
                .save(output);
        layerToLayer(TLRBlocks.BLOCKED_SIDEWALK_LAYER.asItem(),TLRBlocks.SIDEWALK_LAYER.asItem()).save(output);
        layerToLayer(TLRBlocks.SECTIONED_SIDEWALK_LAYER.asItem(),TLRBlocks.SIDEWALK_LAYER.asItem()).save(output);
        layerToLayer(TLRBlocks.CRACKED_SIDEWALK_LAYER.asItem(),TLRBlocks.SIDEWALK_LAYER.asItem()).save(output);

        layerToLayer(TLRBlocks.BLOCKED_SIDEWALK_LAYER_LEFT.asItem(),TLRBlocks.SIDEWALK_LAYER_LEFT.asItem()).save(output);
        layerToLayer(TLRBlocks.SECTIONED_SIDEWALK_LAYER_LEFT.asItem(),TLRBlocks.SIDEWALK_LAYER_LEFT.asItem()).save(output);
        layerToLayer(TLRBlocks.CRACKED_SIDEWALK_LAYER_LEFT.asItem(),TLRBlocks.SIDEWALK_LAYER_LEFT.asItem()).save(output);

        layerToLayer(TLRBlocks.BLOCKED_SIDEWALK_LAYER_RIGHT.asItem(),TLRBlocks.SIDEWALK_LAYER_RIGHT.asItem()).save(output);
        layerToLayer(TLRBlocks.SECTIONED_SIDEWALK_LAYER_RIGHT.asItem(),TLRBlocks.SIDEWALK_LAYER_RIGHT.asItem()).save(output);
        layerToLayer(TLRBlocks.CRACKED_SIDEWALK_LAYER_RIGHT.asItem(),TLRBlocks.SIDEWALK_LAYER_RIGHT.asItem()).save(output);
    }

    public static RecipeBuilder layerToLayer(Item itemOutput,Item itemInput){
        return stonecutterAny(itemOutput,Ingredient.of(itemInput),itemOutput,4).unlockedBy("has_thingy",InventoryChangeTrigger.TriggerInstance.hasItems(itemInput));
    }

    public static RecipeBuilder stonecutterAny(Item requiredItem, Ingredient inputItem, Item result, int amt) {
        return SingleItemRecipeBuilder.stonecutting(inputItem,RecipeCategory.MISC,result,amt)
                .unlockedBy("has_thingy",inventoryTrigger(ItemPredicate.Builder.item().of(requiredItem)));
    }
}


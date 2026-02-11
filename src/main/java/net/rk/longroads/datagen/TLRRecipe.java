package net.rk.longroads.datagen;

import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.concurrent.CompletableFuture;

public class TLRRecipe extends RecipeProvider{
    public TLRRecipe(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pRegistries) {
        super(pOutput, pRegistries);
    }

    @Override
    protected void buildRecipes(RecipeOutput rc) {

    }

    public static RecipeBuilder stonecutterAny(Item requiredItem, Ingredient inputItem, Item result, int amt) {
        return SingleItemRecipeBuilder.stonecutting(inputItem,RecipeCategory.MISC,result,amt)
                .unlockedBy("has_thingy",inventoryTrigger(ItemPredicate.Builder.item().of(requiredItem)));
    }
}


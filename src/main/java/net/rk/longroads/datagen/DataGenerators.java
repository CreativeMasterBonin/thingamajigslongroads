package net.rk.longroads.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.rk.longroads.ThingamajigsLongRoads;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = ThingamajigsLongRoads.MODID, bus = EventBusSubscriber.Bus.MOD)
public class DataGenerators{
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        generator.addProvider(event.includeClient(),new TLRLanguage(packOutput,"en_us"));

        generator.addProvider(event.includeServer(), new LootTableProvider(packOutput, Collections.emptySet(),
                List.of(new LootTableProvider.SubProviderEntry(TLRLoot::new,LootContextParamSets.BLOCK)),lookupProvider));

        event.getGenerator().addProvider(event.includeClient(), new TLRItemModel(packOutput,event.getExistingFileHelper()));

        TLRBlockTag blockTags = new TLRBlockTag(packOutput, lookupProvider, event.getExistingFileHelper());
        generator.addProvider(event.includeServer(), blockTags);

        event.getGenerator().addProvider(event.includeServer(),
                new TLRItemTag(packOutput, lookupProvider, blockTags.contentsGetter(),event.getExistingFileHelper()));

        generator.addProvider(event.includeServer(),new TLRRecipe(packOutput,lookupProvider));
    }
}

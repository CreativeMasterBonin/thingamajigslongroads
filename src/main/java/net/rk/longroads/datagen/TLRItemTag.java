package net.rk.longroads.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.rk.longroads.ThingamajigsLongRoads;
import net.rk.longroads.block.TLRBlocks;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class TLRItemTag extends ItemTagsProvider{
    public TLRItemTag(PackOutput p_275343_, CompletableFuture<HolderLookup.Provider> p_275729_, CompletableFuture<TagLookup<Block>> p_275322_, @Nullable ExistingFileHelper existingFileHelper) {
        super(p_275343_, p_275729_, p_275322_, ThingamajigsLongRoads.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider prvdr) {
        this.tag(TLRTag.ASPHALT_ITEMS)
                .add(TLRBlocks.ASPHALT.asItem())
                .add(TLRBlocks.ASPHALT_OK.asItem())
                .add(TLRBlocks.ASPHALT_MEDIOCRE.asItem())
                .add(TLRBlocks.ASPHALT_OLD.asItem())
        ;

        this.tag(TLRTag.ASPHALT_SLABS_ITEMS)
                .add(TLRBlocks.ASPHALT_SLAB.asItem())
                .add(TLRBlocks.ASPHALT_OK_SLAB.asItem())
                .add(TLRBlocks.ASPHALT_MEDIOCRE_SLAB.asItem())
                .add(TLRBlocks.ASPHALT_OLD_SLAB.asItem())
        ;
    }
}

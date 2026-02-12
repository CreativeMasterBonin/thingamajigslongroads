package net.rk.longroads.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.rk.longroads.ThingamajigsLongRoads;
import net.rk.longroads.block.TLRBlocks;
import net.rk.thingamajigs.item.TItems;
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

        this.tag(TLRTag.ROAD_SIGN_CRAFTING_GLOBS)
                .add(TItems.TRIANGLE_SIGN_GLOB.asItem())
                .add(TItems.SQUARE_SIGN_GLOB.asItem())
                .add(TItems.CIRCLE_SIGN_GLOB.asItem())
                .add(TItems.SIGN_GLOB.asItem())
                .add(TItems.MISC_SIGN_GLOB.asItem())
        ;

        this.tag(TLRTag.ROADWAY_SIGN_CRAFTING_SIGNS)
                .add(Items.ACACIA_SIGN)
                .add(Items.SPRUCE_SIGN)
                .add(Items.BAMBOO_SIGN)
                .add(Items.OAK_SIGN)
                .add(Items.DARK_OAK_SIGN)
                .add(Items.BIRCH_SIGN)
                .add(Items.CHERRY_SIGN)
                .add(Items.CRIMSON_SIGN)
                .add(Items.WARPED_SIGN)
                .add(Items.JUNGLE_SIGN)
                .add(Items.MANGROVE_SIGN)
        ;

        this.tag(TLRTag.ROADWAY_SIGN_EDIT_TOOLS)
                .addTag(Tags.Items.TOOLS)
                .add(TItems.PAINT_BRUSH.asItem())
                .add(Items.BRUSH)
        ;
    }
}

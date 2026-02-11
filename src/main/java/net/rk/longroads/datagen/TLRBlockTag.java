package net.rk.longroads.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.rk.longroads.ThingamajigsLongRoads;
import net.rk.longroads.block.TLRBlocks;
import net.rk.thingamajigs.Thingamajigs;
import net.rk.thingamajigs.datagen.TTag;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class TLRBlockTag extends BlockTagsProvider{
    public TLRBlockTag(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, ThingamajigsLongRoads.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider tc){
        this.tag(TLRTag.ASPHALT_BLOCKS)
                .add(TLRBlocks.ASPHALT.get())
                .add(TLRBlocks.ASPHALT_OK.get())
                .add(TLRBlocks.ASPHALT_MEDIOCRE.get())
                .add(TLRBlocks.ASPHALT_OLD.get())
        ;

        this.tag(TLRTag.ASPHALT_SLABS_BLOCKS)
                .add(TLRBlocks.ASPHALT_SLAB.get())
                .add(TLRBlocks.ASPHALT_OK_SLAB.get())
                .add(TLRBlocks.ASPHALT_MEDIOCRE_SLAB.get())
                .add(TLRBlocks.ASPHALT_OLD_SLAB.get())
        ;

        this.tag(TTag.VERTICAL_REDSTONE_BLOCKS)
                .add(TLRBlocks.VERTICAL_REDSTONE_SIDEWALK.get())
        ;

        this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(TLRBlocks.VERTICAL_REDSTONE_SIDEWALK.get())
                .addTag(TLRTag.ASPHALT_BLOCKS)
                .addTag(TLRTag.ASPHALT_SLABS_BLOCKS)
                .add(TLRBlocks.SIDEWALK.get())
                .add(TLRBlocks.SIDEWALK_CRACKED.get())
                .add(TLRBlocks.SIDEWALK_BLOCKED.get())
                .add(TLRBlocks.SIDEWALK_SECTIONED.get())
                .add(TLRBlocks.SIDEWALK_SLAB.get())
                .add(TLRBlocks.CRACKED_SIDEWALK_SLAB.get())
                .add(TLRBlocks.BLOCKED_SIDEWALK_SLAB.get())
                .add(TLRBlocks.SECTIONED_SIDEWALK_SLAB.get())
                .add(TLRBlocks.DOUBLE_WHITE_ASPHALT.get())
                .add(TLRBlocks.DOUBLE_CORNER_WHITE_ASPHALT.get())
                .add(TLRBlocks.WHITE_PARKING_ASPHALT.get())
                .add(TLRBlocks.WHITE_PARKING_ASPHALT_SLAB.get())
                .add(TLRBlocks.WHITE_PARKING_OK_ASPHALT_SLAB.get())
                .add(TLRBlocks.WHITE_PARKING_MEDIOCRE_ASPHALT_SLAB.get())
                .add(TLRBlocks.WHITE_PARKING_OLD_ASPHALT_SLAB.get())
                .add(TLRBlocks.WHITE_D_ASPHALT_SLAB.get())
                .add(TLRBlocks.WHITE_D_OK_ASPHALT_SLAB.get())
                .add(TLRBlocks.WHITE_D_MEDIOCRE_ASPHALT_SLAB.get())
                .add(TLRBlocks.WHITE_D_OLD_ASPHALT_SLAB.get())
                .add(TLRBlocks.WHITE_DT_ASPHALT_SLAB.get())
                .add(TLRBlocks.WHITE_DT_OK_ASPHALT_SLAB.get())
                .add(TLRBlocks.WHITE_DT_MEDIOCRE_ASPHALT_SLAB.get())
                .add(TLRBlocks.WHITE_DT_OLD_ASPHALT_SLAB.get())
                .add(TLRBlocks.DOUBLE_YELLOW_ASPHALT.get())
                .add(TLRBlocks.DOUBLE_CORNER_YELLOW_ASPHALT.get())
                .add(TLRBlocks.YELLOW_PARKING_ASPHALT.get())
                .add(TLRBlocks.YELLOW_PARKING_ASPHALT_SLAB.get())
                .add(TLRBlocks.YELLOW_PARKING_OK_ASPHALT_SLAB.get())
                .add(TLRBlocks.YELLOW_PARKING_MEDIOCRE_ASPHALT_SLAB.get())
                .add(TLRBlocks.YELLOW_PARKING_OLD_ASPHALT_SLAB.get())
                .add(TLRBlocks.YELLOW_D_ASPHALT_SLAB.get())
                .add(TLRBlocks.YELLOW_D_OK_ASPHALT_SLAB.get())
                .add(TLRBlocks.YELLOW_D_MEDIOCRE_ASPHALT_SLAB.get())
                .add(TLRBlocks.YELLOW_D_OLD_ASPHALT_SLAB.get())
                .add(TLRBlocks.YELLOW_DT_ASPHALT_SLAB.get())
                .add(TLRBlocks.YELLOW_DT_OK_ASPHALT_SLAB.get())
                .add(TLRBlocks.YELLOW_DT_MEDIOCRE_ASPHALT_SLAB.get())
                .add(TLRBlocks.YELLOW_DT_OLD_ASPHALT_SLAB.get())
                .add(TLRBlocks.BLUE_PARKING_ASPHALT.get())
                .add(TLRBlocks.BLUE_PARKING_ASPHALT_SLAB.get())
                .add(TLRBlocks.BLUE_PARKING_OK_ASPHALT_SLAB.get())
                .add(TLRBlocks.BLUE_PARKING_MEDIOCRE_ASPHALT_SLAB.get())
                .add(TLRBlocks.BLUE_PARKING_OLD_ASPHALT_SLAB.get())
                // signs
                .add(TLRBlocks.GREEN_ROADWAY_STANDING_SIGN.get())
                .add(TLRBlocks.GREEN_ROADWAY_WALL_SIGN.get())
                .add(TLRBlocks.RED_ROADWAY_STANDING_SIGN.get())
                .add(TLRBlocks.RED_ROADWAY_WALL_SIGN.get())
                .add(TLRBlocks.BLUE_ROADWAY_STANDING_SIGN.get())
                .add(TLRBlocks.BLUE_ROADWAY_WALL_SIGN.get())
                .add(TLRBlocks.BROWN_ROADWAY_STANDING_SIGN.get())
                .add(TLRBlocks.BROWN_ROADWAY_WALL_SIGN.get())
                .add(TLRBlocks.GREEN_HANGING_SIGN.get())
                .add(TLRBlocks.GREEN_WALL_HANGING_SIGN.get())
        ;

        this.tag(Tags.Blocks.RELOCATION_NOT_SUPPORTED)
                .add(TLRBlocks.VERTICAL_REDSTONE_SIDEWALK.get())
        ;

        this.tag(BlockTags.DRAGON_TRANSPARENT)
                .add(TLRBlocks.VERTICAL_REDSTONE_SIDEWALK.get())
        ;

        this.tag(BlockTags.WITHER_IMMUNE)
                .add(TLRBlocks.VERTICAL_REDSTONE_SIDEWALK.get())
        ;

        this.tag(BlockTags.NEEDS_STONE_TOOL)
                .add(TLRBlocks.VERTICAL_REDSTONE_SIDEWALK.get())
                .addTag(TLRTag.ASPHALT_BLOCKS)
                .addTag(TLRTag.ASPHALT_SLABS_BLOCKS)
                .add(TLRBlocks.SIDEWALK.get())
                .add(TLRBlocks.SIDEWALK_CRACKED.get())
                .add(TLRBlocks.SIDEWALK_BLOCKED.get())
                .add(TLRBlocks.SIDEWALK_SECTIONED.get())
                .add(TLRBlocks.SIDEWALK_SLAB.get())
                .add(TLRBlocks.CRACKED_SIDEWALK_SLAB.get())
                .add(TLRBlocks.BLOCKED_SIDEWALK_SLAB.get())
                .add(TLRBlocks.SECTIONED_SIDEWALK_SLAB.get())
                .add(TLRBlocks.DOUBLE_WHITE_ASPHALT.get())
                .add(TLRBlocks.DOUBLE_CORNER_WHITE_ASPHALT.get())
                .add(TLRBlocks.WHITE_PARKING_ASPHALT.get())
                .add(TLRBlocks.WHITE_PARKING_ASPHALT_SLAB.get())
                .add(TLRBlocks.WHITE_PARKING_OK_ASPHALT_SLAB.get())
                .add(TLRBlocks.WHITE_PARKING_MEDIOCRE_ASPHALT_SLAB.get())
                .add(TLRBlocks.WHITE_PARKING_OLD_ASPHALT_SLAB.get())
                .add(TLRBlocks.WHITE_D_ASPHALT_SLAB.get())
                .add(TLRBlocks.WHITE_D_OK_ASPHALT_SLAB.get())
                .add(TLRBlocks.WHITE_D_MEDIOCRE_ASPHALT_SLAB.get())
                .add(TLRBlocks.WHITE_D_OLD_ASPHALT_SLAB.get())
                .add(TLRBlocks.WHITE_DT_ASPHALT_SLAB.get())
                .add(TLRBlocks.WHITE_DT_OK_ASPHALT_SLAB.get())
                .add(TLRBlocks.WHITE_DT_MEDIOCRE_ASPHALT_SLAB.get())
                .add(TLRBlocks.WHITE_DT_OLD_ASPHALT_SLAB.get())
                .add(TLRBlocks.DOUBLE_YELLOW_ASPHALT.get())
                .add(TLRBlocks.DOUBLE_CORNER_YELLOW_ASPHALT.get())
                .add(TLRBlocks.YELLOW_PARKING_ASPHALT.get())
                .add(TLRBlocks.YELLOW_PARKING_ASPHALT_SLAB.get())
                .add(TLRBlocks.YELLOW_PARKING_OK_ASPHALT_SLAB.get())
                .add(TLRBlocks.YELLOW_PARKING_MEDIOCRE_ASPHALT_SLAB.get())
                .add(TLRBlocks.YELLOW_PARKING_OLD_ASPHALT_SLAB.get())
                .add(TLRBlocks.YELLOW_D_ASPHALT_SLAB.get())
                .add(TLRBlocks.YELLOW_D_OK_ASPHALT_SLAB.get())
                .add(TLRBlocks.YELLOW_D_MEDIOCRE_ASPHALT_SLAB.get())
                .add(TLRBlocks.YELLOW_D_OLD_ASPHALT_SLAB.get())
                .add(TLRBlocks.YELLOW_DT_ASPHALT_SLAB.get())
                .add(TLRBlocks.YELLOW_DT_OK_ASPHALT_SLAB.get())
                .add(TLRBlocks.YELLOW_DT_MEDIOCRE_ASPHALT_SLAB.get())
                .add(TLRBlocks.YELLOW_DT_OLD_ASPHALT_SLAB.get())
                .add(TLRBlocks.BLUE_PARKING_ASPHALT.get())
                .add(TLRBlocks.BLUE_PARKING_ASPHALT_SLAB.get())
                .add(TLRBlocks.BLUE_PARKING_OK_ASPHALT_SLAB.get())
                .add(TLRBlocks.BLUE_PARKING_MEDIOCRE_ASPHALT_SLAB.get())
                .add(TLRBlocks.BLUE_PARKING_OLD_ASPHALT_SLAB.get())
        ;

        this.tag(BlockTags.INCORRECT_FOR_WOODEN_TOOL)
                .addTag(TLRTag.ASPHALT_BLOCKS)
                .addTag(TLRTag.ASPHALT_SLABS_BLOCKS)
                .add(TLRBlocks.SIDEWALK.get())
                .add(TLRBlocks.SIDEWALK_CRACKED.get())
                .add(TLRBlocks.SIDEWALK_BLOCKED.get())
                .add(TLRBlocks.SIDEWALK_SECTIONED.get())
                .add(TLRBlocks.SIDEWALK_SLAB.get())
                .add(TLRBlocks.CRACKED_SIDEWALK_SLAB.get())
                .add(TLRBlocks.BLOCKED_SIDEWALK_SLAB.get())
                .add(TLRBlocks.SECTIONED_SIDEWALK_SLAB.get())
                .add(TLRBlocks.DOUBLE_WHITE_ASPHALT.get())
                .add(TLRBlocks.DOUBLE_CORNER_WHITE_ASPHALT.get())
                .add(TLRBlocks.WHITE_PARKING_ASPHALT.get())
                .add(TLRBlocks.WHITE_PARKING_ASPHALT_SLAB.get())
                .add(TLRBlocks.WHITE_PARKING_OK_ASPHALT_SLAB.get())
                .add(TLRBlocks.WHITE_PARKING_MEDIOCRE_ASPHALT_SLAB.get())
                .add(TLRBlocks.WHITE_PARKING_OLD_ASPHALT_SLAB.get())
                .add(TLRBlocks.WHITE_D_ASPHALT_SLAB.get())
                .add(TLRBlocks.WHITE_D_OK_ASPHALT_SLAB.get())
                .add(TLRBlocks.WHITE_D_MEDIOCRE_ASPHALT_SLAB.get())
                .add(TLRBlocks.WHITE_D_OLD_ASPHALT_SLAB.get())
                .add(TLRBlocks.WHITE_DT_ASPHALT_SLAB.get())
                .add(TLRBlocks.WHITE_DT_OK_ASPHALT_SLAB.get())
                .add(TLRBlocks.WHITE_DT_MEDIOCRE_ASPHALT_SLAB.get())
                .add(TLRBlocks.WHITE_DT_OLD_ASPHALT_SLAB.get())
                .add(TLRBlocks.DOUBLE_YELLOW_ASPHALT.get())
                .add(TLRBlocks.DOUBLE_CORNER_YELLOW_ASPHALT.get())
                .add(TLRBlocks.YELLOW_PARKING_ASPHALT.get())
                .add(TLRBlocks.YELLOW_PARKING_ASPHALT_SLAB.get())
                .add(TLRBlocks.YELLOW_PARKING_OK_ASPHALT_SLAB.get())
                .add(TLRBlocks.YELLOW_PARKING_MEDIOCRE_ASPHALT_SLAB.get())
                .add(TLRBlocks.YELLOW_PARKING_OLD_ASPHALT_SLAB.get())
                .add(TLRBlocks.YELLOW_D_ASPHALT_SLAB.get())
                .add(TLRBlocks.YELLOW_D_OK_ASPHALT_SLAB.get())
                .add(TLRBlocks.YELLOW_D_MEDIOCRE_ASPHALT_SLAB.get())
                .add(TLRBlocks.YELLOW_D_OLD_ASPHALT_SLAB.get())
                .add(TLRBlocks.YELLOW_DT_ASPHALT_SLAB.get())
                .add(TLRBlocks.YELLOW_DT_OK_ASPHALT_SLAB.get())
                .add(TLRBlocks.YELLOW_DT_MEDIOCRE_ASPHALT_SLAB.get())
                .add(TLRBlocks.YELLOW_DT_OLD_ASPHALT_SLAB.get())
                .add(TLRBlocks.BLUE_PARKING_ASPHALT.get())
                .add(TLRBlocks.BLUE_PARKING_ASPHALT_SLAB.get())
                .add(TLRBlocks.BLUE_PARKING_OK_ASPHALT_SLAB.get())
                .add(TLRBlocks.BLUE_PARKING_MEDIOCRE_ASPHALT_SLAB.get())
                .add(TLRBlocks.BLUE_PARKING_OLD_ASPHALT_SLAB.get())
        ;
    }

    @Override
    public String getName() {
        return "Thingamajigs Addon Long Roads Block Tags";
    }
}

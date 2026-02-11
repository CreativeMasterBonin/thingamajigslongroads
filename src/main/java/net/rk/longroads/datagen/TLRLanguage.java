package net.rk.longroads.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.rk.longroads.ThingamajigsLongRoads;
import net.rk.longroads.block.TLRBlocks;
import net.rk.longroads.item.TLRItems;
import net.rk.longroads.registries.SignType;
import net.rk.longroads.util.SignTypeKeys;

public class TLRLanguage extends LanguageProvider {
    public TLRLanguage(PackOutput output, String locale) {
        super(output, ThingamajigsLongRoads.MODID, locale);
    }

    public void addSignType(ResourceKey<SignType> signType, String name) {
        this.add("sign_type." + signType.location().getPath() + ".name",name);
    }

    @Override
    protected void addTranslations() {
        this.add("itemGroup.thingamajigslongroads","Thingamajigs Long Roads");
        this.add("item.paint_brush.data.pattern_type", "Type: %s");
        this.add("item.thingamajigslongroads.scrape_tool.desc", "Scrapes paint off of asphalt and other blocks");
        this.add("container.thingamajigslongroads.dynamic_sign.sign_type", "Type: ");
        this.add("editbox.thingamajigslongroads.hint.dynamic_sign", "Type Resource Here");
        this.add("title.thingamajigslongroads.dynamic_road_sign","Dynamic Sign");
        this.add("button.thingamajigslongroads.rotation_left","<- Rotation");
        this.add("button.thingamajigslongroads.rotation_right","Rotation ->");

        this.addSignType(SignTypeKeys.PlACEHOLDER,"Placeholder For Fun!");
        // asphalt
        this.addBlock(TLRBlocks.ASPHALT,"Asphalt");
        this.addBlock(TLRBlocks.ASPHALT_OK,"Ok Asphalt");
        this.addBlock(TLRBlocks.ASPHALT_MEDIOCRE,"Mediocre Asphalt");
        this.addBlock(TLRBlocks.ASPHALT_OLD,"Old Asphalt");
        this.addBlock(TLRBlocks.ASPHALT_SLAB,"Asphalt Slab");
        this.addBlock(TLRBlocks.ASPHALT_OK_SLAB,"Ok Asphalt Slab");
        this.addBlock(TLRBlocks.ASPHALT_MEDIOCRE_SLAB,"Mediocre Asphalt Slab");
        this.addBlock(TLRBlocks.ASPHALT_OLD_SLAB,"Old Asphalt Slab");
        // sidewalk
        this.addBlock(TLRBlocks.SIDEWALK,"Sidewalk");
        this.addBlock(TLRBlocks.SIDEWALK_CRACKED,"Cracked Sidewalk");
        this.addBlock(TLRBlocks.SIDEWALK_BLOCKED,"Blocked Sidewalk");
        this.addBlock(TLRBlocks.SIDEWALK_SECTIONED,"Sectioned Sidewalk");
        this.addBlock(TLRBlocks.SIDEWALK_SLAB,"Sidewalk Slab");
        this.addBlock(TLRBlocks.CRACKED_SIDEWALK_SLAB,"Cracked Sidewalk Slab");
        this.addBlock(TLRBlocks.BLOCKED_SIDEWALK_SLAB,"Blocked Sidewalk Slab");
        this.addBlock(TLRBlocks.SECTIONED_SIDEWALK_SLAB,"Sectioned Sidewalk Slab");
        // painted asphalt
        this.addBlock(TLRBlocks.DOUBLE_WHITE_ASPHALT,"Double White Asphalt");
        this.addBlock(TLRBlocks.DOUBLE_CORNER_WHITE_ASPHALT,"Double Corner White Asphalt");
        this.addBlock(TLRBlocks.DOUBLE_YELLOW_ASPHALT,"Double Yellow Asphalt");
        this.addBlock(TLRBlocks.DOUBLE_CORNER_YELLOW_ASPHALT,"Double Corner Yellow Asphalt");
        this.addBlock(TLRBlocks.WHITE_PARKING_ASPHALT,"White Parking Asphalt");
        this.addBlock(TLRBlocks.YELLOW_PARKING_ASPHALT,"Yellow Parking Asphalt");
        this.addBlock(TLRBlocks.BLUE_PARKING_ASPHALT,"Blue Parking Asphalt");

        this.addBlock(TLRBlocks.WHITE_PARKING_ASPHALT_SLAB,"Asphalt Slab (Thin White Parking Line)");
        this.addBlock(TLRBlocks.WHITE_PARKING_OK_ASPHALT_SLAB,"Ok Asphalt Slab (Thin White Parking Line)");
        this.addBlock(TLRBlocks.WHITE_PARKING_MEDIOCRE_ASPHALT_SLAB,"Mediocre Asphalt Slab (Thin White Parking Line)");
        this.addBlock(TLRBlocks.WHITE_PARKING_OLD_ASPHALT_SLAB,"Old Asphalt Slab (Thin White Parking Line)");

        this.addBlock(TLRBlocks.WHITE_DT_ASPHALT_SLAB,"Asphalt Slab (Double White Corner)");
        this.addBlock(TLRBlocks.WHITE_DT_OK_ASPHALT_SLAB,"Ok Asphalt Slab (Double White Corner)");
        this.addBlock(TLRBlocks.WHITE_DT_MEDIOCRE_ASPHALT_SLAB,"Mediocre Asphalt Slab (Double White Corner)");
        this.addBlock(TLRBlocks.WHITE_DT_OLD_ASPHALT_SLAB,"Old Asphalt Slab (Double White Corner)");

        this.addBlock(TLRBlocks.BLUE_PARKING_ASPHALT_SLAB,"Asphalt Slab (Thin Blue Parking Line)");
        this.addBlock(TLRBlocks.BLUE_PARKING_OK_ASPHALT_SLAB,"Ok Asphalt Slab (Thin Blue Parking Line)");
        this.addBlock(TLRBlocks.BLUE_PARKING_MEDIOCRE_ASPHALT_SLAB,"Mediocre Asphalt Slab (Thin Blue Parking Line)");
        this.addBlock(TLRBlocks.BLUE_PARKING_OLD_ASPHALT_SLAB,"Old Asphalt Slab (Thin Blue Parking Line)");

        this.addBlock(TLRBlocks.YELLOW_DT_ASPHALT_SLAB,"Asphalt Slab (Double Yellow Corner)");
        this.addBlock(TLRBlocks.YELLOW_DT_OK_ASPHALT_SLAB,"Ok Asphalt Slab (Double Yellow Corner)");
        this.addBlock(TLRBlocks.YELLOW_DT_MEDIOCRE_ASPHALT_SLAB,"Mediocre Asphalt Slab (Double Yellow Corner)");
        this.addBlock(TLRBlocks.YELLOW_DT_OLD_ASPHALT_SLAB,"Old Asphalt Slab (Double Yellow Corner)");

        this.addBlock(TLRBlocks.YELLOW_PARKING_ASPHALT_SLAB,"Asphalt Slab (Thin Yellow Parking Line)");
        this.addBlock(TLRBlocks.YELLOW_PARKING_OK_ASPHALT_SLAB,"Ok Asphalt Slab (Thin Yellow Parking Line)");
        this.addBlock(TLRBlocks.YELLOW_PARKING_MEDIOCRE_ASPHALT_SLAB,"Mediocre Asphalt Slab (Thin Yellow Parking Line)");
        this.addBlock(TLRBlocks.YELLOW_PARKING_OLD_ASPHALT_SLAB,"Old Asphalt Slab (Thin Yellow Parking Line)");

        this.addBlock(TLRBlocks.YELLOW_D_ASPHALT_SLAB,"Asphalt Slab (Double Yellow)");
        this.addBlock(TLRBlocks.YELLOW_D_OK_ASPHALT_SLAB,"Ok Asphalt Slab (Double Yellow)");
        this.addBlock(TLRBlocks.YELLOW_D_MEDIOCRE_ASPHALT_SLAB,"Mediocre Asphalt Slab (Double Yellow)");
        this.addBlock(TLRBlocks.YELLOW_D_OLD_ASPHALT_SLAB,"Old Asphalt Slab (Double Yellow)");

        this.addBlock(TLRBlocks.WHITE_D_ASPHALT_SLAB,"Asphalt Slab (Double White)");
        this.addBlock(TLRBlocks.WHITE_D_OK_ASPHALT_SLAB,"Ok Asphalt Slab (Double White)");
        this.addBlock(TLRBlocks.WHITE_D_MEDIOCRE_ASPHALT_SLAB,"Mediocre Asphalt Slab (Double White)");
        this.addBlock(TLRBlocks.WHITE_D_OLD_ASPHALT_SLAB,"Old Asphalt Slab (Double White)");

        this.addBlock(TLRBlocks.VERTICAL_REDSTONE_SIDEWALK,"Vertical Redstone Sidewalk");
        this.addBlock(TLRBlocks.ROAD_SIGN,"Road Sign");


        this.addItem(TLRItems.SCRAPE_TOOL,"Scrape Tool");
        this.addItem(TLRItems.PAINT_BRUSH,"Paintbrush");
        this.addItem(TLRItems.WHITE_PAINT_BRUSH,"White Paintbrush");
        this.addItem(TLRItems.YELLOW_PAINT_BRUSH,"Yellow Paintbrush");
        this.addItem(TLRItems.BLUE_PAINT_BRUSH,"Blue Paintbrush");
        // green roadway sign
        this.addItem(TLRItems.GREEN_ROADWAY_SIGN_ITEM,"Green Roadway Sign");
        // green hanging sign
        this.addItem(TLRItems.GREEN_HANGING_ROADWAY_SIGN_ITEM,"Hanging Green Roadway Sign");
        // red roadway sign
        this.addItem(TLRItems.RED_ROADWAY_SIGN_ITEM,"Red Roadway Sign");
        // blue roadway sign
        this.addItem(TLRItems.BLUE_ROADWAY_SIGN_ITEM,"Blue Roadway Sign");
        // brown roadway sign
        this.addItem(TLRItems.BROWN_ROADWAY_SIGN_ITEM,"Brown Roadway Sign");
        // road markings
        this.addBlock(TLRBlocks.WHITE_ROAD_MARKING,"White Road Marking");
        this.addBlock(TLRBlocks.YELLOW_ROAD_MARKING,"Yellow Road Marking");
        this.addBlock(TLRBlocks.BLUE_ROAD_MARKING,"Blue Road Marking");
        //
    }
}

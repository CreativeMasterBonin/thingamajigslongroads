package net.rk.longroads.util;

import com.mojang.logging.LogUtils;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;

public class Utilities{
    /* formatting:
        name
        hand can open
        wind charge activatible
        arrow activatible button
        sensitivity
        sound type
        door close
        door open
        trapdoor close
        trapdoor open
        plate off
        plate on
        button click off
        button click on
    */

    private static final org.slf4j.Logger UTILITY_LOGGER = LogUtils.getLogger();

    private static final boolean DEBUG_ONLY = true;

    public static final BlockSetType GREEN_ROADWAY_TYPE = new BlockSetType(
            "green_roadway",
            true,
            true,
            true,
            BlockSetType.PressurePlateSensitivity.EVERYTHING,
            SoundType.LANTERN,
            SoundEvents.IRON_DOOR_CLOSE,
            SoundEvents.IRON_DOOR_OPEN,
            SoundEvents.IRON_TRAPDOOR_CLOSE,
            SoundEvents.IRON_TRAPDOOR_OPEN,
            SoundEvents.METAL_PRESSURE_PLATE_CLICK_OFF,
            SoundEvents.METAL_PRESSURE_PLATE_CLICK_ON,
            SoundEvents.STONE_BUTTON_CLICK_OFF,
            SoundEvents.STONE_BUTTON_CLICK_ON
    );
    public static final WoodType GREEN_ROADWAY_WOOD = new WoodType("green_roadway_wood", GREEN_ROADWAY_TYPE);


    public static final BlockSetType RED_ROADWAY_TYPE = new BlockSetType(
            "red_roadway",
            true,
            true,
            true,
            BlockSetType.PressurePlateSensitivity.EVERYTHING,
            SoundType.LANTERN,
            SoundEvents.IRON_DOOR_CLOSE,
            SoundEvents.IRON_DOOR_OPEN,
            SoundEvents.IRON_TRAPDOOR_CLOSE,
            SoundEvents.IRON_TRAPDOOR_OPEN,
            SoundEvents.METAL_PRESSURE_PLATE_CLICK_OFF,
            SoundEvents.METAL_PRESSURE_PLATE_CLICK_ON,
            SoundEvents.STONE_BUTTON_CLICK_OFF,
            SoundEvents.STONE_BUTTON_CLICK_ON
    );
    public static final WoodType RED_ROADWAY_WOOD = new WoodType("red_roadway_wood", RED_ROADWAY_TYPE);

    public static final BlockSetType BLUE_ROADWAY_TYPE = new BlockSetType(
            "blue_roadway",
            true,
            true,
            true,
            BlockSetType.PressurePlateSensitivity.EVERYTHING,
            SoundType.LANTERN,
            SoundEvents.IRON_DOOR_CLOSE,
            SoundEvents.IRON_DOOR_OPEN,
            SoundEvents.IRON_TRAPDOOR_CLOSE,
            SoundEvents.IRON_TRAPDOOR_OPEN,
            SoundEvents.METAL_PRESSURE_PLATE_CLICK_OFF,
            SoundEvents.METAL_PRESSURE_PLATE_CLICK_ON,
            SoundEvents.STONE_BUTTON_CLICK_OFF,
            SoundEvents.STONE_BUTTON_CLICK_ON
    );
    public static final WoodType BLUE_ROADWAY_WOOD = new WoodType("blue_roadway_wood", BLUE_ROADWAY_TYPE);


    public static final BlockSetType BROWN_ROADWAY_TYPE = new BlockSetType(
            "brown_roadway",
            true,
            true,
            true,
            BlockSetType.PressurePlateSensitivity.EVERYTHING,
            SoundType.LANTERN,
            SoundEvents.IRON_DOOR_CLOSE,
            SoundEvents.IRON_DOOR_OPEN,
            SoundEvents.IRON_TRAPDOOR_CLOSE,
            SoundEvents.IRON_TRAPDOOR_OPEN,
            SoundEvents.METAL_PRESSURE_PLATE_CLICK_OFF,
            SoundEvents.METAL_PRESSURE_PLATE_CLICK_ON,
            SoundEvents.STONE_BUTTON_CLICK_OFF,
            SoundEvents.STONE_BUTTON_CLICK_ON
    );
    public static final WoodType BROWN_ROADWAY_WOOD = new WoodType("brown_roadway_wood", BROWN_ROADWAY_TYPE);

    // sign type related stuff
    public static final boolean verboseLogging = false;
    public static final String missingLocation = "thingamajigslongroads:textures/entity/signs/sign_error.png";
    public static final String missingLocationBig = "thingamajigslongroads:textures/entity/signs/big_sign_error.png";
    public static final String missingLocationRect = "thingamajigslongroads:textures/entity/signs/rect_sign_error.png";

    // Sign Model Types are hardcoded because models CANNOT be generated on the fly
    // these can be picked by specifying the type in the SignType definition file
    public enum SignModelTypes{
        SQUARE("square",0),
        DOUBLE_SQUARE("double_square",1),
        RECTANGLE("rectangle",2),
        CUSTOM("custom",3);

        private final String modelTypeName;
        private final int id;

        SignModelTypes(String modelTypeName, int id) {
            this.modelTypeName = modelTypeName;
            this.id = id;
        }

        public String getModelTypeName() {
            return modelTypeName;
        }

        public int getId() {
            return id;
        }
    }
}

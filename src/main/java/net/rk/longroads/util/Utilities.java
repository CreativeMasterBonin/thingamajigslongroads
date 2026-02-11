package net.rk.longroads.util;

import com.mojang.logging.LogUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.neoforged.fml.loading.FMLPaths;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileAttribute;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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


    // road sign custom loading support

    public static final String missingLocation = "thingamajigslongroads:textures/entity/signs/sign_error.png";

    /*private static final Path ROAD_SIGNS_DIR_PATH = FMLPaths.getOrCreateGameRelativePath(Path.of("thingamajigs_road_signs"));

    public static List<String> signTextureLocs;

    public static List<ResourceLocation> allSignResources;

    public static String getRoadSignsDirPathAsString(){
        return ROAD_SIGNS_DIR_PATH.toString();
    }

    public static void findAndApplySignTextures() throws IOException{
        ArrayList<String> arrTempFileNameList = new ArrayList<>();
        ArrayList<ResourceLocation> arrTempResourceList = new ArrayList<>();

        if(DEBUG_ONLY){
            UTILITY_LOGGER.info("TLong Roads dir sign path: {}", ROAD_SIGNS_DIR_PATH.toString());
        }

        if(ROAD_SIGNS_DIR_PATH == null){
            UTILITY_LOGGER.info("TLong Roads directory is missing! Cannot continue with custom sign texture loading.");
            return;
        }
        else{
            Set<String> imgFileStrs = Stream.of(new File(ROAD_SIGNS_DIR_PATH.toUri()).listFiles())
                    .filter(file -> !file.isDirectory())
                    .map(File::getName)
                    .collect(Collectors.toSet());
            Object[] finFilStrArr = imgFileStrs.toArray();

            List<Object> strImgFileList = Arrays.stream(finFilStrArr).toList();

            for(int i = 0; i < imgFileStrs.size(); i++) {
                UTILITY_LOGGER.info("TLong Roads img file at {} is: File String: {}", i, strImgFileList.get(i).toString());
                String newLocationName = strImgFileList.get(i).toString();
                if(strImgFileList.get(i).toString().equals(".ds_store")
                        || strImgFileList.get(i).toString().equals(".DS_Store")
                        || strImgFileList.get(i).toString().equals("Thumbs.db")
                        || strImgFileList.get(i).toString().equals("thumbs.db")){
                    UTILITY_LOGGER.info("Skipping file system file: {} as it has no purpose for TLong Roads.", strImgFileList.get(i).toString());
                }
                else if(!strImgFileList.get(i).toString().contains(".png")){
                    UTILITY_LOGGER.info("Skipping unsupported file type for TLong Roads. Supported formats: .png");
                }
                else{
                    arrTempFileNameList.add(newLocationName);
                }
            }

            signTextureLocs = arrTempFileNameList.stream().toList();

            if(DEBUG_ONLY){
                for(int j = 0; j < signTextureLocs.size(); j++){
                    UTILITY_LOGGER.info("signTextureLoc index: {} -> File Location: {}", j, signTextureLocs.get(j));
                }
            }

            ArrayList<String> tempResourceStringyArr = new ArrayList<>();

            for(int j = 0; j < signTextureLocs.size(); j++){
                arrTempResourceList.add(ResourceLocation.parse("thingamajigslongroads:textures/custom_signs/" + signTextureLocs.get(j)));
                tempResourceStringyArr.add("thingamajigslongroads:textures/custom_signs/" + signTextureLocs.get(j));
                if(DEBUG_ONLY){
                    UTILITY_LOGGER.info("all Sign Res index: {} -> File: {}", j, arrTempResourceList.get(j));
                }
            }

            allSignResources = arrTempResourceList.stream().toList();

            Path configfile = Path.of(ROAD_SIGNS_DIR_PATH.toString(),"/loaded_signs.txt");
            if(!Files.exists(configfile)){
                Files.write(configfile, tempResourceStringyArr, StandardCharsets.UTF_8);
            }
            UTILITY_LOGGER.info("TLong Roads generated new list of sign texture locations from directory.");
        }
    }

    public static String loadLocationString(int signID){
        ResourceLocation allSignRSTemp = allSignResources.get(signID);
        return allSignRSTemp.toString();
    }

     */
}

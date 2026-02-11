package net.rk.longroads.util;

import com.mojang.blaze3d.platform.Lighting;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.neoforged.fml.loading.FMLPaths;
import net.rk.longroads.registries.TLRRegistries;
import org.joml.Quaternionf;
import org.joml.Vector3f;

import javax.annotation.Nullable;
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

    // render generic entity
    public static void renderNonLivingEntity(GuiGraphics guiGraphics, float x, float y, float scale, Vector3f translate, Quaternionf pose, @Nullable Quaternionf cameraOrientation, Entity entity){
        guiGraphics.pose().pushPose();
        guiGraphics.pose().translate((double)x, (double)y, 50.0);
        guiGraphics.pose().scale(scale, scale, -scale);
        guiGraphics.pose().translate(translate.x, translate.y, translate.z);
        guiGraphics.pose().mulPose(pose);
        Lighting.setupForEntityInInventory();
        EntityRenderDispatcher entityrenderdispatcher = Minecraft.getInstance().getEntityRenderDispatcher();
        if (cameraOrientation != null) {
            entityrenderdispatcher.overrideCameraOrientation(cameraOrientation.conjugate(new Quaternionf()).rotateY(3.1415927F));
        }

        entityrenderdispatcher.setRenderShadow(false);
        RenderSystem.runAsFancy(() -> {
            entityrenderdispatcher.render(entity, 0.0, 0.0, 0.0, 0.0F, 1.0F, guiGraphics.pose(), guiGraphics.bufferSource(), 15728880);
        });
        guiGraphics.flush();
        entityrenderdispatcher.setRenderShadow(true);
        guiGraphics.pose().popPose();
        Lighting.setupFor3DItems();
    }
}

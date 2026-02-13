package net.rk.longroads.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.rk.longroads.entity.blockentity.custom.DynamicRoadSignBE;
import net.rk.longroads.entity.blockentity.model.DynamicDoubleTallSignModel;
import net.rk.longroads.entity.blockentity.model.DynamicRectangleSignModel;
import net.rk.longroads.entity.blockentity.model.DynamicSignModel;
import net.rk.longroads.item.TLRItems;
import net.rk.thingamajigs.block.TBlocks;
import org.joml.Vector3f;

@OnlyIn(Dist.CLIENT)
public class TLRBEWLR extends BlockEntityWithoutLevelRenderer {
    private DynamicRoadSignBE dynamicRoadSignBE;
    private DynamicRoadSignBE dynamicRoadSignBEDoubleTall;
    private DynamicRoadSignBE dynamicRoadSignBERectangle;
    public static final ModelLayerLocation DYNAMIC_ROAD_SIGN_LOC = new ModelLayerLocation(
            ResourceLocation.parse("thingamajigslongroads:textures/entity/signs/sign_error.png"), "main");

    public DynamicSignModel roadSignModel;
    public DynamicDoubleTallSignModel doubleTallRoadSignModel;
    public DynamicRectangleSignModel rectangleRoadSignModel;

    public TLRBEWLR(BlockEntityRenderDispatcher blockEntityRenderDispatcher, EntityModelSet entityModelSet) {
        super(blockEntityRenderDispatcher, entityModelSet);
        this.dynamicRoadSignBE = new DynamicRoadSignBE("square");
        this.dynamicRoadSignBEDoubleTall = new DynamicRoadSignBE("double_square");
        this.dynamicRoadSignBERectangle = new DynamicRoadSignBE("rectangle");
        this.roadSignModel = new DynamicSignModel(Minecraft.getInstance().getEntityModels().bakeLayer(DYNAMIC_ROAD_SIGN_LOC));
        this.doubleTallRoadSignModel = new DynamicDoubleTallSignModel(Minecraft.getInstance().getEntityModels().bakeLayer(DynamicDoubleTallSignModel.DOUBLE_TALL_SIGN_TEXTURE_LOCATION));
        this.rectangleRoadSignModel = new DynamicRectangleSignModel(Minecraft.getInstance().getEntityModels().bakeLayer(DynamicRectangleSignModel.RECTANGLE_SIGN_TEXTURE_LOCATION));
    }

    @Override
    public void onResourceManagerReload(ResourceManager resourceManager) {
        this.roadSignModel = new DynamicSignModel(Minecraft.getInstance().getEntityModels().bakeLayer(DYNAMIC_ROAD_SIGN_LOC));
        this.doubleTallRoadSignModel = new DynamicDoubleTallSignModel(Minecraft.getInstance().getEntityModels().bakeLayer(DynamicDoubleTallSignModel.DOUBLE_TALL_SIGN_TEXTURE_LOCATION));
        this.rectangleRoadSignModel = new DynamicRectangleSignModel(Minecraft.getInstance().getEntityModels().bakeLayer(DynamicRectangleSignModel.RECTANGLE_SIGN_TEXTURE_LOCATION));
    }

    @Override
    public void renderByItem(ItemStack stack, ItemDisplayContext displayContext, PoseStack poseStack, MultiBufferSource buffer, int packedLight, int packedOverlay) {
        VertexConsumer vc;
        if(stack.is(TLRItems.DYNAMIC_ROAD_SIGN.asItem())){
            poseStack.pushPose();
            if(stack.has(DataComponents.BLOCK_ENTITY_DATA)){
                CompoundTag tag = stack.get(DataComponents.BLOCK_ENTITY_DATA).copyTag();
                if(tag != null){
                    if(tag.get("sign_texture") != null && tag.get("model_type") != null && tag.get("y_angle") != null){
                        String textureLocation = tag.getString("sign_texture");
                        String modelType = tag.getString("model_type");
                        float yAngle = tag.getFloat("y_angle");

                        poseStack.mulPose(Axis.YP.rotationDegrees(yAngle));

                        if(modelType.equals("square")){
                            if(ResourceLocation.read(textureLocation).hasResultOrPartial()){
                                vc = buffer.getBuffer(RenderType.entityCutout(ResourceLocation.parse(textureLocation)));
                            }
                            else{
                                vc = buffer.getBuffer(RenderType.entityCutout(
                                        ResourceLocation.parse("thingamajigslongroads:textures/entity/signs/sign_error.png")));
                            }
                            roadSignModel.setupAnim(dynamicRoadSignBE);
                            if(displayContext.firstPerson()){
                                if(displayContext == ItemDisplayContext.FIRST_PERSON_LEFT_HAND){
                                    roadSignModel.getMain().yRot = 1.4f;
                                    roadSignModel.getMain().x = -1.0f;
                                    roadSignModel.getMain().y = 8.0f;
                                }
                                else{
                                    roadSignModel.getMain().yRot = -1.4f;
                                    roadSignModel.getMain().x = 14.0f;
                                    roadSignModel.getMain().y = 8.0f;
                                }
                                roadSignModel.getMain().render(poseStack,vc,packedLight,packedOverlay);
                            }
                            else{
                                if(displayContext == ItemDisplayContext.GUI){
                                    roadSignModel.getMain().x = 8f;
                                    roadSignModel.getMain().y = 8f;
                                    roadSignModel.getMain().render(poseStack,vc,packedLight,packedOverlay);
                                }
                                else{
                                    if(displayContext == ItemDisplayContext.FIXED){
                                        roadSignModel.getMain().x = 8f;
                                        roadSignModel.getMain().y = 8f;
                                        roadSignModel.getMain().z = 8f;
                                        roadSignModel.getMain().yRot = 3.1415f;
                                    }
                                    else if(displayContext == ItemDisplayContext.HEAD){
                                        roadSignModel.getMain().x = 8f;
                                        roadSignModel.getMain().y = 8f;
                                        roadSignModel.getMain().z = 2f;
                                        roadSignModel.getMain().yRot = 3.1415f;
                                    }
                                    else if(displayContext == ItemDisplayContext.GROUND){
                                        roadSignModel.getMain().x = 8f;
                                        roadSignModel.getMain().y = 6f;
                                        roadSignModel.getMain().z = 8f;
                                        roadSignModel.getMain().offsetScale(new Vector3f(-0.75f,-0.75f,-0.75f));
                                        roadSignModel.getMain().yRot = 3.1415f;
                                    }
                                    else if(displayContext == ItemDisplayContext.THIRD_PERSON_LEFT_HAND){
                                        roadSignModel.getMain().x = 8.14f;
                                        roadSignModel.getMain().y = 7.15f;
                                        roadSignModel.getMain().z = 7.0f;
                                        roadSignModel.getMain().yRot = 3.1415f;
                                        poseStack.scale(0.5f,0.5f,0.5f);
                                        poseStack.translate(0.5f,0.75f,0.65f);
                                    }
                                    else if(displayContext == ItemDisplayContext.THIRD_PERSON_RIGHT_HAND){
                                        roadSignModel.getMain().x = 8.14f;
                                        roadSignModel.getMain().y = 7.15f;
                                        roadSignModel.getMain().z = 7.0f;
                                        roadSignModel.getMain().yRot = 3.1415f;
                                        poseStack.scale(0.5f,0.5f,0.5f);
                                        poseStack.translate(0.5f,0.75f,0.65f);
                                    }
                                    roadSignModel.getMain().render(poseStack,vc,packedLight,packedOverlay);
                                }
                            }
                            roadSignModel.getMain().render(poseStack,vc,packedLight,packedOverlay);
                        }
                        else if(modelType.equals("double_square")){
                            if(ResourceLocation.read(textureLocation).hasResultOrPartial()){
                                vc = buffer.getBuffer(RenderType.entityCutout(ResourceLocation.parse(textureLocation)));
                            }
                            else{
                                vc = buffer.getBuffer(RenderType.entityCutout(
                                        ResourceLocation.parse("thingamajigslongroads:textures/entity/signs/big_sign_error.png")));
                            }
                            doubleTallRoadSignModel.setupAnim(dynamicRoadSignBEDoubleTall);
                            if(displayContext.firstPerson()){
                                if(displayContext == ItemDisplayContext.FIRST_PERSON_LEFT_HAND){
                                    doubleTallRoadSignModel.getMain().yRot = 1.4f;
                                    doubleTallRoadSignModel.getMain().x = -8.0f;
                                    doubleTallRoadSignModel.getMain().y = 4.0f;
                                }
                                else{
                                    doubleTallRoadSignModel.getMain().yRot = -1.4f;
                                    doubleTallRoadSignModel.getMain().x = 24.0f;
                                    doubleTallRoadSignModel.getMain().y = 4.0f;
                                }
                                doubleTallRoadSignModel.getMain().render(poseStack,vc,packedLight,packedOverlay);
                            }
                            else{
                                if(displayContext == ItemDisplayContext.GUI){
                                    doubleTallRoadSignModel.getMain().offsetScale(
                                            new Vector3f(-0.5f,-0.5f,-0.5f));
                                    doubleTallRoadSignModel.getMain().x = 8f;
                                    doubleTallRoadSignModel.getMain().y = 4f;
                                    doubleTallRoadSignModel.getMain().render(poseStack,vc,packedLight,packedOverlay);
                                }
                                else{
                                    if(displayContext == ItemDisplayContext.FIXED){
                                        doubleTallRoadSignModel.getMain().offsetScale(
                                                new Vector3f(-0.5f,-0.5f,-0.5f));
                                        doubleTallRoadSignModel.getMain().x = 8f;
                                        doubleTallRoadSignModel.getMain().y = 4f;
                                        doubleTallRoadSignModel.getMain().z = 8f;
                                        doubleTallRoadSignModel.getMain().yRot = 3.1415f;
                                    }
                                    else if(displayContext == ItemDisplayContext.HEAD){
                                        doubleTallRoadSignModel.getMain().x = 8f;
                                        doubleTallRoadSignModel.getMain().y = 8f;
                                        doubleTallRoadSignModel.getMain().z = 2f;
                                        doubleTallRoadSignModel.getMain().yRot = 3.1415f;
                                    }
                                    else if(displayContext == ItemDisplayContext.GROUND){
                                        doubleTallRoadSignModel.getMain().x = 8f;
                                        doubleTallRoadSignModel.getMain().y = 8f;
                                        doubleTallRoadSignModel.getMain().z = 8f;
                                        doubleTallRoadSignModel.getMain().offsetScale(new Vector3f(-0.75f,-0.75f,-0.75f));
                                        doubleTallRoadSignModel.getMain().yRot = 3.1415f;
                                    }
                                    else if(displayContext == ItemDisplayContext.THIRD_PERSON_LEFT_HAND){
                                        doubleTallRoadSignModel.getMain().x = 8.14f;
                                        doubleTallRoadSignModel.getMain().y = 7.15f;
                                        doubleTallRoadSignModel.getMain().z = 7.0f;
                                        doubleTallRoadSignModel.getMain().yRot = 3.1415f;
                                        poseStack.scale(0.5f,0.5f,0.5f);
                                        poseStack.translate(0.5f,0.75f,0.65f);
                                    }
                                    else if(displayContext == ItemDisplayContext.THIRD_PERSON_RIGHT_HAND){
                                        doubleTallRoadSignModel.getMain().x = 8.14f;
                                        doubleTallRoadSignModel.getMain().y = 7.15f;
                                        doubleTallRoadSignModel.getMain().z = 7.0f;
                                        doubleTallRoadSignModel.getMain().yRot = 3.1415f;
                                        poseStack.scale(0.5f,0.5f,0.5f);
                                        poseStack.translate(0.5f,0.75f,0.65f);
                                    }
                                }
                            }
                            doubleTallRoadSignModel.getMain().render(poseStack,vc,packedLight,packedOverlay);
                        }
                        else if(modelType.equals("rectangle")){
                            if(ResourceLocation.read(textureLocation).hasResultOrPartial()){
                                vc = buffer.getBuffer(RenderType.entityCutout(ResourceLocation.parse(textureLocation)));
                            }
                            else{
                                vc = buffer.getBuffer(RenderType.entityCutout(
                                        ResourceLocation.parse("thingamajigslongroads:textures/entity/signs/rect_sign_error.png")));
                            }
                            rectangleRoadSignModel.setupAnim(dynamicRoadSignBERectangle);
                            if(displayContext.firstPerson()){
                                if(displayContext == ItemDisplayContext.FIRST_PERSON_LEFT_HAND){
                                    rectangleRoadSignModel.getMain().yRot = 1.4f;
                                    rectangleRoadSignModel.getMain().x = -4.0f;
                                    rectangleRoadSignModel.getMain().y = 4.0f;
                                }
                                else{
                                    rectangleRoadSignModel.getMain().yRot = -1.4f;
                                    rectangleRoadSignModel.getMain().x = 20.0f;
                                    rectangleRoadSignModel.getMain().y = 4.0f;
                                }
                                rectangleRoadSignModel.getMain().render(poseStack,vc,packedLight,packedOverlay);
                            }
                            else{
                                if(displayContext == ItemDisplayContext.GUI){
                                    rectangleRoadSignModel.getMain().offsetScale(
                                            new Vector3f(-0.5f,-0.5f,-0.5f));
                                    rectangleRoadSignModel.getMain().x = 8f;
                                    rectangleRoadSignModel.getMain().y = 8.5f;
                                    rectangleRoadSignModel.getMain().render(poseStack,vc,packedLight,packedOverlay);
                                }
                                else{
                                    if(displayContext == ItemDisplayContext.FIXED){
                                        rectangleRoadSignModel.getMain().offsetScale(
                                                new Vector3f(-0.5f,-0.5f,-0.5f));
                                        rectangleRoadSignModel.getMain().x = 8f;
                                        rectangleRoadSignModel.getMain().y = 8f;
                                        rectangleRoadSignModel.getMain().z = 8f;
                                        rectangleRoadSignModel.getMain().yRot = 3.1415f;
                                    }
                                    else if(displayContext == ItemDisplayContext.HEAD){
                                        rectangleRoadSignModel.getMain().x = 8f;
                                        rectangleRoadSignModel.getMain().y = 8f;
                                        rectangleRoadSignModel.getMain().z = 2f;
                                        rectangleRoadSignModel.getMain().yRot = 3.1415f;
                                    }
                                    else if(displayContext == ItemDisplayContext.GROUND){
                                        rectangleRoadSignModel.getMain().x = 8f;
                                        rectangleRoadSignModel.getMain().y = 8f;
                                        rectangleRoadSignModel.getMain().z = 8f;
                                        rectangleRoadSignModel.getMain().offsetScale(new Vector3f(-0.75f,-0.75f,-0.75f));
                                        rectangleRoadSignModel.getMain().yRot = 3.1415f;
                                    }
                                    else if(displayContext == ItemDisplayContext.THIRD_PERSON_LEFT_HAND){
                                        rectangleRoadSignModel.getMain().x = 8.14f;
                                        rectangleRoadSignModel.getMain().y = 7.15f;
                                        rectangleRoadSignModel.getMain().z = 7.0f;
                                        rectangleRoadSignModel.getMain().yRot = 3.1415f;
                                        poseStack.scale(0.5f,0.5f,0.5f);
                                        poseStack.translate(0.5f,0.75f,0.65f);
                                    }
                                    else if(displayContext == ItemDisplayContext.THIRD_PERSON_RIGHT_HAND){
                                        rectangleRoadSignModel.getMain().x = 8.14f;
                                        rectangleRoadSignModel.getMain().y = 7.15f;
                                        rectangleRoadSignModel.getMain().z = 7.0f;
                                        rectangleRoadSignModel.getMain().yRot = 3.1415f;
                                        poseStack.scale(0.5f,0.5f,0.5f);
                                        poseStack.translate(0.5f,0.75f,0.65f);
                                    }
                                }
                            }
                            rectangleRoadSignModel.getMain().render(poseStack,vc,packedLight,packedOverlay);
                        }
                        else{
                            vc = buffer.getBuffer(RenderType.entityCutout(
                                    ResourceLocation.parse("thingamajigslongroads:textures/entity/signs/sign_error.png")));
                            roadSignModel.setupAnim(dynamicRoadSignBE);
                            roadSignModel.getMain().render(poseStack,vc,packedLight,packedOverlay);
                        }
                    }
                    else{
                        vc = buffer.getBuffer(RenderType.entityCutout(
                                ResourceLocation.parse("thingamajigslongroads:textures/entity/signs/sign_error.png")));
                        roadSignModel.setupAnim(dynamicRoadSignBE);
                        roadSignModel.getMain().render(poseStack,vc,packedLight,packedOverlay);
                    }
                }
            }
            else{
                vc = buffer.getBuffer(RenderType.entityCutout(
                        ResourceLocation.parse("thingamajigslongroads:textures/entity/signs/default.png")));
                roadSignModel.setupAnim(dynamicRoadSignBE);
                if(displayContext.firstPerson()){
                    if(displayContext == ItemDisplayContext.FIRST_PERSON_LEFT_HAND){
                        roadSignModel.getMain().yRot = 1.4f;
                        roadSignModel.getMain().x = -1.0f;
                        roadSignModel.getMain().y = 7.0f;
                    }
                    else{
                        roadSignModel.getMain().yRot = -1.4f;
                        roadSignModel.getMain().x = 14.0f;
                        roadSignModel.getMain().y = 8.0f;
                    }
                    roadSignModel.getMain().render(poseStack,vc,packedLight,packedOverlay);
                }
                else{
                    if(displayContext == ItemDisplayContext.GUI){
                        roadSignModel.getMain().x = 8f;
                        roadSignModel.getMain().y = 8f;
                        roadSignModel.getMain().render(poseStack,vc,packedLight,packedOverlay);
                    }
                    else{
                        if(displayContext == ItemDisplayContext.FIXED){
                            roadSignModel.getMain().x = 8f;
                            roadSignModel.getMain().y = 8f;
                            roadSignModel.getMain().z = 8f;
                            roadSignModel.getMain().yRot = 3.1415f;
                        }
                        else if(displayContext == ItemDisplayContext.HEAD){
                            roadSignModel.getMain().x = 8f;
                            roadSignModel.getMain().y = 8f;
                            roadSignModel.getMain().z = 2f;
                            roadSignModel.getMain().yRot = 3.1415f;
                        }
                        else if(displayContext == ItemDisplayContext.GROUND){
                            roadSignModel.getMain().x = 8f;
                            roadSignModel.getMain().y = 6f;
                            roadSignModel.getMain().z = 8f;
                            roadSignModel.getMain().offsetScale(new Vector3f(-0.75f,-0.75f,-0.75f));
                            roadSignModel.getMain().yRot = 3.1415f;
                        }
                        else if(displayContext == ItemDisplayContext.THIRD_PERSON_LEFT_HAND){
                            roadSignModel.getMain().x = 8.14f;
                            roadSignModel.getMain().y = 7.15f;
                            roadSignModel.getMain().z = 7.0f;
                            roadSignModel.getMain().yRot = 3.1415f;
                            poseStack.scale(0.5f,0.5f,0.5f);
                            poseStack.translate(0.5f,0.75f,0.65f);
                        }
                        else if(displayContext == ItemDisplayContext.THIRD_PERSON_RIGHT_HAND){
                            roadSignModel.getMain().x = 8.14f;
                            roadSignModel.getMain().y = 7.15f;
                            roadSignModel.getMain().z = 7.0f;
                            roadSignModel.getMain().yRot = 3.1415f;
                            poseStack.scale(0.5f,0.5f,0.5f);
                            poseStack.translate(0.5f,0.75f,0.65f);
                        }
                        roadSignModel.getMain().render(poseStack,vc,packedLight,packedOverlay);
                    }
                }
            }
            if(displayContext == ItemDisplayContext.GUI){
                poseStack.translate(0D,0D,-2.5D);
                Minecraft.getInstance().getBlockRenderer()
                        .renderSingleBlock(TBlocks.PLUS_POLE.get().defaultBlockState()
                                ,poseStack,buffer,packedLight,packedOverlay);
            }
            else if(displayContext.firstPerson()){
                if(displayContext == ItemDisplayContext.FIRST_PERSON_LEFT_HAND){
                    poseStack.mulPose(Axis.YP.rotationDegrees(90));
                    poseStack.translate(-0.2D,0.1D,-1D);
                }
                else if(displayContext == ItemDisplayContext.FIRST_PERSON_RIGHT_HAND){
                    poseStack.mulPose(Axis.YP.rotationDegrees(90));
                    poseStack.translate(-0.2D,0.1D,1D);
                }
                Minecraft.getInstance().getBlockRenderer()
                        .renderSingleBlock(TBlocks.PLUS_POLE.get().defaultBlockState()
                                ,poseStack,buffer,packedLight,packedOverlay);
            }
            poseStack.popPose();
        }
        else if(stack.is(TLRItems.DYNAMIC_STRAIGHT_ROAD_SIGN.asItem())){
            poseStack.pushPose();
            if(stack.has(DataComponents.BLOCK_ENTITY_DATA)){
                CompoundTag tag = stack.get(DataComponents.BLOCK_ENTITY_DATA).copyTag();
                if(tag != null){
                    if(tag.get("sign_texture") != null && tag.get("model_type") != null && tag.get("y_angle") != null){
                        String textureLocation = tag.getString("sign_texture");
                        String modelType = tag.getString("model_type");
                        float yAngle = tag.getFloat("y_angle");

                        poseStack.mulPose(Axis.YP.rotationDegrees(yAngle));

                        if(modelType.equals("square")){
                            if(ResourceLocation.read(textureLocation).hasResultOrPartial()){
                                vc = buffer.getBuffer(RenderType.entityCutout(ResourceLocation.parse(textureLocation)));
                            }
                            else{
                                vc = buffer.getBuffer(RenderType.entityCutout(
                                        ResourceLocation.parse("thingamajigslongroads:textures/entity/signs/sign_error.png")));
                            }
                            roadSignModel.setupAnim(dynamicRoadSignBE);
                            if(displayContext.firstPerson()){
                                if(displayContext == ItemDisplayContext.FIRST_PERSON_LEFT_HAND){
                                    roadSignModel.getMain().yRot = 1.4f;
                                    roadSignModel.getMain().x = -1.0f;
                                    roadSignModel.getMain().y = 8.0f;
                                }
                                else{
                                    roadSignModel.getMain().yRot = -1.4f;
                                    roadSignModel.getMain().x = 14.0f;
                                    roadSignModel.getMain().y = 8.0f;
                                }
                                roadSignModel.getMain().render(poseStack,vc,packedLight,packedOverlay);
                            }
                            else{
                                if(displayContext == ItemDisplayContext.GUI){
                                    roadSignModel.getMain().x = 8f;
                                    roadSignModel.getMain().y = 8f;
                                    roadSignModel.getMain().render(poseStack,vc,packedLight,packedOverlay);
                                }
                                else{
                                    if(displayContext == ItemDisplayContext.FIXED){
                                        roadSignModel.getMain().x = 8f;
                                        roadSignModel.getMain().y = 8f;
                                        roadSignModel.getMain().z = 8f;
                                        roadSignModel.getMain().yRot = 3.1415f;
                                    }
                                    else if(displayContext == ItemDisplayContext.HEAD){
                                        roadSignModel.getMain().x = 8f;
                                        roadSignModel.getMain().y = 8f;
                                        roadSignModel.getMain().z = 2f;
                                        roadSignModel.getMain().yRot = 3.1415f;
                                    }
                                    else if(displayContext == ItemDisplayContext.GROUND){
                                        roadSignModel.getMain().x = 8f;
                                        roadSignModel.getMain().y = 6f;
                                        roadSignModel.getMain().z = 8f;
                                        roadSignModel.getMain().offsetScale(new Vector3f(-0.75f,-0.75f,-0.75f));
                                        roadSignModel.getMain().yRot = 3.1415f;
                                    }
                                    else if(displayContext == ItemDisplayContext.THIRD_PERSON_LEFT_HAND){
                                        roadSignModel.getMain().x = 8.14f;
                                        roadSignModel.getMain().y = 7.15f;
                                        roadSignModel.getMain().z = 7.0f;
                                        roadSignModel.getMain().yRot = 3.1415f;
                                        poseStack.scale(0.5f,0.5f,0.5f);
                                        poseStack.translate(0.5f,0.75f,0.65f);
                                    }
                                    else if(displayContext == ItemDisplayContext.THIRD_PERSON_RIGHT_HAND){
                                        roadSignModel.getMain().x = 8.14f;
                                        roadSignModel.getMain().y = 7.15f;
                                        roadSignModel.getMain().z = 7.0f;
                                        roadSignModel.getMain().yRot = 3.1415f;
                                        poseStack.scale(0.5f,0.5f,0.5f);
                                        poseStack.translate(0.5f,0.75f,0.65f);
                                    }
                                    roadSignModel.getMain().render(poseStack,vc,packedLight,packedOverlay);
                                }
                            }
                            roadSignModel.getMain().render(poseStack,vc,packedLight,packedOverlay);
                        }
                        else if(modelType.equals("double_square")){
                            if(ResourceLocation.read(textureLocation).hasResultOrPartial()){
                                vc = buffer.getBuffer(RenderType.entityCutout(ResourceLocation.parse(textureLocation)));
                            }
                            else{
                                vc = buffer.getBuffer(RenderType.entityCutout(
                                        ResourceLocation.parse("thingamajigslongroads:textures/entity/signs/big_sign_error.png")));
                            }
                            doubleTallRoadSignModel.setupAnim(dynamicRoadSignBEDoubleTall);
                            if(displayContext.firstPerson()){
                                if(displayContext == ItemDisplayContext.FIRST_PERSON_LEFT_HAND){
                                    doubleTallRoadSignModel.getMain().yRot = 1.4f;
                                    doubleTallRoadSignModel.getMain().x = -8.0f;
                                    doubleTallRoadSignModel.getMain().y = 4.0f;
                                }
                                else{
                                    doubleTallRoadSignModel.getMain().yRot = -1.4f;
                                    doubleTallRoadSignModel.getMain().x = 24.0f;
                                    doubleTallRoadSignModel.getMain().y = 4.0f;
                                }
                                doubleTallRoadSignModel.getMain().render(poseStack,vc,packedLight,packedOverlay);
                            }
                            else{
                                if(displayContext == ItemDisplayContext.GUI){
                                    doubleTallRoadSignModel.getMain().offsetScale(
                                            new Vector3f(-0.5f,-0.5f,-0.5f));
                                    doubleTallRoadSignModel.getMain().x = 8f;
                                    doubleTallRoadSignModel.getMain().y = 4f;
                                    doubleTallRoadSignModel.getMain().render(poseStack,vc,packedLight,packedOverlay);
                                }
                                else{
                                    if(displayContext == ItemDisplayContext.FIXED){
                                        doubleTallRoadSignModel.getMain().offsetScale(
                                                new Vector3f(-0.5f,-0.5f,-0.5f));
                                        doubleTallRoadSignModel.getMain().x = 8f;
                                        doubleTallRoadSignModel.getMain().y = 4f;
                                        doubleTallRoadSignModel.getMain().z = 8f;
                                        doubleTallRoadSignModel.getMain().yRot = 3.1415f;
                                    }
                                    else if(displayContext == ItemDisplayContext.HEAD){
                                        doubleTallRoadSignModel.getMain().x = 8f;
                                        doubleTallRoadSignModel.getMain().y = 8f;
                                        doubleTallRoadSignModel.getMain().z = 2f;
                                        doubleTallRoadSignModel.getMain().yRot = 3.1415f;
                                    }
                                    else if(displayContext == ItemDisplayContext.GROUND){
                                        doubleTallRoadSignModel.getMain().x = 8f;
                                        doubleTallRoadSignModel.getMain().y = 8f;
                                        doubleTallRoadSignModel.getMain().z = 8f;
                                        doubleTallRoadSignModel.getMain().offsetScale(new Vector3f(-0.75f,-0.75f,-0.75f));
                                        doubleTallRoadSignModel.getMain().yRot = 3.1415f;
                                    }
                                    else if(displayContext == ItemDisplayContext.THIRD_PERSON_LEFT_HAND){
                                        doubleTallRoadSignModel.getMain().x = 8.14f;
                                        doubleTallRoadSignModel.getMain().y = 7.15f;
                                        doubleTallRoadSignModel.getMain().z = 7.0f;
                                        doubleTallRoadSignModel.getMain().yRot = 3.1415f;
                                        poseStack.scale(0.5f,0.5f,0.5f);
                                        poseStack.translate(0.5f,0.75f,0.65f);
                                    }
                                    else if(displayContext == ItemDisplayContext.THIRD_PERSON_RIGHT_HAND){
                                        doubleTallRoadSignModel.getMain().x = 8.14f;
                                        doubleTallRoadSignModel.getMain().y = 7.15f;
                                        doubleTallRoadSignModel.getMain().z = 7.0f;
                                        doubleTallRoadSignModel.getMain().yRot = 3.1415f;
                                        poseStack.scale(0.5f,0.5f,0.5f);
                                        poseStack.translate(0.5f,0.75f,0.65f);
                                    }
                                }
                            }
                            doubleTallRoadSignModel.getMain().render(poseStack,vc,packedLight,packedOverlay);
                        }
                        else if(modelType.equals("rectangle")){
                            if(ResourceLocation.read(textureLocation).hasResultOrPartial()){
                                vc = buffer.getBuffer(RenderType.entityCutout(ResourceLocation.parse(textureLocation)));
                            }
                            else{
                                vc = buffer.getBuffer(RenderType.entityCutout(
                                        ResourceLocation.parse("thingamajigslongroads:textures/entity/signs/rect_sign_error.png")));
                            }
                            rectangleRoadSignModel.setupAnim(dynamicRoadSignBERectangle);
                            if(displayContext.firstPerson()){
                                if(displayContext == ItemDisplayContext.FIRST_PERSON_LEFT_HAND){
                                    rectangleRoadSignModel.getMain().yRot = 1.4f;
                                    rectangleRoadSignModel.getMain().x = -4.0f;
                                    rectangleRoadSignModel.getMain().y = 4.0f;
                                }
                                else{
                                    rectangleRoadSignModel.getMain().yRot = -1.4f;
                                    rectangleRoadSignModel.getMain().x = 20.0f;
                                    rectangleRoadSignModel.getMain().y = 4.0f;
                                }
                                rectangleRoadSignModel.getMain().render(poseStack,vc,packedLight,packedOverlay);
                            }
                            else{
                                if(displayContext == ItemDisplayContext.GUI){
                                    rectangleRoadSignModel.getMain().offsetScale(
                                            new Vector3f(-0.5f,-0.5f,-0.5f));
                                    rectangleRoadSignModel.getMain().x = 8f;
                                    rectangleRoadSignModel.getMain().y = 8.5f;
                                    rectangleRoadSignModel.getMain().render(poseStack,vc,packedLight,packedOverlay);
                                }
                                else{
                                    if(displayContext == ItemDisplayContext.FIXED){
                                        rectangleRoadSignModel.getMain().offsetScale(
                                                new Vector3f(-0.5f,-0.5f,-0.5f));
                                        rectangleRoadSignModel.getMain().x = 8f;
                                        rectangleRoadSignModel.getMain().y = 8f;
                                        rectangleRoadSignModel.getMain().z = 8f;
                                        rectangleRoadSignModel.getMain().yRot = 3.1415f;
                                    }
                                    else if(displayContext == ItemDisplayContext.HEAD){
                                        rectangleRoadSignModel.getMain().x = 8f;
                                        rectangleRoadSignModel.getMain().y = 8f;
                                        rectangleRoadSignModel.getMain().z = 2f;
                                        rectangleRoadSignModel.getMain().yRot = 3.1415f;
                                    }
                                    else if(displayContext == ItemDisplayContext.GROUND){
                                        rectangleRoadSignModel.getMain().x = 8f;
                                        rectangleRoadSignModel.getMain().y = 8f;
                                        rectangleRoadSignModel.getMain().z = 8f;
                                        rectangleRoadSignModel.getMain().offsetScale(new Vector3f(-0.75f,-0.75f,-0.75f));
                                        rectangleRoadSignModel.getMain().yRot = 3.1415f;
                                    }
                                    else if(displayContext == ItemDisplayContext.THIRD_PERSON_LEFT_HAND){
                                        rectangleRoadSignModel.getMain().x = 8.14f;
                                        rectangleRoadSignModel.getMain().y = 7.15f;
                                        rectangleRoadSignModel.getMain().z = 7.0f;
                                        rectangleRoadSignModel.getMain().yRot = 3.1415f;
                                        poseStack.scale(0.5f,0.5f,0.5f);
                                        poseStack.translate(0.5f,0.75f,0.65f);
                                    }
                                    else if(displayContext == ItemDisplayContext.THIRD_PERSON_RIGHT_HAND){
                                        rectangleRoadSignModel.getMain().x = 8.14f;
                                        rectangleRoadSignModel.getMain().y = 7.15f;
                                        rectangleRoadSignModel.getMain().z = 7.0f;
                                        rectangleRoadSignModel.getMain().yRot = 3.1415f;
                                        poseStack.scale(0.5f,0.5f,0.5f);
                                        poseStack.translate(0.5f,0.75f,0.65f);
                                    }
                                }
                            }
                            rectangleRoadSignModel.getMain().render(poseStack,vc,packedLight,packedOverlay);
                        }
                        else{
                            vc = buffer.getBuffer(RenderType.entityCutout(
                                    ResourceLocation.parse("thingamajigslongroads:textures/entity/signs/sign_error.png")));
                            roadSignModel.setupAnim(dynamicRoadSignBE);
                            roadSignModel.getMain().render(poseStack,vc,packedLight,packedOverlay);
                        }
                    }
                    else{
                        vc = buffer.getBuffer(RenderType.entityCutout(
                                ResourceLocation.parse("thingamajigslongroads:textures/entity/signs/sign_error.png")));
                        roadSignModel.setupAnim(dynamicRoadSignBE);
                        roadSignModel.getMain().render(poseStack,vc,packedLight,packedOverlay);
                    }
                }
            }
            else{
                vc = buffer.getBuffer(RenderType.entityCutout(
                        ResourceLocation.parse("thingamajigslongroads:textures/entity/signs/default.png")));
                roadSignModel.setupAnim(dynamicRoadSignBE);
                if(displayContext.firstPerson()){
                    if(displayContext == ItemDisplayContext.FIRST_PERSON_LEFT_HAND){
                        roadSignModel.getMain().yRot = 1.4f;
                        roadSignModel.getMain().x = -1.0f;
                        roadSignModel.getMain().y = 7.0f;
                    }
                    else{
                        roadSignModel.getMain().yRot = -1.4f;
                        roadSignModel.getMain().x = 14.0f;
                        roadSignModel.getMain().y = 8.0f;
                    }
                    roadSignModel.getMain().render(poseStack,vc,packedLight,packedOverlay);
                }
                else{
                    if(displayContext == ItemDisplayContext.GUI){
                        roadSignModel.getMain().x = 8f;
                        roadSignModel.getMain().y = 8f;
                        roadSignModel.getMain().render(poseStack,vc,packedLight,packedOverlay);
                    }
                    else{
                        if(displayContext == ItemDisplayContext.FIXED){
                            roadSignModel.getMain().x = 8f;
                            roadSignModel.getMain().y = 8f;
                            roadSignModel.getMain().z = 8f;
                            roadSignModel.getMain().yRot = 3.1415f;
                        }
                        else if(displayContext == ItemDisplayContext.HEAD){
                            roadSignModel.getMain().x = 8f;
                            roadSignModel.getMain().y = 8f;
                            roadSignModel.getMain().z = 2f;
                            roadSignModel.getMain().yRot = 3.1415f;
                        }
                        else if(displayContext == ItemDisplayContext.GROUND){
                            roadSignModel.getMain().x = 8f;
                            roadSignModel.getMain().y = 6f;
                            roadSignModel.getMain().z = 8f;
                            roadSignModel.getMain().offsetScale(new Vector3f(-0.75f,-0.75f,-0.75f));
                            roadSignModel.getMain().yRot = 3.1415f;
                        }
                        else if(displayContext == ItemDisplayContext.THIRD_PERSON_LEFT_HAND){
                            roadSignModel.getMain().x = 8.14f;
                            roadSignModel.getMain().y = 7.15f;
                            roadSignModel.getMain().z = 7.0f;
                            roadSignModel.getMain().yRot = 3.1415f;
                            poseStack.scale(0.5f,0.5f,0.5f);
                            poseStack.translate(0.5f,0.75f,0.65f);
                        }
                        else if(displayContext == ItemDisplayContext.THIRD_PERSON_RIGHT_HAND){
                            roadSignModel.getMain().x = 8.14f;
                            roadSignModel.getMain().y = 7.15f;
                            roadSignModel.getMain().z = 7.0f;
                            roadSignModel.getMain().yRot = 3.1415f;
                            poseStack.scale(0.5f,0.5f,0.5f);
                            poseStack.translate(0.5f,0.75f,0.65f);
                        }
                        roadSignModel.getMain().render(poseStack,vc,packedLight,packedOverlay);
                    }
                }
            }
            if(displayContext == ItemDisplayContext.GUI){
                poseStack.translate(0D,0D,-2.5D);
                Minecraft.getInstance().getBlockRenderer()
                        .renderSingleBlock(TBlocks.STRAIGHT_POLE.get().defaultBlockState()
                                ,poseStack,buffer,packedLight,packedOverlay);
            }
            else if(displayContext.firstPerson()){
                if(displayContext == ItemDisplayContext.FIRST_PERSON_LEFT_HAND){
                    poseStack.mulPose(Axis.YP.rotationDegrees(90));
                    poseStack.translate(-0.2D,0.1D,-1D);
                }
                else if(displayContext == ItemDisplayContext.FIRST_PERSON_RIGHT_HAND){
                    poseStack.mulPose(Axis.YP.rotationDegrees(90));
                    poseStack.translate(-0.2D,0.1D,1D);
                }
                Minecraft.getInstance().getBlockRenderer()
                        .renderSingleBlock(TBlocks.STRAIGHT_POLE.get().defaultBlockState()
                                ,poseStack,buffer,packedLight,packedOverlay);
            }
            poseStack.popPose();
        }
        else if(stack.is(TLRItems.DYNAMIC_THREE_WAY_ROAD_SIGN.asItem())){
            poseStack.pushPose();
            if(stack.has(DataComponents.BLOCK_ENTITY_DATA)){
                CompoundTag tag = stack.get(DataComponents.BLOCK_ENTITY_DATA).copyTag();
                if(tag != null){
                    if(tag.get("sign_texture") != null && tag.get("model_type") != null && tag.get("y_angle") != null){
                        String textureLocation = tag.getString("sign_texture");
                        String modelType = tag.getString("model_type");
                        float yAngle = tag.getFloat("y_angle");

                        poseStack.mulPose(Axis.YP.rotationDegrees(yAngle));

                        if(modelType.equals("square")){
                            if(ResourceLocation.read(textureLocation).hasResultOrPartial()){
                                vc = buffer.getBuffer(RenderType.entityCutout(ResourceLocation.parse(textureLocation)));
                            }
                            else{
                                vc = buffer.getBuffer(RenderType.entityCutout(
                                        ResourceLocation.parse("thingamajigslongroads:textures/entity/signs/sign_error.png")));
                            }
                            roadSignModel.setupAnim(dynamicRoadSignBE);
                            if(displayContext.firstPerson()){
                                if(displayContext == ItemDisplayContext.FIRST_PERSON_LEFT_HAND){
                                    roadSignModel.getMain().yRot = 1.4f;
                                    roadSignModel.getMain().x = -1.0f;
                                    roadSignModel.getMain().y = 8.0f;
                                }
                                else{
                                    roadSignModel.getMain().yRot = -1.4f;
                                    roadSignModel.getMain().x = 14.0f;
                                    roadSignModel.getMain().y = 8.0f;
                                }
                                roadSignModel.getMain().render(poseStack,vc,packedLight,packedOverlay);
                            }
                            else{
                                if(displayContext == ItemDisplayContext.GUI){
                                    roadSignModel.getMain().x = 8f;
                                    roadSignModel.getMain().y = 8f;
                                    roadSignModel.getMain().render(poseStack,vc,packedLight,packedOverlay);
                                }
                                else{
                                    if(displayContext == ItemDisplayContext.FIXED){
                                        roadSignModel.getMain().x = 8f;
                                        roadSignModel.getMain().y = 8f;
                                        roadSignModel.getMain().z = 8f;
                                        roadSignModel.getMain().yRot = 3.1415f;
                                    }
                                    else if(displayContext == ItemDisplayContext.HEAD){
                                        roadSignModel.getMain().x = 8f;
                                        roadSignModel.getMain().y = 8f;
                                        roadSignModel.getMain().z = 2f;
                                        roadSignModel.getMain().yRot = 3.1415f;
                                    }
                                    else if(displayContext == ItemDisplayContext.GROUND){
                                        roadSignModel.getMain().x = 8f;
                                        roadSignModel.getMain().y = 6f;
                                        roadSignModel.getMain().z = 8f;
                                        roadSignModel.getMain().offsetScale(new Vector3f(-0.75f,-0.75f,-0.75f));
                                        roadSignModel.getMain().yRot = 3.1415f;
                                    }
                                    else if(displayContext == ItemDisplayContext.THIRD_PERSON_LEFT_HAND){
                                        roadSignModel.getMain().x = 8.14f;
                                        roadSignModel.getMain().y = 7.15f;
                                        roadSignModel.getMain().z = 7.0f;
                                        roadSignModel.getMain().yRot = 3.1415f;
                                        poseStack.scale(0.5f,0.5f,0.5f);
                                        poseStack.translate(0.5f,0.75f,0.65f);
                                    }
                                    else if(displayContext == ItemDisplayContext.THIRD_PERSON_RIGHT_HAND){
                                        roadSignModel.getMain().x = 8.14f;
                                        roadSignModel.getMain().y = 7.15f;
                                        roadSignModel.getMain().z = 7.0f;
                                        roadSignModel.getMain().yRot = 3.1415f;
                                        poseStack.scale(0.5f,0.5f,0.5f);
                                        poseStack.translate(0.5f,0.75f,0.65f);
                                    }
                                    roadSignModel.getMain().render(poseStack,vc,packedLight,packedOverlay);
                                }
                            }
                            roadSignModel.getMain().render(poseStack,vc,packedLight,packedOverlay);
                        }
                        else if(modelType.equals("double_square")){
                            if(ResourceLocation.read(textureLocation).hasResultOrPartial()){
                                vc = buffer.getBuffer(RenderType.entityCutout(ResourceLocation.parse(textureLocation)));
                            }
                            else{
                                vc = buffer.getBuffer(RenderType.entityCutout(
                                        ResourceLocation.parse("thingamajigslongroads:textures/entity/signs/big_sign_error.png")));
                            }
                            doubleTallRoadSignModel.setupAnim(dynamicRoadSignBEDoubleTall);
                            if(displayContext.firstPerson()){
                                if(displayContext == ItemDisplayContext.FIRST_PERSON_LEFT_HAND){
                                    doubleTallRoadSignModel.getMain().yRot = 1.4f;
                                    doubleTallRoadSignModel.getMain().x = -8.0f;
                                    doubleTallRoadSignModel.getMain().y = 4.0f;
                                }
                                else{
                                    doubleTallRoadSignModel.getMain().yRot = -1.4f;
                                    doubleTallRoadSignModel.getMain().x = 24.0f;
                                    doubleTallRoadSignModel.getMain().y = 4.0f;
                                }
                                doubleTallRoadSignModel.getMain().render(poseStack,vc,packedLight,packedOverlay);
                            }
                            else{
                                if(displayContext == ItemDisplayContext.GUI){
                                    doubleTallRoadSignModel.getMain().offsetScale(
                                            new Vector3f(-0.5f,-0.5f,-0.5f));
                                    doubleTallRoadSignModel.getMain().x = 8f;
                                    doubleTallRoadSignModel.getMain().y = 4f;
                                    doubleTallRoadSignModel.getMain().render(poseStack,vc,packedLight,packedOverlay);
                                }
                                else{
                                    if(displayContext == ItemDisplayContext.FIXED){
                                        doubleTallRoadSignModel.getMain().offsetScale(
                                                new Vector3f(-0.5f,-0.5f,-0.5f));
                                        doubleTallRoadSignModel.getMain().x = 8f;
                                        doubleTallRoadSignModel.getMain().y = 4f;
                                        doubleTallRoadSignModel.getMain().z = 8f;
                                        doubleTallRoadSignModel.getMain().yRot = 3.1415f;
                                    }
                                    else if(displayContext == ItemDisplayContext.HEAD){
                                        doubleTallRoadSignModel.getMain().x = 8f;
                                        doubleTallRoadSignModel.getMain().y = 8f;
                                        doubleTallRoadSignModel.getMain().z = 2f;
                                        doubleTallRoadSignModel.getMain().yRot = 3.1415f;
                                    }
                                    else if(displayContext == ItemDisplayContext.GROUND){
                                        doubleTallRoadSignModel.getMain().x = 8f;
                                        doubleTallRoadSignModel.getMain().y = 8f;
                                        doubleTallRoadSignModel.getMain().z = 8f;
                                        doubleTallRoadSignModel.getMain().offsetScale(new Vector3f(-0.75f,-0.75f,-0.75f));
                                        doubleTallRoadSignModel.getMain().yRot = 3.1415f;
                                    }
                                    else if(displayContext == ItemDisplayContext.THIRD_PERSON_LEFT_HAND){
                                        doubleTallRoadSignModel.getMain().x = 8.14f;
                                        doubleTallRoadSignModel.getMain().y = 7.15f;
                                        doubleTallRoadSignModel.getMain().z = 7.0f;
                                        doubleTallRoadSignModel.getMain().yRot = 3.1415f;
                                        poseStack.scale(0.5f,0.5f,0.5f);
                                        poseStack.translate(0.5f,0.75f,0.65f);
                                    }
                                    else if(displayContext == ItemDisplayContext.THIRD_PERSON_RIGHT_HAND){
                                        doubleTallRoadSignModel.getMain().x = 8.14f;
                                        doubleTallRoadSignModel.getMain().y = 7.15f;
                                        doubleTallRoadSignModel.getMain().z = 7.0f;
                                        doubleTallRoadSignModel.getMain().yRot = 3.1415f;
                                        poseStack.scale(0.5f,0.5f,0.5f);
                                        poseStack.translate(0.5f,0.75f,0.65f);
                                    }
                                }
                            }
                            doubleTallRoadSignModel.getMain().render(poseStack,vc,packedLight,packedOverlay);
                        }
                        else if(modelType.equals("rectangle")){
                            if(ResourceLocation.read(textureLocation).hasResultOrPartial()){
                                vc = buffer.getBuffer(RenderType.entityCutout(ResourceLocation.parse(textureLocation)));
                            }
                            else{
                                vc = buffer.getBuffer(RenderType.entityCutout(
                                        ResourceLocation.parse("thingamajigslongroads:textures/entity/signs/rect_sign_error.png")));
                            }
                            rectangleRoadSignModel.setupAnim(dynamicRoadSignBERectangle);
                            if(displayContext.firstPerson()){
                                if(displayContext == ItemDisplayContext.FIRST_PERSON_LEFT_HAND){
                                    rectangleRoadSignModel.getMain().yRot = 1.4f;
                                    rectangleRoadSignModel.getMain().x = -4.0f;
                                    rectangleRoadSignModel.getMain().y = 4.0f;
                                }
                                else{
                                    rectangleRoadSignModel.getMain().yRot = -1.4f;
                                    rectangleRoadSignModel.getMain().x = 20.0f;
                                    rectangleRoadSignModel.getMain().y = 4.0f;
                                }
                                rectangleRoadSignModel.getMain().render(poseStack,vc,packedLight,packedOverlay);
                            }
                            else{
                                if(displayContext == ItemDisplayContext.GUI){
                                    rectangleRoadSignModel.getMain().offsetScale(
                                            new Vector3f(-0.5f,-0.5f,-0.5f));
                                    rectangleRoadSignModel.getMain().x = 8f;
                                    rectangleRoadSignModel.getMain().y = 8.5f;
                                    rectangleRoadSignModel.getMain().render(poseStack,vc,packedLight,packedOverlay);
                                }
                                else{
                                    if(displayContext == ItemDisplayContext.FIXED){
                                        rectangleRoadSignModel.getMain().offsetScale(
                                                new Vector3f(-0.5f,-0.5f,-0.5f));
                                        rectangleRoadSignModel.getMain().x = 8f;
                                        rectangleRoadSignModel.getMain().y = 8f;
                                        rectangleRoadSignModel.getMain().z = 8f;
                                        rectangleRoadSignModel.getMain().yRot = 3.1415f;
                                    }
                                    else if(displayContext == ItemDisplayContext.HEAD){
                                        rectangleRoadSignModel.getMain().x = 8f;
                                        rectangleRoadSignModel.getMain().y = 8f;
                                        rectangleRoadSignModel.getMain().z = 2f;
                                        rectangleRoadSignModel.getMain().yRot = 3.1415f;
                                    }
                                    else if(displayContext == ItemDisplayContext.GROUND){
                                        rectangleRoadSignModel.getMain().x = 8f;
                                        rectangleRoadSignModel.getMain().y = 8f;
                                        rectangleRoadSignModel.getMain().z = 8f;
                                        rectangleRoadSignModel.getMain().offsetScale(new Vector3f(-0.75f,-0.75f,-0.75f));
                                        rectangleRoadSignModel.getMain().yRot = 3.1415f;
                                    }
                                    else if(displayContext == ItemDisplayContext.THIRD_PERSON_LEFT_HAND){
                                        rectangleRoadSignModel.getMain().x = 8.14f;
                                        rectangleRoadSignModel.getMain().y = 7.15f;
                                        rectangleRoadSignModel.getMain().z = 7.0f;
                                        rectangleRoadSignModel.getMain().yRot = 3.1415f;
                                        poseStack.scale(0.5f,0.5f,0.5f);
                                        poseStack.translate(0.5f,0.75f,0.65f);
                                    }
                                    else if(displayContext == ItemDisplayContext.THIRD_PERSON_RIGHT_HAND){
                                        rectangleRoadSignModel.getMain().x = 8.14f;
                                        rectangleRoadSignModel.getMain().y = 7.15f;
                                        rectangleRoadSignModel.getMain().z = 7.0f;
                                        rectangleRoadSignModel.getMain().yRot = 3.1415f;
                                        poseStack.scale(0.5f,0.5f,0.5f);
                                        poseStack.translate(0.5f,0.75f,0.65f);
                                    }
                                }
                            }
                            rectangleRoadSignModel.getMain().render(poseStack,vc,packedLight,packedOverlay);
                        }
                        else{
                            vc = buffer.getBuffer(RenderType.entityCutout(
                                    ResourceLocation.parse("thingamajigslongroads:textures/entity/signs/sign_error.png")));
                            roadSignModel.setupAnim(dynamicRoadSignBE);
                            roadSignModel.getMain().render(poseStack,vc,packedLight,packedOverlay);
                        }
                    }
                    else{
                        vc = buffer.getBuffer(RenderType.entityCutout(
                                ResourceLocation.parse("thingamajigslongroads:textures/entity/signs/sign_error.png")));
                        roadSignModel.setupAnim(dynamicRoadSignBE);
                        roadSignModel.getMain().render(poseStack,vc,packedLight,packedOverlay);
                    }
                }
            }
            else{
                vc = buffer.getBuffer(RenderType.entityCutout(
                        ResourceLocation.parse("thingamajigslongroads:textures/entity/signs/default.png")));
                roadSignModel.setupAnim(dynamicRoadSignBE);
                if(displayContext.firstPerson()){
                    if(displayContext == ItemDisplayContext.FIRST_PERSON_LEFT_HAND){
                        roadSignModel.getMain().yRot = 1.4f;
                        roadSignModel.getMain().x = -1.0f;
                        roadSignModel.getMain().y = 7.0f;
                    }
                    else{
                        roadSignModel.getMain().yRot = -1.4f;
                        roadSignModel.getMain().x = 14.0f;
                        roadSignModel.getMain().y = 8.0f;
                    }
                    roadSignModel.getMain().render(poseStack,vc,packedLight,packedOverlay);
                }
                else{
                    if(displayContext == ItemDisplayContext.GUI){
                        roadSignModel.getMain().x = 8f;
                        roadSignModel.getMain().y = 8f;
                        roadSignModel.getMain().render(poseStack,vc,packedLight,packedOverlay);
                    }
                    else{
                        if(displayContext == ItemDisplayContext.FIXED){
                            roadSignModel.getMain().x = 8f;
                            roadSignModel.getMain().y = 8f;
                            roadSignModel.getMain().z = 8f;
                            roadSignModel.getMain().yRot = 3.1415f;
                        }
                        else if(displayContext == ItemDisplayContext.HEAD){
                            roadSignModel.getMain().x = 8f;
                            roadSignModel.getMain().y = 8f;
                            roadSignModel.getMain().z = 2f;
                            roadSignModel.getMain().yRot = 3.1415f;
                        }
                        else if(displayContext == ItemDisplayContext.GROUND){
                            roadSignModel.getMain().x = 8f;
                            roadSignModel.getMain().y = 6f;
                            roadSignModel.getMain().z = 8f;
                            roadSignModel.getMain().offsetScale(new Vector3f(-0.75f,-0.75f,-0.75f));
                            roadSignModel.getMain().yRot = 3.1415f;
                        }
                        else if(displayContext == ItemDisplayContext.THIRD_PERSON_LEFT_HAND){
                            roadSignModel.getMain().x = 8.14f;
                            roadSignModel.getMain().y = 7.15f;
                            roadSignModel.getMain().z = 7.0f;
                            roadSignModel.getMain().yRot = 3.1415f;
                            poseStack.scale(0.5f,0.5f,0.5f);
                            poseStack.translate(0.5f,0.75f,0.65f);
                        }
                        else if(displayContext == ItemDisplayContext.THIRD_PERSON_RIGHT_HAND){
                            roadSignModel.getMain().x = 8.14f;
                            roadSignModel.getMain().y = 7.15f;
                            roadSignModel.getMain().z = 7.0f;
                            roadSignModel.getMain().yRot = 3.1415f;
                            poseStack.scale(0.5f,0.5f,0.5f);
                            poseStack.translate(0.5f,0.75f,0.65f);
                        }
                        roadSignModel.getMain().render(poseStack,vc,packedLight,packedOverlay);
                    }
                }
            }
            if(displayContext == ItemDisplayContext.GUI){
                poseStack.translate(0D,0D,-2.5D);
                Minecraft.getInstance().getBlockRenderer()
                        .renderSingleBlock(TBlocks.THREE_WAY_POLE.get().defaultBlockState()
                                ,poseStack,buffer,packedLight,packedOverlay);
            }
            else if(displayContext.firstPerson()){
                if(displayContext == ItemDisplayContext.FIRST_PERSON_LEFT_HAND){
                    poseStack.mulPose(Axis.YP.rotationDegrees(90));
                    poseStack.translate(-0.2D,0.1D,-1D);
                }
                else if(displayContext == ItemDisplayContext.FIRST_PERSON_RIGHT_HAND){
                    poseStack.mulPose(Axis.YP.rotationDegrees(90));
                    poseStack.translate(-0.2D,0.1D,1D);
                }
                Minecraft.getInstance().getBlockRenderer()
                        .renderSingleBlock(TBlocks.THREE_WAY_POLE.get().defaultBlockState()
                                ,poseStack,buffer,packedLight,packedOverlay);
            }
            poseStack.popPose();
        }
        else if(stack.is(TLRItems.DYNAMIC_VERTICAL_REDSTONE_ROAD_SIGN.asItem())){
            poseStack.pushPose();
            if(stack.has(DataComponents.BLOCK_ENTITY_DATA)){
                CompoundTag tag = stack.get(DataComponents.BLOCK_ENTITY_DATA).copyTag();
                if(tag != null){
                    if(tag.get("sign_texture") != null && tag.get("model_type") != null && tag.get("y_angle") != null){
                        String textureLocation = tag.getString("sign_texture");
                        String modelType = tag.getString("model_type");
                        float yAngle = tag.getFloat("y_angle");

                        poseStack.mulPose(Axis.YP.rotationDegrees(yAngle));

                        if(modelType.equals("square")){
                            if(ResourceLocation.read(textureLocation).hasResultOrPartial()){
                                vc = buffer.getBuffer(RenderType.entityCutout(ResourceLocation.parse(textureLocation)));
                            }
                            else{
                                vc = buffer.getBuffer(RenderType.entityCutout(
                                        ResourceLocation.parse("thingamajigslongroads:textures/entity/signs/sign_error.png")));
                            }
                            roadSignModel.setupAnim(dynamicRoadSignBE);
                            if(displayContext.firstPerson()){
                                if(displayContext == ItemDisplayContext.FIRST_PERSON_LEFT_HAND){
                                    roadSignModel.getMain().yRot = 1.4f;
                                    roadSignModel.getMain().x = -1.0f;
                                    roadSignModel.getMain().y = 8.0f;
                                }
                                else{
                                    roadSignModel.getMain().yRot = -1.4f;
                                    roadSignModel.getMain().x = 14.0f;
                                    roadSignModel.getMain().y = 8.0f;
                                }
                                roadSignModel.getMain().render(poseStack,vc,packedLight,packedOverlay);
                            }
                            else{
                                if(displayContext == ItemDisplayContext.GUI){
                                    roadSignModel.getMain().x = 8f;
                                    roadSignModel.getMain().y = 8f;
                                    roadSignModel.getMain().render(poseStack,vc,packedLight,packedOverlay);
                                }
                                else{
                                    if(displayContext == ItemDisplayContext.FIXED){
                                        roadSignModel.getMain().x = 8f;
                                        roadSignModel.getMain().y = 8f;
                                        roadSignModel.getMain().z = 8f;
                                        roadSignModel.getMain().yRot = 3.1415f;
                                    }
                                    else if(displayContext == ItemDisplayContext.HEAD){
                                        roadSignModel.getMain().x = 8f;
                                        roadSignModel.getMain().y = 8f;
                                        roadSignModel.getMain().z = 2f;
                                        roadSignModel.getMain().yRot = 3.1415f;
                                    }
                                    else if(displayContext == ItemDisplayContext.GROUND){
                                        roadSignModel.getMain().x = 8f;
                                        roadSignModel.getMain().y = 6f;
                                        roadSignModel.getMain().z = 8f;
                                        roadSignModel.getMain().offsetScale(new Vector3f(-0.75f,-0.75f,-0.75f));
                                        roadSignModel.getMain().yRot = 3.1415f;
                                    }
                                    else if(displayContext == ItemDisplayContext.THIRD_PERSON_LEFT_HAND){
                                        roadSignModel.getMain().x = 8.14f;
                                        roadSignModel.getMain().y = 7.15f;
                                        roadSignModel.getMain().z = 7.0f;
                                        roadSignModel.getMain().yRot = 3.1415f;
                                        poseStack.scale(0.5f,0.5f,0.5f);
                                        poseStack.translate(0.5f,0.75f,0.65f);
                                    }
                                    else if(displayContext == ItemDisplayContext.THIRD_PERSON_RIGHT_HAND){
                                        roadSignModel.getMain().x = 8.14f;
                                        roadSignModel.getMain().y = 7.15f;
                                        roadSignModel.getMain().z = 7.0f;
                                        roadSignModel.getMain().yRot = 3.1415f;
                                        poseStack.scale(0.5f,0.5f,0.5f);
                                        poseStack.translate(0.5f,0.75f,0.65f);
                                    }
                                    roadSignModel.getMain().render(poseStack,vc,packedLight,packedOverlay);
                                }
                            }
                            roadSignModel.getMain().render(poseStack,vc,packedLight,packedOverlay);
                        }
                        else if(modelType.equals("double_square")){
                            if(ResourceLocation.read(textureLocation).hasResultOrPartial()){
                                vc = buffer.getBuffer(RenderType.entityCutout(ResourceLocation.parse(textureLocation)));
                            }
                            else{
                                vc = buffer.getBuffer(RenderType.entityCutout(
                                        ResourceLocation.parse("thingamajigslongroads:textures/entity/signs/big_sign_error.png")));
                            }
                            doubleTallRoadSignModel.setupAnim(dynamicRoadSignBEDoubleTall);
                            if(displayContext.firstPerson()){
                                if(displayContext == ItemDisplayContext.FIRST_PERSON_LEFT_HAND){
                                    doubleTallRoadSignModel.getMain().yRot = 1.4f;
                                    doubleTallRoadSignModel.getMain().x = -8.0f;
                                    doubleTallRoadSignModel.getMain().y = 4.0f;
                                }
                                else{
                                    doubleTallRoadSignModel.getMain().yRot = -1.4f;
                                    doubleTallRoadSignModel.getMain().x = 24.0f;
                                    doubleTallRoadSignModel.getMain().y = 4.0f;
                                }
                                doubleTallRoadSignModel.getMain().render(poseStack,vc,packedLight,packedOverlay);
                            }
                            else{
                                if(displayContext == ItemDisplayContext.GUI){
                                    doubleTallRoadSignModel.getMain().offsetScale(
                                            new Vector3f(-0.5f,-0.5f,-0.5f));
                                    doubleTallRoadSignModel.getMain().x = 8f;
                                    doubleTallRoadSignModel.getMain().y = 4f;
                                    doubleTallRoadSignModel.getMain().render(poseStack,vc,packedLight,packedOverlay);
                                }
                                else{
                                    if(displayContext == ItemDisplayContext.FIXED){
                                        doubleTallRoadSignModel.getMain().offsetScale(
                                                new Vector3f(-0.5f,-0.5f,-0.5f));
                                        doubleTallRoadSignModel.getMain().x = 8f;
                                        doubleTallRoadSignModel.getMain().y = 4f;
                                        doubleTallRoadSignModel.getMain().z = 8f;
                                        doubleTallRoadSignModel.getMain().yRot = 3.1415f;
                                    }
                                    else if(displayContext == ItemDisplayContext.HEAD){
                                        doubleTallRoadSignModel.getMain().x = 8f;
                                        doubleTallRoadSignModel.getMain().y = 8f;
                                        doubleTallRoadSignModel.getMain().z = 2f;
                                        doubleTallRoadSignModel.getMain().yRot = 3.1415f;
                                    }
                                    else if(displayContext == ItemDisplayContext.GROUND){
                                        doubleTallRoadSignModel.getMain().x = 8f;
                                        doubleTallRoadSignModel.getMain().y = 8f;
                                        doubleTallRoadSignModel.getMain().z = 8f;
                                        doubleTallRoadSignModel.getMain().offsetScale(new Vector3f(-0.75f,-0.75f,-0.75f));
                                        doubleTallRoadSignModel.getMain().yRot = 3.1415f;
                                    }
                                    else if(displayContext == ItemDisplayContext.THIRD_PERSON_LEFT_HAND){
                                        doubleTallRoadSignModel.getMain().x = 8.14f;
                                        doubleTallRoadSignModel.getMain().y = 7.15f;
                                        doubleTallRoadSignModel.getMain().z = 7.0f;
                                        doubleTallRoadSignModel.getMain().yRot = 3.1415f;
                                        poseStack.scale(0.5f,0.5f,0.5f);
                                        poseStack.translate(0.5f,0.75f,0.65f);
                                    }
                                    else if(displayContext == ItemDisplayContext.THIRD_PERSON_RIGHT_HAND){
                                        doubleTallRoadSignModel.getMain().x = 8.14f;
                                        doubleTallRoadSignModel.getMain().y = 7.15f;
                                        doubleTallRoadSignModel.getMain().z = 7.0f;
                                        doubleTallRoadSignModel.getMain().yRot = 3.1415f;
                                        poseStack.scale(0.5f,0.5f,0.5f);
                                        poseStack.translate(0.5f,0.75f,0.65f);
                                    }
                                }
                            }
                            doubleTallRoadSignModel.getMain().render(poseStack,vc,packedLight,packedOverlay);
                        }
                        else if(modelType.equals("rectangle")){
                            if(ResourceLocation.read(textureLocation).hasResultOrPartial()){
                                vc = buffer.getBuffer(RenderType.entityCutout(ResourceLocation.parse(textureLocation)));
                            }
                            else{
                                vc = buffer.getBuffer(RenderType.entityCutout(
                                        ResourceLocation.parse("thingamajigslongroads:textures/entity/signs/rect_sign_error.png")));
                            }
                            rectangleRoadSignModel.setupAnim(dynamicRoadSignBERectangle);
                            if(displayContext.firstPerson()){
                                if(displayContext == ItemDisplayContext.FIRST_PERSON_LEFT_HAND){
                                    rectangleRoadSignModel.getMain().yRot = 1.4f;
                                    rectangleRoadSignModel.getMain().x = -4.0f;
                                    rectangleRoadSignModel.getMain().y = 4.0f;
                                }
                                else{
                                    rectangleRoadSignModel.getMain().yRot = -1.4f;
                                    rectangleRoadSignModel.getMain().x = 20.0f;
                                    rectangleRoadSignModel.getMain().y = 4.0f;
                                }
                                rectangleRoadSignModel.getMain().render(poseStack,vc,packedLight,packedOverlay);
                            }
                            else{
                                if(displayContext == ItemDisplayContext.GUI){
                                    rectangleRoadSignModel.getMain().offsetScale(
                                            new Vector3f(-0.5f,-0.5f,-0.5f));
                                    rectangleRoadSignModel.getMain().x = 8f;
                                    rectangleRoadSignModel.getMain().y = 8.5f;
                                    rectangleRoadSignModel.getMain().render(poseStack,vc,packedLight,packedOverlay);
                                }
                                else{
                                    if(displayContext == ItemDisplayContext.FIXED){
                                        rectangleRoadSignModel.getMain().offsetScale(
                                                new Vector3f(-0.5f,-0.5f,-0.5f));
                                        rectangleRoadSignModel.getMain().x = 8f;
                                        rectangleRoadSignModel.getMain().y = 8f;
                                        rectangleRoadSignModel.getMain().z = 8f;
                                        rectangleRoadSignModel.getMain().yRot = 3.1415f;
                                    }
                                    else if(displayContext == ItemDisplayContext.HEAD){
                                        rectangleRoadSignModel.getMain().x = 8f;
                                        rectangleRoadSignModel.getMain().y = 8f;
                                        rectangleRoadSignModel.getMain().z = 2f;
                                        rectangleRoadSignModel.getMain().yRot = 3.1415f;
                                    }
                                    else if(displayContext == ItemDisplayContext.GROUND){
                                        rectangleRoadSignModel.getMain().x = 8f;
                                        rectangleRoadSignModel.getMain().y = 8f;
                                        rectangleRoadSignModel.getMain().z = 8f;
                                        rectangleRoadSignModel.getMain().offsetScale(new Vector3f(-0.75f,-0.75f,-0.75f));
                                        rectangleRoadSignModel.getMain().yRot = 3.1415f;
                                    }
                                    else if(displayContext == ItemDisplayContext.THIRD_PERSON_LEFT_HAND){
                                        rectangleRoadSignModel.getMain().x = 8.14f;
                                        rectangleRoadSignModel.getMain().y = 7.15f;
                                        rectangleRoadSignModel.getMain().z = 7.0f;
                                        rectangleRoadSignModel.getMain().yRot = 3.1415f;
                                        poseStack.scale(0.5f,0.5f,0.5f);
                                        poseStack.translate(0.5f,0.75f,0.65f);
                                    }
                                    else if(displayContext == ItemDisplayContext.THIRD_PERSON_RIGHT_HAND){
                                        rectangleRoadSignModel.getMain().x = 8.14f;
                                        rectangleRoadSignModel.getMain().y = 7.15f;
                                        rectangleRoadSignModel.getMain().z = 7.0f;
                                        rectangleRoadSignModel.getMain().yRot = 3.1415f;
                                        poseStack.scale(0.5f,0.5f,0.5f);
                                        poseStack.translate(0.5f,0.75f,0.65f);
                                    }
                                }
                            }
                            rectangleRoadSignModel.getMain().render(poseStack,vc,packedLight,packedOverlay);
                        }
                        else{
                            vc = buffer.getBuffer(RenderType.entityCutout(
                                    ResourceLocation.parse("thingamajigslongroads:textures/entity/signs/sign_error.png")));
                            roadSignModel.setupAnim(dynamicRoadSignBE);
                            roadSignModel.getMain().render(poseStack,vc,packedLight,packedOverlay);
                        }
                    }
                    else{
                        vc = buffer.getBuffer(RenderType.entityCutout(
                                ResourceLocation.parse("thingamajigslongroads:textures/entity/signs/sign_error.png")));
                        roadSignModel.setupAnim(dynamicRoadSignBE);
                        roadSignModel.getMain().render(poseStack,vc,packedLight,packedOverlay);
                    }
                }
            }
            else{
                vc = buffer.getBuffer(RenderType.entityCutout(
                        ResourceLocation.parse("thingamajigslongroads:textures/entity/signs/default.png")));
                roadSignModel.setupAnim(dynamicRoadSignBE);
                if(displayContext.firstPerson()){
                    if(displayContext == ItemDisplayContext.FIRST_PERSON_LEFT_HAND){
                        roadSignModel.getMain().yRot = 1.4f;
                        roadSignModel.getMain().x = -1.0f;
                        roadSignModel.getMain().y = 7.0f;
                    }
                    else{
                        roadSignModel.getMain().yRot = -1.4f;
                        roadSignModel.getMain().x = 14.0f;
                        roadSignModel.getMain().y = 8.0f;
                    }
                    roadSignModel.getMain().render(poseStack,vc,packedLight,packedOverlay);
                }
                else{
                    if(displayContext == ItemDisplayContext.GUI){
                        roadSignModel.getMain().x = 8f;
                        roadSignModel.getMain().y = 8f;
                        roadSignModel.getMain().render(poseStack,vc,packedLight,packedOverlay);
                    }
                    else{
                        if(displayContext == ItemDisplayContext.FIXED){
                            roadSignModel.getMain().x = 8f;
                            roadSignModel.getMain().y = 8f;
                            roadSignModel.getMain().z = 8f;
                            roadSignModel.getMain().yRot = 3.1415f;
                        }
                        else if(displayContext == ItemDisplayContext.HEAD){
                            roadSignModel.getMain().x = 8f;
                            roadSignModel.getMain().y = 8f;
                            roadSignModel.getMain().z = 2f;
                            roadSignModel.getMain().yRot = 3.1415f;
                        }
                        else if(displayContext == ItemDisplayContext.GROUND){
                            roadSignModel.getMain().x = 8f;
                            roadSignModel.getMain().y = 6f;
                            roadSignModel.getMain().z = 8f;
                            roadSignModel.getMain().offsetScale(new Vector3f(-0.75f,-0.75f,-0.75f));
                            roadSignModel.getMain().yRot = 3.1415f;
                        }
                        else if(displayContext == ItemDisplayContext.THIRD_PERSON_LEFT_HAND){
                            roadSignModel.getMain().x = 8.14f;
                            roadSignModel.getMain().y = 7.15f;
                            roadSignModel.getMain().z = 7.0f;
                            roadSignModel.getMain().yRot = 3.1415f;
                            poseStack.scale(0.5f,0.5f,0.5f);
                            poseStack.translate(0.5f,0.75f,0.65f);
                        }
                        else if(displayContext == ItemDisplayContext.THIRD_PERSON_RIGHT_HAND){
                            roadSignModel.getMain().x = 8.14f;
                            roadSignModel.getMain().y = 7.15f;
                            roadSignModel.getMain().z = 7.0f;
                            roadSignModel.getMain().yRot = 3.1415f;
                            poseStack.scale(0.5f,0.5f,0.5f);
                            poseStack.translate(0.5f,0.75f,0.65f);
                        }
                        roadSignModel.getMain().render(poseStack,vc,packedLight,packedOverlay);
                    }
                }
            }
            if(displayContext == ItemDisplayContext.GUI){
                poseStack.translate(0D,0D,-2.5D);
                Minecraft.getInstance().getBlockRenderer()
                        .renderSingleBlock(TBlocks.VERTICAL_POLE_REDSTONE.get().defaultBlockState()
                                ,poseStack,buffer,packedLight,packedOverlay);
            }
            else if(displayContext.firstPerson()){
                if(displayContext == ItemDisplayContext.FIRST_PERSON_LEFT_HAND){
                    poseStack.mulPose(Axis.YP.rotationDegrees(90));
                    poseStack.translate(-0.2D,0.1D,-1D);
                }
                else if(displayContext == ItemDisplayContext.FIRST_PERSON_RIGHT_HAND){
                    poseStack.mulPose(Axis.YP.rotationDegrees(90));
                    poseStack.translate(-0.2D,0.1D,1D);
                }
                Minecraft.getInstance().getBlockRenderer()
                        .renderSingleBlock(TBlocks.VERTICAL_POLE_REDSTONE.get().defaultBlockState()
                                ,poseStack,buffer,packedLight,packedOverlay);
            }
            poseStack.popPose();
        }
        else{
            super.renderByItem(stack,displayContext,poseStack,buffer,packedLight,packedOverlay);
        }
    }
}

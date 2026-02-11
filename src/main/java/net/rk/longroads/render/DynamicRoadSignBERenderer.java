package net.rk.longroads.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.logging.LogUtils;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.rk.longroads.entity.blockentity.custom.DynamicRoadSignBE;
import net.rk.longroads.entity.blockentity.model.DynamicDoubleTallSignModel;
import net.rk.longroads.entity.blockentity.model.DynamicRectangleSignModel;
import net.rk.longroads.entity.blockentity.model.DynamicSignModel;
import net.rk.longroads.util.Utilities;

import java.util.Objects;

public class DynamicRoadSignBERenderer implements BlockEntityRenderer<DynamicRoadSignBE> {
    public DynamicSignModel dsmodel;
    public DynamicRectangleSignModel dsrectmodel;
    public DynamicDoubleTallSignModel dsdtmodel;

    public DynamicRoadSignBERenderer(BlockEntityRendererProvider.Context ctx){
        this.dsmodel = new DynamicSignModel(ctx.bakeLayer(DynamicSignModel.SIGN_TEXTURE_LOCATION));
        this.dsrectmodel = new DynamicRectangleSignModel(ctx.bakeLayer(DynamicRectangleSignModel.RECTANGLE_SIGN_TEXTURE_LOCATION));
        this.dsdtmodel = new DynamicDoubleTallSignModel(ctx.bakeLayer(DynamicDoubleTallSignModel.DOUBLE_TALL_SIGN_TEXTURE_LOCATION));
    }

    @Override
    public void render(DynamicRoadSignBE dynamicSignBE, float v, PoseStack poseStack, MultiBufferSource multiBufferSource, int i, int i1){
        poseStack.pushPose();
        String str;

        if(ResourceLocation.tryParse(dynamicSignBE.signTexture) != null){
            str = dynamicSignBE.signTexture;
        }
        else{
            switch(dynamicSignBE.modelType){
                case "double_square" -> str = Utilities.missingLocationBig;
                case "rectangle" -> str = Utilities.missingLocationRect;
                case null, default -> str = dynamicSignBE.fallbackSignTexture;
            }
        }

        VertexConsumer vc = multiBufferSource.getBuffer(RenderType.entityTranslucent(ResourceLocation.parse(str)));
        poseStack.translate(0.5f, -0.5f, 0.5f);

        if(Objects.equals(dynamicSignBE.modelType, Utilities.SignModelTypes.SQUARE.getModelTypeName())){
            this.dsmodel.setupAnim(dynamicSignBE);
            this.dsmodel.renderToBuffer(poseStack, vc, i, i1);
        }
        else if (Objects.equals(dynamicSignBE.modelType, Utilities.SignModelTypes.DOUBLE_SQUARE.getModelTypeName())) {
            this.dsdtmodel.setupAnim(dynamicSignBE);
            this.dsdtmodel.renderToBuffer(poseStack,vc,i,i1);
        }
        else if (Objects.equals(dynamicSignBE.modelType, Utilities.SignModelTypes.RECTANGLE.getModelTypeName())) {
            this.dsrectmodel.setupAnim(dynamicSignBE);
            this.dsrectmodel.renderToBuffer(poseStack,vc,i,i1);
        }
        else{
            this.dsmodel.setupAnim(dynamicSignBE);
            this.dsmodel.renderToBuffer(poseStack, vc, i, i1);
        }
        poseStack.popPose();
    }

    @Override
    public int getViewDistance() {
        return 54; // road signs need to be visible from a distance
    }

    @Override
    public AABB getRenderBoundingBox(DynamicRoadSignBE blockEntity){
        return new AABB(blockEntity.getBlockPos().getX() - 1, blockEntity.getBlockPos().getY(), blockEntity.getBlockPos().getZ() - 1,
                blockEntity.getBlockPos().getX() + 1, blockEntity.getBlockPos().getY() + 1, blockEntity.getBlockPos().getZ() + 1);
    }

    @Override
    public boolean shouldRender(DynamicRoadSignBE be, Vec3 vec3) {
        return Vec3.atCenterOf(be.getBlockPos()).multiply(1.0, 0.0, 1.0)
                .closerThan(vec3.multiply(1.0, 0.0, 1.0), (double)this.getViewDistance());
    }

    @Override
    public boolean shouldRenderOffScreen(DynamicRoadSignBE blockEntity) {
        return false;
    }
}

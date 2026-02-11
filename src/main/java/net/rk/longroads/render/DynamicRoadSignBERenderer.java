package net.rk.longroads.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.rk.longroads.entity.blockentity.custom.DynamicRoadSignBE;
import net.rk.longroads.entity.blockentity.model.DynamicSignModel;
import net.rk.longroads.util.Utilities;

import java.util.logging.Logger;

public class DynamicRoadSignBERenderer implements BlockEntityRenderer<DynamicRoadSignBE> {
    public DynamicSignModel dsmodel;

    public DynamicRoadSignBERenderer(BlockEntityRendererProvider.Context ctx){
        this.dsmodel = new DynamicSignModel(ctx.bakeLayer(DynamicSignModel.SIGN_TEXTURE_LOCATION));
    }

    private boolean invalidPath = false;

    @Override
    public void render(DynamicRoadSignBE dynamicSignBE, float v, PoseStack poseStack, MultiBufferSource multiBufferSource, int i, int i1){
        String str;

        if(!ResourceLocation.isValidPath(dynamicSignBE.signTexture)){
            str = Utilities.missingLocation;
            invalidPath = true;
        }
        else{
            invalidPath = false;
        }

        try{
            if (dynamicSignBE.signTexture.equals("-1")){
                str = Utilities.missingLocation;
            }
            else{
                if(invalidPath){
                    return;
                }
                else{
                    str = dynamicSignBE.signTexture;
                }
            }
            poseStack.pushPose();
            VertexConsumer vc = multiBufferSource.getBuffer(RenderType.entityTranslucent(ResourceLocation.parse(str)));
            poseStack.translate(0.5f, -0.5f, 0.5f);

            this.dsmodel.setupAnim(dynamicSignBE);
            this.dsmodel.renderToBuffer(poseStack, vc, i, i1);
            poseStack.popPose();
        }
        catch(Exception e){
            Logger.getAnonymousLogger().warning("DynamicRoadSignBE experienced rendering issues. Removing block.");
            if(dynamicSignBE.getLevel() instanceof ServerLevel){
                dynamicSignBE.getLevel().destroyBlock(dynamicSignBE.getBlockPos(),true);
            }
        }
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

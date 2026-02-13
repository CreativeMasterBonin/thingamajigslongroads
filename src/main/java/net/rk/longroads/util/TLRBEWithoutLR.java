package net.rk.longroads.util;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.rk.longroads.block.TLRBlocks;
import net.rk.longroads.entity.blockentity.custom.DynamicRoadSignBE;
import net.rk.longroads.entity.blockentity.model.DynamicSignModel;

@Deprecated(forRemoval = true,since = "1.0.0")
public class TLRBEWithoutLR extends BlockEntityWithoutLevelRenderer{
    private DynamicSignModel dynamicSignModel;
    private DynamicRoadSignBE dynamicRoadSignBE;
    private final BlockEntityRenderDispatcher blockEntityRenderDispatcher;
    private final EntityModelSet entityModelSet;
    private ChestBlockEntity chestBlock;

    public TLRBEWithoutLR(BlockEntityRenderDispatcher blockEntityRenderDispatcher, EntityModelSet entityModelSet) {
        super(blockEntityRenderDispatcher, entityModelSet);
        this.dynamicRoadSignBE = new DynamicRoadSignBE(BlockPos.ZERO,TLRBlocks.ROAD_SIGN.get().defaultBlockState());
        this.dynamicRoadSignBE.indexId = 0;
        this.blockEntityRenderDispatcher = blockEntityRenderDispatcher;
        this.entityModelSet = entityModelSet;
        this.chestBlock = new ChestBlockEntity(BlockPos.ZERO, Blocks.ENDER_CHEST.defaultBlockState());
    }

    @Override
    public void onResourceManagerReload(ResourceManager resourceManager) {
        this.dynamicSignModel = new DynamicSignModel(this.entityModelSet.bakeLayer(DynamicSignModel.SIGN_TEXTURE_LOCATION));
    }

    @Override
    public void renderByItem(ItemStack stack, ItemDisplayContext displayContext, PoseStack poseStack, MultiBufferSource buffer, int packedLight, int packedOverlay) {
        if(stack.is(TLRBlocks.ROAD_SIGN.asItem())){
            poseStack.pushPose();
            poseStack.scale(2.0F, 2.0F, 2.0F);
            VertexConsumer vc = buffer.getBuffer(RenderType.entityTranslucent(ResourceLocation.parse(Utilities.missingLocation)));
            this.dynamicSignModel.setupAnim(this.dynamicRoadSignBE);
            this.dynamicSignModel.renderToBuffer(poseStack,
                    vc,
                    packedLight,packedOverlay);
            Object blockentity = this.chestBlock;
            this.blockEntityRenderDispatcher.renderItem((BlockEntity)blockentity,poseStack,buffer,packedLight,packedOverlay);
            poseStack.popPose();
        }
        else {
            super.renderByItem(stack,displayContext,poseStack,buffer,packedLight,packedOverlay);
        }
    }
}

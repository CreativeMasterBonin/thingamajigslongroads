package net.rk.longroads.util;

import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.server.packs.resources.ResourceManager;
import net.rk.longroads.entity.blockentity.custom.DynamicRoadSignBE;
import net.rk.longroads.entity.blockentity.model.DynamicSignModel;

public class TLRBEWithoutLR extends BlockEntityWithoutLevelRenderer{
    public TLRBEWithoutLR(BlockEntityRenderDispatcher blockEntityRenderDispatcher, EntityModelSet entityModelSet) {
        super(blockEntityRenderDispatcher, entityModelSet);
    }

    private DynamicSignModel dynamicSignModel;
    private DynamicRoadSignBE dynamicRoadSignBE;

    @Override
    public void onResourceManagerReload(ResourceManager resourceManager) {

    }
}

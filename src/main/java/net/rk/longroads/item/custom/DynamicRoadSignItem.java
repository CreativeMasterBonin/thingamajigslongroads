package net.rk.longroads.item.custom;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import net.rk.longroads.util.TLRBEWithoutLR;

import java.util.function.Consumer;

public class DynamicRoadSignItem extends BlockItem{
    public DynamicRoadSignItem(Block block, Properties properties) {
        super(block, properties);
    }

    public static final class RoadSignClientExtensions implements IClientItemExtensions{
        public static final RoadSignClientExtensions INSTANCE = new RoadSignClientExtensions();

        @Override
        public BlockEntityWithoutLevelRenderer getCustomRenderer() {
            return new TLRBEWithoutLR(Minecraft.getInstance().getBlockEntityRenderDispatcher(),Minecraft.getInstance().getEntityModels());
        }
    }
}

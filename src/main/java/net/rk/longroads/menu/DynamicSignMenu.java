package net.rk.longroads.menu;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.DataSlot;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.rk.longroads.entity.blockentity.custom.DynamicRoadSignBE;
import net.rk.longroads.registries.SignType;
import net.rk.longroads.registries.TLRRegistries;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class DynamicSignMenu extends AbstractContainerMenu{
    public Level level;
    public Player player;
    public DynamicRoadSignBE be;
    public BlockPos pos;
    public final DataSlot signTypeData = DataSlot.standalone();
    public static List<SignType> signTypes;
    public int indexSelected;

    protected DynamicSignMenu(@Nullable MenuType<?> menuType, int containerId) {
        super(menuType, containerId);
    }

    public int getIndex(){
        return indexSelected;
    }

    public DynamicSignMenu(int id, Inventory inv, FriendlyByteBuf extraData){
        this(TLRMenu.SIGN_MENU.get(),id);
        this.player = inv.player;
        this.level = player.level();
        if(extraData != null) {
            BlockPos pos1 = extraData.readBlockPos();
            this.pos = pos1;
            if (level.getBlockEntity(pos) instanceof DynamicRoadSignBE) {
                this.be = (DynamicRoadSignBE) level.getBlockEntity(pos);
            }
        }
        this.addDataSlot(signTypeData);
        DynamicSignMenu.signTypes = this.level.registryAccess().registryOrThrow(TLRRegistries.SIGN_TYPE).stream().toList();

        addDataSlot(new DataSlot(){
            @Override
            public int get() {
                return be.indexId & 0xffff;
            }

            @Override
            public void set(int pValue) {
                DynamicSignMenu.this.indexSelected = (DynamicSignMenu.this.indexSelected & 0xffff0000) | (pValue & 0xffff);
            }
        });
    }

    @Override
    public ItemStack quickMoveStack(Player player, int i) {
        return null;
    }

    @Override
    public boolean stillValid(Player player) {
        return player.level().getBlockEntity(be.getBlockPos()) instanceof DynamicRoadSignBE;
    }

    public boolean clickedSignTypeSelectorButton(Player player, int id) {
        if (id >= 0 && id < this.signTypes.size()) {
            return true;
        }
        else {
            return false;
        }
    }
}

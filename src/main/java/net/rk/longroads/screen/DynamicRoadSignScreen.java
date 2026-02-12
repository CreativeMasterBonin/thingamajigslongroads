package net.rk.longroads.screen;

import com.mojang.blaze3d.platform.Lighting;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.logging.LogUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.blockentity.BeaconRenderer;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;
import net.neoforged.neoforge.network.PacketDistributor;
import net.rk.longroads.ThingamajigsLongRoads;
import net.rk.longroads.menu.DynamicSignMenu;
import net.rk.longroads.network.record.DynamicSignPayload;
import net.rk.longroads.registries.SignType;
import net.rk.longroads.screen.widget.DynamicEditBox;
import net.rk.longroads.util.Utilities;
import net.rk.thingamajigs.screen.widget.RevertedButton;
import net.rk.thingamajigs.xtras.TColors;

import java.util.List;

public class DynamicRoadSignScreen extends AbstractContainerScreen<DynamicSignMenu> {
    private static final ResourceLocation BG_TEXTURE =
            ResourceLocation.parse("thingamajigslongroads:textures/gui/road_sign_bg.png");

    private static final ResourceLocation LIST_BG =
            ResourceLocation.parse("thingamajigslongroads:textures/gui/road_sign_list_bg.png");

    private RevertedButton decreaseRotation;
    private RevertedButton increaseRotation;
    private RevertedButton roundRotation;

    private EditBox customTextureEdit;

    public boolean scrolling = false;
    public float scrollOffs = 0;
    public int startRow = 0;

    public int listButtonStartX = 184;
    public int listButtonStartY = 96;

    public int listBGX = 183;
    public int listBGY = 94;

    public int scrollerStartX = 241;
    public int scrollerStartY = 95;

    private int buttonSizeX = 14;
    private int buttonSizeY = 14;


    public DynamicRoadSignScreen(DynamicSignMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
        this.imageWidth = 320;
        this.imageHeight = 240;
    }

    @Override
    protected void init() {
        super.init();
        setup();
        addRenderableWidget(decreaseRotation);
        addRenderableWidget(increaseRotation);
        addRenderableWidget(roundRotation);
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float v, int mouseX, int mouseY) {
        // render the bg behind gui elements
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.setShaderTexture(0, BG_TEXTURE);
        guiGraphics.blit(BG_TEXTURE, this.leftPos, this.topPos, 0.0F, 0.0F, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);
        RenderSystem.disableBlend();

        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.setShaderTexture(0, BG_TEXTURE);
        guiGraphics.blit(LIST_BG, this.leftPos + listBGX, this.topPos + listBGY, 0.0F, 0.0F,
                71, 60, 71, 60);
        RenderSystem.disableBlend();

        guiGraphics.pose().pushPose();
        guiGraphics.pose().translate(this.leftPos + 132,this.topPos + 20, 0);
        guiGraphics.pose().scale(64,36,0);

        if(ResourceLocation.tryParse(this.menu.be.signTexture) == null){
            BeaconRenderer.renderBeaconBeam(guiGraphics.pose(), guiGraphics.bufferSource(),ResourceLocation.parse(this.menu.be.fallbackSignTexture),
                    0,2,0,0,1, TColors.getWhite(),1,0);
        }
        else{
            BeaconRenderer.renderBeaconBeam(guiGraphics.pose(), guiGraphics.bufferSource(),ResourceLocation.parse(this.menu.be.signTexture),
                    0,2,0,0,1, TColors.getWhite(),1,0);
        }
        guiGraphics.pose().popPose();

        int k = (int)(41.0F * this.scrollOffs);
        ResourceLocation resourcelocation = ResourceLocation.withDefaultNamespace("container/loom/scroller");
        guiGraphics.blitSprite(resourcelocation, this.leftPos + scrollerStartX, this.topPos + scrollerStartY + k, 12, 15);
        Lighting.setupForFlatItems();

        int j2 = this.leftPos + listButtonStartX;
        int k2 = this.topPos + listButtonStartY;
        List<SignType> list = this.menu.signTypes;

        label64:
        for (int l = 0; l < 4; l++) {
            for (int i1 = 0; i1 < 4; i1++) {
                int j1 = l + startRow;
                int k1 = j1 * 4 + i1;
                if (k1 >= list.size()) {
                    break label64;
                }

                int x = j2 + i1 * buttonSizeX;
                int y = k2 + l * buttonSizeY;
                boolean flag = mouseX >= x && mouseY >= y && mouseX < x + buttonSizeX && mouseY < y + buttonSizeY;
                ResourceLocation resourcelocation1;
                if (k1 == this.menu.be.indexId) {
                    resourcelocation1 = RevertedButton.LASER_SPRITES.get(true,false);
                }
                else if (flag) {
                    resourcelocation1 = RevertedButton.LASER_SPRITES.get(true,true);
                }
                else {
                    resourcelocation1 = RevertedButton.LASER_SPRITES.get(false,false);
                }

                guiGraphics.blitSprite(resourcelocation1, x, y, buttonSizeX, buttonSizeY);
                if(DynamicSignMenu.signTypes != null){
                    ResourceLocation res = ResourceLocation.parse(
                            ThingamajigsLongRoads.MODID +
                                    ":textures/entity/signs/" +
                                    DynamicSignMenu.signTypes.get(k1).assetId().getPath() + ".png");
                    if(res == null){
                        res = ResourceLocation.parse(Utilities.missingLocation);
                    }
                    //
                    if(DynamicSignMenu.signTypes.get(k1).signModeltype().equals(Utilities.SignModelTypes.SQUARE.getModelTypeName())){
                        guiGraphics.blit(res, x,y,
                                0,0,
                                14,14,
                                32,32);
                    }
                    else if(DynamicSignMenu.signTypes.get(k1).signModeltype().equals(Utilities.SignModelTypes.DOUBLE_SQUARE.getModelTypeName())){
                        guiGraphics.blit(res, x,y,
                                0,0,
                                14,14,
                                128,128);
                    }
                    else if(DynamicSignMenu.signTypes.get(k1).signModeltype().equals(Utilities.SignModelTypes.RECTANGLE.getModelTypeName())){
                        guiGraphics.blit(res, x,y,
                                0,0,
                                14,14,
                                128,64);
                    }
                }
            }
        }

        Lighting.setupFor3DItems();
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        super.render(guiGraphics, mouseX, mouseY, partialTick);
    }

    @Override
    protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        guiGraphics.drawString(this.font, this.title, this.titleLabelX, this.titleLabelY, 4210752, false);

        String translationKey = this.menu.be.holderList.typesHolderObjectList().get(0).getSignType().translationKey();

        guiGraphics.drawString(this.font,Component.translatable("container.thingamajigslongroads.dynamic_sign.sign_type")
                        .append(Component.translatable(translationKey)),
                this.titleLabelX + 9,this.titleLabelY + 98,
                TColors.getWhite(),true);
    }


    @Override
    public boolean charTyped(char codePoint, int modifiers) {
        return this.customTextureEdit.charTyped(codePoint,modifiers);
    }

    @Override
    public boolean keyPressed(int key, int b, int c) {
        return this.customTextureEdit.keyPressed(key,b,c) || (this.customTextureEdit.isFocused() && key != 256) || super.keyPressed(key,b,c);
    }


    private void setup(){
        int horzLeftButtonPos = leftPos + 100;
        int topRowButtonY = topPos + 68;
        int spacingButtonWidth = 2;
        int spacingButtonHeight = spacingButtonWidth;
        float lowPitch = 0.95f;
        float normalPitch = 1.0f;

        String assetID = "thingamajigslongroads:test";

        // this is not used
        customTextureEdit = new DynamicEditBox(this.font,this.leftPos + 12,this.topPos + 139,154,19,
                Component.literal(assetID)
                        .withStyle(ChatFormatting.DARK_GRAY));
        customTextureEdit.setEditable(true);

        try{
            assetID = this.menu.be.holderList.typesHolderObjectList().getFirst().getSignType().assetId().toString();
            customTextureEdit.setValue(assetID);
        }
        catch (Exception e){
            LogUtils.getLogger().error("DynamicSignMenu SELECT_SIGN_TYPE did not return data to DynamicRoadSignScreen properly. ERR: {}", e.getMessage());
        }


        decreaseRotation = new RevertedButton(horzLeftButtonPos,topRowButtonY,64,16,
                Component.translatable("button.thingamajigslongroads.rotation_left"),(handler) -> {
            PacketDistributor.sendToServer(new DynamicSignPayload(
                    this.menu.pos,
                    this.menu.be.yAngle - 0.05f,
                    this.menu.be.indexId,
                    false
            ));
            Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forUI(SoundEvents.ITEM_PICKUP,lowPitch));
        }){};

        int horzRightButtonPos = decreaseRotation.getX() + decreaseRotation.getWidth() + spacingButtonWidth;

        increaseRotation = new RevertedButton(horzRightButtonPos,topRowButtonY,64,16,
                Component.translatable("button.thingamajigslongroads.rotation_right"),(handler) -> {
            PacketDistributor.sendToServer(new DynamicSignPayload(
                    this.menu.pos,
                    this.menu.be.yAngle + 0.05f,
                    this.menu.be.indexId,
                    false
            ));
            Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forUI(SoundEvents.ITEM_PICKUP,normalPitch));
        }){};

        roundRotation = new RevertedButton(horzLeftButtonPos,topRowButtonY + 72,64,16,
                Component.translatable("button.thingamajigslongroads.round_rotation"),(handler)->{
            PacketDistributor.sendToServer(new DynamicSignPayload(
                    this.menu.pos,
                    Math.round(this.menu.be.yAngle),
                    this.menu.be.indexId,
                    false
            ));
        });
    }


    // from Loom: mouse actions and controls (edited)
    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        this.scrolling = false;
        int i = this.leftPos + listButtonStartX;
        int j = this.topPos + listButtonStartY;

        // math and server data for list of buttons when clicked
        for (int k = 0; k < 4; k++) { // was k < 4
            for (int l = 0; l < 4; l++) { // was l < 4
                double d0 = mouseX - (double)(i + l * 14);
                double d1 = mouseY - (double)(j + k * 14);
                int i1 = k + this.startRow;
                int buttonIndex = i1 * 4 + l;
                if (d0 >= 0.0 && d1 >= 0.0 && d0 < 14.0 && d1 < 14.0 && this.menu.clickedSignTypeSelectorButton(this.minecraft.player, buttonIndex)) {
                    Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forUI(SoundEvents.UI_BUTTON_CLICK, 1.0F));
                    this.minecraft.gameMode.handleInventoryButtonClick(this.menu.containerId, buttonIndex);
                    PacketDistributor.sendToServer(new DynamicSignPayload(
                            this.menu.pos,
                            this.menu.be.yAngle,
                            buttonIndex,
                            true

                    ));
                    return true;
                }
            }
        }

        i = this.leftPos + 119;
        j = this.topPos + 9;
        if (mouseX >= (double)i && mouseX < (double)(i + 12) && mouseY >= (double)j && mouseY < (double)(j + 56)) {
            this.scrolling = true;
        }

        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double dragX, double dragY) {
        int i = this.menu.signTypes.size() - 4;
        if (this.scrolling && i > 0) {
            int j = this.topPos + 13;
            int k = j + 56;
            this.scrollOffs = ((float)mouseY - (float)j - 7.5F) / ((float)(k - j) - 15.0F);
            this.scrollOffs = Mth.clamp(this.scrollOffs, 0.0F, 1.0F);
            this.startRow = Math.max((int)((double)(this.scrollOffs * (float)i) + 0.5), 0);
            return true;
        }
        else {
            return super.mouseDragged(mouseX, mouseY, button, dragX, dragY);
        }
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double scrollX, double scrollY) {
        int i = this.menu.signTypes.size() - 4;
        if (i > 0) {
            float f = (float)scrollY / (float)i;
            this.scrollOffs = Mth.clamp(this.scrollOffs - f, 0.0F, 1.0F);
            this.startRow = Math.max((int)(this.scrollOffs * (float)i + 0.5F), 0);
        }

        return true;
    }

    @Override
    protected boolean hasClickedOutside(double mouseX, double mouseY, int guiLeft, int guiTop, int mouseButton) {
        return mouseX < (double)guiLeft
                || mouseY < (double)guiTop
                || mouseX >= (double)(guiLeft + this.imageWidth)
                || mouseY >= (double)(guiTop + this.imageHeight);
    }

    @Override
    protected void containerTick(){
        if(startRow > this.menu.signTypes.size()){
            startRow = 0;
            scrollOffs = 0.0f;
        }
    }
}

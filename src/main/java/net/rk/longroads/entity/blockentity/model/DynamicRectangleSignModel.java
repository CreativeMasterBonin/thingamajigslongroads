package net.rk.longroads.entity.blockentity.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.rk.longroads.entity.blockentity.custom.DynamicRoadSignBE;
import net.rk.longroads.util.Utilities;

public class DynamicRectangleSignModel extends Model{
    public static final ModelLayerLocation RECTANGLE_SIGN_TEXTURE_LOCATION = new ModelLayerLocation(
            ResourceLocation.parse(Utilities.missingLocationRect), "main");

    public ModelPart main;

    public DynamicRectangleSignModel(ModelPart root) {
        super(RenderType::entityTranslucent);
        this.main = root.getChild("signholder");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition signholder = partdefinition.addOrReplaceChild("signholder", CubeListBuilder.create().texOffs(0, 0).addBox(-16.0F, -8.0F, -1.1F, 32.0F, 16.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 16.0F, 0.0F));

        PartDefinition back_r1 = signholder.addOrReplaceChild("back_r1", CubeListBuilder.create().texOffs(0, 16).addBox(-16.0F, -8.0F, 0.0F, 32.0F, 16.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -1.09F, 0.0F, 3.1416F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 32);
    }

    public void setupAnim(DynamicRoadSignBE be){
        main.yRot = be.yAngle;
        main.zRot = 0;
        main.xRot = Mth.PI;
        main.y = 16.0f;
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int i, int i1, int i2) {
        this.main.render(poseStack,vertexConsumer,i,i1);
    }
}

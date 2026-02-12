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
import net.rk.longroads.entity.blockentity.custom.DynamicRoadSignBE;
import net.rk.longroads.util.Utilities;

public class DynamicDoubleTallSignModel extends Model {
    public static final ModelLayerLocation DOUBLE_TALL_SIGN_TEXTURE_LOCATION = new ModelLayerLocation(
            ResourceLocation.parse(Utilities.missingLocationBig), "main");

    public ModelPart main;

    public DynamicDoubleTallSignModel(ModelPart root) {
        super(RenderType::entityTranslucent);
        this.main = root.getChild("signholder");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition signholder = partdefinition.addOrReplaceChild("signholder", CubeListBuilder.create().texOffs(0, 0).addBox(-16.0F, -24.0F, -1.2F, 32.0F, 32.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 16.0F, 0.0F));

        PartDefinition back_r1 = signholder.addOrReplaceChild("back_r1", CubeListBuilder.create().texOffs(0, 32).addBox(-16.0F, -24.0F, 0.08F, 32.0F, 32.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -1.09F, 0.0F, 3.1416F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    public ModelPart getMain() {
        return main;
    }

    public void setupAnim(DynamicRoadSignBE be){
        main.yRot = be.yAngle;
        main.zRot = 0;
        main.xRot = 3.14555111f;
        main.y = 16.0f;
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int i, int i1, int i2) {
        this.main.render(poseStack,vertexConsumer,i,i1);
    }
}

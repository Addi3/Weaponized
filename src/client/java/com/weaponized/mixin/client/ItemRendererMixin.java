package com.weaponized.mixin.client;



import com.weaponized.Weaponized;
import com.weaponized.core.WeaponizedItems;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.ItemModels;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Mixin for the ItemRenderer to handle custom rendering of the Carrion Cleaver item.
 * This mixin modifies the model used for the Carrion Cleaver when it is held in hand.
 * @author Loqor
 * @license GNU General Public License v3.0
 */
@Mixin(ItemRenderer.class)
public class ItemRendererMixin {

    @Unique
    private static final ModelIdentifier HAND_MODEL = new ModelIdentifier(Weaponized.MOD_ID,"carrion_cleaver", "inventory");
    @Unique
    private static final ModelIdentifier HANDHELD_CARRION_CLEAVER = new ModelIdentifier(Weaponized.MOD_ID,"handheld_carrion_cleaver", "inventory");

    @Final
    @Shadow
    private ItemModels models;


    @Inject(method = "getModel(Lnet/minecraft/item/ItemStack;Lnet/minecraft/world/World;Lnet/minecraft/entity/LivingEntity;I)Lnet/minecraft/client/render/model/BakedModel;", at = @At("HEAD"), cancellable = true)
    private void weaponized$getModel(ItemStack stack, World world, LivingEntity entity, int seed, CallbackInfoReturnable<BakedModel> cir) {
        if (stack.isOf(WeaponizedItems.CARRION_CLEAVER)) {
            BakedModel bakedModel = this.models.getModelManager().getModel(HAND_MODEL);
            ClientWorld clientWorld = world instanceof ClientWorld ? (ClientWorld)world : null;
            BakedModel bakedModel2 = bakedModel.getOverrides().apply(bakedModel, stack, clientWorld, entity, seed);
            cir.setReturnValue(bakedModel2 == null ? this.models.getModelManager().getMissingModel() : bakedModel2);
        }
    }

    @ModifyVariable(method = "renderItem(Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/render/model/json/ModelTransformationMode;ZLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;IILnet/minecraft/client/render/model/BakedModel;)V", at = @At(value = "HEAD"), argsOnly = true)
    public BakedModel weaponized$renderCarrionCleaver(BakedModel value, ItemStack stack, ModelTransformationMode renderMode, boolean leftHanded, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        if (stack.isOf(WeaponizedItems.CARRION_CLEAVER) && renderMode != ModelTransformationMode.GUI && renderMode != ModelTransformationMode.GROUND) {
            return this.models.getModelManager().getModel(HANDHELD_CARRION_CLEAVER);
        }
        return value;
    }
}

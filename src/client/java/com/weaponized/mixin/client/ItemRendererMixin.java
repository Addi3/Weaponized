package com.weaponized.mixin.client;



import com.weaponized.Weaponized;
import com.weaponized.core.WeaponizedItems;
import net.minecraft.client.render.item.ItemModels;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemRenderer.class)
public class ItemRendererMixin {

    @Unique
    private static final Identifier HAND_MODEL = new Identifier(Weaponized.MOD_ID, "handheld_carrion_cleaver");
    @Unique
    private static final ModelIdentifier HAND_MODEL_ID = new ModelIdentifier(HAND_MODEL, "inventory");

    @Final
    @Shadow
    private ItemModels models;


    @Inject(method = "getModel(Lnet/minecraft/item/ItemStack;Lnet/minecraft/world/World;Lnet/minecraft/entity/LivingEntity;I)Lnet/minecraft/client/render/model/BakedModel;", at = @At("HEAD"), cancellable = true)
    private void ait$getModel(ItemStack stack, World world, LivingEntity entity, int seed, CallbackInfoReturnable<BakedModel> cir) {
        if (stack.isOf(WeaponizedItems.CARRION_CLEAVER)) {
            BakedModel bakedModel = this.models.getModelManager().getModel(HAND_MODEL_ID);
            ClientWorld clientWorld = world instanceof ClientWorld ? (ClientWorld)world : null;
            BakedModel bakedModel2 = bakedModel.getOverrides().apply(bakedModel, stack, clientWorld, entity, seed);
            cir.setReturnValue(bakedModel2 == null ? this.models.getModelManager().getMissingModel() : bakedModel2);
        }
    }
}

package com.weaponized.core;

import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

public class WeaponizedToolMaterials {
    public static final ToolMaterial CARRION_CLEAVER = new ToolMaterial() {
        @Override
        public int getDurability() {
            return 1500;
        }

        @Override
        public float getMiningSpeedMultiplier() {
            return 0;
        }

        @Override
        public float getAttackDamage() {
            return 1;
        }

        @Override
        public int getMiningLevel() {
            return 0;
        }

        @Override
        public int getEnchantability() {
            return 0;
        }

        @Override
        public Ingredient getRepairIngredient() {
            return null;
        }
    };

    public static final ToolMaterial CARRION_SCYTHE = new ToolMaterial() {
        @Override
        public int getDurability() {
            return 1300;
        }

        @Override
        public float getMiningSpeedMultiplier() {
            return 1;
        }

        @Override
        public float getAttackDamage() {
            return 5;
        }

        @Override
        public int getMiningLevel() {
            return 1;
        }

        @Override
        public int getEnchantability() {
            return 3;
        }

        @Override
        public Ingredient getRepairIngredient() {
            return null;
        }
    };
}

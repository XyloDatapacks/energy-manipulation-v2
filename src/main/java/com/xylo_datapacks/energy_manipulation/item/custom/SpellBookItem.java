package com.xylo_datapacks.energy_manipulation.item.custom;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;

public class SpellBookItem extends Item {
    private static final String CHARGED_KEY = "Charged";
    private boolean charged = false;
    private boolean loaded = false;
    private boolean completed = false;
    
    public SpellBookItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        if (SpellBookItem.isCharged(itemStack)) {
            SpellBookItem.setCharged(itemStack, false);
            return TypedActionResult.consume(itemStack);
        }
        if (!SpellBookItem.isCharged(itemStack)) {
            this.charged = false;
            this.loaded = false;
            this.completed = false;
            user.setCurrentHand(hand);
        }
        return TypedActionResult.fail(itemStack);
    }

    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        int i = this.getMaxUseTime(stack) - remainingUseTicks;
        float f = SpellBookItem.getPullProgress(i, stack);
        if (f >= 0.2f && !SpellBookItem.isCharged(stack)) {
            SpellBookItem.setCharged(stack, true);
            SoundCategory soundCategory = user instanceof PlayerEntity ? SoundCategory.PLAYERS : SoundCategory.HOSTILE;
            world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.ITEM_CROSSBOW_LOADING_END, soundCategory, 1.0f, 1.0f / (world.getRandom().nextFloat() * 0.5f + 1.0f) + 0.2f);
        }
    }

    public static boolean isCharged(ItemStack stack) {
        NbtCompound nbtCompound = stack.getNbt();
        return nbtCompound != null && nbtCompound.getBoolean(CHARGED_KEY);
    }

    public static void setCharged(ItemStack stack, boolean charged) {
        NbtCompound nbtCompound = stack.getOrCreateNbt();
        nbtCompound.putBoolean(CHARGED_KEY, charged);
    }

    @Override
    public void usageTick(World world, LivingEntity user, ItemStack stack, int remainingUseTicks) {
        if (!world.isClient) {
            int i = EnchantmentHelper.getLevel(Enchantments.QUICK_CHARGE, stack);
            SoundEvent soundEvent = this.getQuickChargeSound(i);
            SoundEvent soundEvent2 = i == 0 ? SoundEvents.ITEM_CROSSBOW_LOADING_MIDDLE : null;
            float f = (float)(stack.getMaxUseTime() - remainingUseTicks) / (float) SpellBookItem.getPullTime(stack);
            if (f < 0.2f) {
                this.charged = false;
                this.loaded = false;
                this.completed = false;
            }
            if (f >= 0.2f && !this.charged) {
                this.charged = true;
                world.playSound(null, user.getX(), user.getY(), user.getZ(), soundEvent, SoundCategory.PLAYERS, 0.5f, 1.0f);
            }
            if (f >= 0.5f && soundEvent2 != null && !this.loaded) {
                this.loaded = true;
                world.playSound(null, user.getX(), user.getY(), user.getZ(), soundEvent2, SoundCategory.PLAYERS, 0.5f, 1.0f);
            }
        }
    }
    
    @Override
    public int getMaxUseTime(ItemStack stack) {
        return SpellBookItem.getPullTime(stack) + 3;
    }

    public static int getPullTime(ItemStack stack) {
        int i = EnchantmentHelper.getLevel(Enchantments.QUICK_CHARGE, stack);
        return i == 0 ? 25 : 25 - 5 * i;
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.BLOCK;
    }

    private SoundEvent getQuickChargeSound(int stage) {
        switch (stage) {
            case 1: {
                return SoundEvents.ITEM_CROSSBOW_QUICK_CHARGE_1;
            }
            case 2: {
                return SoundEvents.ITEM_CROSSBOW_QUICK_CHARGE_2;
            }
            case 3: {
                return SoundEvents.ITEM_CROSSBOW_QUICK_CHARGE_3;
            }
        }
        return SoundEvents.ITEM_CROSSBOW_LOADING_START;
    }

    private static float getPullProgress(int useTicks, ItemStack stack) {
        float f = (float)useTicks / (float) SpellBookItem.getPullTime(stack);
        if (f > 1.0f) {
            f = 1.0f;
        }
        return f;
    }

    @Override
    public boolean isUsedOnRelease(ItemStack stack) {
        return stack.isOf(this);
    }
    
}

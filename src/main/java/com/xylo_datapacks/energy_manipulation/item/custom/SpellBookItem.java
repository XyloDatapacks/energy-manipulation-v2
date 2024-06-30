package com.xylo_datapacks.energy_manipulation.item.custom;

import com.xylo_datapacks.energy_manipulation.EnergyManipulation;
import com.xylo_datapacks.energy_manipulation.config.SpellBookInfo;
import com.xylo_datapacks.energy_manipulation.ui.spell_book.SpellBookScreenHandler;
import net.fabricmc.fabric.api.item.v1.FabricItem;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.Generic3x3ContainerScreenHandler;
import net.minecraft.screen.GenericContainerScreenHandler;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class SpellBookItem extends Item implements FabricItem {
    private static final String CHARGED_KEY = "Charged";
    private static final String CHARGE_KEY = "Charge";
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
            SpellBookItem.setCharge(itemStack, 0.f);
            return TypedActionResult.success(itemStack);
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
        if (!SpellBookItem.isCharged(stack)) {
            SpellBookItem.setCharged(stack, true);
            SpellBookItem.setCharge(stack, f);
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

    public static float getCharge(ItemStack stack) {
        NbtCompound nbtCompound = stack.getNbt();
        if (nbtCompound == null) {
            return 0.0f;
        }
        return nbtCompound.getFloat(CHARGE_KEY);
    }
    
    public static void setCharge(ItemStack stack, float charge) {
        NbtCompound nbtCompound = stack.getOrCreateNbt();
        nbtCompound.putFloat(CHARGE_KEY, charge);
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
            if (f >= 0.75f && !this.completed) {
                this.completed = true;
                //world.playSound(null, user.getX(), user.getY(), user.getZ(), soundEvent2, SoundCategory.PLAYERS, 0.5f, 1.0f);
            }
        }
    }
    
    @Override
    public int getMaxUseTime(ItemStack stack) {
        return 72000;
    }

    public int internalGetMaxUseTime(ItemStack stack) {
        return SpellBookItem.getPullTime(stack) + 3;
    }

    public static int getPullTime(ItemStack stack) {
        int i = EnchantmentHelper.getLevel(Enchantments.QUICK_CHARGE, stack);
        return i == 0 ? 25 : 25 - 5 * i;
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.CROSSBOW;
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

    @Override
    public boolean allowNbtUpdateAnimation(PlayerEntity player, Hand hand, ItemStack oldStack, ItemStack newStack) {
        return SpellBookItem.getCharge(oldStack) == SpellBookItem.getCharge(new ItemStack(this));
    }


    public static void openScreen(PlayerEntity player, ItemStack backpackItemStack) {
        if (player.getWorld() != null && !player.getWorld().isClient()) {
            player.openHandledScreen(new ExtendedScreenHandlerFactory() {
                @Override
                public void writeScreenOpeningData(ServerPlayerEntity serverPlayerEntity, PacketByteBuf packetByteBuf) {
                    packetByteBuf.writeItemStack(backpackItemStack);
                }

                @Override
                public Text getDisplayName() {
                    return Text.translatable(backpackItemStack.getItem().getTranslationKey());
                }

                @Override
                public @Nullable ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
                    return new SpellBookScreenHandler(syncId, inv, backpackItemStack);
                }
            });
        }
    }
    
    public SpellBookInfo getTier() {
        return new SpellBookInfo("spell book", 9, 1, false, "");
    }


    public static boolean isBackpackEmpty(ItemStack stack) {
        NbtList tag = stack.getOrCreateNbt().getList("Inventory", NbtElement.COMPOUND_TYPE);

        // If any inventory element in the Backpack stack is non-empty, return false;
        for (NbtElement element : tag) {
            NbtCompound stackTag = (NbtCompound) element;
            ItemStack backpackStack = ItemStack.fromNbt(stackTag.getCompound("Stack"));
            if (!backpackStack.isEmpty()) {
                return false;
            }
        }

        return true;
    }

    public static List<ItemStack> getBackpackContents(ItemStack stack) {
        List<ItemStack> stacks = new ArrayList<>();
        NbtList tag = stack.getOrCreateNbt().getList("Inventory", NbtElement.COMPOUND_TYPE);

        // If any inventory element in the Backpack stack is non-empty, return false;
        for (NbtElement element : tag) {
            NbtCompound stackTag = (NbtCompound) element;
            ItemStack backpackStack = ItemStack.fromNbt(stackTag.getCompound("Stack"));
            stacks.add(backpackStack);
        }

        return stacks;
    }

    public static void wipeBackpack(ItemStack stack) {
        stack.getOrCreateNbt().remove("Inventory");
    }

    public static Identifier id(String name) {
        return new Identifier(EnergyManipulation.MOD_ID, name);
    }

}

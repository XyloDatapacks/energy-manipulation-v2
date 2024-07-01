package com.xylo_datapacks.energy_manipulation.screen.spell_book;

import com.xylo_datapacks.energy_manipulation.EnergyManipulation;
import com.xylo_datapacks.energy_manipulation.config.SpellBookInfo;
import com.xylo_datapacks.energy_manipulation.item.custom.SpellBookItem;
import com.xylo_datapacks.energy_manipulation.screen.Dimension;
import com.xylo_datapacks.energy_manipulation.screen.Point;
import com.xylo_datapacks.energy_manipulation.util.InventoryUtils;
import io.wispforest.owo.client.screens.OwoScreenHandler;
import net.minecraft.block.ShulkerBoxBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.village.TradeOfferList;

public class SpellBookScreenHandler extends ScreenHandler {

    private final ItemStack spellBookStack;
    private final int padding = 8;
    private final int titleSpace = 10;

    public SpellBookScreenHandler(int synchronizationID, PlayerInventory playerInventory, PacketByteBuf packetByteBuf) {
        this(synchronizationID, playerInventory, packetByteBuf.readItemStack());
    }

    public SpellBookScreenHandler(int synchronizationID, PlayerInventory playerInventory, ItemStack spellBookStack) {
        super(EnergyManipulation.SPELL_BOOK_MENU_TYPE, synchronizationID);
        this.spellBookStack = spellBookStack;

        if (spellBookStack.getItem() instanceof SpellBookItem) {
            setupContainer(playerInventory, spellBookStack);
        } else {
            PlayerEntity player = playerInventory.player;
            this.onClosed(player);
        }
    }

    private void setupContainer(PlayerInventory playerInventory, ItemStack spellBookStack) {
        Dimension dimension = getDimension();
        SpellBookInfo tier = getItem().getTier();
        int rowWidth = tier.getRowWidth();
        int numberOfRows = tier.getNumberOfRows();

        NbtList tag = spellBookStack.getOrCreateNbt().getList("Inventory", NbtElement.COMPOUND_TYPE);
        BackpackInventory inventory = new BackpackInventory(rowWidth * numberOfRows) {
            @Override
            public void markDirty() {
                spellBookStack.getOrCreateNbt().put("Inventory", InventoryUtils.toTag(this));
                super.markDirty();
            }
        };

        InventoryUtils.fromTag(tag, inventory);

        for (int y = 0; y < numberOfRows; y++) {
            for (int x = 0; x < rowWidth; x++) {
                Point backpackSlotPosition = getBackpackSlotPosition(dimension, x, y);
                addSlot(new BackpackLockedSlot(inventory, y * rowWidth + x, backpackSlotPosition.x + 1, backpackSlotPosition.y + 1));
            }
        }

        for (int y = 0; y < 3; ++y) {
            for (int x = 0; x < 9; ++x) {
                Point playerInvSlotPosition = getPlayerInvSlotPosition(dimension, x, y);
                this.addSlot(new BackpackLockedSlot(playerInventory, x + y * 9 + 9, playerInvSlotPosition.x + 1, playerInvSlotPosition.y + 1));
            }
        }

        for (int x = 0; x < 9; ++x) {
            Point playerInvSlotPosition = getPlayerInvSlotPosition(dimension, x, 3);
            this.addSlot(new BackpackLockedSlot(playerInventory, x, playerInvSlotPosition.x + 1, playerInvSlotPosition.y + 1));
        }
    }

    public SpellBookItem getItem() {
        return (SpellBookItem) spellBookStack.getItem();
    }

    public Dimension getDimension() {
        SpellBookInfo tier = getItem().getTier();
        return new Dimension(padding * 2 + Math.max(tier.getRowWidth(), 9) * 18, padding * 2 + titleSpace * 2 + 8 + (tier.getNumberOfRows() + 4) * 18);
    }

    public Point getBackpackSlotPosition(Dimension dimension, int x, int y) {
        SpellBookInfo tier = getItem().getTier();
        return new Point(dimension.getWidth() / 2 - tier.getRowWidth() * 9 + x * 18, padding + titleSpace + y * 18);
    }

    public Point getPlayerInvSlotPosition(Dimension dimension, int x, int y) {
        // SpellBookInfo tier = getItem().getTier();
        return new Point(dimension.getWidth() / 2 - 9 * 9 + x * 18, dimension.getHeight() - padding - 4 * 18 - 3 + y * 18 + (y == 3 ? 4 : 0));
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return spellBookStack.getItem() instanceof SpellBookItem;
    }

    public ItemStack getspellBookStack() {
        return spellBookStack;
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int index) {
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot != null && slot.hasStack()) {
            ItemStack toInsert = slot.getStack();
            itemStack = toInsert.copy();
            SpellBookInfo tier = getItem().getTier();
            if (index < tier.getNumberOfRows() * tier.getRowWidth()) {
                if (!this.insertItem(toInsert, tier.getNumberOfRows() * tier.getRowWidth(), this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.insertItem(toInsert, 0, tier.getNumberOfRows() * tier.getRowWidth(), false)) {
                return ItemStack.EMPTY;
            }

            if (toInsert.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }
        }

        return itemStack;
    }

    private class BackpackLockedSlot extends Slot {

        public BackpackLockedSlot(Inventory inventory, int index, int x, int y) {
            super(inventory, index, x, y);
        }

        @Override
        public boolean canTakeItems(PlayerEntity playerEntity) {
            return stackMovementIsAllowed(getStack());
        }

        @Override
        public boolean canInsert(ItemStack stack) {
            // If the "unstackables only" config option is turned on,
            // do not allow players to insert stacks with >1 max count.
            if (false) { //TODO: fix
                if (stack.getMaxCount() > 1) {
                    return false;
                }
            }

            // Do not allow players to insert shulkers into backpacks.
            if (true && inventory instanceof BackpackInventory) { //TODO: fix
                Item item = stack.getItem();
                if (item instanceof BlockItem blockItem) {
                    return !(blockItem.getBlock() instanceof ShulkerBoxBlock);
                }
            }

            return stackMovementIsAllowed(stack);
        }

        private boolean stackMovementIsAllowed(ItemStack stack) {
            return !(stack.getItem() instanceof SpellBookItem) && stack != spellBookStack;
        }
    }

    public static class BackpackInventory extends SimpleInventory {

        public BackpackInventory(int slots) {
            super(slots);
        }
    }

    
}
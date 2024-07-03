package com.xylo_datapacks.energy_manipulation.screen.spell_book;

import com.xylo_datapacks.energy_manipulation.EnergyManipulation;
import com.xylo_datapacks.energy_manipulation.config.SpellBookInfo;
import com.xylo_datapacks.energy_manipulation.item.custom.SpellBookItem;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.gui.GuiManager;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.GenericNode;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.instruction.InstructionProviderNode;
import com.xylo_datapacks.energy_manipulation.api.Dimension;
import com.xylo_datapacks.energy_manipulation.api.Point;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.shape.ProjectileShapeNode;
import com.xylo_datapacks.energy_manipulation.util.InventoryUtils;
import net.minecraft.block.ShulkerBoxBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.InventoryChangedListener;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;

import java.util.function.Consumer;

public class SpellBookScreenHandler extends ScreenHandler {

    private final ItemStack spellBookStack;
    private final int padding = 8;
    private final int titleSpace = 10;
    private final int verticalOffset = 67;
    private final GuiManager guiManager;
    private BackpackInventory inventory;
    private Consumer<Boolean> sendScreenUpdateConsumer;

    public SpellBookScreenHandler(int synchronizationID, PlayerInventory playerInventory, PacketByteBuf packetByteBuf) {
        this(synchronizationID, playerInventory, packetByteBuf.readItemStack());
    }

    public SpellBookScreenHandler(int synchronizationID, PlayerInventory playerInventory, ItemStack spellBookStack) {
        super(EnergyManipulation.SPELL_BOOK_MENU_TYPE, synchronizationID);
        this.spellBookStack = spellBookStack;
        this.guiManager = new GuiManager(null);
        
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
        
        // create inventory
        inventory = new BackpackInventory(rowWidth * numberOfRows) {
            @Override
            public void markDirty() {
                spellBookStack.getOrCreateNbt().put("Inventory", InventoryUtils.toTag(this));
                super.markDirty();
            }

            @Override
            public void onInventoryChanged(Inventory sender) {
                loadPageSpell();
            }
        };
        // update inventory from nbt
        loadInventoryFromNbt(spellBookStack);

        // spell book inventory slots
        for (int y = 0; y < numberOfRows; y++) {
            for (int x = 0; x < rowWidth; x++) {
                Point backpackSlotPosition = getBackpackSlotPosition(dimension, x, y);
                addSlot(new BackpackLockedSlot(inventory, y * rowWidth + x, backpackSlotPosition.x + 1, backpackSlotPosition.y + 1));
            }
        }

        // player inventory
        for (int y = 0; y < 3; ++y) {
            for (int x = 0; x < 9; ++x) {
                Point playerInvSlotPosition = getPlayerInvSlotPosition(dimension, x, y);
                this.addSlot(new BackpackLockedSlot(playerInventory, x + y * 9 + 9, playerInvSlotPosition.x + 1, playerInvSlotPosition.y + 1));
            }
        }

        // hot bar
        for (int x = 0; x < 9; ++x) {
            Point playerInvSlotPosition = getPlayerInvSlotPosition(dimension, x, 3);
            this.addSlot(new BackpackLockedSlot(playerInventory, x, playerInvSlotPosition.x + 1, playerInvSlotPosition.y + 1));
        }
    }
    
    /**
     * has to be called after every manipulation from handledScreen
     */
    public void updatePageSpell() {
        System.out.println("try update");
        // get page itemStack
        ItemStack page = inventory.getStack(0);
        if (!page.isEmpty() && page.getItem() instanceof Item) {
            // TODO: check if page is instance of SpellBookPageItem and if so run method to save spell in nbt
            System.out.println(guiManager.toNbt());
        }
    }

    /**
     * has to be called by constructor and after every inventory change
     */
    private void loadPageSpell() {
        System.out.println("try load");
        // get page itemStack
        ItemStack page = inventory.getStack(0);
        if (!page.isEmpty() && page.getItem() instanceof Item) {
            // TODO: check if page is instance of SpellBookPageItem and if so run method to get spell from nbt and use it fo init guiManager
            this.guiManager.setRootNode(new InstructionProviderNode());
        } 
        else {
            this.guiManager.reset();   
        }
        // update screen
        sendScreenUpdate();
    }

    /*----------------------------------------------------------------------------------------------------------------*/
    /* Helper methods */
    
    public ItemStack getSpellBookStack() {
        return spellBookStack;
    }
    
    public SpellBookItem getItem() {
        return (SpellBookItem) spellBookStack.getItem();
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return spellBookStack.getItem() instanceof SpellBookItem;
    }

    private void loadInventoryFromNbt(ItemStack spellBookStack) {
        // get spell book inventory in nbt form
        NbtList tag = spellBookStack.getOrCreateNbt().getList("Inventory", NbtElement.COMPOUND_TYPE);
        // fill inventory from nbt
        InventoryUtils.fromTag(tag, inventory);
    }
    
    
    public GuiManager getGuiManager() { return guiManager; }
    
    /**
     * call in screen constructor
     */
    public void registerSetScreenUpdate(Consumer<Boolean> sendScreenUpdateConsumer) { 
        this.sendScreenUpdateConsumer = sendScreenUpdateConsumer; 
    }
    
    private void sendScreenUpdate() {
        if (sendScreenUpdateConsumer != null) {
            this.sendScreenUpdateConsumer.accept(true);
        }
    }
    
    /*----------------------------------------------------------------------------------------------------------------*/
    
    
    /*----------------------------------------------------------------------------------------------------------------*/
    /* Slot positioning */
    
    public Dimension getDimension() {
        SpellBookInfo tier = getItem().getTier();
        return new Dimension(padding * 2 + Math.max(tier.getRowWidth(), 9) * 18, padding * 2 + titleSpace * 2 + 8 + (tier.getNumberOfRows() + 4) * 18);
    }

    public Point getBackpackSlotPosition(Dimension dimension, int x, int y) {
        SpellBookInfo tier = getItem().getTier();
        return new Point(dimension.getWidth() / 2 - tier.getRowWidth() * 9 + x * 18, verticalOffset + padding + titleSpace + y * 18);
    }

    public Point getPlayerInvSlotPosition(Dimension dimension, int x, int y) {
        // SpellBookInfo tier = getItem().getTier();
        return new Point(dimension.getWidth() / 2 - 9 * 9 + x * 18, verticalOffset + dimension.getHeight() - padding - 4 * 18 - 3 + y * 18 + (y == 3 ? 4 : 0));
    }

    /*----------------------------------------------------------------------------------------------------------------*/

    /*----------------------------------------------------------------------------------------------------------------*/
    /* item moving */

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
    
    /*----------------------------------------------------------------------------------------------------------------*/

    
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /* Support classes */
    
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

    public static class BackpackInventory extends SimpleInventory implements InventoryChangedListener {

        public BackpackInventory(int slots) {
            super(slots);
            addListener(this);
        }

        @Override
        public void onInventoryChanged(Inventory sender) {
            
        }
    }

    
}
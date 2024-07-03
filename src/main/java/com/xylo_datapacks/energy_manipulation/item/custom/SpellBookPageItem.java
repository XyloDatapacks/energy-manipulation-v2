package com.xylo_datapacks.energy_manipulation.item.custom;

import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.GenericNode;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.instruction.InstructionProviderNode;
import net.fabricmc.fabric.api.item.v1.FabricItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;

public class SpellBookPageItem extends Item implements FabricItem {
    private static final String SPELL_KEY = "spell";
    
    public SpellBookPageItem(Settings settings) {
        super(settings);
    }

    
    public static void setSpell(ItemStack stack, NbtCompound spell) {
        System.out.println(spell);
        NbtCompound nbtCompound = stack.getOrCreateNbt();
        System.out.println(nbtCompound);
        nbtCompound.put(SPELL_KEY, spell);
        System.out.println(nbtCompound);
    }

    public static GenericNode getSpell(ItemStack stack) {
        NbtCompound nbtCompound = stack.getNbt();
        if (nbtCompound == null) {
            return new InstructionProviderNode();
        }
        return GenericNode.generateFromNbt(nbtCompound.getCompound(SPELL_KEY));
    }
}

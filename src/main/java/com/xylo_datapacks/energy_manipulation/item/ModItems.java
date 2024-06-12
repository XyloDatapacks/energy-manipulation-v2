package com.xylo_datapacks.energy_manipulation.item;

import com.xylo_datapacks.energy_manipulation.EnergyManipulation;
import com.xylo_datapacks.energy_manipulation.item.custom.SpellBookItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {

    public static final Item SPELLBOOK = registerItem("spell_book", new SpellBookItem(new FabricItemSettings().maxDamage(100)));
    
    
    // Function called to add items
    public static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(EnergyManipulation.MOD_ID, name), item);
    }

    // Ingredient Group
    private static void addItemsToIngredientItemGroup(FabricItemGroupEntries entries) {
    }

    // Register items in different item groups
    public static void registerModItems()
    {
        EnergyManipulation.LOGGER.info("Registering Mod Items for " + EnergyManipulation.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems::addItemsToIngredientItemGroup);
    }
}

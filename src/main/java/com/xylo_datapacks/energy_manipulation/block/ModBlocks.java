package com.xylo_datapacks.energy_manipulation.block;

import com.xylo_datapacks.energy_manipulation.EnergyManipulation;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlocks {



    // Function called to add the block
    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(EnergyManipulation.MOD_ID, name), block);
    }

    // Function called to add the item associated to the block
    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(Registries.ITEM, new Identifier(EnergyManipulation.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings()));
    }

    // Register blocks in different item groups
    public static void registerModBlocks() {
        EnergyManipulation.LOGGER.info("Registering Mod Blocks for" + EnergyManipulation.MOD_ID);
    }
}

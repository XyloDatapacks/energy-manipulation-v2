package com.xylo_datapacks.energy_manipulation;

import com.xylo_datapacks.energy_manipulation.util.ModModelPredicateProvider;
import net.fabricmc.api.ClientModInitializer;

import static com.xylo_datapacks.energy_manipulation.event.KeyInputHandler.registerKeyBindings;

public class EnergyManipulationClient implements ClientModInitializer {
    
    @Override
    public void onInitializeClient() {
        ModModelPredicateProvider.registerModModels();
        registerKeyBindings();
    }
}

package com.xylo_datapacks.energy_manipulation;

import com.xylo_datapacks.energy_manipulation.networking.ModPackets;
import com.xylo_datapacks.energy_manipulation.screen.spell_book.SpellBookHandledScreen;
import com.xylo_datapacks.energy_manipulation.util.ModModelPredicateProvider;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.screen.ingame.HandledScreens;

import static com.xylo_datapacks.energy_manipulation.event.KeyInputHandler.registerKeyBindings;

public class EnergyManipulationClient implements ClientModInitializer {
    
    @Override
    public void onInitializeClient() {
        ModModelPredicateProvider.registerModModels();
        registerKeyBindings();

        // Register Server RPCs (so client can receive server rpcs)
        ModPackets.registerS2CPackets();
        HandledScreens.register(EnergyManipulation.SPELL_BOOK_MENU_TYPE, SpellBookHandledScreen::new);
    }
}

package com.xylo_datapacks.energy_manipulation;

import com.xylo_datapacks.energy_manipulation.entity.ModEntities;
import com.xylo_datapacks.energy_manipulation.entity.client.*;
import com.xylo_datapacks.energy_manipulation.networking.ModPackets;
import com.xylo_datapacks.energy_manipulation.screen.spell_book.SpellBookHandledScreen;
import com.xylo_datapacks.energy_manipulation.util.ModModelPredicateProvider;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.entity.DisplayEntityRenderer;

import static com.xylo_datapacks.energy_manipulation.event.KeyInputHandler.registerKeyBindings;

public class EnergyManipulationClient implements ClientModInitializer {
    
    @Override
    public void onInitializeClient() {
        ModModelPredicateProvider.registerModModels();
        registerKeyBindings();

        // Register Server RPCs (so client can receive server rpcs)
        ModPackets.registerS2CPackets();
        HandledScreens.register(EnergyManipulation.SPELL_BOOK_MENU_TYPE, SpellBookHandledScreen::new);
        
        /*------------------------------------------------------------------------------------ -----------------------*/
        /* register entity models */
        
        // spell
        EntityRendererRegistry.register(ModEntities.SPELL, SpellEntityRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.SPELL, SpellEntityModel::getTexturedModelData);
        // projectile shape
        EntityRendererRegistry.register(ModEntities.PROJECTILE_SHAPE, ProjectileShapeEntityRenderer::new);

        /*------------------------------------------------------------------------------------ -----------------------*/
    }
}

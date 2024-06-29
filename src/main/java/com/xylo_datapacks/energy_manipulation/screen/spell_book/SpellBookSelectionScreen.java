package com.xylo_datapacks.energy_manipulation.screen.spell_book;

import com.xylo_datapacks.energy_manipulation.EnergyManipulation;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class SpellBookSelectionScreen extends HandledScreen<SpellBookSelectionScreenHandler> {
    private static final Identifier TEXTURE = new Identifier(EnergyManipulation.MOD_ID, "textures/gui/container/generic_54.png");
    
    public SpellBookSelectionScreen(SpellBookSelectionScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
    protected void init() {
        super.init();
        
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        
    }
}

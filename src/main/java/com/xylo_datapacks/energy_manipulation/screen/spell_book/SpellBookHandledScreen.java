package com.xylo_datapacks.energy_manipulation.screen.spell_book;

import com.xylo_datapacks.energy_manipulation.EnergyManipulation;
import com.xylo_datapacks.energy_manipulation.screen.Dimension;
import io.wispforest.owo.ui.base.BaseUIModelHandledScreen;
import io.wispforest.owo.ui.base.BaseUIModelScreen;
import io.wispforest.owo.ui.component.Components;
import io.wispforest.owo.ui.container.FlowLayout;
import io.wispforest.owo.ui.core.HorizontalAlignment;
import io.wispforest.owo.ui.core.Sizing;
import io.wispforest.owo.ui.core.Surface;
import io.wispforest.owo.ui.core.VerticalAlignment;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.slot.Slot;
import net.minecraft.text.Text;
import net.minecraft.util.Colors;
import net.minecraft.util.Identifier;

public class SpellBookHandledScreen extends BaseUIModelHandledScreen<FlowLayout, SpellBookScreenHandler> {

    private static final Identifier GUI_TEXTURE = new Identifier("inmis", "textures/gui/backpack_container.png");
    private static final Identifier SLOT_TEXTURE = new Identifier("inmis", "textures/gui/backpack_slot.png");

    private final int guiTitleColor = Colors.GRAY;
    
    public SpellBookHandledScreen(SpellBookScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title, FlowLayout.class, BaseUIModelScreen.DataSource.asset(Identifier.of(EnergyManipulation.MOD_ID, "spell_book/spell_book_menu")));

        Dimension dimension = handler.getDimension();
        this.backgroundWidth = dimension.getWidth();
        this.backgroundHeight = dimension.getHeight();
        //this.titleY = 7;
        //this.playerInventoryTitleX = handler.getPlayerInvSlotPosition(dimension, 0, 0).x;
        //this.playerInventoryTitleY = this.backgroundHeight - 94;
    }

    /**
     * Build the component hierarchy of this screen,
     * called after the adapter and root component have been
     * initialized by {@link #createAdapter()}
     *
     * @param rootComponent The root component created
     *                      in the previous initialization step
     */
    @Override
    protected void build(FlowLayout rootComponent) {
        
        // add nodes buttons
        FlowLayout flowLayout = rootComponent.childById(FlowLayout.class, "nodes_list");
        if (flowLayout != null) {
            for (int i = 0; i < 10; i++) {
                int finalI = i;
                flowLayout.child(Components
                        .button(Text.literal("A Button " + i), button -> {
                            System.out.println("click: " + finalI);
                        })
                        .horizontalSizing(Sizing.fill(100)));
            }
        }

    }
}

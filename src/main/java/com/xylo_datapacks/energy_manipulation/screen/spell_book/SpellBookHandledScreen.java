package com.xylo_datapacks.energy_manipulation.screen.spell_book;

import com.xylo_datapacks.energy_manipulation.EnergyManipulation;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.gui.GuiManager;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.records.NodeResult;
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

import java.util.List;

import static com.ibm.icu.text.PluralRules.Operand.i;

public class SpellBookHandledScreen extends BaseUIModelHandledScreen<FlowLayout, SpellBookScreenHandler> {

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
            List<NodeResult> nodeResults = this.handler.getGuiManager().rootNode.getAllSubNodesRecursive();
            for (int i = 0; i < nodeResults.size(); i++) {
                NodeResult nodeResult = nodeResults.get(i);
                GuiManager.ButtonDisplay buttonDisplay = GuiManager.getButtonDisplay(nodeResult);
                GuiManager.EditorInfo editorHeader = GuiManager.getEditorHeader(nodeResult);
                GuiManager.EditorInfo EditorCurrentSelection = GuiManager.getEditorCurrentSelection(nodeResult);
                
                flowLayout.child(Components
                        .button(Text.literal(buttonDisplay.subNodeName() + ": " + buttonDisplay.nodeName()), button -> {
                            System.out.println("click: " + "[" + editorHeader.name() + ": " + editorHeader.description() + " ; " + EditorCurrentSelection.name() + ": " + EditorCurrentSelection.description() + "]" );
                        })
                        .horizontalSizing(Sizing.fill(100)));
            }
        }
    }
    
    
}

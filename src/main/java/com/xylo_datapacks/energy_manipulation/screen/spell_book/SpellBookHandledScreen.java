package com.xylo_datapacks.energy_manipulation.screen.spell_book;

import com.xylo_datapacks.energy_manipulation.EnergyManipulation;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.gui.GuiManager;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.GenericNode;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.records.NodeResult;
import com.xylo_datapacks.energy_manipulation.api.Dimension;
import io.wispforest.owo.ui.base.BaseUIModelHandledScreen;
import io.wispforest.owo.ui.base.BaseUIModelScreen;
import io.wispforest.owo.ui.component.ButtonComponent;
import io.wispforest.owo.ui.component.Components;
import io.wispforest.owo.ui.component.LabelComponent;
import io.wispforest.owo.ui.container.FlowLayout;
import io.wispforest.owo.ui.container.ScrollContainer;
import io.wispforest.owo.ui.core.*;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.List;


public class SpellBookHandledScreen extends BaseUIModelHandledScreen<FlowLayout, SpellBookScreenHandler> {
    
    private String selectedNodePath;
    
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
        refreshNodesList(rootComponent);
    }

    public void refreshNodesList(FlowLayout rootComponent) {
        ScrollContainer<?> scrollContainer = rootComponent.childById(ScrollContainer.class, "nodes_list_scroll");
        if (scrollContainer == null) return;
        
        FlowLayout flowLayout = scrollContainer.childById(FlowLayout.class, "nodes_list");
        if (flowLayout == null) return;

        // needed to calculate the scroll percentage
        double flowLayoutHeightPreUpdate = flowLayout.fullSize().height();
        
        // reset and refresh list
        flowLayout.clearChildren();
        List<NodeResult> nodeResults = this.handler.getGuiManager().getAllSubNodesRecursive();
        for (NodeResult nodeResult : nodeResults) {
            // display data
            GuiManager.ButtonDisplay buttonDisplay = GuiManager.getButtonDisplay(nodeResult);
            String nodePath = GenericNode.listPathToStringPath(nodeResult.path().list()); //TODO: use map path if i ever go back to giving NodeResults in map
            
            flowLayout.child(Components
                    .button(Text.literal(buttonDisplay.subNodeName() + ": " + buttonDisplay.nodeName()), button -> {
                        // on click set this node path at the selected one and load info panel
                        selectedNodePath = nodePath;
                        refreshNodeInfo(rootComponent, nodeResult);
                    })
                    .id(nodePath)
                    .horizontalSizing(Sizing.fill(100)));

            // if the node is selected then update the info 
            if (selectedNodePath != null && !selectedNodePath.isEmpty()) {
                if (selectedNodePath.equals(nodePath)) {
                    refreshNodeInfo(rootComponent, nodeResult);
                }
            }
        }

        // calculate scroll percentage
        double maxScrollHeight = flowLayoutHeightPreUpdate - scrollContainer.height(); // container height minus the exposed area
        double newMaxScrollHeight = flowLayout.fullSize().height() - scrollContainer.height(); // new container height minus the exposed area
        double maxLayoutY = scrollContainer.y() - maxScrollHeight; // Y pos of the layout when scrolled down at 100%
        double distance = Math.abs(flowLayout.y() - maxLayoutY); // goes from maxScrollHeight at 0% to 0 at 100%
        double progressPercent = newMaxScrollHeight > 0.0 ? (Math.min((maxScrollHeight - distance) / newMaxScrollHeight, 1.0)) : 0.0; // clamp [Math.min((maxScrollHeight - distance) / newMaxScrollHeight]
        // restore scroll percentage
        scrollContainer.scrollTo(progressPercent);
    }
    
    public void refreshNodeInfo(FlowLayout rootComponent, NodeResult nodeResult) {

        // display data
        GuiManager.EditorInfo editorHeader = GuiManager.getEditorHeader(nodeResult);
        GuiManager.EditorInfo editorCurrentSelection = GuiManager.getEditorCurrentSelection(nodeResult);

        // update Header
        rootComponent.childById(LabelComponent.class, "node_info_name_label").text(Text.of(editorHeader.name()));
        rootComponent.childById(LabelComponent.class, "node_info_description_label").text(Text.of(editorHeader.description()));
        
        // update current selection
        rootComponent.childById(LabelComponent.class, "node_info_class_name_label").text(Text.of(editorCurrentSelection.name()));
        rootComponent.childById(LabelComponent.class, "node_info_class_description_label").text(Text.of(editorCurrentSelection.description()));
        
        
        // set prev button functionality
        ButtonComponent buttonPrev = rootComponent.childById(ButtonComponent.class, "node_info_class_button_prev");
        if (buttonPrev != null) {
            buttonPrev.onPress(buttonComponent -> {
                GuiManager.setPreviousNodeClass(nodeResult);
                refreshNodesList(rootComponent);
            });
        }

        // set next button functionality
        ButtonComponent buttonNext = rootComponent.childById(ButtonComponent.class, "node_info_class_button_next");
        if (buttonNext != null) {
            buttonNext.onPress(buttonComponent -> {
                GuiManager.setNextNodeClass(nodeResult);
                refreshNodesList(rootComponent);
            });
        }
    }
    
}

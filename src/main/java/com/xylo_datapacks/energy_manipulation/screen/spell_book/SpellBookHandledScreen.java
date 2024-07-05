package com.xylo_datapacks.energy_manipulation.screen.spell_book;

import com.xylo_datapacks.energy_manipulation.EnergyManipulation;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.gui.GuiManager;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.AbstractNodeWithList;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.GenericNode;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.records.NodeResult;
import com.xylo_datapacks.energy_manipulation.api.Dimension;
import com.xylo_datapacks.energy_manipulation.screen.custom_owo.CollapsibleContainerV2;
import com.xylo_datapacks.energy_manipulation.screen.custom_owo.XyloOwoContainers;
import io.wispforest.owo.ui.base.BaseUIModelHandledScreen;
import io.wispforest.owo.ui.base.BaseUIModelScreen;
import io.wispforest.owo.ui.component.ButtonComponent;
import io.wispforest.owo.ui.component.Components;
import io.wispforest.owo.ui.component.LabelComponent;
import io.wispforest.owo.ui.container.CollapsibleContainer;
import io.wispforest.owo.ui.container.Containers;
import io.wispforest.owo.ui.container.FlowLayout;
import io.wispforest.owo.ui.container.ScrollContainer;
import io.wispforest.owo.ui.core.*;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Environment(value= EnvType.CLIENT)
public class SpellBookHandledScreen extends BaseUIModelHandledScreen<FlowLayout, SpellBookScreenHandler> {
    private FlowLayout rootComponent;
    
    
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
        this.rootComponent = rootComponent;
        
        refreshNodesList();
        this.handler.registerNodeListUpdate(bUpdate -> {
            refreshNodesList();
        });
    }
    
    public void refreshNodesList() {
        ScrollContainer<?> scrollContainer = rootComponent.childById(ScrollContainer.class, "nodes_list_scroll");
        if (scrollContainer == null) return;
        
        FlowLayout flowLayout = scrollContainer.childById(FlowLayout.class, "nodes_list_scroll_content");
        if (flowLayout == null) return;

        // needed to calculate the scroll percentage
        double flowLayoutHeightPreUpdate = flowLayout.fullSize().height();
        
        // reset and refresh list
        flowLayout.clearChildren();
        Map<String, NodeResult> nodeResults = this.handler.getGuiManager().getAllNodes();
        addButton(flowLayout, nodeResults);
        // refresh node info panel
        refreshNodeInfo();

        // calculate scroll percentage
        double maxScrollHeight = flowLayoutHeightPreUpdate - scrollContainer.height(); // container height minus the exposed area
        double newMaxScrollHeight = flowLayout.fullSize().height() - scrollContainer.height(); // new container height minus the exposed area
        double maxLayoutY = scrollContainer.y() - maxScrollHeight; // Y pos of the layout when scrolled down at 100%
        double distance = Math.abs(flowLayout.y() - maxLayoutY); // goes from maxScrollHeight at 0% to 0 at 100%
        double progressPercent = newMaxScrollHeight > 0.0 ? Math.max((Math.min((maxScrollHeight - distance) / newMaxScrollHeight, 1.0)), 0.0) : 0.0; // clamp [(maxScrollHeight - distance) / newMaxScrollHeight]
        // restore scroll percentage
        scrollContainer.scrollTo(progressPercent);
    }

    private void addButton(FlowLayout flowLayout, Map<String, NodeResult> nodeResults) {
        int nodeIndex = 0;
        for (Map.Entry<String, NodeResult> entry : nodeResults.entrySet()) {
            // find parent container
            List<String> parentNodeListPath = new ArrayList<>(entry.getValue().path().list());
            parentNodeListPath.remove(parentNodeListPath.size() - 1);
            String parentNodeStringPath = GenericNode.listPathToStringPath(parentNodeListPath);
            CollapsibleContainerV2 parentContainer = flowLayout.childById(CollapsibleContainerV2.class, parentNodeStringPath);
            // add button
            if (parentContainer != null) {
                parentContainer.child(generateButton(entry.getKey(), entry.getValue(), nodeIndex));
            }
            else {
                flowLayout.child(generateButton(entry.getKey(), entry.getValue(), nodeIndex));
            }
            nodeIndex++;
        }
    }

    private Component generateButton(String nodePath, NodeResult nodeResult, int nodeIndex) {
        // display data for button
        GuiManager.ButtonDisplay buttonDisplay = GuiManager.getButtonDisplay(nodeResult);
        
        // create layout to contain button
        CollapsibleContainerV2 buttonLayout = (CollapsibleContainerV2) XyloOwoContainers.collapsibleV2(Sizing.fill(100), Sizing.content(0), Text.of(buttonDisplay.subNodeName()), true)
                .padding(Insets.left(2))
                .surface(Surface.DARK_PANEL)
                .id(nodePath);
        
        // create button
        ButtonComponent buttonComponent = (ButtonComponent) Components.button(
                Text.literal(buttonDisplay.subNodeName() + ": " + buttonDisplay.nodeName()), button -> {
                    if (((SpellBookScreenHandler) this.handler).onButtonClick(this.client.player, nodeIndex + SpellBookScreenHandler.BUTTON_CATEGORY.NODE_SELECT_BUTTON.getOffset())) {
                        this.client.interactionManager.clickButton(((SpellBookScreenHandler) this.handler).syncId, nodeIndex + SpellBookScreenHandler.BUTTON_CATEGORY.NODE_SELECT_BUTTON.getOffset());
                    }
                })
                .id(nodePath)
                .horizontalSizing(Sizing.fill(100));
        
        // add button component to layout
        buttonLayout.child(buttonComponent);
        
        // add "+" button for list nodes
        if (nodeResult.node() instanceof AbstractNodeWithList<?>) {
            buttonComponent.horizontalSizing(Sizing.fill(80));

            ButtonComponent plusButton = (ButtonComponent) Components.button(
                            Text.literal("+"), button -> {
                                if (((SpellBookScreenHandler) this.handler).onButtonClick(this.client.player, nodeIndex + SpellBookScreenHandler.BUTTON_CATEGORY.ADD_ELEMENT_TO_LIST_BUTTON.getOffset())) {
                                    this.client.interactionManager.clickButton(((SpellBookScreenHandler) this.handler).syncId, nodeIndex + SpellBookScreenHandler.BUTTON_CATEGORY.ADD_ELEMENT_TO_LIST_BUTTON.getOffset());
                                }
                            })
                    .id(nodePath)
                    .horizontalSizing(Sizing.fill(20));

            buttonLayout.child(plusButton);
        }

        // add "remove" button for children of list nodes
        if (nodeResult.node().getParentNode() instanceof AbstractNodeWithList<?>) {
            buttonComponent.horizontalSizing(Sizing.fill(80));

            ButtonComponent removeButton = (ButtonComponent) Components.button(
                            Text.literal("-"), button -> {
                                if (((SpellBookScreenHandler) this.handler).onButtonClick(this.client.player, nodeIndex + SpellBookScreenHandler.BUTTON_CATEGORY.REMOVE_NODE_FROM_LIST_BUTTON.getOffset())) {
                                    this.client.interactionManager.clickButton(((SpellBookScreenHandler) this.handler).syncId, nodeIndex + SpellBookScreenHandler.BUTTON_CATEGORY.REMOVE_NODE_FROM_LIST_BUTTON.getOffset());
                                }
                            })
                    .id(nodePath)
                    .horizontalSizing(Sizing.fill(20));

            buttonLayout.child(removeButton);
        }
        
        return buttonLayout;
    }


    public void refreshNodeInfo() {
        FlowLayout flowLayout = rootComponent.childById(FlowLayout.class, "node_info_scroll_content");
        if (flowLayout == null) return;
        
        NodeResult nodeResult = this.handler.getGuiManager().getSelectedNode();
        // reset children if needed
        if (nodeResult == null) {
            flowLayout.clearChildren();
            return;
        };
        
        // add child (second page)
        if (flowLayout.children().isEmpty()) {
            final var second_page= this.model.expandTemplate(FlowLayout.class, "second_page", Map.of());
            flowLayout.child(second_page);
        }
        
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
                if (((SpellBookScreenHandler)this.handler).onButtonClick(this.client.player, -1)) {
                    this.client.interactionManager.clickButton(((SpellBookScreenHandler) this.handler).syncId, -1);
                }
            });
        }

        // set next button functionality
        ButtonComponent buttonNext = rootComponent.childById(ButtonComponent.class, "node_info_class_button_next");
        if (buttonNext != null) {
            buttonNext.onPress(buttonComponent -> {
                if (((SpellBookScreenHandler)this.handler).onButtonClick(this.client.player, -2)) {
                    this.client.interactionManager.clickButton(((SpellBookScreenHandler) this.handler).syncId, -2);
                }
            });
        }
    }
    
    
    
}

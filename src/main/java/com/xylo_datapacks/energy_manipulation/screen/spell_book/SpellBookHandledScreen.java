package com.xylo_datapacks.energy_manipulation.screen.spell_book;

import com.ibm.icu.impl.number.parse.PaddingMatcher;
import com.xylo_datapacks.energy_manipulation.EnergyManipulation;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.gui.GuiManager;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.Nodes;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.records.NodeResult;
import com.xylo_datapacks.energy_manipulation.screen.Dimension;
import io.wispforest.owo.ui.base.BaseUIModelHandledScreen;
import io.wispforest.owo.ui.base.BaseUIModelScreen;
import io.wispforest.owo.ui.component.Components;
import io.wispforest.owo.ui.container.Containers;
import io.wispforest.owo.ui.container.FlowLayout;
import io.wispforest.owo.ui.core.*;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.slot.Slot;
import net.minecraft.text.Text;
import net.minecraft.util.Colors;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;

import java.util.List;


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
        refreshNodesList(rootComponent);
    }

    @Override
    public void render(DrawContext vanillaContext, int mouseX, int mouseY, float delta) {
        super.render(vanillaContext, mouseX, mouseY, delta);
        
        
    }

    public void refreshNodesList(FlowLayout rootComponent) {
        FlowLayout flowLayout = rootComponent.childById(FlowLayout.class, "nodes_list");
        if (flowLayout != null) {
            flowLayout.clearChildren();
            List<NodeResult> nodeResults = this.handler.getGuiManager().getAllSubNodesRecursive();
            for (int i = 0; i < nodeResults.size(); i++) {
                NodeResult nodeResult = nodeResults.get(i);
                GuiManager.ButtonDisplay buttonDisplay = GuiManager.getButtonDisplay(nodeResult);
                GuiManager.EditorInfo editorHeader = GuiManager.getEditorHeader(nodeResult);
                GuiManager.EditorInfo EditorCurrentSelection = GuiManager.getEditorCurrentSelection(nodeResult);

                flowLayout.child(Components
                        .button(Text.literal(buttonDisplay.subNodeName() + ": " + buttonDisplay.nodeName()), button -> {
                            System.out.println("click: " + "[" + editorHeader.name() + ": " + editorHeader.description() + " ; " + EditorCurrentSelection.name() + ": " + EditorCurrentSelection.description() + "]" );
                            refreshNodeInfo(rootComponent, nodeResult);
                            
                            //this.handler.getGuiManager().modifyNodeClass(this.handler.getGuiManager().rootNode.getNodeResultFromPath("instruction[1].position"), Nodes.POSITION_OFFSET.identifier());
                            //refreshNodesList(rootComponent);
                        })
                        .horizontalSizing(Sizing.fill(100)));
            }
        }
    }
    
    public void refreshNodeInfo(FlowLayout rootComponent, NodeResult nodeResult) {
        FlowLayout flowLayout = rootComponent.childById(FlowLayout.class, "node_info");
        if (flowLayout != null) {
            GuiManager.EditorInfo editorHeader = GuiManager.getEditorHeader(nodeResult);
            GuiManager.EditorInfo editorCurrentSelection = GuiManager.getEditorCurrentSelection(nodeResult);
            
            flowLayout.clearChildren();

            NodeDescription(flowLayout, editorHeader, 50);
            NodeDescription(flowLayout, editorCurrentSelection, 50);


        }
    }

    private static void NodeDescription(FlowLayout flowLayout, GuiManager.EditorInfo editorInfo, int size) {
        flowLayout.child(Containers.verticalFlow(Sizing.fill(100), Sizing.fill(size))
                .child(Containers.verticalFlow(Sizing.content(0), Sizing.content(0))
                        .child(Components.label(Text.of(editorInfo.name()))
                                .sizing(Sizing.content(0), Sizing.content(0))
                                .horizontalSizing(Sizing.fill(100)))
                        .margins(Insets.bottom(2))
                        .padding(Insets.both(8,6))
                        .alignment(HorizontalAlignment.CENTER,VerticalAlignment.CENTER)
                        .surface(Surface.PANEL))
                .child(Containers.verticalFlow(Sizing.fill(100), Sizing.fill(60))
                        .child(Containers.verticalScroll(Sizing.fill(100), Sizing.fill(100),
                                Components.label(Text.of(editorInfo.description()))
                                        .sizing(Sizing.content(0), Sizing.content(0))
                                        .horizontalSizing(Sizing.fill(100))))
                        .padding(Insets.both(8,6))
                        .alignment(HorizontalAlignment.CENTER,VerticalAlignment.TOP)
                        .surface(Surface.PANEL))
                .alignment(HorizontalAlignment.CENTER,VerticalAlignment.CENTER)
                .padding(Insets.both(8,8))
                .surface(Surface.DARK_PANEL));
    }


}

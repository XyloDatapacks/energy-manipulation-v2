package com.xylo_datapacks.energy_manipulation.item.custom.spell_book.gui;

import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.gui.value_selector.ValueSelector;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.AbstractNodeWithValue;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.GenericNode;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.ValueTypeNode;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.records.NodeData;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.records.NodeResult;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.records.SubNodeData;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class GuiManager {
    private GenericNode rootNode;

    public GuiManager(GenericNode rootNode) {
        this.rootNode = rootNode;
    }

    public GenericNode getRootNode()
    {
        return rootNode;
    }

    public void reset() {
        rootNode = null;
    }
    
    public void setRootNode(GenericNode rootNode)
    {
        this.rootNode = rootNode;
    }
    
    public Map<String, NodeResult> getAllSubNodesRecursive() {
        if (rootNode != null) {
            return rootNode.getAllSubNodesRecursive();
        }
        return Map.of();
    }
    
    public NbtCompound toNbt() {
        if (rootNode != null) {
            return rootNode.toNbt();
        }
        return new NbtCompound();
    }
    

    /*----------------------------------------------------------------------------------------------------------------*/
    /* Display */
    
    public static ButtonDisplay getButtonDisplay(NodeResult nodeResult) {
        GenericNode node = nodeResult.node();
        GenericNode parentNode = node.getParentNode();
        String id = nodeResult.path().id();

        SubNodeData subNodeData = parentNode.getSubNodeData(id);
        NodeData<? extends GenericNode> nodeData = node.getNodeData();
        
        if (nodeData != null) {
            String nodeName = node instanceof ValueTypeNode<?> nodeValue ? nodeValue.getValueDisplay() : nodeData.name();
            
            if (subNodeData != null) {
                return new ButtonDisplay(subNodeData.name(), nodeName);
            }
            return new ButtonDisplay("null", nodeName);
        }
        return new ButtonDisplay("error", "error");
    }
    
    public static EditorInfo getEditorHeader(NodeResult nodeResult) {
        GenericNode parentNode = nodeResult.node().getParentNode();
        String id = nodeResult.path().id();

        SubNodeData subNodeData = parentNode.getSubNodeData(id);

        if (subNodeData != null) {
            return new EditorInfo(subNodeData.name(), subNodeData.description());
        }
        return new EditorInfo("error", "error");
    }

    public static EditorInfo getEditorCurrentSelection(NodeResult nodeResult) {
        GenericNode node = nodeResult.node();

        NodeData<? extends GenericNode> nodeData = node.getNodeData();

        if (nodeData != null) {
            return new EditorInfo(nodeData.name(), nodeData.description());
        }
        return new EditorInfo("error", "error");
    }

    /*----------------------------------------------------------------------------------------------------------------*/

    /*----------------------------------------------------------------------------------------------------------------*/
    /* Class Management */
    
    public static void setNextNodeClass(NodeResult nodeResult) {
        GenericNode parentNode = nodeResult.node().getParentNode();
        String path = nodeResult.path().list().get(nodeResult.path().list().size() - 1);
        parentNode.getSubNode(path).setNextNodeClass();
    }

    public static void setPreviousNodeClass(NodeResult nodeResult) {
        GenericNode parentNode = nodeResult.node().getParentNode();
        String path = nodeResult.path().list().get(nodeResult.path().list().size() - 1);
        parentNode.getSubNode(path).setPreviousNodeClass();
    }

    /*----------------------------------------------------------------------------------------------------------------*/
    
    





    public record ButtonDisplay(String subNodeName, String nodeName) {}
    public record EditorInfo(String name, String description) {}
}

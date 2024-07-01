package com.xylo_datapacks.energy_manipulation.item.custom.spell_book.gui;

import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.gui.value_selector.ValueSelector;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.AbstractNodeWithValue;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.GenericNode;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.ValueTypeNode;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.records.NodeData;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.records.NodeResult;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.records.SubNodeData;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.function.Supplier;

public class GuiManager {
    public final GenericNode rootNode;

    public GuiManager(GenericNode rootNode) {
        this.rootNode = rootNode;
    }

    public void refreshGui() {
        printAll(rootNode.getAllSubNodesRecursive());
    }

    public List<NodeResult> getAllSubNodesRecursive() {
        return rootNode.getAllSubNodesRecursive();
    }

    public static List<Identifier> getEditorClassOptions(NodeResult nodeResult) {
        GenericNode parentNode = nodeResult.node().getParentNode();
        String path = nodeResult.path().list().get(nodeResult.path().list().size() - 1);

        return parentNode.getSubNode(path).getPossibleNodeClasses().keySet().stream().toList();
    }
    
    public boolean modifyNodeClass(NodeResult nodeResult, Identifier newNodeClassIdentifier) {
        GenericNode parentNode = nodeResult.node().getParentNode();
        String path = nodeResult.path().list().get(nodeResult.path().list().size() - 1);
        
        if (parentNode.getSubNode(path).setNodeClass(newNodeClassIdentifier)) {
            refreshGui();
            return true;
        }
        return false;
    }

    public static ValueSelector<?> getEditorValueSelectionMethod(NodeResult nodeResult) {
        return nodeResult.node() instanceof ValueTypeNode<?> nodeValue ? nodeValue.getValueSelector() : null;
    }
    
    public boolean modifyNodeValue(NodeResult nodeResult, ValueSelector<?> valueSelector) {
        if (nodeResult.node() instanceof AbstractNodeWithValue<?> nodeValue) {
            if (nodeValue.setValueFromSelector(valueSelector)) {
                refreshGui();
                return true;
            }
        }
        return false;
    }

    
    /*----------------------------------------------------------------------------------------------------------------*/
    /* Printing */
    
    public static void printAll(List<NodeResult> nodes) {
        for (NodeResult nodeResult : nodes) {
            
            ButtonDisplay buttonDisplay = getButtonDisplay(nodeResult);
            EditorInfo editorHeader = getEditorHeader(nodeResult);
            EditorInfo EditorCurrentSelection = getEditorCurrentSelection(nodeResult);
            String selector = getEditorValueSelectionMethod(nodeResult) != null ? "{" + getEditorValueSelectionMethod(nodeResult).getClass().getSimpleName() + "}" : "";
            
            System.out.println(buttonDisplay.subNodeName + ": " + buttonDisplay.nodeName + " -> [" + editorHeader.name + ": " + editorHeader.description + " ; " + EditorCurrentSelection.name + ": " + EditorCurrentSelection.description + "] " + selector);
        }
    }
    
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
    
    





    public record ButtonDisplay(String subNodeName, String nodeName) {}
    public record EditorInfo(String name, String description) {}
}

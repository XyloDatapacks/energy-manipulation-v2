package com.xylo_datapacks.energy_manipulation.item.custom.spell_book.gui;

import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.AbstractNodeValue;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.GenericNode;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.records.NodeData;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.records.NodeResult;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.records.SubNodeData;
import net.minecraft.util.Identifier;
import net.minecraft.util.Pair;

import java.util.List;

public class GuiManager {

    public record ButtonDisplay(String subNodeName, String nodeName) {}
    public record EditorInfo(String name, String description) {}
    
    public static void printAll(List<NodeResult> nodes) {
        for (NodeResult nodeResult : nodes) {
            
            ButtonDisplay buttonDisplay = getButtonDisplay(nodeResult);
            EditorInfo editorHeader = getEditorHeader(nodeResult);
            EditorInfo EditorCurrentSelection = getEditorCurrentSelection(nodeResult);
            
            System.out.println(buttonDisplay.subNodeName + ": " + buttonDisplay.nodeName + " -> [" + editorHeader.name + ": " + editorHeader.description + " ; " + EditorCurrentSelection.name + ": " + EditorCurrentSelection.description + "]");
        }
    }
    
    public static ButtonDisplay getButtonDisplay(NodeResult nodeResult) {
        GenericNode node = nodeResult.node();
        GenericNode parentNode = node.getParentNode();
        String id = nodeResult.path().id();

        SubNodeData subNodeData = parentNode.getSubNodeData(id);
        NodeData<? extends GenericNode> nodeData = node.getNodeData();
        
        if (nodeData != null) {
            String nodeName = node instanceof AbstractNodeValue<?> nodeValue ? nodeValue.getValueDisplay() : nodeData.name();
            
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
    
    public static Pair<String, String> getEditorSelectionMethod(NodeResult nodeResult) {
        return null;
    }
    
    public static List<Identifier> getEditorOptionList(NodeResult nodeResult) {
        GenericNode parentNode = nodeResult.node().getParentNode();
        String id = nodeResult.path().id();

        return parentNode.getSubNode(id).getPossibleNodeClasses().keySet().stream().toList();
    }
}

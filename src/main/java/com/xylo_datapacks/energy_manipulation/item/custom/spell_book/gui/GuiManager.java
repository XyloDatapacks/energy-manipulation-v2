package com.xylo_datapacks.energy_manipulation.item.custom.spell_book.gui;

import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.AbstractNodeWithList;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.GenericNode;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.ValueTypeNode;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.records.NodeData;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.records.NodeResult;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.records.SubNodeData;
import net.minecraft.nbt.NbtCompound;

import java.util.List;
import java.util.Map;

public class GuiManager {
    private GenericNode rootNode;
    private String selectedNodePath = "";

    public GuiManager(GenericNode rootNode) {
        this.rootNode = rootNode;
    }

    public void reset() {
        rootNode = null;
        selectedNodePath = "";
    }

    /*----------------------------------------------------------------------------------------------------------------*/
    /* Set and Get */

    public void setRootNode(GenericNode rootNode)
    {
        this.rootNode = rootNode;
    }
    
    public GenericNode getRootNode()
    {
        return rootNode;
    }
    
    public Map<String, NodeResult> getAllNodes() {
        if (rootNode != null) {
            return rootNode.getAllSubNodesRecursive();
        }
        return Map.of();
    }

    public Map<String, NodeResult> getRootSubNodes() {
        if (rootNode != null) {
            return rootNode.getAllSubNodes();
        }
        return Map.of();
    }

    public Map<String, NodeResult> getSubNodes(NodeResult nodeResult) {
        if (nodeResult != null) {
            return nodeResult.node().getAllSubNodes();
        }
        return Map.of();
    }
    
    public NodeResult getNodeAtPath(String path) {
        return rootNode.getNodeResultFromPath(path);
    }
    
    public String getPathAtIndex(int index) {
        Map<String, NodeResult> nodeResultMap = getAllNodes();
        List<String> keyList = nodeResultMap.keySet().stream().toList();
        return index < keyList.size() ? keyList.get(index) : null;
    }

    /*----------------------------------------------------------------------------------------------------------------*/
    

    /*----------------------------------------------------------------------------------------------------------------*/
    /* Selected Node */
    
    public String getSelectedNodePath() {
        return selectedNodePath;
    }
    
    public boolean selectNode(String path) {
        if (getAllNodes().containsKey(path)) {
            selectedNodePath = path;
            return true;
        }
        selectedNodePath = "";
        return false;
    }

    public boolean selectNode(NodeResult nodeResult) {
        String path = GenericNode.listPathToStringPath(nodeResult.path().list());
        if (getAllNodes().containsKey(path)) {
            selectedNodePath = path;
            return true;
        }
        selectedNodePath = "";
        return false;
    }
    
    public NodeResult getSelectedNode() {
        return getAllNodes().get(selectedNodePath);
    }

    /*----------------------------------------------------------------------------------------------------------------*/

    /*----------------------------------------------------------------------------------------------------------------*/
    /* Class Management */

    public void setNextNodeClass() {
        GuiManager.setNextNodeClass(getSelectedNode());
    }

    public void setPreviousNodeClass() {
        GuiManager.setPreviousNodeClass(getSelectedNode());
    }
    
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


    /*----------------------------------------------------------------------------------------------------------------*/
    /* List Management */

    public void removeNodeFromList(String path) {
        GenericNode parentNode = getNodeAtPath(path).node().getParentNode();
        if (parentNode instanceof AbstractNodeWithList<?> listNode) {
            listNode.removeSubNode(path);
        }
    }

    public void addEntryToList(String path) {
        GenericNode node = getNodeAtPath(path).node();
        if (node instanceof AbstractNodeWithList<?> listNode) {
            listNode.appendSubNode();
        }
    }
    
    /*----------------------------------------------------------------------------------------------------------------*/
    

    /*----------------------------------------------------------------------------------------------------------------*/
    /* nbt */
    
    public NbtCompound toNbt() {
        if (rootNode != null) {
            return rootNode.toNbt();
        }
        return new NbtCompound();
    }

    /*----------------------------------------------------------------------------------------------------------------*/
    

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

    
    





    public record ButtonDisplay(String subNodeName, String nodeName) {}
    public record EditorInfo(String name, String description) {}
}

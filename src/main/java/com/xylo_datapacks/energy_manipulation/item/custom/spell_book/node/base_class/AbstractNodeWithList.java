package com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class;

import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.records.NodeData;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.records.NodePath;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.records.NodeResult;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class AbstractNodeWithList<T extends GenericNode> extends AbstractNode {
    private final String subNodesId;
    private final SubNode.Builder<T> subNodeBuilderTemplate;
    private final List<SubNode<T>> subNodes = new ArrayList<>();
    
    public AbstractNodeWithList(NodeData nodeData, String subNodesId, SubNode.Builder<T> subNodeBuilderTemplate) {
        super(nodeData);
        this.subNodesId = subNodesId;
        this.subNodeBuilderTemplate = subNodeBuilderTemplate;
    }
    
    public void appendSubNode(Identifier newSubNodeValueIdentifier) {
        subNodes.add(subNodes.size(), subNodeBuilderTemplate.build(this, newSubNodeValueIdentifier));
    }

    public SubNode<T> getSubNode(int index) {
        if (index >= 0 && index < subNodes.size()) {
            return subNodes.get(index);
        }
        return null;
    }

    public boolean modifySubNode(int index, Identifier newSubNodeValueIdentifier) {
        if (index >= 0 && index < subNodes.size()) {
            return subNodes.get(index).setNodeClass(newSubNodeValueIdentifier, this);
        }
        return false;
    }
    
    public void prependSubNode(Identifier newSubNodeValueIdentifier) {
        subNodes.add(0, subNodeBuilderTemplate.build(this, newSubNodeValueIdentifier));
    }
    
    public void insertSubNode(int index, Identifier newSubNodeValueIdentifier) {
        subNodes.add(index, subNodeBuilderTemplate.build(this, newSubNodeValueIdentifier));
    }

    public SubNode<T> removeSubNode(int index) {
        return subNodes.remove(index);
    }
    
    public SubNode<T> removeFirstSubNode() {
        return subNodes.remove(0);
    }
    
    public SubNode<T> removeLastSubNode() {
        return subNodes.remove(subNodes.size() - 1);
    }

    public void removeAllSubNodes() {
        subNodes.clear();
    }
    
    /*----------------------------------------------------------------------------------------------------------------*/
    /* GenericNode Interface */

    @Override
    public NbtCompound toNbt() {
        return null;
    }

    @Override
    public List<NodeResult> getAllSubNodes() {
        
        List<NodeResult> returnSubNodes = new ArrayList<>();
        for (int index = 0; index < subNodes.size(); index++) {
            // generate path
            List<String> subNodePath = new ArrayList<>(Collections.singletonList(subNodesId + "[" + index + "]"));
            // this sub node
            returnSubNodes.add(new NodeResult(new NodePath(subNodePath, subNodesId), subNodes.get(index).getNode()));
        }
        return returnSubNodes;
    }

    @Override
    public List<NodeResult> getAllSubNodesRecursive(List<String> pathStart) {
       
        List<NodeResult> returnSubNodes = new ArrayList<>();
        for (int index = 0; index < subNodes.size(); index++) {
            // generate path
            List<String> subNodePath = new ArrayList<>(pathStart);
            subNodePath.add(subNodesId + "[" + index + "]");
            // this sub node
            returnSubNodes.add(new NodeResult(new NodePath(subNodePath, subNodesId), subNodes.get(index).getNode()));
            // recursive
            returnSubNodes.addAll(subNodes.get(index).getNode().getAllSubNodesRecursive(subNodePath));
        }
        return returnSubNodes;
    }
    
    @Override
    public SubNode<? extends GenericNode> getSubNode(String path) {
        return getSubNode(GenericNode.stripIndexFromPathElement(path));
    }

    @Override
    public boolean modifySubNode(String path, Identifier newSubNodeValueIdentifier) {
        if (modifySubNode(GenericNode.stripIndexFromPathElement(path), newSubNodeValueIdentifier)) {
            return true;
        }
        System.out.println("failed to modify sub node " + path + " with " + newSubNodeValueIdentifier);
        return false;
    }

    /*----------------------------------------------------------------------------------------------------------------*/
    
}

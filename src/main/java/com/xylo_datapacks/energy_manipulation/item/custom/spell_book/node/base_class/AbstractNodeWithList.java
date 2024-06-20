package com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class;

import net.minecraft.util.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class AbstractNodeWithList<T extends GenericNode> extends AbstractNode {
    private String subNodesId;
    private List<T> subNodes = new ArrayList<>();;
    
    public AbstractNodeWithList(String nodeId, String nodeGroupId, GenericNode parentNode, String subNodesId) {
        super(nodeId, nodeGroupId, parentNode);
        this.subNodesId = subNodesId;
    }
    
    public void appendSubNode(T subNode) {
        subNodes.add(subNodes.size(), subNode);
    }

    public void prependSubNode(T subNode) {
        subNodes.add(0, subNode);
    }

    public void modifySubNode(int index, T subNode) {
        subNodes.set(index, subNode);
    }

    public void modifySubNode(String subNodeId, T subNode) {
        modifySubNode(GenericNode.stripIndexFromPathElement(subNodeId), subNode);
    }
    
    public void insertSubNode(int index, T subNode) {
        subNodes.add(index, subNode);
    }

    public T removeSubNode(int index, T subNode) {
        return subNodes.remove(index);
    }
    
    public T removeFirstSubNode() {
        return subNodes.remove(0);
    }
    
    public T removeLastSubNode() {
        return subNodes.remove(subNodes.size() - 1);
    }

    public void removeAllSubNodes(T subNode) {
        subNodes.clear();
    }

    public T getSubNode(int subNodeIndex) {
        return subNodes.get(subNodeIndex);
    }

/*--------------------------------------------------------------------------------------------------------------------*/
    /* GenericNode Interface */
    
    @Override
    public List<NodeResult> getAllSubNodes() {
        
        List<NodeResult> returnSubNodes = new ArrayList<>();
        for (int index = 0; index < subNodes.size(); index++) {
            // generate path
            List<String> subNodePath = new ArrayList<>(Collections.singletonList(subNodesId + "[" + index + "]"));
            // this sub node
            returnSubNodes.add(new NodeResult(new NodePath(subNodePath, subNodesId), subNodes.get(index)));
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
            returnSubNodes.add(new NodeResult(new NodePath(subNodePath, subNodesId), subNodes.get(index)));
            // recursive
            returnSubNodes.addAll(subNodes.get(index).getAllSubNodesRecursive(subNodePath));
        }
        return returnSubNodes;
    }

    @Override
    public GenericNode getNodeFromPath(List<String> path) {
        if (path.isEmpty()) return this;
        
        GenericNode node = this.getSubNode(GenericNode.stripIndexFromPathElement(path.get(0)));
        
        if (node != null) {
            path.remove(0);
            return node.getNodeFromPath(path);
        }
        System.out.println("path failed at: " + path);
        return null;
    }

/*--------------------------------------------------------------------------------------------------------------------*/
    
}

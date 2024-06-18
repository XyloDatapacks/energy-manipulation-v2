package com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class;

import net.minecraft.util.Pair;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractNodeWithList<T extends GenericNode> extends AbstractNode {
    private String subNodesId;
    private List<T> subNodes = new ArrayList<>();;
    
    public AbstractNodeWithList(String nodeName, String nodeDescription, GenericNode parentNode, String subNodesId) {
        super(nodeName, nodeDescription, parentNode);
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
    public List<Pair<String, GenericNode>> getAllSubNodes() {
        
        List<Pair<String, GenericNode>> returnSubNodes = new ArrayList<>();
        for (T subNode : subNodes) {
            // this sub node
            returnSubNodes.add(new Pair<String, GenericNode>(subNodesId, subNode));
        }
        return returnSubNodes;
    }

    @Override
    public List<Pair<String, GenericNode>> getAllSubNodesIterative() {
       
        List<Pair<String, GenericNode>> returnSubNodes = new ArrayList<>();
        for (T subNode : subNodes) {
            // this sub node
            returnSubNodes.add(new Pair<String, GenericNode>(subNodesId, subNode));
            // recursive
            returnSubNodes.addAll(subNode.getAllSubNodesIterative());
        }
        return returnSubNodes;
    }

/*--------------------------------------------------------------------------------------------------------------------*/
    
}

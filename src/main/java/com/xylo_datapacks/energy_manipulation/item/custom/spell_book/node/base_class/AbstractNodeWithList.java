package com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class;

import net.minecraft.util.Pair;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractNodeWithList<T extends AbstractNode> extends AbstractNode {
    private String subNodesId;
    private List<T> subNodes = new ArrayList<>();;
    
    public AbstractNodeWithList(String nodeName, String nodeDescription, String subNodesId) {
        super(nodeName, nodeDescription);
        this.subNodesId = subNodesId;
    }
    
    public void appendSubNode(T subNode) {
        subNodes.add(subNodes.size(), subNode);
    }

    public void prependSubNode(T subNode) {
        subNodes.add(0, subNode);
    }

    public void insertSubNode(int index, T subNode) {
        subNodes.add(index, subNode);
    }

    public void removeAllSubNodes(T subNode) {
        subNodes.clear();
    }

    public T removeFirstSubNode() {
        return subNodes.remove(0);
    }
    
    public T removeLastSubNode() {
        return subNodes.remove(subNodes.size() - 1);
    }

    public T getSubNode(int subNodeIndex) {
        return subNodes.get(subNodeIndex);
    }
    
    @Override
    public List<Pair<String, AbstractNode>> getAllSubNodes() {
        
        List<Pair<String, AbstractNode>> returnSubNodes = new ArrayList<>();
        for (T subNode : subNodes) {
            // this sub node
            returnSubNodes.add(new Pair<String, AbstractNode>(subNodesId, subNode));
        }
        return returnSubNodes;
    }

    @Override
    public List<Pair<String, AbstractNode>> getAllSubNodesDisplayData() {
       
        List<Pair<String, AbstractNode>> returnSubNodes = new ArrayList<>();
        for (T subNode : subNodes) {
            // this sub node
            returnSubNodes.add(new Pair<String, AbstractNode>(subNodesId, subNode));
            // recursive
            returnSubNodes.addAll(subNode.getAllSubNodesDisplayData());
        }
        return returnSubNodes;
    }
}

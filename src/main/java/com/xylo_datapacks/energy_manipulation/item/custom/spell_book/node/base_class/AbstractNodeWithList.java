package com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class;

import net.minecraft.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractNodeWithList<T extends AbstractNode> extends AbstractNode {
    protected String subNodesId;
    protected List<T> subNodes = new ArrayList<>();;
    
    public AbstractNodeWithList(String nodeName, String nodeDescription, String subNodesId) {
        super(nodeName, nodeDescription);
        this.subNodesId = subNodesId;
    }
    
    /*public void appendSubNode(T subNode) {
        subNodes.addFirst(subNode);
    }

    public void prependSubNode(T subNode) {
        subNodes.addLast(subNode);
    }*/

    public void insertSubNode(int index, T subNode) {
        subNodes.add(index, subNode);
    }

    public void removeAllSubNodes(T subNode) {
        subNodes.clear();
    }

    /*public T removeFirstSubNode() {
        return subNodes.removeFirst();
    }
    
    public T removeLastSubNode() {
        return subNodes.removeLast();
    }*/

    @Override
    public List<Pair<String, AbstractNode>> getSubNodes() {
        List<Pair<String, AbstractNode>> returnSubNodes = new ArrayList<>();
        for (T subNode : subNodes) {
            // this sub node
            returnSubNodes.add(new Pair<String, AbstractNode>(subNodesId, subNode));
            // recursive
            returnSubNodes.addAll(subNode.getSubNodes());
        }
        return returnSubNodes;
    }
}

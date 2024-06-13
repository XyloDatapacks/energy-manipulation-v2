package com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class;

import net.minecraft.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractNodeWithMap extends AbstractNode {
    private Map<String, AbstractNode> subNodes = new HashMap<>();
    private Map<String, Class<? extends AbstractNode>> subNodesClass = new HashMap<>();
    
    public AbstractNodeWithMap(String nodeName, String nodeDescription) {
        super(nodeName, nodeDescription);
    }

    /** register a subNode and its class */
    protected <T extends AbstractNode> boolean registerSubNode(String subNodeName, Class<T> subNodeClass) {
        if (!subNodesClass.containsKey(subNodeName)) {
            // if the subNodeName is not already registered then we register it
            subNodesClass.put(subNodeName, subNodeClass);
            return true;
        }
        return false;
    }
    
    /** modify a subNode that must be registered */
    public <T extends AbstractNode> boolean modifySubNode(String subNodeName, T node) {
        if (subNodesClass.containsKey(subNodeName)) {
            // if the subNodeName is already registered then we check if the new node class is the same as the old one
            if (subNodesClass.get(subNodeName).isInstance(node)) {
                // since the new node class is the same as the old one, we can update the subNode value
                subNodes.put(subNodeName, node);
                return true;
            }
        }
        return false;
    }
    
    public AbstractNode getSubNode(String subNodeName) {
        return subNodes.get(subNodeName);
    }
    
    public <T extends AbstractNode> T getSubNode(String subNodeName, Class<T> subNodeClass) {
        AbstractNode node = getSubNode(subNodeName);
        return subNodeClass.isInstance(node) ? subNodeClass.cast(node) : null;
    }
    
    @Override
    public List<Pair<String, AbstractNode>> getAllSubNodes() {
        
        List<Pair<String, AbstractNode>> returnSubNodes = new ArrayList<>();
        for (Map.Entry<String, AbstractNode> subNode : subNodes.entrySet()) {
            // this sub node
            returnSubNodes.add(new Pair<String, AbstractNode>(subNode.getKey(), subNode.getValue()));
        }
        return returnSubNodes;
    }
    
    @Override
    public List<Pair<String, AbstractNode>> getAllSubNodesDisplayData() {
        
        List<Pair<String, AbstractNode>> returnSubNodes = new ArrayList<>();
        for (Map.Entry<String, AbstractNode> subNode : subNodes.entrySet()) {
            // this sub node
            returnSubNodes.add(new Pair<String, AbstractNode>(subNode.getKey(), subNode.getValue()));
            // recursive
            returnSubNodes.addAll(subNode.getValue().getAllSubNodesDisplayData());
        }
        return returnSubNodes;
    }
}

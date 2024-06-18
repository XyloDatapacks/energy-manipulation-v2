package com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class;

import net.minecraft.util.Pair;

import java.util.*;

public abstract class AbstractNodeWithMap extends AbstractNode {
    private Map<String, GenericNode> subNodes = new LinkedHashMap<>();
    private Map<String, Class<? extends GenericNode>> subNodesClass = new LinkedHashMap<>();
    
    public AbstractNodeWithMap(String nodeName, String nodeDescription, GenericNode parentNode) {
        super(nodeName, nodeDescription, parentNode);
    }

    /** register a subNode and its class */
    protected <T extends GenericNode> boolean registerSubNode(String subNodeName, Class<T> subNodeClass, T defaultNode) {
        // if the subNodeName is not in the classes map
        if (!subNodesClass.containsKey(subNodeName)) {
            // we add the name and class to the class map
            subNodesClass.put(subNodeName, subNodeClass);
            // we add the name and node to the nodes map
            subNodes.put(subNodeName, defaultNode);
            return true;
        }
        return false;
    }
    
    /** modify a subNode that must be registered */
    public <T extends GenericNode> boolean modifySubNode(String subNodeName, T node) {
        // if the subNodeName is already registered
        if (subNodesClass.containsKey(subNodeName)) {
            // we check if the new node class is the same as the old one
            if (subNodesClass.get(subNodeName).isInstance(node)) {
                // since the new node class is the same as the old one, we can update the subNode value
                subNodes.put(subNodeName, node);
                return true;
            }
        }
        return false;
    }
    
    public GenericNode getSubNode(String subNodeName) {
        return subNodes.get(subNodeName);
    }
    
    public <T extends GenericNode> T getSubNode(String subNodeName, Class<T> subNodeClass) {
        GenericNode node = getSubNode(subNodeName);
        return subNodeClass.isInstance(node) ? subNodeClass.cast(node) : null;
    }


/*--------------------------------------------------------------------------------------------------------------------*/
    /* GenericNode Interface */
    
    @Override
    public List<Pair<String, GenericNode>> getAllSubNodes() {
        
        List<Pair<String, GenericNode>> returnSubNodes = new ArrayList<>();
        for (Map.Entry<String, GenericNode> subNode : subNodes.entrySet()) {
            // this sub node
            returnSubNodes.add(new Pair<String, GenericNode>(subNode.getKey(), subNode.getValue()));
        }
        return returnSubNodes;
    }
    
    @Override
    public List<Pair<String, GenericNode>> getAllSubNodesIterative() {
        
        List<Pair<String, GenericNode>> returnSubNodes = new ArrayList<>();
        for (Map.Entry<String, GenericNode> subNode : subNodes.entrySet()) {
            // this sub node
            returnSubNodes.add(new Pair<String, GenericNode>(subNode.getKey(), subNode.getValue()));
            // recursive
            returnSubNodes.addAll(subNode.getValue().getAllSubNodesIterative());
        }
        return returnSubNodes;
    }

/*--------------------------------------------------------------------------------------------------------------------*/
    
}

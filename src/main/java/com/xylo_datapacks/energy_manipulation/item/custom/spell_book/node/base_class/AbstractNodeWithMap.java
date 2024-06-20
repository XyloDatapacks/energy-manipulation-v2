package com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class;

import net.minecraft.util.Pair;

import java.util.*;

public abstract class AbstractNodeWithMap extends AbstractNode {
    private Map<String, GenericNode> subNodes = new LinkedHashMap<>();
    private Map<String, Class<? extends GenericNode>> subNodesClass = new LinkedHashMap<>();
    
    public AbstractNodeWithMap(String nodeId, String nodeGroupId, GenericNode parentNode) {
        super(nodeId, nodeGroupId, parentNode);
    }

    /** register a subNode and its class */
    protected <T extends GenericNode> boolean registerSubNode(String subNodeId, Class<T> subNodeClass, T defaultNode) {
        // if the subNodeId is not in the classes map
        if (!subNodesClass.containsKey(subNodeId)) {
            // we add the name and class to the class map
            subNodesClass.put(subNodeId, subNodeClass);
            // we add the name and node to the nodes map
            subNodes.put(subNodeId, defaultNode);
            return true;
        }
        return false;
    }
    
    /** modify a subNode that must be registered */
    public <T extends GenericNode> boolean modifySubNode(String subNodeId, T node) {
        // if the subNodeId is already registered
        if (subNodesClass.containsKey(subNodeId)) {
            // we check if the new node class is the same as the old one
            if (subNodesClass.get(subNodeId).isInstance(node)) {
                // since the new node class is the same as the old one, we can update the subNode value
                subNodes.put(subNodeId, node);
                return true;
            }
        }
        return false;
    }
    
    public GenericNode getSubNode(String subNodeId) {
        return subNodes.get(subNodeId);
    }
    
    public <T extends GenericNode> T getSubNode(String subNodeId, Class<T> subNodeClass) {
        GenericNode node = getSubNode(subNodeId);
        return subNodeClass.isInstance(node) ? subNodeClass.cast(node) : null;
    }


/*--------------------------------------------------------------------------------------------------------------------*/
    /* GenericNode Interface */
    
    @Override
    public List<NodeResult> getAllSubNodes() {
        
        List<NodeResult> returnSubNodes = new ArrayList<>();
        for (Map.Entry<String, GenericNode> subNode : subNodes.entrySet()) {
            // generate path
            List<String> subNodePath = new ArrayList<>(Collections.singletonList(subNode.getKey()));
            // this sub node
            returnSubNodes.add(new NodeResult(new NodePath(subNodePath, subNode.getKey()), subNode.getValue()));
        }
        return returnSubNodes;
    }
    
    @Override
    public List<NodeResult> getAllSubNodesRecursive(List<String> pathStart) {
        
        List<NodeResult> returnSubNodes = new ArrayList<>();
        for (Map.Entry<String, GenericNode> subNode : subNodes.entrySet()) {
            // generate path
            List<String> subNodePath = new ArrayList<>(pathStart);
            subNodePath.add(subNode.getKey());
            // this sub node
            returnSubNodes.add(new NodeResult(new NodePath(subNodePath, subNode.getKey()), subNode.getValue()));
            // recursive
            returnSubNodes.addAll(subNode.getValue().getAllSubNodesRecursive(subNodePath));
        }
        return returnSubNodes;
    }

    @Override
    public GenericNode getNodeFromPath(List<String> path) {
        if (path.isEmpty()) return this;
        
        GenericNode node = this.getSubNode(path.get(0));
        
        if (node != null) {
            path.remove(0);
            return node.getNodeFromPath(path);
        }
        System.out.println("path failed at: " + path);
        return null;
    }

    /*--------------------------------------------------------------------------------------------------------------------*/
    
}

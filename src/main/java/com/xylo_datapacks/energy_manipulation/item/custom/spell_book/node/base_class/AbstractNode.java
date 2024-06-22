package com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class;


import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.effect.BreakEffectNode;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.effect.EffectProviderNode;

import java.util.List;
import java.util.function.Supplier;

public abstract class AbstractNode implements GenericNode {
    private final String NODE_ID;
    private final String NODE_GROUP_ID;
    private GenericNode parentNode;
    public static Supplier<? extends AbstractNode> nodeSupplier;
    
    public AbstractNode(String nodeId, String nodeGroupId) {
        NODE_ID = nodeId;
        NODE_GROUP_ID = nodeGroupId;
    }
    
    /** set parent node */
    protected void setParentNode(GenericNode parent) { parentNode = parent; }
    
    /** register a subNode and its class */
    protected abstract <T extends GenericNode> boolean registerSubNode(String subNodeId, Class<T> subNodeClass, T defaultNode);

    public static Supplier<? extends AbstractNode> getNodeSupplier() {
        return null;
    }

    /*--------------------------------------------------------------------------------------------------------------------*/
    /* GenericNode Interface */
    
    @Override
    public String getNodeId() { return NODE_ID; }
    @Override
    public String getNodeGroupId() { return NODE_GROUP_ID; }
    
    @Override
    public GenericNode getParentNode() { return parentNode; };

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
    
    @Override
    public boolean modifyNodeFromPath(List<String> path, GenericNode newNode) {
        if (path.isEmpty()) return false;
        if (path.size() == 1) return modifySubNode(path.get(0), newNode);

        // we have a compound path. the last path element is the target subNode
        String subNodeTargetPath = path.remove(path.size() - 1);
        // we find the parent of the target subNode (since we removed the last element)
        GenericNode parentNode = this.getNodeFromPath(path);
        if (parentNode != null) {
            // now that we have the parent node, we can call this function again 
            // with the path of the target subNode (size == 1)
            return parentNode.modifyNodeFromPath(subNodeTargetPath, newNode);
        }
        return false;
    }
    
/*--------------------------------------------------------------------------------------------------------------------*/
    
}

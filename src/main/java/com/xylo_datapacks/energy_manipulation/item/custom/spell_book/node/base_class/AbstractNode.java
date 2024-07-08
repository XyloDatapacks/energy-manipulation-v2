package com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class;

import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.Nodes;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.records.NodeData;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.records.NodeResult;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.records.SubNodeData;
import net.minecraft.util.Identifier;

import java.util.List;

public abstract class AbstractNode implements GenericNode {
    private final Identifier nodeIdentifier;
    private GenericNode parentNode;
    private int nesting = 0;
    
    public AbstractNode(NodeData nodeData) {
        this.nodeIdentifier = nodeData.identifier();
    }
    
    /** set parent node */
    protected final void setParentNode(GenericNode parent) { 
        parentNode = parent; 
        nesting = parentNode.getNesting() + 1;
    }
    
    /*----------------------------------------------------------------------------------------------------------------*/
    /* GenericNode Interface */
    
    @Override
    public final Identifier getNodeIdentifier() { return nodeIdentifier; }

    @Override
    public final NodeData<? extends GenericNode> getNodeData() {
        return Nodes.NODES.get(nodeIdentifier);
    }

    @Override
    public final SubNodeData getSubNodeData(String subNodeId) {
        return Nodes.NODES.get(nodeIdentifier).subNodes().get(subNodeId);
    }
    
    @Override
    public final GenericNode getParentNode() { return parentNode; };

    @Override
    public int getNesting() {
        return nesting;
    }

    @Override
    public final GenericNode getNodeFromPath(List<String> path) {
        if (path.isEmpty()) return this;

        SubNode<? extends GenericNode> node = this.getSubNode(path.get(0));

        if (node != null) {
            path.remove(0);
            return node.getNode().getNodeFromPath(path);
        }
        if (!path.isEmpty()) System.out.println("path failed at: " + path);
        return null;
    }
    
    @Override
    public final boolean modifyNodeFromPath(List<String> path, Identifier newSubNodeValueIdentifier) {
        if (path.isEmpty()) return false;
        if (path.size() == 1) return modifySubNode(path.get(0), newSubNodeValueIdentifier);

        // we have a compound path. the last path element is the target subNode
        String subNodeTargetPath = path.remove(path.size() - 1);
        // we find the parent of the target subNode (since we removed the last element)
        GenericNode parentNode = this.getNodeFromPath(path);
        if (parentNode != null) {
            // now that we have the parent node, we can call this function again 
            // with the path of the target subNode (size == 1)
            return parentNode.modifyNodeFromPath(subNodeTargetPath, newSubNodeValueIdentifier);
        }
        return false;
    }

    /*----------------------------------------------------------------------------------------------------------------*/
    
    
}

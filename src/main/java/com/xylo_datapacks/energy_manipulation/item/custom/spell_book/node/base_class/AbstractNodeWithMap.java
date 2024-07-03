package com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class;

import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.records.NodeData;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.records.NodePath;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.records.NodeResult;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;

import java.util.*;

public abstract class AbstractNodeWithMap extends AbstractNode {
    private final Map<String, SubNode<? extends GenericNode>> subNodes = new LinkedHashMap<>();
    
    public AbstractNodeWithMap(NodeData nodeData) {
        super(nodeData);
    }

    protected final <T extends GenericNode> SubNode<T> registerSubNode(String subNodeId, SubNode<T> subNode) {
        subNodes.put(subNodeId, subNode);
        return subNode;
    }
    
    /*----------------------------------------------------------------------------------------------------------------*/
    /* GenericNode Interface */

    @Override
    public final NbtCompound toNbt() {
        NbtCompound nbt = new NbtCompound();
        nbt.putString("node_type", getNodeIdentifier().toString());
        // TODO: ...
        return null;
    }

    @Override
    public final Map<String, NodeResult> getAllSubNodes() {
        
        Map<String, NodeResult> returnSubNodes = new LinkedHashMap<>();
        for (Map.Entry<String, SubNode<? extends GenericNode>> subNode : subNodes.entrySet()) {
            // generate path
            List<String> subNodePath = new ArrayList<>();
            subNodePath.add(subNode.getKey());
            // this sub node
            returnSubNodes.put(GenericNode.listPathToStringPath(subNodePath), new NodeResult(new NodePath(subNodePath, subNode.getKey()), subNode.getValue().getNode()));
        }
        return returnSubNodes;
    }
    
    @Override
    public final Map<String, NodeResult> getAllSubNodesRecursive(List<String> pathStart) {
        
        Map<String, NodeResult> returnSubNodes = new LinkedHashMap<>();
        for (Map.Entry<String, SubNode<? extends GenericNode>> subNode : subNodes.entrySet()) {
            // generate path
            List<String> subNodePath = new ArrayList<>(pathStart);
            subNodePath.add(subNode.getKey());
            // this sub node
            returnSubNodes.put(GenericNode.listPathToStringPath(subNodePath), new NodeResult(new NodePath(subNodePath, subNode.getKey()), subNode.getValue().getNode()));
            // recursive
            returnSubNodes.putAll(subNode.getValue().getNode().getAllSubNodesRecursive(subNodePath));
        }
        return returnSubNodes;
    }
    
    @Override
    public final SubNode<? extends GenericNode> getSubNode(String path) {
        return subNodes.get(path);
    }
    
    @Override
    public final boolean modifySubNode(String path, Identifier newSubNodeValueIdentifier) {
        // if the subNodeId is already registered
        if (subNodes.containsKey(path)) {
            if (subNodes.get(path).setNodeClass(newSubNodeValueIdentifier)) {
                return true;
            }
        }
        System.out.println("failed to modify sub node " + path + " with " + newSubNodeValueIdentifier);
        return false;
    }

    /*----------------------------------------------------------------------------------------------------------------*/
    
}

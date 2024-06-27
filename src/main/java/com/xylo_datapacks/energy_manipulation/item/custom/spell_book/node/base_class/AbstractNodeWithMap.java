package com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class;

import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.records.NodeData;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.records.NodePath;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.records.NodeResult;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;

import java.util.*;

public abstract class AbstractNodeWithMap extends AbstractNode {
    private final Map<String, SubNode<? extends GenericNode>> subNodes = new LinkedHashMap<>();
    
    public AbstractNodeWithMap(NodeData nodeData) {
        super(nodeData);
    }

    protected <T extends GenericNode> SubNode<T> registerSubNode(String subNodeId, SubNode<T> subNode) {
        subNodes.put(subNodeId, subNode);
        return subNode;
    }
    
    /*----------------------------------------------------------------------------------------------------------------*/
    /* GenericNode Interface */

    @Override
    public NbtCompound toNbt() {
        NbtCompound nbt = new NbtCompound();
        nbt.putString("node_type", getNodeIdentifier().toString());
        // TODO: ...
        return null;
    }

    @Override
    public List<NodeResult> getAllSubNodes() {
        
        List<NodeResult> returnSubNodes = new ArrayList<>();
        for (Map.Entry<String, SubNode<? extends GenericNode>> subNode : subNodes.entrySet()) {
            // generate path
            List<String> subNodePath = new ArrayList<>(Collections.singletonList(subNode.getKey()));
            // this sub node
            returnSubNodes.add(new NodeResult(new NodePath(subNodePath, subNode.getKey()), subNode.getValue().getNode()));
        }
        return returnSubNodes;
    }
    
    @Override
    public List<NodeResult> getAllSubNodesRecursive(List<String> pathStart) {
        
        List<NodeResult> returnSubNodes = new ArrayList<>();
        for (Map.Entry<String, SubNode<? extends GenericNode>> subNode : subNodes.entrySet()) {
            // generate path
            List<String> subNodePath = new ArrayList<>(pathStart);
            subNodePath.add(subNode.getKey());
            // this sub node
            returnSubNodes.add(new NodeResult(new NodePath(subNodePath, subNode.getKey()), subNode.getValue().getNode()));
            // recursive
            returnSubNodes.addAll(subNode.getValue().getNode().getAllSubNodesRecursive(subNodePath));
        }
        return returnSubNodes;
    }
    
    @Override
    public SubNode<? extends GenericNode> getSubNode(String path) {
        return subNodes.get(path);
    }
    
    @Override
    public boolean modifySubNode(String path, Identifier newSubNodeValueIdentifier) {
        // if the subNodeId is already registered
        if (subNodes.containsKey(path)) {
            if (subNodes.get(path).setNode(newSubNodeValueIdentifier, this)) {
                return true;
            }
        }
        System.out.println("failed to modify sub node " + path + " with " + newSubNodeValueIdentifier);
        return false;
    }

    /*----------------------------------------------------------------------------------------------------------------*/
    
}

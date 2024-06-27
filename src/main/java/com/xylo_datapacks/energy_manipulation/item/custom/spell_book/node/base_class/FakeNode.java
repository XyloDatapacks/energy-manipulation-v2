package com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class;

import com.xylo_datapacks.energy_manipulation.EnergyManipulation;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.Nodes;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.records.NodeData;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.records.NodeResult;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.records.SubNodeData;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;

import java.util.List;

public class FakeNode implements GenericNode {
    private GenericNode parentNode;
    
    public FakeNode() {
    }

    /** set parent node */
    protected void setParentNode(GenericNode parent) { parentNode = parent; }

    /*----------------------------------------------------------------------------------------------------------------*/
    /* GenericNode Interface */
    
    @Override
    public Identifier getNodeIdentifier() {
        return Nodes.FAKE_NODE.identifier();
    }

    @Override
    public NbtCompound toNbt() {
        return null;
    }

    @Override
    public NodeData<? extends GenericNode> getNodeData() {
        return Nodes.NODES.get(parentNode.getNodeIdentifier());
    }

    @Override
    public SubNodeData getSubNodeData(String subNodeId) {
        return Nodes.NODES.get(parentNode.getNodeIdentifier()).subNodes().get(subNodeId);
    }

    @Override
    public GenericNode getParentNode() {
        return parentNode;
    }

    @Override
    public List<NodeResult> getAllSubNodes() {
        return List.of();
    }

    @Override
    public List<NodeResult> getAllSubNodesRecursive(List<String> pathStart) {
        return List.of();
    }

    @Override
    public SubNode<? extends GenericNode> getSubNode(String path) {
        return null;
    }

    @Override
    public GenericNode getNodeFromPath(List<String> path) {
        return null;
    }

    @Override
    public boolean modifySubNode(String path, Identifier newSubNodeValueIdentifier) {
        return false;
    }

    @Override
    public boolean modifyNodeFromPath(List<String> path, Identifier newSubNodeValueIdentifier) {
        return false;
    }

    /*----------------------------------------------------------------------------------------------------------------*/
}

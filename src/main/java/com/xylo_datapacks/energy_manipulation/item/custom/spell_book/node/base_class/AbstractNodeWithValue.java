package com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class;

import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.Nodes;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.records.NodeData;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.records.NodePath;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.records.NodeResult;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.value_type.DoubleValueTypeNode;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.value_type.ValueTypeNode;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class AbstractNodeWithValue<T extends ValueTypeNode> extends AbstractNode {
    private final String subNodesId;
    private final SubNode<T> subNode;

    public AbstractNodeWithValue(NodeData nodeData, String subNodesId, SubNode.Builder<T> subNodeBuilderTemplate) {
        super(nodeData);
        this.subNodesId = subNodesId;
        subNode = subNodeBuilderTemplate.build(this);
    }

    /*----------------------------------------------------------------------------------------------------------------*/
    /* GenericNode Interface */

    @Override
    public NbtCompound toNbt() {
        return null;
    }

    @Override
    public List<NodeResult> getAllSubNodes() {
        
        List<NodeResult> returnSubNodes = new ArrayList<>();
        // generate path
        List<String> subNodePath = new ArrayList<>(Collections.singletonList(subNodesId));
        // this sub node
        returnSubNodes.add(new NodeResult(new NodePath(subNodePath, subNodesId), subNode.getNode()));
        return returnSubNodes;
    }

    @Override
    public List<NodeResult> getAllSubNodesRecursive(List<String> pathStart) {
        return getAllSubNodes();
    }

    @Override
    public SubNode<? extends GenericNode> getSubNode(String path) {
        return null;
    }

    @Override
    public boolean modifySubNode(String path, Identifier newSubNodeValueIdentifier) {
        return false;
    }

    /*----------------------------------------------------------------------------------------------------------------*/
}

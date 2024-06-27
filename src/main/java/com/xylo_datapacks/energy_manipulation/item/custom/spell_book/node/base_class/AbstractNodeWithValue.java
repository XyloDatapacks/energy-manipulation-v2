package com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class;

import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.Nodes;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.records.NodeData;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.records.NodePath;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.records.NodeResult;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class AbstractNodeWithValue<T> extends AbstractNode {
    private final String subNodesId;
    private T value;
    private final Supplier<FakeNode> valueNodeSupplier = () -> { 
        FakeNode resultNode = new FakeNode();
        resultNode.setParentNode(this);
        return resultNode;
    };
    
    public AbstractNodeWithValue(NodeData nodeData, String subNodesId) {
        super(nodeData);
        this.subNodesId = subNodesId;
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
        returnSubNodes.add(new NodeResult(new NodePath(subNodePath, subNodesId), valueNodeSupplier.get()));
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

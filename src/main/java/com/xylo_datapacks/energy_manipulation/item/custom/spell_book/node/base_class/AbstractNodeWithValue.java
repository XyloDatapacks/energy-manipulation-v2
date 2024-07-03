package com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class;

import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.records.NodeData;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.records.NodeResult;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.Map;

public abstract class AbstractNodeWithValue<T> extends AbstractNode implements ValueTypeNode<T> {
    private T value;
    
    public AbstractNodeWithValue(NodeData nodeData) {
        super(nodeData);
    }

    /*----------------------------------------------------------------------------------------------------------------*/
    /* ValueTypeNode Interface */
    
    @Override
    public T getValue() { return value; }

    @Override
    public abstract String getValueDisplay();
    
    @Override
    public boolean setValue(T value) {
        this.value = value;
        return true;
    }

    /*----------------------------------------------------------------------------------------------------------------*/

    /*----------------------------------------------------------------------------------------------------------------*/
    /* GenericNode Interface */
    
    @Override
    public final NbtCompound toNbt() {
        return null;
    }

    @Override
    public final Map<String, NodeResult> getAllSubNodes() {
        return Map.of();
    }

    @Override
    public final Map<String, NodeResult> getAllSubNodesRecursive(List<String> pathStart) {
        return Map.of();
    }

    @Override
    public final SubNode<? extends GenericNode> getSubNode(String path) {
        return null;
    }

    @Override
    public final boolean modifySubNode(String path, Identifier newSubNodeValueIdentifier) {
        return false;
    }

    /*----------------------------------------------------------------------------------------------------------------*/
}

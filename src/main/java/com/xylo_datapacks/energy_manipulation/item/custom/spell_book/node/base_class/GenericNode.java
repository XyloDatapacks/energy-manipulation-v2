package com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class;

import net.minecraft.util.Pair;

import java.util.List;

public interface GenericNode {

    public abstract String getNodeName();
    public abstract String getNodeDescription();

    public abstract GenericNode getParentNode();
    
    public abstract List<Pair<String, GenericNode>> getAllSubNodes();
    public abstract List<Pair<String, GenericNode>> getAllSubNodesIterative();

    public abstract List<Pair<String, GenericNode>> getNodeDisplayData();
}

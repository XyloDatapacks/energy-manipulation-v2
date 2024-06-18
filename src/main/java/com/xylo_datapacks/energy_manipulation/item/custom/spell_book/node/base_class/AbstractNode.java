package com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class;


import net.minecraft.util.Pair;

import java.util.List;

public abstract class AbstractNode implements GenericNode {
    private final String NODE_NAME;
    private final String NODE_DESCRIPTION;
    private final GenericNode parentNode;
    
    public AbstractNode(String nodeName, String nodeDescription, GenericNode parentNode) {
        NODE_NAME = nodeName;
        NODE_DESCRIPTION = nodeDescription;
        this.parentNode = parentNode;
    }
    
/*--------------------------------------------------------------------------------------------------------------------*/
    /* GenericNode Interface */
    
    @Override
    public String getNodeName() { return NODE_NAME; }
    @Override
    public String getNodeDescription() { return NODE_DESCRIPTION; }

    @Override
    public GenericNode getParentNode() { return parentNode; };
    
    @Override
    public abstract List<Pair<String, GenericNode>> getAllSubNodes();
    @Override
    public abstract List<Pair<String, GenericNode>> getAllSubNodesIterative();

    @Override
    public List<Pair<String, GenericNode>> getNodeDisplayData() {
        return List.of();
    }


    /*--------------------------------------------------------------------------------------------------------------------*/
    
}

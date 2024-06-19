package com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class;


import net.minecraft.util.Pair;

import java.util.List;

public abstract class AbstractNode implements GenericNode {
    private final String NODE_NAME;
    private final String NODE_DESCRIPTION;
    private final GenericNode parentNode;
    private final int nesting;
    
    public AbstractNode(String nodeName, String nodeDescription, GenericNode parentNode) {
        NODE_NAME = nodeName;
        NODE_DESCRIPTION = nodeDescription;
        this.parentNode = parentNode;
        this.nesting = parentNode != null ? parentNode.getNesting() + 1 : 0;
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
    public Integer getNesting() { return nesting; }

    @Override
    public NodeDisplayData getNodeDisplayData() {
        return new NodeDisplayData(getNodeName(), getNodeDescription(), getNesting());
    }
    
/*--------------------------------------------------------------------------------------------------------------------*/
    
}

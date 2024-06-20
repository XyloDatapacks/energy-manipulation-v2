package com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class;


public abstract class AbstractNode implements GenericNode {
    private final String NODE_ID;
    private final String NODE_GROUP_ID;
    private final GenericNode parentNode;
    private final int nesting;
    
    public AbstractNode(String nodeId, String nodeGroupId, GenericNode parentNode) {
        NODE_ID = nodeId;
        NODE_GROUP_ID = nodeGroupId;
        this.parentNode = parentNode;
        this.nesting = parentNode != null ? parentNode.getNesting() + 1 : 0;
    }
    
/*--------------------------------------------------------------------------------------------------------------------*/
    /* GenericNode Interface */
    
    @Override
    public String getNodeId() { return NODE_ID; }
    @Override
    public String getNodeGroupId() { return NODE_GROUP_ID; }

    @Override
    public GenericNode getParentNode() { return parentNode; };
    @Override
    public Integer getNesting() { return nesting; }

    @Override
    public NodeDisplayData getNodeDisplayData() {
        return new NodeDisplayData(getNodeId(), getNodeGroupId(), getNesting());
    }
    
/*--------------------------------------------------------------------------------------------------------------------*/
    
}

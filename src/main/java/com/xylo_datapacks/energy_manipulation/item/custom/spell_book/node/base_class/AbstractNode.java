package com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class;


import net.minecraft.util.Pair;

import java.util.List;
import java.util.Map;

public abstract class AbstractNode {
    protected final String NODE_NAME;
    protected final String NODE_DESCRIPTION;
    
    public AbstractNode(String nodeName, String nodeDescription) {
        NODE_NAME = nodeName;
        NODE_DESCRIPTION = nodeDescription;
    }
    
    public String getNodeName() { return NODE_NAME; }
    public String getNodeDescription() { return NODE_DESCRIPTION; }
    
    public abstract AbstractNode getNodeParent();
    public abstract List<Pair<String, AbstractNode>> getSubNodes(); 
    
    public abstract Map<String, String> getSubNodesDisplayData();
}

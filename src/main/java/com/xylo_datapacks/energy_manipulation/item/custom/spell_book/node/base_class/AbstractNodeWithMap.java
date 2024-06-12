package com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class;

import net.minecraft.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractNodeWithMap<T extends AbstractNode> extends AbstractNode {
    protected Map<String, T> subNodes = new HashMap<>();
    
    public AbstractNodeWithMap(String nodeName, String nodeDescription) {
        super(nodeName, nodeDescription);
    }

    @Override
    public List<Pair<String, AbstractNode>> getSubNodes() {
        List<Pair<String, AbstractNode>> returnSubNodes = new ArrayList<>();
        for (Map.Entry<String, T> subNode : subNodes.entrySet()) {
            // this sub node
            returnSubNodes.add(new Pair<String, AbstractNode>(subNode.getKey(), subNode.getValue()));
            // recursive
            returnSubNodes.addAll(subNode.getValue().getSubNodes());
        }
        return returnSubNodes;
    }
}

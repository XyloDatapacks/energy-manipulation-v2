package com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.effect;

import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.AbstractNode;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.AbstractNodeWithMap;

public class AbstractEffectNode extends AbstractNodeWithMap {
    
    public AbstractEffectNode(String nodeName, String nodeDescription) {
        super(nodeName, nodeDescription);
    }

    @Override
    public AbstractNode getNodeParent() {
        return null;
    }
}

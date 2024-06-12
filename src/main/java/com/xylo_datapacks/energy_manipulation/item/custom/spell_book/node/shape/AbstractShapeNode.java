package com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.shape;

import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.AbstractNode;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.AbstractNodeWithMap;

public abstract class AbstractShapeNode extends AbstractNodeWithMap<AbstractNode> {
    
    public AbstractShapeNode(String nodeName, String nodeDescription) {
        super(nodeName, nodeDescription);
    }

    public abstract boolean shapeInit();
    public abstract boolean shapeTick();
    public abstract boolean shapeDestroy();
}

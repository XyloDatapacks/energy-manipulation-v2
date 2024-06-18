package com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.shape;

import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.GenericNode;

public interface ShapeNode extends GenericNode {
    
    public abstract boolean shapeInit();
    public abstract boolean shapeTick();
    public abstract boolean shapeDestroy();
    
}

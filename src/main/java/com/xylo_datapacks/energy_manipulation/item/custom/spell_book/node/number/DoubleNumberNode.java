package com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.number;

import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.Nodes;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.AbstractNodeWithValue;

public class DoubleNumberNode extends AbstractNodeWithValue<Double> implements NumberNode {
    
    public DoubleNumberNode() {
        super(Nodes.NUMBER_DOUBLE, "value");
    }
}

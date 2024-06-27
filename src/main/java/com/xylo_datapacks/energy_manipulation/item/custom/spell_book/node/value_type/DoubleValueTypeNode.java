package com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.value_type;

import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.Nodes;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.AbstractNodeValue;

public class DoubleValueTypeNode extends AbstractNodeValue<Double> implements ValueTypeNode {

    public DoubleValueTypeNode() {
        super(Nodes.VALUE_TYPE_DOUBLE);
    }
}

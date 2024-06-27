package com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.number;

import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.Nodes;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.AbstractNodeWithValue;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.SubNode;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.value_type.DoubleValueTypeNode;

import java.util.List;

public class DoubleNumberNode extends AbstractNodeWithValue<DoubleValueTypeNode> implements NumberNode {
    
    public DoubleNumberNode() {
        super(Nodes.NUMBER_DOUBLE, "value", new SubNode.Builder<DoubleValueTypeNode>()
                .addNodeValues(List.of(Nodes.VALUE_TYPE_DOUBLE))
        );
    }
}

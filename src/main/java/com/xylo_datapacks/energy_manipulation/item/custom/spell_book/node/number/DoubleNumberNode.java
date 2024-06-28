package com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.number;

import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.Nodes;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.AbstractNodeWithMap;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.SubNode;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.value_type.DoubleValueTypeNode;

import java.util.List;

public class DoubleNumberNode extends AbstractNodeWithMap implements NumberNode {
    SubNode<DoubleValueTypeNode> value = registerSubNode("value", new SubNode.Builder<DoubleValueTypeNode>()
            .addNodeValues(List.of(
                    Nodes.VALUE_TYPE_DOUBLE))
            .build(this));
    
    public DoubleNumberNode() {
        super(Nodes.NUMBER_DOUBLE);
    }
}

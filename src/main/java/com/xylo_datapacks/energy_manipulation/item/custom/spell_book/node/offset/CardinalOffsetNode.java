package com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.offset;

import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.Nodes;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.AbstractNodeWithMap;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.SubNode;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.number.NumberNode;

import java.util.List;

public class CardinalOffsetNode extends AbstractNodeWithMap implements OffsetNode {
    SubNode<NumberNode> x = registerSubNode("x", new SubNode.Builder<NumberNode>()
            .addNodeValues(List.of(
                    Nodes.NUMBER_DOUBLE))
            .build(this));
    SubNode<NumberNode> y = registerSubNode("y", new SubNode.Builder<NumberNode>()
            .addNodeValues(List.of(
                    Nodes.NUMBER_DOUBLE))
            .build(this));
    SubNode<NumberNode> z = registerSubNode("z", new SubNode.Builder<NumberNode>()
            .addNodeValues(List.of(
                    Nodes.NUMBER_DOUBLE))
            .build(this));
    
    public CardinalOffsetNode() {
        super(Nodes.OFFSET_CARDINAL);
    }
}

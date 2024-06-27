package com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.position;

import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.Nodes;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.AbstractNodeWithMap;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.SubNode;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.number.NumberNode;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.offset.OffsetNode;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.records.NodeData;

import java.util.List;

public class AlignPositionNode extends AbstractNodeWithMap implements PositionNode {
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
    
    public AlignPositionNode() {
        super(Nodes.POSITION_ALIGN);
    }
}

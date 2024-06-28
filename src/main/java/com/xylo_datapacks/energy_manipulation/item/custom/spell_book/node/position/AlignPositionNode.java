package com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.position;

import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.Nodes;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.AbstractNodeWithMap;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.SubNode;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.ValueTypeNode;

import java.util.List;

public class AlignPositionNode extends AbstractNodeWithMap implements PositionNode {
    SubNode<ValueTypeNode<Boolean>> x = registerSubNode("x", new SubNode.Builder<ValueTypeNode<Boolean>>()
            .addNodeValues(List.of(
                    Nodes.VALUE_TYPE_BOOLEAN))
            .build(this));
    SubNode<ValueTypeNode<Boolean>> y = registerSubNode("y", new SubNode.Builder<ValueTypeNode<Boolean>>()
            .addNodeValues(List.of(
                    Nodes.VALUE_TYPE_BOOLEAN))
            .build(this));
    SubNode<ValueTypeNode<Boolean>> z = registerSubNode("z", new SubNode.Builder<ValueTypeNode<Boolean>>()
            .addNodeValues(List.of(
                    Nodes.VALUE_TYPE_BOOLEAN))
            .build(this));
    
    public AlignPositionNode() {
        super(Nodes.POSITION_ALIGN);
    }
}

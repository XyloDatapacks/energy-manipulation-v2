package com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.position;

import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.Nodes;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.AbstractNodeWithMap;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.SubNode;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.offset.OffsetNode;

import java.util.List;

public class OffsetPositionNode extends AbstractNodeWithMap implements PositionNode{
    SubNode<OffsetNode> offset = registerSubNode("offset", new SubNode.Builder<OffsetNode>()
            .addNodeValues(List.of(
                    Nodes.OFFSET_CARDINAL,
                    Nodes.OFFSET_DIRECTIONAL))
            .build(this));
    
    public OffsetPositionNode() {
        super(Nodes.POSITION_OFFSET);
    }
}

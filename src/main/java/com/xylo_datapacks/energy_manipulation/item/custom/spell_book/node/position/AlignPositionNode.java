package com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.position;

import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.Nodes;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.AbstractNodeWithMap;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.SubNode;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.ValueTypeNode;
import net.minecraft.util.math.Vec3d;

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


    /*----------------------------------------------------------------------------------------------------------------*/
    /* PositionNode Interface */

    @Override
    public Vec3d getPosition(Vec3d initialPosition) {
        double x = Math.floor(initialPosition.x);
        double y = Math.floor(initialPosition.x);
        double z = Math.floor(initialPosition.x);
        return new Vec3d(x, y, z);
    }

    /*----------------------------------------------------------------------------------------------------------------*/
}

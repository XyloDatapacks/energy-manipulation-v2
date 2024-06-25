package com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.shape;

import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.Nodes;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.AbstractNode;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.AbstractNodeWithMap;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.GenericNode;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.SubNode;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.effect.EffectProviderNode;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.instruction.ModifyPositionInstructionNode;

import java.util.function.Supplier;


public class RayShapeNode extends AbstractNodeWithMap implements ShapeNode {
    SubNode<EffectProviderNode> effects = registerSubNode("effects", new SubNode.Builder<EffectProviderNode>()
            .addNodeValues(
                    Nodes.EFFECT_PROVIDER.identifier())
            .build(this));
    
    public RayShapeNode() {
        super(Nodes.SHAPE_RAY);
    }

/*--------------------------------------------------------------------------------------------------------------------*/
    /* InstructionNode Interface */
    
    @Override
    public boolean shapeInit() {
        return false;
    }

    @Override
    public boolean shapeTick() {
        return false;
    }

    @Override
    public boolean shapeDestroy() {
        return false;
    }

/*--------------------------------------------------------------------------------------------------------------------*/
    
}

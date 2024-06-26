package com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.shape;

import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.Nodes;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.AbstractNodeWithMap;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.SubNode;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.effect.EffectProviderNode;

import java.util.List;


public class ProjectileShapeNode extends AbstractNodeWithMap implements ShapeNode {
    SubNode<EffectProviderNode> effects = registerSubNode("effects", new SubNode.Builder<EffectProviderNode>()
            .addNodeValues(List.of(
                    Nodes.EFFECT_PROVIDER))
            .build(this));
    
    
    public ProjectileShapeNode() {
        super(Nodes.SHAPE_PROJECTILE);
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

package com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.shape;

import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.Nodes;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.AbstractNode;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.AbstractNodeWithMap;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.GenericNode;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.effect.EffectProviderNode;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.instruction.ModifyPositionInstructionNode;

import java.util.function.Supplier;


public class ProjectileShapeNode extends AbstractNodeWithMap implements ShapeNode {
    
    public ProjectileShapeNode() {
        super(Nodes.SHAPE_PROJECTILE);
        // movement
        this.registerSubNode("effects", EffectProviderNode.class, new EffectProviderNode());
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

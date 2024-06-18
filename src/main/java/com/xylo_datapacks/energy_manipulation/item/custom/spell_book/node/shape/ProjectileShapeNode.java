package com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.shape;

import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.AbstractNode;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.AbstractNodeWithMap;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.GenericNode;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.effect.EffectProviderNode;


public class ProjectileShapeNode extends AbstractNodeWithMap implements ShapeNode {
    
    public ProjectileShapeNode(GenericNode parentNode) {
        super("projectile_shape_node_name", "projectile_shape_node_description", parentNode);
        // movement
        this.registerSubNode("effects", EffectProviderNode.class, new EffectProviderNode(this));
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

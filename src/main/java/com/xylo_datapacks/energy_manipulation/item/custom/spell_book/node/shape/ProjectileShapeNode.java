package com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.shape;

import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.AbstractNode;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.effect.EffectProviderNode;


public class ProjectileShapeNode extends AbstractShapeNode {
    
    public ProjectileShapeNode() {
        super("projectile_shape_node_name", "projectile_shape_node_description");
        // movement
        this.registerSubNode("effects", EffectProviderNode.class);
        this.modifySubNode("effects", new EffectProviderNode());
    }

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

    @Override
    public AbstractNode getNodeParent() {
        return null;
    }
}

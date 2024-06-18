package com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.effect;

import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.AbstractNodeWithMap;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.GenericNode;

public class FireEffectNode extends AbstractNodeWithMap implements EffectNode {
    
    public FireEffectNode(GenericNode parentNode) {
        super("fire_effect_node_name", "fire_effect_node_description", parentNode);
    }
}

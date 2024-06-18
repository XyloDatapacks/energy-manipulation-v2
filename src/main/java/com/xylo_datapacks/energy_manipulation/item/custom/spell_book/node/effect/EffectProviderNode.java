package com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.effect;

import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.AbstractNode;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.AbstractNodeWithList;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.GenericNode;

public class EffectProviderNode extends AbstractNodeWithList<EffectNode> {
    
    public EffectProviderNode(GenericNode parentNode) {
        super("effect_provider_node_name", "effect_provider_node_description", parentNode, "effect");
        this.appendSubNode(new FireEffectNode(this));
        this.appendSubNode(new FireEffectNode(this));
        this.appendSubNode(new FireEffectNode(this));
    }
    
}

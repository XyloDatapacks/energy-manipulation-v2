package com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.effect;

import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.AbstractNodeWithList;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.GenericNode;

public class EffectProviderNode extends AbstractNodeWithList {
    
    public EffectProviderNode() {
        super("effect_provider", "effect");
        this.registerSubNode("effect", EffectNode.class, null);
        this.appendSubNode(new FireEffectNode());
        this.appendSubNode(new FireEffectNode());
        this.appendSubNode(new FireEffectNode());
    }
    
}

package com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.effect;

import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.Nodes;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.AbstractNode;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.AbstractNodeWithList;

import java.util.function.Supplier;

public class EffectProviderNode extends AbstractNodeWithList {
    
    public EffectProviderNode() {
        super(Nodes.EFFECT_PROVIDER);
        this.registerSubNode("effect", EffectNode.class, null);
        this.appendSubNode(new FireEffectNode());
        this.appendSubNode(new FireEffectNode());
        this.appendSubNode(new FireEffectNode());
    }
    
}

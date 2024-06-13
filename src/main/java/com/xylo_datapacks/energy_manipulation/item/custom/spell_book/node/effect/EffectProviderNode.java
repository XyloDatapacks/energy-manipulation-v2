package com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.effect;

import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.AbstractNode;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.AbstractNodeWithList;

public class EffectProviderNode extends AbstractNodeWithList<AbstractEffectNode> {
    
    public EffectProviderNode() {
        super("effect_provider_node_name", "effect_provider_node_description", "effect");
        this.appendSubNode(new FireEffectNode());
        this.appendSubNode(new FireEffectNode());
        this.appendSubNode(new FireEffectNode());
    }

    @Override
    public AbstractNode getNodeParent() {
        return null;
    }
}

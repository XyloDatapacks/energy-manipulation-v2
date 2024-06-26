package com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.effect;

import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.Nodes;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.AbstractNodeWithList;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.SubNode;

import java.util.List;

public class EffectProviderNode extends AbstractNodeWithList<EffectNode> {
    
    public EffectProviderNode() {
        super(Nodes.EFFECT_PROVIDER, "effect");
        
        this.appendSubNode(new SubNode.Builder<EffectNode>()
                .addNodeValues(List.of(
                        Nodes.EFFECT_BREAK,
                        Nodes.EFFECT_FIRE))
                .build(this));
        this.appendSubNode(new SubNode.Builder<EffectNode>()
                .addNodeValues(List.of(
                        Nodes.EFFECT_BREAK,
                        Nodes.EFFECT_FIRE))
                .build(this));
        this.appendSubNode(new SubNode.Builder<EffectNode>()
                .addNodeValues(List.of(
                        Nodes.EFFECT_BREAK,
                        Nodes.EFFECT_FIRE))
                .build(this));
    }
    
}

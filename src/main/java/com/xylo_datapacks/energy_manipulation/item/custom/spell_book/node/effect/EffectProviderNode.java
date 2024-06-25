package com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.effect;

import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.Nodes;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.AbstractNode;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.AbstractNodeWithList;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.SubNode;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.instruction.InstructionNode;

import java.util.function.Supplier;

public class EffectProviderNode extends AbstractNodeWithList<EffectNode> {
    
    public EffectProviderNode() {
        super(Nodes.EFFECT_PROVIDER, "effect");
        
        this.appendSubNode(new SubNode.Builder<EffectNode>()
                .addNodeValues(
                        Nodes.EFFECT_BREAK.identifier(),
                        Nodes.EFFECT_FIRE.identifier())
                .build(this));
        this.appendSubNode(new SubNode.Builder<EffectNode>()
                .addNodeValues(
                        Nodes.EFFECT_BREAK.identifier(),
                        Nodes.EFFECT_FIRE.identifier())
                .build(this));
        this.appendSubNode(new SubNode.Builder<EffectNode>()
                .addNodeValues(
                        Nodes.EFFECT_BREAK.identifier(),
                        Nodes.EFFECT_FIRE.identifier())
                .build(this));
    }
    
}

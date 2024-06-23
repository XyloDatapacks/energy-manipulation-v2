package com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.effect;

import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.AbstractNode;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.AbstractNodeWithMap;

import java.util.function.Supplier;

public class BreakEffectNode extends AbstractNodeWithMap implements EffectNode {
    public static Supplier<? extends AbstractNode> nodeSupplier = BreakEffectNode::new;
    
    public BreakEffectNode() {
        super("break", "effect");
    }
}

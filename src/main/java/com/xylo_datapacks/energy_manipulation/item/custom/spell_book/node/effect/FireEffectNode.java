package com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.effect;

import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.AbstractNode;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.AbstractNodeWithMap;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.GenericNode;

import java.util.function.Supplier;

public class FireEffectNode extends AbstractNodeWithMap implements EffectNode {
    public static Supplier<? extends AbstractNode> nodeSupplier = FireEffectNode::new;
    
    public FireEffectNode() {
        super("fire", "effect");
    }
}

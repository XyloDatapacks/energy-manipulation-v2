package com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.spell;

import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.Nodes;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.AbstractNodeWithMap;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.SubNode;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.records.NodeData;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.effect.EffectProviderNode;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.instruction.InstructionProviderNode;

import java.util.List;

public class SpellNode extends AbstractNodeWithMap {
    SubNode<InstructionProviderNode> program = registerSubNode("program", new SubNode.Builder<InstructionProviderNode>()
            .addNodeValues(List.of(
                    Nodes.INSTRUCTION_PROVIDER))
            .build(this));
    
    public SpellNode() {
        super(Nodes.SPELL);
    }
}

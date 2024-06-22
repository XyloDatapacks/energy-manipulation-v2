package com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.instruction;

import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.AbstractNodeWithList;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.effect.EffectNode;


public class InstructionProviderNode extends AbstractNodeWithList {


    public InstructionProviderNode() {
        super("instruction_provider", "instruction");
        this.registerSubNode("instruction", InstructionNode.class, null);
        this.appendSubNode(new ModifyPositionInstructionNode());
        this.appendSubNode(new ModifyPositionInstructionNode());
        this.appendSubNode(new GenerateShapeInstructionNode());
    }
    
}

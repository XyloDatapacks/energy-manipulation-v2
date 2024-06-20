package com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.instruction;

import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.AbstractNode;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.AbstractNodeWithList;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.GenericNode;


public class InstructionProviderNode extends AbstractNodeWithList<InstructionNode> {


    public InstructionProviderNode(GenericNode parentNode) {
        super("instruction_provider", "instruction", parentNode, "instruction");
        this.appendSubNode(new ModifyPositionInstructionNode(this));
        this.appendSubNode(new ModifyPositionInstructionNode(this));
        this.appendSubNode(new GenerateShapeInstructionNode(this));
    }
    
}

package com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.instruction;

import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.AbstractNode;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.AbstractNodeWithList;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.GenericNode;


public class InstructionProviderNode extends AbstractNodeWithList<InstructionNode> {


    public InstructionProviderNode(GenericNode parentNode) {
        super("page_node", "page_node_description", parentNode, "instruction_node");
        this.appendSubNode(new ModifyPositionInstructionNode(this));
        this.appendSubNode(new ModifyPositionInstructionNode(this));
        this.appendSubNode(new GenerateShapeInstructionNode(this));
    }
    
}

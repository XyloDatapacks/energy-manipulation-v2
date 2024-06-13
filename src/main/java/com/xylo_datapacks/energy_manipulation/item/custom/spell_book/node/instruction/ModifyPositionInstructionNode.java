package com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.instruction;

import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.AbstractNode;


public class ModifyPositionInstructionNode extends AbstractInstructionNode {
    
    public ModifyPositionInstructionNode() {
        super("position_instruction_node_name", "position_instruction_node_description");
        // position
    }

    @Override
    public boolean executeInstruction() {
        // do stuff
        return false;
    }

    @Override
    public AbstractNode getNodeParent() {
        return null;
    }
}

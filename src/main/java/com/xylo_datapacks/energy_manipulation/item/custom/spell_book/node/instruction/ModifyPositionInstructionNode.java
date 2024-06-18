package com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.instruction;

import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.AbstractNode;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.AbstractNodeWithMap;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.GenericNode;


public class ModifyPositionInstructionNode extends AbstractNodeWithMap implements InstructionNode {
    
    public ModifyPositionInstructionNode(GenericNode parentNode) {
        super("position_instruction_node_name", "position_instruction_node_description", parentNode);
        // position
    }

/*--------------------------------------------------------------------------------------------------------------------*/
    /* InstructionNode Interface */
    
    @Override
    public boolean executeInstruction() {
        // do stuff
        return false;
    }

/*--------------------------------------------------------------------------------------------------------------------*/
    
    
}

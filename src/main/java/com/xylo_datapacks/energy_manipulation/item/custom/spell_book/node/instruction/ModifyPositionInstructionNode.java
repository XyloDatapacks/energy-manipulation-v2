package com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.instruction;

import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.AbstractNodeWithMap;


public class ModifyPositionInstructionNode extends AbstractNodeWithMap implements InstructionNode {
    
    public ModifyPositionInstructionNode() {
        super("modify_position", "instruction");
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

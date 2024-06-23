package com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.instruction;

import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.Nodes;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.AbstractNode;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.AbstractNodeWithMap;

import java.util.function.Supplier;


public class ModifyPositionInstructionNode extends AbstractNodeWithMap implements InstructionNode {
    
    public ModifyPositionInstructionNode() {
        super(Nodes.INSTRUCTION_MODIFY_POSITION);
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

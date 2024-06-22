package com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.instruction;

import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.AbstractNode;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.AbstractNodeWithMap;

import java.util.function.Supplier;


public class ModifyPositionInstructionNode extends AbstractNodeWithMap implements InstructionNode {
    public static Supplier<? extends AbstractNode> nodeSupplier = ModifyPositionInstructionNode::new;
    
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

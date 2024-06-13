package com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.instruction;

import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.AbstractNodeWithMap;

//Annotation public protected private abstract static final synchronized native strictfp


public abstract class AbstractInstructionNode extends AbstractNodeWithMap {
    
    public AbstractInstructionNode(String nodeName, String nodeDescription) {
        super(nodeName, nodeDescription);
    }
    
    public abstract boolean executeInstruction();
}

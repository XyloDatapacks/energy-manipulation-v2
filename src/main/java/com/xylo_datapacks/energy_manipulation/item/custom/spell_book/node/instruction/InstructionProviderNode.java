package com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.instruction;

import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.Nodes;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.AbstractNode;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.AbstractNodeWithList;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.records.SubNodeData;

import java.util.List;
import java.util.function.Supplier;


public class InstructionProviderNode extends AbstractNodeWithList {
    
    public InstructionProviderNode() {
        super(Nodes.INSTRUCTION_PROVIDER);
        this.registerSubNode("instruction", InstructionNode.class, null);
        this.appendSubNode(new ModifyPositionInstructionNode());
        this.appendSubNode(new ModifyPositionInstructionNode());
        this.appendSubNode(new GenerateShapeInstructionNode());
    }
}

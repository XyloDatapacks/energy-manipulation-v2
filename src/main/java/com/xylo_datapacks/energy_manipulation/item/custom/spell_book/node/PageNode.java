package com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node;

import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.AbstractNode;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.AbstractNodeWithList;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.instruction.AbstractInstructionNode;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.instruction.GenerateShapeInstructionNode;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.instruction.ModifyPositionInstructionNode;


public class PageNode extends AbstractNodeWithList<AbstractInstructionNode> {


    public PageNode() {
        super("page_node", "page_node_description", "instruction_node");
        this.appendSubNode(new ModifyPositionInstructionNode());
        this.appendSubNode(new ModifyPositionInstructionNode());
        this.appendSubNode(new GenerateShapeInstructionNode());
    }

    @Override
    public AbstractNode getNodeParent() {
        return null;
    }

    
}

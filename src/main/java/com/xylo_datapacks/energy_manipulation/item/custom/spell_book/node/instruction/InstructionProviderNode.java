package com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.instruction;

import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.Nodes;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.AbstractNode;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.AbstractNodeWithList;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.SubNode;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.records.SubNodeData;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.shape.ShapeNode;

import java.util.List;
import java.util.function.Supplier;


public class InstructionProviderNode extends AbstractNodeWithList<InstructionNode> {
    
    public InstructionProviderNode() {
        super(Nodes.INSTRUCTION_PROVIDER, "instruction");
        
        this.appendSubNode(new SubNode.Builder<InstructionNode>()
                .addNodeValues(
                        Nodes.INSTRUCTION_GENERATE_SHAPE.identifier(),
                        Nodes.INSTRUCTION_MODIFY_POSITION.identifier())
                .build(this));
        this.appendSubNode(new SubNode.Builder<InstructionNode>()
                .addNodeValues(
                        Nodes.INSTRUCTION_GENERATE_SHAPE.identifier(),
                        Nodes.INSTRUCTION_MODIFY_POSITION.identifier())
                .build(this));
        this.appendSubNode(new SubNode.Builder<InstructionNode>()
                .addNodeValues(
                        Nodes.INSTRUCTION_GENERATE_SHAPE.identifier(),
                        Nodes.INSTRUCTION_MODIFY_POSITION.identifier())
                .build(this, Nodes.INSTRUCTION_GENERATE_SHAPE.identifier()));
    }
}

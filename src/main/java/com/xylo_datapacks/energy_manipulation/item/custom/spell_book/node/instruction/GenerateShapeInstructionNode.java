package com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.instruction;

import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.AbstractNode;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.shape.ProjectileShapeNode;

import java.util.List;
import java.util.Map;

public class GenerateShapeInstructionNode extends AbstractInstructionNode {
   
    public GenerateShapeInstructionNode() {
        super("generate_instruction_node_name", "generate_instruction_node_description");
        subNodes.put("shape", new ProjectileShapeNode());
    }

    @Override
    public boolean executeInstruction() {
        return false;
    }

    @Override
    public AbstractNode getNodeParent() {
        return null;
    }

    @Override
    public Map<String, String> getSubNodesDisplayData() {
        return Map.of();
    }
}

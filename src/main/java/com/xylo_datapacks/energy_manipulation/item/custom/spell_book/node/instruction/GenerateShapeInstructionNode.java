package com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.instruction;

import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.PageNode;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.AbstractNode;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.AbstractNodeWithList;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.effect.EffectProviderNode;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.shape.AbstractShapeNode;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.shape.ProjectileShapeNode;


public class GenerateShapeInstructionNode extends AbstractInstructionNode {
   
    public GenerateShapeInstructionNode() {
        super("generate_instruction_node_name", "generate_instruction_node_description");
        this.registerSubNode("shape", AbstractShapeNode.class);
        this.modifySubNode("shape", new ProjectileShapeNode());
    }

    @Override
    public boolean executeInstruction() {
        
        ProjectileShapeNode projectileShapeNode = this.getSubNode("shape", ProjectileShapeNode.class);
        return false;
    }

    @Override
    public AbstractNode getNodeParent() {
        return null;
    }
    
}

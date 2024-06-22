package com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.instruction;

import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.AbstractNode;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.AbstractNodeWithMap;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.GenericNode;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.effect.EffectProviderNode;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.shape.ProjectileShapeNode;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.shape.ShapeNode;

import java.util.function.Supplier;


public class GenerateShapeInstructionNode extends AbstractNodeWithMap implements InstructionNode {
    public static Supplier<? extends AbstractNode> nodeSupplier = GenerateShapeInstructionNode::new;
    
    public GenerateShapeInstructionNode() {
        super("generate_shape", "instruction");
        this.registerSubNode("shape", ShapeNode.class, new ProjectileShapeNode());
    }
    

/*--------------------------------------------------------------------------------------------------------------------*/
    /* InstructionNode Interface */

    @Override
    public boolean executeInstruction() {
        
        ProjectileShapeNode projectileShapeNode = this.getSubNode("shape", ProjectileShapeNode.class);
        return false;
    }

/*--------------------------------------------------------------------------------------------------------------------*/



}

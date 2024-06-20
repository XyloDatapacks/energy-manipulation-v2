package com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.database;

import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.AbstractNode;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.GenericNode;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.effect.EffectProviderNode;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.effect.FireEffectNode;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.instruction.GenerateShapeInstructionNode;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.instruction.InstructionProviderNode;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.instruction.ModifyPositionInstructionNode;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.shape.ProjectileShapeNode;

import java.util.HashMap;
import java.util.Map;

public class NodesRegister {
    public static Map<Class<? extends GenericNode>, NodeData>  NodesData = new HashMap<>();
    
    public NodesRegister() {
        
    }
}

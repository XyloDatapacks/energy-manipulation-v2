package com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node;

import com.xylo_datapacks.energy_manipulation.EnergyManipulation;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.effect.BreakEffectNode;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.effect.EffectNode;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.effect.EffectProviderNode;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.effect.FireEffectNode;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.instruction.GenerateShapeInstructionNode;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.instruction.InstructionNode;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.instruction.InstructionProviderNode;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.instruction.ModifyPositionInstructionNode;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.records.NodeData;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.records.SubNodeData;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.shape.ProjectileShapeNode;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.shape.RayShapeNode;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.shape.ShapeNode;
import net.minecraft.util.Identifier;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Nodes {
    public static Map<Identifier, NodeData> NODES = new HashMap<>();

    public static final NodeData EFFECT_BREAK = registerNode("effect", "break", new NodeData.NodeDataMaker("Break", "Breaks a block", BreakEffectNode::new), Map.of(
    ));
    public static final NodeData EFFECT_FIRE = registerNode("effect", "fire", new NodeData.NodeDataMaker("Fire", "Set on fire", FireEffectNode::new), Map.of(
    ));
    public static final NodeData EFFECT_PROVIDER = registerNode("effect", "effect_provider", new NodeData.NodeDataMaker("Effect Provider", "List of effects", EffectProviderNode::new), Map.of(
            "effect", new SubNodeData("Effect","A single effect", EffectNode.class, List.of(
                    Nodes.EFFECT_FIRE.identifier(),
                    Nodes.EFFECT_BREAK.identifier()
            ))
    ));
    
    public static final NodeData SHAPE_PROJECTILE = registerNode("shape", "projectile", new NodeData.NodeDataMaker("Projectile", "Projectile like spell", ProjectileShapeNode::new), Map.of(
            "effects", new SubNodeData("Effects","The effects to apply", EffectProviderNode.class, List.of(
                    Nodes.EFFECT_PROVIDER.identifier()
            ))
    ));
    public static final NodeData SHAPE_RAY = registerNode("shape", "ray", new NodeData.NodeDataMaker("Ray", "Ray like spell", RayShapeNode::new), Map.of(
            "effects", new SubNodeData("Effects","The effects to apply", EffectProviderNode.class, List.of(
                    Nodes.EFFECT_PROVIDER.identifier()
            ))
    ));
    public static final NodeData INSTRUCTION_GENERATE_SHAPE = registerNode("instruction", "generate_shape", new NodeData.NodeDataMaker("Generate Shape", "Generates a shape", GenerateShapeInstructionNode::new), Map.of(
            "shape", new SubNodeData("Shape","The shape to generate", ShapeNode.class, List.of(
                    Nodes.SHAPE_PROJECTILE.identifier(),
                    Nodes.SHAPE_RAY.identifier()
            ))
    ));
    
    public static final NodeData INSTRUCTION_MODIFY_POSITION = registerNode("instruction", "modify_position", new NodeData.NodeDataMaker("Modify Position", "Changes the position context", ModifyPositionInstructionNode::new), Map.of(
    ));
    public static final NodeData INSTRUCTION_PROVIDER = registerNode("instruction", "instruction_provider", new NodeData.NodeDataMaker("Instruction Provider", "List of instructions", InstructionProviderNode::new), Map.of(
            "instruction", new SubNodeData("Instruction","A single instruction", InstructionNode.class, List.of(
                    Nodes.INSTRUCTION_GENERATE_SHAPE.identifier(),
                    Nodes.INSTRUCTION_MODIFY_POSITION.identifier()
            ))
    ));


    // Function called to add nodes
    public static NodeData registerNode(String groupId, String nodeId, NodeData.NodeDataMaker nodeDataMaker, Map<String, SubNodeData> subNodeData) {
        Identifier id = Identifier.of(EnergyManipulation.MOD_ID, groupId + "." + nodeId);
        NodeData newNodeData = new NodeData.Builder(id, nodeDataMaker).addSubNodes(subNodeData).build();
        NODES.put(id, newNodeData);
        return newNodeData;
    }
}

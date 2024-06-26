package com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node;

import com.xylo_datapacks.energy_manipulation.EnergyManipulation;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.GenericNode;
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
    public static Map<Identifier, NodeData<? extends GenericNode>> NODES = new HashMap<>();
    public static Map<Identifier, Map<String, SubNodeData>> SUB_NODES = new HashMap<>();

    public static final NodeData<GenericNode> INSTRUCTION_PROVIDER = registerNode("instruction", "instruction_provider", new NodeData.NodeDataMaker<>("Instruction Provider", "List of instructions", GenericNode.class, InstructionProviderNode::new));
    public static final NodeData<GenerateShapeInstructionNode> INSTRUCTION_GENERATE_SHAPE = registerNode("instruction", "generate_shape", new NodeData.NodeDataMaker<>("Generate Shape", "Generates a shape", GenerateShapeInstructionNode.class, GenerateShapeInstructionNode::new));
    public static final NodeData<ModifyPositionInstructionNode> INSTRUCTION_MODIFY_POSITION = registerNode("instruction", "modify_position", new NodeData.NodeDataMaker<>("Modify Position", "Changes the position context", ModifyPositionInstructionNode.class, ModifyPositionInstructionNode::new));

    public static final NodeData<ProjectileShapeNode> SHAPE_PROJECTILE = registerNode("shape", "projectile", new NodeData.NodeDataMaker<>("Projectile", "Projectile like spell", ProjectileShapeNode.class, ProjectileShapeNode::new));
    public static final NodeData<RayShapeNode> SHAPE_RAY = registerNode("shape", "ray", new NodeData.NodeDataMaker<>("Ray", "Ray like spell", RayShapeNode.class, RayShapeNode::new));

    public static final NodeData<EffectProviderNode> EFFECT_PROVIDER = registerNode("effect", "effect_provider", new NodeData.NodeDataMaker<>("Effect Provider", "List of effects", EffectProviderNode.class, EffectProviderNode::new));
    public static final NodeData<BreakEffectNode> EFFECT_BREAK = registerNode("effect", "break", new NodeData.NodeDataMaker<>("Break", "Breaks a block", BreakEffectNode.class, BreakEffectNode::new));
    public static final NodeData<FireEffectNode> EFFECT_FIRE = registerNode("effect", "fire", new NodeData.NodeDataMaker<>("Fire", "Set on fire", FireEffectNode.class, FireEffectNode::new));

    
    
    public static final Map<String, SubNodeData> INSTRUCTION_PROVIDER_SUB_NODES = registerSubNode(Nodes.INSTRUCTION_PROVIDER.identifier(), Map.of(
            "instruction", new SubNodeData("Instruction","A single instruction", InstructionNode.class, List.of(
                    Nodes.INSTRUCTION_GENERATE_SHAPE.identifier(),
                    Nodes.INSTRUCTION_MODIFY_POSITION.identifier()
            ))
    ));
    public static final Map<String, SubNodeData> INSTRUCTION_GENERATE_SHAPE_SUB_NODES = registerSubNode(Nodes.INSTRUCTION_GENERATE_SHAPE.identifier(), Map.of(
            "shape", new SubNodeData("Shape","The shape to generate", ShapeNode.class, List.of(
                    Nodes.SHAPE_PROJECTILE.identifier(),
                    Nodes.SHAPE_RAY.identifier()
            ))
    ));
    public static final Map<String, SubNodeData> INSTRUCTION_MODIFY_POSITION_SUB_NODES = registerSubNode(Nodes.INSTRUCTION_MODIFY_POSITION.identifier(), Map.of());


    public static final Map<String, SubNodeData> SHAPE_PROJECTILE_SUB_NODES = registerSubNode(Nodes.SHAPE_PROJECTILE.identifier(), Map.of(
            "effects", new SubNodeData("Effects","The effects to apply", EffectProviderNode.class, List.of(
                    Nodes.EFFECT_PROVIDER.identifier()
            ))
    ));
    public static final Map<String, SubNodeData> SHAPE_RAY_SUB_NODES = registerSubNode(Nodes.SHAPE_RAY.identifier(), Map.of(
            "effects", new SubNodeData("Effects","The effects to apply", EffectProviderNode.class, List.of(
                    Nodes.EFFECT_PROVIDER.identifier()
            ))
    ));


    public static final Map<String, SubNodeData> EFFECT_PROVIDER_SUB_NODES = registerSubNode(Nodes.EFFECT_PROVIDER.identifier(), Map.of(
            "effect", new SubNodeData("Effect","A single effect", EffectNode.class, List.of(
                    Nodes.EFFECT_FIRE.identifier(),
                    Nodes.EFFECT_BREAK.identifier()
            ))
    ));
    public static final Map<String, SubNodeData> EFFECT_BREAK_SUB_NODES = registerSubNode(Nodes.EFFECT_BREAK.identifier(), Map.of());
    public static final Map<String, SubNodeData> EFFECT_FIRE_SUB_NODES = registerSubNode(Nodes.EFFECT_FIRE.identifier(), Map.of());




    // Function called to add nodes
    public static <T extends GenericNode> NodeData<T> registerNode(String groupId, String nodeId, NodeData.NodeDataMaker<T> nodeDataMaker) {
        Identifier id = Identifier.of(EnergyManipulation.MOD_ID, groupId + "." + nodeId);
        NodeData<T> newNodeData = new NodeData.Builder<T>(id, nodeDataMaker).build();
        NODES.put(id, newNodeData);
        return newNodeData;
    }

    // Function called to add nodes
    public static Map<String, SubNodeData> registerSubNode(Identifier parentNodeId, Map<String, SubNodeData> subNodeData) {
        SUB_NODES.put(parentNodeId, subNodeData);
        return subNodeData;
    }
}

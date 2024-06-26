package com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node;

import com.xylo_datapacks.energy_manipulation.EnergyManipulation;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.GenericNode;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.effect.BreakEffectNode;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.effect.EffectProviderNode;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.effect.FireEffectNode;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.instruction.GenerateShapeInstructionNode;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.instruction.InstructionProviderNode;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.instruction.ModifyPositionInstructionNode;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.records.NodeData;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.records.SubNodeData;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.shape.ProjectileShapeNode;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.shape.RayShapeNode;
import net.minecraft.util.Identifier;

import java.util.HashMap;
import java.util.Map;

public class Nodes {
    public static Map<Identifier, NodeData<? extends GenericNode>> NODES = new HashMap<>();

    /** Instructions */
    public static final NodeData<GenericNode> INSTRUCTION_PROVIDER = registerNode("instruction", "instruction_provider", new NodeData.NodeDataMaker<>("Instruction Provider", "List of instructions", InstructionProviderNode::new, Map.of(
            "instruction", new SubNodeData("Instruction","A single instruction")
    )));
    public static final NodeData<GenerateShapeInstructionNode> INSTRUCTION_GENERATE_SHAPE = registerNode("instruction", "generate_shape", new NodeData.NodeDataMaker<>("Generate Shape", "Generates a shape", GenerateShapeInstructionNode::new, Map.of(
            "shape", new SubNodeData("Shape","The shape to generate")
    )));
    public static final NodeData<ModifyPositionInstructionNode> INSTRUCTION_MODIFY_POSITION = registerNode("instruction", "modify_position", new NodeData.NodeDataMaker<>("Modify Position", "Changes the position context", ModifyPositionInstructionNode::new, Map.of(
            
    )));

    /** Shapes */
    public static final NodeData<ProjectileShapeNode> SHAPE_PROJECTILE = registerNode("shape", "projectile", new NodeData.NodeDataMaker<>("Projectile", "Projectile like spell", ProjectileShapeNode::new, Map.of(
            "effects", new SubNodeData("Effects","The effects to apply")
    )));
    public static final NodeData<RayShapeNode> SHAPE_RAY = registerNode("shape", "ray", new NodeData.NodeDataMaker<>("Ray", "Ray like spell", RayShapeNode::new, Map.of(
            "effects", new SubNodeData("Effects","The effects to apply")
    )));

    /** Effects */
    public static final NodeData<EffectProviderNode> EFFECT_PROVIDER = registerNode("effect", "effect_provider", new NodeData.NodeDataMaker<>("Effect Provider", "List of effects", EffectProviderNode::new, Map.of(
            "effect", new SubNodeData("Effect","A single effect")
    )));
    public static final NodeData<BreakEffectNode> EFFECT_BREAK = registerNode("effect", "break", new NodeData.NodeDataMaker<>("Break", "Breaks a block", BreakEffectNode::new, Map.of(
            
    )));
    public static final NodeData<FireEffectNode> EFFECT_FIRE = registerNode("effect", "fire", new NodeData.NodeDataMaker<>("Fire", "Set on fire", FireEffectNode::new, Map.of(
            
    )));



    // Function called to add nodes
    public static <T extends GenericNode> NodeData<T> registerNode(String groupId, String nodeId, NodeData.NodeDataMaker<T> nodeDataMaker) {
        Identifier id = Identifier.of(EnergyManipulation.MOD_ID, groupId + "." + nodeId);
        NodeData<T> newNodeData = new NodeData.Builder<T>(id, nodeDataMaker).build();
        NODES.put(id, newNodeData);
        return newNodeData;
    }
}

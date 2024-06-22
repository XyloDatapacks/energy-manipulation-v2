package com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node;

import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.AbstractNode;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.GenericNode;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.NodeResult;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.database.NodeData;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.effect.BreakEffectNode;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.effect.EffectProviderNode;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.instruction.GenerateShapeInstructionNode;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.instruction.InstructionProviderNode;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.shape.RayShapeNode;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.function.Supplier;

public class test {
    
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException {

        /*TypeToken<Map<String, NodeData>> listType = new TypeToken<Map<String, NodeData>>() {};
        Map<String, NodeData> target = new HashMap<>();
        target.put("shape.projectile", new NodeData("projectile", "projectile shape"));
        target.put("effect.fire", new NodeData("fire", "fire effect"));
        target.put("instruction.generate_shape", new NodeData("generate_shape", "generate_shape instruction"));

        Gson gson = new Gson();
        // For serialization you normally do not have to specify the type, Gson will use
        // the runtime type of the objects, however you can also specify it explicitly
        String json = gson.toJson(target, listType.getType());
        
        // But for deserialization you have to specify the type
        Map<String, NodeData> target2 = gson.fromJson(json, listType);
        */

        Map<String, NodeData> nodesMap = new HashMap<>();
        nodesMap.put("effect.break", new NodeData("Break", "Breaks a block"));
        nodesMap.put("effect.effect_provider", new NodeData("Effect Provider", "Provides effects"));
        nodesMap.put("effect.fire", new NodeData("Fire", "Sets target on fire"));
        nodesMap.put("instruction.generate_shape", new NodeData("Generate Shape", "Generates a shape"));
        nodesMap.put("instruction.instruction_provider", new NodeData("Instruction Provider", "Provides Instructions"));
        nodesMap.put("instruction.modify_position", new NodeData("Modify Position", "Changes the context position"));
        nodesMap.put("shape.projectile", new NodeData("Projectile", "Projectile shaped spell"));
        nodesMap.put("shape.ray", new NodeData("Ray", "Ray shaped spell"));

        Map<String, NodeData> subNodesMap = new HashMap<>();
        subNodesMap.put("instruction.instruction_provider.instruction", new NodeData("Instruction", "A single instruction"));
        subNodesMap.put("instruction.generate_shape.shape", new NodeData("Shape", "A single shape"));
        subNodesMap.put("shape.projectile.effects", new NodeData("Effects", "A list of effects"));
        subNodesMap.put("shape.ray.effects", new NodeData("Effects", "A list of effects"));
        subNodesMap.put("effect.effect_provider.effect", new NodeData("Effect", "A single effect"));
        
        
        InstructionProviderNode pageNode = new InstructionProviderNode();
        printNodes(pageNode.getAllSubNodesRecursive(), subNodesMap, nodesMap);

        System.out.println();
        // modify shape node
        modifyNodeAtPath(pageNode, "instruction_node[2].shape", new RayShapeNode());
        // modify effect
        modifyNodeAtPath(pageNode, "instruction_node[2].shape.effects.effect[1]", new BreakEffectNode());

        System.out.println();
        printNodes(pageNode.getAllSubNodesRecursive(), subNodesMap, nodesMap);

        System.out.println();
        printNodeFromPath(pageNode, "instruction_node[2].shape.effects");

        List<Class<? extends AbstractNode>> list = new ArrayList<>();
        list.add(BreakEffectNode.class);
        list.add(EffectProviderNode.class);
        System.out.println(BreakEffectNode.nodeSupplier.get().getNodeId());
        System.out.println(EffectProviderNode.nodeSupplier.get().getNodeId());
        System.out.println(list.get(0).getConstructor().newInstance().getNodeId());
        Supplier<? extends AbstractNode> a = ((Supplier<? extends AbstractNode>) list.get(0).getMethod("getNodeSupplier").invoke(null));
        System.out.println(a.get().getNodeId());
    }

    private static void modifyNodeAtPath(GenericNode startingNode,  String path, GenericNode newValue) {
       startingNode.modifyNodeFromPath(path, newValue);
    }

    /** prints id of the node at that path */
    private static void printNodeFromPath(GenericNode node,  String path) {
        GenericNode nodeFound = node.getNodeFromPath(path);
        if (nodeFound != null) {
            System.out.println("found node:" + nodeFound.getNodeId());
        }
    }

    /** prints path : nodeId */
    private static void printRawNodes(List<NodeResult> nodes) {
        for (NodeResult nodeResult : nodes) {
            System.out.println(GenericNode.listPathToStringPath(nodeResult.path().list()) + " : " + nodeResult.node().getNodeId());
        }
    }

    /** prints all info */
    private static void printNodes(List<NodeResult> nodes, Map<String, NodeData> subNodesMap, Map<String, NodeData> nodesMap) {
        for (NodeResult nodeResult : nodes) {
            GenericNode node = nodeResult.node();
            GenericNode parentNode = node.getParentNode();
            String id = nodeResult.path().id();

            //System.out.println("subnode key:" + parentNode.getNodeFullId() + "." + id);
            NodeData subNodeData = subNodesMap.get(parentNode.getNodeFullId() + "." + id);
            NodeData nodeData = nodesMap.get(node.getNodeFullId());
            if (subNodeData != null && nodeData != null) {
                System.out.println(subNodeData.name() + " : " + nodeData.name() + " -> [" + subNodeData.name() + " : " + subNodeData.description() + " ; " + nodeData.name() + " : " + nodeData.description() + "]");
            }
            else {
                System.out.println("errore: [" + "subNode: " + parentNode.getNodeFullId() + "." + id + " ; Node: " + node.getNodeFullId() + "]");
            }
        }
    }
    
}

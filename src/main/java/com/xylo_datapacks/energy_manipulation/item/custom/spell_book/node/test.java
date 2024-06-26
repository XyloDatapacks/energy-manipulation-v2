package com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node;

import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.GenericNode;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.instruction.GenerateShapeInstructionNode;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.records.NodeResult;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.records.NodeData;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.records.SubNodeData;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.effect.BreakEffectNode;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.instruction.InstructionProviderNode;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.shape.RayShapeNode;
import net.minecraft.util.Identifier;
import org.w3c.dom.Node;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

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

        GenerateShapeInstructionNode generateShapeInstructionNode = new GenerateShapeInstructionNode();
        printNodes(generateShapeInstructionNode.getAllSubNodesRecursive());
        
        /*InstructionProviderNode pageNode = new InstructionProviderNode();
        printNodes(pageNode.getAllSubNodesRecursive());

        System.out.println();
        // modify shape node
        modifyNodeAtPath(pageNode, "instruction_node[2].shape", Nodes.SHAPE_RAY.identifier());
        // modify effect
        modifyNodeAtPath(pageNode, "instruction_node[2].shape.effects.effect[1]", Nodes.EFFECT_BREAK.identifier());

        System.out.println();
        printNodes(pageNode.getAllSubNodesRecursive());

        System.out.println();
        printNodeFromPath(pageNode, "instruction_node[2].shape.effects");
        
        System.out.println(Nodes.SHAPE_PROJECTILE.identifier());
        RayShapeNode rayShapeNode = ((RayShapeNode) Nodes.SHAPE_RAY.nodeSupplier().get());
        System.out.println(Nodes.NODES.get(rayShapeNode.getNodeIdentifier()).identifier());
    }

    private static void modifyNodeAtPath(GenericNode startingNode, String path, Identifier newNodeValueIdentifier) {
       startingNode.modifyNodeFromPath(path, newNodeValueIdentifier);
       
         */
    }

    /** prints id of the node at that path */
    private static void printNodeFromPath(GenericNode node,  String path) {
        GenericNode nodeFound = node.getNodeFromPath(path);
        if (nodeFound != null) {
            System.out.println("found node:" + nodeFound.getNodeIdentifier());
        }
    }

    /** prints path : nodeId */
    private static void printRawNodes(List<NodeResult> nodes) {
        for (NodeResult nodeResult : nodes) {
            System.out.println(GenericNode.listPathToStringPath(nodeResult.path().list()) + " : " + nodeResult.node().getNodeIdentifier());
        }
    }

    /** prints all info */
    private static void printNodes(List<NodeResult> nodes) {
        for (NodeResult nodeResult : nodes) {
            GenericNode node = nodeResult.node();
            GenericNode parentNode = node.getParentNode();
            String id = nodeResult.path().id();

            //System.out.println("subnode key:" + parentNode.getNodeFullId() + "." + id);
            NodeData nodeData = Nodes.NODES.get(node.getNodeIdentifier());
            if (nodeData != null) {
                SubNodeData subNodeData = Nodes.SUB_NODES.get(parentNode.getNodeIdentifier()).get(id);
                if (subNodeData != null) {
                    System.out.println(subNodeData.name() + " : " + nodeData.name() + " -> [" + subNodeData.name() + " : " + subNodeData.description() + " ; " + nodeData.name() + " : " + nodeData.description() + "]");
                } else {
                    System.out.println("null" + " : " + nodeData.name() + " -> [" + "null" + " : " + "null" + " ; " + nodeData.name() + " : " + nodeData.description() + "]");
                }
            }
            else {
                System.out.println("errore: [" + "subNode: " + parentNode.getNodeIdentifier() + "." + id + " ; Node: " + node.getNodeIdentifier() + "]");
            }
        }
    }
    
}

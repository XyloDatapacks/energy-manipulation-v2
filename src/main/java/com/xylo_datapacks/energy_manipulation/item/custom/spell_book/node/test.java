package com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node;

import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.gui.GuiManager;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.GenericNode;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.records.NodeResult;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.instruction.InstructionProviderNode;
import net.minecraft.util.Identifier;

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
        
        
        offset: directional
            forward: float provider
                value: 4.5
            right: float provider
                value: 5.3
            up: float provider
                value: 9.8
        
        float provider extends NumberNode interface
        value is a subNode i guess, but that would also mean that the value has to be set through identifier. so it is a bad idea
        instead of returning a subnode i return the value
        
        
        */

        
        InstructionProviderNode pageNode = new InstructionProviderNode();
        GuiManager.printAll(pageNode.getAllSubNodesRecursive());

        System.out.println();
        // modify position node
        modifyNodeAtPath(pageNode, "instruction_node[1].position", Nodes.POSITION_OFFSET.identifier());
        // modify shape node
        modifyNodeAtPath(pageNode, "instruction_node[2].shape", Nodes.SHAPE_RAY.identifier());
        // modify effect
        modifyNodeAtPath(pageNode, "instruction_node[2].shape.effects.effect[1]", Nodes.EFFECT_BREAK.identifier());

        System.out.println();
        GuiManager.printAll(pageNode.getAllSubNodesRecursive());

        System.out.println();
        printNodeFromPath(pageNode, "instruction_node[2].shape.effects");
    }
    
    
    
    
    
    
    private static void modifyNodeAtPath(GenericNode startingNode, String path, Identifier newNodeValueIdentifier) {
       startingNode.modifyNodeFromPath(path, newNodeValueIdentifier);
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
    
}

package com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node;

import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.GenericNode;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.effect.EffectProviderNode;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.effect.FireEffectNode;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.instruction.InstructionProviderNode;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.instruction.ModifyPositionInstructionNode;
import net.minecraft.util.Pair;

import java.util.List;

public class test {
    
    public static void main(String[] args) {
        
        
        
        InstructionProviderNode pageNode = new InstructionProviderNode(null);
        List<Pair<String, GenericNode>> nodes = pageNode.getAllSubNodesIterative();
        for (Pair<String, GenericNode> node : nodes) {
                System.out.println(node.getLeft() + " : " + node.getRight().getNodeName());
        }

        System.out.println();
        GenericNode targetNode = pageNode.getAllSubNodes().get(2).getRight().getAllSubNodes().get(0).getRight().getAllSubNodes().get(0).getRight();
        System.out.println("target node:" +  targetNode.getNodeName());
        ((EffectProviderNode) targetNode).removeLastSubNode();
        System.out.println();

        List<Pair<String, GenericNode>> nodes2 = pageNode.getAllSubNodesIterative();
        for (Pair<String, GenericNode> node : nodes2) {
            System.out.println(node.getLeft() + " : " + node.getRight().getNodeName());
        }
    }
}

package com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node;

import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.AbstractNode;
import net.minecraft.util.Pair;

import java.util.List;

public class test {
    
    public static void main(String[] args) {
        
        
        
        PageNode pageNode = new PageNode();
        List<Pair<String, AbstractNode>> nodes = pageNode.getAllSubNodesDisplayData();
        for (Pair<String, AbstractNode> node : nodes) {
                System.out.println(node.getLeft() + " : " + node.getRight().getNodeName());
        }
    }
}

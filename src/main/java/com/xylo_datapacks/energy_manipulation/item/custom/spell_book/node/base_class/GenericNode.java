package com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class;

import net.minecraft.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public interface GenericNode {

    public abstract String getNodeName();
    public abstract String getNodeDescription();

    public abstract GenericNode getParentNode();
    public abstract Integer getNesting();
    
    public abstract List<Pair<String, GenericNode>> getAllSubNodes();
    public abstract List<Pair<String, GenericNode>> getAllSubNodesRecursive(String pathStart);
    public default List<Pair<String, GenericNode>> getAllSubNodesRecursive() {
        return getAllSubNodesRecursive("");
    };
   
    public static List<String> stringPathToListPath(String path) {
        return new ArrayList<String>(Arrays.asList(path.split("\\.")));
    };
    public abstract GenericNode getNodeFromPath(List<String> path);
    public default GenericNode getNodeFromPath(String path) {
        return getNodeFromPath(stringPathToListPath(path));
    };
    
    public abstract NodeDisplayData getNodeDisplayData();
}

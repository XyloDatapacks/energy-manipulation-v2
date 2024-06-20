package com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class;

import net.minecraft.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public interface GenericNode {

    public abstract String getNodeId();
    public abstract String getNodeGroupId();
    public default String getNodeFullId() {
        return getNodeGroupId() + "." + getNodeId();
    };

    public abstract GenericNode getParentNode();
    public abstract Integer getNesting();
    
    public abstract List<NodeResult> getAllSubNodes();
    public abstract List<NodeResult> getAllSubNodesRecursive(List<String> pathStart);
    public default List<NodeResult> getAllSubNodesRecursive(String pathStart) {
        return getAllSubNodesRecursive(stringPathToListPath(pathStart));
    };
    public default List<NodeResult> getAllSubNodesRecursive() {
        return getAllSubNodesRecursive(new ArrayList<>());
    };
   
    public static List<String> stringPathToListPath(String path) {
        return new ArrayList<String>(Arrays.asList(path.split("\\.")));
    };
    public static String listPathToStringPath(List<String> path) {
        return String.join(".", path);
    };
    public static int stripIndexFromPathElement(String pathElement) {
        int indexStart = pathElement.indexOf("[") + 1;
        int indexEnd = pathElement.length() - 1;
        return Integer.parseInt(pathElement.substring(indexStart, indexEnd));
    }
    public abstract GenericNode getNodeFromPath(List<String> path);
    public default GenericNode getNodeFromPath(String path) {
        return getNodeFromPath(stringPathToListPath(path));
    };
    
    public abstract NodeDisplayData getNodeDisplayData();
}

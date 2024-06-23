package com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class;

import com.xylo_datapacks.energy_manipulation.EnergyManipulation;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.records.NodeData;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.records.NodeResult;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.records.SubNodeData;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public interface GenericNode {

    // node Id
    public abstract String getNodeId();
    public abstract String getNodeGroupId();
    public default String getNodeFullId() {
        return getNodeGroupId() + "." + getNodeId();
    };
    public default Identifier getNodeIdentifier() {
        return Identifier.of(EnergyManipulation.MOD_ID, getNodeGroupId() + "." + getNodeId());
    };
    public default Identifier getSubNodeIdentifier(String subNodeId) {
        return Identifier.of(EnergyManipulation.MOD_ID, getNodeGroupId() + "." + getNodeId() + "." + subNodeId);
    };

    /** get data of this node */
    public abstract NodeData getNodeData();
    /** get data of this sub node */
    public abstract SubNodeData getSubNodeData(List<String> path);
    public default SubNodeData getSubNodeData(String path) {
        return getSubNodeData(stringPathToListPath(path));
    }
    
    /** get parent node */
    public abstract GenericNode getParentNode();

    /** get all sub nodes of this node */
    public abstract List<NodeResult> getAllSubNodes();

    /** get all sub nodes and their sub nodes */
    public abstract List<NodeResult> getAllSubNodesRecursive(List<String> pathStart);
    /** get all sub nodes and their sub nodes */
    public default List<NodeResult> getAllSubNodesRecursive(String pathStart) {
        return getAllSubNodesRecursive(stringPathToListPath(pathStart));
    };
    /** get all sub nodes and their sub nodes */
    public default List<NodeResult> getAllSubNodesRecursive() {
        return getAllSubNodesRecursive(new ArrayList<>());
    };
    
    /** modify a sub node using a one element path */
    public abstract GenericNode getSubNode(String path);
    /** get a node from a path relative to this node. an empty path returns this node */
    public abstract GenericNode getNodeFromPath(List<String> path);
    /** get a node from a path relative to this node. an empty path returns this node */
    public default GenericNode getNodeFromPath(String path) {
        return getNodeFromPath(stringPathToListPath(path));
    };
    
    /** modify a sub node using a one element path */
    public abstract boolean modifySubNode(String path, GenericNode newNode);
    /** modify a node from a path relative to this node */
    public abstract boolean modifyNodeFromPath(List<String> path, GenericNode newNode);
    public default boolean modifyNodeFromPath(String path, GenericNode newNode) {
        return modifyNodeFromPath(stringPathToListPath(path), newNode);
    };




    /** split a sting path into a list path */
    public static List<String> stringPathToListPath(String path) {
        return new ArrayList<String>(Arrays.asList(path.split("\\.")));
    };
    /** join a list path into a string path */
    public static String listPathToStringPath(List<String> path) {
        return String.join(".", path);
    };
    /** extract the index of a path element, which is in between square brackets "something[index]" */
    public static int stripIndexFromPathElement(String pathElement) {
        int indexStart = pathElement.indexOf("[") + 1;
        int indexEnd = pathElement.length() - 1;
        return Integer.parseInt(pathElement.substring(indexStart, indexEnd));
    }
}

package com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class;

import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.Nodes;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.records.NodeData;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.records.NodePath;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.records.NodeResult;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.records.SubNodeData;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public interface GenericNode {

    // node Id
    public abstract Identifier getNodeIdentifier();
    // nbt
    public abstract NbtCompound toNbt();
    
    /** get data of this node */
    public abstract NodeData<? extends GenericNode> getNodeData();
    /** get data of this sub node */
    public abstract SubNodeData getSubNodeData(String subNodeId);
    
    
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


    /** get a node result from a path */
    public default NodeResult getNodeResultFromPath(List<String> path) {
        List<String> pathSaved = new ArrayList<>(path);
        return new NodeResult(new NodePath(pathSaved, pathSaved.get(pathSaved.size()-1)), getNodeFromPath(path));
    };
    /** get a node result from a path */
    public default NodeResult getNodeResultFromPath(String path) {
        return getNodeResultFromPath(stringPathToListPath(path));
    };
    
    
    /** get a sub node using a one element path */
    public abstract SubNode<? extends GenericNode> getSubNode(String path);
    /** get a node from a path relative to this node. an empty path returns this node */
    public abstract GenericNode getNodeFromPath(List<String> path);
    /** get a node from a path relative to this node. an empty path returns this node */
    public default GenericNode getNodeFromPath(String path) {
        return getNodeFromPath(stringPathToListPath(path));
    };
    
    
    /** modify a sub node using a one element path */
    public abstract boolean modifySubNode(String path, Identifier newSubNodeValueIdentifier);
    /** modify a node from a path relative to this node */
    public abstract boolean modifyNodeFromPath(List<String> path, Identifier newSubNodeValueIdentifier);
    public default boolean modifyNodeFromPath(String path, Identifier newSubNodeValueIdentifier) {
        return modifyNodeFromPath(stringPathToListPath(path), newSubNodeValueIdentifier);
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

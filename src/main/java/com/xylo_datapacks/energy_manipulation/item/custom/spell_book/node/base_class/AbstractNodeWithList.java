package com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class;

import net.minecraft.util.Pair;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractNodeWithList<T extends GenericNode> extends AbstractNode {
    private String subNodesId;
    private List<T> subNodes = new ArrayList<>();;
    
    public AbstractNodeWithList(String nodeName, String nodeDescription, GenericNode parentNode, String subNodesId) {
        super(nodeName, nodeDescription, parentNode);
        this.subNodesId = subNodesId;
    }
    
    public void appendSubNode(T subNode) {
        subNodes.add(subNodes.size(), subNode);
    }

    public void prependSubNode(T subNode) {
        subNodes.add(0, subNode);
    }

    public void modifySubNode(int index, T subNode) {
        subNodes.set(index, subNode);
    }
    
    public void insertSubNode(int index, T subNode) {
        subNodes.add(index, subNode);
    }

    public T removeSubNode(int index, T subNode) {
        return subNodes.remove(index);
    }
    
    public T removeFirstSubNode() {
        return subNodes.remove(0);
    }
    
    public T removeLastSubNode() {
        return subNodes.remove(subNodes.size() - 1);
    }

    public void removeAllSubNodes(T subNode) {
        subNodes.clear();
    }

    public T getSubNode(int subNodeIndex) {
        return subNodes.get(subNodeIndex);
    }

/*--------------------------------------------------------------------------------------------------------------------*/
    /* GenericNode Interface */
    
    @Override
    public List<Pair<String, GenericNode>> getAllSubNodes() {
        
        List<Pair<String, GenericNode>> returnSubNodes = new ArrayList<>();
        for (T subNode : subNodes) {
            // this sub node
            returnSubNodes.add(new Pair<String, GenericNode>(subNodesId, subNode));
        }
        return returnSubNodes;
    }

    @Override
    public List<Pair<String, GenericNode>> getAllSubNodesRecursive(String pathStart) {
       
        List<Pair<String, GenericNode>> returnSubNodes = new ArrayList<>();
        for (int index = 0; index < subNodes.size(); index++) {
            // generate path
            String subNodePath = (!pathStart.isEmpty() ? pathStart + "." : pathStart) + subNodesId + "[" + index + "]";
            // this sub node
            returnSubNodes.add(new Pair<String, GenericNode>(subNodePath, subNodes.get(index)));
            // recursive
            returnSubNodes.addAll(subNodes.get(index).getAllSubNodesRecursive(subNodePath));
        }
        return returnSubNodes;
    }

    @Override
    public GenericNode getNodeFromPath(List<String> path) {
        if (path.isEmpty()) return this;

        int indexStart = path.get(0).indexOf("[") + 1;
        int indexEnd = path.get(0).length() - 1;
        int index = Integer.parseInt(path.get(0).substring(indexStart, indexEnd));
        GenericNode node = this.getSubNode(index);
        
        if (node != null) {
            path.remove(0);
            return node.getNodeFromPath(path);
        }
        System.out.println("path failed at: " + path);
        return null;
    }

    /*--------------------------------------------------------------------------------------------------------------------*/
    
}

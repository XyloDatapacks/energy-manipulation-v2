package com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class;

import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.records.NodeData;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.records.NodePath;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.records.NodeResult;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.records.SubNodeData;

import java.util.*;

public abstract class AbstractNodeWithMap extends AbstractNode {
    private Map<String, GenericNode> subNodes = new LinkedHashMap<>();
    private Map<String, Class<? extends GenericNode>> subNodesClass = new LinkedHashMap<>();
    
    public AbstractNodeWithMap(String nodeId, String nodeGroupId) {
        super(nodeId, nodeGroupId);
    }

    public <T extends GenericNode> T getSubNode(String subNodeId, Class<T> subNodeClass) {
        GenericNode node = getSubNode(subNodeId);
        return subNodeClass.isInstance(node) ? subNodeClass.cast(node) : null;
    }

    /*----------------------------------------------------------------------------------------------------------------*/
    /* AbstractNode Interface */

    @Override
    protected <T extends GenericNode> boolean registerSubNode(String subNodeId, Class<T> subNodeClass, T defaultNode) {
        // if the defaultNode is not null and subNodeId is not in the classes map (not registered)
        if (defaultNode != null && !subNodesClass.containsKey(subNodeId)) {
            // we add the name and class to the class map
            subNodesClass.put(subNodeId, subNodeClass);
            // we add the name and node to the nodes map
            ((AbstractNode) defaultNode).setParentNode(this);
            subNodes.put(subNodeId, defaultNode);
            return true;
        }
        return false;
    }

    /*----------------------------------------------------------------------------------------------------------------*/

    /*----------------------------------------------------------------------------------------------------------------*/
    /* GenericNode Interface */

    @Override
    public NodeData getNodeData() {
        return null;
    }

    @Override
    public SubNodeData getSubNodeData(List<String> path) {
        return null;
    }

    @Override
    public List<NodeResult> getAllSubNodes() {
        
        List<NodeResult> returnSubNodes = new ArrayList<>();
        for (Map.Entry<String, GenericNode> subNode : subNodes.entrySet()) {
            // generate path
            List<String> subNodePath = new ArrayList<>(Collections.singletonList(subNode.getKey()));
            // this sub node
            returnSubNodes.add(new NodeResult(new NodePath(subNodePath, subNode.getKey()), subNode.getValue()));
        }
        return returnSubNodes;
    }
    
    @Override
    public List<NodeResult> getAllSubNodesRecursive(List<String> pathStart) {
        
        List<NodeResult> returnSubNodes = new ArrayList<>();
        for (Map.Entry<String, GenericNode> subNode : subNodes.entrySet()) {
            // generate path
            List<String> subNodePath = new ArrayList<>(pathStart);
            subNodePath.add(subNode.getKey());
            // this sub node
            returnSubNodes.add(new NodeResult(new NodePath(subNodePath, subNode.getKey()), subNode.getValue()));
            // recursive
            returnSubNodes.addAll(subNode.getValue().getAllSubNodesRecursive(subNodePath));
        }
        return returnSubNodes;
    }
    
    @Override
    public GenericNode getSubNode(String path) {
        return subNodes.get(path);
    }
    
    @Override
    public boolean modifySubNode(String path, GenericNode newNode) {
        // if the subNodeId is already registered
        if (subNodesClass.containsKey(path)) {
            // we check if the new node class is the same as the old one
            if (subNodesClass.get(path).isInstance(newNode)) {
                // since the new node class is the same as the old one, we can update the subNode value
                ((AbstractNode) newNode).setParentNode(this);
                subNodes.put(path, newNode);
                return true;
            }
        }
        return false;
    }

    /*----------------------------------------------------------------------------------------------------------------*/
    
}

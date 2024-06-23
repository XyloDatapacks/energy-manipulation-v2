package com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class;

import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.records.NodeData;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.records.NodePath;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.records.NodeResult;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.records.SubNodeData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class AbstractNodeWithList extends AbstractNode {
    private String subNodesId;
    private Class<? extends GenericNode> subNodesClass;
    private List<GenericNode> subNodes = new ArrayList<>();;
    
    public AbstractNodeWithList(String nodeId, String nodeGroupId) {
        super(nodeId, nodeGroupId);
    }

    public boolean appendSubNode(GenericNode subNode) {
        if (subNodesClass.isInstance(subNode)) {
            ((AbstractNode) subNode).setParentNode(this);
            subNodes.add(subNodes.size(), subNode);
            return true;
        }
        return false;
    }

    public GenericNode getSubNode(int index) {
        if (index >= 0 && index < subNodes.size()) {
            return subNodes.get(index);
        }
        return null;
    }

    public boolean modifySubNode(int index, GenericNode subNode) {
        if (index >= 0 && index < subNodes.size() && subNodesClass.isInstance(subNode)) {
            ((AbstractNode) subNode).setParentNode(this);
            subNodes.set(index, subNode);
            return true;
        }
        return false;
    }
    
    public boolean prependSubNode(GenericNode subNode) {
        if (subNodesClass.isInstance(subNode)) {
            ((AbstractNode) subNode).setParentNode(this);
            subNodes.add(0, subNode);
            return true;
        }
        return false;
    }
    
    public boolean insertSubNode(int index, GenericNode subNode) {
        if (index >= 0 && index < subNodes.size() && subNodesClass.isInstance(subNode)) {
            ((AbstractNode) subNode).setParentNode(this);
            subNodes.add(index, subNode);
            return true;
        }
        return false;
    }

    public GenericNode removeSubNode(int index, GenericNode subNode) {
        return subNodes.remove(index);
    }
    
    public GenericNode removeFirstSubNode() {
        return subNodes.remove(0);
    }
    
    public GenericNode removeLastSubNode() {
        return subNodes.remove(subNodes.size() - 1);
    }

    public void removeAllSubNodes(GenericNode subNode) {
        subNodes.clear();
    }

    /*----------------------------------------------------------------------------------------------------------------*/
    /* AbstractNode Interface */

    @Override
    protected <T extends GenericNode> boolean registerSubNode(String subNodeId, Class<T> subNodeClass, T defaultNode) {
        if (subNodesId == null || subNodesId.isEmpty()) {
            subNodesId = subNodeId;
            subNodesClass = subNodeClass;

            if (defaultNode != null) {
                appendSubNode(defaultNode);
            }
            return true;
        };
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
        for (int index = 0; index < subNodes.size(); index++) {
            // generate path
            List<String> subNodePath = new ArrayList<>(Collections.singletonList(subNodesId + "[" + index + "]"));
            // this sub node
            returnSubNodes.add(new NodeResult(new NodePath(subNodePath, subNodesId), subNodes.get(index)));
        }
        return returnSubNodes;
    }

    @Override
    public List<NodeResult> getAllSubNodesRecursive(List<String> pathStart) {
       
        List<NodeResult> returnSubNodes = new ArrayList<>();
        for (int index = 0; index < subNodes.size(); index++) {
            // generate path
            List<String> subNodePath = new ArrayList<>(pathStart);
            subNodePath.add(subNodesId + "[" + index + "]");
            // this sub node
            returnSubNodes.add(new NodeResult(new NodePath(subNodePath, subNodesId), subNodes.get(index)));
            // recursive
            returnSubNodes.addAll(subNodes.get(index).getAllSubNodesRecursive(subNodePath));
        }
        return returnSubNodes;
    }
    
    @Override
    public GenericNode getSubNode(String path) {
        return getSubNode(GenericNode.stripIndexFromPathElement(path));
    }

    @Override
    public boolean modifySubNode(String path, GenericNode newNode) {
        return modifySubNode(GenericNode.stripIndexFromPathElement(path), newNode);
    }

    /*----------------------------------------------------------------------------------------------------------------*/
    
}

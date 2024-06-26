package com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class;

import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.records.NodeData;
import net.minecraft.util.Identifier;
import net.minecraft.util.Pair;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class SubNode<T extends GenericNode> {
    private T node;
    private final Map<Identifier, Supplier<T>> nodeValues;
    private Identifier SelectedValueIdentifier;
    
    private SubNode(GenericNode parentNode, Map<Identifier, Supplier<T>> nodeValues, Identifier SelectedValue) {
        this.nodeValues = nodeValues;
        this.SelectedValueIdentifier = SelectedValue;
        this.node = nodeValues.get(SelectedValueIdentifier).get();
        ((AbstractNode) this.node).setParentNode(parentNode);
    }
    
    /**
     * @return node contained by this object
     */
    public T getNode() {
        return node;
    }

    /**
     * @param identifier the identifier of the possibleNodeValue to use
     * @return true if the operation was successful 
     */
    public boolean setNode(Identifier identifier, GenericNode parentNode) {
        if (this.nodeValues.containsKey(identifier)) {
            this.SelectedValueIdentifier = identifier;
            this.node = this.nodeValues.get(SelectedValueIdentifier).get();
            ((AbstractNode) this.node).setParentNode(parentNode);
            return true;
        }
        return false;
    }

    /**
     * @return map with node identifier as key, and node supplier as value
     */
    public Map<Identifier, Supplier<T>> getPossibleNodeValues() {
        return nodeValues;
    }

    /**
     * @return pair with selected node identifier as left, and selected node supplier as right
     */
    public Pair<Identifier, Supplier<T>> getSelectedValue() {
        if (nodeValues.containsKey(SelectedValueIdentifier)) {
            return new Pair<>(SelectedValueIdentifier, nodeValues.get(SelectedValueIdentifier));
        }
        return null;
    }
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    public static final class Builder<T extends GenericNode> {
        private final Map<Identifier, Supplier<T>> nodeValues = new HashMap<>();
        private Identifier selectedValueIdentifier;
        
        public Builder() {
        }

        /**
         * nodeData type (S) is defined by the class of the supplier it contains
         * @param nodeData node identifier of the node we want to add as possible value
         */
        public Builder<T> addNodeValues(List<NodeData<? extends T>> nodeData) {
            nodeData.forEach( singleNodeData -> { addNodeValue(singleNodeData, null); });
            return this;
        }

        /**
         * nodeData type (S) is defined by the class of the supplier it contains
         * @param nodeData node we want to add as possible value
         * @param customSupplier node supplier to use instead of the default one
         */
        public <S extends T> Builder<T> addNodeValue(NodeData<S> nodeData, Supplier<S> customSupplier) {
            this.nodeValues.put(nodeData.identifier(), (Supplier<T>) (customSupplier != null ? customSupplier : nodeData.nodeSupplier()) );
            return this;
        }

        /**
         * call to finalize the construction of the sub node
         * @param defaultValue identifier of one of the node values added. if not found, use first one instead
         */
        public SubNode<T> build(GenericNode parentNode, Identifier defaultValue) {
            if (nodeValues.containsKey(defaultValue)) {
                selectedValueIdentifier = defaultValue;
                return new SubNode<>(parentNode, nodeValues, selectedValueIdentifier);
            } 
           return build(parentNode);
        }

        /**
         * call to finalize the construction of the sub node
         */
        public SubNode<T> build(GenericNode parentNode) {
            selectedValueIdentifier = nodeValues.keySet().iterator().next();
            return new SubNode<>(parentNode, nodeValues, selectedValueIdentifier);
        }
    }
} 
package com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class;

import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.Nodes;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.effect.BreakEffectNode;
import net.minecraft.util.Identifier;
import net.minecraft.util.Pair;

import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class SubNode<T extends GenericNode> {
    private T node;
    private Map<Identifier, Supplier<T>> nodeValues;
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
            this.node = this.nodeValues.get(identifier).get();
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
    
    
    
    public static final class Builder<T extends GenericNode> {
        private Map<Identifier, Supplier<T>> nodeValues = new HashMap<>();
        private Identifier selectedValueIdentifier;
        private Class<Supplier<T>> clazz;
        
        public Builder() {
        }

        /**
         * @param identifiers node identifier of the node we want to add as possible value
         */
        public Builder<T> addNodeValues(Identifier ... identifiers) {
            Arrays.stream(identifiers).forEach( identifier -> { addNodeValue(identifier, null); });
            return this;
        }

        /**
         * @param identifier node identifier of the node we want to add as possible value
         * @param customSupplier node supplier to use instead of the default one
         */
        public Builder<T> addNodeValue(Identifier identifier, Supplier<T> customSupplier) {
            // get the default node supplier linked to the identifier
            Supplier<? extends GenericNode> defaultSupplier = Nodes.NODES.get(identifier).nodeSupplier();
            // check if the default supplier is of the required class
            if (true) {
                // if the node supplier provided is of the same class of the default one, use this one
                if (defaultSupplier.getClass().isInstance(customSupplier)) {
                    nodeValues.put(identifier, customSupplier);
                }
                // if the node supplier provided is of the WRONG class (relative to the identifier), use default
                else {
                    nodeValues.put(identifier, (Supplier<T>) defaultSupplier);
                }
            }
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
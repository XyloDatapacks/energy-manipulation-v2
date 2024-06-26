package com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.records;

import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.GenericNode;
import net.minecraft.util.Identifier;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public record NodeData<T extends GenericNode>(Identifier identifier, String name, String description, Class<T> nodeClass, Supplier<T> nodeSupplier) {
    
    public static final class Builder<T extends GenericNode> {
        Identifier identifier;
        String name;
        String description;
        Class<T> nodeClass;
        Supplier<T> nodeSupplier;

        public Builder(Identifier identifier, NodeDataMaker nodeDataMaker) {
            this.identifier = identifier;
            this.name = nodeDataMaker.name;
            this.description = nodeDataMaker.description;
            this.nodeClass = nodeDataMaker.nodeClass;
            this.nodeSupplier = nodeDataMaker.nodeSupplier;
        }

        public NodeData<T> build() {
            return new NodeData<T>(identifier, name, description, nodeClass, nodeSupplier);
        }
    }
    
    public record NodeDataMaker<T extends GenericNode>(String name, String description, Class<T> nodeClass, Supplier<T> nodeSupplier) {}
}

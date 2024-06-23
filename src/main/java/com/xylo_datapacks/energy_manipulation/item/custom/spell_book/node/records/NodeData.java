package com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.records;

import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.GenericNode;
import net.minecraft.util.Identifier;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public record NodeData(Identifier identifier, String name, String description, Supplier<? extends GenericNode> nodeSupplier, Map<Identifier, SubNodeData> subNodes) {
    
    public static final class Builder {
        Identifier identifier;
        String name;
        String description;
        Supplier<? extends GenericNode> nodeSupplier;
        Map<Identifier, SubNodeData> subNodes = new HashMap<>();

        public Builder(Identifier identifier, NodeDataMaker nodeDataMaker) {
            this.identifier = identifier;
            this.name = nodeDataMaker.name;
            this.description = nodeDataMaker.description;
            this.nodeSupplier = nodeDataMaker.nodeSupplier;
        }

        public Builder addSubNode(String name, SubNodeData subNodeData) {
            this.subNodes.put(new Identifier(this.identifier.toString() + "." + name), subNodeData);
            return this;
        }

        public Builder addSubNodes(Map<String, SubNodeData> subNodes) {
            for (Map.Entry<String, SubNodeData> entry : subNodes.entrySet()) {
                this.subNodes.put(new Identifier(this.identifier.toString() + "." + entry.getKey()), entry.getValue());
            }
            return this;
        }

        public NodeData build() {
            return new NodeData(identifier, name, description, nodeSupplier, subNodes);
        }
    }
    
    public record NodeDataMaker(String name, String description, Supplier<? extends GenericNode> nodeSupplier) {}
}

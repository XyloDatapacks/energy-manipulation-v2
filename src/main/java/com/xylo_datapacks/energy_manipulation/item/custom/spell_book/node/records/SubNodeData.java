package com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.records;

import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public record SubNodeData(String name, String description, List<Identifier> possibleValues) {

    public static final class Builder {
        Identifier identifier;
        String name;
        String description;
        List<Identifier> possibleValues = new ArrayList<>();

        public Builder(Identifier identifier, subNodeDataMaker subNodeDataMaker) {
            this.identifier = identifier;
            this.name = subNodeDataMaker.name;
            this.description = subNodeDataMaker.description;
            this.possibleValues = subNodeDataMaker.possibleValues;
        }

        public Builder addPossibleValue(String name, Identifier possibleValue) {
            this.possibleValues.add(possibleValue);
            return this;
        }

        public Builder addPossibleValues(List<Identifier> possibleValues) {
            this.possibleValues.addAll(possibleValues);
            return this;
        }

        public SubNodeData build() {
            return new SubNodeData(name, description, possibleValues);
        }
    }
    
    public record subNodeDataMaker(String name, String description, List<Identifier> possibleValues) {}
}

package com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.database;

import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.GenericNode;

import java.util.List;

public record SubNodeData<T extends GenericNode>(String name, String description, Class<T> interfaceClass, List<Class<? extends T>> possibleClasses) {
}

package com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.records;

import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.AbstractNode;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.GenericNode;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public record SubNodeData(String name, String description, Class<? extends GenericNode> clazz, List<Identifier> possibleValues) {
}
     

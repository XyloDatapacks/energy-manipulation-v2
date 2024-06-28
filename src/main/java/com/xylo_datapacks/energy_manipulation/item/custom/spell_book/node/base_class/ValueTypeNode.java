package com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class;

import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.gui.value_selector.SelectorType;

public interface ValueTypeNode<T> extends GenericNode {
    
    public abstract T getValue();

    public abstract String getValueDisplay();

    public abstract boolean setValue(T value);
    
    public abstract SelectorType getValueSelectorType();
}

package com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.value_type;

import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.gui.value_selector.Button;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.gui.value_selector.ValueSelector;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.Nodes;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.AbstractNodeWithValue;

public class BooleanValueTypeNode extends AbstractNodeWithValue<Boolean> {
    
    public BooleanValueTypeNode() {
        super(Nodes.VALUE_TYPE_BOOLEAN);
        setValue(true);
    }
    
    /*----------------------------------------------------------------------------------------------------------------*/
    /* AbstractNodeWithValue Interface */
    
    @Override
    public String getValueDisplay() {
        return getValue().toString();
    }

    @Override
    public ValueSelector<?> getValueSelector() {
        return new Button(2);
    }

    @Override
    public boolean setValueFromSelector(ValueSelector<?> valueSelector) {
        if (valueSelector instanceof Button button) {
            return setValue(button.getValue() == 1);
        }
        return false;
    }

    /*----------------------------------------------------------------------------------------------------------------*/
}

package com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.value_type;

import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.gui.value_selector.Button;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.gui.value_selector.Slider;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.gui.value_selector.ValueSelector;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.Nodes;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.AbstractNodeWithValue;

public class DoubleValueTypeNode extends AbstractNodeWithValue<Double> {
    private double minValue = -100;
    private double maxValue = 100;
    
    public DoubleValueTypeNode() {
        super(Nodes.VALUE_TYPE_DOUBLE);
        setValue(0.0);
    }
    
    public void setBounds(double minValue, double maxValue) {
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    /*----------------------------------------------------------------------------------------------------------------*/
    /* AbstractNodeWithValue Interface */
    
    @Override
    public String getValueDisplay() {
        return getValue().toString();
    }

    @Override
    public boolean setValue(Double value) {
        if (value >= minValue && value <= maxValue) {
            return super.setValue(value);
        }
        return false;
    }

    @Override
    public ValueSelector<?> getValueSelector() {
        return new Slider(minValue, maxValue);
    }

    @Override
    public boolean setValueFromSelector(ValueSelector<?> valueSelector) {
        if (valueSelector instanceof Slider slider) {
            return setValue(slider.getValue()) ;
        }
        return false;
    }

    /*----------------------------------------------------------------------------------------------------------------*/
}

package com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.value_type;

import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.gui.value_selector.SelectorType;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.Nodes;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.AbstractNodeValue;

public class DoubleValueTypeNode extends AbstractNodeValue<Double> {
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
    /* AbstractNodeValue Interface */
    
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
    public SelectorType getValueSelectorType() {
        return SelectorType.SLIDER;
    }

    /*----------------------------------------------------------------------------------------------------------------*/
}

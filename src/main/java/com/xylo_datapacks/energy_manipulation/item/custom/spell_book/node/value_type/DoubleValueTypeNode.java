package com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.value_type;

import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.Nodes;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.AbstractNodeWithValue;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.number.NumberNode;
import io.wispforest.owo.ui.component.DiscreteSliderComponent;
import io.wispforest.owo.ui.container.FlowLayout;
import io.wispforest.owo.ui.parsing.UIModel;

import java.util.Map;
import java.util.function.Consumer;

public class DoubleValueTypeNode extends AbstractNodeWithValue<Double> implements NumberNode {
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
    public FlowLayout getValueSelectorComponent(UIModel model, Consumer<Double> onValueChanged) {
        FlowLayout flowLayout = model.expandTemplate(FlowLayout.class, "slider_selector_template", Map.of("value", Double.toString(getValue()), "min_value", Double.toString(minValue), "max_value", Double.toString(maxValue)));
        DiscreteSliderComponent sliderComponent = flowLayout.childById(DiscreteSliderComponent.class, "slider");
        flowLayout.child(sliderComponent);
        return flowLayout;
    }

    @Override
    public boolean setValueFromSelector(Object value) {
        if (value instanceof Double doubleValue) {
            return setValue(doubleValue) ;
        }
        return false;
    }

    /*----------------------------------------------------------------------------------------------------------------*/
}

package com.xylo_datapacks.energy_manipulation.item.custom.spell_book.gui.value_selector;

public class Slider implements ValueSelector<Double> {
    private final double minValue;
    private final double maxValue;

    public Slider() {
        this.minValue = 0;
        this.maxValue = 1;
    }
    
    public Slider(double minValue, double maxValue) {
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    /*----------------------------------------------------------------------------------------------------------------*/
    /* ValueSelector Interface */
    
    @Override
    public Double getValue() {
        return 0.0;
    }
    
    /*----------------------------------------------------------------------------------------------------------------*/
}

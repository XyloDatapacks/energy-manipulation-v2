package com.xylo_datapacks.energy_manipulation.item.custom.spell_book.gui.value_selector;

public class Button implements ValueSelector<Integer> {
    private final Integer statusCount;

    public Button(Integer statusCount) {
        this.statusCount = statusCount;
    }

    /*----------------------------------------------------------------------------------------------------------------*/
    /* ValueSelector Interface */
    
    @Override
    public Integer getValue() {
        return 0;
    }

    /*----------------------------------------------------------------------------------------------------------------*/
}

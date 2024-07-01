package com.xylo_datapacks.energy_manipulation.screen.test_owo;

import com.xylo_datapacks.energy_manipulation.EnergyManipulation;
import io.wispforest.owo.ui.base.BaseUIModelScreen;
import io.wispforest.owo.ui.component.ButtonComponent;
import io.wispforest.owo.ui.component.Components;
import io.wispforest.owo.ui.container.FlowLayout;
import io.wispforest.owo.ui.container.ScrollContainer;
import io.wispforest.owo.ui.core.Sizing;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class MyDataDrivenScreen extends BaseUIModelScreen<FlowLayout> {

    public MyDataDrivenScreen() {
        super(FlowLayout.class, DataSource.asset(Identifier.of(EnergyManipulation.MOD_ID, "my_data_driven_screen")));
    }

    /**
     * Build the component hierarchy of this screen,
     * called after the adapter and root component have been
     * initialized by {@link #createAdapter()}
     *
     * @param rootComponent The root component created
     *                      in the previous initialization step
     */
    @Override
    protected void build(FlowLayout rootComponent) {
        /*rootComponent.childById(ButtonComponent.class, "the_button").onPress(buttonComponent -> {
            System.out.println("Button pressed!");
        });*/

        FlowLayout flowLayout = rootComponent.childById(FlowLayout.class, "the_scroll_container");
        if (flowLayout != null) {
            for (int i= 0 ; i<10; i++) {
                int finalI = i;
                flowLayout.child(Components
                        .button(Text.literal("A Button " + i), button -> {
                            System.out.println("click: "+ finalI);
                        })
                        .horizontalSizing(Sizing.fill(100)));
            }
        }
                
    }
}

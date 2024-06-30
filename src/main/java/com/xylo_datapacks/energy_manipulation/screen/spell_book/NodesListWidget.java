package com.xylo_datapacks.energy_manipulation.screen.spell_book;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.Selectable;
import net.minecraft.client.gui.tooltip.Tooltip;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.client.gui.widget.ElementListWidget;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.List;

public class NodesListWidget extends ElementListWidget<NodesListWidget.WidgetEntry>  {
    
    public NodesListWidget(MinecraftClient minecraftClient, int width, int height, int topMargin, int bottomMargin, int itemHeight) {
        
        super(minecraftClient, width, height, topMargin, bottomMargin, itemHeight);
        this.centerListVertically = false;
        this.setRenderHeader(false, 0);
    }

    public int addSingleEntry() {
        return this.addEntry(new WidgetEntry(this.width, this.itemHeight, 5, 2));
    }
    
    @Override
    public int getRowWidth() {
        return this.width;
    }

    @Override
    protected int getScrollbarPositionX() {
        return super.getScrollbarPositionX();
    }
    
    


    @Environment(value= EnvType.CLIENT)
    protected static class WidgetEntry extends Entry<NodesListWidget.WidgetEntry> {
        
        final List<ClickableWidget> buttons;

        private WidgetEntry(int width, int height, int rightMargin, int topMargin) {
            ButtonWidget button = ButtonWidget.builder(Text.literal("Button A"), buttonAction -> {
                        System.out.println("You clicked buttonA!");
                    })
                    .dimensions(rightMargin, topMargin, width - rightMargin*2, height - topMargin)
                    .tooltip(Tooltip.of(Text.literal("Tooltip of buttonA")))
                    .build();
            this.buttons = new ArrayList<ClickableWidget>();
            this.buttons.add(button);
        }

        @Override
        public void render(DrawContext context, int index, int y, int x, int entryWidth, int entryHeight, int mouseX, int mouseY, boolean hovered, float tickDelta) {
            this.buttons.forEach(widget -> {
                widget.setY(y);
                widget.render(context, mouseX, mouseY, tickDelta);
            });
        }

        @Override
        public List<? extends Element> children() {
            return this.buttons;
        }

        @Override
        public List<? extends Selectable> selectableChildren() {
            return this.buttons;
        }
    }
}

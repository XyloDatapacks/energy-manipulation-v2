package com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node;

import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.gui.GuiManager;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.gui.value_selector.Button;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.gui.value_selector.Slider;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.AbstractNodeWithValue;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.GenericNode;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.base_class.records.NodeResult;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.instruction.InstructionProviderNode;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.value_type.DoubleValueTypeNode;
import net.minecraft.util.Identifier;
import org.w3c.dom.Node;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;

public class test {
    
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException {

        /*TypeToken<Map<String, NodeData>> listType = new TypeToken<Map<String, NodeData>>() {};
        Map<String, NodeData> target = new HashMap<>();
        target.put("shape.projectile", new NodeData("projectile", "projectile shape"));
        target.put("effect.fire", new NodeData("fire", "fire effect"));
        target.put("instruction.generate_shape", new NodeData("generate_shape", "generate_shape instruction"));

        Gson gson = new Gson();
        // For serialization you normally do not have to specify the type, Gson will use
        // the runtime type of the objects, however you can also specify it explicitly
        String json = gson.toJson(target, listType.getType());
        
        // But for deserialization you have to specify the type
        Map<String, NodeData> target2 = gson.fromJson(json, listType);
        
        
        offset: directional
            forward: float provider
                value: 4.5
            right: float provider
                value: 5.3
            up: float provider
                value: 9.8
        
        float provider extends NumberNode interface
        value is a subNode i guess, but that would also mean that the value has to be set through identifier. so it is a bad idea
        instead of returning a subnode i return the value
        
        
        */

        final InstructionProviderNode pageNode = new InstructionProviderNode();
        final GuiManager guiManager = new GuiManager(pageNode);
        
        guiManager.refreshGui();
        
       
        // modify position node
        System.out.println();
        System.out.println();
        guiManager.modifyNodeClass(guiManager.rootNode.getNodeResultFromPath("instruction[1].position"), Nodes.POSITION_OFFSET.identifier());
        // modify shape node
        System.out.println();
        System.out.println();
        guiManager.modifyNodeClass(guiManager.rootNode.getNodeResultFromPath("instruction[2].shape"), Nodes.SHAPE_RAY.identifier());
        // modify effect
        System.out.println();
        System.out.println();
        guiManager.modifyNodeClass(guiManager.rootNode.getNodeResultFromPath("instruction[2].shape.effects.effect[1]"), Nodes.EFFECT_BREAK.identifier());
        // modify value
        System.out.println();
        System.out.println();
        guiManager.modifyNodeValue(guiManager.rootNode.getNodeResultFromPath( "instruction[0].position.x"), new Button(2));
        // modify value
        System.out.println();
        System.out.println();
        guiManager.modifyNodeValue(guiManager.rootNode.getNodeResultFromPath( "instruction[1].position.offset.x.value"), new Slider(-20,20));

        // modify value
        System.out.println();
        System.out.println();
        ((DoubleValueTypeNode) guiManager.rootNode.getNodeFromPath("instruction[1].position.offset.y.value")).setValue(4.0);
        guiManager.refreshGui();
        
        
    }
    
    
    
}

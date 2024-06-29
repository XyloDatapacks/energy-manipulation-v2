package com.xylo_datapacks.energy_manipulation.event;

import com.xylo_datapacks.energy_manipulation.EnergyManipulation;
import com.xylo_datapacks.energy_manipulation.screen.spell_book.SpellBookSelectionScreen;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

public class KeyInputHandler {
    
    // Categories
    public static final String KEY_CATEGORY_TUTORIAL = "key.category." + EnergyManipulation.MOD_ID + ".tutorial";
    
    // Keybindings
    public static final String KEY_OPEN_SPELL_MENU = "key." + EnergyManipulation.MOD_ID + ".spell_book.open_spell_menu";
    public static KeyBinding openSpellMenuKey;

    public static final String KEY_CUSTOM_ACTION = "key." + EnergyManipulation.MOD_ID + ".custom_action";
    public static KeyBinding customActionKey;

    /**
     * Has to be called in onInitializeClient function of your ClientModInitializer class
     */
    public static void registerKeyBindings() {
        
        // Register Keybindings
        openSpellMenuKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                KEY_OPEN_SPELL_MENU, 
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_G,
                KEY_CATEGORY_TUTORIAL
        ));

        customActionKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                KEY_CUSTOM_ACTION,
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_R,
                KEY_CATEGORY_TUTORIAL
        ));
        
        // Add other Keybindings
        // ...
        
        // register lambda for key press checks
        registerKeyInputs();
    }

    public static void registerKeyInputs() {

        // Register Lambda with containing key press checks
        ClientTickEvents.END_CLIENT_TICK.register(client -> {

            // Check key press
            if (openSpellMenuKey.isPressed() && client.player != null) {
                client.player.sendMessage(Text.of("key pressed openspellbook menu"), false);
                // call Custom Action Server RPC
                //client.setScreen(new SpellBookSelectionScreen(client));
            }

            if (customActionKey.isPressed() && client.player != null) {
                //client.player.sendMessage(Text.of("key pressed example"), false);
                
                // call Example Server RPC
                //ClientPlayNetworking.send(ModPackets.EXAMPLE_ID, PacketByteBufs.create());
                //ClientPlayNetworking.send(ModPackets.PERFORMING_CUSTOM_ACTION_ID, PacketByteBufs.create());
               
            }

            // Other keys
            // ...

        });
    }
}

package kenymylankca.harshenuniverse.handlers.client;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;

import kenymylankca.harshenuniverse.HarshenClientUtils;
import kenymylankca.harshenuniverse.network.HarshenNetwork;
import kenymylankca.harshenuniverse.network.packets.MessagePacketRingUpdate;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;

public class HandlerKeyBinding 
{
	private final KeyBinding telering;
	private final KeyBinding minering;
	private final KeyBinding openAccessoryInventory;
	
	ArrayList<KeyBinding> ringEvents = new ArrayList<>();
	
	public HandlerKeyBinding()
	{
		telering = regRingKey("telering", Keyboard.KEY_LMENU);
		minering = regRingKey("minering", Keyboard.KEY_V);
		openAccessoryInventory = regKey("open_inventory", Keyboard.KEY_H);
	}
	
	private KeyBinding regKey(String name, int keycode)
	{
		KeyBinding key = new KeyBinding("key." +  name + ".description", keycode, "harshenuniverse");
		ClientRegistry.registerKeyBinding(key);
		return key;
	}
	
	private KeyBinding regRingKey(String name, int keycode)
	{
		KeyBinding key = regKey(name, keycode);
		ringEvents.add(key);
		return key;
	}
	
	@SubscribeEvent
	public void onKeyInput(KeyInputEvent event)
	{
		for(KeyBinding key : ringEvents)
			if(key.isPressed())
				sendRingEvent(ringEvents.indexOf(key));
		if(openAccessoryInventory.isPressed())
			HarshenClientUtils.openAccessoryInventory();
	}
	
	private void sendRingEvent(int ringType)
	{
		HarshenNetwork.sendToServer(new MessagePacketRingUpdate(ringType));
	}
}
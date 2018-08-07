package kenijey.harshenuniverse.handlers;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import kenijey.harshenuniverse.HarshenUtils;
import kenijey.harshenuniverse.api.BlockItem;
import kenijey.harshenuniverse.api.HarshenEvent;
import kenijey.harshenuniverse.api.IHarshenProvider;
import kenijey.harshenuniverse.network.HarshenNetwork;
import kenijey.harshenuniverse.network.packets.MessagePacketRequestHarshenInv;
import kenijey.harshenuniverse.objecthandlers.HarshenItemStackHandler;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.entity.player.PlayerPickupXpEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerRespawnEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class HandlerHarshenInventory 
{	
	HashMap<EntityPlayer, Integer> tickMap = new HashMap<>();
	private static ArrayList<ItemStack> prevInvClient = new ArrayList<>();
	
	@SubscribeEvent
	public void playerTick(PlayerTickEvent event)
	{
		ArrayList<ItemStack> prevInvServer = new ArrayList<>();
		if(event.player.world.isRemote)
		{
			if(event.player instanceof EntityOtherPlayerMP) return;
			if(!event.player.getEntityData().hasKey("harshenInventory"))
				HarshenNetwork.sendToServer(new MessagePacketRequestHarshenInv());
		}
		if(!tickMap.containsKey(event.player)) tickMap.put(event.player, 0);
		tickMap.put(event.player, tickMap.get(event.player) + 1);
		HarshenItemStackHandler handler = HarshenUtils.getHandler(event.player);
		ArrayList<ItemStack> prevInv = event.side.isServer() ? prevInvServer : prevInvClient;
		if(prevInv.size() > 0)
			for(int slot = 0; slot < handler.getSlots(); slot++)
				if(!(prevInv.get(slot).getItem() == handler.getStackInSlot(slot).getItem()) && (handler.getStackInSlot(slot).getItem() instanceof IHarshenProvider || prevInv.get(slot).getItem() instanceof IHarshenProvider))
				{
					if(handler.getStackInSlot(slot).getItem() instanceof IHarshenProvider)
						((IHarshenProvider)handler.getStackInSlot(slot).getItem()).onAdd(event.player, slot);
					if(prevInv.get(slot).getItem() instanceof IHarshenProvider)
						((IHarshenProvider)prevInv.get(slot).getItem()).onRemove(event.player, slot);
				}
		ArrayList<ItemStack> tickHandlerInventory = new ArrayList<>();
		for(int slot = 0; slot < handler.getSlots(); slot++)
		{
			if(handler.getStackInSlot(slot).getItem() instanceof IHarshenProvider)
				((IHarshenProvider)handler.getStackInSlot(slot).getItem()).onTick(event.player, tickMap.get(event.player));
			tickHandlerInventory.add(handler.getStackInSlot(slot));
		}	
		prevInv.clear();
		prevInv.addAll(tickHandlerInventory);
	}
	
	private static HashMap<UUID, HarshenItemStackHandler> stackMap = new HashMap<>();
	
	@SubscribeEvent(priority = EventPriority.LOWEST)
	public void onPlayerDeath(LivingDeathEvent event)
	{
		if(event.getEntityLiving() instanceof EntityPlayer)
		{
			HarshenItemStackHandler handler = HarshenUtils.getHandler(event.getEntity().getEntityData());
			stackMap.put(event.getEntity().getUniqueID(), handler);
		}
	}
	
	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public void onPlayerRespawn(PlayerRespawnEvent event)
	{
		if(stackMap.containsKey(event.player.getUniqueID()))
		{
			event.player.getEntityData().setTag("harshenInventory", stackMap.get(event.player.getUniqueID()).serializeNBT());
			for(int i = 0; i < stackMap.get(event.player.getUniqueID()).getSlots(); i++)
				if(stackMap.get(event.player.getUniqueID()).getStackInSlot(i).getItem() instanceof IHarshenProvider)
					((IHarshenProvider)stackMap.get(event.player.getUniqueID()).getStackInSlot(i).getItem()).onAdd(event.player, i);
		}
	}
	
	@SubscribeEvent
	public void onPlayerInteractXP(PlayerPickupXpEvent event)
	{
         if (!EnchantmentHelper.getEnchantedItem(Enchantments.MENDING, event.getEntityPlayer()).isEmpty())
        	 return;
         HarshenItemStackHandler handler = HarshenUtils.getHandler(event.getEntityPlayer());
         for(int o = 0; o < handler.getSlots(); o++)
         	if(handler.getStackInSlot(o).isItemDamaged() && EnchantmentHelper.getEnchantmentLevel(Enchantments.MENDING, handler.getStackInSlot(o)) > 0)
         	{
         		int i = Math.min(event.getOrb().xpValue * 2, (handler.getStackInSlot(o).getItemDamage()));
                event.getOrb().xpValue -= i / 2;
                HarshenUtils.damageFirstOccuringItem(event.getEntityPlayer(), handler.getStackInSlot(o).getItem(), - i);
         		break;
         	}
	}
	
	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void onToolTip(ItemTooltipEvent event)
	{
		if(HarshenUtils.hasProvider(new BlockItem(event.getItemStack().getItem())))
		{
			IHarshenProvider provider = HarshenUtils.getProvider(new BlockItem(event.getItemStack().getItem()));
			event.getToolTip().add("\u00A75" + new TextComponentTranslation("accessoryitem", "\u00A77" + new TextComponentTranslation(provider.getSlot().getName()).getFormattedText()).getFormattedText());
			if(provider.toolTipLines() > 0)
				event.getToolTip().add(" ");
			for(int i = 0; i < provider.toolTipLines(); i ++)
				event.getToolTip().add("\u00A73" + new TextComponentTranslation(event.getItemStack().getItem().getRegistryName().getResourcePath() + String.valueOf(i + 1)).getFormattedText());
		}
	}
		
	@SubscribeEvent
	public void onEvent(Event event)
	{
		if(HarshenUtils.isPlayerInvolved(event))
		{
			EntityPlayer player = HarshenUtils.getPlayer(event);
			ArrayList<Item> loadedItems = new ArrayList<>();
			for(int i = 0; i < HarshenUtils.getHandler(player).getSlots(); i ++)
			{
				ItemStack stack = HarshenUtils.getHandler(player).getStackInSlot(i);
				if(!HarshenUtils.hasProvider(stack))
					continue; //practically impossible
				IHarshenProvider provider = HarshenUtils.getProvider(stack);
				Object object = provider.getProvider(stack);
				if(object != null && !(loadedItems.contains(stack.getItem()) && !provider.isMultiplyEvent(stack)))
					try {
						Method method = HarshenUtils.getMethod(object.getClass(), HarshenEvent.class, event.getClass());
						if(method != null)
							method.invoke(object, event);
					} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
						if(e instanceof InvocationTargetException)
							((InvocationTargetException)e).getTargetException().printStackTrace();
						else
							e.printStackTrace();
					}
				loadedItems.add(stack.getItem());
			}
		}
	}
}
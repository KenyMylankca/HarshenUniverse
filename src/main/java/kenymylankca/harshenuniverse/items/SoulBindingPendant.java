package kenymylankca.harshenuniverse.items;

import java.util.HashMap;
import java.util.UUID;

import kenymylankca.harshenuniverse.HarshenItems;
import kenymylankca.harshenuniverse.HarshenSounds;
import kenymylankca.harshenuniverse.HarshenUtils;
import kenymylankca.harshenuniverse.api.EnumAccessoryInventorySlots;
import kenymylankca.harshenuniverse.api.HarshenEvent;
import kenymylankca.harshenuniverse.api.IHarshenProvider;
import kenymylankca.harshenuniverse.handlers.HandlerHarshenInventory;
import kenymylankca.harshenuniverse.network.HarshenNetwork;
import kenymylankca.harshenuniverse.network.packets.MessagePacketReviveInventory;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.play.server.SPacketCombatEvent;
import net.minecraft.scoreboard.IScoreCriteria;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.Team;
import net.minecraft.stats.StatList;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerRespawnEvent;

public class SoulBindingPendant extends Item implements IHarshenProvider
{
	public SoulBindingPendant() {
		setRegistryName("soul_binding_pendant");
		setUnlocalizedName("soul_binding_pendant");
		setMaxDamage(24);
	}
	
	@Override
	public EnumAccessoryInventorySlots getSlot() {
		return EnumAccessoryInventorySlots.NECK;
	}
	
	@Override
	public void onAdd(EntityPlayer player, int slot) {
		player.playSound(HarshenSounds.ONE_RING, 1f, 1.8f);
		IHarshenProvider.super.onAdd(player, slot);
	}
	
	@Override
	public void onRemove(EntityPlayer player, int slot) {
		player.playSound(HarshenSounds.SOUL_RIPPER_BOW_HIT, 1f, 0.6f);
		IHarshenProvider.super.onRemove(player, slot);
	}
	
	@Override
	public boolean isEnchantable(ItemStack stack) {
		return false;
	}
	
	@Override
	public Object getProvider(ItemStack stack) {
		return new SoulBindingPendantHandler();
	}
	
	private static final HashMap<UUID, NBTTagList> INVENTORY_MAP = new HashMap<>();

	
	public class SoulBindingPendantHandler
	{
		@HarshenEvent
		public void onEntityDeath(LivingDeathEvent event)
		{
			event.setCanceled(true);
			World world = event.getEntityLiving().world;
			if(!world.isRemote)
			{
				boolean flag = world.getGameRules().getBoolean("showDeathMessages");
				EntityPlayerMP player = (EntityPlayerMP) event.getEntity();
				player.connection.sendPacket(new SPacketCombatEvent(player.getCombatTracker(), SPacketCombatEvent.Event.ENTITY_DIED, flag));
		        if (flag)
		        {
		            Team team = player.getTeam();
		            if (team != null && team.getDeathMessageVisibility() != Team.EnumVisible.ALWAYS)
		                if (team.getDeathMessageVisibility() == Team.EnumVisible.HIDE_FOR_OTHER_TEAMS)
		                	player.mcServer.getPlayerList().sendMessageToAllTeamMembers(player, player.getCombatTracker().getDeathMessage());
		                else if (team.getDeathMessageVisibility() == Team.EnumVisible.HIDE_FOR_OWN_TEAM)
		                	player.mcServer.getPlayerList().sendMessageToTeamOrAllPlayers(player, player.getCombatTracker().getDeathMessage());
		            else
		            	player.mcServer.getPlayerList().sendMessage(player.getCombatTracker().getDeathMessage());
		        }

		        for (ScoreObjective scoreobjective : world.getScoreboard().getObjectivesFromCriteria(IScoreCriteria.DEATH_COUNT))
		            player.getWorldScoreboard().getOrCreateScore(player.getName(), scoreobjective).incrementScore();

		        EntityLivingBase entitylivingbase = player.getAttackingEntity();

		        if (entitylivingbase != null)
		        {
		            EntityList.EntityEggInfo entitylist$entityegginfo = EntityList.ENTITY_EGGS.get(EntityList.getKey(entitylivingbase));
		            if (entitylist$entityegginfo != null)
		            	player.addStat(entitylist$entityegginfo.entityKilledByStat);
		        }
		        player.addStat(StatList.DEATHS);
		        player.takeStat(StatList.TIME_SINCE_DEATH);
		        player.extinguish();
		        HarshenUtils.setFlag(player, 0, false);
		        player.getCombatTracker().reset();
		        INVENTORY_MAP.put(event.getEntity().getUniqueID(), ((EntityPlayer)event.getEntity()).inventory.writeToNBT(new NBTTagList()));
		        HarshenUtils.damageFirstOccuringItemNoPacket(player, HarshenItems.SOUL_BINDING_PENDANT, 1);
		        if(HarshenUtils.getFirstOccuringItem(player, HarshenItems.SOUL_BINDING_PENDANT).getItemDamage() == HarshenUtils.getFirstOccuringItem(player, HarshenItems.SOUL_BINDING_PENDANT).getMaxDamage())
		        			HarshenUtils.damageFirstOccuringItemNoPacket(player, HarshenItems.SOUL_BINDING_PENDANT, 1);    	
			}	
			new HandlerHarshenInventory().onPlayerDeath(event);
		}
		
		@HarshenEvent
		public void onPlayerRespawn(PlayerRespawnEvent event)
		{
			event.player.inventory.readFromNBT(INVENTORY_MAP.get(event.player.getUniqueID()));
			HarshenNetwork.sendToPlayer(event.player, new MessagePacketReviveInventory(event.player));
		}
	}
}
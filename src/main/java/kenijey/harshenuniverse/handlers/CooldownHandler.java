package kenijey.harshenuniverse.handlers;

import kenijey.harshenuniverse.HarshenUniverse;
import kenijey.harshenuniverse.items.HarshenNightBlade;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CooldownHandler
{
	@CapabilityInject(ICooldownHandler.class)
	public static final Capability<ICooldownHandler> COOLDOWN = null;
    
    public static void register() {
        
        CapabilityManager.INSTANCE.register(ICooldownHandler.class, new Storage(), DefaultCooldownHandler.class);
        MinecraftForge.EVENT_BUS.register(new CooldownHandler());
    }
    
    @SubscribeEvent
    public void attachCapabilities(AttachCapabilitiesEvent<ItemStack> event)
    {
    	if(event.getObject().getItem() instanceof HarshenNightBlade)
    	{
    		event.addCapability(new ResourceLocation(HarshenUniverse.MODID, "cooldown"), new Provider(421));
    	}
    }
    
    public static ICooldownHandler getHandler(Entity entity) {

        if (entity.hasCapability(COOLDOWN, EnumFacing.DOWN))
            return entity.getCapability(COOLDOWN, EnumFacing.DOWN);
        
        return null;
    }
    
    public interface ICooldownHandler
    {
    	int getMaxCooldown();
    	int getCooldown();
        boolean isReady();
        void addProgress();
        void setCooldown(int cooldown);
        void setMaxCooldown(int maxCooldown);
    }

    public static class DefaultCooldownHandler implements ICooldownHandler
    {
    	public DefaultCooldownHandler(int maxCooldown)
    	{
    		this.maxcooldown=maxCooldown;
    	}
    	private int maxcooldown;
        private int cooldown = 0;

		@Override
		public boolean isReady()
		{
            return cooldown >= maxcooldown;
        }

		@Override
		public void addProgress()
		{
			this.cooldown ++;
		}
		
		@Override
		public int getMaxCooldown()
		{
			return maxcooldown;
		}
		
		@Override
		public int getCooldown()
		{
			return cooldown;
		}
		
		@Override
		public void setCooldown(int cooldown)
		{
			this.cooldown=cooldown;
		}
		
		@Override
		public void setMaxCooldown(int maxCooldown)
		{
			this.maxcooldown=maxCooldown;
		}
    }
    
    public static class Storage implements Capability.IStorage<ICooldownHandler> {

        @Override
        public NBTBase writeNBT (Capability<ICooldownHandler> capability, ICooldownHandler instance, EnumFacing side)
        {
            final NBTTagCompound tag = new NBTTagCompound();
            tag.setInteger("cooldown", instance.getCooldown());
            tag.setInteger("maxCooldown", instance.getMaxCooldown());
            return tag;
        }

        @Override
        public void readNBT (Capability<ICooldownHandler> capability, ICooldownHandler instance, EnumFacing side, NBTBase nbt)
        {
            final NBTTagCompound tag = (NBTTagCompound) nbt;
            instance.setCooldown(tag.getInteger("cooldown"));
            instance.setMaxCooldown(tag.getInteger("maxCooldown"));
        }
    }
    
    public static class Provider implements ICapabilitySerializable<NBTTagCompound>
    {
    	ICooldownHandler instance;
    	
    	Provider(int maxCooldown) {
    		instance = new DefaultCooldownHandler(maxCooldown);
    	}

        @Override
        public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
            return capability == COOLDOWN;
        }

        @Override
        public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
            return hasCapability(capability, facing) ? COOLDOWN.<T>cast(instance) : null;
        }

        @Override
        public NBTTagCompound serializeNBT() {
            return (NBTTagCompound) COOLDOWN.getStorage().writeNBT(COOLDOWN, instance, null);
        }

        @Override
        public void deserializeNBT(NBTTagCompound nbt) {
        	COOLDOWN.getStorage().readNBT(COOLDOWN, instance, null, nbt);
        }
    }
}
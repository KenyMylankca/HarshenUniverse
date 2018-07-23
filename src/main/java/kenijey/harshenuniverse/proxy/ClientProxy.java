package kenijey.harshenuniverse.proxy;

import kenijey.harshenuniverse.HarshenBlocks;
import kenijey.harshenuniverse.HarshenItems;
import kenijey.harshenuniverse.HarshenUniverse;
import kenijey.harshenuniverse.HarshenUtils;
import kenijey.harshenuniverse.armor.HarshenArmors;
import kenijey.harshenuniverse.dimensions.pontus.PontusWorldProvider;
import kenijey.harshenuniverse.entity.EntityHarshenSoul;
import kenijey.harshenuniverse.entity.EntityKazzendre;
import kenijey.harshenuniverse.entity.EntityRenderFactoryRegisters;
import kenijey.harshenuniverse.entity.EntitySoulPart;
import kenijey.harshenuniverse.entity.EntitySoulShooter;
import kenijey.harshenuniverse.entity.EntitySoullessKnight;
import kenijey.harshenuniverse.entity.EntityThrown;
import kenijey.harshenuniverse.enums.items.EnumRitualStick;
import kenijey.harshenuniverse.enums.items.GlassContainerValue;
import kenijey.harshenuniverse.enums.particle.EnumHarshenParticle;
import kenijey.harshenuniverse.fluids.HarshenFluids;
import kenijey.harshenuniverse.handlers.client.HandlerEntityUpdater;
import kenijey.harshenuniverse.handlers.client.HandlerFlatPlateRenderer;
import kenijey.harshenuniverse.handlers.client.HandlerGameOverlay;
import kenijey.harshenuniverse.handlers.client.HandlerGuiEvent;
import kenijey.harshenuniverse.handlers.client.HandlerHarshenClientEffects;
import kenijey.harshenuniverse.handlers.client.HandlerKeyBinding;
import kenijey.harshenuniverse.handlers.client.HandlerRenderError;
import kenijey.harshenuniverse.handlers.client.HandlerRendererGuiInventory;
import kenijey.harshenuniverse.internal.HarshenAPIHandler;
import kenijey.harshenuniverse.models.ModelJaguarArmor;
import kenijey.harshenuniverse.objecthandlers.FaceRenderer;
import kenijey.harshenuniverse.particle.ParticleBlood;
import kenijey.harshenuniverse.particle.ParticleCauldron;
import kenijey.harshenuniverse.particle.ParticleCauldronTop;
import kenijey.harshenuniverse.particle.ParticleItem;
import kenijey.harshenuniverse.renderers.entity.RenderEntityThrown;
import kenijey.harshenuniverse.renderers.item.RendererBloodFactory;
import kenijey.harshenuniverse.renderers.item.RendererDimensionalPedestal;
import kenijey.harshenuniverse.renderers.item.RendererHarshenDisplayBlock;
import kenijey.harshenuniverse.renderers.item.RendererHarshenSpawner;
import kenijey.harshenuniverse.renderers.item.RendererHereticCauldron;
import kenijey.harshenuniverse.renderers.item.RendererMagicTable;
import kenijey.harshenuniverse.renderers.item.RendererPedestalSlab;
import kenijey.harshenuniverse.renderers.sky.WeatherPontus;
import kenijey.harshenuniverse.tileentity.TileEntityBloodFactory;
import kenijey.harshenuniverse.tileentity.TileEntityHarshenDimensionalPedestal;
import kenijey.harshenuniverse.tileentity.TileEntityHarshenDisplayBlock;
import kenijey.harshenuniverse.tileentity.TileEntityHarshenMagicTable;
import kenijey.harshenuniverse.tileentity.TileEntityHarshenSpawner;
import kenijey.harshenuniverse.tileentity.TileEntityHereticCauldron;
import kenijey.harshenuniverse.tileentity.TileEntityPedestalSlab;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticlePortal;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.client.resources.IReloadableResourceManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy 
{
    public static ResourceLocation particleTexturesLocation = new ResourceLocation(HarshenUniverse.MODID, "textures/particles/particles.png");
	@Override
    public void regRenders(FMLPreInitializationEvent event) {
    	super.regRenders(event);
    	
    	HarshenBlocks.regRenders();
    	
		HarshenItems.regRenders();
		
		HarshenFluids.regRenders();
		
		HarshenArmors.regRenders();
		
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityHarshenDimensionalPedestal.class, new RendererDimensionalPedestal());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityHereticCauldron.class, new RendererHereticCauldron());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityHarshenDisplayBlock.class, new RendererHarshenDisplayBlock());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityHarshenSpawner.class, new RendererHarshenSpawner());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPedestalSlab.class, new RendererPedestalSlab());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBloodFactory.class, new RendererBloodFactory());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityHarshenMagicTable.class, new RendererMagicTable());

		RenderingRegistry.registerEntityRenderingHandler(EntitySoullessKnight.class, new EntityRenderFactoryRegisters.FactorySoullessKnight());
		RenderingRegistry.registerEntityRenderingHandler(EntitySoulPart.class, new EntityRenderFactoryRegisters.FactorySoulPart());
		RenderingRegistry.registerEntityRenderingHandler(EntityThrown.class, new EntityRenderFactoryRegisters.FactoryEntityThrown());
		RenderingRegistry.registerEntityRenderingHandler(EntityHarshenSoul.class, new EntityRenderFactoryRegisters.FactoryHarhenSoul());
		RenderingRegistry.registerEntityRenderingHandler(EntitySoulShooter.class, new EntityRenderFactoryRegisters.FactorySoulShooter());
		RenderingRegistry.registerEntityRenderingHandler(EntityKazzendre.class, new EntityRenderFactoryRegisters.FactoryKazzendre());
	}
	
	 @Override
	public int getrenderDistance() {
		return Minecraft.getMinecraft().gameSettings.renderDistanceChunks;
	}
    
	@Override
	public void setWorldRenderer(PontusWorldProvider prov) {
		prov.setWeatherRenderer(new WeatherPontus());
	}
	
    @Override
    public void init(FMLInitializationEvent event) 
    {
    	super.init(event);
    	
    	HarshenUtils.registerHandlers(new HandlerKeyBinding(),
    			new HandlerGameOverlay(),
    			new HandlerGuiEvent(),
    			new HandlerRendererGuiInventory(), 
    			new HandlerFlatPlateRenderer(),
    			//new HandlerUpdateChecker(),
    			new HandlerRenderError(),
    			new RenderEntityThrown(null),
    			new HandlerEntityUpdater(),
    			new HandlerHarshenClientEffects());
    	
    	ItemColors itemcolors = Minecraft.getMinecraft().getItemColors();
    	itemcolors.registerItemColorHandler(new IItemColor() {
			
			@Override
			public int colorMultiplier(ItemStack stack, int tintIndex) {
				return tintIndex == 1 ? -1 : GlassContainerValue.getContainerFromMeta(stack.getMetadata()).color;
			}
		}, HarshenItems.GLASS_CONTAINER);
    	
    	itemcolors.registerItemColorHandler(new IItemColor() {
			@Override
			public int colorMultiplier(ItemStack stack, int tintIndex) {
				return EnumRitualStick.getColorFromMeta(stack.getMetadata());
			}
		}, HarshenItems.RITUAL_STICK);
    }
    
    @Override
    public EntityPlayer getPlayer() {
    	return Minecraft.getMinecraft().player;
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) 
    {
    	super.postInit(event);
    	((IReloadableResourceManager)  Minecraft.getMinecraft().getResourceManager()).registerReloadListener(resourceManager -> {
			HarshenAPIHandler.register();
			HarshenUniverse.LOGGER.info("All recipes loaded");
		});
    }
    
    @Override
    public void registerFluidBlockRendering(Block block, String name) 
    {    	
        final ModelResourceLocation fluidLocation = new ModelResourceLocation(HarshenUniverse.MODID.toLowerCase() + ":fluids", name);

        ModelLoader.setCustomStateMapper(block, new StateMapperBase()
        {
            @Override
            protected ModelResourceLocation getModelResourceLocation(IBlockState state)
            {

                return fluidLocation;
            }
        });
    }
    
    @Override
    public Particle spawnParticle(EnumHarshenParticle type, Vec3d position, Vec3d directionSpeed, float scale, boolean disableMoving, Object...info) {
    	Minecraft minecraft = Minecraft.getMinecraft();
        Particle entityFx = null;
        if(minecraft.world !=  null)
	        switch (type)
	        {
		        case BLOOD:
		            entityFx = new ParticleBlood(minecraft.world, position.x, position.y, position.z, directionSpeed.x, directionSpeed.y, directionSpeed.z, scale, disableMoving);
		            break;
		        case CAULDRON:
			        if(info.length > 0 )
			        	if(info[0] instanceof ResourceLocation)
			        		entityFx = new ParticleCauldron(minecraft.world, (ResourceLocation) info[0], position.x, position.y, position.z, directionSpeed.x, directionSpeed.y, directionSpeed.z, scale / 5f, disableMoving);
			        	if(info[0] instanceof IBlockState)
			        		entityFx = new ParticleCauldron(minecraft.world, position.x, position.y, position.z, directionSpeed.x, directionSpeed.y, directionSpeed.z, scale / 5f, disableMoving, ((IBlockState)info[0]));
			        break;
		        case ITEM:
		        	if(info.length > 0 && info[0] instanceof ItemStack)
		        		entityFx = new ParticleItem(minecraft.world, position.x, position.y, position.z, directionSpeed.x, directionSpeed.y, directionSpeed.z, scale / 5f, disableMoving, (ItemStack) info[0]);
		        	break;
		        case PORTAL:
		        	entityFx = new ParticlePortal.Factory().createParticle(EnumParticleTypes.PORTAL.getParticleID(), minecraft.world, 
	        				position.x, position.y, position.z, directionSpeed.x, directionSpeed.y, directionSpeed.z);
		        	entityFx.setMaxAge((int)(Math.random() * 20.0D) + 100);
		        case CAULDRON_LIQUID:
		        	if(info.length > 0)
		        		if(info[0] instanceof ResourceLocation)
		        			entityFx = new ParticleCauldronTop(minecraft.world, position.x, position.y, position.z, scale, (ResourceLocation) info[0]).setDirectScale(scale);
		        		else if(info[0] instanceof IBlockState)
		        			entityFx = new ParticleCauldronTop(minecraft.world, position.x, position.y, position.z, scale, ((IBlockState) info[0])).setDirectScale(scale);
		        	break;
		        default:
		            break;
	        }
        if (entityFx != null) {minecraft.effectRenderer.addEffect(entityFx);}
        return entityFx;
    }
    
    @Override
    public ModelJaguarArmor getArmorModel(int id) {
    	switch (id) {
    	 case 0:
    	 return new ModelJaguarArmor(1.0f);
    	 case 1:
    	 return new ModelJaguarArmor(0.5f);
    	}
    	return null;
    }
    
    @Override
    public void addErroredPosition(FaceRenderer renderer) {
    	HandlerRenderError.erroredPositions.add(renderer);
    }
    
    @Override
    public void resetErroredPositions() {
    	HandlerRenderError.erroredPositions.clear();
    }
}
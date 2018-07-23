package kenijey.harshenuniverse.entity;

import kenijey.harshenuniverse.renderers.entity.RenderEntityThrown;
import kenijey.harshenuniverse.renderers.entity.RenderHarshenSoul;
import kenijey.harshenuniverse.renderers.entity.RenderKazzendre;
import kenijey.harshenuniverse.renderers.entity.RenderSoulPart;
import kenijey.harshenuniverse.renderers.entity.RenderSoulShooter;
import kenijey.harshenuniverse.renderers.entity.RenderSoullessKnight;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class EntityRenderFactoryRegisters 
{
    public static class FactorySoulPart implements IRenderFactory<EntitySoulPart> 
    {
        @Override
        public Render<? super EntitySoulPart> createRenderFor(RenderManager manager) {
          return new RenderSoulPart(manager);
        }
    }

    public static class FactorySoullessKnight implements IRenderFactory<EntitySoullessKnight> 
    {
    	@Override
        public Render<? super EntitySoullessKnight> createRenderFor(RenderManager manager) {
          return new RenderSoullessKnight(manager);
        }
    }
    
    public static class FactoryEntityThrown implements IRenderFactory<EntityThrown> 
    {
    	@Override
        public Render<? super EntityThrown> createRenderFor(RenderManager manager) {
          return new RenderEntityThrown(manager);
        }
    }
    
    public static class FactoryHarhenSoul implements IRenderFactory<EntityHarshenSoul> 
    {
    	@Override
        public Render<? super EntityHarshenSoul> createRenderFor(RenderManager manager) {
          return new RenderHarshenSoul(manager);
        }
    }
    
    public static class FactorySoulShooter implements IRenderFactory<EntitySoulShooter> 
    {
    	@Override
        public Render<? super EntitySoulShooter> createRenderFor(RenderManager manager) {
          return new RenderSoulShooter(manager);
        }
    }
    
    public static class FactoryKazzendre implements IRenderFactory<EntityKazzendre> 
    {
    	@Override
        public Render<? super EntityKazzendre> createRenderFor(RenderManager manager) {
          return new RenderKazzendre(manager);
        }
    }
}
package kenymylankca.harshenuniverse.entity;

import kenymylankca.harshenuniverse.renderers.entity.RenderBloodySheep;
import kenymylankca.harshenuniverse.renderers.entity.RenderEntityThrown;
import kenymylankca.harshenuniverse.renderers.entity.RenderHarshenSoul;
import kenymylankca.harshenuniverse.renderers.entity.RenderJacob;
import kenymylankca.harshenuniverse.renderers.entity.RenderKazzendre;
import kenymylankca.harshenuniverse.renderers.entity.RenderSoulPart;
import kenymylankca.harshenuniverse.renderers.entity.RenderSoulShooter;
import kenymylankca.harshenuniverse.renderers.entity.RenderSoullessKnight;
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
    
    public static class FactoryJacob implements IRenderFactory<EntityJacob> 
    {
    	@Override
        public Render<? super EntityJacob> createRenderFor(RenderManager manager) {
          return new RenderJacob(manager);
        }
    }
    
    public static class FactoryBloodySheep implements IRenderFactory<EntityBloodySheep> 
    {
    	@Override
        public Render<? super EntityBloodySheep> createRenderFor(RenderManager manager) {
          return new RenderBloodySheep(manager);
        }
    }
}
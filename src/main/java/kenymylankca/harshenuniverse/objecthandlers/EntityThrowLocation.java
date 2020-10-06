package kenymylankca.harshenuniverse.objecthandlers;

import kenymylankca.harshenuniverse.HarshenUniverse;
import net.minecraft.util.ResourceLocation;

public class EntityThrowLocation extends ResourceLocation
{
	private final int id;
	public EntityThrowLocation(int id) {
		super(HarshenUniverse.MODID, "textures/particles/throw_particles.png");
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
}

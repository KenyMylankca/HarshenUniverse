package kenymylankca.harshenuniverse.api;
/**
 * Used to register IDS to interfaces. Dont worry about using this it wont help. This is all handled internally 
 * and is only in the api section because {@link EnumAccessoryInventorySlots} relies on it, and nothing in the API can rely on 
 * anything HarshenUniverse related that is outside of the API package as it will cause gradle to crash when you try and build your mod.
 * @author Wyn Price
 */
public interface IIDSet {
	public void setId(int id);
}

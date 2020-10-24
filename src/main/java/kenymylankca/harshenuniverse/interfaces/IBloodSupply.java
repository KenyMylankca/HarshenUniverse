package kenymylankca.harshenuniverse.interfaces;

public interface IBloodSupply 
{
	public int getAmountPerSecond();
	
	/**
	 * You can set this to -1 if you want to make the supply infinite.
	 */
	public int ticksUntillUsed();
}
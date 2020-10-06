package kenymylankca.harshenuniverse.api;

import java.awt.Point;
import java.util.ArrayList;
/**
 * The Inventory Slots for HarshenUniverse.
 * @author Wyn Price
 *
 */
public enum EnumAccessoryInventorySlots implements IIDSet
{
	LEFT_EAR("earring", 56, 14, 1),
	RIGHT_EAR("earring", 104, 14, 0),
	NECK("pendant", 80, 23),
	RING1("ring", 68, 50, 4),
	RING2("ring", 92, 50, 3);
	
	private int id;
	private final ArrayList<Integer> alowedIds;
	private final Point point;
	private final String name;
	
	private EnumAccessoryInventorySlots(String name, int x, int y, int... relatedTypes)
	{
		this.point = new Point(x, y);
		alowedIds = new ArrayList<>();
		for(int i : relatedTypes)
			alowedIds.add(i);
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public int getId() {
		return id;
	}
	
	public Point getPoint() {
		return point;
	}
	
	public boolean isAllowed(EnumAccessoryInventorySlots slotType)
	{
		return this.alowedIds.contains(slotType.getId());
	}
	
	public static EnumAccessoryInventorySlots getFromMeta(int meta)
	{
		for(EnumAccessoryInventorySlots slot : EnumAccessoryInventorySlots.values())
			if(slot.id == meta)
				return slot;
		return null;
	}

	@Override
	public void setId(int id) {
		this.id = id;
		alowedIds.add(id);
	}
}
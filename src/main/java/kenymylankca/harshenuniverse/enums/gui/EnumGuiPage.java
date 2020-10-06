package kenymylankca.harshenuniverse.enums.gui;

import java.util.ArrayList;

public enum EnumGuiPage
{
	MAIN("Main", -1),
	MOBS("Mobs", 0),
	POTIONS("Potions", 1),
	STRUCTURE("Structure", 2),
	DIMENSION("Dimension", 3),
	RITUAL("Ritual", 4);
	
	private final String name;
	private final int id;
	private final String tag;
	
	private EnumGuiPage(String name, int id)
	{
		this(name, id, name);
	}
	
	private EnumGuiPage(String name, int id, String tag)
	{
		this.name = name;
		this.id = id;
		this.tag = tag;
	}
	
	public static EnumGuiPage[] buttonPages()
	{
		ArrayList<EnumGuiPage> editedPages = new ArrayList<EnumGuiPage>();
		for(EnumGuiPage page : EnumGuiPage.values())
			if(page.getId() >= 0)
				editedPages.add(page);
		EnumGuiPage[] type = new EnumGuiPage[editedPages.size()];
		return editedPages.toArray(type);
	}
	
	public int getId()
	{
		return id;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public String getTag()
	{
		return this.tag;
	}
}
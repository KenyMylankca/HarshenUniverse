package com.kenymylankca.harshenuniverse.enums.gui;

import java.util.ArrayList;

public enum EnumBookGuiPage
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
	
	private EnumBookGuiPage(String name, int id)
	{
		this(name, id, name);
	}
	
	private EnumBookGuiPage(String name, int id, String tag)
	{
		this.name = name;
		this.id = id;
		this.tag = tag;
	}
	
	public static EnumBookGuiPage[] buttonPages()
	{
		ArrayList<EnumBookGuiPage> editedPages = new ArrayList<EnumBookGuiPage>();
		for(EnumBookGuiPage page : EnumBookGuiPage.values())
			if(page.getId() >= 0)
				editedPages.add(page);
		EnumBookGuiPage[] type = new EnumBookGuiPage[editedPages.size()];
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
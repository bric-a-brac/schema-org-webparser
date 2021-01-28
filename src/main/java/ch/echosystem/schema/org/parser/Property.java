package ch.echosystem.schema.org.parser;

import java.util.List;

public final class Property extends TypeOrProperty
	{
	private List<String> subPropertyOf;
	
	public List<String> getSubPropertyOf()
		{
		return subPropertyOf;
		}
	
	public void setSubPropertyOf(final List<String> subPropertyOf)
		{
		this.subPropertyOf = subPropertyOf;
		}
	}

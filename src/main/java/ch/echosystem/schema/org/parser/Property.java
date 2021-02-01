package ch.echosystem.schema.org.parser;

import java.util.List;

public final class Property extends TypeOrProperty
	{
	private List<String> domainIncludes;
	private List<String> rangeIncludes;
	private String inverseOf;
	
	public List<String> getSubPropertyOf()
		{
		return subOf;
		}
	
	public void setSubPropertyOf(final List<String> subPropertyOf)
		{
		subOf = subPropertyOf;
		}
	
	public String getEquivalentProperty()
		{
		return equivalent;
		}
	
	public void setEquivalentProperty(final String equivalentProperty)
		{
		equivalent = equivalentProperty;
		}
	
	public List<String> getSubProperties()
		{
		return sub;
		}
	
	public void setSubProperties(final List<String> subProperties)
		{
		sub = subProperties;
		}
	
	public List<String> getDomainIncludes()
		{
		return domainIncludes;
		}
	
	public void setDomainIncludes(final List<String> domainIncludes)
		{
		this.domainIncludes = domainIncludes;
		}
	
	public List<String> getRangeIncludes()
		{
		return rangeIncludes;
		}
	
	public void setRangeIncludes(final List<String> rangeIncludes)
		{
		this.rangeIncludes = rangeIncludes;
		}
	
	public String getInverseOf()
		{
		return inverseOf;
		}
	
	public void setInverseOf(final String inverseOf)
		{
		this.inverseOf = inverseOf;
		}
	}

package ch.echosystem.schema.org.parser;

import java.util.List;

public final class Type extends TypeOrProperty
	{
	private String enumerationType;
	
	public List<String> getSubTypeOf()
		{
		return subOf;
		}
	
	public void setSubTypeOf(final List<String> subTypeOf)
		{
		subOf = subTypeOf;
		}
	
	//equivalentClass
	//subTypes
	
	/**
	 * Est-ce que ce type est membre d'une énumération ?
	 * 
	 * @return <code>true</code> si fait partie d'une énumération, càd <code>isEnumerationMember() != null</code>.
	 */
	public boolean isEnumerationMember()
		{
		return enumerationType != null;
		}
	
	public String getEnumerationType()
		{
		return enumerationType;
		}
	
	public void setEnumerationType(final String enumerationType)
		{
		this.enumerationType = enumerationType;
		}
	}

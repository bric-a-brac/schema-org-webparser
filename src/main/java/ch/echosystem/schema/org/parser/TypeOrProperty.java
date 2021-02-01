package ch.echosystem.schema.org.parser;

import java.util.List;

public abstract class TypeOrProperty
	{
	protected String id;
	protected String label;
	protected String comment;
	protected List<String> subOf;
	protected String equivalent;
	protected List<String> sub;
	protected List<String> supersedes;
	//supersededBy
	
	protected String isPartOf;
	
	public final String getId()
		{
		return id;
		}
	
	public final void setId(final String id)
		{
		this.id = id;
		}
	
	public final String getLabel()
		{
		return label;
		}
	
	public final void setLabel(final String label)
		{
		this.label = label;
		}
	
	public final String getComment()
		{
		return comment;
		}
	
	public final void setComment(final String comment)
		{
		this.comment = comment;
		}
	
	public final List<String> getSupersedes()
		{
		return supersedes;
		}
	
	public final void setSupersedes(final List<String> supersedes)
		{
		this.supersedes = supersedes;
		}
	
	public final String getPartOf()
		{
		return isPartOf;
		}
	
	public final void setPartOf(final String partOf)
		{
		isPartOf = partOf;
		}
	}

package ch.echosystem.schema.org.parser;

public abstract class TypeOrProperty
	{
	protected String id;
	protected String label;
	protected String comment;
	
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
	}

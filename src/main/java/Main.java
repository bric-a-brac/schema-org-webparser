import ch.echosystem.schema.org.parser.WebParser;

public final class Main
	{
	private Main()
		{
		throw new UnsupportedOperationException();
		}
	
	public static void main(final String[] args) throws Exception
		{
		new WebParser().dev();
		
		/*
		new WebParser().parse().forEach(type ->
			{
			System.out.println(type.getId());
			});
		*/
		}
	}

package ch.echosystem.schema.org.parser;

import java.io.IOException;

public class WebParser
	{
	public void dev() throws IOException
		{
		final Schema schema = Schema.parse();
		
		// TODO Tester doaminIncludes et rangeIncludes ont , !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		
		schema.getTypes().forEach((id, type) ->
			{
			});
		}
	}

import java.io.Reader;
import java.io.StringReader;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.http.client.fluent.Request;

public final class Main
	{
	private Main()
		{
		throw new UnsupportedOperationException();
		}
	
	public static void main(final String[] args) throws Exception
		{
		System.out.println("Main");
		
		final String csv = Request.Get("https://github.com/schemaorg/schemaorg/blob/main/data/releases/11.01/schemaorg-all-https-types.csv?raw=true").execute().returnContent().asString();
		
		try (final Reader reader = new StringReader(csv))
			{
			try (final CSVParser parser = new CSVParser(reader, CSVFormat.DEFAULT))
				{
				parser.forEach(record ->
					{
					System.out.println(record.get(1));
					});
				}
			}
		}
	}

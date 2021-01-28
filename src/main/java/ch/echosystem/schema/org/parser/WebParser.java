package ch.echosystem.schema.org.parser;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.http.client.fluent.Request;

public class WebParser
	{
	private static final String URL = "https://raw.githubusercontent.com/schemaorg/schemaorg/main/data/releases/11.01/schemaorg-all-https-%s.csv";
	
	private enum PropertyHeader
		{
		id,
		label,
		comment,
		subPropertyOf,
		equivalentProperty,
		subproperties,
		domainIncludes,
		rangeIncludes,
		inverseOf,
		supersedes,
		supersededBy,
		isPartOf
		}
	
	private enum TypeHeader
		{
		id,
		label,
		comment,
		subTypeOf,
		enumerationtype,
		equivalentClass,
		properties,
		subTypes,
		supersedes,
		supersededBy,
		isPartOf
		}
	
	public void dev() throws IOException
		{
		parseProperties().forEach(property ->
			{
			System.out.println(property.getSubPropertyOf());
			});
		}
	
	public List<Type> parse() throws IOException
		{
		return parseTypes();
		}
	
	private List<Property> parseProperties() throws IOException
		{
		final List<Property> properties = new ArrayList<>();
		
		parse(String.format(URL, "properties"), PropertyHeader.class, record ->
			{
			final Property property = new Property();
			
			property.setId(stringNotEmpty(record.get(PropertyHeader.id)));
			
			property.setSubPropertyOf(parseIds(record.get(PropertyHeader.subPropertyOf)));
			
			properties.add(property);
			});
		
		return properties;
		}
	
	private List<Type> parseTypes() throws IOException
		{
		final List<Type> types = new ArrayList<>();
		
		parse(String.format(URL, "types"), TypeHeader.class, record ->
			{
			//System.out.println(record);
			
			final Type type = new Type();
			
			type.setId(stringNotEmpty(record.get(TypeHeader.id)));
			type.setLabel(stringNotEmpty(record.get(TypeHeader.label)));
			type.setComment(stringOrNull(record.get(TypeHeader.comment)));
			
			//stringNotEmpty(record.get(TypeHeader.subTypeOf));
			//stringNotEmpty(record.get(TypeHeader.enumerationtype));
			
			types.add(type);
			});
		
		return types;
		}
	
	private void parse(final String url, final Class<? extends Enum<?>> headers, final Consumer<CSVRecord> consumer) throws IOException
		{
		final String csv = Request.Get(url).execute().returnContent().asString();
		
		try (final Reader reader = new StringReader(csv))
			{
			try (final CSVParser parser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader(headers).withFirstRecordAsHeader()))
				{
				parser.forEach(consumer);
				}
			}
		}
	
	private String stringNotEmpty(final String text)
		{
		if (text.isBlank())
			{
			throw new RuntimeException("BLANK");
			}
		
		return text.trim();
		}
	
	private String stringOrNull(String text)
		{
		text = text.trim();
		
		if (text.isBlank())
			{
			return null;
			}
		
		return text;
		}
	
	private List<String> parseIds(String text)
		{
		text = stringOrNull(text);
		
		if (text == null)
			{
			return null;
			}
		
		return Arrays.asList(text.split(","))
			.stream().map(String::trim)
			.collect(Collectors.toList());
		}
	}

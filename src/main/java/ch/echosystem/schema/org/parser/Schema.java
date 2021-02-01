package ch.echosystem.schema.org.parser;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.http.client.fluent.Request;

public final class Schema
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
	
	private Map<String, Property> properties;
	private Map<String, Type> types;
	private Map<String, Type> enumerations;
	
	public Map<String, Property> getProperties()
		{
		return properties;
		}
	
	public Map<String, Type> getTypes()
		{
		return types;
		}
	
	public Map<String, Type> getEnumerations()
		{
		return enumerations;
		}
	
	public static Schema parse() throws IOException
		{
		final Schema schema = new Schema();
		
		schema.properties = schema.parseProperties();
		// TODO Séparer Type et Enum
		schema.types = schema.parseTypes();
		
		return schema;
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
	
	private Map<String, Property> parseProperties() throws IOException
		{
		final Map<String, Property> properties = new HashMap<>();
		
		parse(String.format(URL, "properties"), PropertyHeader.class, record ->
			{
			final Property property = new Property();
			
			final String id = stringNotEmpty(record.get(PropertyHeader.id));
			
			property.setId(id);
			property.setLabel(stringNotEmpty(record.get(PropertyHeader.label)));
			property.setComment(stringOrNull(record.get(PropertyHeader.comment)));
			property.setSubPropertyOf(parseIds(record.get(PropertyHeader.subPropertyOf)));
			property.setEquivalentProperty(stringOrNull(record.get(PropertyHeader.equivalentProperty)));
			property.setSubProperties(parseIds(record.get(PropertyHeader.subproperties)));
			property.setDomainIncludes(parseIds(record.get(PropertyHeader.domainIncludes)));
			property.setRangeIncludes(parseIds(record.get(PropertyHeader.rangeIncludes)));
			property.setInverseOf(stringOrNull(record.get(PropertyHeader.inverseOf)));
			property.setSupersedes(parseIds(record.get(PropertyHeader.supersedes)));
			//supersededBy
			property.setPartOf(stringOrNull(record.get(PropertyHeader.isPartOf)));
			
			if (record.get(PropertyHeader.supersededBy).contains(","))
				{
				System.out.println("supersededBy ,");
				}
			
			properties.put(id, property);
			});
		
		return properties;
		}
	
	private Map<String, Type> parseTypes() throws IOException
		{
		final Map<String, Type> types = new HashMap<>();
		
		parse(String.format(URL, "types"), TypeHeader.class, record ->
			{
			final Type type = new Type();
			
			final String id = stringNotEmpty(record.get(TypeHeader.id));
			
			type.setId(id);
			type.setLabel(stringNotEmpty(record.get(TypeHeader.label)));
			type.setComment(stringOrNull(record.get(TypeHeader.comment)));
			//stringNotEmpty(record.get(TypeHeader.subTypeOf));
			type.setEnumerationType(stringOrNull(record.get(TypeHeader.enumerationtype)));
			
			types.put(id, type);
			});
		
		return types;
		}
	
	private String stringNotEmpty(final String text)
		{
		if (text.isBlank())
			{
			// TODO Erreur
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

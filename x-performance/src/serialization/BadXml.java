package serialization;

import java.io.ByteArrayInputStream;
import java.nio.charset.Charset;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import util.PrintUtil;

public class BadXml
{
	protected static class BadXmlContentHandler implements ContentHandler
	{
		@Override
		public void setDocumentLocator(Locator locator)
		{
			PrintUtil.print("");
		}
		
		@Override
		public void startDocument() throws SAXException
		{
			PrintUtil.print("");
		}
		
		@Override
		public void endDocument() throws SAXException
		{
			PrintUtil.print("");
		}
		
		@Override
		public void startPrefixMapping(String prefix, String uri) throws SAXException
		{
			PrintUtil.print("");
		}
		
		@Override
		public void endPrefixMapping(String prefix) throws SAXException
		{
			PrintUtil.print("");
		}
		
		@Override
		public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException
		{
			PrintUtil.print("");
		}
		
		@Override
		public void endElement(String uri, String localName, String qName) throws SAXException
		{
			PrintUtil.print("");
		}
		
		@Override
		public void characters(char[] ch, int start, int length) throws SAXException
		{
			PrintUtil.print("");
		}
		
		@Override
		public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException
		{
			PrintUtil.print("");
		}
		
		@Override
		public void processingInstruction(String target, String data) throws SAXException
		{
			PrintUtil.print("");
		}
		
		@Override
		public void skippedEntity(String name) throws SAXException
		{
			PrintUtil.print("");
		}
	}
	
	protected static class BadXmlErrorHandler implements ErrorHandler
	{
		@Override
		public void warning(SAXParseException exception) throws SAXException
		{
			PrintUtil.print("");
		}

		@Override
		public void error(SAXParseException exception) throws SAXException
		{
			PrintUtil.print("");
		}

		@Override
		public void fatalError(SAXParseException exception) throws SAXException
		{
			PrintUtil.print("");
		}
		
	}
	
	public static void main(String[] args) throws Exception
	{
		String xml = "<html> junk < input name=token value=\"1245234\" /> more junk </html>";
		
		ByteArrayInputStream bis = new ByteArrayInputStream(xml.getBytes(Charset.defaultCharset()));
		XMLReader reader = XMLReaderFactory.createXMLReader();
		reader.setContentHandler(new BadXmlContentHandler());
		reader.setErrorHandler(new BadXmlErrorHandler());
		reader.parse(new InputSource(bis));
	}
}

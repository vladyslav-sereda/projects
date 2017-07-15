package ua.nure.sereda.SummaryTask3;


import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

public class Demo {
	
	public static void main(String[] args) throws SAXException, TransformerException, ParserConfigurationException, XMLStreamException, IOException {
		Main.main(new String[] { "input.xml" });
	}
	
}
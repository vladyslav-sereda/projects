package ua.nure.sereda.SummaryTask3;

import org.xml.sax.SAXException;
import ua.nure.sereda.SummaryTask3.cards.Cards;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import javax.xml.transform.TransformerException;
import java.io.IOException;


public class Main {

    public static void main(String[] args)
            throws IOException, SAXException, ParserConfigurationException,
            TransformerException, XMLStreamException {


        String xmlFile = args[0];
        System.out.println("Input ==> " + xmlFile);

        DOMController domController = new DOMController(xmlFile);
        domController.parse(true);
        Cards cards = domController.getCard();
        Sorter.setSortCardsByYear(cards);
        DOMController.saveToXML(cards, "output.dom.xml");
        System.out.println("Output ==> output.dom.xml");

        SAXController saxController = new SAXController(xmlFile);
        saxController.parse(true);
        cards = saxController.getCards();
        Sorter.setSortCardsBySend(cards);
        DOMController.saveToXML(cards, "output.sax.xml");
        System.out.println("Output ==> output.sax.xml");

		STAXController staxController = new STAXController(xmlFile);
		staxController.parse();
		cards = staxController.getCards();
		Sorter.setSortCardsByTypeName(cards);
		Sorter.setSortCardsByValuable(cards);
		DOMController.saveToXML(cards, "output.stax.xml");
		System.out.println("Output ==> output.stax.xml");
    }
}
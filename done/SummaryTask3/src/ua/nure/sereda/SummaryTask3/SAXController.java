package ua.nure.sereda.SummaryTask3;

import java.io.IOException;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import ua.nure.sereda.SummaryTask3.cards.*;


public class SAXController extends DefaultHandler {

    private String xmlFile;

    private String currElement;

    private Cards cards;

    private OldCard oldCard;

    private Type type;


    SAXController(String xmlFile) {
        this.xmlFile = xmlFile;
    }

    /**
     * Parses XML document.
     *
     * @param validation If true validate XML document against its XML schema. With
     *                 this parameter it is possible make parser validating.
     */
    void parse(boolean validation) throws ParserConfigurationException, SAXException, IOException {

        SAXParserFactory saxFactory = SAXParserFactory.newInstance();
        saxFactory.setNamespaceAware(true);

        if (validation) {
            saxFactory.setFeature("http://xml.org/sax/features/validation", true);
            saxFactory.setFeature("http://apache.org/xml/features/validation/schema", true);
        }

        SAXParser saxParser = saxFactory.newSAXParser();
        saxParser.parse(xmlFile, this);
    }

    @Override
    public void error(org.xml.sax.SAXParseException ex) throws SAXException {
        if (ex != null) {
            throw ex;
        }
    }

    /**
     * Getter for cards
     *
     * @return cards from SAX Controller
     */
    public Cards getCards() {
        return cards;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        currElement = localName;

        if (XML.CARDS.equalsTo(currElement)) {
            cards = new Cards();
            return;
        }

        if (XML.OLDCARD.equalsTo(currElement)) {
            oldCard = new OldCard();
            return;
        }

        if (XML.TYPE.equalsTo(currElement)) {
            type = new Type();
            if (attributes.getLength() > 0) {
                type.setSend(Boolean.parseBoolean(attributes.getValue(uri, XML.SEND.value())));
            }
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String textElement = new String(ch, start, length).trim();

        if (textElement.isEmpty()) {
            return;
        }

        if (XML.THEMA.equalsTo(currElement)) {
            oldCard.setThema(textElement);
            return;
        }

        if (XML.TYPENAME.equalsTo(currElement)) {
            type.setTypeName(TypeName.fromValue(textElement));
            return;
        }

        if (XML.COUNTRY.equalsTo(currElement)) {
            oldCard.setCountry(textElement);
            return;
        }

        if (XML.YEAR.equalsTo(currElement)) {
            try {
                oldCard.setYear(DatatypeFactory.newInstance().newXMLGregorianCalendar(textElement));
            } catch (DatatypeConfigurationException ex) {
                System.out.println("DatatypeConfigurationException in yearNode!!!");
            }
            return;
        }

        if (XML.AUTHOR.equalsTo(currElement)) {
            oldCard.setAuthor(textElement);
            return;
        }

        if (XML.VALUABLE.equalsTo(currElement)) {
            oldCard.setValuable(Valuable.fromValue(textElement));
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {

        if (XML.OLDCARD.equalsTo(localName)) {
            cards.getOldCard().add(oldCard);
            return;
        }

        if (XML.TYPE.equalsTo(localName)) {
            oldCard.setType(type);
        }
    }
}

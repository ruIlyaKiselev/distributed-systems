import generated.Node;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.apache.log4j.Logger;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.InputStream;

public class StaxStreamProcessor implements AutoCloseable {

    private static final Logger log = Logger.getLogger(StaxStreamProcessor.class);
    private static final XMLInputFactory FACTORY = XMLInputFactory.newInstance();
    private final XMLEventReader reader;
    private final Unmarshaller nodeUnmarshaller;

    public StaxStreamProcessor(InputStream is) throws XMLStreamException, JAXBException {
        reader = FACTORY.createXMLEventReader(is);
        nodeUnmarshaller = JAXBContext.newInstance(Node.class).createUnmarshaller();
        log.info("Reader created");
    }

    public Node nextNode() throws XMLStreamException, JAXBException {
        while (true) {
            if (!reader.hasNext()) {
                return null;
            }
            XMLEvent nextEvent = reader.peek();
            if (nextEvent.isStartElement()) {
                StartElement startElement = nextEvent.asStartElement();
                if (startElement.getName().getLocalPart().equals("node")) {
                    return (Node) nodeUnmarshaller.unmarshal(reader);
                }
            }
            reader.nextEvent();
        }
    }

    @Override
    public void close() {
        if (reader != null) {
            try {
                reader.close();
                log.info("Reader closed");
            } catch (XMLStreamException e) {
                log.error(e);
            }
        }
    }
}
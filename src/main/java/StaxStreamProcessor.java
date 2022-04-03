import org.apache.log4j.Logger;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.InputStream;

public class StaxStreamProcessor implements AutoCloseable {

    private static final Logger log = Logger.getLogger(StaxStreamProcessor.class);
    private static final XMLInputFactory FACTORY = XMLInputFactory.newInstance();
    private final XMLEventReader reader;

    public StaxStreamProcessor(InputStream is) throws XMLStreamException {
        reader = FACTORY.createXMLEventReader(is);
        log.info("Reader created");
    }

    public XMLEventReader getReader() {
        return reader;
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
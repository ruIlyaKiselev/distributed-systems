import org.apache.log4j.Logger;

import javax.lang.model.element.QualifiedNameable;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Iterator;

public class OsmParser {

    private static final Logger log = Logger.getLogger(OsmParser.class);

    public OsmProcessingResult parse(String fileName) {

        final OsmProcessingResult osmProcessingResult = new OsmProcessingResult();

        try {
            InputStream is = new FileInputStream(fileName);
            process(is, osmProcessingResult);
        } catch (FileNotFoundException ignored) {}

        return osmProcessingResult;
    }

    private void process(InputStream inputStream, OsmProcessingResult osmProcessingResult) {
        try (StaxStreamProcessor processor = new StaxStreamProcessor(inputStream)) {
            XMLEventReader xmlEventReader = processor.getReader();

            log.info("Start osm parsing");

            while (xmlEventReader.hasNext()) {

                var currentEvent = xmlEventReader.nextEvent();

                if (currentEvent.isStartElement()) {
                    StartElement startElement = currentEvent.asStartElement();

                    if (isNode(startElement)) {
                        processNode(startElement, osmProcessingResult);
                    }

                    if (isTag(startElement)) {
                        processTag(startElement, osmProcessingResult);
                    }
                }
            }

            xmlEventReader.close();
            log.info("Finish osm parsing");
        } catch (Exception e) {
            log.error(e);
        }
    }

    private static void processNode(StartElement element, OsmProcessingResult osmProcessingResult) {
        var user = element.getAttributeByName(new QName("user"));
        osmProcessingResult.redactions.merge(user.getValue(), 1, Integer::sum);
    }

    private static void processTag(StartElement element, OsmProcessingResult osmProcessingResult) {
        var key = element.getAttributeByName(new QName("k"));
        osmProcessingResult.tagKeys.merge(key.getValue(), 1, Integer::sum);
    }

    private boolean isNode(StartElement element) {
        return element.getName().equals(new QName("node"));
    }

    private boolean isTag(StartElement element) {
        return element.getName().equals(new QName("tag"));
    }
}

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class OsmParser {

    public OsmProcessingResult parse(String fileName) {

        final OsmProcessingResult osmProcessingResult = new OsmProcessingResult();

        try {
            InputStream is = new FileInputStream(fileName);
            process(is, osmProcessingResult);
        } catch (FileNotFoundException ignored) {}

        return osmProcessingResult;
    }

    private void process(InputStream br, OsmProcessingResult osmProcessingResult) {

        final Logger log = LogManager.getRootLogger();

        try (StaxStreamProcessor processor = new StaxStreamProcessor(br)) {
            XMLStreamReader xmlStreamReader = processor.getReader();

            log.info("Start osm parsing");

            while (xmlStreamReader.hasNext()) {
                xmlStreamReader.next();
                if (xmlStreamReader.isStartElement()) {
                    if (xmlStreamReader.getName().getLocalPart().equals("node")) {
                        String username = xmlStreamReader.getAttributeValue(4);
                        osmProcessingResult.redactions.merge(username, 1, Integer::sum);
                    }
                }
            }

            log.info("Finish osm parsing");
        } catch (Exception e) {
            log.error(e);
        }
    }
}

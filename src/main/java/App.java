import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class App {
    public static void main(String[] args) throws IOException, XMLStreamException {

        final Logger logger = LogManager.getRootLogger();

        final Map<String, Integer> userCount = new HashMap<>();

        System.out.println("Start process");
//        Archiver.decompressBz2("assets/RU-NVS.osm.bz2", "data.xml");
        System.out.println("Finish process");

        try (StaxStreamProcessor processor = new StaxStreamProcessor(Files.newInputStream(Paths.get("data.xml")))) {
            XMLStreamReader reader = processor.getReader();
            while (reader.hasNext()) {       // while not end of XML
                int event = reader.next();   // read next event
                if (event == XMLEvent.START_ELEMENT && "node".equals(reader.getLocalName()) && reader.getAttributeCount() == 8) {
                    String currentName = reader.getAttributeValue(4);
                    if (userCount.containsKey(currentName)) {
                        userCount.put(currentName, userCount.get(currentName) + 1);
                    } else {
                        userCount.put(currentName, 0);
                    }
                }
            }
        }

        final Map<String, Integer> result = MapUtil.sortByValue(userCount);
        System.out.println(result.entrySet());
        System.out.println(result.size());
    }
}

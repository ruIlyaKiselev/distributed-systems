import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XmlToMapExtractor {

    public static Map<String, Integer> countUniqueAttributes(
            String xmlName,
            String requiredTagName,
            List<String> finishTags,
            int countOfAttributes,
            int requiredAttributePosition
    ) throws IOException, XMLStreamException {

        final Map<String, Integer> userCount = new HashMap<>();

        try (StaxStreamProcessor processor = new StaxStreamProcessor(Files.newInputStream(Paths.get(xmlName)))) {
            XMLStreamReader reader = processor.getReader();
            while (reader.hasNext()) {
                int event = reader.next();

                if (event == XMLEvent.START_ELEMENT) {
                    for (String finishTag : finishTags) {
                        if (finishTag.equals(reader.getLocalName())) {
                            break;
                        }
                    }

                    if (requiredTagName.equals(reader.getLocalName()) && reader.getAttributeCount() == countOfAttributes) {
                        String currentName = reader.getAttributeValue(requiredAttributePosition);
                        if (userCount.containsKey(currentName)) {
                            userCount.put(currentName, userCount.get(currentName) + 1);
                        } else {
                            userCount.put(currentName, 0);
                        }
                    }
                }
            }
        }

        return MapUtils.sortByValue(userCount);
    }
}

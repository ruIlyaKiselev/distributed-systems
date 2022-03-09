import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.util.*;

public class App {

    //xjc .\OSMSchema.xsd

    static final String archiveName = "assets/RU-NVS.osm.bz2";
    static final String xmlName = "data.xml";

    public static void main(String[] args) throws IOException, XMLStreamException, JAXBException {

//        Archiver.decompressBz2(archiveName, xmlName);
//        Map<String, Integer> uniqueAttributes =  XmlToMapExtractor.countUniqueAttributes(
//                xmlName,
//                "node",
//                Arrays.asList("way", "relation"),
//                8,
//                4
//        );
//
//        System.out.println(uniqueAttributes.entrySet());
//        System.out.println(uniqueAttributes.size());

        JaxbProcessor jaxbParser = new JaxbProcessor();
        Collection<org.openstreetmap.osm._0.Node> elements= jaxbParser.parse("data.xml");
        for (org.openstreetmap.osm._0.Node element : elements) {
            System.out.println(element.toString());
        }

        MarshallProcessor.marshal("aaa.xml");
        Node restoredNode = MarshallProcessor.unmarshall("aaa.xml");

        System.out.println(restoredNode);
    }
}

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class MarshallProcessor {
    public static void marshal(String filename) throws JAXBException, IOException {
        Node node = new Node();
        node.setId(0L);
        node.setVersion(0);
        node.setTimestamp("");
        node.setUid(0L);
        node.setUser("");
        node.setChangeSet(0L);
        node.setLat(0.0);
        node.setLon(0.0);

        JAXBContext context = JAXBContext.newInstance(Node.class);
        Marshaller mar = context.createMarshaller();
        mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        mar.marshal(node, new File(filename));
    }

    public static Node unmarshall(String filename) throws JAXBException, IOException {
        JAXBContext context = JAXBContext.newInstance(Node.class);
        return (Node) context.createUnmarshaller().unmarshal(new FileReader(filename));
    }
}

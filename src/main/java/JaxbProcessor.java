import org.openstreetmap.osm._0.Osm;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import java.io.File;
import java.util.List;

public class JaxbProcessor {
    public List<org.openstreetmap.osm._0.Node> parse(String inputXml) throws JAXBException  {
        JAXBContext jc = JAXBContext.newInstance(Osm.class);
        Unmarshaller unmarshaller = jc.createUnmarshaller();
        Osm root = (Osm) unmarshaller.unmarshal(new File(inputXml));
        return root.getNode();
    }
}

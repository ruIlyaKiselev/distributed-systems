import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class App {
    public static void main(String[] args) {

        final Logger logger = LogManager.getRootLogger();

        logger.info("Start process");
        Archiver.decompressBz2("assets/RU-NVS.osm.bz2", "data.xml");
        logger.info("Finish process");
        XmlParser.parse("data.xml");
    }
}

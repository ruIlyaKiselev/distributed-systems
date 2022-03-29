import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class App {

    static final String archiveFileName = "src/main/resources/RU-NVS.osm.bz2";
    static final String xmlFileName = "data.xml";

    private static final Logger log = LogManager.getRootLogger();

    public static void main(String[] args) {

        log.info("Start process");
        Archiver.decompressBz2(archiveFileName, xmlFileName);

        final OsmParser osmParser = new OsmParser();
        OsmProcessingResult osmProcessingResult = osmParser.parse(xmlFileName);
        osmProcessingResult.sortRedactions();
        osmProcessingResult.sortTagsKey();
        log.info(osmProcessingResult);
        log.info("Finish process");
    }
}

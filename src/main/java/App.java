import database.DatabaseProvider;
import database.DatabaseStrategy;
import org.apache.log4j.Logger;

public class App {

    static final String archiveFileName = "src/main/resources/RU-NVS.osm.bz2";
    static final String xmlFileName = "data.xml";
    static final long parseLimit = 100000;
    static final long batchStep = 1000;
    private static final Logger log = Logger.getLogger(App.class);


    public static void main(String[] args) {

//        log.info("Start process");
//        Archiver.decompressBz2(archiveFileName, xmlFileName);

        //100K
        //100K
        final OsmParser osmParser = new OsmParser();
        final DatabaseProvider databaseProvider = new DatabaseProvider();

        OsmProcessingResult osmProcessingResult = osmParser.parse(
                xmlFileName,
                databaseProvider,
                DatabaseStrategy.batch,
                batchStep,
                parseLimit
        );

        osmProcessingResult.sortRedactions();
        osmProcessingResult.sortTagsKey();

        log.info(osmProcessingResult);
        log.info("Finish process");
    }
}

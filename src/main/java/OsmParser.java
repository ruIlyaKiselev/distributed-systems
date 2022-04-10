import database.DatabaseProvider;
import database.DatabaseStrategy;
import generated.Node;
import generated.Tag;
import org.apache.log4j.Logger;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

import static database.DatabaseStrategy.*;

public class OsmParser {

    private static final Logger logger = Logger.getLogger(OsmParser.class);

    public OsmProcessingResult parse(
            String fileName,
            DatabaseProvider databaseProvider,
            DatabaseStrategy databaseStrategy
    ) {
        final OsmProcessingResult osmProcessingResult = new OsmProcessingResult();

        try {
            databaseProvider.dropTables();
        } catch (SQLException e) {
            logger.error(e);
        }

        try {
            databaseProvider.createTables();
        } catch (SQLException e) {
            logger.error(e);
        }

        try {
            InputStream is = new FileInputStream(fileName);
            process(
                    is,
                    osmProcessingResult,
                    databaseProvider,
                    databaseStrategy
            );
        } catch (FileNotFoundException ignored) {}

        return osmProcessingResult;
    }

    private void process(
            InputStream inputStream,
            OsmProcessingResult osmProcessingResult,
            DatabaseProvider databaseProvider,
            DatabaseStrategy databaseStrategy
    ) {
        long insertTime = 0;

        try (StaxStreamProcessor processor = new StaxStreamProcessor(inputStream)) {
            logger.info("Start osm parsing");

            while (true) {
                Node node = processor.nextNode();

                if (node == null) {
                    break;
                }

                switch (databaseStrategy) {
                    case statement:
                        insertTime += databaseProvider.insertByStatement(node);
                        break;
                    case preparedStatement:
                        insertTime += databaseProvider.insertByPreparedStatement(node);
                        break;
                    case batch:
                        databaseProvider.addToBatch(node);
                }

                processNode(node, osmProcessingResult);
                List<Tag> tags = node.getTag();

                for (int i = 0; i != tags.size(); i++) {
                    processTag(tags.get(i), osmProcessingResult);
                }
            }

            if (databaseStrategy.equals(batch)) {
                insertTime += databaseProvider.executeBatch();
            }

            logger.info("Finish osm parsing");
            logger.info("Insert time: " + insertTime + "; databaseStrategy: " + databaseStrategy);
        } catch (Exception e) {
            logger.error(e);
        }
    }

    private static void processNode(Node node, OsmProcessingResult osmProcessingResult) {
        var user = node.getUser();
        osmProcessingResult.redactions.merge(user, 1, Integer::sum);
    }

    private static void processTag(Tag tag, OsmProcessingResult osmProcessingResult) {
        var key = tag.getK();
        osmProcessingResult.tagKeys.merge(key, 1, Integer::sum);
    }
}

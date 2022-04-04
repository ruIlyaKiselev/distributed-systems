import generated.Node;
import generated.Tag;
import org.apache.log4j.Logger;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

public class OsmParser {

    private static final Logger log = Logger.getLogger(OsmParser.class);

    public OsmProcessingResult parse(String fileName) {

        final OsmProcessingResult osmProcessingResult = new OsmProcessingResult();

        try {
            InputStream is = new FileInputStream(fileName);
            process(is, osmProcessingResult);
        } catch (FileNotFoundException ignored) {}

        return osmProcessingResult;
    }

    private void process(InputStream inputStream, OsmProcessingResult osmProcessingResult) {
        try (StaxStreamProcessor processor = new StaxStreamProcessor(inputStream)) {
            log.info("Start osm parsing");

            while (true) {
                Node node = processor.nextNode();

                if (node == null) {
                    break;
                }

                processNode(node, osmProcessingResult);
                List<Tag> tags = node.getTag();

                for (int i = 0; i != tags.size(); i++) {
                    processTag(tags.get(i), osmProcessingResult);
                }
            }

            log.info("Finish osm parsing");
        } catch (Exception e) {
            log.error(e);
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

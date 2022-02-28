import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class XmlParser {

    public static void parse(String filename) {
        DocumentBuilderFactory df;
        DocumentBuilder builder;
        Document document;

        final Logger logger = LogManager.getRootLogger();

        logger.info("Start parse XML");
        try {
            df = DocumentBuilderFactory.newInstance();
            builder = df.newDocumentBuilder();
            document = builder.parse(filename);

            NodeList children = document.getDocumentElement().getChildNodes();
            logger.info("Children count: " + children.getLength());
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("Finish parse XML");
    }
}

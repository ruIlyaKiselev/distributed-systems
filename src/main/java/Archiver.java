import org.apache.commons.compress.compressors.bzip2.BZip2CompressorInputStream;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.log4j.Logger;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Archiver {

    private static final Logger log = Logger.getLogger(Archiver.class);

    public static void decompressBz2(String inputFile, String outputFile) {


        log.info("Start decompress");
        try (
                BZip2CompressorInputStream input = new BZip2CompressorInputStream(
                        new BufferedInputStream(
                                new FileInputStream(inputFile)
                        )
                );
                FileOutputStream output = new FileOutputStream(outputFile)
        ) {
            log.info("Start copy");
            IOUtils.copy(input, output);
            log.info("Finish copy");
        } catch (Exception e) {
            log.error(e);
        }
        log.info("Finish decompress");
    }
}

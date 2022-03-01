import org.apache.commons.compress.compressors.bzip2.BZip2CompressorInputStream;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Arrays;

public class Archiver {
    public static void decompressBz2(String inputFile, String outputFile) {

        final Logger logger = LogManager.getRootLogger();

        System.out.println("Start decompress");
        try (
                BZip2CompressorInputStream input = new BZip2CompressorInputStream(
                        new BufferedInputStream(
                                new FileInputStream(inputFile)
                        )
                );
                FileOutputStream output = new FileOutputStream(outputFile)
        ) {
            System.out.println("Start copy");
            IOUtils.copy(input, output);
            System.out.println("Finish copy");
        } catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
        System.out.println("Finish decompress");
    }
}

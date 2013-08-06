/*
Purpose: Combine the list of return delimited files, and create a single PDF. (bookmarks not copied)
Returns: Boolean

Name: CombinePDF ( inputFiles; outputPath; enableCompression )

Parameters:     ( inputFiles ) list of filepaths
                ( outputPath ) filepath of combined PDF
                ( enableCompression ) boolean


Dependencies: iText.jar

2011-09-22, JPS, Created, adapted from work by Matt Petrowsky and Mark Thompson.
2013-07-25, JPS, Now checks the provided list of files to make sure they exist.
2013-07-25, JPS, Now your can enable compression (NOTICE: compression can break font formatting).
2013-08-06, JPS, Will now validate the enableCompression parameter. Any of the following may be used as true, anything else will be false: 1, true or yes.

NOTES:
NONE
*/


import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfSmartCopy;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfWriter;

try {
    String[] inFiles = inputFiles.split("\n");
    int f = 0;
    int pageOffset = 0;
    String outFile = outputPath;
    Document document = null;

    //Convert enableCompression to Boolean
    try {
        if ( enableCompression == "1" || enableCompression.equalsIgnoreCase("true") || enableCompression.equalsIgnoreCase("yes")) {
            enableCompression = "true";
        } else {
            enableCompression = "false";
        }
    } catch ( Exception e ) {
        String this.enableCompression = "false";
    }
    boolean compressionFlag = Boolean.parseBoolean(enableCompression)

                              // Make sure files exist
    for ( int i = 0; i < inFiles.length; ) {
        def fileTest = new File (inFiles[i]);
        if ( ! fileTest.exists()) {
            return "ERROR\rMissing source files.";
        }
        i++;
    }

    while (f < inFiles.length) {
        // Create a reader for the next document
        PdfReader reader = new PdfReader(inFiles[f]);
        reader.consolidateNamedDestinations();

        // Retrieve the total number of pages
        int n = reader.getNumberOfPages();

        pageOffset += n;

        // Create the master document
        if (f == 0) {
            // Create the document-object
            document = new Document(reader.getPageSizeWithRotation(1));

            // step 2: set the compression level, and set the PDF Copy Mode,
            // the writer that listens to the document
            if ( compressionFlag ) {
                document.compress = true;
                writer = new PdfSmartCopy(document, new FileOutputStream(outputPath));
                writer.setPdfVersion(PdfWriter.VERSION_1_7);
                writer.setFullCompression();
            } else {
                document.compress = false;
                writer = new PdfCopy(document, new FileOutputStream(outputPath));
                writer.setPdfVersion(PdfWriter.VERSION_1_7);
                writer.setCompressionLevel(-1);
            }

            // step 3: Open the document
            document.open();
        }
        // step 4: Add content
        PdfImportedPage page;

        for (int i = 0; i < n; ) {
            ++i;
            page = writer.getImportedPage(reader, i);
            writer.addPage(page);
        }

        f++;
    }

    //Cleanup
    document.close()
    writer.flush();
    writer.close();

    // Return the path to the merged file
    return outFile;

} catch (e) {
    return "ERROR\r" + e;
}

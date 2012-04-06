/* 
Purpose: Combine the list of return delimited files, and create a single PDF. (bookmarks not copied)
Returns: Boolean

Name: CombinePDF ( files; output )

Parameters: 	( files ) list of filepaths
                ( output ) filepath

Dependencies: iText.jar

2011-09-22 JPS, Created, adapted from work by Matt Petrowsky and Mark Thompson.

NOTES:
NONE
*/


import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfImportedPage;

try {
  String[] inFiles = files.split("\n");
  int f = 0;
  int pageOffset = 0;
  String outFile = output;
  Document document = null;
  PdfCopy  writer = null;

  while (f < inFiles.length) {
    // Create a reader for the next document
    PdfReader reader = new PdfReader(inFiles[f]);
    reader.consolidateNamedDestinations();

    // Retrieve the total number of pages
    int n = reader.getNumberOfPages();

    pageOffset += n;

    // Create the master document
    if (f == 0) {
      // step 1: Create the document-object
      document = new Document(reader.getPageSizeWithRotation(1));
      // step 2: Create the writer that listens to the document
      writer = new PdfCopy(document, new FileOutputStream(outFile));
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

  document.close();
  return outFile;
} catch(e) {
    return 'ERROR';
}
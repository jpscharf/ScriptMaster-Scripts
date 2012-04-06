/* 
Purpose: Asks a user for a save location and filename.
Returns: File Path

Name: SaveFileDialog ( saveTitle ; defaultDirectory ; defaultFileName )

Parameters: 	( saveTitle ) text
            	( defaultDirectory ) filepath
			( defaultFileName ) text

Dependencies: NONE

2011-09-01 JPS, Created.

NOTES:
NONE
*/

import java.awt.*;
import javax.swing.JOptionPane;

System.gc();

String title = saveTitle;
FileDialog saveDialog = new FileDialog(JOptionPane.getRootFrame(),title, FileDialog.SAVE);
saveDialog.setDirectory(defaultDirectory);
saveDialog.setFile(defaultFileName);
saveDialog.show();
if (saveDialog.getFile() != null) {
  selectedFile = fmpro.convertPathToFileMaker(saveDialog.getDirectory() + saveDialog.getFile())
  saveDialog = null;
  System.gc();
  return selectedFile;
} else {
  saveDialog = null;
  System.gc();
  return false;
}
/* 
Purpose: Asks a user to select a file to open.
Returns: File Path

Name: OpenFileDialog ( saveTitle ; defaultDirectory )

Parameters: 	( openTitle ) text
            	( defaultDirectory ) filepath

Dependencies: NONE

2011-09-01 JPS, Created.

NOTES:
NONE
*/

import java.awt.*;
import javax.swing.JOptionPane;

String title = openTitle;
FileDialog openDialog = new FileDialog(JOptionPane.getRootFrame(),title, FileDialog.LOAD);

openDialog.setDirectory(defaultDirectory);
openDialog.show();
if (openDialog.getFile() != null) {
  selectedFile = fmpro.convertPathToFileMaker(openDialog.getDirectory() + openDialog.getFile())
  return selectedFile;
} else {
  return false;
}
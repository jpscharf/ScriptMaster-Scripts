##ScriptMaster Scripts

These Groovy scripts were developed in [ScriptMaster](http://www.360works.com/scriptmaster/) for use with ScriptMaster and [FileMaker Pro](http://www.filemaker.com/).

* `SaveTextToFile( filePath ; theText ; theEncoding ; eolFormat )`
>Writes a text file. You can change the character encoding parameter to write in ASCII or international character sets.

* `SaveFileDialog ( saveTitle ; defaultDirectory ; defaultFileName )`
>Ask the user where to save a file. 

* `ExecuteSQL ( theQuery ; columnDelimiter ; rowDelimiter )`
>Run the provided SQL query and return the result with the provided delimiters.

* `OpenFileDialog ( saveTitle ; defaultDirectory )`
>Asks the user to select a file. Returns the selected files path.

* `CombinePDF ( files; output )`
>Combine the list of return delimited files, and create a single PDF. (bookmarks not copied)
>
>Requires: iText [http://itextpdf.com/](http://itextpdf.com/)

* `CreateFolder ( pathToCreate )`
>Creates a folder using the provided path.

* `PathExists ( thePath )`
>Checks to see if the file/folder exists for the provided file path.

* `DeletePath ( pathToDelete )`
>Deletes the provided folder/file (***recursive***)
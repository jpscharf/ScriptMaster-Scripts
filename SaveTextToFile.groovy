/* 
Purpose: Writes the provided text to a file with the requested encoding and EOL.
Returns: Text

Name: SaveTextToFile ( filePath ; theText ; theEncoding ; eolFormat )

Parameters: 	( filePath ) filepath
            	( theText ) text
			( theEncoding ) enum = (UTF-8, US-ASCII, LATIN-1...LATIN-9)
			( eolFormat ) enum = (MAC (< Mac OS X), WIN, UNX (includes Mac OS X)

Dependencies: NONE

2011-08-31 JPS, Created.

NOTES:
Supported Encodings
 8859_1 (ISO-8859-1/LATIN-1)
 8859_2 (ISO-8859-2/LATIN-2)
 8859_3 (ISO-8859-3/LATIN-3)
 8859_4 (ISO-8859-4/LATIN-4)
 8859_5 (ISO-8859-5/LATIN-5)
 8859_6 (ISO-8859-6/LATIN-6)
 8859_7 (ISO-8859-7/LATIN-7)
 8859_8 (ISO-8859-8/LATIN-8)
 8859_9 (ISO-8859-9/LATIN-9)
 ASCII (7-bit ASCII)
 UTF8 (UCS Transformation Format-8)

Supported EOL:
Mac, Windows, Linux/UNIX
*/

theEncoding = theEncoding.toUpperCase();

//Determine if the provided encoding is valid, otherwise return an error.
switch ( theEncoding ) {
    case ["UTF8", "UTF-8"]:
        theEncoding = "UTF-8";
        break;
 
    case ["US-ASCII", "ASCII"]:
        theEncoding = "US-ASCII";
        break;
 
    case ["ISO-8859-1/LATIN-1", "ISO-8859-1", "8859-1", "8859_1", "LATIN 1", "LATIN-1"]:
        theEncoding = "8859_1";
        break;

    case ["ISO-8859-2/LATIN-2", "ISO-8859-2","8859-2", "8859_2", "LATIN 2", "LATIN-2"]:
        theEncoding = "8859_2";
        break;
 
    case ["ISO-8859-3/LATIN-3", "ISO-8859-3","8859-3", "8859_3", "LATIN 3", "LATIN-3"]:
        theEncoding = "8859_3";
        break;
        
    case ["ISO-8859-4/LATIN-4", "ISO-8859-4","8859-4", "8859_4", "LATIN 4", "LATIN-4"]:
        theEncoding = "8859_4";
        break;
    
    case ["ISO-8859-5/LATIN-5", "ISO-8859-5","8859-5", "8859_5", "LATIN 5", "LATIN-5"]:
        theEncoding = "8859_5";
        break;
 
    case ["ISO-8859-6/LATIN-6", "ISO-8859-6","8859-6", "8859_6", "LATIN 6", "LATIN-6"]:
        theEncoding = "8859_6";
        break;
        
    case ["ISO-8859-7/LATIN-7", "ISO-8859-7","8859-7", "8859_7", "LATIN 7", "LATIN-7"]:
        theEncoding = "8859_7";
        break;

    case ["ISO-8859-5/LATIN-8", "ISO-8859-8","8859-8", "8859_8", "LATIN 8", "LATIN-8"]:
        theEncoding = "8859_8";
        break;

    case ["ISO-8859-5/LATIN-9", "ISO-8859-9","8859-9", "8859_9", "LATIN 9", "LATIN-9"]:
        theEncoding = "8859_9";
        break;

    default:
        //Invalid Type provided
        return false;
} 

eolFormat = eolFormat.toUpperCase();

//Determine if the provided eolForm is valid, otherwise return false.
switch ( eolFormat ) {
    case ["WIN", "WINDOWS", "CR+LF", "CRLF", "\r\n"]:
        eolFormat = "\r\n";
        break;
 
    case ["MAC", "CR", "\r"]:
        eolFormat = "\r";
        break;
 
    case ["UNIX", "UNX", "LINUX", "LNX", "MAC OS X", "\n"]:
        eolFormat = "\n";
        break;

    default:
        //Invalid Type provided
        return false;
}

//The BufferedReader reads lines without the EOL, just append the selected EOL
BufferedReader br = new BufferedReader( new StringReader ( theText ));
StringBuilder sb = new StringBuilder();
for (;;) {
    String line = br.readLine();
    if (line == null)
        break;
    sb.append(line + eolFormat);
}
theText = sb.toString();

//This more advanced version allows you to specify the character encoding
OutputStreamWriter writer = new OutputStreamWriter( new FileOutputStream( filePath ), theEncoding );
writer.write( theText );
writer.close();
return true;
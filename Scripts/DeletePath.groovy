/* 
Purpose: Deletes the provided folder/file (recursive)
Returns: Boolean

Name: DeletePath ( pathToDelete )

Parameters: 	( pathToDelete ) filepath

Dependencies: NONE

2011-10-24 JPS, Created.

NOTES:
It will recursively delete subdirectories, so be careful!
*/

if( pathToDelete == null ) throw new Exception("You must provide a pathToDelete");
File toDelete = new File(pathToDelete);
if( ! toDelete.exists() ) {
	return false;
} else {
	deleteRecursive( toDelete );
}

void deleteRecursive( File fileToDelete ) throws IOException {
	if( fileToDelete.isDirectory() ) {
		File[] children = fileToDelete.listFiles();
		for( int n=0; n<children.length; n++ ) {
			deleteRecursive( children[n] )
		}
	}
	if( ! fileToDelete.delete() ) {
		throw new IOException("Could not delete " + fileToDelete.getAbsolutePath());
	}
}
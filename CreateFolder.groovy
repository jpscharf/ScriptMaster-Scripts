/* 
Purpose: Creates a folder using the provided path.
Returns: Boolean

Name: CreateFolder ( pathToCreate )

Parameters: 	( pathToCreate ) filepath

Dependencies: NONE

2011-09-06 JPS, Created, adapted from Scriptmaster module.

NOTES:
NONE
*/


File dir = new File(pathToCreate);
if( dir.exists() ) return false;
if( dir.mkdirs() ) { //Success!
	return true;
} else { //Figure out why it failed
	File realParent = dir;
	while( realParent != null && ! realParent.exists() ) {
		realParent = realParent.getParentFile();
	}
	if( realParent != null && !realParent.canWrite() ) {
		throw new FileNotFoundException("Directory " + dir.getAbsolutePath() + " could not be created because the parent directory " + realParent.getAbsolutePath() + " is not writeable" );
	} else {
		throw new FileNotFoundException("Directory + " + dir.getAbsolutePath() + " could not be created because of an unknown error." );
	}
}
/* 
Purpose: Wrapper for the ScriptMaster fmpro.executeSQL method.
Returns: Formatted list of the results.

Name: ExecuteSQL ( theQuery ; columnDelimiter ; rowDelimiter )

Parameters: 	( theQuery ) the SQL query
            	( columnDelimiter ) single character
			    ( rowDelimiter ) single character

Dependencies: NONE

2011-09-01 JPS, Created.
2011-09-01 JPS, Updated to determine the query type, and return false if the user is attempting to CREATE or ALTER tables.

NOTES:
NONE
*/

//DETERMINE THE QUERY TYPE
def queryType = theQuery.split (" ")
queryType = queryType[0].toUpperCase()

switch ( queryType ) {
    case "SELECT":
        eolFormat = "\r\n";
        break;
 
    case "INSERT":
        eolFormat = "\r";
        break;
 
    case "UPDATE":
        eolFormat = "\n";
        break;

    case "UPDATE":
        eolFormat = "\n";
        break;

    case "DELETE":
        eolFormat = "\n";
        break;

    case "CREATE":
    case "ALTER":
    default:
        //Invalid or unsuported statement provided
        return false;
}

//SELECT INSERT UPDATE DELETE CREATE ALTER
fmpro.executeSql(theQuery, columnDelimiter, rowDelimiter)
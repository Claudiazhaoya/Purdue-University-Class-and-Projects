Brian Shrawder
bshrawde

the password file will take in a string from a file and test it for stregth
bassed on critera given and then ouput the result to command line.

the backup file will take in a file a directory name, a interval and a max
backups.
It will find the newsest file in the directory and check against the file for
changes, if changed it will backup and send the diffrence to the users emai. 
It will keep the number of backups up to the max given 

I have added one print statment to the backup file in the first if that finds
if the given directory exists. if it does i want to do nothing for the time.
	but in bash i need to have someting in the if before the else so i put
	print statment in to not get an error as per a ta suggetion.

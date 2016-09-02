/*
 * workers.h
 *
 * Header file to include the common definitions and declarations.
 *
 */

#ifndef WORKERS_H_
#define WORKERS_H_

#include "interface.h"

/*
Define your variables, funtcions, structs etc. here
*/

int place=0;//placement in the input file array
int filelength;//finds the length of the input file
int wordnum=0;//number of keywords
int charnum=0;
int inputplace=0;
int keywordnum=0;
int i=0;
int splitplace=0;
int splitnum=0;
int keyin=0;
int startpostion=0;
int currentpostion=0;
int pid[MAX_PROCS];
int testpostion=0;
int counttest=0;
int testnum=0;
int wordplace=0;


char charnumin;//used to get the characters of the input file
char charin; 
char** inputarray;//keyword array?
char wordin;//for finding the number of keywords.
char wordnumin;
char* temp;
char wordtest[25];
char test[MAX_BUFFER_SIZE];




#endif /* WORKERS_H_ */

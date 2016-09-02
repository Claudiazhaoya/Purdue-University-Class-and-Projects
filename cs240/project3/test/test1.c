#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#define MAXLINE 2048 

int glob= 6; /*external variable in intialized data */
char buf[] = "a write to stdout\n";

int main(void)
{
int var; /* automatic variable on the stack */

pid_t pid; //note pid can be an int or of type pid_t

var = 88;

if(write(STDOUT_FILENO, buf, sizeof(buf)-1) != sizeof(buf)-1){
 perror("write error");
 exit(1);
}

printf("before fork\n"); /* we don't flush stdout */

if((pid = fork()) < 0) {
 perror("fork error");
 exit(1);
}else if( pid == 0 ) {
 /* child */
 glob++; // modify variables
 var++;
} else {
 /* parent */
 sleep(2); 
}

printf("pid = %d, glob = %d, var = %d\n", getpid(),glob, var);

exit(0);

}

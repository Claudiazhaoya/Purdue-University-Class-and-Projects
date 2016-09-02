/*
 * workers.c
 * Implementation for a parallel search using processes.
 * Only the parent process is allowed to FORK.
 * (not following the divide and conquer technique).
 * 1- Parent creates a shared buffer
 * 2- Parent FORK processes and make each one has its own buffer. the parent will be
 *    responsible of reading the file and the childs will do the search.
 * 3- First FORKed child process will search in first chunk of the file, 2nd FORKed child process will search in 2nd chunk and so on..
 * 4- each child reads a part of the input file and will be responsible of
 *    doing search in that part. Child process writes struct proc_key_count (defined in interface.h)
 *	  to the shared memory
 */
/**********************************************************************													List of includes
 **********************************************************************/
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/syscall.h>
#include <sys/mman.h>
#include <sys/sem.h>
#include <sys/shm.h>
#include <sys/types.h>
#include <string.h>
#include <errno.h>			
#include <stddef.h>
#include <semaphore.h>    
#include "interface.h"
#include "workers.h"
#include <stdarg.h>
#include <pthread.h>
/**************************** global variables *********************************/
char** keywords             = NULL;     /* array of keywords */
int    keys_cnt             = 0;        /* number of keywords read from the file */
int    shm_id               = 0;        /* shared memory id */
void*  shared_mem         = NULL;     /* shared memory value created by SHMGET */
/**************************** Done global variables *********************************/
void destroy_sharedmem() {
	/*Function to check if all data stored in shared memory is correct.*/
	shmem_checker();
	/*
	Write your code to destroy shared memory here.
	*/
}
int get_sh_mem(size_t* size){
	int _shm_id = shmget(IPC_PRIVATE, *size, 0666 | IPC_CREAT | IPC_EXCL );
		return _shm_id;
		}
void init_memory(int* mem_id){
	size_t _data_size = sizeof(struct proc_key_count*);
	*mem_id = get_sh_mem(&_data_size);
	shared_mem = shmat(shm_id, NULL, 0);
	memset(shared_mem, 0,_data_size);
	}
int main(int argc, char *argv[]) {
	/*
	Write all your code here.
	Set keys_cnt, keywords, shm_id and shared_mem appropriately in your code. 
	These variables are defined and described in interface.h
	*/
	FILE* input=stdin;
	FILE* output=stdout;
	FILE* keyword=stdin;
	int inputnum=0;
	if(argc<6){//if command line arguments have less than two end program
		return 0;
		}
		//searches command line for the input file output file and the keyword file.
		for(inputnum=0; inputnum<argc;inputnum++){
			if(strcmp(argv[inputnum],"-i")==0){
				input=fopen(argv[inputnum+1],"r");
				}
			if(strcmp(argv[inputnum],"-o")==0){
				output=fopen(argv[inputnum+1],"w");
				}
			if(strcmp(argv[inputnum],"-k")==0){
				keyword=fopen(argv[inputnum+1],"r");
				}
			}
	fseek(input,0,SEEK_END);
	filelength=ftell(input)-1;
	fseek(input,0,SEEK_SET);
	char file[filelength]; //char array that has the input file
	
		while((charin=fgetc(input))!=EOF){//sets the char array
			file[place]=charin;
			place++;
			}
			file[place]='\0';
			
		//finds the number of keywords
		while((wordnumin=fgetc(keyword))!=EOF){
			if(wordnumin=='\n'){
				wordnum++;
				}
			}
		fseek(keyword,0,SEEK_SET);
		//gets the file 
		keywords=(char**)calloc(wordnum, sizeof(char*));
		temp=(char*)malloc(100);
		while((keyin=fgetc(keyword))!=EOF){
			if(keyin=='\n'){
				temp[i+1]=='\0';
				*(keywords+inputplace)=temp;
				inputplace++;
				i=0;
				temp=(char*)malloc(100);
				continue;
			}
			temp[i]=keyin;
			i++;
		}
		//FORKing area.
		shared_mem = shmat(shm_id, NULL, 0);
		init_memory(&shm_id);
	
while(splitnum<MAX_PROCS){
//child proccess
	if(pid[splitnum]=FORK()==0){
		shared_mem = shmat(shm_id, NULL, 0);
		struct proc_key_count* info = (struct proc_key_count*) shared_mem +(splitnum*wordnum);
		int counter=0;
		while(counter<MAX_BUFFER_SIZE){
			test[testpostion]=file[currentpostion];
			currentpostion++;
			counter++;
			testpostion++;
			}
			test[testpostion]='\0';
			while(counttest<MAX_BUFFER_SIZE){
				wordtest[wordplace]=test[counttest];
				if((wordtest[wordplace]==' ')||(wordtest[wordplace]=='\t')||
					(wordtest[wordplace]=='\n')){
					wordtest[wordplace]='\0';
						wordplace=-1;
							while(testnum<wordnum){
								if(strcmp(wordtest,keywords
								[testnum])==0){
									fprintf(stderr,"found\n");
									}
								testnum++;
						}
						testnum=0;
					}
				wordplace++;
				counttest++;	
				}
				
			
			_exit(EXIT_SUCCESS);
			}
			
							
		else if((pid[splitnum]<0)){
				//error
			exit(1);
			}	
		else{
			
				}
			splitnum++;
			currentpostion=currentpostion+MAX_BUFFER_SIZE;
			//fprintf(stderr,"currentpostion:%d\n",currentpostion);
			}
	destroy_sharedmem();
	return 0;
}//main

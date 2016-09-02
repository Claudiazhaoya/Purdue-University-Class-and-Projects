/*
 * interface.h
 *
 *DO NOT CHANGE THIS FILE. YOU ARE NOT SUPPOSE TO UPLOAD THIS FILE ON AUTOGRADER
 */

#ifndef INTERFACE_H_
#define INTERFACE_H_

#include <stdio.h>
#include <stdarg.h>
#include <sys/types.h>
#include <sys/sem.h>

#ifndef MAX_PROCS
#define MAX_PROCS 			3            /* maximum number of processes */
#endif

#ifndef MAX_BUFFER_SIZE
#define MAX_BUFFER_SIZE 256    			 /* maximum  buffer size */
#endif


#define FORK fork

#define SEMGET semget

#define SHMGET shmget

/**
 * Struct to keep track of the relation between the process and
 * the keywords frequency
 */
struct proc_key_count {
	pid_t pid;	  /* process id */
	int   key_id; /* index of the keyword in the keyword list*/
	int   freq;   /* frequency of occurrence. If no match found the
	                 result should be zero */
}__attribute__((__packed__));

/* count of the keywords we are searching for */
extern int keys_cnt;

/* array of strings of keywords */
extern char** keywords;

/*shared memory id*/
extern int shm_id;

/* shared memory where the results are stored */
extern void* shared_mem;

/*********************Function Definitions*************************/

/*
 * checks the result returned from the processes.
 *
 * @shm   pointer to the shared memory from which the array of
 * struct proc_key_count begins.
 */
void shmem_checker() {}


#endif /* INTERFACE_H_ */

/*
 * proj4_demo.c
 *
 * Copyright (C) 2013 Computer Science, Purdue University
 *
 * Created on: Apr 2, 2013
 *
 * Sharing data between processes using shmget and posix semaphores.
 *
 * compile command:
 *     gcc -g -o demo -lrt -m32 -pthread proj4_demo.c
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdarg.h>
#include <sys/shm.h>				/* for shared memory */
#include <sys/types.h>
#include <semaphore.h>
#include <pthread.h>
#include <errno.h>			    /* errors terminating the child processes */

/*******************************Macros****************************************/
#define DEBUG	              1

#define MAX_LOG_BUFFER			1024  /* maximum size of the log used to display
                                     messages and it is also used to read the
                                     keywords
                                   */

int __print_msg(FILE* out, pid_t pid, const char* func, const char* format, ...) {
	va_list ap;
	char buf[MAX_LOG_BUFFER];
	snprintf(buf, MAX_LOG_BUFFER, "pid:%d; func:%s >> ", pid, func);
	if(format){
		va_start(ap, format);
		vsnprintf(buf + strlen(buf), MAX_LOG_BUFFER, format, ap);
		va_end(ap);
	}
	fprintf(out, "%s", buf);
	return 0;
}

#define CONDITION(cond)	__builtin_expect((cond)!=0,0))

#define PRINTE(...) 		__print_msg(stderr, getpid(), __func__, __VA_ARGS__)

#if DEBUG
	#define PRINTI(...) 	__print_msg(stdout, getpid(), __func__, __VA_ARGS__)
#else
	#define PRINTI(...) ((void)0)
#endif

#define BAIL(...) \
	PRINTE(__VA_ARGS__); \
	exit(1)

/******************************DataTypes**************************************/
typedef struct SharedStruct_S{
	int val1;
	int val2;
}SharedStruct;


/****************************Global Variables***********************************/

/* id of the shared memory segment */
int shm_id;

/* memory address of the shared segment */
void* shared_mem = NULL;

/* semaphore id used to synchronize on the shared memory */
sem_t lock;


/*
 * Create a shared memory segment using the IPC_PRIVATE key.
 *
 * @size pointer to a size_T to determine the size of the memory segment.
 *
 * - Parameters passed shmget:
 *       * The permissions 0666 gives all the entities the write to read/write
 *         to the memory segment
 *       * Throws an error if already exists
 *       * Create if it does not exist
 */
int get_sh_mem(size_t* size){
	int _shm_id = shmget(IPC_PRIVATE, *size, 0666 | IPC_CREAT | IPC_EXCL );
	if(_shm_id == -1){
		BAIL("error in shmget\n");
	}
	return _shm_id;
}

/*
 * Initializes the memory by creating a segment of size equal to the size of
 * struct(SharedStruct)
 */
void init_memory(int* mem_id){
	size_t _data_size = sizeof(SharedStruct);
	*mem_id = get_sh_mem(&_data_size);
	shared_mem = shmat(*mem_id, NULL, 0);

  if (shared_mem == NULL ) {
  	BAIL("error attaching memory\n");
  }

  memset(shared_mem, 0, _data_size);
}


/*
 * detach the memory from the parent process, and send a control request to remove the memory id
 * Assumptions: called by the parent and it should be called only once.
 */
void destroy_shmem(void* sh_mem, int shm_id){
  int _res = shmdt(sh_mem);		/* detach the memory */
  if (_res == -1) {
      BAIL("error: detaching failed\n");
  }
  struct shmid_ds shmid_struct;
  _res = shmctl(shm_id, IPC_RMID, &shmid_struct);
  if (_res == -1) {
  	BAIL("main: shmctl (removing) failed\n");
  }
}

/*
 * code executed by the child after being forked
 */
void child_process(){
	PRINTI("Child Starting\n");
	shared_mem = shmat(shm_id, NULL, 0);
	if(shared_mem == NULL) {
		 BAIL("child process: shmat() failed\n");
	}
	SharedStruct* data = (SharedStruct*) shared_mem;
	sem_wait(&lock);
	data->val1 = 5;
	data->val2 = 5;
	sem_post(&lock);

  int _res = shmdt(shared_mem);
  if (_res == -1) {
      BAIL("child process: shmdt() failed\n");
  }
  PRINTI("Child Leaving\n");
  exit(0);
}

/*
 * code executed by the parent after forking the child processes
 */
void parent_process(){
	PRINTI("Parent Starting\n");
	SharedStruct* data = (SharedStruct*) shared_mem;
	sem_wait(&lock);
	data->val1 = 10;
	data->val2 = 10;
	sem_post(&lock);

	PRINTI("Parent waiting child processes\n");
	/* waiting all processes to terminate*/
	pid_t child;
	while ((child = waitpid(-1, NULL, 0))) { //-1 because we really do not care which child terminates first
		if (errno == ECHILD) {
			break;
	  }
	}
}

void execute_process(void (*func)(void)){
	func();
}

int main(){

	init_memory(&shm_id);

	/* make sure we initialize semaphores before any fork */
	sem_init(&lock, 1, 1);

	pid_t pid;

	if ((pid = fork()) == -1) {		/* fork a child */
		BAIL("error calling fork()\n");
	}

	if(pid == 0) { //child process
		execute_process(child_process);
		return 0;
	}

	execute_process(parent_process);
	SharedStruct* poin = (SharedStruct*)shared_mem;

	PRINTI("data in shared memory: val1=%d;val2=%d \n",
			poin->val1, poin->val2);

	/* destroy the semaphore */
	sem_destroy(&lock);

	destroy_shmem(shared_mem, shm_id);

	PRINTI("Parent Leaving\n");

	return 0;
}

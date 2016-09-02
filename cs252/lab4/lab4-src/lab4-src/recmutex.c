#include <stdio.h>
#include <pthread.h>
#include <errno.h>
#include "recmutex.h"

int recursive_mutex_init (recursive_mutex_t *mu)
{	
	
	int initcheck = pthread_mutex_init(&(mu->mutex),NULL);
	pthread_cond_init(&(mu->cond),NULL);
		if(initcheck != 0){
			return 1;
		}
		mu->owner = 0;
		mu->count = 0;
		mu->wait_count = 0;
		return 0;
}

int recursive_mutex_destroy (recursive_mutex_t *mu)
{
	if((mu->count)!=0){
		int errcheck = pthread_mutex_destroy(&(mu->mutex));
		pthread_cond_destroy(&(mu->cond));
		if(errcheck != 0){
			return 1;
		}
	}
	mu->count=0;
	return 0;
}

int recursive_mutex_lock (recursive_mutex_t *mu)
{	
	int lockcheck = pthread_mutex_lock (&(mu->mutex));
	if(mu->count==0){
		mu->owner = pthread_self();
		mu->count ++;
			}
		int owncheck = pthread_equal(pthread_self(),mu->owner);
	if(owncheck!=0){   //pthread_self()==mu->owner;
		mu->count++;
	}
	if(owncheck==0){
		while(mu->count!=0){
				fprintf(stderr,"before wait\n");
				fprintf(stderr,"count:%d\n",mu->count);
				fprintf(stderr,"test\n");
			//if(mu->owner==0){
			//	break;
			//}
			pthread_cond_wait(&(mu->cond),&(mu->mutex));
				fprintf(stderr,"after wait\n");
				
		}
			fprintf(stderr,"num:%d\n",mu->count);

			mu->owner = pthread_self();
			mu->count ++;
			
	}
	pthread_mutex_unlock(&(mu->mutex));
	return 0;
}

int recursive_mutex_unlock (recursive_mutex_t *mu)
{	
	int lockcheck = pthread_mutex_lock (&(mu->mutex));
	if(lockcheck!=0){
		pthread_mutex_unlock(&(mu->mutex));
		return 1;
	}
	if(mu->count <=0){
		pthread_mutex_unlock(&(mu->mutex));
		return 1;
		
	}else{
		mu->count--;
		fprintf(stderr,"countnum::%d\n",mu->count);
		if(mu->count==0){
			pthread_cond_signal(&(mu->cond));	
		}
	}
	pthread_mutex_unlock(&(mu->mutex));
	return 0;
}

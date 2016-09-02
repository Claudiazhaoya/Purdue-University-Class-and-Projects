#include <sys/types.h>
#include <stdlib.h>
#include <stdio.h>


/*
 * Implement the following string procedures.
 *
 * Type "man strstr" to find what each of the functions should do.
 *
 * For example, mystrcpy should do the same as strcpy.
 *
 * IMPORTANT: DO NOT use predefined string functions.
 */

char *mystrcpy(char * s1, const char * s2)
{
	int i;
	for(i=0;s2[i] !='\0';++i){
		s1[i] = s2[i];
	}
	s1[i]='\0';
	return s1;
}

size_t mystrlen(const char *s)
{
  const char *len;
  len = s;
  while( *s !='\0'){
	  ++s;
  }
  return s-len;
}

char *mystrdup(const char *s1)
{
  char *new = malloc (mystrlen(s1)+1);
  mystrcpy(new,s1);
  return new;
}
char *mystrcat(char * s1, const char * s2)
{
	mystrcpy(s1 + mystrlen(s1), s2);
	return s1;
}

char *mystrstr(char * s1, const char * s2)
{
  char *first = (char*)s1;
  while(*first){
	  char*start = first;
	  char*sub = (char*)s2;
	  while(*first == *sub){
		  first++;
		  sub++;
	  }
	  if(*sub=='\0'){
		  return start;
	  }
	  first = start+1;
  }
  return '\0';
}

int mystrcmp(const char *s1, const char *s2) {
	if(*s1 != *s2){
		return *s1-*s2;
	}
	for(; *s1 == *s2; ++s1, ++s2){
		if(*s1 == '\0'){
			return 0;
		}
}
}


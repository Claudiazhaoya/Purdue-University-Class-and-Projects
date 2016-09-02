#include <stdio.h>
#include <string.h>
#include "list.h"
#include "sort.h"
/*
 * Compares n1 and n2 based on the words stored in nodes.
 * Return -1 if word in n1 less than word in n2
 * Return  0 if word in n1 is equal to word in n2
 * Return  1 if word in n1 is greater than  word in n2
 */
int wordCmp (struct lnode* n1, struct lnode* n2) {
  /* FILL IN HERE */
}
/*
 * Compares n1 and n2 based on the lines stored in nodes.
 * Return -1 if line in n1 less than line in n2
 * Return  0 if line in n1 is equal to line in n2
 * Return  1 if line in n1 is greater than  line in n2
 */
int lineCmp (struct lnode* n1, struct lnode* n2) {
  /* FILL IN HERE */
}
/*
 * Compares n1 and n2 based on the counts stored in nodes.
 * Return -1 if count in n1 less than count in n2
 * Return  0 if count in n1 is equal to count in n2
 * Return  1 if count in n1 is greater than  count in n2
 */
int countCmp (struct lnode* n1, struct lnode* n2) {
  /* FILL IN HERE */
}
/*
 * Swap nodes n1 and n2 in the linked list. For eg, if the
 * list looks like: -- n1 -- n2 -- , then after swapping n1
 * with n2, it should look like this: -- n2 -- n1 --.
 * head is the pointer to the head pointer of the linked list.
 */
void swap (struct lnode** head, struct lnode* n1, struct lnode* n2) {
  /* FILL IN HERE */
}
/*
 * Sorts the linked list whose head is (*head).
 * Based on this description, complete the function signature of sort
 */
void sortByWord (struct lnode** head) {
  /* FILL IN HERE */
}

void sortByCount (struct lnode** head) {
  /* FILL IN HERE */
}

void sortByLine (struct lnode** head) {
  /* FILL IN HERE */
}



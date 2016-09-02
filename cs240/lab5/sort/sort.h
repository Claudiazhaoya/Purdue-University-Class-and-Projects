/*
 * sort.h
 * You will not submit this file. This is for you to
 * include in your test.c when you are testing your 
 * functions
*/

int wordCmp   (struct lnode* n1, struct lnode* n2);
int lineCmp   (struct lnode* n1, struct lnode* n2);
int countCmp  (struct lnode* n1, struct lnode* n2);

void swap     (struct lnode** head, struct lnode* n1, struct lnode* n2);

void sortByWord     (struct lnode** head);
void sortByCount    (struct lnode** head);
void sortByLine     (struct lnode** head);

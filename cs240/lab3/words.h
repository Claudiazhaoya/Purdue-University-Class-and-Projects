#define SIZE 140

int readMessage(char* buf);
int getNextWordIndex(char* str, int len, int prevEnd);
void matchAndBlankWord(char* str, int start, int wLen, char* keyword);
void lowerCaseString(char* str, int len);
int wordLength(char* str, int strLen, int start);

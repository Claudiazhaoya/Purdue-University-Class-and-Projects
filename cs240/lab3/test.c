
int main(int argc, char** argv) {
  char msg[SIZE];
	int length;
	//char word[];
	int prevEnd, wLen;
	int word, wordStart;


  // read in every line of the tweets
  // till there are no more lines
  while ( (length = readMessage(msg)) != EOF ) {
	wordStart=0;
	wLen = 0;
    // turn the string to lower case
	lowerCaseString(msg, length);
    // get every word in the current line
    // until there are no more words
    do {


	wLen = wordLen( msg, length, wordStart );



// printf("%d",wLen);



      //get length of the current word

	for ( int x = 0 ; x < argc; x++) {
		matchAndBlankWord( msg, wordStart, wLen, argv[x]);

	}
wordStart = getNextWordIndex(msg, length, wordStart + wLen);


      // check the word against each keyword
 //     for(int x = 0; x < 5; x++) {
//		matchAndEraseWord(msg, wordStart, prevEnd - wordStart, keys);
//
//	}

    } while ( wordStart != -1 );
    // output the processed message
	for (int x = 0; x < length; x++) {
		putchar(msg[x]);
	}
  }
  return 0;
}

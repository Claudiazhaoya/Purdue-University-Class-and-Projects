For some reason it will ocasianlly go into an infinite loop. I have no idea why.
When I compile again and run it is fine. 
It also has a odd issue when run where it will print a stack trace
of Exception in thread. I could not work this issue out. I join the threads to make sure they are done 
and have checks so I am not sure. However I do not think it effects my code that much as there are still 
almost 4000 entries in the array-list. which I expect due to there being 4000 entries with some bad scattered 
throughout.

I have all of the queue and the teller parts done.
The tellers are on seperate threads one has a 1 second delay the other a two second delay.
 but the transactions are not complete. They will 
detect if there is incorrect syntax on the input transactions. Such as no value for deposited or withdraw or transfer
The transactions can detect if it needs a transfer deposit or withdraw but can not do it as of now.
I do use saving and checking .java They are in the Make Account method of Bank Teller.java around line 60.

IF there is any questions please let me know at bshrawde@purdue.edu
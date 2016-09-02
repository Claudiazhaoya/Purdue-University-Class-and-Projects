int isLittleEndian()
{
     int i = 5;
     char *  p = (char *) &i;
     printf("p"+*p);
      if (*p==5) {
        return 1;
     }
     return 0;
}

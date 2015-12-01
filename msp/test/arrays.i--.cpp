# 1 "../test/arrays.c--"
# 1 "<built-in>"
# 1 "<command line>"
# 1 "../test/arrays.c--"
int arr[10];

main () : int
{ int i;
  i = 1;
  while ( i < 11 )
  { arr[i] = i;
    i = i + 1;
  };
  i = 1;
  while ( i < 11 )
  { print (arr[i]);
    i = i + 1;
  };
};

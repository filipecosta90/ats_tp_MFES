# 1 "heap.c--"
# 1 "<built-in>"
# 1 "<command line>"
# 1 "heap.c--"







char heap[50];
int heapIndex[50];


int freeIndex;

main() : int
{
  char a ;
  char b ;
  char c ;
  char d ;
  char e ;
  char nl;

  nl = 10;

  init_heap();

  a = ler_lista();

  b = ler_lista();

  show_lista(a);

  print nl;

  print head(a);

  print nl;

  show_lista (tail(a));

  free_lista(a);

  c = ler_lista();

  show_lista(c);







  print elem(71,c);
};
# 63 "heap.c--"
init_heap() : int
{ int i;
  i = 1;

  freeIndex = 1;
  while ( i < 15 )
   { heapIndex[i] = i + 1;
     i = i + 1 ;
   };
  heapIndex[i] = -1 ;
};


cons_lista(int a , int l) : int
{
  int aux ;

  if ( freeIndex == -1 ) then
    { cons_lista = l; }
  else
    { aux = freeIndex;
      freeIndex = heapIndex[freeIndex];



      heap[aux] = a;

      heapIndex[aux] = l ;

      cons_lista = aux ;
    };
};


show_lista(int l) : int
{ int i ;
  i = l ;
  while (heapIndex[i] > 0)
   { print heap[i];
     i = heapIndex[i];
   };
};


free_lista(int l) : int
{ int i ;
   int aux ;

   i = l;
   while (heapIndex[i] > 0)
    { heap[i] = 0 ;
      aux = i ;
      i = heapIndex[i];
    };

   heapIndex[aux] = freeIndex ;
   freeIndex = l;
};
# 138 "heap.c--"
head(int l) : int
{ head = heap[l];
};


tail(int l) : int
{ tail = heapIndex[l];
};


elem (int e , int l) : int
{ if (l == -1) then
    { elem = 0; }
  else
    { if ( e == head(l)) then
        { elem = 1 ; }
      else
        { elem = elem(e,tail(l)); };
    };
};


ler_lista() : int
{ char c;
  int l;
  l = -1;
  input c;
  while (c > 0)
   { l = cons_lista(c,l);
     input c;
   };
  ler_lista = l ;
};

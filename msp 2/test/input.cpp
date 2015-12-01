# 1 "../test/input"
# 1 "<built-in>"
# 1 "<command line>"
# 1 "../test/input"
int a;
int b;
int c;
int a;


fact (int x) : int
{
  fact = 1;
  while ( x > 0 )
  { fact = fact * x;
    x = x - 1;
  };
};

recfact (int x) : int
{ if ( x == 0) then
   { recfact = 1;}
  else
   { recfact = x * recfact (x - 1); };
};


fib (int n) : int
{
  if ( n == 0 ) then
  { fib = 0; }
  else
  { if (fib == 1 ) then
    { fib = 1; }
    else
    { fib = fib(n - 2) + fib(n - 1); };
  };
};



power (int x,int n): int
{ if (n == 0) then
    { power = 1; }
  else
    { power = x * power(x, n - 1); };
};


int k;
int ac[200];
int i;
int j;

substr ( int len): bool
{
  k = 0;
  i = 0;
  while ( (! ((ac[i]) == 0) ) && ( k < len) )
  { j = i;
    k = 0;
    while (( a[j] == b[k] ) && ( k < len ) )
    { j = j + 1;
      k = k + 1;
    };
    i = i + 1;
  };
  if ( k == len ) then
   { substr = true; }
  else
   { substr = false; };
};


int x;
int y;

maior (int x, int y): int
{
  if (x > y) then
    { maior = x; }
  else
    { maior = y;};
  print maior;
};

int z;
ex2 (int x,int y) : real
{
  if ((x + y) > 21) then
    { z = x + y * 2; }
  else
    { if ( x == 7) then
        { z = 7 * x + y; }
      else
        { if ( x == y) then
            { z = 3 * y + 5; }
          else
            { };
        };
    };
  print z;
};

int r;
int taxa;
real desc;
ex3 () : int
{
  input r;
  if ((r < 20) || (r == 20)) then
    { taxa = 5; }
  else
    { if ((r > 20) && ((r < 50) || (r == 50))) then
        { taxa = 4; }
      else
        { if (((r > 51) || (r == 51)) && ((r < 70) || (r == 70))) then
            { taxa = 3; }
          else
            { taxa = 2; };
        };
    };
  desc = r / taxa;
  print desc;
};

int n;
impar () : int
{
  input n;
  impar = 1;
  i = 1;
  while ( (i < n) || (i == n))
  { print i;
    impar = impar + 2;
    i = i + 1;
  };
};

int a;
int b;
real res;
expo () : real
{
  input a;
  input b;
  res = 1;
  while (b > 0)
  { res = res * a;
    b = b - 1;
  };
  print res;
};

int a1;
int b1;
int resto;
int quociente;
calc_quoc () : int
{
  input a1;
  input b1;
  quociente = 0;
  while ((a > b) || (a == b))
  { a = a - b;
    quociente = quociente + 1;
  };
  resto = a;
  print (quociente);
  print (resto);
};

int letra;
minmaiu (int letra): bool
{
  input letra;
  while ( letra == 32 )
  { if ((letra > 46) && (letra < 56)) then
    { letra = chr(ord(letra) - 32); }
    else {};
    print (letra);
    input letra;
  };
};

int cont;
int soma;
media (int n) : real
{
  cont = 0;
  soma = 0;
  input n;
  while ( n > 0 )
  { soma = soma + n;
    cont = cont + 1;
    input n;
  };
  if ( cont > 0 ) then
    { media = soma / cont;
      print media;
    }
  else {} ;
};

int arr[10];
prearr () : int
{
  i = 1;
  while ( i < 10 )
  { arr[i] = i;
    i = i + 1;
  };
  i = 1;
  while ( i < 10 )
  { print (a[i]);
    i = i + 1;
  };
};

int s[10];
int car;
conv () : int
{
  i = 1;
  input car;
  while (((i < 10) || (i == 10)) && (! (car == 32)))
  {
    if ((car > 46) && (car < 56)) then
     { n = n - 32; }
    else
     { };
    s[i] = car;
    i = i + 1;
    input car;
  };
  i = 1;
  while ( i < 10 )
  { print (s[i]);
  };
};

int occor;
int tab[10];
contocc (): int
{
  i = 0;
  occor = 0;
  input n;
  while ( i < 10 )
  { if (tab[i] == n) then
      { occor = occor + 1; }
    else { };
    i = i + 1;
  };
 contocc = occor;
};

maior3 (int a, int b, int c) : int
{
  int m;

  if ( (a > b) || ( a == b)) then
  { if ( ( a > c) || ( a == c)) then
      { m = a; }
    else
      { m = c; };
  }
  else
  { if ( ( b > c) || ( b == c )) then
      { m = b; }
    else
      { m = c; };
  };
 maior = m;
};

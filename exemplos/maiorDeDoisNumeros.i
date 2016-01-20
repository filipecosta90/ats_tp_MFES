void main() {
  int a;
  int b;
  int c;
  //teste comentario
  int res, res2;
  a = input(int);
  b = input(int);
  c = input(int);
  res = max(a,b);
  print(';');
  print(res);
  res2 = max3numbers( a,b,c );
  print(';');
  print(res2);
  if (a == b){
      a = a;
      a = a+1;
  }
}

int max(int a, int b){
  int res;
  int unused;
  //teste comentario 
  if (a > b) {
    if (a > b) {
      if (a > b) {
        if (a > b) {
          if (a > b) {
      res = a;
          }
        }
          }
    }
  }
  else {
    res = b;
  }
  // teste comentario 3
  if (a == b) {
          a = a + b;
          b = b + b;
          b = b * b;
  }
  return res;
}

int max3numbers (int a, int b, int c){
  //teste comentario
  //teste comentario
  //teste comentario
  int res;
  if (a>b){
    if (a>b){
      if (a>b){
      res = a;
      a = b - a;
      b = b / a;
      }
    }
  }
  else {
    res = b;
  }
  return res;
}

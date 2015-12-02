void main() {
  int a;
  int b;
  int c;
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
}

int max(int a, int b){
  int res;
  if (a > b) {
    res = a;
  }
  else {
    res = b;
  }
  return res;
}

int max3numbers (int a, int b, int c){
  int res;
  if (a>b){
    res = a;
  }
  else {
    res = b;
  }
  if (res < c){
    res = c;
  }
  return res;
}

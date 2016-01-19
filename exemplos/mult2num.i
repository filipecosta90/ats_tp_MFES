void main (){
  int a;
  int b;
  a = input(int);
  b = input(int);
  int res;
  res = mult2num(a,b);
  print(res);
}

int mult2num (int a, int b){
  //teste comentario
  int res;
  int unused;
  res = 0;
  while(b>0){
    res = res + a;
    b=b-1;
  }
  return res;
}

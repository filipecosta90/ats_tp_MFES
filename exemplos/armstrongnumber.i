void main(){
  int n, r;
  n = input(int);
  r = armstrongNumber(n);
  if(r == 1){
    print('y');
  }else{
    print('n');
  }
}

int armstrongNumber(int n){
  int temp;
  int soma;
  soma = 0;
  int digits;
  digits = 0;
  temp = n;
  while(temp != 0){
    digits = digits + 1;
    temp = temp/10;
  }
  temp = n;
  int rem;
  while (temp != 0) {
    rem = calculateRemainder(temp,10);
    soma = soma + power(rem, digits);
    temp = temp / 10;
  }
  int res;
  if(n == soma){
    res = 1;
  }else{
    res = 0;
  }
  return res;
}

int calculateRemainder(int dividend, int divisor){
  int remainder;
  int tempdivisor;
  tempdivisor = divisor;
  if(dividend == divisor){
    remainder = 0;
  }
  else if (dividend < divisor){
    remainder = 1;
  }
  while(tempdivisor < dividend + 1){
    tempdivisor = tempdivisor + divisor;
  }
  int aux;
  aux = tempdivisor - divisor;
  remainder = dividend - aux;
  return remainder;
}

int power(int n, int r) {
  int c;
  int p;
  p = 1;
  c = 1;
  while (c < r + 1){ 
    p = p * n;
    c = c + 1;
  }
  return p;   
}

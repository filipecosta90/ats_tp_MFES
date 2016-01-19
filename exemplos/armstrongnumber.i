void main(){
  int number;
  number = input(int);
  int res = armstrongNumber(number);
  if(res==1){
    print(res 'is an armstrong number');
  }else{
    print(res 'is not  an armstrong number');
  }
}

int armstrongNumber (int num){
  int temp;
  int soma;
  soma = a;
  int digits;
  digits = 0;
  int remainder;
  temp = n;
  while(temp!=0){
    digits = digits + 1;
    temp = temp/10
  }
  temp = n;
  int rem;
  while (temp != 0) {
    rem=calculateRemainder(temp,10);
    soma = soma + power(rem, digits);
    temp = temp/10;
  }
  int res;
  if(n == soma){
    res = 1;
  }else{
    res = 0;
  }
}

int calculateRemainder(int dividend, int divisor){
  int quotient;
  quotient = 0;
  int tempvisor;
  tempdivisor = divisor;
  if(dividend == divisor){
    remainder=0;
  }
  else if (dividend < divisor){

    remainder=1;
  }
  while(tempdivisor <= dividend){
    tempdivisor = tempdivisor + divisor;
  }
  remainder=dividend-(tempdivisor-divisor);
  return remainder;
}


int power(int n, int r) {
  int c;
  int p;
  p = 1;
  c = 1;
  while (c <= r){ 
    p = p*n;
    c = c + 1;
  }
  return p;   
}

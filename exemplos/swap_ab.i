void main() {
   int a;
   int b;
   int temp;

   a = input(int);
   b = input(int);
   print('a'); print('='); print(a);
   print('b'); print('='); print(b);

   temp = a;
   a = b;
   b = temp;

   print('a'); print('='); print(a);
   print('b'); print('='); print(b);
}

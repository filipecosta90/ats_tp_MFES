void main() {
   int n; 
   int firstval; 
   int secondval;
   int nextval;
   int cval;
   
   cval = 0;
   firstval = 0;
   secondval = 1;

   n = input(int);
   for(cval=0; cval < n; cval++) {
      if ( cval <= 1 ){ 
        nextval = cval;
      }
      else {
        nextval = firstval + secondval;
        firstval = secondval;
        secondval = nextval;
      }
      print(nextval);
   }
}

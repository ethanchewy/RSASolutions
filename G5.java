/**
 * Auto Generated Java Language Level Class.
 */
class G5 {
  
  /* ADD YOUR CODE HERE */
  public static void main (String[] args){
    long start = 444444444;
    long end = 477777777;
    long start2 = 744444444;
    long end2 = 777777777;
    long n = 0; 
    long digit = 0;
    boolean nice = true;
    long counter1 = 0;
    long counter2 = 0;
    
    for(long i=7777444447744L;i<=10000000000000000L;i+=512){
      n=i;
      counter1++;
      while ( n > 0 ) {
        digit = n % 10;
        if(digit==7||digit==4){
        } else{
          nice = false;
          break;
        }
        n = n / 10;
        nice = true;
      }
      if(nice==true){
        System.out.println(i);
      }
      
    }
    /*
    for(long i=start2;i<=end2;i++){
      n=i;
      counter2++;
      while ( n > 0 ) {
        digit = n % 10;
        if(digit==7||digit==4){
        } else{
          nice = false;
          break;
        }
        n = n / 10;
        nice = true;
      }
      if(i%512==0&&nice==true){
        System.out.prlongln(i);
      }
      
    }
    System.out.prlongln(counter1);
    System.out.prlongln(counter2);
    */
    
    
  }
  
}

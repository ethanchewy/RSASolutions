/**
Problem 2
DrJava
Java SE 8 & JDK 8.0_112
Mac

Instructions:
- Just run the program. 
- Enter input and the answer will be outputted. 
- Please input number without any punctions like commas.
- Type -1 in input to exit.

Test Cases:
58148527 => prime
665999921 => prime
1558438050556301 => not prime

My own Test Case
100109100129100151 => prime
10093100991010310111
1000000000000000035000061
170141183460469231731687303715884105727
 */
import java.util.Scanner;
import java.math.BigInteger;
class CSProblem2 {
  
  public static void main (String[] args){
    boolean getInput = true;
    while(getInput){
      //Get User Input
      Scanner reader = new Scanner(System.in);
      System.out.println("Enter Number: ");
      BigInteger input = reader.nextBigInteger();
      reader.close();
      
      if(input.equals("-1")){
        getInput = false;
        System.out.println("test");
        break;
      }
      
      //To shorten time of function. Set equal to the corresponding factor. 
      //Ex: 17 is a prime number. So when the program divides by 2, it sets big to 8 to avoid running through unnecssary factors.
      BigInteger upperbound = input;
      //Boolean for end output
      boolean prime = true;
      //Resultant of division 
      BigInteger resultant = new BigInteger("0");
      
      //Upperbound is the input initially
      upperbound=input;
      
      //Main loop
      //Best Case: O(1)
      //Worse Case: O(sqrt(n))
      if(input.equals(BigInteger.ZERO) || input.equals(BigInteger.ONE)){
        //0 and 1 are not prime nor are composite
        System.out.println("It is not prime");
      } else if (input.equals(new BigInteger("2"))){
        System.out.println("It is prime");
      }else {
        //Start with 3 and add by 2 each interation to save time. All primes are odd except 2.
        //Check i^2 against upperbound to save from overcounting factors. ex: 4 is a factor of 2, etc.
        for(BigInteger i = new BigInteger("3"); i.multiply(i).compareTo(upperbound)<1;i=i.add(new BigInteger("2"))){
          resultant = input.remainder(i);
          //System.out.println(i);
          if(resultant.equals(BigInteger.ZERO)){
            prime = false;
            System.out.println("It is not prime");
            break;
          } else{
            //Set big to corresponding larger factor.
            prime = true;
          }
        }
        if(prime==true){
          System.out.println("It is prime");
        }
      }
      
      
    }
  }
}

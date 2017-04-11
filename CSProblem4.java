/**
Problem 4
DrJava
Java SE 8 & JDK 8.0_112
Mac

Instructions:
- Just run the program. 
- Enter first number and then the second number and the answer will be outputted. 
- Please input number without any punctions like commas.

Test Cases:
1530864174490193 and 1324693829967097
3478169421466939 and 18237769603176901
 */
import java.util.Scanner;
import java.math.BigInteger;
class CSProblem4 {
  
  public static void main (String[] args){
    //Get Input
    Scanner reader = new Scanner(System.in);
    System.out.println("Enter First Number: ");
    BigInteger first = reader.nextBigInteger();
    System.out.println("Enter Second Number: ");
    BigInteger second = reader.nextBigInteger();
    
    //Set temp variables for next loop
    BigInteger big = new BigInteger("0");
    BigInteger small = new BigInteger("0");
    
    //If either is equal to zero, the other number is the divisor
    if(first.equals(BigInteger.ZERO)){
      System.out.println("Greatest Common Divisor: " + second);
    } else if(second.equals(BigInteger.ZERO)){
      System.out.println("Greatest Common Divisor: " + first);
    } else {
      //Set big and small
      if(first.compareTo(second)==1){
        big = first;
        small = second;
      } else {
        big = second;
        small = first;
      }
      //Run GCD Function
      BigInteger gcd = gcd(big, small);
      System.out.println("Greatest Common Divisor: " + gcd);
    }
  }
  public static BigInteger gcd(BigInteger big, BigInteger small){
    //base case when the remainder is equal to 0
    if(small.equals(BigInteger.ZERO))
      return big;
    //Continually takes mod of big until small 
    return gcd(small, big.mod(small));
  }
  
}

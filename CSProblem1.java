/**
Problem 1 part 2
DrJava
Java SE 8 & JDK 8.0_112
Mac

Instructions:
- Run the program. 
- Enter input and the answer will be outputted. 
- Please input number without any punctions like commas.
- Enter numbers and operations in order
- Doesn't obey orders of operations yet

Ex:
For 100 participants, type "100" for input.

 */
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.math.BigInteger;
class CSProblem1 {
  
  public static void main (String[] args){
    //Follows equation lined out in text solution
    Scanner reader = new Scanner(System.in);
    System.out.println("Input number of participants: ");
    BigInteger n = reader.nextBigInteger();
    BigInteger total = BigInteger.ZERO;
    BigInteger temp = BigInteger.ZERO;
    BigInteger numerator = factorial(n);
    BigInteger denominator = BigInteger.ZERO;
    for(BigInteger i = new BigInteger("2"); i.compareTo(n)<=0;i=i.add(BigInteger.ONE)){
      denominator = factorial(i).multiply(factorial((n.subtract(i))));
      //System.out.println("dem " +i +"is: "+denominator);
      total=total.add(numerator.divide(denominator));
    }
    System.out.println("Total number of keys: " + total);
  }
  public static BigInteger factorial(BigInteger n){
    BigInteger total = BigInteger.ONE;
    for (BigInteger i = BigInteger.ONE; i.compareTo(n)<=0; i=i.add(BigInteger.ONE)) {
      total = total.multiply(i);
    }
    return total;
  }
  
}

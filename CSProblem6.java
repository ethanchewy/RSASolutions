/**
Problem 6
DrJava
Java SE 8 & JDK 8.0_112
Mac

Instructions:
- Run the program. 
- Enter the number of operations
- Please input number without any punctions like commas.
- Operations possible is +, *, or ^
- Enter numbers and operations in order

Test Cases:
1. 100000000000000000 + 2000000000000000 mod 9988426849099
2. 100000000000000000 * 2000000000000000 mod 9988426849099
3. 100000000000000000^2000000000000000 mod 9988426849099
 */
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.math.BigInteger;


class CSProblem6 {
  
  public static void main (String[] args){
    //Read Input
    Scanner reader = new Scanner(System.in);
    System.out.println("Enter Modulus: ");
    BigInteger modulus = reader.nextBigInteger();
    List<BigInteger> numbers = new ArrayList<BigInteger>();
    char operation = 0;
    System.out.println("Enter First Number: ");
    numbers.add(reader.nextBigInteger());
    System.out.println("Enter Operation: ");
    operation = reader.next().charAt(0);
    if(operation== '+'){
      System.out.println("Enter Second Number: ");
      numbers.add(reader.nextBigInteger());
      System.out.println(add(numbers.get(0),numbers.get(1),modulus));
    } else if (operation == '*'){
      System.out.println("Enter Number: ");
      numbers.add(reader.nextBigInteger());
      System.out.println(multiply(numbers.get(0),numbers.get(1),modulus));
    } else if(operation == '^'){
      System.out.println("Enter Number: ");
      numbers.add(reader.nextBigInteger());
      System.out.println(raise(modulus, numbers.get(1), numbers.get(0)));
    }else{
      System.out.println("error");
    }

  }
  public static BigInteger add(BigInteger a, BigInteger b, BigInteger modulus){
    //(A + B) mod C = (A mod C + B mod C) mod C
    
    //Initialize variables First => A. Second => B. Sum not set yet
    BigInteger sum = new BigInteger("0");
    BigInteger first = a;
    BigInteger second = b;
    
    //A mod C
    first = first.mod(modulus);
    
    //B mod C
    second = second.mod(modulus);
    
    //Add first and second together
    sum = first.add(second);
    
    //Take mod C of sum
    sum = sum.mod(modulus);
 
    return sum;
  }
  public static BigInteger multiply(BigInteger a, BigInteger b, BigInteger modulus){
    //(A * B) mod C = (A mod C * B mod C) mod C
    //Initialize variables First => A. Second => B. Product not set yet
    BigInteger product = new BigInteger("0");
    
    //A mod C
    BigInteger first = a.mod(modulus);
    //B mod C
    BigInteger second = b.mod(modulus);
    
    //Total product 
    product = a.multiply(b);
    //mod C
    product = product.mod(modulus);
    
    return product;
  }
  
  //Calculates power of (a^exp) % mod.
  public static BigInteger raise(BigInteger modulus, BigInteger exp, BigInteger a){
    //A^B mod C = ( (A mod C)^B ) mod C
    
    //Initialize one, zero, and two so that it can be referenced quickly
    BigInteger one = BigInteger.ONE;
    BigInteger zero = BigInteger.ZERO;
    BigInteger two = new BigInteger("2");
    
    //If exponent is 1, return itself mod the modulus(base case)
    if (exp.compareTo(one) == 0) 
      return a.mod(modulus);
    //If exponent is 0, return 1 (base case)
    if (exp.compareTo(zero) == 0) 
      return one;
       
    if(exp.mod(two).compareTo(zero) == 0) {
     //Recursively raise by exp/2 if exponent is divisible by 2
     BigInteger temp = raise(modulus,exp.divide(two),a);
     return (temp.multiply(temp)).mod(modulus) ;
    }
    
    //If exponent is not divisible by 2, recursively raise by exp/(2-1)
    BigInteger temp = raise(modulus,exp.subtract(one).divide(two),a);
    return (temp.multiply(temp).multiply(a)).mod(modulus); 
   }
  
}

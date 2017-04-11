/**
Problem 13
DrJava
Java SE 8 & JDK 8.0_112
Mac

Instructions:
- Run the program. 
- Enter input and the answer will be outputted. 
- Please input number without any punctions like commas.
- Enter numbers and operations in order
- Doesn't obey orders of operations yet
- If nothing for a variable, type nothing

Test Cases:
1. The signature is 111889714168449968140, the message is ”12345”, n =
178076698764316482877 and e = 65537 .
2. The signature is 68295009307484396257, the message is ”54321”, n =
178076698764316482877 and e = 65537 .
 */
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.math.BigInteger;
class CSProblem13 {
  
  public static void main (String[] args){
    //Take input
    Scanner reader = new Scanner(System.in);
    
    //Sign or verify
    System.out.println("Type s to sign or v to verify: ");
    String choice = reader.nextLine();
    
    if(choice.charAt(0)=='s'){
      System.out.println("m: ");
      BigInteger m = reader.nextBigInteger();
      System.out.println("p: ");
      BigInteger p = reader.nextBigInteger();
      System.out.println("q: ");
      BigInteger q = reader.nextBigInteger();
      System.out.println("d: ");
      BigInteger d = reader.nextBigInteger();
      sign(m, p,q,d);
    } else if(choice.charAt(0)=='v'){
      System.out.println("s: ");
      BigInteger s = reader.nextBigInteger();
      System.out.println("m: ");
      BigInteger m = reader.nextBigInteger();
      System.out.println("n: ");
      BigInteger n = reader.nextBigInteger();
      System.out.println("e: ");
      BigInteger e = reader.nextBigInteger();
      //hasfunction();
      verify(s, m, n, e);
    } else{
      System.out.println("Invalid Input");
    }
    
  }
  
  public static void sign(BigInteger m, BigInteger p, BigInteger q, BigInteger d){
    //Based on Understanding Cryptography Chapter 10 info
    //s = sigkpr (x) ≡ x^d mod n
    
    
    BigInteger n = p.multiply(q);
    
    //Calculate m^e
    BigInteger s = m.modPow(d,n);
    
    System.out.println("Signed Message: " + s);
    
  }
  
  public static void verify(BigInteger s, BigInteger m, BigInteger n, BigInteger e){
    BigInteger x = s.modPow(e,n);
    BigInteger x2 = m.mod(n);
    if(x.equals(x2)){
      System.out.println("Verified");
    } else{
      System.out.println("Not Verified");
    }
  }
  
}
  

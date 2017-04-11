/**
Problem 7
DrJava
Java SE 8 & JDK 8.0_112
Mac

Instructions:
- Run the program. 
- Enter input and the answer will be outputted. 
- Please input number without any punctions like commas.

Test Cases:
1. p = 10040238757, q = 12604798513, e = 23,
2. p = 10040238757, q = 12604798513, e = 65537
 */
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.math.BigInteger;
class CSProblem7 {
  
  public static void main (String[] args){
    //Get input p, q, e
    Scanner reader = new Scanner(System.in);
    System.out.println("p: ");
    BigInteger p = reader.nextBigInteger();
    System.out.println("q: ");
    BigInteger q = reader.nextBigInteger();
    System.out.println("e: ");
    BigInteger e = reader.nextBigInteger();
    
    //Compute private/public RSA keys
    //Product
    BigInteger n = p.multiply(q);
    
    //Euler's totient function
    BigInteger totient = (p.subtract(BigInteger.ONE)).multiply((q.subtract(BigInteger.ONE)));
    //System.out.println(totient); 
    //BigInteger totient = getTotient(n,m);
    
    //Multiplicative Inverse of e modulo totient function result
    System.out.println("Public key n: " + n + " e: "+e);
    
    //Calculates d and returns it
    System.out.println("Private key: " + extended_euclidean(e,totient)); 
    
  }
  
  //From problem 5 java file
  public static BigInteger extended_euclidean(BigInteger a, BigInteger b){
    BigInteger x = new BigInteger("0");
    BigInteger y = new BigInteger("0");
    BigInteger last_x = new BigInteger("1");
    BigInteger last_y = new BigInteger("0");
    BigInteger temp = new BigInteger("0");
    BigInteger modulus = b;
    
    int iteration = 1;
    while (!b.equals(BigInteger.ZERO)) {
      BigInteger q = a.divide(b);
      BigInteger r = a.mod(b);
      
      a = b;
      b = r;
      
      temp = x;
      x = last_x.subtract(q.multiply(x));
      last_x = temp;
      
      temp = y;
      y = last_y.subtract(q.multiply(y));
      last_y = temp;  
      
      iteration++;
    }
    //System.out.println("x : "+ last_x +" y :"+ last_y);
    if(last_x.compareTo(BigInteger.ZERO)==-1){
      return last_x.add(modulus);
    } else{
      return last_x;
    }
  }
  
}

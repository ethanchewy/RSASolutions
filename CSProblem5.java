/**
Problem 5
DrJava
Java SE 8 & JDK 8.0_112
Mac

Instructions:
- Run the program. 
- Enter input and the answer will be outputted. 
- Please input number without any punctions like commas 
- Number a has to be smaller than modulo m

Test Cases:
a = 7, m = 12
a = 3, m = 9988426849099
a = 65537, m = 9988426849099
 */
import java.util.Scanner;
import java.math.BigInteger;
class CSProblem5 {
  
  public static void main(String[] args){
    //Read Input
    Scanner reader = new Scanner(System.in);
    System.out.println("Enter Number: ");
    BigInteger number = reader.nextBigInteger();
    System.out.println("Enter Modulo: ");
    BigInteger modulo = reader.nextBigInteger();
    
    //gcd(number, modulo)=1
    //Run Euclidean Algorithm
    BigInteger gcd = euclidean(number, modulo);
    //If algorithm doesn't work
    if(gcd.equals(BigInteger.ONE)){
      BigInteger inverse = extended_euclidean(number, modulo);
      System.out.println("Mutiplicative Inverse is: " + inverse);
    } else{
      //If the greatest common divisor is not 1
      System.out.println("No Multiplicative Inverse");
    }
  }
  public static BigInteger euclidean(BigInteger big, BigInteger small){
    //base case when the remainder is equal to 0
    if(small.equals(BigInteger.ZERO))
      return big;
    //Continually takes mod of big until small 
    return euclidean(small, big.mod(small));
  }
  
  //Only returns ax of ax+by=1
  public static BigInteger extended_euclidean(BigInteger a, BigInteger b){
    BigInteger x = new BigInteger("0");
    BigInteger y = new BigInteger("0");
    BigInteger last_x = new BigInteger("1");
    BigInteger last_y = new BigInteger("0");
    BigInteger temp = new BigInteger("0");
    BigInteger modulus = b;
    
    int iteration = 1;
    while (!b.equals(BigInteger.ZERO)) {
      //System.out.println("interation: "+iteration);
      BigInteger q = a.divide(b);
      //System.out.println("q: "+q);
      BigInteger r = a.mod(b);
      //System.out.println("r: "+r);
      
      a = b;
      //System.out.println("a: "+a);
      b = r;
      //System.out.println("b: "+b);
      
      temp = x;
      //System.out.println("temp: "+temp);
      x = last_x.subtract(q.multiply(x));
      //System.out.println("x: "+x);
      last_x = temp;
      //System.out.println("last_x: "+last_x);
      
      temp = y;
      //System.out.println("temp: "+temp);
      y = last_y.subtract(q.multiply(y));
      //System.out.println("y: "+y);
      last_y = temp;  
      //System.out.println("last_y: "+last_y);
      
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

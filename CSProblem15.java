/**
Problem 15
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
"Show step-by-step how Alice can obtain a blind signature from Bob on the
message 33333333333 using only Bob’s public key, where Bob signs the message
without knowing what it is. Bob’s private key is p = 694420975411, q =
12583867141, d = 7272195257079707781473, you can compute his public key
from his private key. After removing the blinding factor, verify Bob’s signature
on the message."

 */
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;
class CSProblem15 {
  
  public static void main (String[] args){
    //Take input
    Scanner reader = new Scanner(System.in);
    
    System.out.println("Type c to compute message m' or r to remove blind factor from the signature s' or s for sample function: ");
    String type = reader.nextLine();
    if(type.charAt(0)=='c'){
      System.out.println("m: ");
      BigInteger m = reader.nextBigInteger();
      System.out.println("n: ");
      BigInteger n = reader.nextBigInteger();
      System.out.println("e: ");
      BigInteger e = reader.nextBigInteger();
      System.out.println("d: ");
      BigInteger d = reader.nextBigInteger();
      
      compute_blind(m, n, e, d);
    } else if(type.charAt(0)=='r'){
      System.out.println("s': ");
      BigInteger s_ = reader.nextBigInteger();
      System.out.println("n: ");
      BigInteger n = reader.nextBigInteger();
      System.out.println("r: ");
      BigInteger r = reader.nextBigInteger();
    } else if(type.charAt(0)=='s'){
      System.out.println("m: ");
      BigInteger m = reader.nextBigInteger();
      System.out.println("p: ");
      BigInteger p = reader.nextBigInteger();
      System.out.println("q: ");
      BigInteger q = reader.nextBigInteger();
      System.out.println("d: ");
      BigInteger d = reader.nextBigInteger();
      example_problem(m,p,q,d);
    } else{
      System.out.println("error");
    }
    
  }
  public static BigInteger gcd(BigInteger n, BigInteger m){
    if(m.equals(BigInteger.ZERO))
      return n;
    return gcd(m, n.mod(m));
  }
  public static BigInteger phi(BigInteger p, BigInteger q){
    BigInteger phiN = p.subtract(BigInteger.ONE);
    phiN = phiN.multiply(q.subtract(BigInteger.ONE));
    return phiN;   
  }
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
  
  //calculate inverse
  public static BigInteger inverse (BigInteger a, BigInteger N){
    BigInteger ans = extended_euclidean(a,N);
  
    if (ans.compareTo(BigInteger.ZERO) == 1)
        return ans;
    else return ans.add(N);
  }
  //Underscore indicates inverse of said variable
  public static BigInteger compute_blind(BigInteger m, BigInteger n, BigInteger e, BigInteger d){
    //Generate random number r such that gcd(r, n) = 1
    Random randomGenerator = new Random();
    int r_int;
    BigInteger r;
    while(true){
      r_int=randomGenerator.nextInt(100);
      r = BigInteger.valueOf(r_int);
      if(gcd(r,n).equals(BigInteger.ONE))
        break; 
    }
    //Compute m' = mr^e mod n
    BigInteger m_ = m.multiply((r.modPow(e,n)));
    //Compute s' = (m')^d mod n
    BigInteger s_ = m_.modPow(d,n);
    
    //Compute signature s'r^(-1)
    BigInteger r_ = inverse(r,n);
    BigInteger s = s_.multiply(r_);
    
    //Signature on just m
    s = s.mod(n);
    System.out.println("Message m': " + m_ +" Signature s on m: "+s);
    return s;
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
  public static void remove_blind_factor(BigInteger s_, BigInteger n, BigInteger r){
    //return m
    //S ≡ S'r^-1 (mod N )
    BigInteger r_ = inverse(r,n);
    BigInteger s = r_.multiply(s_);
    s = s.mod(n);
    System.out.println("Signature s (after removing the blinding factor): "+s);
  }
  //Answer to "Show step-by-step how Alice can obtain a blind signature from Bob on the
  //message 33333333333 using only Bob’s public key"
  public static void example_problem(BigInteger m, BigInteger p, BigInteger q, BigInteger d){
    //Compute Bob's public key
    BigInteger n = p.multiply(q);
    BigInteger phiN = phi(p,q);
    BigInteger e = inverse(d,phiN);
    
    //Compute blind
    BigInteger s = compute_blind(m, n, e, d);
    //Verify Bob's signature
    verify(s,m,n,e);
  }
}

/**
Problem 9
DrJava
Java SE 8 & JDK 8.0_112
Mac

Instructions:
- Run the program. 
- Enter input and the answer will be outputted. 
- Please input number without any punctions like commas.
- Enter numbers and operations in order

Test Cases:
1. Encrypt a message m = 123456789 with n = 178076698764316482877 and
e = 65537 (this value of e is frequently used in RSA cryptography).
2. Decrypt a message c = 7533231374898382904174 with the private key
p = 694420975411, q = 12583867141, d = 7272195257079707781473. Also
compute e.
3. Decrypt a message c = 4792189117479614137708 with the private key
p = 694420975411, q = 12583867141, d = 1028058975739840826753. Also
compute e.
 */
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.math.BigInteger;
class CSProblem9 {
  
  /* ADD YOUR CODE HERE */
  public static void main (String[] args){
    //Get Input. Set values.
    Scanner reader = new Scanner(System.in);
    System.out.println("Type e for encryption, d for decryption");
    String type = reader.next();
    if(type.charAt(0)=='e'){
      System.out.println("m: ");
      BigInteger m = reader.nextBigInteger();
      System.out.println("n: ");
      BigInteger n = reader.nextBigInteger();
      System.out.println("e: ");
      BigInteger e = reader.nextBigInteger();
      //Encryption Process
      encrypt(m,n,e);
    } else{
      System.out.println("c: ");
      BigInteger c = reader.nextBigInteger();
      System.out.println("p: ");
      BigInteger p = reader.nextBigInteger();
      System.out.println("q: ");
      BigInteger q = reader.nextBigInteger();
      System.out.println("d: ");
      BigInteger d = reader.nextBigInteger();
      //Calculate e
      BigInteger phiN = phi(p,q);
      BigInteger e = inverse(d,phiN);
      System.out.println("e: "+e);
      //Decrypt
      decrypt_crt(d,p,q,c);
    }
  }
  //Calculate phi(N)
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
  //decrypt using Chinese Remainder Theorem
  //Based on definition outlined on page 184 of Understanding Cryptography
  public static void decrypt_crt(BigInteger d, BigInteger p, BigInteger q, BigInteger m){
    BigInteger dp, dq, q_inverse, yp, yq, h;
    
    //dp = d mod(p-1)
    //dq = d mod(q-1)
    dp = d.mod(p.subtract(BigInteger.ONE));
    dq = d.mod(q.subtract(BigInteger.ONE));
    //qinverse= q^(-1) mod p
    q_inverse = inverse(q,p);
    
    //yp = x^(dp) mod p
    //xp = x^(dq) mod q
    yp = m.modPow(dp,p);
    yq = m.modPow(dq,q);
    
    //h=q_inverse(yp-yq) mod p
    h = q_inverse.multiply(yp.subtract(yq)).mod(p);
    //m = yq + hq
    m = yq.add(h.multiply(q));
    
    System.out.println("Decrypted message: " + m);
  }
  
  public static void encrypt(BigInteger m, BigInteger n, BigInteger e){
    //c = m^e mod n
    
    //Calculate m^e
    //BigInteger raised = m.pow(e);
    
    BigInteger c = m.modPow(e,n);
    
    System.out.println("Encrypted Message: " + c);
    
  }
}

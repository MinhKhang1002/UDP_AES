/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ltm_gk;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author Khang
 */
public class LTM_GK {


    
    
       public String maHoaAES(String plainText, String myKey) {
        try {
            MessageDigest sha = MessageDigest.getInstance("SHA-1");
            byte[] key = myKey.getBytes("UTF-8");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);  
            SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(plainText.getBytes("UTF-8")));
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return null;
    }

    public String giaiMaAES(String cypherText, String myKey) {
        try {
            MessageDigest sha = MessageDigest.getInstance("SHA-1");
            byte[] key = myKey.getBytes("UTF-8");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);
            SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(cypherText)));
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return null;
    }
 static String DemKyTu(String str)
    {
        HashMap<String,String>  a = new HashMap<>();
        int counter[] = new int[256];
        int len = str.length();
        String k = null;
        for (int i = 0; i < len; i++){
            //System.out.print(i);
            counter[str.charAt(i)]++;
            //System.out.println(counter[str.charAt(i)]++);
            System.out.println(String.valueOf(str.charAt(i))+" là: "+String.valueOf(counter[str.charAt(i)] ));
            
        }
        char array[] = new char[str.length()];
        for (int i = 0; i < len; i++) {
            array[i] = str.charAt(i);
            int flag = 0;
            for (int j = 0; j <= i; j++) {
                
                if (str.charAt(i) == array[j]){
                    flag++;
                 //   System.out.println(flag);
                }
            }
            if (flag==1){
                 
                 k+= "Số lần xuất hiện của  " +String.valueOf(str.charAt(i))+" là: "+ String.valueOf(counter[str.charAt(i)] ) +"\n";}
           // a.put(k,k);
            
        }
        return k;
}
    
    public static void main(String[] args) {
        String s = "huynh minh khang";
        String key ="123";
        LTM_GK gk = new LTM_GK();
       String s1= gk.maHoaAES(s, key);
        String s2 = gk.giaiMaAES(s1, key);
        System.out.println("Chuỗi mã hóa :" +s1);
        System.out.println("Chuỗi được giải mã: "+s2);
//        HashMap s3 = DemKyTu("huynhminh khang   ");
        //System.out.println(s3.values());
        System.out.println(DemKyTu(s));
//        for(int i=0;i<s3.size();i++){
//            System.out.println(s3.get(i));
//        }
      //  System.out.println(s3.get(3));
    }
    
}

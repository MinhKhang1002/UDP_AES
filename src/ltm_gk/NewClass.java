
package ltm_gk;


import java.util.HashMap;
import javax.print.DocFlavor;

/**
 *
 * @author n18dccn092
 */
public class NewClass {
           static String DemKyTu(String str)
    {
        HashMap<String,String>  a = new HashMap<>();
        int counter[] = new int[256];
     
        String k = null;
        //Đếm số lần xuất hiện của kí tự
        for (int i = 0; i <  str.length(); i++){
            counter[str.charAt(i)]++;
            System.out.println(counter[str.charAt(i)]);
            
        }
        char array[] = new char[str.length()];
        
        //Xuất kết quả số lần xuất hiện
        for (int i = 0; i <  str.length(); i++) {
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
            System.out.println(NewClass.DemKyTu("123321"));
         
           
             }
    
}

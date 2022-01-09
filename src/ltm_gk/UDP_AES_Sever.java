/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ltm_gk;

import java.util.HashMap;
import java.security.MessageDigest;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.*;
import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Khang
 */
public class UDP_AES_Sever extends javax.swing.JFrame {

    Thread t;
    DatagramSocket sever;
    static DatagramPacket dinChuoi;
    static String b1;
    Thread a;

// static HashMap<String, String> DemKyTu(String str)
//    {
//        HashMap<String,String>  a = new HashMap<>();
//        int counter[] = new int[256];
//        int len = str.length();
//        char kyTu = ' ';   
//        String k = null;
//        String c= "";
//        for (int i = 0; i < len; i++)
//            counter[str.charAt(i)]++;
//        char array[] = new char[str.length()];
//        for (int i = 0; i < len; i++) {
//            array[i] = str.charAt(i);
//            int flag = 0;
//            for (int j = 0; j <= i; j++) {
//                if(str.charAt(i)==' '){
//                    continue;
//                }
//                if (str.charAt(i) == array[j])
//                    flag++;
//            }
//            
//            if (flag == 1)
//                k= "Số lần xuất hiện của " +String.valueOf(str.charAt(i))+" là: "+ String.valueOf(counter[str.charAt(i)]+"\n" );
//          
//            a.put(k,k);
//            
//        }
//        return a;
//}
    static String DemKyTu(String str) {
        int counter[] = new int[256];
        int len = str.length();
        String k = null;
        for (int i = 0; i < len; i++) {
            // System.out.print(i);
            counter[str.charAt(i)]++;
            //System.out.println(String.valueOf(str.charAt(i))+" là: "+String.valueOf(counter[str.charAt(i)] ));

        }
        char array[] = new char[str.length()];
        for (int i = 0; i < len; i++) {
            array[i] = str.charAt(i);
            int flag = 0;
            for (int j = 0; j <= i; j++) {
                if (str.charAt(i) == ' ' || str.charAt(i)=='\t') {
                    continue;
                }
                if (str.charAt(i) == array[j]) {
                    flag++;

                }
            }
            if (flag == 1) {
                k += "Số lần xuất hiện của  " + String.valueOf(str.charAt(i)) + " là: " + String.valueOf(counter[str.charAt(i)]) + "\n";
            }

        }
        return k;
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

    public UDP_AES_Sever() throws SocketException, IOException {
        initComponents();
        sever = new DatagramSocket(1002);
        luongCho();

    }

    public void luongCho() {
        t = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    byte chuoi[] = new byte[10000];
                    byte key[] = new byte[10000];
                    dinChuoi = new DatagramPacket(chuoi, chuoi.length);
                    try {
                        sever.receive(dinChuoi);
                    } catch (IOException ex) {
                        Logger.getLogger(UDP_AES_Sever.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    DatagramPacket dinKey = new DatagramPacket(key, key.length);
                    try {
                        sever.receive(dinKey);
                    } catch (IOException ex) {
                        Logger.getLogger(UDP_AES_Sever.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    String chuoiMaHoa, Key;
                    chuoiMaHoa = new String(dinChuoi.getData(), 0, dinChuoi.getLength());
                    Key = new String(dinKey.getData(), 0, dinKey.getLength());

                    b1 = giaiMaAES(chuoiMaHoa, Key);
                    txtNhan_Sever.setText(chuoiMaHoa);
                    txtBanRo_Sever.setText(b1);
                    txtKey_Sever.setText(Key);

                    String soKyTu = DemKyTu(b1);
                    byte[] byteSoKyTu = new byte[10000];
                    byteSoKyTu = soKyTu.getBytes();
                    DatagramPacket doutDemSoKyTu = new DatagramPacket(byteSoKyTu, byteSoKyTu.length, dinChuoi.getAddress(), dinChuoi.getPort());
                    try {
                        sever.send(doutDemSoKyTu);
                    } catch (IOException ex) {
                        Logger.getLogger(UDP_AES_Sever.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }

        });
        t.start();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtNhan_Sever = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtBanRo_Sever = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtKey_Sever = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Bản mã hóa");

        txtNhan_Sever.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("Bản rõ");

        txtBanRo_Sever.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("SEVER");

        txtKey_Sever.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText("Khóa");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Huỳnh Minh Khang");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("N18DCCN092");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(23, 23, 23)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtBanRo_Sever, javax.swing.GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE)
                                        .addComponent(txtKey_Sever))
                                    .addComponent(txtNhan_Sever, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(209, 209, 209)
                                .addComponent(jLabel3))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))))
                .addContainerGap(78, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(jLabel3))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(txtNhan_Sever, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtBanRo_Sever, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtKey_Sever, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(147, 147, 147))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) throws SocketException, IOException {

        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new UDP_AES_Sever().setVisible(true);

                } catch (SocketException ex) {
                    Logger.getLogger(UDP_AES_Sever.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(UDP_AES_Sever.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JTextField txtBanRo_Sever;
    private javax.swing.JTextField txtKey_Sever;
    private javax.swing.JTextField txtNhan_Sever;
    // End of variables declaration//GEN-END:variables
}

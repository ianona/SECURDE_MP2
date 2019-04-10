/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.User;
import View.Frame;
import static View.MgmtUser.designer;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.KeySpec;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author ianona
 */
public class SecurityConfig {

//AES 256 Encryption secret code
    private final String secretKey = "fritz'sbestsquad";
    private final String salt = "choonapena";
    public static final String ADMIN = "admin";

    public static String hash(String password) {
        //link to how to do SHA-512 encryption (https://www.geeksforgeeks.org/sha-512-hash-in-java/)
        try {
            // getInstance() method is called with algorithm SHA-512 
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            // digest() method is called 
            // to calculate message digest of the input string 
            // returned as array of byte 
            byte[] messageDigest = md.digest(password.getBytes());
            // Convert byte array into signum representation 
            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value 
            String hashtext = no.toString(16);

            // Add preceding 0s to make it 32 bit 
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            password = hashtext;
            return password;
        } // For specifying wrong message digest algorithms 
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<String> checkPassword(String username, String password, String confpass) {
        boolean hasUppercase = !password.equals(password.toLowerCase());
        boolean hasLowercase = !password.equals(password.toUpperCase());
        boolean isAtLeast8 = password.length() >= 8;
        boolean hasSpecial = !password.matches("[A-Za-z0-9 ]*");
        boolean hasNumber = password.matches(".*\\d+.*");

        List<String> errors = new ArrayList<>();
        // checks if both username and password are not empty
        if (password.isEmpty() || username.isEmpty()) {
            errors.add("Error! Username and password cannot be empty");
        }
        //checks if the password is same as the confirmation password
        if (!password.equals(confpass)) {
            errors.add("Error! Passwords are not the same");
        }
        //checks if has 1 upper case letter
        if (!hasUppercase) {
//            System.out.println("NO UPPER CASE");
            errors.add("Error! Password requires at least 1 uppercase character");
        }
        //checks if has 1 lower case letter
        if (!hasLowercase) {
//            System.out.println("NO LOWER CASE");
            errors.add("Error! Password requires at least 1 lowercase character");
        }
        //check if it has at least 8 characters
        if (!isAtLeast8) {
//            System.out.println("NOT MORE THAN 8 CHAR");
            errors.add("Error! Password requires at least 8 characters");
        }
        //check if it has number
        if (!hasNumber) {
//            System.out.println("NO NUMBER");
            errors.add("Error! Password requires at least 1 number");
        }
        //check if it has special characters
        if (!hasSpecial) {
//            System.out.println("NOT SPECIAL");
            errors.add("Error! Password requires at least 1 special character");
        }
        //check if is too long
        if (password.length() >= 128 ){
//            System.out.println("MORE THAN 128 CHARACTERS");
            errors.add("Error! Password has more than 128 characters");
        }
        return errors;
    }
    
    public static Boolean checkUsername(String input){
                if(input.length() <= 32 && input.length() >= 6 && input.matches("^[A-Z+a-z+0-9]+(?:[\\. _-][A-Za-z0-9]+)*$") && input.matches(".*[A-Za-z].*") && !input.matches("^[0-9]+(?:[\\. _-][A-Za-z0-9]+)*$"))
            return true;
        else
            return false;
    }
    
    public static void readDebugMode(SQLite sql) {
        BufferedReader csvReader = null;
        try {
            String row;
            csvReader = new BufferedReader(new FileReader("./config.csv"));
            while ((row = csvReader.readLine()) != null) {
                String[] data = row.split(",");
                sql.DEBUG_MODE = Integer.parseInt(data[1]);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SQLite.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SQLite.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                csvReader.close();
            } catch (IOException ex) {
                Logger.getLogger(SQLite.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void updateConfig(int debugMode) {
        FileWriter csvWriter = null;
        try {
            csvWriter = new FileWriter("./config.csv");
            csvWriter.append("DebugMode");
            csvWriter.append(",");
            csvWriter.append(debugMode + "");
            csvWriter.append("\n");
        } catch (IOException ex) {
            Logger.getLogger(SQLite.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                csvWriter.close();
            } catch (IOException ex) {
                Logger.getLogger(SQLite.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        updateTitle(debugMode);
    }

    public static void updateTitle(int mode) {
        Frame.getInstance().setTitle("SECURDE - SECURITY Svcs");
        if (mode == 1 && Frame.getCurUser() != null && Frame.getCurUser().getRole() == 5) {
            Frame.getInstance().setTitle("SECURDE - SECURITY Svcs [DEBUG MODE]");
        }
    }

    public static void updateUserArchive(User user) {
        FileWriter csvWriter = null;
        try {
            csvWriter = new FileWriter("./userArchive.csv", true);

            csvWriter.append("\n");
            csvWriter.append("" + user.getId());
            csvWriter.append(",");
            csvWriter.append(user.getUsername());
            csvWriter.append(",");
            csvWriter.append(user.getPassword());
            csvWriter.append(",");
            csvWriter.append("" + user.getRole());
            csvWriter.append(",");
            csvWriter.append("" + user.getLocked());
            // TO DO: MAKE READ ONLY
        } catch (IOException ex) {
            Logger.getLogger(SQLite.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                csvWriter.close();
            } catch (IOException ex) {
                Logger.getLogger(SQLite.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    // for db, pass the instance of SQLite being passed in every GUI class (there should only be one instance throughout the program)
    // mode is 1 for debug mode, 0 for non debug mode
    public static void log(SQLite db, int mode, String event, String desc) {
        // if trying to log a debug mode log but current mode is 0, stop
        if (mode == 1 && db.DEBUG_MODE == 0) {
            return;
        }
        String time = new Timestamp(new Date().getTime()).toString();
//        System.out.println("TIME NOW: " + time);
        if (time.length() == 22) {
            time = time + "0";
        }
        if (time.length() == 21) {
            time = time + "00";
        }
        User user = Frame.getCurUser();
        try {
            db.addLogs(event + (mode == 1 ? " [DEBUG]" : ""),
                    user != null ? user.getUsername() : "---",
                    desc,
                    time,
                    InetAddress.getLocalHost().getHostAddress());
        } catch (UnknownHostException ex) {
            Logger.getLogger(SecurityConfig.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //link for AES 256 tutorial https://howtodoinjava.com/security/aes-256-encryption-decryption/
    //AES 256 encryption
    public String encryptAES(String strToEncrypt) {
        String secret = this.secretKey;
        try {
            byte[] iv = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            IvParameterSpec ivspec = new IvParameterSpec(iv);

            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            KeySpec spec = new PBEKeySpec(secretKey.toCharArray(), salt.getBytes(), 65536, 128);
            SecretKey tmp = factory.generateSecret(spec);
            SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivspec);
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
        } catch (Exception e) {
//            System.out.println("Error while encrypting: " + e.toString());
//              e.printStackTrace();
        }
        return null;
    }

    //AES 256 decryption
    public String decryptAES(String strToDecrypt) {
        String secret = this.secretKey;
        try {
            byte[] iv = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            IvParameterSpec ivspec = new IvParameterSpec(iv);

            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            KeySpec spec = new PBEKeySpec(secretKey.toCharArray(), salt.getBytes(), 65536, 128);
            SecretKey tmp = factory.generateSecret(spec);
            SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivspec);
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        } catch (Exception e) {
//            System.out.println("Error while decrypting: " + e.toString());
        }
        return null;
    }

    public void testingAES() {
        String temp = "This is the best whatever chuchu";
//
//        System.out.println(encryptAES(temp));
//
//        System.out.println(decryptAES(encryptAES(temp)));
    }
    
    public static boolean reauthenticate(String msg) {
        JTextField password = new JPasswordField();
        designer(password, "PASSWORD");
        Object[] message = {
            msg,
            password
        };
        int result = JOptionPane.showConfirmDialog(null, message, "CHANGE PASSWORD", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null);
        if (result == JOptionPane.OK_OPTION) {
            if (SecurityConfig.hash(password.getText()).equals(Frame.getCurUser().getPassword())) {
                return true;
            }
        }

        return false;
    }
}

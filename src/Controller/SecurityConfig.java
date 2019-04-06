/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ianona
 */
public class SecurityConfig {

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
            System.out.println("NO UPPER CASE");
            errors.add("Error! Password requires at least 1 uppercase character");
        } 
        //checks if has 1 lower case letter
        if (!hasLowercase) {
            System.out.println("NO LOWER CASE");
            errors.add("Error! Password requires at least 1 lowercase character");
        } 
        //check if it has at least 8 characters
        if (!isAtLeast8) {
            System.out.println("NOT MORE THAN 8 CHAR");
            errors.add("Error! Password requires at least 8 characters");
        } 
        //check if it has number
        if (!hasNumber) {
            System.out.println("NO NUMBER");
            errors.add("Error! Password requires at least 1 number");
        }
        //check if it has special characters
        if (!hasSpecial) {
            System.out.println("NOT SPECIAL");
            errors.add("Error! Password requires at least 1 special character");
        } 
        return errors;
    }
}

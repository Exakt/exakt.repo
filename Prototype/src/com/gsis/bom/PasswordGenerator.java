package com.gsis.bom;

public class PasswordGenerator {
 
    /**
     * JavaProgrammingForums.com
     */
    public static int count, random, intOrChar, upperOrLower;
    public static char myChar;
    public static String lowerCase, password, finalPasswd;
 
    public static void main(String[] args) {
 
        while (count < 8) {
 
            // Integer or character
            intOrChar = (int) (Math.random() * 2);
 
            // if 0 then Integer
            if (intOrChar == 0) {
 
                // Print random number 0-9
                random = (int) (Math.random() * 9);
                //System.out.print(random);
                password = Integer.toString(random) + password;
 
                // if 1 then character
            } else if (intOrChar == 1) {
 
                // decide lower or upper case
                upperOrLower = (int) (Math.random() * 2);
 
                // if 0 then upper case
                if (upperOrLower == 0) {
 
                    // Print random Capital char
                    random = (int) (Math.random() * 74);
                    myChar = (char) (random + '0');
                    //System.out.print(myChar);
                    password = Character.toString(myChar) + password;
 
                    // if 1 then lower case
                } else if (upperOrLower == 1) {
 
                    // Print random Small char
                    random = (int) (Math.random() * 74);
                    myChar = (char) (random + '0');
                    lowerCase = Character.toString(myChar).toLowerCase() + password;
                    //System.out.print(lowerCase);
                    password = lowerCase;
 
                }
 
            }
 
            count++;
        }
        finalPasswd = password.replace("null", "");
        System.out.println(finalPasswd);
    }
 
}
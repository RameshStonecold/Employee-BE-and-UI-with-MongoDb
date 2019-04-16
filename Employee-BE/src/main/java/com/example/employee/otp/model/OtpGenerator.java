package com.example.employee.otp.model;

import java.util.Random;

class OtpGenerator {

   char[] otpGen ( int len){
       String numbers = "0123456789";

       Random rndm_method = new Random();

       char[] otp = new char[len];

       for (int i = 0; i < len; i++) {
           otp[i] =
                   numbers.charAt(rndm_method.nextInt(numbers.length()));
       }
       return otp;
   }
    String generateRandomString(){
       int len =6;

       return  String.valueOf(otpGen(len));
   }
}

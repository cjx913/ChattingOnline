package com.cjx913.chattingonline.utils;


import java.util.Random;

public class UserAccountGenerate {


    public static final String getUniqueAccount(int length){
        char[] num={'0','9','1','8','2','7','3','6','4','5'}; 
        StringBuilder account = new StringBuilder();
        Random random = new Random();
        /*第一个数不为0*/
        account.append(num[random.nextInt(9)+1]);
        for(int i=1;i<length;i++){
            account.append(num[random.nextInt(10)]);
        }
        return account.toString();
    }
}

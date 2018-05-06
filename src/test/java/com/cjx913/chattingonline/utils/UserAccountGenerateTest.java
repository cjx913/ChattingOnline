package com.cjx913.chattingonline.utils;

import org.junit.Test;

public class UserAccountGenerateTest {
    @Test
    public void getAccount(){
        String account = UserAccountGenerate.getUniqueAccount(8);
        System.out.println(account);
    }
}

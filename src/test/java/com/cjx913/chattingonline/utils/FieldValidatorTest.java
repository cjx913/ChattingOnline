package com.cjx913.chattingonline.utils;


import com.cjx913.chattingonline.entity.User;
import com.cjx913.chattingonline.entity.UserModelDriven;
import com.cjx913.chattingonline.entity.validator.InformationValidator;
import com.cjx913.chattingonline.entity.validator.LoginValidator;
import com.cjx913.chattingonline.entity.validator.RegisterValidator;
import org.junit.Test;

import javax.validation.constraints.*;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

public class FieldValidatorTest {
    public class Book {
        @Pattern(regexp = "\\w{6,8}]", message = "6-8个字母、数字和下划线", groups = {LoginValidator.class})
        @Size(min = 5, message = "不能少于5个字符", groups = {LoginValidator.class})
        @NotNull(message = "不能为空")
        private String name;
        @DecimalMin(value = "20.00", message = "不少于20.00", groups = {RegisterValidator.class})
        private float price;
        @Past(message = "请输入过去的时间")
        private Date publishDate;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public float getPrice() {
            return price;
        }

        public void setPrice(float price) {
            this.price = price;
        }

        public Date getPublishDate() {
            return publishDate;
        }

        public void setPublishDate(Date publishDate) {
            this.publishDate = publishDate;
        }
    }

    @Test
    public void jsr303Validator() {
        Book book = new Book();
        book.setPrice(11f);
        book.setName("sssss");
        book.setPublishDate(new Date());

        User user = new User();
        user.setName("cjx913");

        UserModelDriven u = new UserModelDriven();
//        u.setName("1");
//        u.setAccount("11111111");
//        u.setPassword("312312");

        u.setEmail("cjx913.ss.ss@ss.ss.ll");
        u.setAge(null);
        u.setGender("");
        u.setPhone("");

        Map <String, String> map = FieldValidator.jsr303Validator(u);
        if (map == null) {
            System.out.println("没有错误");
        } else {
            Iterator <Map.Entry <String, String>> iterator = map.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry <String, String> m = iterator.next();
                System.out.println(m.getKey() + "+" + m.getValue());
            }
        }
    }


    @Test
    public void canvasVerify() {
        UserModelDriven user = new UserModelDriven();
        user.setPhone("22");
        user.setVerifyCode("Asdf");
        user.setCanvasVerifyCode("aDf");
        user.setName("23");
        user.setAccount("122");
        user.setPassword("23123");
        Map <String, String> map = FieldValidator.jsr303Validator(user);
        if (map == null) {
            System.out.println("没有错误");
        } else {
            Iterator <Map.Entry <String, String>> iterator = map.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry <String, String> m = iterator.next();
                System.out.println(m.getKey() + "+" + m.getValue());
            }
        }
    }
}



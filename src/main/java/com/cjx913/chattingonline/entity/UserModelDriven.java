package com.cjx913.chattingonline.entity;

import com.cjx913.chattingonline.entity.validator.VerifyCode;

import javax.validation.constraints.*;
import java.util.Date;

@VerifyCode(verifyCode = "verifyCode",canvasVerifyCode = "canvasVerifyCode",message = "{UserModelDriven.VerifyCode}")
public class UserModelDriven {
    private Integer id;
    @Pattern(message = "{UserModelDriven.account.Pattern}",regexp = "[1-9]\\d{7}")
    private String account;
    @Pattern(regexp = "\\w{4,16}",message = "{UserModelDriven.name.Pattern}")
    private String name;
    @Pattern(regexp = "\\w{8,16}",message = "{UserModelDriven.password.Pattern}")
    private String password;
    @Pattern(regexp = "\\w{8,16}",message = "{UserModelDriven.password.Pattern}")
    private String originalPassword;
    @Pattern(regexp = "\\w{8,16}",message = "{UserModelDriven.password.Pattern}")
    private String againPassword;
    @Pattern(message = "{UserModelDriven.gender.Pattern}",regexp = "|男|女")
    private String gender;
    @Min(message = "{UserModelDriven.age.Min}",value = 1)
    @Max(message = "{UserModelDriven.age.Max}",value = 120)
    private Integer age;
    @Past(message = "{UserModelDriven.birth.Past}")
    private Date birth;
    private String address;
    private String headPortrait;
    @Email(message = "{UserModelDriven.eMail.Email}",regexp = "|^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$")
    private String email;
    @Pattern(message = "{UserModelDriven.phone.Pattern}",regexp = "|\\d{11}")
    private String phone;
//    @Min(message = "{UserModelDriven.year.Min}",value=1900)
//    @Max(message = "{UserModelDriven.year.Max}",value=2018)
//    private Integer year;
//    @Min(message = "{UserModelDriven.moon.Min}",value=1)
//    @Max(message = "{UserModelDriven.moon.Max}",value=12)
//    private Integer moon;
//    @Min(message = "{UserModelDriven.day.Min}",value=1)
//    @Max(message = "{UserModelDriven.day.Max}",value=31)
//    private Integer day;
    private String verifyCode;
    private String canvasVerifyCode;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOriginalPassword() {
        return originalPassword;
    }

    public void setOriginalPassword(String originalPassword) {
        this.originalPassword = originalPassword;
    }

    public String getAgainPassword() {
        return againPassword;
    }

    public void setAgainPassword(String againPassword) {
        this.againPassword = againPassword;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getBirth() {
        return this.birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHeadPortrait() {
        return headPortrait;
    }

    public void setHeadPortrait(String headPortrait) {
        this.headPortrait = headPortrait;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

//    public Integer getYear() {
//        return year;
//    }
//
//    public void setYear(Integer year) {
//        this.year = year;
//    }
//
//    public Integer getMoon() {
//        return moon;
//    }
//
//    public void setMoon(Integer moon) {
//        this.moon = moon;
//    }
//
//    public Integer getDay() {
//        return day;
//    }
//
//    public void setDay(Integer day) {
//        this.day = day;
//    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    public String getCanvasVerifyCode() {
        return canvasVerifyCode;
    }

    public void setCanvasVerifyCode(String canvasVerifyCode) {
        this.canvasVerifyCode = canvasVerifyCode;
    }



}

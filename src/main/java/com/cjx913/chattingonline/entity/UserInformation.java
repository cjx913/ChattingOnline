package com.cjx913.chattingonline.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "t_user_information")
public class UserInformation implements Serializable {
    private static final long serialVersionUID = -5543393348539339451L;
    private Integer userId;
    private String gender;
    private Integer age;
    private Date birth;
    private String address;
    private String headPortrait;
    private String email;
    private String phone;

    private User user;

    public UserInformation() {
    }

    public UserInformation(Integer userId, String gender, Integer age, Date birth, String address, String headPortrait, String email, String phone) {
        this.userId = userId;
        this.gender = gender;
        this.age = age;
        this.birth = birth;
        this.address = address;
        this.headPortrait = headPortrait;
        this.email = email;
        this.phone = phone;
    }

    @Id
    @Column(name = "user_id", nullable = false)
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "gender", nullable = true, length = 8)
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Basic
    @Column(name = "age", nullable = true)
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Basic
    @Column(name = "birth", nullable = true)
    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    @Basic
    @Column(name = "address", nullable = true, length = 256)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "head_portrait", nullable = true, length = 256)
    public String getHeadPortrait() {
        return headPortrait;
    }

    public void setHeadPortrait(String headPortrait) {
        this.headPortrait = headPortrait;
    }

    @Basic
    @Column(name = "e_mail", nullable = true, length = 128)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "phone", nullable = true, length = 11)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserInformation that = (UserInformation) o;

        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (gender != null ? !gender.equals(that.gender) : that.gender != null) return false;
        if (age != null ? !age.equals(that.age) : that.age != null) return false;
        if (birth != null ? !birth.equals(that.birth) : that.birth != null) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        if (headPortrait != null ? !headPortrait.equals(that.headPortrait) : that.headPortrait != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        return phone != null ? phone.equals(that.phone) : that.phone == null;
    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + (age != null ? age.hashCode() : 0);
        result = 31 * result + (birth != null ? birth.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (headPortrait != null ? headPortrait.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        return result;
    }
}

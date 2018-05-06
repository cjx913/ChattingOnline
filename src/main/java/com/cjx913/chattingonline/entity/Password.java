package com.cjx913.chattingonline.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "t_password")
public class Password implements Serializable {
    private static final long serialVersionUID = 6186872656270206304L;

    private Integer userId;
    private String password;

    private User user;

    public Password() {
    }

    public Password(Integer userId, String password) {
        this.userId = userId;
        this.password = password;
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
    @Column(name = "password", nullable = false, length = 128)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

        Password password1 = (Password) o;

        if (userId != null ? !userId.equals(password1.userId) : password1.userId != null) return false;
        return password != null ? password.equals(password1.password) : password1.password == null;
    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }
}

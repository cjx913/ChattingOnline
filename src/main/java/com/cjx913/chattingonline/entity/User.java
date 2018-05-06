package com.cjx913.chattingonline.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "t_user")
public class User implements Serializable {
    private static final long serialVersionUID = -4544222586432671909L;
    private Integer id;
    private String account;
    private String name;

    private Password password;
    private UserInformation userInformation;
    private Set <User> friends;
    private Set <Group> groups;
    private Set <Message> sendMessages;
    private Set <Message> receiveMessages;


    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "account", nullable = false, length = 32)
    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 32)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToOne(mappedBy = "user")
    public Password getPassword() {
        return password;
    }

    public void setPassword(Password password) {
        this.password = password;
    }

    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL)
    public UserInformation getUserInformation() {
        return userInformation;
    }

    public void setUserInformation(UserInformation userInformation) {
        this.userInformation = userInformation;
    }


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "t_friend",
            joinColumns = {@JoinColumn(name = "owner_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "friend_id", referencedColumnName = "id")})
    public Set <User> getFriends() {
        return friends;
    }

    public void setFriends(Set <User> friends) {
        this.friends = friends;
    }

    @ManyToMany(targetEntity = Group.class, fetch = FetchType.EAGER)
    @JoinTable(name = "t_user_group",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "group_id", referencedColumnName = "id")})
    public Set <Group> getGroups() {
        return groups;
    }

    public void setGroups(Set <Group> groups) {
        this.groups = groups;
    }

    @OneToMany(mappedBy = "send", fetch = FetchType.EAGER)
    public Set <Message> getSendMessages() {
        return sendMessages;
    }

    public void setSendMessages(Set <Message> sendMessages) {
        this.sendMessages = sendMessages;
    }

    @OneToMany(mappedBy = "receive", fetch = FetchType.EAGER)
    public Set <Message> getReceiveMessages() {
        return receiveMessages;
    }

    public void setReceiveMessages(Set <Message> receiveMessages) {
        this.receiveMessages = receiveMessages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != null ? !id.equals(user.id) : user.id != null) return false;
        if (account != null ? !account.equals(user.account) : user.account != null) return false;
        return name != null ? name.equals(user.name) : user.name == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (account != null ? account.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}

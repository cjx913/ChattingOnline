package com.cjx913.chattingonline.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "t_group")
public class Group {
    private int id;
    private String name;
    private String announcement;

    private Set<User> users;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 64)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "announcement", nullable = true, length = 256)
    public String getAnnouncement() {
        return announcement;
    }

    public void setAnnouncement(String announcement) {
        this.announcement = announcement;
    }

    @ManyToMany(targetEntity = User.class,fetch = FetchType.EAGER)
    @JoinTable(name = "t_user_group",
            joinColumns = {@JoinColumn(name = "group_id",referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id",referencedColumnName = "id")})
    public Set <User> getUsers() {
        return users;
    }

    public void setUsers(Set <User> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Group group = (Group) o;

        if (id != group.id) return false;
        if (name != null ? !name.equals(group.name) : group.name != null) return false;
        if (announcement != null ? !announcement.equals(group.announcement) : group.announcement != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (announcement != null ? announcement.hashCode() : 0);
        return result;
    }
}

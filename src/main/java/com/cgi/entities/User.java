package com.cgi.entities;

import com.cgi.utils.JpaJsonConverter;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @SequenceGenerator(name="users_id_seq", sequenceName="users_id_seq", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="users_id_seq")
    @Column(name = "id", updatable=false)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name="userdetail")
    @Convert(converter = UserDetailConverter.class)
    private UserDetail userDetail;

    public User() {
    }

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public static class UserDetailConverter extends JpaJsonConverter<UserDetail> {}

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserDetail getUserDetail() {
        return userDetail;
    }

    public void setUserDetail(UserDetail userDetail) {
        this.userDetail = userDetail;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", userDetail=" + userDetail +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        return email != null ? email.equals(user.email) : user.email == null;

    }

}

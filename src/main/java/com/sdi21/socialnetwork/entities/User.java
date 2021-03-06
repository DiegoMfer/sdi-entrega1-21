package com.sdi21.socialnetwork.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue
    private long id;

    @Column(unique = true)
    private String email;

    private String password;

    @Transient
    private String passwordConfirm;

    private String name;
    private String surname;

    private String role;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sender", orphanRemoval = true)
    private Set<FriendRequest> friendRequest = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "receiver", orphanRemoval = true)
    private Set<FriendRequest> friendRequestReceiver = new HashSet<>();

    @ManyToMany(mappedBy = "recommendations", fetch = FetchType.LAZY,
        cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private Set<Publication> recommended = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "op", orphanRemoval = true)
    private List<Publication> publications;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "receiver", orphanRemoval = true)
    private List<FriendRequest> requests;

    public User() {}
  
    public User(String email){
        this.email = email;
    }

    public User(String email, String name, String surname) {
        this.email = email;
        this.name = name;
        this.surname = surname;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }

}

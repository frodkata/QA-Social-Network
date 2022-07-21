package com.restAPI.QA.Entities;


import javax.persistence.*;

@Entity
@Table(name =  "user_profile", uniqueConstraints = @UniqueConstraint(columnNames = "username"))
public class UserProfile {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    private String email;


    private Role role;


    public UserProfile() {

    }

    public UserProfile(String username, String password, Role role) {
        super();

        this.username = username;
        this.password = password;
        this.role = role;
    }

    public UserProfile(Long id, String username, String password, Role role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }
    public void setRole(Role role) {
        this.role = role;
    }


}

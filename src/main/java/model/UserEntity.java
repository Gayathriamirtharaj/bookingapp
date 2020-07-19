package model;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;
@Table(name = "[user]")
@ToString
@Data
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String email;


    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"))
    List<Role> roles;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRoles() {
        return roles;
    }

    /*public static <Role> java.util.List<Role> getRoles() {
        return (java.util.List<Role>) roles;
    }*/

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

}


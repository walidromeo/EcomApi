package com.ecomapi.ws.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity(name = "users") @Data @NoArgsConstructor @AllArgsConstructor
public class UserEntity implements Serializable {

    private static final long serialVersionUID = -8802014973706613559L;

    @Id
    @GeneratedValue
    private long id;
    @Column(nullable = true, length = 120)
    private String name;

    @Email
    @Column(nullable = false, length = 120)
    private String email;

    @Column(nullable = true, length = 120)
    private String username;

    @Column(nullable = false)
    private String encryptedPassword;

    @ManyToMany(fetch=FetchType.EAGER)
    private Collection<RoleEntity> roles = new ArrayList<>();

    @OneToMany(mappedBy="user",cascade=CascadeType.PERSIST)
    private List<ProductsEntity> userAddingToStock;
}

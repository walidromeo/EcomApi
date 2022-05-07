package com.ecomapi.ws.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity(name = "role")@Data @NoArgsConstructor @AllArgsConstructor
public class RoleEntity {
    @Id
    @GeneratedValue
    private long id;
    private String name;
}

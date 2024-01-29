package com.usersDetails.users.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "Name is mandatory")
    @Size(min = 3,max = 7)
    private String username;

    @NotNull(message = "Email can't be empty")
    @Email(message = "Email required")
    private String email;

    @Max(100)
    private int age;

    @Pattern(regexp = "^(completed|processing)$")
    private String status;

    @OneToOne(cascade = CascadeType.ALL)
    private Car car;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "user")
    @JsonManagedReference
    private List<Address> addresses;


    public User( String username, String email, int age) {
        this.username = username;
        this.email = email;
        this.age = age;
    }

    public User(){
        this.id = UUID.randomUUID();
    }
}

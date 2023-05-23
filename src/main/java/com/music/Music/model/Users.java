package com.music.Music.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "userTable")

//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "songs")

public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @Email
    private String email;
    @NotNull
    private String password;
    @Min(18)
    @Max(65)
    private int age;

    @Pattern ( regexp = ("^\\+(?:[0-9] ?){6,14}[0-9]$"))
    private  String phone_Number;

    @JsonIgnore
    @OneToMany
    private List<Song> songs;
    @JsonIgnore
    @OneToOne(mappedBy = "user")
    private AuthenticationToken token;

}

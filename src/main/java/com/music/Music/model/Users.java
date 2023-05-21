package com.music.Music.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
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


    @OneToMany
    private List<Song> songs;
}

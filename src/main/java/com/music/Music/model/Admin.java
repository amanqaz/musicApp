package com.music.Music.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long adminId;
    @Email()
    private String email;
    @NotNull
    private String password;



    @OneToMany
    @JsonIgnore
    private List<Song> songs;

    @OneToOne
    @JsonIgnore
    private AuthenticationToken token;

}

package com.music.Music.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Song {

     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long songId;
    @NotNull
    private String songName;
    @NotNull
    private String writtenBy;

    private LocalDate releasedDate = LocalDate.now ();

    @ManyToMany
    @JsonIgnore

    private List<Users> user;
    @ManyToOne
    @JsonIgnore
    private  Admin admin;
}

package com.music.Music.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;
@Entity
@Data
@NoArgsConstructor

public class AuthenticationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tokenId;
    private String token;
    private LocalDate tokenCreationDate;

    @OneToOne()
    @JoinColumn(name = "fk_User_ID")
    private Users user;


    public AuthenticationToken( Users user) {
        this.user = user;
        this.tokenCreationDate = LocalDate.now();
        this.token = UUID.randomUUID().toString();

    }
    public AuthenticationToken( Admin admin) {

        this.tokenCreationDate = LocalDate.now();
        this.token = UUID.randomUUID().toString();
        this.user = null;
    }
}

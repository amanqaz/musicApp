package com.music.Music.repositatory;

import com.music.Music.model.AuthenticationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITokenRepo extends JpaRepository<AuthenticationToken,Long> {
}

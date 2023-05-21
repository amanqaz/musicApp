package com.music.Music.repositatory;

import com.music.Music.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISongRepo extends JpaRepository<Song,Long> {
}

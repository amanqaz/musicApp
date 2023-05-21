package com.music.Music.service;

import com.music.Music.model.Song;
import com.music.Music.repositatory.ISongRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SongService {
    @Autowired
    ISongRepo iSongRepo;

    public String addSong(Song song) {
        iSongRepo.save ( song );
        return "song Added successfully";
    }

    public String deleteSongById(Long songId) {
        boolean check=iSongRepo.existsById ( songId );
        if(check){
            iSongRepo.deleteById ( songId );
            return "delete successfully";
        }
        return "Please check the Id and try again";
    }
}

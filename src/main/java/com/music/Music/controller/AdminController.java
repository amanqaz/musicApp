package com.music.Music.controller;

import com.music.Music.dto.SignIn;
import com.music.Music.model.Admin;
import com.music.Music.model.Song;
import com.music.Music.service.AdminService;
import com.music.Music.service.SongService;
import com.music.Music.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin")
public class AdminController {
    @Autowired
    AdminService adminService;
    @Autowired
    TokenService
    tokenService;
    @Autowired
    SongService
    songService;


    @PostMapping("signup")
    public ResponseEntity<String> addAdmin(@RequestBody Admin admin){
        String respone = adminService.addAdmin ( admin );
        if(respone.equals ( "User already exists!!!!...sign in instead" )){
            return ResponseEntity.status ( HttpStatus.FORBIDDEN ).body ( respone );
        }
        return ResponseEntity.status ( HttpStatus.CREATED ).body ( respone );
    }

    @GetMapping("signin/{email}/{password}")
    public ResponseEntity<String> signIn(@PathVariable String email , @PathVariable String password){
        SignIn data = new SignIn ( email,password );
        String respone  = adminService.signIn(data);

        if(respone.equals ( "Check Email and Password again" )){
            return  ResponseEntity.status ( HttpStatus.BAD_GATEWAY ).body ( respone );
        }

        return ResponseEntity.ok ( "Please use this Token for making the request ->  "+  respone );
    }

    @PostMapping("addSong/{token}/{email}")
    public ResponseEntity<String> addSong(@RequestBody Song song, @PathVariable String token,@PathVariable String email)
    {
        String respone=null;
        //token check
        boolean tokenVaildation = tokenService.tokenVaildation(token,email);
        if(tokenVaildation){
            respone = songService.addSong(song,email);
            return ResponseEntity.ok ( respone );
        }
        return ResponseEntity.badRequest ().body (  "you are not valid Admin");
    }

    @DeleteMapping("deleteSong/{token}/{email}/{songId}")
    public ResponseEntity<String> deleteSongById(@PathVariable String token,@PathVariable String email,@PathVariable Long songId){
        String response = null;
        boolean tokenVaildation = tokenService.tokenVaildation(token,email);
        if(tokenVaildation){
            response = songService.deleteSongById(songId);
            return ResponseEntity.ok().body ( response);
        }
        return ResponseEntity.badRequest ().body (  "you are not valid Admin");

    }




}

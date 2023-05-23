package com.music.Music.controller;

import com.music.Music.dto.SignIn;
import com.music.Music.model.Song;
import com.music.Music.model.Users;
import com.music.Music.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("signUp")
    public ResponseEntity<String> addUser(@RequestBody Users user){
        String respone = userService.addUser(user);

        if(respone.equals ( "User already exists!!!!...sign in instead" )){
            return ResponseEntity.status ( HttpStatus.FORBIDDEN ).body ( respone );
        }
        return ResponseEntity.status ( HttpStatus.CREATED ).body ( respone );
    }
    @PostMapping("signin")
    public ResponseEntity<String> signIn(@RequestBody SignIn data){
        String respone  = userService.signIn(data);

        if(respone.equals ( "Check Email and Password again" )){
            return  ResponseEntity.status ( HttpStatus.BAD_GATEWAY ).body ( respone );
        }

        return ResponseEntity.ok ( "Please use this Token for making the request ->  "+  respone );
    }
    @GetMapping("showsongs")
    public ResponseEntity<List<Song>> displaySong(){
        return userService.showSong();
    }

    @PutMapping("markFavarate/{id}/{token}/{email}")
    public ResponseEntity<String> markingFavorate(@PathVariable Long id,@PathVariable String token,@PathVariable String email){
      return userService.markFavorate(id,token,email);
    }

    @GetMapping("userPlayList/{token}/{email}")
    public ResponseEntity<List<Song>> userPlayList(@PathVariable String token , @PathVariable String email){
        return  userService.userPlayList(token,email);
    }

}

package com.music.Music.service;

import com.music.Music.dto.SignIn;
import com.music.Music.model.AuthenticationToken;
import com.music.Music.model.Song;
import com.music.Music.model.Users;
import com.music.Music.repositatory.ISongRepo;
import com.music.Music.repositatory.IUserRepo;
import jakarta.xml.bind.DatatypeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public class UserService {
    @Autowired
    IUserRepo iUserRepo;

    @Autowired
    TokenService tokenService;

    @Autowired
    SongService
    songService;
    @Autowired
    private ISongRepo iSongRepo;


    public String addUser(Users user) {

        Users realUser = iUserRepo.findFirstByEmail ( user.getEmail () );



        if(realUser != null)
        {
           return "User already exists!!!!...sign in instead";
        }
        String string=user.getPhone_Number ();
        if(string.length ()==10){
            string ="+91"+string;
            user.setPhone_Number ( string );
        }



        //encryption
        String encryptedPassword = null;
        try {
            encryptedPassword = encryptPassword(user.getPassword ());//takes  a string and encrypts it...
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();

        }

        //save the user
        user.setPassword ( encryptedPassword );

        iUserRepo.save ( user );
        return ("User registered "+" User created successfully");

    }

    private String encryptPassword(String userPassword) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update(userPassword.getBytes());
        byte[] digested =  md5.digest();

        String hash = DatatypeConverter.printHexBinary(digested);
        return hash;
    }

    public String signIn(SignIn data) {
        Users realUser = iUserRepo.findFirstByEmail ( data.getEmail () );
        if(realUser == null){
            return "Check Email and Password again";
        }
        String dataPassword;
        try {
          dataPassword = encryptPassword ( data.getPassword ( ) );
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException ( e );
        }
        String respone = null;
        if(realUser.getPassword ().equals ( dataPassword )){
            //token generated
            AuthenticationToken token = new AuthenticationToken(realUser);

            tokenService.saveToken(token);
            realUser.setToken ( token );
            respone = token.getToken ();
            return respone;

        }
        return "Check Email and Password again";



    }

    public ResponseEntity<List<Song>> showSong() {
        List<Song> songs=songService.findAllSong();
        return ResponseEntity.ok ( songs );

    }

    public ResponseEntity<String> markFavorate(Long id, String token, String email) {
        Users user =  iUserRepo.findFirstByEmail ( email );
        if(user==null){
            return ResponseEntity.badRequest ().body ( "please check Email" );
        }
        Song song = songService.findById ( id );
        if(song ==null){
            return ResponseEntity.badRequest ().body ( "Invaild song !!! Please request to admin to added that song" );
        }
        List<Song> songList = user.getSongs ();
        songList.add(song);
         user.setSongs ( songList );
         iUserRepo.save ( user );
        return ResponseEntity.ok ( "song Added at your favorite list" );

    }

    public ResponseEntity<List<Song>> userPlayList(String token, String email) {
        Users users = iUserRepo.findFirstByEmail ( email );
        if(users == null)  return ResponseEntity.badRequest ().body ( null );
        String vaildToken = users.getToken ().getToken ();
        if(vaildToken.equals ( token )){
            return ResponseEntity.ok ( users.getSongs () );
        }
        return ResponseEntity.badRequest ().body ( null );


    }
}

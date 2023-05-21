package com.music.Music.service;

import com.music.Music.dto.SignIn;
import com.music.Music.model.AuthenticationToken;
import com.music.Music.model.Users;
import com.music.Music.repositatory.IUserRepo;
import jakarta.xml.bind.DatatypeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class UserService {
    @Autowired
    IUserRepo iUserRepo;

    @Autowired
    TokenService tokenService;


    public String addUser(Users user) {

        Users realUser = iUserRepo.findFirstByEmail ( user.getEmail () );



        if(realUser != null)
        {
           return "User already exists!!!!...sign in instead";
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
            respone = token.getToken ();
            return respone;

        }
        return "Check Email and Password again";



    }
}

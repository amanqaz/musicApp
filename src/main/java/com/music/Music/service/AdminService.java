package com.music.Music.service;

import com.music.Music.dto.SignIn;
import com.music.Music.model.Admin;
import com.music.Music.model.AuthenticationToken;

import com.music.Music.repositatory.IAdminRepo;
import jakarta.xml.bind.DatatypeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class AdminService {
    @Autowired
    IAdminRepo
    iAdminRepo;
    @Autowired
    TokenService
    tokenService;

    public String addAdmin(Admin admin) {
        Admin realUser = iAdminRepo.findFirstByEmail ( admin.getEmail () );



        if(realUser != null)
        {
            return "Admin already exists!!!!...sign in instead";
        }
        realUser = admin;


        //encryption
        String encryptedPassword = null;
        try {
            encryptedPassword = encryptPassword(admin.getPassword ());//takes  a string and encrypts it...
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();

        }

        //save the user
         realUser.setPassword ( encryptedPassword );

        iAdminRepo.save ( realUser );
        return ("admin registered "+" admin created successfully");

    }

    private String encryptPassword(String userPassword) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update(userPassword.getBytes());
        byte[] digested =  md5.digest();

        String hash = DatatypeConverter.printHexBinary(digested);
        return hash;
    }

    public String signIn(SignIn data) {
        Admin realAdmin = iAdminRepo.findFirstByEmail ( data.getEmail () );
        if(realAdmin == null){
            return "Check Email and Password again";
        }
        String dataPassword;
        try {
            dataPassword = encryptPassword ( data.getPassword ( ) );
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException ( e );
        }
        String respone = null;
        if(realAdmin.getPassword ().equals ( dataPassword )){
            //token generated
            AuthenticationToken token = new AuthenticationToken(realAdmin);
            realAdmin.setToken ( token );

        tokenService.saveToken(token);
            respone = token.getToken ();
            realAdmin.setToken ( new AuthenticationToken ( realAdmin ) );
            return respone;

        }
        return "Check Email and Password again";


    }

    public Admin findFirstByEmail(String email) {
        return iAdminRepo.findFirstByEmail ( email );
    }
}

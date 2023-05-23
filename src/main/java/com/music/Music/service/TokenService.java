package com.music.Music.service;

import com.music.Music.model.Admin;
import com.music.Music.model.AuthenticationToken;
import com.music.Music.repositatory.IAdminRepo;
import com.music.Music.repositatory.ITokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenService {
    @Autowired
    ITokenRepo
    iTokenRepo;
    @Autowired
    IAdminRepo
    adminService;
    public void saveToken(AuthenticationToken token) {
        iTokenRepo.save ( token );

    }

    public boolean tokenVaildation(String token, String email) {
        Admin admin =  adminService.findFirstByEmail ( email);
        String password = admin.getPassword ();



        return token.equals (password);

    }
}

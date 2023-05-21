package com.music.Music.repositatory;

import com.music.Music.model.Admin;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IAdminRepo extends JpaRepository<Admin,Long> {
    Admin findFirstByEmail(String email);
}

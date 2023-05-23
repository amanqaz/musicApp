package com.music.Music.repositatory;


import com.music.Music.model.Users;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepo extends JpaRepository<Users,Long> {
    Users findFirstByEmail(String email);
}

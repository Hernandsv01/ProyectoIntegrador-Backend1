package com.dh.integrador.security;

import com.dh.integrador.entities.AppUser;
import com.dh.integrador.entities.AppUsuarioRoles;
import com.dh.integrador.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    UserRepository userRepository;
    
    @Override
    public void run(ApplicationArguments args) throws Exception {
        BCryptPasswordEncoder passwordEncoder= new BCryptPasswordEncoder();
        
        String pass= passwordEncoder.encode("digital");
        userRepository.save(new AppUser("Rodolfo","Rodolfo","rodolfo@gmail.com",pass, AppUsuarioRoles.ROLE_USER));
    }
    
}

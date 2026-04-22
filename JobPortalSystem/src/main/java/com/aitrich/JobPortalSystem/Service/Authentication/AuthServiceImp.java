package com.aitrich.JobPortalSystem.Service.Authentication;

import com.aitrich.JobPortalSystem.DTO.LoginRequestDTO;
import com.aitrich.JobPortalSystem.Entity.User;
import com.aitrich.JobPortalSystem.Repository.IUserRepo;
import com.aitrich.JobPortalSystem.Security.IAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImp implements IAuthService {

    private final IUserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public String login(LoginRequestDTO dto){
        User user=userRepo.findByEmail(dto.getEmail())
                .orElseThrow(()->new RuntimeException("User Not Found"));

        if(!passwordEncoder.matches(dto.getPassword(), user.getPassword())){
            throw new RuntimeException("Invalid Password");
        }
        return "Login successful. Role: "+user.getRole();
    }
}

package com.krishav.completesetup.completesetup.services;

import com.krishav.completesetup.completesetup.dto.LoginDto;
import com.krishav.completesetup.completesetup.dto.LoginResponseDTO;
import com.krishav.completesetup.completesetup.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserService userService;
    private final SessionService sessionService;

    public LoginResponseDTO login(LoginDto loginDto) {

        Authentication authentication =authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword())
        );

        User user = (User) authentication.getPrincipal();

        String accesstoken = jwtService.generateAcessToken(user);
        String refrehToken = jwtService.generateRefreshToken(user);

        sessionService.generateNewSession(user,refrehToken);

        return new LoginResponseDTO(user.getId(), accesstoken , refrehToken);

    }

    public LoginResponseDTO refreshToken(String refreshToken) {

        Long userId = jwtService.getUserIdFromToken(refreshToken);
        sessionService.validateSession(refreshToken);

        User user = userService.getUserById(userId);

        String accessToken = jwtService.generateAcessToken(user);
        return new LoginResponseDTO(user.getId(), accessToken, refreshToken);
    }
}

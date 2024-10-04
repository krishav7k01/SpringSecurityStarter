package com.krishav.completesetup.completesetup.controllers;

import com.krishav.completesetup.completesetup.advices.ApiResponse;
import com.krishav.completesetup.completesetup.dto.LoginDto;
import com.krishav.completesetup.completesetup.dto.LoginResponseDTO;
import com.krishav.completesetup.completesetup.dto.SignUpDTO;
import com.krishav.completesetup.completesetup.dto.UserDto;
import com.krishav.completesetup.completesetup.services.AuthService;
import com.krishav.completesetup.completesetup.services.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final AuthService authService;


    @PostMapping("/signup")
    public ResponseEntity<UserDto> signUp(@RequestBody SignUpDTO signUpDTO)
    {
        UserDto userDto = userService.signUp(signUpDTO);

        return ResponseEntity.ok(userDto);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginDto loginDto, HttpServletRequest request, HttpServletResponse response) {

        LoginResponseDTO loginResponseDTO = authService.login(loginDto);


        Cookie cookie = new Cookie("refreshToken" , loginResponseDTO.getRefreshToken());
        cookie.setHttpOnly(true);
        response.addCookie(cookie);

        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + loginResponseDTO.getAccessToken())
                .body(loginResponseDTO);

    }

    @PostMapping("/refresh")
    public ResponseEntity<LoginResponseDTO> refresh(HttpServletRequest request)
    {
        String refreshToken = Arrays.stream(request.getCookies())
                .filter(cookie -> "refreshToken".equals(cookie.getName()))
                .findFirst()
                .map(cookie -> cookie.getValue())
                .orElseThrow(() -> new AuthenticationServiceException("Refresh token not found inside cookies"));

          LoginResponseDTO loginResponseDTO =   authService.refreshToken(refreshToken);

        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + loginResponseDTO.getAccessToken())
                .body(loginResponseDTO);

    }
}

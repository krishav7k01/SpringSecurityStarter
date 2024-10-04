package com.krishav.completesetup.completesetup.services;


import com.krishav.completesetup.completesetup.dto.SignUpDTO;
import com.krishav.completesetup.completesetup.dto.UserDto;
import com.krishav.completesetup.completesetup.entities.User;
import com.krishav.completesetup.completesetup.exceptions.ResourceNotFoundException;
import com.krishav.completesetup.completesetup.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

   private final UserRepository userRepository;
   private final ModelMapper modelMapper;
   private final PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username).orElseThrow(() -> new ResourceNotFoundException("Username Not Found"));
    }

    public User getUserById(Long userId)
    {
        return userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with id " + userId + " not found"));
    }

    public UserDto signUp(SignUpDTO signUpDTO)
    {
        Optional<User> user = userRepository.findByEmail(signUpDTO.getEmail());

        if(user.isPresent())
        {
            throw new BadCredentialsException("User with email exists" + signUpDTO.getEmail());
        }

        User toCreate = modelMapper.map(signUpDTO, User.class);
         toCreate.setPassword(passwordEncoder.encode(toCreate.getPassword()));
        User savedUser = userRepository.save(toCreate);

        return modelMapper.map(savedUser, UserDto.class);

    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    public User save(User newUser) {

        return userRepository.save(newUser);
    }
}

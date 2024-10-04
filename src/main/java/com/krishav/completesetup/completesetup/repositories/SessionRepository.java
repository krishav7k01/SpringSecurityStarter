package com.krishav.completesetup.completesetup.repositories;

import com.krishav.completesetup.completesetup.entities.Session;
import com.krishav.completesetup.completesetup.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SessionRepository extends JpaRepository<Session, Long > {


    List<Session> findByUser(User user);

    Optional<Session> findByRefreshToken(String refreshToken);
}

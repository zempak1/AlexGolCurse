package ru.makhmudov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.makhmudov.entity.UserReg;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserReg, Long> {
    Optional<UserReg> findUserByName(String username);

    Optional<UserReg> findUserByEmail(String email);

    Optional<UserReg> findUserById(Long id);
}

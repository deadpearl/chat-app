package kz.pearl.chat_service.repository;

import kz.pearl.chat_service.entity.Session;
import kz.pearl.chat_service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select c from User c where c.email = :email")
    Optional<User> findByEmail(String email);
}

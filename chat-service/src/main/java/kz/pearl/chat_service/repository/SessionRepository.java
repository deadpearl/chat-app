package kz.pearl.chat_service.repository;


import kz.pearl.chat_service.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {
    Session findFirstByTokenOrderByTokenCreateDateDesc(String token);
}

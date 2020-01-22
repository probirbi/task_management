package de.neasy.task.repository;

import de.neasy.task.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmailAndPassword(String email, String password);
    //User deleteInBatch(Long id);

    void deleteById(Long integer);

    User findById(Long id);

    //void update(User user);
}

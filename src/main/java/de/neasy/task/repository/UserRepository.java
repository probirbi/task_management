package de.neasy.task.repository;

import de.neasy.task.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmailAndPassword(String email, String password);
    //User findPagedResultByUserElseId(long Id, int offset, int limit);
    User findById(Long id);
}


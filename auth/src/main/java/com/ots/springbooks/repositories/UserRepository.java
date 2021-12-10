package com.ots.springbooks.repositories;

import com.ots.springbooks.models.UserData;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserData, Long> {

  Optional<UserData> findByLogin(String login);
}

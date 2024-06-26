package edu.tcu.cs.hogwartsartifactsonline.hogwartsuser;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<HogwartsUser, Integer> {
  
    Optional<HogwartsUser> findByUsername(String username);

}

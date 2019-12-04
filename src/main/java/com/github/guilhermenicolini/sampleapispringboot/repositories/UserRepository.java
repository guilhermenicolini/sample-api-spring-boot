package com.github.guilhermenicolini.sampleapispringboot.repositories;

import com.github.guilhermenicolini.sampleapispringboot.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    @Transactional(readOnly = true)
    User findOneByEmail(String email);
}
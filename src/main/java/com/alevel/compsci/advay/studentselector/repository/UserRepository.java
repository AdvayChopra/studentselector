package com.alevel.compsci.advay.studentselector.repository;


import com.alevel.compsci.advay.studentselector.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<AppUser, Integer> {

    AppUser findAllByUsername(String username);

    AppUser findByPassword(String password);

}

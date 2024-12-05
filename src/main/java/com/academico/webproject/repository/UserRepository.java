package com.academico.webproject.repository;

import com.academico.webproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//Trying this way, if I don't like I will try the way that I used do
@Repository
public interface UserRepository extends JpaRepository<User, String> {
}

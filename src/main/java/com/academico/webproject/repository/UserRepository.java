package com.academico.webproject.repository;

import com.academico.webproject.model.Band;
import com.academico.webproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//Trying this way, if I don't like I will try the way that I used do
@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);

    @Query("SELECT u FROM User u JOIN u.bands b WHERE b.id = :bandId")
    List<User> findMembersByBandId(@Param("bandId") String bandId);

}

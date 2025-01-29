package com.academico.webproject.repository;

import com.academico.webproject.model.Band;
import com.academico.webproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BandRepository extends JpaRepository<Band, String> {
    List<Band> findByMembersContaining( @Param("userId") User userId);
    List<Band> findByLeaderId(String leaderId);
}

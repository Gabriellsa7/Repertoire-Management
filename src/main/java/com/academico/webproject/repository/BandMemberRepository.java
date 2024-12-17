package com.academico.webproject.repository;

import com.academico.webproject.model.BandMember;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface BandMemberRepository extends JpaRepository<BandMember, String> {
    Optional<BandMember> findByBandIdAndUserId(String bandId, String userId);
    List<BandMember> findByBandId(String bandId);
    List<BandMember> findByUserId(String userId);
}
package org.capgemini.aarogyaNiketan.Repository;

import org.capgemini.aarogyaNiketan.model.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HospitalRepository extends JpaRepository<Hospital, Long>{
    List<Hospital> findAllByUserId(Long userId);
    List<Hospital> findAllByLocation(String location);
    Hospital findByIdAndUserId(Long id, Long userId);
}

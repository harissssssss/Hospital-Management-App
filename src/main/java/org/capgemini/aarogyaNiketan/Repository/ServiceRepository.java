package org.capgemini.aarogyaNiketan.Repository;

import org.capgemini.aarogyaNiketan.model.Services;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends JpaRepository<Services, Long> {
}

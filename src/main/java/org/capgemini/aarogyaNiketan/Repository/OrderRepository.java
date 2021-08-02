package org.capgemini.aarogyaNiketan.Repository;

import org.capgemini.aarogyaNiketan.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByUserId(Long userId);
    List<Order> findAllByHospitalIdInAndApprove(List<Long> hospitalId, Boolean approve);
}

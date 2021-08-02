package org.capgemini.aarogyaNiketan.service;

import org.capgemini.aarogyaNiketan.dto.request.OrderPostRequest;
import org.capgemini.aarogyaNiketan.model.Order;

import java.util.List;

public interface OrderService {
    Order create(OrderPostRequest orderPostRequest) throws Exception;
    List<Order> getByUserId(Long userId) throws Exception;
    Order get(Long orderId) throws Exception;
    void approveOrder(Long orderId) throws Exception;
}

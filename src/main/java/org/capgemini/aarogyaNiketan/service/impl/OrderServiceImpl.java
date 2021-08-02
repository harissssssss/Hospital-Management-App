package org.capgemini.aarogyaNiketan.service.impl;

import org.capgemini.aarogyaNiketan.Repository.HospitalRepository;
import org.capgemini.aarogyaNiketan.Repository.OrderRepository;
import org.capgemini.aarogyaNiketan.Repository.ServiceRepository;
import org.capgemini.aarogyaNiketan.dto.request.OrderPostRequest;
import org.capgemini.aarogyaNiketan.model.Hospital;
import org.capgemini.aarogyaNiketan.model.Order;
import org.capgemini.aarogyaNiketan.model.Services;
import org.capgemini.aarogyaNiketan.service.OrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    HospitalRepository hospitalRepository;

    @Autowired
    ServiceRepository serviceRepository;

    @Override
    public Order create(OrderPostRequest orderPostRequest) throws Exception {
        Order order = new Order();
        BeanUtils.copyProperties(orderPostRequest, order);
        Hospital hospital = hospitalRepository.getById(orderPostRequest.getHospitalId());
        Services services = serviceRepository.getById(orderPostRequest.getServiceId());
        order.setServices(services);
        order.setHospital(hospital);
        return orderRepository.save(order);
    }

    @Override
    public List<Order> get(Long userId) throws Exception {
        return orderRepository.findAllByUserId(userId);
    }
}

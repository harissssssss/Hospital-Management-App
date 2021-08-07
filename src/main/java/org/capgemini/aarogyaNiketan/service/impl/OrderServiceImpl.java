package org.capgemini.aarogyaNiketan.service.impl;

import org.capgemini.aarogyaNiketan.Repository.HospitalRepository;
import org.capgemini.aarogyaNiketan.Repository.OrderRepository;
import org.capgemini.aarogyaNiketan.Repository.ServiceRepository;
import org.capgemini.aarogyaNiketan.dto.request.OrderPostRequest;
import org.capgemini.aarogyaNiketan.model.Hospital;
import org.capgemini.aarogyaNiketan.model.Order;
import org.capgemini.aarogyaNiketan.model.Services;
import org.capgemini.aarogyaNiketan.service.OrderService;
import org.capgemini.aarogyaNiketan.util.UserHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    HospitalRepository hospitalRepository;

    @Autowired
    ServiceRepository serviceRepository;

    @Autowired
    UserHandler userHandler;

    @Override
    public Order create(OrderPostRequest orderPostRequest) throws Exception {
        Order order = new Order();
        BeanUtils.copyProperties(orderPostRequest, order);
        Hospital hospital = hospitalRepository.getById(orderPostRequest.getHospitalId());
        Services services = serviceRepository.getById(orderPostRequest.getServiceId());
        if (order.getNoOfServices() < 0 || services.getVacancy() < order.getNoOfServices()) {
            throw new Exception("Invalid number of bookings");
        }
        order.setServices(services);
        order.setHospital(hospital);
        return orderRepository.save(order);
    }

    @Override
    public List<Order> getByUserId(Long userId) throws Exception {
        return orderRepository.findAllByUserId(userId);
    }

    @Override
    public Order get(Long orderId) throws Exception {
        Optional<Order> orderOptional = orderRepository.findById(orderId);
        if (!orderOptional.isPresent()) {
            throw new Exception("No order present");
        }
        return orderOptional.get();
    }

    @Override
    public void approveOrder(Long orderId) throws Exception {
        Optional<Order> orderOptional = orderRepository.findById(orderId);
        Order order = new Order();
        if (orderOptional.isPresent()) {
            order = orderOptional.get();
            Hospital hospital = hospitalRepository.findByIdAndUserId(order.getHospital().getId(), userHandler.getLoggedInUser().getId());
            if (hospital == null) {
                throw new Exception("Permission Denied to approve this booking");
            }
        } else {
            throw new Exception("No order found");
        }
        order.setApprove(Boolean.TRUE);
        order.setApprovedAt(ZonedDateTime.now());
        orderRepository.save(order);

        Services services = order.getServices();
        services.setVacancy(services.getVacancy() - order.getNoOfServices());
        serviceRepository.save(services);
    }


}

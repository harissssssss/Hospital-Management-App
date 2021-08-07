package org.capgemini.aarogyaNiketan.controller;

import org.capgemini.aarogyaNiketan.dto.request.OrderPostRequest;
import org.capgemini.aarogyaNiketan.dto.response.HospitalPostResponse;
import org.capgemini.aarogyaNiketan.dto.response.OrderPostResponse;
import org.capgemini.aarogyaNiketan.dto.response.ServicesPostResponse;
import org.capgemini.aarogyaNiketan.model.Order;
import org.capgemini.aarogyaNiketan.service.OrderService;
import org.capgemini.aarogyaNiketan.util.UserHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class OrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    UserHandler userHandler;

    @PostMapping(path = "/v1/order")
    public ResponseEntity<OrderPostResponse> create(@RequestBody OrderPostRequest orderPostRequest) throws Exception {
        Order order = orderService.create(orderPostRequest);

        HospitalPostResponse hospitalPostResponse = new HospitalPostResponse();
        BeanUtils.copyProperties(order.getHospital(), hospitalPostResponse);

        ServicesPostResponse servicesPostResponse = new ServicesPostResponse();
        BeanUtils.copyProperties(order.getServices(), servicesPostResponse);

        OrderPostResponse orderPostResponse = new OrderPostResponse();
        orderPostResponse.setHospital(hospitalPostResponse);
        orderPostResponse.setServices(servicesPostResponse);
        BeanUtils.copyProperties(order, orderPostResponse);

        return new ResponseEntity<>(orderPostResponse, HttpStatus.CREATED);
    }

    @GetMapping(path = "/v1/order")
    public ResponseEntity<List<OrderPostResponse>> getAllByUserId() throws Exception {
        List<Order> order = orderService.getByUserId(userHandler.getLoggedInUser().getId());
        List<OrderPostResponse> orderPostResponse = new ArrayList<>();
        for (Order o : order) {
            HospitalPostResponse hospitalPostResponse = new HospitalPostResponse();
            BeanUtils.copyProperties(o.getHospital(), hospitalPostResponse);

            ServicesPostResponse servicesPostResponse = new ServicesPostResponse();
            BeanUtils.copyProperties(o.getServices(), servicesPostResponse);

            OrderPostResponse orderResponse = new OrderPostResponse();
            orderResponse.setHospital(hospitalPostResponse);
            orderResponse.setServices(servicesPostResponse);
            BeanUtils.copyProperties(o, orderResponse);
            orderPostResponse.add(orderResponse);
        }

        return new ResponseEntity<>(orderPostResponse, HttpStatus.CREATED);
    }

    @GetMapping(path = "/v1/order/{orderId}")
    public ResponseEntity<OrderPostResponse> get(@PathVariable Long orderId) throws Exception {
        Order order = orderService.get(orderId);

        HospitalPostResponse hospitalPostResponse = new HospitalPostResponse();
        BeanUtils.copyProperties(order.getHospital(), hospitalPostResponse);

        ServicesPostResponse servicesPostResponse = new ServicesPostResponse();
        BeanUtils.copyProperties(order.getServices(), servicesPostResponse);

        OrderPostResponse orderPostResponse = new OrderPostResponse();
        orderPostResponse.setHospital(hospitalPostResponse);
        orderPostResponse.setServices(servicesPostResponse);
        BeanUtils.copyProperties(order, orderPostResponse);

        return new ResponseEntity<>(orderPostResponse, HttpStatus.CREATED);
    }

    @PostMapping(path = "/v1/order/approve")
    public ResponseEntity<Void> approveOrder(@RequestParam Long orderId) throws Exception {
        orderService.approveOrder(orderId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

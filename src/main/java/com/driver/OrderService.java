package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrderService {

    OrderService(){}
    @Autowired
    OrderRepository orderRepository;

    public void saveOrder(Order order) {
        orderRepository.saveOrder(order);
    }

    public void savePartner(String partnerId) {
        orderRepository.savePartner(partnerId);
    }

    public void saveOrderPartnerPair(String orderId,String partnerId) {
        orderRepository.saveOrderPartnerPair(orderId,partnerId);
    }

    public Order findOrderById(String orderId) {
        return orderRepository.findOrderById(orderId);
    }

    public DeliveryPartner findPartnerById(String partnerId) {
        return orderRepository.findPartnerById(partnerId);
    }

    public int  findOrderCountByPartnerId(String partnerId) {
        return orderRepository.findOrderCountByPartnerId(partnerId);
    }

    public List<String> findOrdersByPartnerId(String partnerId) {
        return orderRepository.findOrdersByPartnerId(partnerId);
    }

    public List<String> findAllOrders() {
        return orderRepository.findAllOrders();
    }

    public int findCountOfUnassignedOrders() {
        return orderRepository.findCountOfUnassignedOrders();
    }

    public int findOrdersLeftAfterGivenTimeByPartnerId(String time, String partnerId) {
        return orderRepository.findOrdersLeftAfterGivenTimeByPartnerId(time,partnerId);
    }

    public String findLastDeliveryTimeByPartnerId(String partnerId) {
        return orderRepository.findLastDeliveryTimeByPartnerId(partnerId);
    }

    public void removePartnerById(String partnerId) {
        orderRepository.removePartnerById(partnerId);
    }

    public void removeOrderById(String orderId) {
        orderRepository.removeOrderById(orderId);
    }
}

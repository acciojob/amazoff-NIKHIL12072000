package com.driver;

import org.springframework.beans.factory.annotation.Autowired;

public class OrderService {
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

    public void findOrderById(String orderId) {
        orderRepository.findOrderById(orderId);
    }

    public void findPartnerById(String partnerId) {
        orderRepository.findPartnerById(partnerId);
    }

    public void findOrderCountByPartnerId(String partnerId) {
        orderRepository.findOrderCountByPartnerId(partnerId);
    }

    public void findOrdersByPartnerId(String partnerId) {
        orderRepository.findOrdersByPartnerId(partnerId);
    }

    public void findAllOrders() {
        orderRepository.findAllOrders();
    }

    public void findCountOfUnassignedOrders() {
        orderRepository.findCountOfUnassignedOrders();
    }

    public void findOrdersLeftAfterGivenTimeByPartnerId(String time, String partnerId) {
        orderRepository.findOrdersLeftAfterGivenTimeByPartnerId(time,partnerId);
    }

    public void findLastDeliveryTimeByPartnerId(String partnerId) {
        orderRepository.findLastDeliveryTimeByPartnerId(partnerId);
    }

    public void removePartnerById(String partnerId) {
        orderRepository.removePartnerById(partnerId);
    }

    public void removeOrderById(String orderId) {
        orderRepository.removeOrderById(orderId);
    }
}

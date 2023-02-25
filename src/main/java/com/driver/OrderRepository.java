package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class OrderRepository {

    private HashMap<String,Order> orderMap;
    private HashMap<String,DeliveryPartner> deliveryPartnerMap;

    private HashMap<String,String> orderPartnerPairMap;
    private HashMap<String, List<Order>> orderPartnerMap;

    OrderRepository(){
        this.orderMap=new HashMap<>();
        this.deliveryPartnerMap=new HashMap<>();
        this.orderPartnerPairMap=new HashMap<>();
        this.orderPartnerMap=new HashMap<>();
    }
    public void saveOrder(Order order) {
        orderMap.put(order.getId(),order);
        orderPartnerMap.get("nopartner").add(order);
        orderPartnerPairMap.put(order.getId(), "nopartner");
    }

    public void savePartner(String partnerId) {
        DeliveryPartner partner=new DeliveryPartner(partnerId);
        deliveryPartnerMap.put(partnerId,partner);
        orderPartnerMap.put(partnerId,new ArrayList<>());
    }

    public void saveOrderPartnerPair(String orderId, String partnerId) {
        if(partnerId.equals(null)||partnerId.trim().equals("")) partnerId="nopartner";
        List<Order> update=orderPartnerMap.get(partnerId);
        update.add(orderMap.get(orderId));
        orderPartnerMap.put(partnerId,update);
        int x=deliveryPartnerMap.get(partnerId).getNumberOfOrders();
        deliveryPartnerMap.get(partnerId).setNumberOfOrders(x+1);
        orderPartnerPairMap.put(orderId,partnerId);
    }

    public Order findOrderById(String orderId) {
        return orderMap.get(orderId);
    }

    public DeliveryPartner findPartnerById(String partnerId) {
        return deliveryPartnerMap.get(partnerId);
    }

    public int findOrderCountByPartnerId(String partnerId) {
        return orderPartnerMap.get(partnerId).size();
    }

    public List<String> findOrdersByPartnerId(String partnerId) {
        List<String> orders=new ArrayList<>();
        for(Order order:orderPartnerMap.get(partnerId)){
            orders.add(order.getId());
        }
        return orders;
    }

    public List<String> findAllOrders() {
        List<String> orders=new ArrayList<>();
        for(String orderId:orderPartnerMap.keySet())
            orders.add(orderId);
        return orders;
    }

    public int findCountOfUnassignedOrders() {
        return orderPartnerMap.containsKey("nopartner")?orderPartnerMap.get("nopartner").size():0;
    }

    public int findOrdersLeftAfterGivenTimeByPartnerId(String time, String partnerId) {
        String[] times=time.split(":");
        int t=Integer.parseInt(times[0])*60+Integer.parseInt(times[1]);
        int count=0;
        for(Order order:orderPartnerMap.get(partnerId)){
            if(order.getDeliveryTime()>t) count++;
        }
        return count;
    }

    public String findLastDeliveryTimeByPartnerId(String partnerId) {
        int last=0;
        for(Order order:orderPartnerMap.get(partnerId)){
            last=Math.max(last,order.getDeliveryTime());
        }
        String time=(last/60)+":"+(last%60);
        return time;
    }

    public void removePartnerById(String partnerId) {
        deliveryPartnerMap.remove(partnerId);
        List<Order> al=orderPartnerMap.get(partnerId);
        List<Order> nal=orderPartnerMap.get("nopartner");
        for(Order order:al){
            nal.add(order);
            orderPartnerPairMap.put(order.getId(), "nopartner");
        }
        orderPartnerMap.put("nopartner",nal);
        orderPartnerMap.remove(partnerId);
    }

    public void removeOrderById(String orderId) {
        Order order=orderMap.get(orderId);
        orderMap.remove(orderId);
        String partnerId=orderPartnerPairMap.get(orderId);
        orderPartnerMap.get(partnerId).remove(order);
        orderPartnerPairMap.put(orderId,"nopartner");
        List<Order> nal=orderPartnerMap.get("nopartner");
        nal.add(order);
    }
}
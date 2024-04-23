package tn.spring.pispring.Interfaces;

import tn.spring.pispring.Entities.OrderItem;

import java.util.List;

public interface OrderItemInterface {
    OrderItem addOrderItem(OrderItem orderItem);

    List<OrderItem> retrieveOrderItems();

    OrderItem updateOrderItem(OrderItem orderItem);

    OrderItem updateOrderItem(int id, OrderItem orderItem);

    OrderItem retrieveOrderItem(int id);

    void removeOrderItem(int id);


    OrderItem addOrderItemForStaticUser(OrderItem orderItem);



    OrderItem addOrderItem(int productId);


    OrderItem addOrderItem1(int productId);




}

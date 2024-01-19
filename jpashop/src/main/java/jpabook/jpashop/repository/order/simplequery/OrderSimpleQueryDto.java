package jpabook.jpashop.repository.order.simplequery;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderStatus;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 주문 조회 API (V4. JPA에서 DTO로 바로 조회)를 위한 DTO
 */
@Data
public class OrderSimpleQueryDto {

    private Long orderId;
    private String name;  //member
    private LocalDateTime orderDate;
    private OrderStatus orderStatus;
    private Address address;  //delivery

    public OrderSimpleQueryDto(Long orderId, String name, LocalDateTime
            orderDate, OrderStatus orderStatus, Address address) {
        this.orderId = orderId;
        this.name = name;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.address = address;
    }
}

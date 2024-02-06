package jpabook.jpashop.api;

import jpabook.jpashop.domain.*;
import jpabook.jpashop.repository.OrderRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * (OneToMany)에서의 성능 최적화를 어떻게 할 것인가
 */
@RestController
@RequiredArgsConstructor
public class OrderApiController {

    private final OrderRepository orderRepository;

    /**
     * V1. 엔티티 직접 노출
     * - Hibernate5Module 모듈 등록, LAZY=null 처리
     * - 양방향 관계 문제 발생 -> @JsonIgnore
     */
//    @GetMapping("/api/v2/orders")
    public List<Order> ordersV1() {
        List<Order> all = orderRepository.findAllByString(new OrderSearch());
        for (Order order : all) {
            order.getMember().getName();  //Lazy 강제 초기화
            order.getDelivery().getAddress();  //Lazy 강제 초기화
            List<OrderItem> orderItems = order.getOrderItems();
            orderItems.stream().forEach(o -> o.getItem().getName());  //Lazy 강제 초기화
        }
        return all;
    }

    /**
     * V2. 엔티티를 조회해서 DTO로 변환(fetch join 사용X)
     * - 트랜잭션 안에서 지연 로딩 필요
     */
    @GetMapping("/api/v2/orders")
    public List<OrderDto> orderV2() {
        List<Order> orders = orderRepository.findAllByString(new OrderSearch());
        List<OrderDto> result = orders.stream()
                .map(o -> new OrderDto(o))
                .collect(toList());

        return result;
    }

    /**
     * V3. 엔티티를 조회해서 DTO로 변환(fetch join 사용 O)
     * - 페이징 시에는 N 부분을 포기해야함(대신에 batch fetch size? 옵션 주면 N -> 1 쿼리로 변경 가능)
     */
    @GetMapping("/api/v3/orders")
    public List<OrderDto> orderV3() {
        List<Order> orders = orderRepository.findAllWithTeam();
        List<OrderDto> result = orders.stream()
                .map(o -> new OrderDto(o))
                .collect(toList());

        return result;
    }

    @GetMapping("/api/v3.1/orders")
    public List<OrderDto> orderV3_page(@RequestParam(value = "offset", defaultValue = "0") int offset,
                                       @RequestParam(value = "limit", defaultValue = "100") int limit) {
        List<Order> orders = orderRepository.findAllWithMemberDelivery();
        List<OrderDto> result = orders.stream()
                .map(o -> new OrderDto(o))
                .collect(toList());

        return result;
    }

    @Data
    static class OrderDto {
        private Long orderId;
        private String name;
        private LocalDateTime orderDate;
        private OrderStatus orderStatus;
        private Address address;
        private List<OrderItemDto> orderItems;

        public OrderDto(Order order) {
            orderId = order.getId();
            name = order.getMember().getName();
            orderDate = order.getOrderDate();
            orderStatus = order.getStatus();
            address = order.getDelivery().getAddress();
            order.getOrderItems().stream()
                    .map(orderItem -> new OrderItemDto(orderItem))
                    .collect(toList());
        }
    }

    @Data
    static class OrderItemDto {
        private String itemName;  //상품 명
        private int orderPrice;  //주문 가격
        private int count;  //주문 수량

        public OrderItemDto(OrderItem orderItem) {
            itemName = orderItem.getItem().getName();
            orderPrice = orderItem.getOrderPrice();
            count = orderItem.getCount();
        }
    }

}

package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class OrderServiceTest {

    @Autowired OrderService orderService;
    @Autowired OrderRepository orderRepository;

    @Test
    private void 상품주문() throws Exception {

        //given

        //when


        //then

    }

    @Test
    private void 주문취소() throws Exception {

        //given

        //when


        //then

    }

    @Test
    private void 상품주문_재고수량초과() throws Exception {

        //given

        //when


        //then

    }

}
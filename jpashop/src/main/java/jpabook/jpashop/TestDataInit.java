package jpabook.jpashop;

import jakarta.annotation.PostConstruct;
import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.repository.ItemRepository;
import jpabook.jpashop.repository.MemberRepository;
import jpabook.jpashop.service.ItemService;
import jpabook.jpashop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TestDataInit {

    private final MemberService memberService;
    private final ItemService itemService;

    /**
     * 테스트용 데이터 추가
     */
    @PostConstruct
    public void init() {

        Member member1 = new Member();
        Address address1 = new Address("A1", "B2", "C3");
        member1.setLoginId("user1");
        member1.setPassword("1234");
        member1.setName("James");
        member1.setAddress(address1);
        memberService.join(member1);

        Member member2 = new Member();
        Address address2 = new Address("C1", "D2", "E3");
        member2.setLoginId("user2");
        member2.setPassword("1234");
        member2.setName("Kevin");
        member2.setAddress(address1);
        memberService.join(member2);

        Book book1 = new Book();
        book1.setName("book1");
        book1.setPrice(12000);
        book1.setStockQuantity(2);
        book1.setAuthor("Ronaldo");
        book1.setIsbn("123");
        itemService.saveItem(book1);

        Book book2 = new Book();
        book2.setName("book2");
        book2.setPrice(14000);
        book2.setStockQuantity(5);
        book2.setAuthor("Messi");
        book2.setIsbn("456");
        itemService.saveItem(book2);
    }

}

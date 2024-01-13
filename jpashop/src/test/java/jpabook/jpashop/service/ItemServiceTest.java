package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
public class ItemServiceTest {

    @Autowired ItemService itemService;
    @Autowired ItemRepository itemRepository;

    @Test
    public void 상품등록() throws Exception {

        //given
        Book book1 = new Book();
        book1.setAuthor("kim");

        Book book2 = new Book();
        book2.setAuthor("Jung");

        //when
        itemService.saveItem(book1);
        itemService.saveItem(book2);
        List<Item> items = itemService.findItems();

        //then
        Assertions.assertThat(items.size()).isEqualTo(2);
    }

}
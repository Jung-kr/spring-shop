package jpabook.jpashop.domain.item;

import jakarta.persistence.*;
import jpabook.jpashop.domain.Category;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
//상속 관계 전략을 부모 클래스에 지정 (Join도 있지만 싱글 테이블 전략으로 지정)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")  //싱글 테이블이기 때문에 DB가 구분할 수 있어야하기 때문
@Getter
@Setter
public abstract class Item {

    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;

    private int price;

    private int stockQuantity;

    @ManyToMany(mappedBy = "items")  //실무에서는 사용 X
    private List<Category> categories = new ArrayList<>();
}

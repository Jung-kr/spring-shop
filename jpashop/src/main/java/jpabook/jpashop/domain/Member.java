package jpabook.jpashop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Embedded  //내장 타입 (DB 저장할 때는 다 풀어서 저장됨)
    private Address address;

    @OneToMany(mappedBy = "member")  //연관관계 주인인 Order 테이블의 member 필드에 의해 매핑됐음을 나타냄
    private List<Order> orders = new ArrayList<>();  // 컬렉션은 필드에서 초기화 하자 (자료)

}

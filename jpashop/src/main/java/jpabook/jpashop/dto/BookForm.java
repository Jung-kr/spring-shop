package jpabook.jpashop.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BookForm {

    private Long id;

    @NotEmpty(message = "상품 이름은 필수입니다.")
    private String name;

    @Min(value = 10, message = "상품 가격은 10원 이상을 입력해주세요.")
    private int price;

    @Min(value = 1, message = "상품 수량은 1개 이상을 입력해주세요.")
    private int stockQuantity;
    private String author;
    private String isbn;
}

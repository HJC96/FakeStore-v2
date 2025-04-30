package com.fakeapi.FakeStore.product.dto;


import com.fakeapi.FakeStore.product.domain.Rating;
import jakarta.annotation.Nullable;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor

//@Data
//@Getter
@Setter
@ToString @Builder
public class ProductDTO {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private Double price;
    private String description;
    private String category;
    private String image;
    @Nullable
    private Rating rating;

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

//     double데이터 소수점이 0인경우 정수로 출력을 위해
//    public String getPriceAsString() {
//        if (price % 1 == 0) {
//            return String.format("%.0f", price);
//        } else {
//            return price.toString();
//        }
//    }

    public Double getPrice() {
        return price;
    }

//    public String getPrice() {
//        return price;
//    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public String getimage() {
        return image;
    }

    public Rating getRating() {
        return rating;
    }
}

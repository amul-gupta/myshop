package com.myshop.product.dto;

import com.myshop.product.entity.Review;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto {

    private UUID uuid;

    private String title;

    private String shortDesc;
    private String longDesc;

    private Double price;
    private Integer discount;

    private Boolean live;

    private String categoryTitle;

}

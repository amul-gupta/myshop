package com.myshop.product.dto;


import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewDto {

    private UUID uuid;

    private String title;
    private String comment;
    private Double rating;
    private ProductDto productDto;
}

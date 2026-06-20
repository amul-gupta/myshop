package com.myshop.product.dto;


import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewDto {

    private UUID id;

    private String title;
    private String comment;
    private Double rating;
    private ProductDto productDto;
}

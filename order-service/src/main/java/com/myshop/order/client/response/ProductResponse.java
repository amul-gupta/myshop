package com.myshop.order.client.response;

import lombok.Data;

import java.util.UUID;

@Data
public class ProductResponse {


    private UUID uuid;

    private String title;

    private String shortDesc;
    private String longDesc;

    private Double price;
    private Integer discount;

    private Boolean live;

    private String categoryTitle;
}

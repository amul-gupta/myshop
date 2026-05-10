package com.myshop.product.exception;
import lombok.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorResponse {

    private String message;
    private int status;
    private LocalDateTime timestamp;

}
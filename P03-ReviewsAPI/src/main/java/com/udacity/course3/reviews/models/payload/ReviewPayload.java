package com.udacity.course3.reviews.models.payload;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReviewPayload {
    private Integer product;
    private String review;
    private String reviewer;
    private Integer productId;
}

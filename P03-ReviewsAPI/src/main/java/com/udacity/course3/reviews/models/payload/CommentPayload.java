package com.udacity.course3.reviews.models.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentPayload {
    private String comment;
    private String user;
    private Integer review;
}

package com.udacity.course3.reviews.models.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentPayload {
    @NotNull
    private String comment;
    private String user;

    @NotNull
    private Integer reviewId;
}

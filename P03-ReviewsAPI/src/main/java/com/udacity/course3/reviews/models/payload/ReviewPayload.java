package com.udacity.course3.reviews.models.payload;


import com.udacity.course3.reviews.models.mongo.CommentNosql;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class ReviewPayload {
    private Integer id;

    @NotNull
    private Integer productId;

    @NotNull
    private String review;
    private String reviewer;
    private Date createdAt;
    private List<CommentNosql> comments;
}

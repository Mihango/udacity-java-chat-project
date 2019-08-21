package com.udacity.course3.reviews.models.mongo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@Document(value = "reviews")
public class ReviewNoSql {
    private Integer id;
    private String reviewer;
    private String review;
    private Date createdAt;
    private Integer productId;
    private List<CommentNosql> comments;
}

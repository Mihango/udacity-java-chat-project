package com.udacity.course3.reviews.models.mongo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class CommentNosql {
    private String comment;
    private String user;
    private Date createdAt;
}

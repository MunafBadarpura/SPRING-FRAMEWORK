package org.munaf.a33_mongo_with_springboot.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Document(collection = "users")
@CompoundIndexes({ // creating index on multiple fields
        @CompoundIndex(
                name = "email_name_idx",
                def = "{'email': 1, 'name': 1}"
        )
})
public class UserMaster {

    @Id
    private String id;

    private String name;

    private String email;

    private Set<UserRole> userRoles = new HashSet<>();

    private Address address; // embedding document (here address cant make sense without user that is why we used embedding)

    @DBRef(lazy = true) // The @DBRef annotation tells Spring to store references to LearningCourse documents rather than embedding them.
    // The lazy = true parameter means products are only loaded when accessed, improving performance.
    private List<LearningCourse> enrolledCourses; // Document Referencing


    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

}

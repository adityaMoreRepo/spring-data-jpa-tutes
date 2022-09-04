package com.jpa.tutorial.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

//@Entity //we don't wanna persist it as it's jsut demo
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CourseDup {
    //this is to show how we can use UUID for
    //massive data entries which are in order of millions.
    //int and bigint(long in java) will fail to address this issue.
    //We use varchar in mysql while here in java we use UUID
    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    @Type(type = "org.hibernate.type.UUIDCharType")//for specifying that we are using
    //varchar in database.
    @Column(
            columnDefinition = "varbinary(16)",
            updatable = false,
            nullable = false
    )
    //this annotation helps hibernate to tell about underneath DB
    private UUID courseId;
    private int standard;

}

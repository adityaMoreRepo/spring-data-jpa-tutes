package com.jpa.tutorial.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@IdClass(NameId.class)//we gonna set a IdClass for composite keys
public class CourseComposite {
    @Id
    String courseName;
    @Id
    int id;
    String standard;
    //here we are going to create composite primary keys

}

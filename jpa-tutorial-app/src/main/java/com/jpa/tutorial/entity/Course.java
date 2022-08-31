package com.jpa.tutorial.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Course {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private int courseId;
    private int standard;
    @UpdateTimestamp
    private LocalDateTime lastUpdateDate;
    @CreationTimestamp
    private LocalDateTime createDate;

    @OneToMany(targetEntity = Subject.class,
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "course_id", referencedColumnName = "courseId")
    private List<Subject> subjects;
}

package com.demo.springDataJpaDemo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = "course")
public class CourseMaterial {

    @Id
    @SequenceGenerator(
            name = "course_material_sequence",
            sequenceName = "course_material_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "course_material_sequence"
    )
    private Long courseMaterialId;

    private String url;

    @OneToOne(
            cascade = CascadeType.ALL,      // in sql -> ON DELETE CASCADE, ON UPDATE CASCADE
            fetch = FetchType.LAZY,         // LAZY -> fetch data from parent only when needed | EAGER -> fetch data from parent as well
            optional = false                // default -> true | false -> courseMaterial must have a course
    )
    @JoinColumn(
            name = "course_id",
            referencedColumnName = "courseId"
    )
    private Course course;
}

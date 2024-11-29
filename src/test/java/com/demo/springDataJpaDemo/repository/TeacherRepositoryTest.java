package com.demo.springDataJpaDemo.repository;

import com.demo.springDataJpaDemo.entity.Course;
import com.demo.springDataJpaDemo.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TeacherRepositoryTest {

    @Autowired
    private TeacherRepository teacherRepository;


    @Test
    public void test_saveTeacherWithCourse() {
        Course course1 = Course.builder()
                .title("OOP")
                .credit(3)
                .build();

        Course course2 = Course.builder()
                .title("SE")
                .credit(2)
                .build();

        Teacher teacher = Teacher.builder()
                .firstName("Tom")
                .lastName("Dee")
                .courses(List.of(course1, course2))
                .build();

        System.out.println(teacherRepository.save(teacher));
    }
}
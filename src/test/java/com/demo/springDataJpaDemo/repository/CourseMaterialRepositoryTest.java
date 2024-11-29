package com.demo.springDataJpaDemo.repository;

import com.demo.springDataJpaDemo.entity.Course;
import com.demo.springDataJpaDemo.entity.CourseMaterial;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CourseMaterialRepositoryTest {

    @Autowired
    private CourseMaterialRepository courseMaterialRepository;


    @Test
    public void test_saveCourseMaterial() {
        Course course = Course.builder()
                .title("DSA")
                .credit(3)
                .build();

        CourseMaterial courseMaterial = CourseMaterial.builder()
                .url("www.test1.com")
                .course(course)
                .build();

        System.out.println(courseMaterialRepository.save(courseMaterial));
    }

    @Test
    public void test_getCourseMaterials() {
        List<CourseMaterial> courseMaterials = courseMaterialRepository.findAll();
        // Exclude the 'course' field from toString to prevent LazyInitializationException caused by accessing a lazy-loaded field outside an active session.
        courseMaterials.forEach(courseMaterial -> System.out.println(courseMaterial));
    }
}
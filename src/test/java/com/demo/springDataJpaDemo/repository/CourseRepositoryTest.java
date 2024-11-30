package com.demo.springDataJpaDemo.repository;

import com.demo.springDataJpaDemo.entity.Course;
import com.demo.springDataJpaDemo.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@SpringBootTest
class CourseRepositoryTest {

    @Autowired
    private CourseRepository courseRepository;


    @Test
    public void test_getCourses() {
        List<Course> courses = courseRepository.findAll();
        courses.forEach(course -> System.out.println(course));
    }

    @Test
    public void test_saveCourseWithTeacher() {
        Teacher teacher = Teacher.builder()
                .firstName("Lee")
                .lastName("Chang")
                .build();

        Course course = Course.builder()
                .title("C")
                .credit(3)
                .teacher(teacher)
                .build();

        System.out.println(courseRepository.save(course));
    }

    @Test
    public void test_getCoursesPage() {
        Pageable pageRequest = PageRequest.of(1, 2);
        Page<Course> page1 = courseRepository.findAll(pageRequest);

        long totalElements = page1.getTotalElements();
        long totalPages = page1.getTotalPages();

        System.out.println(totalPages);
        System.out.println(totalElements);

        List<Course> courses = page1.getContent();
        courses.forEach(course -> System.out.println(course));
    }

    @Test void test_getSortedCoursesPage() {
        Sort sort = Sort.by(Sort.Direction.DESC, "title");
        Pageable pageRequest = PageRequest.of(0, 4, sort);
        Page<Course> page1 = courseRepository.findAll(pageRequest);

        List<Course> courses = page1.getContent();
        courses.forEach(course -> System.out.println(course));
    }
}
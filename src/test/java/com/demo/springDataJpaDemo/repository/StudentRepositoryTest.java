package com.demo.springDataJpaDemo.repository;

import com.demo.springDataJpaDemo.entity.Guardian;
import com.demo.springDataJpaDemo.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
//@DataJpaTest
class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void test_saveStudent() {
        Student student = Student.builder()
                .firstName("John")
                .lastName("Doe")
                .emailId("john2@email.com")
//                .guardianName("John Wick")
//                .guardianEmail("wick@emil.com")
//                .guardianMobile("1234567890")
                .build();

        studentRepository.save(student);
    }

    @Test
    public void test_saveStudentWithGuardian() {
        Guardian guardian = Guardian.builder()
                .name("John Wick")
                .email("john2@email.com")
                .mobile("1234567890")
                .build();

        Student student = Student.builder()
                .firstName("Jane")
                .lastName("Doe")
                .emailId("jane@email.com")
                .guardian(guardian)
                .build();

        System.out.println(studentRepository.save(student));
    }

    @Test
    public void test_getStudents() {
        List<Student> students = studentRepository.findAll();
        for (Student student : students) {
            System.out.println(student);
        }
    }

    @Test
    public void test_getStudentsByFirstName() {
        List<Student> students = studentRepository.findAllByFirstName("John");
        for (Student student : students) {
            System.out.println(student);
        }
    }

    @Test
    public void test_getStudentsByFirstNameContaining() {
        List<Student> students = studentRepository.findAllByFirstNameContaining("oH");
        for (Student student : students) {
            System.out.println(student);
        }
    }

    @Test
    public void test_getStudentsByGuardianEmail() {
        List<Student> students = studentRepository.findAllByGuardianEmail("wick@emil.com");
        for (Student student : students) {
            System.out.println(student);
        }
    }

    @Test
    public void test_getStudentsByFirstNameAndLastName() {
        System.out.println(studentRepository.findFirstByFirstNameAndLastName("Jane", "Doe"));
    }

    @Test
    public void test_getStudentByEmail() {
        Student student = studentRepository.getStudentByEmailAddress("jane@email.com");
        System.out.println(student);
    }

    @Test
    public void test_getStudentFirstNameByEmail() {
        System.out.println(studentRepository.getStudentFirstNameByEmailAddress("jane@email.com"));
    }
}
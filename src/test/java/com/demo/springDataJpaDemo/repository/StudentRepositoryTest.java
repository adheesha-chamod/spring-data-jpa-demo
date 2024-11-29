package com.demo.springDataJpaDemo.repository;

import com.demo.springDataJpaDemo.entity.Guardian;
import com.demo.springDataJpaDemo.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
//@DataJpaTest
class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;


    // pre-build
    @Test
    public void test_saveStudent() {
        Student student = Student.builder()
                .firstName("John")
                .lastName("Doe")
                .emailId("john@email.com")
                .build();

        studentRepository.save(student);
    }

    @Test
    public void test_saveStudentWithGuardian() {
        Guardian guardian = Guardian.builder()
                .name("Martha")
                .email("martha@email.com")
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


    // custom query
    @Test
    public void test_getStudentsByFirstName() {
        List<Student> students = studentRepository.findAllByFirstName("John");
        for (Student student : students) {
            System.out.println(student);
        }
    }

    @Test
    public void test_getStudentsByFirstNameContaining() {
        List<Student> students = studentRepository.findAllByFirstNameContaining("oh");
        for (Student student : students) {
            System.out.println(student);
        }
    }

    @Test
    public void test_getStudentsByGuardianEmail() {
        List<Student> students = studentRepository.findAllByGuardianEmail("martha@email.com");
        for (Student student : students) {
            System.out.println(student);
        }
    }

    @Test
    public void test_getStudentsByFirstNameAndLastName() {
        System.out.println(studentRepository.findFirstByFirstNameAndLastName("Jane", "Doe"));
    }


    /**
     * '@Query' -> when need to define custom query without following spring data's naming convention
     * benefits: customization | efficiency | readability | maintainability | support for complex conditions
     */
    @Test
    public void test_getStudentByEmail() {
        Student student = studentRepository.getStudentByEmailAddress("jane@email.com");
        System.out.println(student);
    }

    @Test
    public void test_getStudentFirstNameByEmail() {
        System.out.println(studentRepository.getStudentFirstNameByEmailAddress("jane@email.com"));
    }


    /**
     * native -> when we need to have complex queries in sql
     * benefits: customization | efficiency | readability | maintainability | support for complex conditions
     */
    @Test
    public void test_getStudentByEmailNative() {
        Student student = studentRepository.getStudentByEmailAddressNative("jane@email.com");
        System.out.println(student);
    }


    // query named params
    @Test
    public void test_getStudentByEmailNativeNamedParams() {
        Student student = studentRepository.getStudentByEmailAddressNativeNamedParam("jane@email.com");
        System.out.println(student);
    }


    // update and delete
    @Test
    public void test_updateStudentFirstNameByEmailId() {;
        int rows = studentRepository.updateStudentFirstNameByEmailId("Jane F.", "jane@email.com");
        System.out.println(rows);
    }

    @Test
    public void test_deleteStudentByEmailId() {
        int rows = studentRepository.deleteStudentByEmailId("john3@email.com");
        System.out.println(rows);
    }


    /**
     * special case -> update by save
     * transactions managed automatically | less efficient -> fetch and save, not directly update
     */
    @Test
    public void test_updateStudent() {
        Optional<Student> optionalStudent = studentRepository.findById(2L);
        if (optionalStudent.isEmpty()) {
            System.out.println("Student not found");
            return;
        }

        Student student = optionalStudent.get();
        student.setFirstName("Jane");

        System.out.println(studentRepository.save(student));
    }
}
package com.demo.springDataJpaDemo.repository;

import com.demo.springDataJpaDemo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    // custom
    List<Student> findAllByFirstName(String firstName);

    List<Student> findAllByFirstNameContaining(String name);

    List<Student> findAllByGuardianEmail(String email);

    List<Student> findFirstByFirstNameAndLastName(String firstName, String lastName);


    // query
    Student findStudentByEmailId(String emailId);

    // JPQL(Java Persistence Query Language) query -> not SQL
    @Query("SELECT s FROM Student s WHERE s.emailId = ?1")
    Student getStudentByEmailAddress(String emailId);

    @Query("SELECT s.firstName FROM Student s WHERE s.emailId = ?1")
    String getStudentFirstNameByEmailAddress(String emailId);


    // native
    @Query(
            value = "SELECT * FROM students s WHERE s.email_address = ?1",
            nativeQuery = true
    )
    Student getStudentByEmailAddressNative(String emailId);


    // query named params
    @Query(
            value = "SELECT * FROM students s WHERE s.email_address = :emailId",
            nativeQuery = true
    )
    Student getStudentByEmailAddressNativeNamedParam(@Param("emailId") String emailId);
}

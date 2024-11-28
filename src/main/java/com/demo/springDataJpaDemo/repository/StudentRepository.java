package com.demo.springDataJpaDemo.repository;

import com.demo.springDataJpaDemo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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


    // updating and deleting data
    // update & delete -> not support by naming convention, cannot be derived directly from method names, should be defined using @Query
    // for update this is not a good practice -> use save() method instead
    @Modifying
    @Transactional
    @Query(
            value = "UPDATE students SET first_name = ?1 WHERE email_address = ?2",
            nativeQuery = true
    )
    int updateStudentFirstNameByEmailId(String firstName, String emailId);      // return value -> number of rows affected


    @Modifying
    @Transactional
    @Query(
            value = "DELETE FROM students WHERE email_address = ?1",
            nativeQuery = true
    )
    int deleteStudentByEmailId(String emailId);     // returns value -> number of rows affected
}

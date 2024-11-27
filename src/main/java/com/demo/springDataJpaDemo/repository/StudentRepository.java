package com.demo.springDataJpaDemo.repository;

import com.demo.springDataJpaDemo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findAllByFirstName(String firstName);

    List<Student> findAllByFirstNameContaining(String name);

    List<Student> findAllByGuardianEmail(String email);

    List<Student> findFirstByFirstNameAndLastName(String firstName, String lastName);
}

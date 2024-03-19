package com.example.Curd_Opration.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.example.Curd_Opration.domain.student;

//
@EnableJpaRepositories("com.example.Curd_Opration.domain")
//@EntityScan(basePackages = "com.example.Curd_Opration.domain")
@Repository
public interface StudentRepository extends JpaRepository<student, Long> {

}
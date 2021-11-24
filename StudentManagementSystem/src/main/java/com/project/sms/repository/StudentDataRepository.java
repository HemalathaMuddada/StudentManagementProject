package com.project.sms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.sms.model.StudentData;

public interface StudentDataRepository extends JpaRepository<StudentData, Long> {

}

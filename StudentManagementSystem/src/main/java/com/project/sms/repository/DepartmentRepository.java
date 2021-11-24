package com.project.sms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.sms.model.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

}
